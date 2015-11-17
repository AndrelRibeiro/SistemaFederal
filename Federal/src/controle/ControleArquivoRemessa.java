package controle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dao.ClienteDao;
import dao.ClienteDaoImplementation;
import dao.MensalidadeDao;
import dao.MensalidadeDaoImplementation;
import beans.Cliente;
import beans.Mensalidade;
import beans.Registro;

public class ControleArquivoRemessa {
	List<Registro> registros = new ArrayList<Registro>();
	Registro registro = new Registro();
	// HEADER
	private String tipoRegistro = "0";
	private String operacao = "1";
	private String literalRemessa = "REMESSA";
	private String codigoServico = "01";
	private String literalServico = "COBRANCA       ";
	private String agencia = "7145";
	private String zeros = "00";
	private String conta = "03679";
	private String dac = "9";
	private String brancos;
	private String nomeEmpresa = "FEDERAL ORG. N. C. F. LTDA    ";
	private String codigoBanco = "341";
	private String nomeBanco = "BANCO ITAU SA  ";
	private String dataGeracao = "";
	private String brancos5;
	private String numeroSequencia = "000001";
	// REGISTRO
	private String tipoRegDetal = "1";// 1
	private String codInsc = "02";// 2
	private String numInsc = "00447519000112";// 14
	// private String agencia="7145";// 4
	// private String zeros="00";// 2
	// private String conta="03679";// 5
	// private String dac="9";// 1
	private String brancos1 = String.format("%-4s", "");// X4
	private String alegacao = "0000";// 9(4)
	private String usoEmpresa = String.format("%-25s", "");// X25
	private String nossoNumero = "00000000";// 9(8)
	private String qtdMoeda = "0000000000000";// 9(8)V9(5)
	private String numCarteira = "109";// 9(3) anterior 174
	private String usoBanco = String.format("%-21s", "");// X21
	private String carteira = "I";// X1
	private String codOcorrencia = "01";// 9(2)
	private String numDocumento = String.format("%-10s", "");// X10
	private String vencimento;// 9(6)
	private String valorTitulo;// 9(11)V9(2)
	//private String codigoBanco="341";// 9(3)
	private String agenciaCobra = "00000";// 9(5)
	private String especie = "01";// X2
	private String aceite = "A";// X1
	private String dataEmissao;// 9(6)
	private String instrucao1 = "24";// X2
	private String instrucao2 = "  ";// X2
	private String juros1Dia = "0000000000000";// 9(11)V9(2)
	private String desconto = "000000";// 9(6)
	private String valorDesconto = "0000000000000";// 9(11)V9(2)
	private String valorIOF = "0000000000000";// 9(11)V9(2)
	private String abatimento = "0000000000000";// 9(11)V9(2)
	private String codigoInscricao = "01";// 9(2)
	private String numeroInscricao;// 9(14)
	private String nome;// X30
	private String brancos2 = String.format("%-10s", "");// X10
	private String logradouro;// X40
	private String bairro;// X12
	private String cep;// 9(8)
	private String cidade;// X15
	private String estado;// X2
	private String sacador;// X30
	private String brancos3 = String.format("%-4s", "");// X4
	private String dataDeMora = "000000";// 9(6)
	private String prazo = "00";// 9(2)
	private String brancos4 = String.format("%-1s", "");// X1
	private int numeroSequencial = 1;// 9(6)
	// TRAILER
	private String tipoRegTrailer = "9";
	private String brancos6;

	public static void main(String[] args) {
		

	}

