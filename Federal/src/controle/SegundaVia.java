package controle;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.pdf.Files;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.utilix.ClassLoaders;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import beans.Cliente;
import beans.Mensalidade;

public class SegundaVia {
	/*
	 * banco=341
	 * moeda=9
	 * carteira=174
	 * agencia=71450
	 * conta corrente=3679
	 * DACContaCorrente=9
	 * complemento=000
	 */
	private int DACBarras;
	private String nossoNumero;
	private int DACNossoNumero;

	public static void main(String[] args) {
SegundaVia g=new SegundaVia();
Mensalidade ms=new Mensalidade();
ms.setNossoNumero("05756824");
		g.CodigoBarras(ms);
	}

	/*
	 * Dados para geração do boleto: Data de vencimento valor Numero do contrato
	 * Nome do cliente Endereço Numero Bairro CEP Estado Cidade
	 */

//Segunda via de boleto anteriormente impresso, aproveitando nosso número.
	private Boleto segundaViaBoleto(Mensalidade mensalidade, Cliente cliente) {
		Cedente cedente;
		Sacado sacado;
		Endereco enderecoSac;
		ContaBancaria contaBancaria;
		Titulo titulo;
		//SacadorAvalista sacadorAvalista;
		Boleto boleto;
		cedente = new Cedente("FEDERAL ORGANIZAÇÃO N C F LTDA",	"00.447.519/0001-12");System.out.println(cliente.getCpf());
		sacado = new Sacado(cliente.getNome(), cliente.getCpf());

		enderecoSac = new Endereco();
		enderecoSac.setLocalidade(cliente.getCidade());
		enderecoSac.setCep(cliente.getCep());
		enderecoSac.setBairro(cliente.getBairro());
		enderecoSac.setLogradouro(cliente.getLogradouro());
		sacado.addEndereco(enderecoSac);

		contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
		contaBancaria.setNumeroDaConta(new NumeroDaConta(3679, "9"));
		contaBancaria.setCarteira(new Carteira(104));
		contaBancaria.setAgencia(new Agencia(714, "5"));

		titulo = new Titulo(contaBancaria, sacado, cedente);
		titulo.setNumeroDoDocumento("");
		titulo.setNossoNumero(mensalidade.getNossoNumero());
		titulo.setDigitoDoNossoNumero(mensalidade.getDacNossoNumero());
		titulo.setValor(BigDecimal.valueOf(mensalidade.getValorParcela()));
		titulo.setDataDoDocumento(new Date());
		titulo.setDataDoVencimento(mensalidade.getDataVencimento());
		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
		// titulo.setAceite(Aceite.A);
		titulo.setDesconto(BigDecimal.ZERO);
		titulo.setDeducao(BigDecimal.ZERO);
		titulo.setMora(BigDecimal.ZERO);
		titulo.setAcrecimo(BigDecimal.ZERO);
		titulo.setValorCobrado(BigDecimal.ZERO);
		boleto = new Boleto(titulo);

		boleto.setLocalPagamento("ATÉ O VENCIMENTO, PAGÁVEL EM QUALQUER AGÊNCIA BANCÁRIA");
		boleto.setInstrucaoAoSacado("");
		boleto.setInstrucao1("ATÉ O VENCIMENTO PAGÁVEL EM QUALQUER AGÊNCIA BANCÁRIA");
		boleto.setInstrucao2("APÓS O VENCIMENTO PAGUE NO ITAÚ");
		boleto.setInstrucao3("ATRASO GERA CARÊNCIA CONFORME ESTIPULADO EM CONTRATO");
		boleto.setInstrucao4("E O NÃO ATENDIMENTO EM CASO DE ÓBITO");
		boleto.setInstrucao5("SR. CAIXA NÃO RECEBER APÓS 30 DIAS DE VENCIMENTO.");

		
		return boleto;
		//exibeBoleto(arquivoPdf);
		//exibeNavegador(boleto);
	}
	
