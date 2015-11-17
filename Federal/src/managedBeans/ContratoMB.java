package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import beans.Beneficiario;
import beans.Cliente;
import beans.Contrato;
import beans.Endereco;
import beans.Mensalidade;
import controle.CalculoDatas;
import controle.Plano;
import controle.SegundaVia;
import controle.ValidaCPF;
import dao.BeneficiarioDao;
import dao.BeneficiarioDaoImplementation;
import dao.CepDao;
import dao.CepDaoImplementation;
import dao.ClienteDao;
import dao.ClienteDaoImplementation;
import dao.ContratoDao;
import dao.ContratoDaoImplementation;
import dao.MensalidadeDao;
import dao.MensalidadeDaoImplementation;
import dao.NossoNumeroDao;
import dao.NossoNumeroDaoImplementation;
@ManagedBean
@RequestScoped
public class ContratoMB implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Contrato contratoNovo=new Contrato();
private List<Mensalidade>mensalidades;
private Mensalidade mensalidadeNova;
private Endereco endereco=new Endereco();
private List<Contrato>contratos;
private String radio;
private String mensal;
private Cliente cliente;
private Beneficiario beneficiario;
private List<Beneficiario>beneficiarios;
private String codigoBarras;
private int contador=0;
private String atualiza="";
private String situacao;
private Plano plano;
public ContratoMB(){
	
}
@PostConstruct 
public void init() {
	
	mensal = "1"; 
	cliente=new Cliente();
	beneficiario=new Beneficiario();
	beneficiarios=new ArrayList<Beneficiario>();
	mensalidadeNova=new Mensalidade();
	plano=new Plano();
}

public Endereco getEndereco() {
	return endereco;
}
public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
}
public Plano getPlano() {
	return plano;
}
public void setPlano(Plano plano) {
	this.plano = plano;
}
public String getSituacao() {
	return situacao;
}
public void setSituacao(String situacao) {
	this.situacao = situacao;
}
public String getAtualiza() {
	return atualiza;
}
public void setAtualiza(String atualiza) {
	this.atualiza = atualiza;
}
public String getCodigoBarras() {
	return codigoBarras;
}
public void setCodigoBarras(String codigoBarras) {
	System.out.println(codigoBarras);
	this.codigoBarras = codigoBarras;
}
public Mensalidade getMensalidadeNova() {
	return mensalidadeNova;
}
public void setMensalidadeNova(Mensalidade mensalidadeNova) {
	this.mensalidadeNova = mensalidadeNova;
}
public List<Mensalidade> getMensalidades() {
	return mensalidades;
}
public void setMensalidades(List<Mensalidade> mensalidades) {
	this.mensalidades = mensalidades;
}
public String getRadio() {
	return radio;
}

public void setRadio(String radio) {
	this.radio = radio;
}

public String getMensal() {
	return mensal;
}

public void setMensal(String mensal) {
	this.mensal = mensal;
	if(mensal.equalsIgnoreCase("1")){
		contratoNovo.setPeriodicidade("MENSAL");
	}else if(mensal.equalsIgnoreCase("2")){
		contratoNovo.setPeriodicidade("BIMESTRAL");
	}else
	contratoNovo.setPeriodicidade("TRIMESTRAL");
}

public Contrato getContratoNovo() {
	return contratoNovo;
}

public void setContratoNovo(Contrato contratoNovo) {
	this.contratoNovo = contratoNovo;
}

public List<Contrato> getContratos() {
	return contratos;
}

