package controle;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.LinhaDigitavel;
import org.jrimum.bopepo.pdf.CodigoDeBarras;
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

import beans.Cliente;
import beans.Mensalidade;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import dao.MensalidadeDao;
import dao.MensalidadeDaoImplementation;
import dao.NossoNumeroDao;
import dao.NossoNumeroDaoImplementation;

public class SegundaVia {
	/*
	 * banco=341 moeda=9 carteira=174 agencia=71450 conta corrente=3679
	 * DACContaCorrente=9 complemento=000
	 */
	private int DACBarras;
	private String nossoNumero;
	private int DACNossoNumero;
	private String valor;
	private long fator;
	private String representacaoNumerica;
	
	public static void main(String[] args) {
		SegundaVia g = new SegundaVia();
		NossoNumeroDao nnd=new NossoNumeroDaoImplementation();
		int n=nnd.buscar();System.out.println("Resultado: "+n);
		int nossoN[]=new int[8];
		String nosso=String.valueOf(n);System.out.println("Resultado String nosso: "+nosso);
		for(int i=0;i<8;i++){
			nossoN[i]=Integer.parseInt(String.valueOf(nosso.charAt(i)));System.out.println("Resultado charAt: "+nosso.charAt(i));
			System.out.println(" - Indice: "+nossoN[i]);
		}
		
		System.out.println("DAC: "+g.DACNossoNumero(nossoN));
	}

	/*
	 * Dados para geração do boleto: Data de vencimento valor Numero do contrato
	 * Nome do cliente Endereço Numero Bairro CEP Estado Cidade
	 */

	// Segunda via de boleto anteriormente impresso, aproveitando nosso número.
	private Boleto geraBoleto(Mensalidade mensalidade, Cliente cliente) {
		Cedente cedente;
		Sacado sacado;
		Endereco enderecoSac;
		Endereco enderecoSacAval;
		ContaBancaria contaBancaria;
		SacadorAvalista sacadorAvalista;
		Titulo titulo;
		Boleto boleto;
		cedente = new Cedente("FEDERAL ORGANIZAÇÃO N C F LTDA","00.447.519/0001-12");
		System.out.println(cliente.getCpf());
		sacado = new Sacado(cliente.getNome(), cliente.getCpf());
		

		enderecoSac = new Endereco();
		enderecoSac.setLocalidade(cliente.getCidade());
		enderecoSac.setCep(cliente.getCep());
		enderecoSac.setBairro(cliente.getBairro());
		enderecoSac.setLogradouro(cliente.getLogradouro());
		sacado.addEndereco(enderecoSac);

		contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
		contaBancaria.setNumeroDaConta(new NumeroDaConta(3679, "9"));
		contaBancaria.setCarteira(new Carteira(109));
		contaBancaria.setAgencia(new Agencia(714, "5"));

		enderecoSacAval=new Endereco();
		enderecoSacAval.setLogradouro("");
		enderecoSacAval.setBairro("");
		enderecoSacAval.setNumero("");
		enderecoSacAval.setCep("");

		//sacadorAvalista=new SacadorAvalista("FEDERAL ORGANIZAÇÃO N C F LTDA Rua João Belo, 11 CEP 08011-040 São Paulo - SP", "00.447.519/0001-12");
		//sacadorAvalista.addEndereco(enderecoSacAval);
		
		String codigoDeBarras=codigoBarras(mensalidade);
		Image img=CodigoDeBarras.valueOf(codigoDeBarras).toImage();
		
		titulo = new Titulo(contaBancaria, sacado, cedente);
		titulo.setNumeroDoDocumento(mensalidade.getNossoNumero());
		titulo.setNossoNumero(mensalidade.getNossoNumero());
		titulo.setDigitoDoNossoNumero(mensalidade.getDacNossoNumero());
		titulo.setValor(BigDecimal.valueOf(mensalidade.getValorParcela()));
		titulo.setDataDoDocumento(new Date());
		titulo.setDataDoVencimento(mensalidade.getDataVencimento());
		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
		titulo.setDesconto(BigDecimal.ZERO);
		titulo.setDeducao(BigDecimal.ZERO);
		titulo.setMora(BigDecimal.ZERO);
		titulo.setAcrecimo(BigDecimal.ZERO);
		titulo.setValorCobrado(BigDecimal.ZERO);
		//titulo.setSacadorAvalista(sacadorAvalista);
		boleto = new Boleto(titulo);
		
		String carteiraNossoNumero=String.format("%s-%s", 
		titulo.getNossoNumero(),titulo.getDigitoDoNossoNumero());  
		boleto.addTextosExtras("txtFcNossoNumero", titulo.getContaBancaria().getCarteira().getCodigo()+"/"+carteiraNossoNumero); 
		boleto.addTextosExtras("txtRsNossoNumero", carteiraNossoNumero); 
		
		/*String agenciaCodigoDoCedenteParaExibicao = String.format("%04d/%05d-%s",
		titulo.getContaBancaria().getAgencia().getCodigo(),
		titulo.getContaBancaria().getNumeroDaConta().getCodigoDaConta(),
		titulo.getContaBancaria().getNumeroDaConta().getDigitoDaConta());*/
		String personalizado="7145/03679-9";
		boleto.addTextosExtras("txtFcAgenciaCodigoCedente", personalizado);
		boleto.addTextosExtras("txtRsAgenciaCodigoCedente", personalizado);
		//boleto.addTextosExtras("txtFcAgenciaCodigoCedente", agenciaCodigoDoCedenteParaExibicao); 
		//boleto.addTextosExtras("txtRsAgenciaCodigoCedente", agenciaCodigoDoCedenteParaExibicao); 
			
		boleto.setLocalPagamento("");
		boleto.setInstrucaoAoSacado("");
		//boleto.setInstrucao1("ATÉ O VENCIMENTO PAGÁVEL EM QUALQUER AGÊNCIA BANCÁRIA");
		//boleto.setInstrucao2("APÓS O VENCIMENTO PAGUE NO ITAÚ");
		boleto.setInstrucao1("ATRASO GERA CARÊNCIA CONFORME ESTIPULADO EM CONTRATO");
		boleto.setInstrucao2("E O NÃO ATENDIMENTO EM CASO DE ÓBITO");
		boleto.setInstrucao3("SR. CAIXA NÃO RECEBER APÓS 30 DIAS DE VENCIMENTO.");
		boleto.addTextosExtras("txtRsNumeroParcela", String.valueOf(mensalidade.getNumParcela()));
		boleto.addTextosExtras("txtFcCnpj", "00.447.519/0001-12");
		boleto.addTextosExtras("txtFcCnpj1", "00.447.519/0001-12");
		boleto.addTextosExtras("txtFcAvalista", "FEDERAL ORGANIZAÇÃO LTDA Rua João Belo, 11 CEP 08011-040 São Paulo - SP");
		boleto.addTextosExtras("txtRsAvalista1", "FEDERAL ORGANIZAÇÃO LTDA");
		boleto.addTextosExtras("txtRsAvalista2", "Rua João Belo, 11 CEP 08011-040 São Paulo - SP");
		boleto.addImagensExtras("txtFcCodigoBarra", new ImageIcon(img).getImage());
		boleto.addTextosExtras("txtRsLinhaDigitavel", representacaoNumerica);
		return boleto;
		// exibeBoleto(arquivoPdf);
		// exibeNavegador(boleto);
	}

