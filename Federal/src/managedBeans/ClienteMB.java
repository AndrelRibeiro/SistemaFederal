package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.print.PrintException;

import beans.Beneficiario;
import beans.Cliente;
import beans.Contrato;
import beans.Endereco;
import beans.Mensalidade;
import controle.Impressao;
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
@ManagedBean
@RequestScoped
public class ClienteMB implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -6816511926054713256L;
private List<Cliente>clientes;
Cliente clienteNovo=new Cliente();
private String tipo;
private String mensagem;
private Endereco endereco=new Endereco();
public ClienteMB(){

}
public Endereco getEndereco() {
	return endereco;
}

public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
}

public List<Cliente> getClientes() {
	return clientes;
}

public void setClientes(List<Cliente> clientes) {
	this.clientes = clientes;
}

public Cliente getClienteNovo() {
	return clienteNovo;
}

public void setClienteNovo(Cliente clienteNovo) {
	this.clienteNovo = clienteNovo;
}
public void adicionar(){
	ClienteDao cli=new ClienteDaoImplementation();
	boolean retorno=cli.adicionar(clienteNovo);
	if(retorno==true){
		mensagem=" Cliente adicionado com sucesso!";
		tipo="Sucesso!";
	}else{
		mensagem="Erro ao adicionar cliente!";
		tipo="Erro!";
	}
	clienteNovo=new Cliente();
	addMessage(tipo,mensagem);
}

public Cliente buscar(){
	ClienteDao cli=new ClienteDaoImplementation();
	int numeroContrato=clienteNovo.getNumeroContrato();
	clienteNovo=cli.buscar(clienteNovo.getNumeroContrato());
	if(clienteNovo==null){
		Contrato contrato=new Contrato();
		ContratoDao contd=new ContratoDaoImplementation();
		contrato=contd.buscar(numeroContrato);
		if(contrato==null){
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não há cliente ou contrato com este número!",null));
		}else{
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Contrato sem cliente cadastrado! Vá até Cadastro > Cliente sem cadastro",null));
		}
	}
	return clienteNovo;
}

public Cliente buscarPorNome(){
	ClienteDao cli=new ClienteDaoImplementation();
	clientes=cli.buscarPorNome(clienteNovo);
	return clienteNovo;
}

public List<Cliente> listarInativos(){
	clientes=new ArrayList<Cliente>();
	ClienteDao cli=new ClienteDaoImplementation();
	return clientes=cli.listarInativos();
}

