package beans;

import java.io.Serializable;
import java.util.Date;

public class Mensalidade implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -3780075241673666736L;
private int idMensalidade;
private int contrato;
private int numParcela;
private Date dataVencimento;
private double valorParcela;
private String situacao;
private String documento;
private String nossoNumero;
private String dacNossoNumero;
private String codBarras;
private Date dataPagamento;//Verificar se o pagamento está em aberto ou se está atrasado. null = em aberto date = pago, comparar com data de vencimento.
private long parcela_carne_ano;
private Date dataCarencia;
private String periodicidade;
private int idFuncionario;
public Mensalidade(){
	
}
public int getIdMensalidade() {
	return idMensalidade;
}
public void setIdMensalidade(int idMensalidade) {
	this.idMensalidade = idMensalidade;
}
public int getContrato() {
	return contrato;
}
public void setContrato(int contrato) {
	this.contrato = contrato;
}
public int getNumParcela() {
	return numParcela;
}
public void setNumParcela(int numParcela) {
	this.numParcela = numParcela;
}
public Date getDataVencimento() {
	return dataVencimento;
}
public void setDataVencimento(Date dataVencimento) {
	this.dataVencimento = dataVencimento;
}
public double getValorParcela() {
	return valorParcela;
}
public void setValorParcela(double valorParcela) {
	this.valorParcela = valorParcela;
}
public String getSituacao() {
	return situacao;
}
public void setSituacao(String situacao) {
	this.situacao = situacao;
}
public String getDocumento() {
	return documento;
}
public void setDocumento(String documento) {
	this.documento = documento;
}
public String getNossoNumero() {
	return nossoNumero;
}
public void setNossoNumero(String nossoNumero) {
	this.nossoNumero = nossoNumero;
}
public String getDacNossoNumero() {
	return dacNossoNumero;
}
public void setDacNossoNumero(String dacNossoNumero) {
	this.dacNossoNumero = dacNossoNumero;
}
public String getCodBarras() {
	return codBarras;
}
public void setCodBarras(String codBarras) {
	this.codBarras = codBarras;
}
public Date getDataPagamento() {
	return dataPagamento;
}
public void setDataPagamento(Date dataPagamento) {
	this.dataPagamento = dataPagamento;
}
public long getParcela_carne_ano() {
	return parcela_carne_ano;
}
public void setParcela_carne_ano(long parcela_carne_ano) {
	this.parcela_carne_ano = parcela_carne_ano;
}
public Date getDataCarencia() {
	return dataCarencia;
}
public void setDataCarencia(Date dataCarencia) {
	this.dataCarencia = dataCarencia;
}
public String getPeriodicidade() {
	return periodicidade;
}
public void setPeriodicidade(String periodicidade) {
	this.periodicidade = periodicidade;
}
public int getIdFuncionario() {
	return idFuncionario;
}
public void setIdFuncionario(int idFuncionario) {
	this.idFuncionario = idFuncionario;
}

@Override
public String toString() {
	return "Mensalidade [idMensalidade=" + idMensalidade + ", contrato="
			+ contrato + ", numParcela=" + numParcela + ", dataVencimento="
			+ dataVencimento + ", valorParcela=" + valorParcela + ", situacao="
			+ situacao + ", documento=" + documento + ", nossoNumero="
			+ nossoNumero + ", dacNossoNumero=" + dacNossoNumero
			+ ", codBarras=" + codBarras + ", dataPagamento=" + dataPagamento
			+ ", parcela_carne_ano=" + parcela_carne_ano + ", dataCarencia="
			+ dataCarencia + ", periodicidade=" + periodicidade
			+ ", idFuncionario=" + idFuncionario + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((codBarras == null) ? 0 : codBarras.hashCode());
	result = prime * result + contrato;
	result = prime * result
			+ ((dacNossoNumero == null) ? 0 : dacNossoNumero.hashCode());
	result = prime * result
			+ ((dataCarencia == null) ? 0 : dataCarencia.hashCode());
	result = prime * result
			+ ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
	result = prime * result
			+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
	result = prime * result + ((documento == null) ? 0 : documento.hashCode());
	result = prime * result + idFuncionario;
	result = prime * result + idMensalidade;
	result = prime * result
			+ ((nossoNumero == null) ? 0 : nossoNumero.hashCode());
	result = prime * result + numParcela;
	result = prime * result
			+ (int) (parcela_carne_ano ^ (parcela_carne_ano >>> 32));
	result = prime * result
			+ ((periodicidade == null) ? 0 : periodicidade.hashCode());
	result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
	long temp;
	temp = Double.doubleToLongBits(valorParcela);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Mensalidade other = (Mensalidade) obj;
	if (codBarras == null) {
		if (other.codBarras != null)
			return false;
	} else if (!codBarras.equals(other.codBarras))
		return false;
	if (contrato != other.contrato)
		return false;
	if (dacNossoNumero == null) {
		if (other.dacNossoNumero != null)
			return false;
	} else if (!dacNossoNumero.equals(other.dacNossoNumero))
		return false;
	if (dataCarencia == null) {
		if (other.dataCarencia != null)
			return false;
	} else if (!dataCarencia.equals(other.dataCarencia))
		return false;
	if (dataPagamento == null) {
		if (other.dataPagamento != null)
			return false;
	} else if (!dataPagamento.equals(other.dataPagamento))
		return false;
	if (dataVencimento == null) {
		if (other.dataVencimento != null)
			return false;
	} else if (!dataVencimento.equals(other.dataVencimento))
		return false;
	if (documento == null) {
		if (other.documento != null)
			return false;
	} else if (!documento.equals(other.documento))
		return false;
	if (idFuncionario != other.idFuncionario)
		return false;
	if (idMensalidade != other.idMensalidade)
		return false;
	if (nossoNumero == null) {
		if (other.nossoNumero != null)
			return false;
	} else if (!nossoNumero.equals(other.nossoNumero))
		return false;
	if (numParcela != other.numParcela)
		return false;
	if (parcela_carne_ano != other.parcela_carne_ano)
		return false;
	if (periodicidade == null) {
		if (other.periodicidade != null)
			return false;
	} else if (!periodicidade.equals(other.periodicidade))
		return false;
	if (situacao == null) {
		if (other.situacao != null)
			return false;
	} else if (!situacao.equals(other.situacao))
		return false;
	if (Double.doubleToLongBits(valorParcela) != Double
			.doubleToLongBits(other.valorParcela))
		return false;
	return true;
}

}
