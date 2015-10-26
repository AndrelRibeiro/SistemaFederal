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
	
	String valor[]=precoNovo.getSespecial().split("\\,");
	String valor2[]=precoNovo.getSexecutivo().split("\\,");
	String valor3[]=precoNovo.getScrematorio().split("\\,");
	String valor4[]=precoNovo.getSurna().split("\\,");
	String valor5[]=precoNovo.getSsepultamento().split("\\,");
	String valor6[]=precoNovo.getSadesao().split("\\,");
	//String valor7[]=precoNovo.getSpromocao().split("\\,");
	//String valor8[]=precoNovo.getSavista().split("\\,");
	//String valor9[]=precoNovo.getSmensal().split("\\,");
	String valor10[]=precoNovo.getSdep66().split("\\,");
	String valor11[]=precoNovo.getSdep76().split("\\,");
	String valor12[]=precoNovo.getSdep86().split("\\,");
	String valor13[]=precoNovo.getSdep91().split("\\,");
	String valor14[]=precoNovo.getSavu65().split("\\,");
	String valor15[]=precoNovo.getSavu66().split("\\,");
	String valor16[]=precoNovo.getSavu76().split("\\,");
	String valor17[]=precoNovo.getSavu86().split("\\,");
	String valor18[]=precoNovo.getSavu91().split("\\,");
	String valor19[]=precoNovo.getSminimo().split("\\,");
	precoNovo.setEspecial(Double.parseDouble(valor[0]));
	precoNovo.setExecutivo(Double.parseDouble(valor2[0]));
	precoNovo.setCrematorio(Double.parseDouble(valor3[0]));
	precoNovo.setUrna(Double.parseDouble(valor4[0]));
	precoNovo.setSepultamento(Double.parseDouble(valor5[0]));
	precoNovo.setAdesao(Double.parseDouble(valor6[0]));
	//precoNovo.setPromocao(Double.parseDouble(valor7[0]));
	//precoNovo.setAvista(Double.parseDouble(valor8[0]));
	//precoNovo.setMensal(Double.parseDouble(valor9[0]));
	precoNovo.setDep66(Double.parseDouble(valor10[0]));
	precoNovo.setDep76(Double.parseDouble(valor11[0]));
	precoNovo.setDep86(Double.parseDouble(valor12[0]));
	precoNovo.setDep91(Double.parseDouble(valor13[0]));
	precoNovo.setAvu65(Double.parseDouble(valor14[0]));
	precoNovo.setAvu66(Double.parseDouble(valor15[0]));
	precoNovo.setAvu76(Double.parseDouble(valor16[0]));
	precoNovo.setAvu86(Double.parseDouble(valor17[0]));
	precoNovo.setAvu91(Double.parseDouble(valor18[0]));
	precoNovo.setMinimo(Double.parseDouble(valor19[0]));
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
