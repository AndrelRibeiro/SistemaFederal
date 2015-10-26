package controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import beans.Cliente;

public class ControleArquivoRemessa {
	// HEADER
	private String tipoRegistro;
	private String operacao;
	private String literalRemessa;
	private String codigoServico;
	private String literalServico;
	// private String agencia;
	// private String zeros;
	// private String conta;
	// private String dac;
	private String brancos;
	private String nomeEmpresa;
	// private String codigoBanco;
	private String nomeBanco;
	private String dataGeracao;
	private String brancos5;
	private String tipoReg;// 1
	private String codInsc;// 2
	private String numInsc;// 14
	private String agencia;// 4
	private String zeros;// 2
	private String conta;// 5
	private String dac;// 1
	private String brancos1;// 4
	private String alegacao;// 25
	private String usoEmpresa;// 8
	private String nossoNumero;// 9(8)V9(5)
	private String qtdMoeda;// 3
	private String numCarteira;// 21
	private String usoBanco;// 1
	private String carteira;// 2
	private String codOcorrencia;// 16
	private String numDocumento;// 6
	private String vencimento;// 9(11)V9(2)
	private String valorTitulo;// 3
	private String codigoBanco;// 5
	private String agenciaCobra;// 2
	private String especie;// 1
	private String aceite;// 6
	private String dataEmissao;// 2
	private String instrucao1;// 2
	private String instrucao2;// 2
	private String juros1Dia;// 9(11)V9(2)
	private String desconto;// 6
	private String valorDesconto;// 9(11)V9(2)
	private String valorIOF;// 9(11)V9(2)
	private String abatimento;// 9(11)V9(2)
	private String codigoInscricao;// 2
	private String numeroInscricao;// 14
	private String nome;// 30
	private String brancos2;// 10
	private String logradouro;// 40
	private String bairro;// 12
	private String cep;// 8
	private String cidade;// 15
	private String estado;// 2
	private String sacador;// 30
	private String brancos3;// 4
	private String dataDeMora;// 6
	private String prazo;// 2
	private String brancos4;// 1
	private int numeroSequencial;// 6
	
	public ControleArquivoRemessa(){
		
	}
	public void geraArquivo(List<Cliente>clientes){
		File file=new File("C:/Federal/ArquivoRemessa.rem");
		FileReader fr;
		try {
			boolean e=file.exists();
			if(!e){
				file.createNewFile();
			}
			fr = new FileReader(file);
			BufferedReader bf=new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