public List<Cliente> listar(){
	clientes=new ArrayList<Cliente>();
	ClienteDao cli=new ClienteDaoImplementation();
	clientes=cli.listarAtivos();
	
	return clientes;
}
public List<Cliente> listarParaImprimirEtiquetas(){
	clientes=new ArrayList<Cliente>();
	ClienteDao cli=new ClienteDaoImplementation();
	MensalidadeDao md=new MensalidadeDaoImplementation();
	List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
	mensalidades=md.imprimirEtiquetas();
	for(Mensalidade m:mensalidades){
		clienteNovo=cli.buscar(m.getContrato());
		clientes.add(clienteNovo);
		clienteNovo=new Cliente();
	}
	
	return clientes;
}
public void imprimir(){
	Impressao imp=new Impressao();
	try {
		imp.sendTextToPrinter("Etiquetas", clientes);
	} catch (PrintException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void atualizar(){
	ClienteDao cli=new ClienteDaoImplementation();System.out.println("Chamada de atualização: "+clienteNovo.toString());
	boolean retorno=cli.atualizar(clienteNovo);
	if(retorno==true){
		FacesContext.getCurrentInstance().addMessage("Sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Cliente atualizado com sucesso!",null));
	}else{
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao atualizar Cliente!",null));
	}
	clienteNovo=null;
}

public String remover(){
	ClienteDao cli=new ClienteDaoImplementation();System.out.println("Excluir: "+clienteNovo.toString());
	BeneficiarioDao ben=new BeneficiarioDaoImplementation();
	ContratoDao cd=new ContratoDaoImplementation();
	List<Beneficiario>beneficiarios=new ArrayList<Beneficiario>();
	beneficiarios=ben.buscar(clienteNovo.getNumeroContrato());
	Contrato contrato=new Contrato();
	contrato=cd.buscar(clienteNovo.getNumeroContrato());
	String pagina="";
	boolean retornoBen=false;
	for(Beneficiario beneficiario:beneficiarios){		
		retornoBen=ben.remover(beneficiario);
		}	
	if(retornoBen){
		boolean retorno=cli.remover(clienteNovo);	
		boolean retornoCont=cd.excluir(contrato);
	if(retorno&&retornoCont){
		clienteNovo=null;
		contrato=null;
		beneficiarios=null;		
		FacesContext.getCurrentInstance().addMessage("Sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Cliente excluído com sucesso!",null));
		pagina= "/index.xhtml";
	}else{
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_INFO,"Erro ao excluir Cliente!",null));
		pagina="/informe.xhtml";
	}
	}else{
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_INFO,"Erro ao excluir Cliente!",null));
		pagina="/informe.xhtml";
	}
	
	return pagina;
	
}
public void addMessage(String tipo, String mensagem) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,  null);
    FacesContext.getCurrentInstance().addMessage(tipo, message);
}
public List<Cliente> contratos(){
	ClienteDao cli=new ClienteDaoImplementation();
	MensalidadeDao men=new MensalidadeDaoImplementation();
	List<Cliente>geral=new ArrayList<Cliente>();
	clientes=new ArrayList<Cliente>();
	geral=cli.listar_CPF_Ok();
	Mensalidade m=new Mensalidade();
	for(int f=0;f<geral.size();f++){
		clienteNovo=geral.get(f);
		m=men.buscaUltimoPgm(clienteNovo.getNumeroContrato());
		clienteNovo.setValor(m.getValorParcela());
		clientes.add(clienteNovo);
	}
	
	return clientes;
}
public void pesquisa(AjaxBehaviorEvent event){System.out.println("Pesquisa contrato: "+clienteNovo.toString());
	int contrato=clienteNovo.getNumeroContrato();
	ContratoDao cd=new ContratoDaoImplementation();
	ClienteDao cli=new ClienteDaoImplementation();
	Contrato contratoNovo=null;
	Cliente verifica=null;
	verifica=cli.buscar(contrato);
	contratoNovo=cd.buscar(contrato);
	if(contratoNovo!=null){		
			if(verifica==null){
			FacesContext.getCurrentInstance().addMessage("Erro",new FacesMessage(FacesMessage.SEVERITY_INFO,"Contrato sem Cliente encontrado! Prossiga!",null));
		}else{
		FacesContext.getCurrentInstance().addMessage("Erro",new FacesMessage(FacesMessage.SEVERITY_ERROR,"O número de contrato possui cliente, vá até a opção Editar > Cliente!",null));
		}
	}else if(contratoNovo==null){
		FacesContext.getCurrentInstance().addMessage("Erro",new FacesMessage(FacesMessage.SEVERITY_ERROR,"O contrato não está cadastrado, vá até a opção: Cadastro > Contrato sem cliente",null));
	}	System.out.println("Pesquisa contrato saida: "+clienteNovo.toString());
	}
public void validaCPF(AjaxBehaviorEvent event){
	System.out.println("valida cpf: "+clienteNovo.toString());
	boolean valida=ValidaCPF.valida(clienteNovo);
	if(!valida){
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número de CPF inválido! Informe novamente!",  null));
	}System.out.println("valida cpf saida: "+clienteNovo.toString());
	}
public void validaAniversarioCli(AjaxBehaviorEvent event){
	Calendar c= Calendar.getInstance();System.out.println("Valida aniversario: "+clienteNovo.toString());
	if(clienteNovo.getNascimento().after(c.getTime())){
		    FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de nascimento inválida!",  null));
	}System.out.println("Valida aniversario saida: "+clienteNovo.toString());
}
public void endereco(){
	System.out.println("endereço: "+clienteNovo.toString());
	if(endereco.getCep().isEmpty()||endereco.getCep().length()!=8){
		FacesContext.getCurrentInstance().addMessage("Aviso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Informe um cep válido",null));
	}else{
	System.out.println(endereco.toString());
	//FacesContext.getCurrentInstance().addMessage("Aviso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Aguarde!",null));
	CepDao cep=new CepDaoImplementation();
	endereco=cep.buscar(endereco.getCep());
	clienteNovo.setBairro(endereco.getBairro());
	clienteNovo.setCep(endereco.getCep());
	clienteNovo.setCidade(endereco.getCidade());
	clienteNovo.setEstado(endereco.getEstado());
	clienteNovo.setLogradouro(endereco.getLogradouro());
	if(endereco.getLogradouro()==null||endereco.getLogradouro().equalsIgnoreCase("")){
		FacesContext.getCurrentInstance().addMessage("Aviso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Informe um cep válido",null));
	}
	}System.out.println("endereço saida: "+clienteNovo.toString());
}
}
