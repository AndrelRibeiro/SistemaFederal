package beans;

import java.io.Serializable;
import java.util.Date;


public class Atendimento  implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int idAtendimento;
private int contrato;
private int idBeneficiario;
private Date falecimento;
private Date data_atendimento;
private int numNota;
private String agencia;
private double ressarcimento;
private String observacao;
private int idFuncionario;
public Atendimento(){

}
public int getIdAtendimento() {
	return idAtendimento;
}
public void setIdAtendimento(int idAtendimento) {
	this.idAtendimento = idAtendimento;
}

public int getContrato() {
	return contrato;
}
public void setContrato(int contrato) {
	this.contrato = contrato;
}
public int getIdBeneficiario() {
	return idBeneficiario;
}
public void setIdBeneficiario(int idBeneficiario) {
	this.idBeneficiario = idBeneficiario;
}
public Date getFalecimento() {
	return falecimento;
}
public void setFalecimento(Date falecimento) {
	this.falecimento = falecimento;
}
public Date getData_atendimento() {
	return data_atendimento;
}
public void setData_atendimento(Date data_atendimento) {
	this.data_atendimento = data_atendimento;
}

public int getNumNota() {
	return numNota;
}
public void setNumNota(int numNota) {
	this.numNota = numNota;
}
public String getAgencia() {
	return agencia;
}
public void setAgencia(String agencia) {
	this.agencia = agencia;
}
public double getRessarcimento() {
	return ressarcimento;
}
public void setRessarcimento(double ressarcimento) {
	this.ressarcimento = ressarcimento;
}

public String getObservacao() {
	return observacao;
}
public void setObservacao(String observacao) {
	this.observacao = observacao;
}
public int getIdFuncionario() {
	return idFuncionario;
}
public void setIdFuncionario(int idFuncionario) {
	this.idFuncionario = idFuncionario;
}
@Override
public String toString() {
	return "Atendimento [idAtendimento=" + idAtendimento + ", contrato="
			+ contrato + ", idBeneficiario=" + idBeneficiario
			+ ", falecimento=" + falecimento + ", data_atendimento="
			+ data_atendimento + ", numNota=" + numNota + ", agencia="
			+ agencia + ", ressarcimento=" + ressarcimento + ", idFuncionario="
			+ idFuncionario + "]";
}

}
