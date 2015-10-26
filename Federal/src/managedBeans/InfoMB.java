package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.MensalidadeDao;
import dao.MensalidadeDaoImplementation;
import beans.Notificacao;
import beans.Mensalidade;
@ManagedBean
@RequestScoped
public class InfoMB implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Notificacao informacaoNova=new Notificacao();
private List<Notificacao>lista;

public InfoMB(){
	
}

public Notificacao getInformacaoNova() {
	return informacaoNova;
}

public void setInformacaoNova(Notificacao informacaoNova) {
	this.informacaoNova = informacaoNova;
}

public List<Notificacao> getLista() {
	return lista;
}

public void setLista(List<Notificacao> lista) {
	this.lista = lista;
}

public List<Notificacao> listar(){
	lista=new ArrayList<Notificacao>();
	MensalidadeDao md=new MensalidadeDaoImplementation();
	List<Mensalidade>mens=new ArrayList<Mensalidade>();
	mens=md.listar("A");
	for(Mensalidade m:mens){
		informacaoNova=new Notificacao();
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("Mensalidades");
		informacaoNova.setDescricao("Mensalidade em atraso! Contrato: "+m.getContrato());
		lista.add(informacaoNova);
	}
	List<Mensalidade>carnes=new ArrayList<Mensalidade>();
	carnes=md.listarCarnes();
	for(Mensalidade mg:carnes){
		informacaoNova=new Notificacao();
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("Carnês");
		informacaoNova.setDescricao("O carnê do contrato: "+mg.getContrato()+" "+mg.getSituacao());
		lista.add(informacaoNova);
	}
	List<Mensalidade>cnrs=new ArrayList<Mensalidade>();
	cnrs=md.listarSemCNR();
	for(Mensalidade cnr:cnrs){
		informacaoNova=new Notificacao();
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("CNRs");
		informacaoNova.setDescricao("A mensalidade do contrato: "+cnr.getContrato()+" não possui número de CNR");
		lista.add(informacaoNova);
	}
	return lista;
}
public void adicionar(){
	
}
public void excluir(){
	
}

}