public void setContratos(List<Contrato> contratos) {
	this.contratos = contratos;
}
public void adicionar(){
	ContratoDao cd=new ContratoDaoImplementation();
	ClienteDao cli=new ClienteDaoImplementation();
	BeneficiarioDao bd=new BeneficiarioDaoImplementation();
	int cont=0;
	boolean retBen=false;
	Calendar c=Calendar.getInstance();
	c.setTime(contratoNovo.getDataContrato());
	c.add(Calendar.MONTH, 3);
	contratoNovo.setCarencia(c.getTime());
	contratoNovo.setIdFuncionario(cliente.getIdFuncionario());
	
	cliente.setNumeroContrato(contratoNovo.getnContrato());
	contratoNovo.setPlano(plano.configuraPlanos(contratoNovo.getPlano()));
	System.out.println("Plano: "+contratoNovo.getPlano());
	boolean retorno=cd.adicionar(contratoNovo);
	if(retorno){System.out.println("Contrato adicionado com sucesso!"+contratoNovo.toString());
	
	cliente.setLogradouro(cliente.getLogradouro()+", "+cliente.getNumero());
		boolean retCli=cli.adicionar(cliente);
		if(retCli){
			System.out.println("Cliente adicionado com sucesso!"+cliente.toString());
			geraM();
			gravarMen();
			for(Beneficiario b:beneficiarios){
				b.setIdFuncionario(cliente.getIdFuncionario());			
				retBen=false;
				b.setContrato(contratoNovo.getnContrato());
				b.setDataCadastro(contratoNovo.getDataContrato());
				retBen=bd.adicionar(b);
				if(retBen){
					System.out.println("Beneficiario adicionado: "+b.toString());
				}else{
					System.out.println("Erro ao adicionar Beneficiario : "+b.toString());
					cont++;
				}
			}
		}else{
			System.out.println("Erro ao adicionar Cliente : "+cliente.toString());
			cont++;
		}
		
	}else{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Erro!","Erro ao adicionar Contrato!"));
		System.out.println("Erro ao adicionar Contrato: "+contratoNovo.toString());
		cont++;
	}
	if(cont==0){
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Sucesso!","Contrato adicionado com sucesso!"));
	}
}
public void excluir(){
	ContratoDao cd=new ContratoDaoImplementation();
	boolean retorno=cd.excluir(contratoNovo);
	if(retorno==true){
		addMensagem("Sucesso","Contrato Excluído com sucesso!");
	}else{
		addMensagem("Erro","Erro ao Excluir contrato");
	}
}
public void alterar(){
	ContratoDao cd=new ContratoDaoImplementation();
	boolean retorno=cd.alterar(contratoNovo);
	if(retorno==true){
		addMensagem("Sucesso","Contrato adicionado com sucesso!");
	}else{
		addMensagem("Erro","Erro ao Alterar contrato");
	}
}
public void buscar(){
	ContratoDao cd=new ContratoDaoImplementation();System.out.println("Contrato: "+contratoNovo.getnContrato());
    ClienteDao cli=new ClienteDaoImplementation();
    if(contratoNovo.getnContrato()==0){
		addMensagem("Erro", "Número de contrato inválido!");
	}else{
    cliente=cli.buscar(contratoNovo.getnContrato());
	contratoNovo=cd.buscar(contratoNovo.getnContrato());}
    	
}//Gera novas mensalidades

	public void geraM() {
		MensalidadeDao md = new MensalidadeDaoImplementation();
		int nossoNumero=0;
			NossoNumeroDao nd=new NossoNumeroDaoImplementation();
			SegundaVia sv=new SegundaVia();
			mensalidadeNova = new Mensalidade();
			CalculoDatas cd = new CalculoDatas();
			Date ultimaParc=null;
			Date dataAtual=null;
			double valorMensal=0;
			mensalidades = new ArrayList<Mensalidade>();
			int parcelas = 0;
			if (contratoNovo.getnContrato() == 0) {
				addMensagem("Erro", "Número de contrato inválido!");
			} else {
				if (contratoNovo.getPeriodicidade().equalsIgnoreCase("MENSAL")) {
					parcelas = 12;
				} else if (contratoNovo.getPeriodicidade().equalsIgnoreCase("BIMESTRAL")) {
					parcelas = 6;
				} else if (contratoNovo.getPeriodicidade().equalsIgnoreCase("TRIMESTRAL")) {
					parcelas = 4;
				}// cuidado com a data de pagamento!!!!!
			
				//BUSCA O NOSSO NÚMERO NO BANCO DE DADOS 
				nossoNumero=nd.buscar();
				//CASO NÃO HAJA NENHUMA PARCELA PAGA ANTERIORMENTE, GERA A MENSALIDADE PARA O PRÓXIMO VENCIMENTO
				
					valorMensal=contratoNovo.getMensalidade();					
					Calendar c = Calendar.getInstance();
					dataAtual=c.getTime();
						c.set(Calendar.DAY_OF_MONTH,contratoNovo.getDiaVencimento());
						ultimaParc = c.getTime();
						if(ultimaParc.before(dataAtual)){
							c.add(Calendar.MONTH, 1);
							ultimaParc=c.getTime();
								
				for (int i = 1; i <= parcelas; i++) {
					mensalidadeNova = new Mensalidade();
					mensalidadeNova.setNumParcela(i);
					mensalidadeNova.setContrato(contratoNovo.getnContrato());
					mensalidadeNova.setIdFuncionario(contratoNovo.getIdFuncionario());
					if(i==1){
						mensalidadeNova.setDataVencimento(ultimaParc);
						}else{
						mensalidadeNova.setDataVencimento(cd.calculaVencimento(ultimaParc, parcelas));
						}				
					mensalidadeNova.setPeriodicidade(contratoNovo.getPeriodicidade());
					mensalidadeNova.setValorParcela(valorMensal);
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
					ultimaParc = mensalidadeNova.getDataVencimento();
				}
				boolean ret = md.geraCarne(mensalidadeNova);
				
				if (!ret) {
					System.out.println("Erro ao enviar para geração de novo carnê: "+ mensalidadeNova.toString());
				}
			}
			boolean ok=nd.alterar(nossoNumero);
			if(ok){
				System.out.println("Nosso numero gravado com sucesso!");
			}else{
				System.out.println("Erro ao gravar Nosso numero!");
			}
			
	}
		}
		

