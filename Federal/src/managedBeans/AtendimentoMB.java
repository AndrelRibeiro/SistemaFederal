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
private Atendimento atendimentoNovo;
private Beneficiario beneficiarioNovo;
private List<Beneficiario>beneficiarios;
private List<Atendimento>atendimentos;
private String tipo;
private String mensagem;
private String radio;
private Date data;
public AtendimentoMB(){
	
}
@PostConstruct 
public void init() {
	Calendar c=Calendar.getInstance();
	data=c.getTime();System.out.println(data);
	radio = "1"; 
	beneficiarioNovo=new Beneficiario();
	atendimentoNovo=new Atendimento();
}

public Date getData() {
	return data;
}
public void setData(Date data) {
	this.data = data;
}
public Atendimento getAtendimentoNovo() {
	if(atendimentoNovo==null){
	}
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
	AtendimentoDao ad=new AtendimentoDaoImplementation();System.out.println("Adicionar AtendimentoMB: "+atendimentoNovo.toString());
	if(radio.equalsIgnoreCase("1")){
		atendimentoNovo.setRessarcimento(0.0);
	}
	atendimentoNovo.setContrato(beneficiarioNovo.getContrato());
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
public void atender(){
	BeneficiarioDao bd=new BeneficiarioDaoImplementation();System.out.println("Entrada do objeto: Atender: "+beneficiarioNovo.toString());
	boolean resultado=false,ret=false;
	ret=adicionar();
	if(ret){}
	resultado=bd.registrar(beneficiarioNovo);
	beneficiarioNovo=new Beneficiario();
	beneficiarios=new ArrayList<Beneficiario>();
	if(resultado==true){
		mensagem="Atendimento registrado!";
		tipo="Sucesso!";
		addMessage(tipo,mensagem);
	}else{
		mensagem="Erro ao registrar Atendimento";
		tipo="Erro!";
		addMessage(tipo,mensagem);
	}
}
public void addMessage(String tipo, String mensagem) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,tipo, mensagem);
    FacesContext.getCurrentInstance().addMessage(null, message);
}
}