	//Exibe o boleto no Desktop utilizando o programa padrão
	public void exibeBoleto(Mensalidade mensalidade,Cliente cliente) {
		Boleto boleto=new Boleto();
		boleto=segundaViaBoleto(mensalidade,cliente);
		BoletoViewer boletoViewer = new BoletoViewer(boleto);
		File arquivoPdf = boletoViewer.getPdfAsFile("C:/Federal/2Via/Contrato-"+ cliente.getNumeroContrato() + ".pdf");
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		try {
			desktop.open(arquivoPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Exibe o boleto no Navegador padrão
	public void exibeNavegador(Mensalidade mensalidade, Cliente cliente){
		Boleto boleto=new Boleto();
		boleto=segundaViaBoleto(mensalidade,cliente);
		BoletoViewer boletoViewer=new BoletoViewer(boleto);
		byte[]pdfAsBytes=boletoViewer.getPdfAsByteArray();
		FacesContext context=FacesContext.getCurrentInstance();
		HttpServletResponse response=(HttpServletResponse)context.getExternalContext().getResponse();
		
		try {
			response.setContentType("aplication/pdf");
			response.setHeader("Content-Disposition", "inline;filename=boleto.pdf");
			OutputStream output=response.getOutputStream();
			output.write(pdfAsBytes);
			response.flushBuffer();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();System.out.println("Messagem: "+e.getMessage()+"Causa: "+e.getCause());
		}
	}
	//Gera o código de barras, DAC nosso número e representação numérica
	private String CodigoBarras(Mensalidade m) {
		System.out.println(m.getNossoNumero());
		String barras = "";
		//valor da parte 1=204
		//int parte1[] = { 3, 4, 1, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 7, 4 };
		int parte2[] = new int[8];
		//valor da parte 3=301
		//int parte3[] = { 7, 1, 4, 5, 0, 3, 6, 7, 9, 0, 0, 0 };
		nossoNumero = m.getNossoNumero();
		int total=0,mod=8;
		for (int i = 0; i < 8; i++) {
			parte2[i] = Integer.parseInt(nossoNumero.charAt(i) + "");System.out.println("Valor: "+parte2[i]+" Indice: "+i);
		}
		DACNossoNumero=DACNossoNumero(parte2);
		total=DACNossoNumero*7;
		for(int i=7;i>=0;i--){
			total+=parte2[i]*mod;System.out.println("Total: "+total+" - Valor: "+parte2[i]+"Mod: "+mod+" Soma: "+(parte2[i]*mod));
			if(mod==9){
				mod=2;
			}else{
				mod++;
			}
		}
		total=204+301+total;System.out.println("Total das somas de parte1 e parte2:"+total);
		total=total%11;System.out.println("Total mod11: "+total);
		DACBarras=11-total;System.out.println("DAC: "+DACBarras);
		barras="3419"+DACBarras+"00000000000000174"+nossoNumero+DACNossoNumero+"7145036799000";
		m.setCodBarras(barras);
		m.setDacNossoNumero(Integer.toString(DACNossoNumero));
		String representacao=representacaoNumerica(parte2);
		System.out.println("Código de barras: "+barras+"\n Representação numérica: "+representacao+"\nNosso número + DAC: "+m.getNossoNumero()+"-"+m.getDacNossoNumero());
		return barras;
	}
	//Gera segunda via de carnê pré impresso
	public void groupInPages(List<Mensalidade>mensalidades, Cliente cliente){
		//Informando o template personalizado:
		File templatePersonalizado=new File("C:/Federal/Templates/BoletoCarne.pdf");
		File arq=null;
		Boleto boleto=new Boleto();
		List<Boleto>boletos = new ArrayList<Boleto>();
		for(Mensalidade m:mensalidades){
			boleto=segundaViaBoleto(m,cliente);
			boletos.add(boleto);
			boleto=new Boleto();
		}
		BoletoViewer boletoViewer=new BoletoViewer(boletos.get(0));
		boletoViewer.setTemplate(templatePersonalizado);
		List<byte[]>boletosEmBytes=new ArrayList<byte[]>(boletos.size());
		//Adicionando os PDF, em forma de array de bytes, na lista.
		for(Boleto b:boletos){
			boletosEmBytes.add(boletoViewer.setBoleto(b).getPdfAsByteArray());
		}
		try {
			//Criando o arquivo com os boletos da lista.
			arq=Files.bytesToFile("C:/Federal/Carne/Carne"+cliente.getNumeroContrato()+".pdf", mergeFilesInPages(boletosEmBytes));
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
			desktop.open(arq);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private byte[]mergeFilesInPages(List<byte[]>pdfFilesAsByteArray)throws DocumentException, IOException{
		Document document=new Document();
		ByteArrayOutputStream byteOS=new ByteArrayOutputStream();
		PdfWriter writer= PdfWriter.getInstance(document, byteOS);
		document.open();
		PdfContentByte cb=writer.getDirectContent();
		float positionAnterior=0;
		//Para cada arquivo da lista, cria-se um PDFReader, responsavel por ler o arquivo PDF e recuperar informações dele.
		for(byte[] pdfFile:pdfFilesAsByteArray){
			PdfReader reader=new PdfReader(pdfFile);
			//Faz o processo de mesclagem por página do arquivo, começando pela de número 1.
			for(int i=1;i<=reader.getNumberOfPages();i++){
				float documentHeight=cb.getPdfDocument().getPageSize().getHeight();
				//Importa a página de PDF de origem.
				PdfImportedPage page=writer.getImportedPage(reader, i);
				
				float pagePosition=positionAnterior;
				/*
				 * Se a altura restante no documento de destino for menor que a altura do documento,
				 * cria-se uma nova página no documento de destino.
				 */
				if((documentHeight-positionAnterior)<=page.getHeight()){
				document.newPage();
				pagePosition=0;
				positionAnterior=0;
				}
				//Adiciona a página ao PDF destino
				cb.addTemplate(page, 0, pagePosition);
				positionAnterior+=page.getHeight();
			}
	}
		byteOS.flush();
		document.close();
		byte[]arquivoEmBytes=byteOS.toByteArray();
		byteOS.close();
		return arquivoEmBytes;
	}
	
	//Gera o digito de verificação do nosso número(DAC)
	private int DACNossoNumero(int[] nossoNumero) {
		int dac = 0;
		// valor base= mod 10 destes números: 7,1,4,5,0,3,6,7,9,1,7,4=57;
		int soma =mod10(nossoNumero);
		int base = 57;
		soma = base + soma;
		dac = soma % 10;
		dac = 10 - dac;
		System.out.println("Dac Nosso Número: " + dac);
		return dac;
	}
	
	//Gera a representação numérica
	private String representacaoNumerica(int[]NN){
		int parte1[]={3,4,1,9,1,7,4,NN[0],NN[1]};
		int parte2[]={NN[2],NN[3],NN[4],NN[5],NN[6],NN[7],DACNossoNumero,7,1,4};
		int parte3[]={5,0,3,6,7,9,9,0,0,0};
		//int parte5[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int dac1, dac2, dac3, soma;
		soma=mod10(parte1);
		dac1=soma%10;
		dac1=10-dac1;
		
		soma=mod10(parte2);
		dac2=soma%10;
		dac2=10-dac2;
		
		soma=mod10(parte3);
		dac3=soma%10;
		dac3=10-dac3;
		
		String representacao="34191.74"+NN[0]+NN[1]+dac1+" "+NN[2]+NN[3]+NN[4]+NN[5]+NN[6]+"."+NN[7]+DACNossoNumero+"714"+dac2+" "+"50367.99000"+dac3+" "+DACBarras+" "+"000";
		return representacao;
	}
	//Calculo utilizado para gerar o DAC do nosso número e da representação numérica
	private int mod10(int calculo[]){
		String numero="";
		for(int i=0;i<calculo.length;i++){
			numero+=calculo[i];
		}
		System.out.println("Valor de entrada: "+numero);
		int calcula[] = new int[calculo.length];
		int mod = 2, soma = 0;
		for (int i = calculo.length-1; i >= 0; i--) {
			calcula[i] = calculo[i] * mod;System.out.println("Total: "+calcula[i]+" Valor: "+calculo[i]+" Mod"+mod);
			if (mod == 2) {
				mod = 1;
			} else {
				mod = 2;
			}
			if (calcula[i] > 9) {
				String a = calcula[i] + "";
				int b = Integer.parseInt(a.charAt(0)+"");
				int c = Integer.parseInt(a.charAt(1)+"");
				calcula[i] = b + c;
			}
			soma += calcula[i];
			
		}
		return soma;
	}
}
