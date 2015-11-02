package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import beans.Cidade;
import beans.Estado;
import dao.EstadoCidadeDao;
import dao.EstadoCidadeDaoImplementation;
@ManagedBean
@RequestScoped
public class EstadoCidadeMB implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 613545447322428361L;
private Estado estadoNovo;
private Cidade cidadeNova;
private List<Estado> estados;
private List<Cidade>cidades;

public EstadoCidadeMB(){
	
}
@PostConstruct
public void init(){
	estados=buscarEstados();
	estadoNovo=new Estado();
}


public Estado getEstadoNovo() {
	if(estadoNovo==null){
		estadoNovo=new Estado();
	}
	return estadoNovo;
}
public void setEstadoNovo(Estado estadoNovo) {
	this.estadoNovo = estadoNovo;
}
public Cidade getCidadeNova() {
	if(cidadeNova==null){
		cidadeNova=new Cidade();
	}
	return cidadeNova;
}
public void setCidadeNova(Cidade cidadeNova) {
	this.cidadeNova = cidadeNova;
}
public List<Estado> getEstados() {
	return estados;
}
public void setEstados(List<Estado> estados) {
	this.estados = estados;
}
public List<Cidade> getCidades() {
	return cidades;
}
public void setCidades(List<Cidade> cidades) {
	this.cidades = cidades;
}
public List<Estado> buscarEstados(){
	estados=new ArrayList<Estado>();
	EstadoCidadeDao ecd=new EstadoCidadeDaoImplementation();
	estados=ecd.buscarEstados();
	
	return estados;
}

public void listarCidades(){
	cidades=new ArrayList<Cidade>();
	EstadoCidadeDao ecd=new EstadoCidadeDaoImplementation();
	cidades=ecd.buscarCidades(estadoNovo.getId());
	FacesContext.getCurrentInstance().getRenderResponse();
}
    }
