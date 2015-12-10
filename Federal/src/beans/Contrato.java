package beans;

import java.io.Serializable;
import java.util.Date;

public class Contrato implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int nContrato;
private double valorContrato;
private double entrada;
private int parcEntrada;
private double vParcelas;
private double mensalidade;
private int nTaxa1;
private int nTaxa2;
private int nTaxa3;
private int nTaxa4;
private int nTaxa5;
private double vTaxa1;
private double vTaxa2;
private double vTaxa3;
private double vTaxa4;
private double vTaxa5;
private Date dataContrato;
private Date dataCancelamento;
private String periodicidade;
private String situacao;
private Date carencia;
private double valorTotal;
private String plano;
private int idFuncionario;
private int diaVencimento;
private int idVendedor;
private int atualizado;
public Contrato(){
	//valorContrato=450;
	//parcEntrada=1;
}
public int getnContrato() {
	return nContrato;
}
public void setnContrato(int nContrato) {
	this.nContrato = nContrato;
}
public double getValorContrato() {
		
	return valorContrato;
}
public void setValorContrato(double valorContrato) {
	this.valorContrato = valorContrato;
}
public double getEntrada() {
	return entrada;
}
public void setEntrada(double entrada) {
	this.entrada = entrada;
}
public int getParcEntrada() {
	return parcEntrada;
}
public void setParcEntrada(int parcEntrada) {
	this.parcEntrada = parcEntrada;
}
public double getvParcelas() {
	return vParcelas;
}
public void setvParcelas(double vParcelas) {
	this.vParcelas = vParcelas;
}
public double getMensalidade() {
	return mensalidade;
}
public void setMensalidade(double mensalidade) {
	this.mensalidade = mensalidade;
}
public int getnTaxa1() {
	return nTaxa1;
}
public void setnTaxa1(int nTaxa1) {
	this.nTaxa1 = nTaxa1;
}
public int getnTaxa2() {
	return nTaxa2;
}
public void setnTaxa2(int nTaxa2) {
	this.nTaxa2 = nTaxa2;
}
public int getnTaxa3() {
	return nTaxa3;
}
public void setnTaxa3(int nTaxa3) {
	this.nTaxa3 = nTaxa3;
}
public int getnTaxa4() {
	return nTaxa4;
}
public void setnTaxa4(int nTaxa4) {
	this.nTaxa4 = nTaxa4;
}
public int getnTaxa5() {
	return nTaxa5;
}
public void setnTaxa5(int nTaxa5) {
	this.nTaxa5 = nTaxa5;
}
public double getvTaxa1() {
	return vTaxa1;
}
public void setvTaxa1(double vTaxa1) {
	this.vTaxa1 = vTaxa1;
}
public double getvTaxa2() {
	return vTaxa2;
}
public void setvTaxa2(double vTaxa2) {
	this.vTaxa2 = vTaxa2;
}
public double getvTaxa3() {
	return vTaxa3;
}
public void setvTaxa3(double vTaxa3) {
	this.vTaxa3 = vTaxa3;
}
public double getvTaxa4() {
	return vTaxa4;
}
public void setvTaxa4(double vTaxa4) {
	this.vTaxa4 = vTaxa4;
}
public double getvTaxa5() {
	return vTaxa5;
}
public void setvTaxa5(double vTaxa5) {
	this.vTaxa5 = vTaxa5;
}
public Date getDataContrato() {
	return dataContrato;
}
public void setDataContrato(Date dataContrato) {
	this.dataContrato = dataContrato;
}
public Date getDataCancelamento() {
	return dataCancelamento;
}
public void setDataCancelamento(Date dataCancelamento) {
	this.dataCancelamento = dataCancelamento;
}
public String getPeriodicidade() {
	return periodicidade;
}
public void setPeriodicidade(String periodicidade) {
	this.periodicidade = periodicidade;
}
public String getSituacao() {
	return situacao;
}
public void setSituacao(String situacao) {
	this.situacao = situacao;
}
public Date getCarencia() {
	return carencia;
}
public void setCarencia(Date carencia) {
	this.carencia = carencia;
}
public double getValorTotal() {
	return valorTotal;
}
public void setValorTotal(double valorTotal) {
	this.valorTotal = valorTotal;
}
public String getPlano() {
	return plano;
}
public void setPlano(String plano) {
	this.plano = plano;
}
public int getIdFuncionario() {
	return idFuncionario;
}
public void setIdFuncionario(int idFuncionario) {
	this.idFuncionario = idFuncionario;
}
public int getDiaVencimento() {
	return diaVencimento;
}
public void setDiaVencimento(int diaVencimento) {
	this.diaVencimento = diaVencimento;
}

public int getIdVendedor() {
	return idVendedor;
}
public void setIdVendedor(int idVendedor) {
	this.idVendedor = idVendedor;
}

public int getAtualizado() {
	return atualizado;
}
public void setAtualizado(int atualizado) {
	this.atualizado = atualizado;
}
@Override
public String toString() {
	return "Contrato [nContrato=" + nContrato + ", valorContrato="
			+ valorContrato + ", entrada=" + entrada + ", parcEntrada="
			+ parcEntrada + ", vParcelas=" + vParcelas + ", mensalidade="
			+ mensalidade + ", nTaxa1=" + nTaxa1 + ", nTaxa2=" + nTaxa2
			+ ", nTaxa3=" + nTaxa3 + ", nTaxa4=" + nTaxa4 + ", nTaxa5="
			+ nTaxa5 + ", vTaxa1=" + vTaxa1 + ", vTaxa2=" + vTaxa2
			+ ", vTaxa3=" + vTaxa3 + ", vTaxa4=" + vTaxa4 + ", vTaxa5="
			+ vTaxa5 + ", dataContrato=" + dataContrato + ", dataCancelamento="
			+ dataCancelamento + ", periodicidade=" + periodicidade
			+ ", situacao=" + situacao + ", carencia=" + carencia
			+ ", valorTotal=" + valorTotal + ", plano=" + plano
			+ ", idFuncionario=" + idFuncionario + ", diaVencimento="
			+ diaVencimento + ", idVendedor=" + idVendedor + ", atualizado="
			+ atualizado + "]";
}

}