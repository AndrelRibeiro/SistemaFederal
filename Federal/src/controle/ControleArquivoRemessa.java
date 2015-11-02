package controle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import beans.Cliente;

public class ControleArquivoRemessa {
	// HEADER
	private String tipoRegistro="0";
	private String operacao="1";
	private String literalRemessa="REMESSA";
	private String codigoServico="01";
	private String literalServico="COBRANCA       ";
	private String agencia="7145";
	private String zeros="00";
	private String conta="03679";
	private String dac="9";
	private String brancos;
	private String nomeEmpresa="FEDERAL ORG. N. C. F. LTDA    ";
	private String codigoBanco="341";
	private String nomeBanco="BANCO ITAU SA  ";
	private String dataGeracao="";
	private String brancos5;	
	private String numeroSequencia="000001";
	//REGISTRO
	private String tipoRegDetal;// 1
	private String codInsc;// 2
	private String numInsc;// 14
	//private String agencia;// 4
	//private String zeros;// 2
	//private String conta;// 5
	//private String dac;// 1
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
	//private String codigoBanco;// 5
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
	private int numeroSequencial=2;// 6
	//TRAILER
	private String tipoRegTrailer="9";
	private String brancos6;
	public static void main(String[]args){
		ControleArquivoRemessa gr=new ControleArquivoRemessa();
		
		gr.geraArquivo();
		
	}
	public ControleArquivoRemessa(){
		
	}
	public void geraArquivo(){
		List<Cliente>clientes=new ArrayList<Cliente>();
		Calendar c= Calendar.getInstance();
		Date atual=c.getTime();
		String data=new SimpleDateFormat("ddMMyy").format(atual);		
		dataGeracao=data;
		//Linha Header do arquivo
		brancos=String.format("%-8s", "");
		brancos5=String.format("%-294s", "");
		System.out.println("Tamanho da string: "+brancos5.length());
		String header=tipoRegistro+operacao+literalRemessa+codigoServico+literalServico+agencia+zeros+conta+dac+brancos+nomeEmpresa+codigoBanco+nomeBanco+dataGeracao+brancos5+numeroSequencia;
		//Linha registro do arquivo
		List<String>linhas=new ArrayList<String>();
		for(Cliente cliente:clientes){
			
		}
		
		//Linha trailer do arquivo
		String num=numeroSequencial+"";
		String zero="";
		while((zero+num).length()<6){
			zero+="0";
			System.out.println(zero+num);
		}
		
		brancos6=String.format("%-393s", "");
		String trailer=tipoRegTrailer+brancos6+zero+numeroSequencial;
		File file=new File("C:/Federal/ArquivoRemessa/ArquivoRemessa.rem");System.out.println("Header: "+header.length()+"\nTrailer: "+trailer.length());
		boolean e=file.exists();
		if(!e){
			//file.mkdir();
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			FileWriter fw=new FileWriter(file);
			BufferedWriter bfw=new BufferedWriter(fw);
			bfw.write(header);
			bfw.newLine();
			bfw.write(trailer);
			bfw.flush();
			bfw.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}

