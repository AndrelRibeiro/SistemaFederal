package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import beans.InformeErro;
import dao.NotificacaoDao;
import dao.NotificacaoDaoImplementation;

@ManagedBean
@RequestScoped
public class NotificaErroMB implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -2632466104217142113L;
InformeErro infoNova=new InformeErro();
private List<InformeErro>informacoes;
public NotificaErroMB(){

}
public InformeErro getInfoNova() {
	return infoNova;
}


public void setInfoNova(InformeErro infoNova) {
	this.infoNova = infoNova;
}


public List<InformeErro> getInformacoes() {
	informacoes=new ArrayList<InformeErro>();
	return informacoes;
}
public void setInformacoes(List<InformeErro> informacoes) {
	this.informacoes = informacoes;
}
public void adicionar(){
	NotificacaoDao infDao=new NotificacaoDaoImplementation();System.out.println("MB-"+infoNova.toString());
	boolean retorno=infDao.adicionar(infoNova);
	if(retorno==true){
		addMessage("Informação adicionada com sucesso!");
	}else{
		addMessage("Erro ao adicionar informação!");
	}
}
public void remover(){
	NotificacaoDao inf=new NotificacaoDaoImplementation();
	boolean retorno=inf.remover(infoNova);
}
public void alterar(){
	NotificacaoDao inf=new NotificacaoDaoImplementation();
	boolean retorno=inf.alterar(infoNova);
}
public InformeErro buscar(){
	NotificacaoDao inf=new NotificacaoDaoImplementation();
	infoNova=inf.buscar(infoNova);
	return infoNova;
}
public List<InformeErro> listar(){
	NotificacaoDao inf=new NotificacaoDaoImplementation();
	informacoes=inf.listar(infoNova);
	return informacoes;
}
public void addMessage(String summary) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
    FacesContext.getCurrentInstance().addMessage(null, message);
}
}
