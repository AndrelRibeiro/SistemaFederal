package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.FuncionarioDao;
import dao.FuncionarioDaoImplementation;
import beans.Funcionario;


@ManagedBean
@SessionScoped
public class FuncionarioMB implements Serializable{
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
private Funcionario funcionarioNovo = new Funcionario();
private List<Funcionario>funcionarios;
	public FuncionarioMB(){
	
}
	public Funcionario getFuncionarioNovo() {
		return funcionarioNovo;
	}
	public void setFuncionarioNovo(Funcionario funcionarioNovo) {
		this.funcionarioNovo = funcionarioNovo;
	}
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public void adicionar(){
		FuncionarioDao fd=new FuncionarioDaoImplementation();
		boolean retorno=fd.adicionar(funcionarioNovo);
		if(retorno==true){
			addMessage("Funcionario adicionado com sucesso!");
		}else{
			addMessage("Erro ao adicionar funcionario");
		}
	}
	public Funcionario buscar(){
		FuncionarioDao fd=new FuncionarioDaoImplementation();
		funcionarioNovo=fd.buscar(funcionarioNovo);
		return funcionarioNovo;
	}
	public String validar(){
		FuncionarioDao fd=new FuncionarioDaoImplementation();
		boolean retorno=fd.validar(funcionarioNovo);
		String url="";
		if(retorno==true){
			addMessage("Funcionario alterado com sucesso!");
		}else{
			addMessage("Erro ao alterar funcionario");
		}
		return url;
	}
	public List<Funcionario> excluir(){
		FuncionarioDao fd=new FuncionarioDaoImplementation();
		boolean retorno=fd.excluir(funcionarioNovo);
		funcionarioNovo=null;
		funcionarios=fd.listar();
		if(retorno==true){
			addMessage("Funcionario excluido com sucesso!");
		}else{
			addMessage("Erro ao excluir funcionario");
		}
		return funcionarios;
	}
	public List<Funcionario> listar(){
		funcionarios=new ArrayList<Funcionario>();
		FuncionarioDao fd=new FuncionarioDaoImplementation();
		funcionarios=fd.listar();
		return funcionarios;
		
	}public void alterar(){
		FuncionarioDao fd=new FuncionarioDaoImplementation();
		boolean retorno=fd.alterar(funcionarioNovo);
		if(retorno==true){
			addMessage("Funcionario alterado com sucesso!");
		}else{
			addMessage("Erro ao alterar funcionario");
		}
	}
	public String sair(){
		funcionarioNovo=new Funcionario();
		return "/index.xhtml";
	}
	public void addMessage(String summary) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
