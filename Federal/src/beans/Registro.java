package beans;

import java.io.Serializable;

public class Registro implements Serializable{
	/**
	 * Bean para objeto recuperado do arquivo de importação
	 */
	private static final long serialVersionUID = 1L;
	private String contrato;
	private String nome;
	private String CPF;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String vencimento;
	private String tipoRegRegistro;//REGISTRO
	private String nossoNumero;//REGISTRO
	private String codOcorrencia;//REGISTRO
	private String dataOcorrencia;//REGISTRO
	private String valorTitulo;//REGISTRO
	private String codLiquidacao;//REGISTRO
	private int numeroSequencial;//REGISTRO
	public Registro(){
		
	}
	
	public String getContrato() {
		return contrato;
	}

	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
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
		return "Registro [contrato=" + contrato + ", nome=" + nome + ", CPF="
				+ CPF + ", endereco=" + endereco + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep
				+ ", vencimento=" + vencimento + ", tipoRegRegistro="
				+ tipoRegRegistro + ", nossoNumero=" + nossoNumero
				+ ", codOcorrencia=" + codOcorrencia + ", dataOcorrencia="
				+ dataOcorrencia + ", valorTitulo=" + valorTitulo
				+ ", codLiquidacao=" + codLiquidacao + ", numeroSequencial="
				+ numeroSequencial + "]";
	}

	
	
	
}
