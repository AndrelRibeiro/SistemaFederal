package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.AtendimentoDao;
import dao.AtendimentoDaoImplementation;
import beans.Atendimento;
@ManagedBean
@RequestScoped
public class AtendimentoMB implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Atendimento atendimentoNovo=new Atendimento();
private List<Atendimento>atendimentos;
private String radio;
private Date data;
public AtendimentoMB(){
	Calendar c=Calendar.getInstance();
	data=c.getTime();System.out.println(data);
}
@PostConstruct 
public void init() {
	radio = "1"; 
}

public Date getData() {
	return data;
}
public void setData(Date data) {
	this.data = data;
}
public Atendimento getAtendimentoNovo() {
	return atendimentoNovo;
}
public void setAtendimentoNovo(Atendimento atendimentoNovo) {
	this.atendimentoNovo = atendimentoNovo;
}
public List<Atendimento> getAtendimentos() {
	return atendimentos;
}
public void setAtendimentos(List<Atendimento> atendimentos) {
	this.atendimentos = atendimentos;
}
public String getRadio() {
	return radio;
}
public void setRadio(String radio) {
	this.radio = radio;
}
public void adicionar(){
	AtendimentoDao ad=new AtendimentoDaoImplementation();
	boolean retorno=ad.adicionar(atendimentoNovo);
	if(retorno==true){
		System.out.println("Atendimento Adicionado com sucesso!");
	}else{
		System.out.println("Erro ao Adicionar atendimento!");
	}
}
public void buscar(){
	AtendimentoDao ad=new AtendimentoDaoImplementation();
	atendimentoNovo=ad.buscar(atendimentoNovo);
}
public void buscarPorContrato(){
	atendimentos=new ArrayList<Atendimento>();
	AtendimentoDao ad=new AtendimentoDaoImplementation();
	atendimentos=ad.buscarPorContrato(atendimentoNovo);
	
}
public List<Atendimento> listar(){
	//Ordenar por contrato
	AtendimentoDao ad=new AtendimentoDaoImplementation();
	atendimentos=new ArrayList<Atendimento>();
	atendimentos=ad.listar();
	return atendimentos;
}
public void excluir(){
	AtendimentoDao ad=new AtendimentoDaoImplementation();
	boolean retorno=ad.excluir(atendimentoNovo);
	if(retorno==true){
		System.out.println("Atendimento Excluido com sucesso!");
	}else{
		System.out.println("Erro ao Excluir atendimento!");
	}
}
public void atualizar(){
	AtendimentoDao ad=new AtendimentoDaoImplementation();
	boolean retorno=ad.alterar(atendimentoNovo);
	if(retorno==true){
		System.out.println("Atendimento Atualizado com sucesso!");
	}else{
		System.out.println("Erro ao Atualizar atendimento!");
	}
}

}
