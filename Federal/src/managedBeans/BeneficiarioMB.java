package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import beans.Beneficiario;
import beans.Cliente;
import dao.BeneficiarioDao;
import dao.BeneficiarioDaoImplementation;
import dao.ClienteDao;
import dao.ClienteDaoImplementation;
@ManagedBean
@ViewScoped
public class BeneficiarioMB implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Beneficiario beneficiarioNovo=new Beneficiario();
List<Beneficiario>beneficiarios;
Cliente cliente;
private int idBeneficiario;
private String tipo;
private String mensagem;
private boolean render;
public void Beneficiario(){
	
}

public Cliente getCliente() {
	return cliente;
}

public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public Beneficiario getBeneficiarioNovo() {
	return beneficiarioNovo;
}
public void setBeneficiarioNovo(Beneficiario beneficiarioNovo) {
	this.beneficiarioNovo = beneficiarioNovo;
}
public List<Beneficiario> getBeneficiarios() {
	return beneficiarios;
}
public void setBeneficiarios(List<Beneficiario> beneficiarios) {
	this.beneficiarios = beneficiarios;
}
public int getIdBeneficiario() {
	return idBeneficiario;
}
public void setIdBeneficiario(int idBeneficiario) {
	this.idBeneficiario = idBeneficiario;
}

public boolean isRender() {
	return render;
}
public void setRender(boolean render) {
	this.render = render;
}
public void adicionar(){System.out.println("Chamada do metodo adicionar beneficiario");
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	boolean resultado=bn.adicionar(beneficiarioNovo);
	if(resultado==true){System.out.println("adicionar beneficiario com sucesso");
		FacesContext.getCurrentInstance().addMessage("Sucesso", new FacesMessage(FacesMessage.SEVERITY_INFO, "Benefici�rio adicionado com sucesso!",  null));		
	}else{System.out.println("adicionar beneficiario sem sucesso");
		FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar benefici�rio!",  null));
		
	}
}

public void remover(){
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	boolean retorno=bn.adicionarCancelado(beneficiarioNovo);
	if(retorno){
	boolean resultado=bn.remover(beneficiarioNovo);
	if(resultado==true){
		mensagem="Benefici�rio Exclu�do com sucesso!";
		tipo="Sucesso!";
	}else{
		mensagem="Erro ao Excluir Benefici�rio";
		tipo="Erro!";
	}}
	addMessage(tipo,mensagem);
}
public List<Beneficiario> buscar(){
	beneficiarios=new ArrayList<Beneficiario>();
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	beneficiarios=bn.buscar(beneficiarioNovo.getContrato());
return beneficiarios;
}
public List<Beneficiario> buscarPorNome(){
	beneficiarios=new ArrayList<Beneficiario>();
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	beneficiarios=bn.buscarPorNome(beneficiarioNovo);
	return beneficiarios;
}
public Beneficiario buscarPorId(){
	idBeneficiario=beneficiarioNovo.getIdBeneficiario();
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	beneficiarioNovo=bn.buscarPorId(idBeneficiario);
	ClienteDao cd=new ClienteDaoImplementation();
	cliente=new Cliente();
	cliente=cd.buscar(beneficiarioNovo.getContrato());
	
	return beneficiarioNovo;
}
public void atualizar(){
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	boolean resultado=bn.atualizar(beneficiarioNovo);
	if(resultado==true){
		beneficiarioNovo=bn.buscarPorId(beneficiarioNovo.getIdBeneficiario());
		mensagem="Benefici�rio Atualizado com sucesso!";
		tipo="Sucesso!";
	}else{
		mensagem="Erro ao atualizar Benefici�rio!";
		tipo="Erro!";
	}
	 addMessage(tipo,mensagem);
}
public List<Beneficiario> listarInativos(){
	beneficiarios=new ArrayList<Beneficiario>();
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	beneficiarios=bn.listarInativos();
	if(!beneficiarios.isEmpty()){
		mensagem="Lista Importada!";
		tipo="Sucesso!";
		}else{
			mensagem="Erro ao importar lista, ou n�o h� clientes � serem exibidos!";
			tipo="Aviso!";
		}
		addMessage(tipo,mensagem);
	return beneficiarios;
}
public List<Beneficiario> listarAtivos(){
	beneficiarios=new ArrayList<Beneficiario>();
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	beneficiarios=bn.listarAtivos();
	if(!beneficiarios.isEmpty()){
	mensagem="Lista Importada!";
	tipo="Sucesso!";
	}else{
		mensagem="Erro ao importar lista, ou n�o h� clientes � serem exibidos!";
		tipo="Aviso!";
	}
	addMessage(tipo,mensagem);
	return beneficiarios;
}

public void verificar(AjaxBehaviorEvent event){
	BeneficiarioDao bd=new BeneficiarioDaoImplementation();
	boolean existe=bd.pesquisa(beneficiarioNovo);
	if(existe){
		addMessage("Aviso","Benefici�rio j� cadastrado!");System.out.println("Beneficiario cadastrado");
	}else{
		System.out.println("Beneficiario n�o cadastrado");
	}
}
public void buscarContrato(){
	BeneficiarioDao bd=new BeneficiarioDaoImplementation();
	beneficiarios=new ArrayList<Beneficiario>();
	beneficiarios=bd.buscar(beneficiarioNovo.getContrato());
	if(!beneficiarios.isEmpty()||beneficiarios.size()!=0){

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso","O contrato n�o possui benefici�rios ou n�o existe!"));
		System.out.println("Contrato invalido");
	}else{
		System.out.println("Contrato encontrado");
	}
}
	public void onChange(){
		buscarPorId();
		if(beneficiarioNovo.getFalecimento()==null){
			render=false;
		}else{
			beneficiarioNovo.setSituacao("Falecido(a)");
			render=true;
		}
	}
public void carregar(){
	
}
public void addMessage(String tipo, String mensagem) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,tipo, mensagem);
    FacesContext.getCurrentInstance().addMessage(null, message);
}

public void pesquisa(AjaxBehaviorEvent event){
	int contrato=beneficiarioNovo.getContrato();System.out.println(contrato);
	ClienteDao cd=new ClienteDaoImplementation();
	cliente=new Cliente();System.out.println(cliente.toString());
	cliente=cd.buscar(contrato);System.out.println(cliente.toString());
	if(cliente.getNumeroContrato()!=beneficiarioNovo.getContrato()){
		FacesContext.getCurrentInstance().addMessage("Erro",new FacesMessage(FacesMessage.SEVERITY_ERROR,"O n�mero de contrato digitado n�o existe!",null));
	}
}
public void validaAniversarioBen(AjaxBehaviorEvent event){
	Calendar c= Calendar.getInstance();
	if(beneficiarioNovo.getDataNascimento().after(c.getTime())){
		    FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de nascimento inv�lida!",  null));
	}
}
}
