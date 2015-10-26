package beans;

import java.io.Serializable;
import java.util.Date;

public class Beneficiario implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int idBeneficiario;//
private int contrato;//
private String email;
private String nome;//
private Date dataNascimento;//
private String classificacao;//
private String estadoCivil;//
private String sexo;
private String religiao;
private String profissao;
private String situacao;
private String observacao;
private String falecimento;
private int idFuncionario;
private Date dataCadastro;
public Beneficiario(){
	
}
public int getIdBeneficiario() {
	return idBeneficiario;
}
public void setIdBeneficiario(int idBeneficiario) {
	this.idBeneficiario = idBeneficiario;
}
public int getContrato() {
	return contrato;
}
public void setContrato(int contrato) {
	this.contrato = contrato;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public Date getDataNascimento() {
	return dataNascimento;
}
public void setDataNascimento(Date dataNascimento) {
	this.dataNascimento = dataNascimento;	
}
public String getClassificacao() {
	return classificacao;
}
public void setClassificacao(String classificacao) {
	this.classificacao = classificacao;
}
public String getEstadoCivil() {
	return estadoCivil;
}
public void setEstadoCivil(String estadoCivil) {
	this.estadoCivil = estadoCivil;
}
public String getSexo() {
	return sexo;
}
public void setSexo(String sexo) {
	this.sexo = sexo;
}
public String getReligiao() {
	return religiao;
}
public void setReligiao(String religiao) {
	this.religiao = religiao;
}
public String getProfissao() {
	return profissao;
}
public void setProfissao(String profissao) {
	this.profissao = profissao;
}
public String getSituacao() {
	return situacao;
}
public void setSituacao(String situacao) {
	this.situacao = situacao;
}
public String getObservacao() {
	return observacao;
}
public void setObservacao(String observacao) {
	this.observacao = observacao;
}

public String getFalecimento() {
	return falecimento;
}
public void setFalecimento(String falecimento) {
	this.falecimento = falecimento;
}
public int getIdFuncionario() {
	return idFuncionario;
}
public void setIdFuncionario(int idFuncionario) {
	this.idFuncionario = idFuncionario;
}
public Date getDataCadastro() {
	return dataCadastro;
}
public void setDataCadastro(Date dataCadastro) {
	this.dataCadastro = dataCadastro;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((classificacao == null) ? 0 : classificacao.hashCode());
	result = prime * result + contrato;
	result = prime * result
			+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
	result = prime * result
			+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result
			+ ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
	result = prime * result
			+ ((falecimento == null) ? 0 : falecimento.hashCode());
	result = prime * result + idBeneficiario;
	result = prime * result + idFuncionario;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result
			+ ((observacao == null) ? 0 : observacao.hashCode());
	result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
	result = prime * result + ((religiao == null) ? 0 : religiao.hashCode());
	result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
	result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
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
	Beneficiario other = (Beneficiario) obj;
	if (classificacao == null) {
		if (other.classificacao != null)
			return false;
	} else if (!classificacao.equals(other.classificacao))
		return false;
	if (contrato != other.contrato)
		return false;
	if (dataCadastro == null) {
		if (other.dataCadastro != null)
			return false;
	} else if (!dataCadastro.equals(other.dataCadastro))
		return false;
	if (dataNascimento == null) {
		if (other.dataNascimento != null)
			return false;
	} else if (!dataNascimento.equals(other.dataNascimento))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (estadoCivil == null) {
		if (other.estadoCivil != null)
			return false;
	} else if (!estadoCivil.equals(other.estadoCivil))
		return false;
	if (falecimento == null) {
		if (other.falecimento != null)
			return false;
	} else if (!falecimento.equals(other.falecimento))
		return false;
	if (idBeneficiario != other.idBeneficiario)
		return false;
	if (idFuncionario != other.idFuncionario)
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (observacao == null) {
		if (other.observacao != null)
			return false;
	} else if (!observacao.equals(other.observacao))
		return false;
	if (profissao == null) {
		if (other.profissao != null)
			return false;
	} else if (!profissao.equals(other.profissao))
		return false;
	if (religiao == null) {
		if (other.religiao != null)
			return false;
	} else if (!religiao.equals(other.religiao))
		return false;
	if (sexo == null) {
		if (other.sexo != null)
			return false;
	} else if (!sexo.equals(other.sexo))
		return false;
	if (situacao == null) {
		if (other.situacao != null)
			return false;
	} else if (!situacao.equals(other.situacao))
		return false;
	return true;
}
@Override
public String toString() {
	return "Beneficiario [idBeneficiario=" + idBeneficiario + ", contrato="
			+ contrato + ", email=" + email + ", nome=" + nome
			+ ", dataNascimento=" + dataNascimento + ", classificacao="
			+ classificacao + ", estadoCivil=" + estadoCivil + ", sexo=" + sexo
			+ ", religiao=" + religiao + ", profissao=" + profissao
			+ ", situacao=" + situacao + ", observacao=" + observacao
			+ ", falecimento=" + falecimento + ", idFuncionario="
			+ idFuncionario + ", dataCadastro=" + dataCadastro + "]";
}

}