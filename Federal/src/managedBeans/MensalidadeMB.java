package managedBeans;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Cliente;
import beans.Contrato;
import beans.Mensalidade;
import beans.Registro;
import controle.CalculoDatas;
import controle.ControleArquivoRemessa;
import controle.SegundaVia;
import dao.ClienteDao;
import dao.ClienteDaoImplementation;
import dao.ContratoDao;
import dao.ContratoDaoImplementation;
import dao.MensalidadeDao;
import dao.MensalidadeDaoImplementation;
import dao.NossoNumeroDao;
import dao.NossoNumeroDaoImplementation;

@ManagedBean
@SessionScoped
public class MensalidadeMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Mensalidade> mensalidades;
	Mensalidade mensalidadeNova=new Mensalidade();
	Mensalidade mensalSelecionada;
	Cliente cliente=new Cliente();
	Contrato contrato=new Contrato();
	List<String> erros = new ArrayList<String>();
	Calendar c=Calendar.getInstance();
	private int opc;
	private String cnr;
	private Date atrasadasInicio = null;
	private Date pagasInicio = null;
	private Date atrasadasFim = null;
	private Date pagasFim = null;
	private boolean tAtrasada = false;
	private boolean tPaga = false;
	public MensalidadeMB() {
		
		
	}

	@PostConstruct
	public void init() {
		opc = 1;
		
	}


	public Mensalidade getMensalSelecionada() {
		return mensalSelecionada;
	}

	public void setMensalSelecionada(Mensalidade mensalSelecionada,SelectEvent event) {
		mensalSelecionada=(Mensalidade)event.getObject();
		System.out.println(mensalSelecionada.toString());
		this.mensalSelecionada = mensalSelecionada;System.out.println(mensalSelecionada.toString());
	}
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public void setAtrasadasInicio(Date atrasadasInicio) {
		this.atrasadasInicio = atrasadasInicio;
	}

	public void settPaga(boolean tPaga) {
		this.tPaga = tPaga;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public Date getAtrasadasInicio() {
		return atrasadasInicio;
	}

	public void setAtrasadas(Date atrasadasInicio) {
		this.atrasadasInicio = atrasadasInicio;
	}

	public Date getPagasInicio() {
		return pagasInicio;
	}

	public void setPagasInicio(Date pagasInicio) {
		this.pagasInicio = pagasInicio;
	}

	public int getOpc() {
		return opc;
	}

	public void setOpc(int opc) {
		this.opc = opc;
	}

	public Mensalidade getMensalidadeNova() {
		return mensalidadeNova;
	}

	public void setMensalidadeNova(Mensalidade mensalidadeNova) {
		System.out.println("setMB"+mensalidadeNova.toString());
		this.mensalidadeNova = mensalidadeNova;
	}

	public List<Mensalidade> getMensalidades() {
		return mensalidades;
	}

	public void setMensalidades(List<Mensalidade> mensalidades) {
		this.mensalidades = mensalidades;
	}

	public Date getAtrasadasFim() {
		return atrasadasFim;
	}

	public void setAtrasadasFim(Date atrasadasFim) {
		this.atrasadasFim = atrasadasFim;
	}

	public Date getPagasFim() {
		return pagasFim;
	}

	public void setPagasFim(Date pagasFim) {
		this.pagasFim = pagasFim;
	}

	public boolean istAtrasada() {
		return tAtrasada;
	}

	public void settAtrasada(boolean tAtrasada) {
		this.tAtrasada = tAtrasada;
	}

	public boolean istPaga() {
		return tPaga;
	}

	public void settPagas(boolean tPaga) {
		this.tPaga = tPaga;
	}

	public void adicionar() {
		MensalidadeDao md = new MensalidadeDaoImplementation();
		boolean retorno = md.adicionar(mensalidadeNova);
		if (retorno == true) {
			System.out.println("Mensalidade adicionada com sucesso!");
		} else {
			System.out.println("Erro ao adicionar mensalidade");
		}
	}

	public void excluir() {
		MensalidadeDao md = new MensalidadeDaoImplementation();
		mensalidadeNova=md.buscar(mensalidadeNova.getNossoNumero());System.out.println("Exclusão MB:"+mensalidadeNova.toString());
		boolean retorno = md.excluir(mensalidadeNova);
		if (retorno) {
			FacesMessage msg = new FacesMessage("Sucesso!","Exclusão efetuada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Erro!","Erro ao excluir mensalidade!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		buscar();
	}

	public void alterar() {
		MensalidadeDao md = new MensalidadeDaoImplementation();
		boolean retorno = md.alterar(mensalidadeNova);
		if (retorno == true) {
			System.out.println("Mensalidade alterada com sucesso!");
		} else {
			System.out.println("Erro ao alterar mensalidade");
		}
	}// Efetua buscar de mensalidades através do nosso número e lista também as

	public void buscarNN() {
		
		cnr = mensalidadeNova.getNossoNumero();System.out.println(mensalidadeNova.getNossoNumero()+"-----"+cnr);
		if (cnr.length() != 8) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!","Insira um número válido, o nosso número deve conter 8 dígitos!"));
		} else {
			MensalidadeDao md = new MensalidadeDaoImplementation();
			mensalidadeNova = md.buscar(mensalidadeNova.getNossoNumero());
			if (mensalidadeNova.getContrato() != 0) {
				ClienteDao cd = new ClienteDaoImplementation();
				cliente = cd.buscar(mensalidadeNova.getContrato());System.out.println("Cliente antes de imprimir"+cliente.toString());
				if(opc==1){
					// outras mensalidades do mesmo contrato.
					mensalidades = new ArrayList<Mensalidade>();
					mensalidades = md.listar(cliente.getNumeroContrato());
				}
				
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!","O registro com o Nosso Número informado não foi encontrado!"));
			}
		}
	}

	// Lista as mensalidades conforme solicitação, 1 = por contrato, 2 =
	// atrasadas por período, 3 = pagas por período, 4 = todas atrasadas e 5 =
	// todas pagas.
	public void buscar() {
		System.out.println("Chamada do metodo");
		MensalidadeDao md = new MensalidadeDaoImplementation();
		mensalidades = new ArrayList<Mensalidade>();
		if (opc == 1) {
			System.out.println(mensalidadeNova);
			ClienteDao cd=new ClienteDaoImplementation();
			cliente=cd.buscar(mensalidadeNova.getContrato());
			mensalidades = md.listar(mensalidadeNova.getContrato());
		} else if (opc == 2 && atrasadasInicio!=null&& atrasadasFim!=null) {
			mensalidades = md.buscar(atrasadasInicio, atrasadasFim, "ABERTO");
			System.out.println("Inicio: " + atrasadasInicio + "fim: "
					+ atrasadasFim);
		} else if (opc == 3) {
			mensalidades = md.buscar(pagasInicio, pagasFim, "PAGO");
			System.out.println("Inicio: " + pagasInicio + "fim: " + pagasFim);
		} else if (opc == 4) {
			mensalidades = md.listar("ABERTO");
			System.out.println("Listar em aberto");
		} else if (opc == 5) {
			mensalidades = md.listar("PAGO");
			System.out.println("Listar pagos");
		}
	}// Lista todas as mensalidades

	public List<Mensalidade> listar() {
		mensalidades = new ArrayList<Mensalidade>();
		MensalidadeDao md = new MensalidadeDaoImplementation();
		mensalidades = md.listar();

		return mensalidades;
	}// Lista as mensalidades por contrato

	public List<Mensalidade> listarContrato() {
		mensalidades = new ArrayList<Mensalidade>();
		MensalidadeDao md = new MensalidadeDaoImplementation();
		ClienteDao cd=new ClienteDaoImplementation();
		cliente=cd.buscar(mensalidadeNova.getContrato());
		if(cliente.getNumeroContrato()==0){
			FacesMessage msg = new FacesMessage("Aviso!","Não foi encontrado cliente com este número de contrato!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
		mensalidades = md.listar(mensalidadeNova.getContrato());
		}
		return mensalidades;
	}// Lista as mensalidades por situação

	public List<Mensalidade> listarSituacao() {
		mensalidades = new ArrayList<Mensalidade>();
		MensalidadeDao md = new MensalidadeDaoImplementation();
		mensalidades = md.listar(mensalidadeNova.getSituacao());

		return mensalidades;
	}// Busca as mensalidades e dá a baixa através do arquivo de retorno.

	public List<Mensalidade> baixa(List<Registro> registros) {
		CalculoDatas cd = new CalculoDatas();
		MensalidadeDao md = new MensalidadeDaoImplementation();
		mensalidades=new ArrayList<Mensalidade>();
		for (Registro reg : registros) {
			System.out.println("Registro Men MB"+reg.toString());
			mensalidadeNova = md.buscar(reg.getNossoNumero());System.out.println("Men MB"+mensalidadeNova.toString());
			if (mensalidadeNova.getNossoNumero()!=null) {
				System.out.println("Mensalidade encontrada! "+ mensalidadeNova.toString());
			System.out.println("Valida"+mensalidadeNova.toString());
			if (!mensalidadeNova.getNossoNumero().equalsIgnoreCase("0")	&& (!mensalidadeNova.getSituacao().equalsIgnoreCase("PAGO") || (!mensalidadeNova
							.getSituacao().equalsIgnoreCase("QUITADO")))) {
				mensalidadeNova.setSituacao("PAGO");
				Date pagamento = cd.converteStringToDate(reg.getDataOcorrencia());
				mensalidadeNova.setDataPagamento(pagamento);
				mensalidadeNova = cd.carencia(mensalidadeNova);
				boolean retorno = md.alterar(mensalidadeNova);
				if (!retorno) {
					erros.add("Erro ao dar baixa na mensalidade: "+ mensalidadeNova.toString());
				}else{
					System.out.println(mensalidadeNova.toString());
					mensalidades.add(mensalidadeNova);
				//Cadastra em banco de dados a informação dos carnês quitados;
					geraCarne(mensalidadeNova);
				}
			}} else {
				System.out.println("Mensalidade não encontrada");
				erros.add("A mensalidade com o NOSSO NÚMERO: "+ reg.getNossoNumero() + "---"+" não pode ser encontrada ");
			}
		}
		for (int i = 0; i < erros.size(); i++) {
			System.out.println(erros.get(i));
		}
		return mensalidades;
	}// Baixa manual de mensalidades que foram pagas de outra forma ou que não
		// foi feita baixa através do arquivo de retorno.

	public void baixaManual() {
		boolean retorno = false;
		MensalidadeDao md = new MensalidadeDaoImplementation();
		System.out.println("MENSALIDADE MB "+mensalidadeNova.getNossoNumero());
		mensalidadeNova = md.buscar(mensalidadeNova.getNossoNumero());System.out.println("Nosso numero recuperado: "+mensalidadeNova.getNossoNumero());
		if (mensalidadeNova.getNossoNumero()!=null) {
			mensalidadeNova.setDataPagamento(pagasInicio);
			CalculoDatas cd = new CalculoDatas();
			if (mensalidadeNova.getDataVencimento()==null|| mensalidadeNova.getDataPagamento()==null||mensalidadeNova.getDataPagamento().after(c.getTime())) {
				if(mensalidadeNova.getDataVencimento()==null){
					FacesMessage msg = new FacesMessage("Erro!","A data de vencimento é inválida!");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}else if(mensalidadeNova.getDataPagamento()==null){
					FacesMessage msg = new FacesMessage("Erro!","Você deve informar a data de pagamento!");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					}else if(mensalidadeNova.getDataPagamento().after(c.getTime())){
				FacesMessage msg = new FacesMessage("Erro!","A data de pagamento é posterior a data de hoje!");
				FacesContext.getCurrentInstance().addMessage(null, msg);}
			} else {
				mensalidadeNova.setSituacao("PAGO");
				mensalidadeNova = cd.carencia(mensalidadeNova);
				retorno = md.alterar(mensalidadeNova);
				if(mensalidadeNova.getDataCarencia()!=null){
					if(mensalidadeNova.getSituacao().equalsIgnoreCase("Cancelar")){
						FacesMessage msg = new FacesMessage("Aviso!","Atraso superior a 90 dias! Contrato deve ser cancelado!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}else{
					FacesMessage msg = new FacesMessage("Aviso!","Pagamento efetuado com atraso! Carência até: "+mensalidadeNova.getDataCarencia());
					FacesContext.getCurrentInstance().addMessage(null, msg);}
				}
				if (retorno) {
					FacesMessage msg = new FacesMessage("Sucesso!","Baixa efetuada com sucesso!");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					FacesMessage msg = new FacesMessage("Erro!","A operação não pode ser realizada! Favor informar a ocorrência do erro!");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			}
		} else {
			FacesMessage msg = new FacesMessage("Erro!","O NOSSO NÚMERO informado não foi encontrado! Verifique!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		geraCarne(mensalidadeNova);
	    buscarNN();
	}

	// Verifica se foram pagas todas as mensalidades do carnê, caso positivo,
	// envia para a tabela geracarne uma informação para gerar um novo
	// carne.

	public void geraCarne(Mensalidade mensalidadeNova) {

		if (mensalidadeNova.getNumParcela() == 12|| mensalidadeNova.getNumParcela() == 6|| (mensalidadeNova.getNumParcela() == 4 && mensalidadeNova
						.getPeriodicidade().equalsIgnoreCase("TRIMESTRAL"))) {
			MensalidadeDao md = new MensalidadeDaoImplementation();
			List<Mensalidade> mensContrato = new ArrayList<Mensalidade>();
			mensContrato = md.listar(mensalidadeNova.getContrato());
			int i=0;
			for (Mensalidade mens : mensContrato) {
				
				if (!mens.getSituacao().equalsIgnoreCase("PAGO")||!mens.getSituacao().equalsIgnoreCase("QUITADO")) {
					i++;
					erros.add("Mensalidade em aberto: "+ mensalidadeNova.toString());
				}
				}
				if (i == 0) {
					boolean ret = md.geraCarne(mensalidadeNova);
					
					if (!ret) {
						erros.add("Erro ao enviar para geração de novo carnê: "
								+ mensalidadeNova.toString());
					}else{
						/*Mover mensalidades pagas para tabela de MENSALIDADE_PAGA
						*Apagar mensalidade de tabela mensalidade
						*gerar novas mensalidades
						*/
						List<Mensalidade>novasMensalidades=new ArrayList<Mensalidade>();
						novasMensalidades=gerarM(mensalidadeNova);
						for(Mensalidade m:mensContrato){
							boolean gravar=md.adicionarPagas(m);
							if(gravar){
								System.out.println("Mensalidade adicionada a tabela de pagas com sucesso: "+m.toString());
								boolean excluir=md.excluir(m);
								if(excluir){
									System.out.println("Mensalidade excluida com sucesso: "+m.toString());
								}else{
									System.out.println("Erro ao excluir mensalidade: "+m.toString());
								}
							}else{
								System.out.println("Erro ao adicionar mensalidade a tabela de pagas: "+m.toString());
							}
						}int f=0;
						for(Mensalidade mens:novasMensalidades){
							boolean retorno=md.adicionar(mens);
							if(!retorno){
								f++;
							System.out.println("Erro ao adicionar mensalidade: "+mens.toString());
							}else{
								System.out.println("Mensalidade adicionada com sucesso: "+mens.toString());
							}
						}
						if(f==0){
							FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sucesso!","Novas mensalidades geradas com sucesso!"));
						}
					}
				}
		}
		
	}
	//Gera novas mensalidades quando o carnê for quitado
	public List<Mensalidade> gerarM(Mensalidade m){
		int nossoNumero=0;
		NossoNumeroDao nd=new NossoNumeroDaoImplementation();
		MensalidadeDao md=new MensalidadeDaoImplementation();
		ContratoDao contd=new ContratoDaoImplementation();
		SegundaVia sv=new SegundaVia();
		Contrato contratoNovo=new Contrato();
		contratoNovo=contd.buscar(m.getContrato());
		mensalidadeNova=new Mensalidade();
		CalculoDatas cd=new CalculoDatas();
		Date ultimaParc=null;
		Date atual;
		mensalidades=new ArrayList<Mensalidade>();
		int parcelas=0;
		if(contratoNovo.getnContrato()==0){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Erro!","Número de contrato inválido!"));
		}else{
		if(contratoNovo.getPeriodicidade().equalsIgnoreCase("MENSAL")){
			parcelas=12;
		}else if(contratoNovo.getPeriodicidade().equalsIgnoreCase("BIMESTRAL")){
			parcelas=6;
		}else if(contratoNovo.getPeriodicidade().equalsIgnoreCase("TRIMESTRAL")){
			parcelas=4;
	}//cuidado com a data de pagamento!!!!!
		mensalidadeNova=md.buscaUltimoPgm(contratoNovo.getnContrato());
		if(mensalidadeNova.getDataVencimento()==null){
		System.out.println(mensalidadeNova.toString());
		}else{
			ultimaParc=mensalidadeNova.getDataVencimento();	
		}		
		nossoNumero=nd.buscar();
		Calendar c=Calendar.getInstance();
		atual=c.getTime();
		if(ultimaParc==null){
			c.set(Calendar.DAY_OF_MONTH, contratoNovo.getDiaVencimento());
			ultimaParc=c.getTime();
			if(ultimaParc.before(atual)){
				c.add(Calendar.MONTH, 1);
				ultimaParc=c.getTime();
			}
		}
		for(int i=1;i<=parcelas;i++){
			mensalidadeNova=new Mensalidade();
			mensalidadeNova.setNumParcela(i);
			mensalidadeNova.setContrato(contratoNovo.getnContrato());
			if(i==1){
				mensalidadeNova.setDataVencimento(ultimaParc);
				}else{
				mensalidadeNova.setDataVencimento(cd.calculaVencimento(ultimaParc, parcelas));
				}	
			mensalidadeNova.setPeriodicidade(contratoNovo.getPeriodicidade());
			mensalidadeNova.setValorParcela(contratoNovo.getMensalidade());
			String nn=String.valueOf(nossoNumero);	
			nossoNumero++;
			mensalidadeNova.setNossoNumero(nn);
			int nossoN[]=new int[8];
			for(int s=0;s<nn.length();s++){
			nossoN[s]=Integer.parseInt(String.valueOf(nn.charAt(s)));
			}
			mensalidadeNova.setDacNossoNumero(String.valueOf(sv.DACNossoNumero(nossoN)));
			mensalidadeNova.setSituacao("IMPRIMIR");
			mensalidades.add(mensalidadeNova);
			ultimaParc=mensalidadeNova.getDataVencimento();
		}
		boolean ok=nd.alterar(nossoNumero);
		if(ok){
			System.out.println("Nosso numero gravado com sucesso!");
		}else{
			System.out.println("Erro ao gravar Nosso numero!");
		}
		}
		
		return mensalidades;
	}
	
	public void via2Mensalidade(){
		MensalidadeDao md=new MensalidadeDaoImplementation();
		ClienteDao cd=new ClienteDaoImplementation();
		if(mensalidadeNova.getNossoNumero().equalsIgnoreCase(null)||mensalidadeNova.getNossoNumero().equalsIgnoreCase("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!","Você deve informar o nosso Número!"));
		}else{
		mensalidadeNova=md.buscar(mensalidadeNova.getNossoNumero());
		cliente=cd.buscar(mensalidadeNova.getContrato());
		SegundaVia gr=new SegundaVia();
		gr.exibeBoleto(mensalidadeNova, cliente);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso!","Boleto gerado com sucesso!"));
		//gr.exibeNavegador(mensalidadeNova, cliente);
		}
		
	}
	
	public void via2Carne(){
		MensalidadeDao md=new MensalidadeDaoImplementation();
		ClienteDao cd=new ClienteDaoImplementation();
		List<Mensalidade>emAberto=new ArrayList<Mensalidade>();
		int codigo=0;
		mensalidades=md.listar(mensalidadeNova.getContrato());
		cliente=cd.buscar(mensalidadeNova.getContrato());
		for(Mensalidade m:mensalidades){
			if("ABERTO".equalsIgnoreCase(m.getSituacao())||"IMPRIMIR".equalsIgnoreCase(m.getSituacao())){
				emAberto.add(m);
			}else if("CADASTRAR".equalsIgnoreCase(m.getSituacao())){
				codigo=1;				
			}else{
				codigo=2;				
			}
		}
		if(!emAberto.isEmpty()){
			SegundaVia g=new SegundaVia();
			g.groupInPages(emAberto, cliente);	
			codigo=3;			
		}
			if(codigo==1){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso!","Ainda não foi cadastrado o código de barras"));
			}else if(codigo==2){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso!","Cliente não possui carnê em aberto"));
			}else if(codigo==3){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso!","Arquivo gerado com sucesso!"));
			}		
	}

	public List<Mensalidade> imprimeCarnes() {
		MensalidadeDao md = new MensalidadeDaoImplementation();
		ClienteDao cd = new ClienteDaoImplementation();
		List<Cliente> clientes = new ArrayList<Cliente>();
		mensalidades = new ArrayList<Mensalidade>();
		List<Mensalidade> geraRemessa = new ArrayList<Mensalidade>();
		mensalidades = md.ImprimirCarnes();
		if (mensalidades.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Aviso!","Não há mensalidades a serem impressas!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Aviso!","Carregando Clientes!"));
			for (Mensalidade m : mensalidades) {
				cliente = cd.buscar(m.getContrato());
				boolean carne = buscarCarne(cliente.getNumeroContrato());
				if (!carne) {
					int f = 0;
					List<Mensalidade> novasMensalidades = new ArrayList<Mensalidade>();
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Aviso!","Gerando mensalidades!"));
					novasMensalidades = gerarM(m);
					for (Mensalidade mens : novasMensalidades) {
						boolean retorno = md.adicionar(mens);
						if (!retorno) {
							f++;
							System.out.println("Erro ao adicionar mensalidade: "+ mens.toString());
						} else {
							System.out.println("Mensalidade adicionada com sucesso: "+ mens.toString());
						}
					}
				}
				clientes.add(cliente);
				cliente = new Cliente();
			}
			SegundaVia g = new SegundaVia();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Aviso!","Gerando boletos!"));
			List<Mensalidade>geral = g.geraArquivoParaImpressao(clientes);
			
				for (Mensalidade m : mensalidades) {
					boolean ret = md.excluirMensalGerada(m);
					if (ret) {
						System.out.println("Gera carnê, registro excluído");
					} else {
						System.out.println("Gera carnê, erro ao excluir");
					}				
			}FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Aviso!","Gerando arquivo de Remessa!"));
				ControleArquivoRemessa car=new ControleArquivoRemessa();
				car.equals(geral);
		}
		return mensalidades;
	}
	public void cadastrarBarras(){
		File file=new File("C:/Federal/app/CadastraCNR.jar");
		java.awt.Desktop desktop=java.awt.Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean buscarCarne(int contrato) {
		MensalidadeDao md=new MensalidadeDaoImplementation();
		boolean boleto=false;
		List<Mensalidade> valida = new ArrayList<Mensalidade>();
		valida = md.listar(contrato);
		if(valida.isEmpty()){
			boleto=false;
		}else{
			boleto=true;}
		return boleto;
		}
}