	public void exibeBoleto2(Mensalidade mensalidade, Cliente cliente){
			// Informando o template personalizado:
			
			File templatePersonalizado= new File("C:/Federal/Templates/BoletoCarne.pdf");
			File arq = null;
			Boleto boleto = new Boleto();
			List<Boleto> boletos = new ArrayList<Boleto>();
				
			
				int nosso=Integer.parseInt(mensalidade.getNossoNumero());
				if(nosso==0){
					mensalidade=geraNossoNumero(mensalidade);
					
				}
				boleto = geraBoleto(mensalidade, cliente);
				boletos.add(boleto);
				boleto = new Boleto();
			
			
			BoletoViewer boletoViewer = new BoletoViewer(boletos.get(0));
			boletoViewer.setTemplate(templatePersonalizado);
			List<byte[]> boletosEmBytes = new ArrayList<byte[]>(boletos.size());
			// Adicionando os PDF, em forma de array de bytes, na lista.
			for (Boleto b : boletos) {
				boletosEmBytes.add(boletoViewer.setBoleto(b).getPdfAsByteArray());
			}
			try {
				// Criando o arquivo com os boletos da lista.
				Calendar c=Calendar.getInstance();
				String data=new SimpleDateFormat("dd-MM-yyyy").format(c.getTime());
				arq = Files.bytesToFile("C:/Federal/2Via/Boleto/Cliente - "+cliente.getNome()+".pdf", mergeFilesInPages(boletosEmBytes));
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
	
	// Exibe o boleto no Desktop utilizando o programa padrão
	public void exibeBoleto(Mensalidade mensalidade, Cliente cliente) {
		Boleto boleto = new Boleto();
		boleto = geraBoleto(mensalidade, cliente);
		BoletoViewer boletoViewer = new BoletoViewer(boleto);
		File arquivoPdf = boletoViewer.getPdfAsFile("C:/Federal/2Via/Boleto/Contrato-"+ cliente.getNumeroContrato() + ".pdf");
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		try {
			desktop.open(arquivoPdf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Exibe o boleto no Navegador padrão
	public void exibeNavegador(Mensalidade mensalidade, Cliente cliente) {
		Boleto boleto = new Boleto();
		boleto = geraBoleto(mensalidade, cliente);
		BoletoViewer boletoViewer = new BoletoViewer(boleto);
		byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		try {
			response.setContentType("aplication/pdf");
			response.setHeader("Content-Disposition","inline;filename=boleto.pdf");
			OutputStream output = response.getOutputStream();
			output.write(pdfAsBytes);
			response.flushBuffer();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("Messagem: " + e.getMessage() + "Causa: "
					+ e.getCause());
		}
	}

	// Gera o código de barras, DAC nosso número e representação numérica
	private String codigoBarras(Mensalidade m) {
		System.out.println(m.getNossoNumero());
		String barras = "";
		String data=new SimpleDateFormat("dd/MM/yyyy").format(m.getDataVencimento());
		String dataVenc[]=data.split("/");
		Calendar c=Calendar.getInstance();
		c.set(2000, 07, 3);
		Calendar d=Calendar.getInstance();
		int dia=Integer.parseInt(dataVenc[0])+1;
		d.set(Integer.parseInt(dataVenc[2]),Integer.parseInt(dataVenc[1]),dia);
		
		//GregorianCalendar base=new GregorianCalendar(2000,Calendar.JULY,3);//=1000
		//GregorianCalendar vencimento=new GregorianCalendar(Integer.parseInt(dataVenc[2]),Integer.parseInt(dataVenc[1]),Integer.parseInt(dataVenc[0]));
		long diferenca=d.getTimeInMillis()-c.getTimeInMillis();
		fator=diferenca/(24*60*60*1000);
		fator=fator+1000;
		String fatorString=String.valueOf(fator);
		int fatorV[]=new int[4];
		for(int i=0;i<4;i++){
		fatorV[i]=Integer.parseInt(String.valueOf(fatorString.charAt(i)));
		}
		// antiga carteira: 174 nova carteira: 109
		// valor da parte 1=204
		int parte1[] = { 3, 4, 1, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for(int f=0;f<4;f++){
			parte1[f+4]=fatorV[f];
		}
		String v=String.valueOf(m.getValorParcela());
		System.out.println("Valor parcela: "+v);
		String valorArr[]=v.split("\\.");System.out.println(Arrays.toString(valorArr));
		if(valorArr[1].length()==2){
			valor=valorArr[0]+valorArr[1];	
		}else{
		valor=valorArr[0]+valorArr[1]+"0";
		}
		int t=valor.length();
		t=10-t;
		for(int f=0;f<t;f++){
			valor="0"+valor;
		}
		for(int i=0;i<9;i++){
			parte1[i+8]=Integer.parseInt(String.valueOf(valor.charAt(i)));
		}
				
		int parte2NossoNumero[] = new int[8];
		// valor da parte 3=301
		// int parte3[] = { 7, 1, 4, 5, 0, 3, 6, 7, 9, 9, 0, 0, 0 };
		
		nossoNumero = m.getNossoNumero();
		int total = 0, mod = 4;
		for (int i = 0; i < 8; i++) {
			parte2NossoNumero[i] = Integer.parseInt(String.valueOf(nossoNumero.charAt(i)));
			parte1[i+21]=parte2NossoNumero[i];
			System.out.println("Valor: " + parte2NossoNumero[i] + " Indice: " + i);
		}
		DACNossoNumero = DACNossoNumero(parte2NossoNumero);
		parte1[29]=DACNossoNumero;
		for(int f=0;f<30;f++){
			total+=mod*parte1[f];
			System.out.println("Total: " + total + " - Valor: " + parte1[f] 
					+ " Mod: " + mod + " multiplicação: " + (parte1[f] * mod));
			if (mod == 2) {
				mod = 9;
			} else {
				mod--;
			}
		}
		total = 301 + total;
		System.out.println("Total das somas de parte1 e parte2:" + total);
		total = total % 11;
		System.out.println("Total mod11: " + total);
		DACBarras = 11 - total;
		System.out.println("DAC: " + DACBarras);
		barras = "3419" + DACBarras + fator+valor+"109" + nossoNumero+ DACNossoNumero + "7145036799000";
		m.setCodBarras(barras);
		m.setDacNossoNumero(Integer.toString(DACNossoNumero));
		representacaoNumerica = representacaoNumerica(parte2NossoNumero);
		System.out.println("Código de barras: " + barras
				+ "\n Representação numérica: " + representacaoNumerica
				+ "\nNosso número + DAC: " + m.getNossoNumero() + "-"
				+ m.getDacNossoNumero());
		return barras;
	}

	// Gera segunda via de carnê pré impresso
	public void groupInPages(List<Mensalidade> mensalidades, Cliente cliente) {
		// Informando o template personalizado:
		
		File templatePersonalizado= new File("C:/Federal/Templates/BoletoCarne.pdf");
		File arq = null;
		Boleto boleto = new Boleto();
		List<Boleto> boletos = new ArrayList<Boleto>();
		for (Mensalidade m : mensalidades) {
			boleto = geraBoleto(m, cliente);
			boletos.add(boleto);
			boleto = new Boleto();
		}
		BoletoViewer boletoViewer = new BoletoViewer(boletos.get(0));
		boletoViewer.setTemplate(templatePersonalizado);
		List<byte[]> boletosEmBytes = new ArrayList<byte[]>(boletos.size());
		// Adicionando os PDF, em forma de array de bytes, na lista.
		for (Boleto b : boletos) {
			boletosEmBytes.add(boletoViewer.setBoleto(b).getPdfAsByteArray());
		}
		try {
			// Criando o arquivo com os boletos da lista.
			arq = Files.bytesToFile("C:/Federal/2Via/Carne/Carne" + cliente.getNumeroContrato()+ ".pdf", mergeFilesInPages(boletosEmBytes));
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
	// Gera boletos para impressão
		public List<Mensalidade> geraArquivoParaImpressao(List<Cliente> clientes) {
			// Informando o template personalizado:
			
			File templatePersonalizado= new File("C:/Federal/Templates/BoletoCarne.pdf");
			File arq = null;
			Boleto boleto = new Boleto();
			MensalidadeDao md=new MensalidadeDaoImplementation();
			List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
			List<Mensalidade>geral=new ArrayList<Mensalidade>();
			List<Boleto> boletos = new ArrayList<Boleto>();
			for(Cliente c:clientes){
				mensalidades=md.listar(c.getNumeroContrato());		
			for (Mensalidade m : mensalidades) {
				int nosso=Integer.parseInt(m.getNossoNumero());
				if(nosso==0){
					m=geraNossoNumero(m);
					
				}
				boleto = geraBoleto(m, c);
				boletos.add(boleto);
				boleto = new Boleto();
				geral.add(m);
			}
			mensalidades=new ArrayList<Mensalidade>();
			}
			BoletoViewer boletoViewer = new BoletoViewer(boletos.get(0));
			boletoViewer.setTemplate(templatePersonalizado);
			List<byte[]> boletosEmBytes = new ArrayList<byte[]>(boletos.size());
			// Adicionando os PDF, em forma de array de bytes, na lista.
			for (Boleto b : boletos) {
				boletosEmBytes.add(boletoViewer.setBoleto(b).getPdfAsByteArray());
			}
			try {
				// Criando o arquivo com os boletos da lista.
				Calendar c=Calendar.getInstance();
				String data=new SimpleDateFormat("dd-MM-yyyy").format(c.getTime());
				arq = Files.bytesToFile("C:/Federal/ImpressaoCarnes/Carnes-"+data+".pdf", mergeFilesInPages(boletosEmBytes));
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
return geral;
		}
	private byte[] mergeFilesInPages(List<byte[]> pdfFilesAsByteArray)throws DocumentException, IOException {
		Document document = new Document();
		ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, byteOS);
		document.open();
		PdfContentByte cb = writer.getDirectContent();
		float positionAnterior = 0;
		// Para cada arquivo da lista, cria-se um PDFReader, responsavel por ler
		// o arquivo PDF e recuperar informações dele.
		for (byte[] pdfFile : pdfFilesAsByteArray) {
			PdfReader reader = new PdfReader(pdfFile);
			// Faz o processo de mesclagem por página do arquivo, começando pela
			// de número 1.
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				float documentHeight = cb.getPdfDocument().getPageSize()
						.getHeight();
				// Importa a página de PDF de origem.
				PdfImportedPage page = writer.getImportedPage(reader, i);

				float pagePosition = positionAnterior;
				/*
				 * Se a altura restante no documento de destino for menor que a
				 * altura do documento, cria-se uma nova página no documento de
				 * destino.
				 */
				if ((documentHeight - positionAnterior) <= page.getHeight()) {
					document.newPage();
					pagePosition = 0;
					positionAnterior = 0;
				}
				// Adiciona a página ao PDF destino
				cb.addTemplate(page, 0, pagePosition);
				positionAnterior += page.getHeight();
			}
		}
		byteOS.flush();
		document.close();
		byte[] arquivoEmBytes = byteOS.toByteArray();
		byteOS.close();
		return arquivoEmBytes;
	}

	// Gera o digito de verificação do nosso número(DAC)
	public int DACNossoNumero(int[] nossoNumero) {
		int dac = 0;System.out.println("ENTRADA Nosso Número EM SEGUNDA VIA: " + Arrays.toString(nossoNumero));
		// valor base= mod 10 destes números: 7,1,4,5,   0,3,6,7,9,    1,7,4=57;
		// valor base= mod 10 destes números: 7,1,4,5,   0,3,6,7,9,    1,0,9=51;
		int soma = mod10(nossoNumero);
		int base = 51;
		soma = base + soma;
		dac = soma % 10;
		dac = 10 - dac;
		if(dac==10){
			dac=0;
		}
		System.out.println("Dac Nosso Número: " + dac);
		return dac;
	}

	// Gera a representação numérica
	private String representacaoNumerica(int[] NN) {
		int parte1[] = { 3, 4, 1, 9, 1, 0, 9, NN[0], NN[1] };
		int parte2[] = { NN[2], NN[3], NN[4], NN[5], NN[6], NN[7],DACNossoNumero, 7, 1, 4 };
		int parte3[] = { 5, 0, 3, 6, 7, 9, 9, 0, 0, 0 };
		// int parte5[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int dac1, dac2, dac3, soma;
		soma = mod10(parte1);
		dac1 = soma % 10;
		dac1 = 10 - dac1;
		if(dac1==10){
			dac1=0;
		}

		soma = mod10(parte2);
		dac2 = soma % 10;
		dac2 = 10 - dac2;
		if(dac2==10){
			dac2=0;
		}
		
		soma = mod10(parte3);
		dac3 = soma % 10;
		dac3 = 10 - dac3;
		if(dac3==10){
			dac3=0;
		}
System.out.println("DAC 1: "+dac1+" DAC 2: "+dac2+" DAC 3: "+dac3);
		String representacao = "34191.09" + NN[0] + NN[1] + dac1 + " " + NN[2]
				+ NN[3] + NN[4] + NN[5] + NN[6] + "." + NN[7] + DACNossoNumero
				+ "714" + dac2 + " " + "50367.99000" + dac3 + " " + DACBarras
				+ " " + fator+valor;
		return representacao;
	}

	// Calculo utilizado para gerar o DAC do nosso número e da representação
	// numérica
	private int mod10(int calculo[]) {
		String numero = "";
		for (int i = 0; i < calculo.length; i++) {
			numero += calculo[i];
		}
		System.out.println("Valor de entrada: " + numero);
		int calcula[] = new int[calculo.length];
		int mod = 2, soma = 0;
		for (int i = calculo.length - 1; i >= 0; i--) {
			calcula[i] = calculo[i] * mod;
			System.out.println("Total: " + calcula[i] + " Valor: " + calculo[i]+ " Mod" + mod);
			if (mod == 2) {
				mod = 1;
			} else {
				mod = 2;
			}
			while (calcula[i] > 9) {
				String a = String.valueOf(calcula[i]);
				int b = Integer.parseInt(String.valueOf(a.charAt(0)));
				int c = Integer.parseInt(String.valueOf(a.charAt(1)));
				calcula[i] = b + c;
			}
			soma += calcula[i];

		}
		return soma;
	}
	private Mensalidade geraNossoNumero(Mensalidade m){
		NossoNumeroDao nd=new NossoNumeroDaoImplementation();
		int nossoNumero=nd.buscar();
		String nn=String.valueOf(nossoNumero);	
		nossoNumero++;
		m.setNossoNumero(nn);
		int nossoN[]=new int[8];
		for(int s=0;s<nn.length();s++){
		nossoN[s]=Integer.parseInt(String.valueOf(nn.charAt(s)));
		}
		boolean ok=nd.alterar(nossoNumero);
		if(ok){
			System.out.println("Nosso numero gravado com sucesso!");
		}else{
			System.out.println("Erro ao gravar Nosso numero!");
		}
		return m;
	}
	
}