public List<Contrato> listar(){
	contratos=new ArrayList<Contrato>();
	ContratoDao cd=new ContratoDaoImplementation();
	contratos=cd.listar();
	return contratos;
}
public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
public Beneficiario getBeneficiario() {
	return beneficiario;
}
public void setBeneficiario(Beneficiario beneficiario) {
	this.beneficiario = beneficiario;
}
public List<Beneficiario> getBeneficiarios() {
	return beneficiarios;
}
public void setBeneficiarios(List<Beneficiario> beneficiarios) {
	this.beneficiarios = beneficiarios;
}
public void copiarTitular(){
	beneficiario=new Beneficiario();
	beneficiario.setNome(cliente.getNome());
	beneficiario.setDataNascimento(cliente.getNascimento());
	beneficiario.setEmail(cliente.getEmail());
	beneficiario.setSexo(cliente.getSexo());
	beneficiario.setEstadoCivil(cliente.getEstadoCivil());
	beneficiario.setObservacao(cliente.getObservacao());
	beneficiario.setProfissao(cliente.getProfissao());
	beneficiario.setReligiao(cliente.getReligiao());
	beneficiario.setClassificacao("Titular");
}

public void adicionarBeneficiario() {
        beneficiarios.add(beneficiario);
        beneficiario=new Beneficiario();
        for(Beneficiario b:beneficiarios){
        	System.out.println(b.toString());
        }
}

public void validaCpf(){
	ClienteDao cli =new ClienteDaoImplementation();
	cliente=cli.buscar(cliente.getNumeroContrato());
		
}
public void addMensagem(String tipo,String mensagem){
	 FacesMessage msg = new FacesMessage(tipo,mensagem);
     FacesContext.getCurrentInstance().addMessage(null, msg);
   
}

	public void gravarMen() {
		boolean retorno = false;
		MensalidadeDao md = new MensalidadeDaoImplementation();
		int cont = 0;
		MensalidadeMB mmb=new MensalidadeMB();
		boolean boleto = mmb.buscarCarne(contratoNovo.getnContrato());
		if (boleto) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Aviso!","Esta operação não pode ser efetuada, o cliente possui mensalidades em aberto!"));
			
		} else {
			for (Mensalidade m : mensalidades) {
				retorno = md.adicionar(m);
				if (retorno) {
					cont++;
				}
			}
			if (cont == mensalidades.size()) {
				FacesMessage msg = new FacesMessage("Sucesso","Mensalidades Geradas com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				FacesMessage msg = new FacesMessage("Erro",	"Erro ao criar novas mensalidades!");
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}
		}
	}
public void barras(AjaxBehaviorEvent event){
	System.out.println(contador+"-"+codigoBarras);
	if(codigoBarras.length()==44 && mensalidades.size()>contador){
	mensalidadeNova=mensalidades.get(contador);
	mensalidadeNova.setNossoNumero(codigoBarras.substring(22, 30));
	mensalidadeNova.setDacNossoNumero(codigoBarras.substring(30,31));
	mensalidades.set(contador, mensalidadeNova);System.out.println(mensalidadeNova.getNossoNumero());
	mensalidadeNova=new Mensalidade();codigoBarras="";
	contador++;	atualiza="barras";
	//event.getComponent().setRendered(true);onsuccess="if(this.value.length==44)contrato.barras();this.value='';"	
	}else{
		System.out.println(codigoBarras);
	}
}
public void pesquisa(AjaxBehaviorEvent event){
	int contrato=contratoNovo.getnContrato();
	ContratoDao cd=new ContratoDaoImplementation();
	ClienteDao cli=new ClienteDaoImplementation();
	Cliente cl=new Cliente();
	cl=cli.buscar(contrato);
	Contrato ver=new Contrato();
	ver=cd.buscar(contrato);
	if(ver.getnContrato()==contrato||cl.getNumeroContrato()==contrato){
		FacesContext.getCurrentInstance().addMessage("Erro",new FacesMessage(FacesMessage.SEVERITY_ERROR,"O número de contrato digitado já está sendo usado!",null));
	}else if(contratoNovo.getnContrato()==0){
		FacesContext.getCurrentInstance().addMessage("Erro",new FacesMessage(FacesMessage.SEVERITY_ERROR,"O número de contrato não pode ser igual a zero!",null));
	}		
	}
