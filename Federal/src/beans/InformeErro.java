package beans;

import java.io.Serializable;
import java.util.Date;

public class InformeErro implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int idInformacao;
private String descricao;
private Date data;
private int contrato;
private String objeto;
private String casoDeUso;
private int idFuncionario;
public InformeErro() {
	

}

public int getIdInformacao() {System.out.println("getid" +idInformacao);
	return idInformacao;
}
public void setIdInformacao(int idInformacao) {System.out.println("setid"+idInformacao);
	this.idInformacao = idInformacao;
}
public String getDescricao() {System.out.println("getdescricao"+descricao);
	return descricao;
}
public void setDescricao(String descricao) {System.out.println("setdescricao"+descricao);
	this.descricao = descricao;
}
public Date getData() {
	return data;
}
public void setData(Date data) {
	this.data = data;
}
public int getContrato() {
	return contrato;
}
public void setContrato(int contrato) {
	this.contrato = contrato;
}
public String getObjeto() {
	return objeto;
}
public void setObjeto(String objeto) {
	this.objeto = objeto;
}
public String getCasoDeUso() {
	return casoDeUso;
}
public void setCasoDeUso(String casoDeUso) {
	this.casoDeUso = casoDeUso;
}
public int getIdFuncionario() {
	return idFuncionario;
}
public void setIdFuncionario(int idFuncionario) {
	this.idFuncionario = idFuncionario;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
@Override
public String toString() {
	return "InformeErro [idInformacao=" + idInformacao + ", descricao="
			+ descricao + ", data=" + data + ", contrato=" + contrato
			+ ", objeto=" + objeto + ", casoDeUso=" + casoDeUso
			+ ", idFuncionario=" + idFuncionario + "]";
}

}