package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import beans.Atendimento;
import beans.Beneficiario;
import dao.AtendimentoDao;
import dao.AtendimentoDaoImplementation;
import dao.BeneficiarioDao;
import dao.BeneficiarioDaoImplementation;
@ManagedBean
@RequestScoped
public class AtendimentoMB implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Atendimento atendimentoNovo=new Atendimento();
private Beneficiario beneficiarioNovo=new Beneficiario();
private List<Beneficiario>beneficiarios;
private List<Atendimento>atendimentos;
private String tipo;
private String mensagem;
private String radio;
private Date data;
public AtendimentoMB(){
	//Calendar c=Calendar.getInstance();
	//data=c.getTime();System.out.println(data);
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
public boolean adicionar(){
	AtendimentoDao ad=new AtendimentoDaoImplementation();
	boolean retorno=ad.adicionar(atendimentoNovo);
	if(retorno==true){
		System.out.println("Atendimento Adicionado com sucesso!");
	}else{
		System.out.println("Erro ao Adicionar atendimento!");
	}
	return retorno;
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
public List<Beneficiario> buscarBeneficiarios(){
	beneficiarios=new ArrayList<Beneficiario>();
	BeneficiarioDao bn=new BeneficiarioDaoImplementation();
	beneficiarios=bn.buscar(beneficiarioNovo.getContrato());
return beneficiarios;
}
public String atender(){
	BeneficiarioDao bd=new BeneficiarioDaoImplementation();
	boolean resultado=false,ret=adicionar();
	if(ret){
	resultado=bd.registrar(beneficiarioNovo);System.out.println("bene Registrar");}
	beneficiarioNovo=new Beneficiario();
	beneficiarios=new ArrayList<Beneficiario>();
	String result="";
	if(resultado==true){
		mensagem="Atendimento registrado!";
		tipo="Sucesso!";
		addMessage(tipo,mensagem);
		result= "/index.xhtml";
	}else{
		mensagem="Erro ao registrar Atendimento";
		tipo="Erro!";
		addMessage(tipo,mensagem);
		result= "/informe.xhtml";
	}
	return result;
}
public void addMessage(String tipo, String mensagem) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,tipo, mensagem);
    FacesContext.getCurrentInstance().addMessage(null, message);
}
}