public void pesquisaBen(AjaxBehaviorEvent event){
	BeneficiarioDao bd=new BeneficiarioDaoImplementation();
	boolean existe=bd.pesquisa(beneficiario);
	if(existe){
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Beneficiário já cadastrado!",  null));
		
	}else{
		System.out.println("Beneficiario não cadastrado");
	}
}
public void validaAniversarioBen(AjaxBehaviorEvent event){
	Calendar c= Calendar.getInstance();
	if(beneficiario.getDataNascimento().after(c.getTime())){
		    FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de nascimento inválida!",  null));
	}
}
public void validaAniversarioCli(AjaxBehaviorEvent event){
	Calendar c= Calendar.getInstance();
	if(cliente.getNascimento().after(c.getTime())){
		    FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de nascimento inválida!",  null));
	}
}
public void validaCPF(AjaxBehaviorEvent event){
	boolean valida=ValidaCPF.valida(cliente);
	if(!valida){
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número de CPF inválido! Informe novamente!",  null));
	}
	
}
public void calculaCarencia(AjaxBehaviorEvent event){
	Calendar c=Calendar.getInstance();
	c.setTime(contratoNovo.getDataContrato());
	c.add(Calendar.MONTH, 3);
	contratoNovo.setCarencia(c.getTime());
	System.out.println(contratoNovo.getCarencia());
}
public void endereco(){
	if(endereco.getCep().isEmpty()||endereco.getCep().length()!=8){
		FacesContext.getCurrentInstance().addMessage("Aviso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Informe um cep válido",null));
	}else{
	System.out.println(endereco.toString());
	//FacesContext.getCurrentInstance().addMessage("Aviso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Aguarde!",null));
	CepDao cep=new CepDaoImplementation();
	endereco=cep.buscar(endereco.getCep());
	cliente.setBairro(endereco.getBairro());
	cliente.setCep(endereco.getCep());
	cliente.setCidade(endereco.getCidade());
	cliente.setEstado(endereco.getEstado());
	cliente.setLogradouro(endereco.getLogradouro());
	if(endereco.getLogradouro()==null||endereco.getLogradouro().equalsIgnoreCase("")){
		FacesContext.getCurrentInstance().addMessage("Aviso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Informe um cep válido",null));
	}
	}
}
public void reinit(){
	beneficiario=new Beneficiario();
}
public void atendimento(){
	situacao="";
	CalculoDatas cc=new CalculoDatas();
	ClienteDao cd=new ClienteDaoImplementation();
	ContratoDao cont=new ContratoDaoImplementation();
	MensalidadeDao md=new MensalidadeDaoImplementation();
	Calendar c=Calendar.getInstance();
	int contrato=contratoNovo.getnContrato();
	cliente=cd.buscar(contrato);System.out.println(cliente.toString());
	if(cliente.getNumeroContrato()==0){
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_INFO, "Não há cliente cadastrado com este número!",  null));
	}
	mensalidades=new ArrayList<Mensalidade>();
	mensalidades=md.listar(contrato);
	contratoNovo=cont.buscar(contrato);System.out.println(contratoNovo.toString());
	if(contratoNovo.getnContrato()==0){
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente sem contrato em sistema!",  null));
	}else if(contratoNovo.getDataCancelamento()!=null){
		String dt=cc.converteUtilToString(contratoNovo.getDataCancelamento());
		situacao="Contrato cancelado. Cancelamento: "+dt+" \n- ";
	}else{
	plano =new Plano();
	contratoNovo.setPlano(plano.buscaPlanos(contratoNovo.getPlano()));
	if(contratoNovo.getCarencia()!=null&&contratoNovo.getCarencia().after(c.getTime())){
		String d=cc.converteUtilToString(contratoNovo.getCarencia());
		situacao+="Contrato em carência de inicio contratual até: "+d+" \n- ";
	}}
	if(!mensalidades.isEmpty()){
	
	for(Mensalidade m:mensalidades){System.out.println(m.toString());
		if(m.getDataCarencia()!=null&&m.getDataCarencia().after(c.getTime())){
			situacao+="Contrato em carência \n- ";
			
		}
		if(m.getDataVencimento().before(c.getTime())&&m.getDataPagamento()==null&&!m.getSituacao().equalsIgnoreCase("PAGO")){
			String dt=cc.converteUtilToString(m.getDataVencimento());
			situacao+="Mensalidade em atraso: "+dt+" = Pagamento não efetuado \n- ";
		}
	}}else{
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_INFO, "Não há mensalidades emitidas para este contrato/cliente!",  null));
	}
	
}

}
