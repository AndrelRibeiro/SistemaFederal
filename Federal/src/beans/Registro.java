package beans;

import java.io.Serializable;

public class Registro implements Serializable{
	/**
	 * Bean para objeto recuperado do arquivo de importação
	 */
	private static final long serialVersionUID = 1L;
	private String tipoRegRegistro;//REGISTRO
	private String nossoNumero;//REGISTRO
	private String codOcorrencia;//REGISTRO
	private String dataOcorrencia;//REGISTRO
	private String valorTitulo;//REGISTRO
	private String codLiquidacao;//REGISTRO
	private int numeroSequencial;//REGISTRO
	public Registro(){
		
	}
	public String getTipoRegRegistro() {
		return tipoRegRegistro;
	}
	public void setTipoRegRegistro(String tipoRegRegistro) {
		this.tipoRegRegistro = tipoRegRegistro;
	}
	public String getNossoNumero() {
		return nossoNumero;
	}
	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}
	public String getCodOcorrencia() {
		return codOcorrencia;
	}
	public void setCodOcorrencia(String codOcorrencia) {
		this.codOcorrencia = codOcorrencia;
	}
	public String getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(String dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	public String getValorTitulo() {
		return valorTitulo;
	}
	public void setValorTitulo(String valorTitulo) {
		this.valorTitulo = valorTitulo;
	}
	public String getCodLiquidacao() {
		return codLiquidacao;
	}
	public void setCodLiquidacao(String codLiquidacao) {
		this.codLiquidacao = codLiquidacao;
	}
	public int getNumeroSequencial() {
		return numeroSequencial;
	}
	public void setNumeroSequencial(int numeroSequencial) {
		this.numeroSequencial = numeroSequencial;
	}
	@Override
	public String toString() {
		return "Registro [tipoRegRegistro=" + tipoRegRegistro
				+ ", nossoNumero=" + nossoNumero + ", codOcorrencia="
				+ codOcorrencia + ", dataOcorrencia=" + dataOcorrencia
				+ ", valorTitulo=" + valorTitulo + ", codLiquidacao="
				+ codLiquidacao + ", numeroSequencial=" + numeroSequencial
				+ "]";
	}
	
}