	public ControleArquivoRemessa() {

	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public List<Registro> geraArquivo(List<Mensalidade>mensalidades) {
		// List<Cliente>clientes=new ArrayList<Cliente>();
		Cliente cliente;
		JOptionPane.showMessageDialog(null, "Teste", "Teste de mensagem", JOptionPane.INFORMATION_MESSAGE);
		ClienteDao cd = new ClienteDaoImplementation();
		MensalidadeDao md = new MensalidadeDaoImplementation();
		for (Mensalidade m : mensalidades) {
			cliente = new Cliente();
			cliente = cd.buscar(m.getContrato());
			m.setSituacao("ABERTO");
			m.setNossoNumero(m.getNossoNumero());
			boolean ret=md.alterar(m);
			if(ret){
				System.out.println("Mensalidade atualizada com sucesso: "+m.toString());
			}else{
				System.out.println("Erro ao atualizar mensalidade: "+m.toString());
			}
			registro.setNome(cliente.getNome());
			registro.setCPF(cliente.getCpf());
			registro.setEndereco(cliente.getLogradouro());
			registro.setBairro(cliente.getBairro());
			registro.setCidade(cliente.getCidade());
			registro.setEstado(cliente.getEstado());
			registro.setCep(cliente.getCep());
			registro.setValorTitulo(String.valueOf(m.getValorParcela()));
			String valor = registro.getValorTitulo().replace(".", "");
			valor=valor+"0";
			System.out.println("Replace: " + valor);
			registro.setValorTitulo(valor);
			String dataV = new SimpleDateFormat("ddMMyy").format(m
					.getDataVencimento());
			registro.setVencimento(dataV);
			System.out.println("Vencimento: " + dataV);
			registros.add(registro);
			registro = new Registro();

		}
		Calendar c = Calendar.getInstance();
		Date atual = c.getTime();
		String data = new SimpleDateFormat("ddMMyy").format(atual);
		dataGeracao = data;
		// Linha Header do arquivo
		brancos = String.format("%-8s", "");
		brancos5 = String.format("%-294s", "");
		String header = tipoRegistro + operacao + literalRemessa
				+ codigoServico + literalServico + agencia + zeros + conta
				+ dac + brancos + nomeEmpresa + codigoBanco + nomeBanco
				+ dataGeracao + brancos5 + numeroSequencia;
		// Linha registro do arquivo
		List<String> linhas = new ArrayList<String>();
		linhas.add(header);
		for (Registro r : registros) {
			valorTitulo = numeroSequencial(13, r.getValorTitulo());
			valorTitulo+=r.getValorTitulo();
			System.out.println("Valor do titulo: " + valorTitulo);
			vencimento = r.getVencimento();
			dataEmissao = data;
			String cpf[] = r.getCPF().split("\\.|\\-|\\_|\\*|\\/|\\ |\\,|\\+");
			String cpfv = "";
			for (int z = 0; z < cpf.length; z++) {
				cpfv += cpf[z];
			}
			numeroInscricao = cpfv + "   ";
			int t = 30 - r.getNome().length();
			nome = r.getNome() + String.format("%-" + t + "s", "");
			nome = nome.substring(0, 30);
			System.out.println("Tamanho do nome: "+nome.length());
			int t2 = 40 - r.getEndereco().length();
			logradouro = r.getEndereco() + String.format("%-" + t2 + "s", "");
			logradouro = logradouro.substring(0, 40);
			System.out.println(logradouro);
			int tam = r.getBairro().length();
			int t3;
			if (tam > 12) {
				t3 = tam - 12;
				bairro = r.getBairro().substring(t3, tam);
			} else {
				t3 = 12 - tam;
				bairro = r.getBairro() + String.format("%-" + t3 + "s", "");
			}
			System.out.println(bairro);
			int t4 = 15 - r.getCidade().length();
			cidade = r.getCidade() + String.format("%-" + t4 + "s", "");
			cidade = cidade.substring(0, 15);
			System.out.println(cidade);
			estado = "SP";
			cep = r.getCep().replace("-", "");
			sacador = nome;
			numeroSequencial++;
			String nn = numeroSequencial + "";
			String n = numeroSequencial(6, nn);
			String registro = tipoRegDetal + codInsc + numInsc + agencia
					+ zeros + conta + dac + brancos1 + alegacao + usoEmpresa
					+ nossoNumero + qtdMoeda + numCarteira + usoBanco
					+ carteira + codOcorrencia + numDocumento + vencimento
					+ valorTitulo + codigoBanco + agenciaCobra + especie
					+ aceite + dataEmissao + instrucao1 + instrucao2
					+ juros1Dia + desconto + valorDesconto + valorIOF
					+ abatimento + codigoInscricao + numeroInscricao + nome
					+ brancos2 + logradouro + bairro + cep + cidade + estado
					+ sacador + brancos3 + dataDeMora + prazo + brancos4 + n+numeroSequencial;
			linhas.add(registro);
		}
        numeroSequencial++;
		// Linha trailer do arquivo
		String n2 = numeroSequencial + "";
		String sequencia = numeroSequencial(6, n2);

		brancos6 = String.format("%-393s", "");
		String trailer = tipoRegTrailer + brancos6 + sequencia+ numeroSequencial;
		linhas.add(trailer);
		File file = new File("C:/Federal/ArquivoRemessa/AR"+data+".rem");
		boolean e = file.exists();
		if (!e) {
			// file.mkdir();
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bfw = new BufferedWriter(fw);
			for (String s : linhas) {
				String NFD=Normalizer.normalize(s, Normalizer.Form.NFD);
		        s=NFD.replaceAll("[^\\p{ASCII}]", "");
				bfw.write(s);
				System.out.println("Tamanho da linha: " + s.length());
				bfw.newLine();
			}
			bfw.flush();
			bfw.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return registros;
	}

	public String numeroSequencial(int casas, String numero) {
		// int num=numero;
		String num = numero;
		String zero = "";
		while ((zero + num).length() < casas) {
			zero += "0";
			System.out.println("String de numeral: " + zero + num);
		}
		return zero;
	}
}
