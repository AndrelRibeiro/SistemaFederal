package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.PrecoDao;
import dao.PrecoDaoImplementation;
import beans.Preco;
@ManagedBean
@ViewScoped
public class PrecoMB implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Preco precoNovo=new Preco();
private List<Preco>precos=new ArrayList<Preco>();
public PrecoMB(){
	//precoNovo.setAdesao(450.00);
}
public Preco getPrecoNovo() {
	return precoNovo;
}
public void setPrecoNovo(Preco precoNovo) {
	this.precoNovo = precoNovo;
}
public List<Preco> getPrecos() {
	return precos;
}
public void setPrecos(List<Preco> precos) {
	this.precos = precos;
}
public void adicionar(){
	
	String valor6[]=precoNovo.getSadesao().split("\\,");
	precoNovo.setAdesao(Double.parseDouble(valor6[0]));
	PrecoDao pr=new PrecoDaoImplementation();
	boolean retorno=pr.adicionar(precoNovo);
	if(retorno){
		addMessage("Sucesso","Valores adicionados com sucesso!");
	}else{
		addMessage("Erro","Os valores não puderam ser adicionados!");
	}
}
public void alterar(){
	
}
public void remover(){
	
}

public List<Preco> listar(){
	
	return precos;
}
public void addMessage(String tipo, String mensagem) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,  null);
    FacesContext.getCurrentInstance().addMessage(tipo, message);
}
}
