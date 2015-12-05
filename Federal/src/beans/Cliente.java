package beans;

import java.io.Serializable;
import java.util.Date;
public class Cliente implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int idCliente;
private int numeroContrato;
private String nome;
private Date nascimento;
private String cpf;
private String rg;
private String logradouro;
private String numero;
private String cep;
private String bairro;
private String complemento;
private String cidade;
private String estado;
private String telefone;
private String celular;
private String religiao;
private String profissao;
private String pontoRef;
private String email;
private String situacao;
private int idFuncionario; 
private String observacao;
private String estadoCivil;
private String naturalidade;
private String sexo;
private double valor;
private int cpfok;
public Cliente() {
	
}
public int getNumeroContrato() {
	return numeroContrato;
}
public void setNumeroContrato(int numeroContrato) {
	this.numeroContrato = numeroContrato;
}
public int getIdFuncionario() {
	return idFuncionario;
}
public void setIdFuncionario(int idFuncionario) {
	this.idFuncionario = idFuncionario;
}
public String getEstadoCivil() {
	return estadoCivil;
}
public void setEstadoCivil(String estadoCivil) {
	this.estadoCivil = estadoCivil;
}
public int getIdCliente() {
	return idCliente;
}
public void setIdCliente(int idCliente) {
	this.idCliente = idCliente;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public Date getNascimento() {
	return nascimento;
}
public void setNascimento(Date nascimento) {
	this.nascimento = nascimento;
}
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
public String getRg() {
	return rg;
}
public void setRg(String rg) {
	this.rg = rg;
}
public String getLogradouro() {
	return logradouro;
}
public void setLogradouro(String logradouro) {
	this.logradouro = logradouro;
}
public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}
public String getCep() {
	return cep;
}
public void setCep(String cep) {
	this.cep = cep;
}
public String getBairro() {
	return bairro;
}
public void setBairro(String bairro) {
	this.bairro = bairro;
}
public String getComplemento() {
	return complemento;
}
public void setComplemento(String complemento) {
	this.complemento = complemento;
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
public String getTelefone() {
	return telefone;
}
public void setTelefone(String telefone) {
	this.telefone = telefone;
}
public String getCelular() {
	return celular;
}
public void setCelular(String celular) {
	this.celular = celular;
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
public String getPontoRef() {
	return pontoRef;
}
public void setPontoRef(String pontoRef) {
	this.pontoRef = pontoRef;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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

public String getNaturalidade() {
	return naturalidade;
}
public void setNaturalidade(String naturalidade) {
	this.naturalidade = naturalidade;
}
public String getSexo() {
	return sexo;
}
public void setSexo(String sexo) {
	this.sexo = sexo;	
}

public double getValor() {
	return valor;
}
public void setValor(double valor) {
	this.valor = valor;
}

public int getCpfok() {
	return cpfok;
}
public void setCpfok(int cpfok) {
	this.cpfok = cpfok;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
	result = prime * result + ((celular == null) ? 0 : celular.hashCode());
	result = prime * result + ((cep == null) ? 0 : cep.hashCode());
	result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
	result = prime * result
			+ ((complemento == null) ? 0 : complemento.hashCode());
	result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((estado == null) ? 0 : estado.hashCode());
	result = prime * result
			+ ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
	result = prime * result + idCliente;
	result = prime * result + idFuncionario;
	result = prime * result
			+ ((logradouro == null) ? 0 : logradouro.hashCode());
	result = prime * result
			+ ((nascimento == null) ? 0 : nascimento.hashCode());
	result = prime * result
			+ ((naturalidade == null) ? 0 : naturalidade.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((numero == null) ? 0 : numero.hashCode());
	result = prime * result + numeroContrato;
	result = prime * result
			+ ((observacao == null) ? 0 : observacao.hashCode());
	result = prime * result + ((pontoRef == null) ? 0 : pontoRef.hashCode());
	result = prime * result + ((profissao == null) ? 0 : profissao.hashCode());
	result = prime * result + ((religiao == null) ? 0 : religiao.hashCode());
	result = prime * result + ((rg == null) ? 0 : rg.hashCode());
	result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
	result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
	result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
	Cliente other = (Cliente) obj;
	if (bairro == null) {
		if (other.bairro != null)
			return false;
	} else if (!bairro.equals(other.bairro))
		return false;
	if (celular == null) {
		if (other.celular != null)
			return false;
	} else if (!celular.equals(other.celular))
		return false;
	if (cep == null) {
		if (other.cep != null)
			return false;
	} else if (!cep.equals(other.cep))
		return false;
	if (cidade == null) {
		if (other.cidade != null)
			return false;
	} else if (!cidade.equals(other.cidade))
		return false;
	if (complemento == null) {
		if (other.complemento != null)
			return false;
	} else if (!complemento.equals(other.complemento))
		return false;
	if (cpf == null) {
		if (other.cpf != null)
			return false;
	} else if (!cpf.equals(other.cpf))
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (estado == null) {
		if (other.estado != null)
			return false;
	} else if (!estado.equals(other.estado))
		return false;
	if (estadoCivil == null) {
		if (other.estadoCivil != null)
			return false;
	} else if (!estadoCivil.equals(other.estadoCivil))
		return false;
	if (idCliente != other.idCliente)
		return false;
	if (idFuncionario != other.idFuncionario)
		return false;
	if (logradouro == null) {
		if (other.logradouro != null)
			return false;
	} else if (!logradouro.equals(other.logradouro))
		return false;
	if (nascimento == null) {
		if (other.nascimento != null)
			return false;
	} else if (!nascimento.equals(other.nascimento))
		return false;
	if (naturalidade == null) {
		if (other.naturalidade != null)
			return false;
	} else if (!naturalidade.equals(other.naturalidade))
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (numero == null) {
		if (other.numero != null)
			return false;
	} else if (!numero.equals(other.numero))
		return false;
	if (numeroContrato != other.numeroContrato)
		return false;
	if (observacao == null) {
		if (other.observacao != null)
			return false;
	} else if (!observacao.equals(other.observacao))
		return false;
	if (pontoRef == null) {
		if (other.pontoRef != null)
			return false;
	} else if (!pontoRef.equals(other.pontoRef))
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
	if (rg == null) {
		if (other.rg != null)
			return false;
	} else if (!rg.equals(other.rg))
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
	if (telefone == null) {
		if (other.telefone != null)
			return false;
	} else if (!telefone.equals(other.telefone))
		return false;
	return true;
}
@Override
public String toString() {
	return "Cliente [idCliente=" + idCliente + ", numeroContrato="
			+ numeroContrato + ", nome=" + nome + ", nascimento=" + nascimento
			+ ", cpf=" + cpf + ", rg=" + rg + ", logradouro=" + logradouro
			+ ", numero=" + numero + ", cep=" + cep + ", bairro=" + bairro
			+ ", complemento=" + complemento + ", cidade=" + cidade
			+ ", estado=" + estado + ", telefone=" + telefone + ", celular="
			+ celular + ", religiao=" + religiao + ", profissao=" + profissao
			+ ", pontoRef=" + pontoRef + ", email=" + email + ", situacao="
			+ situacao + ", idFuncionario=" + idFuncionario + ", observacao="
			+ observacao + ", estadoCivil=" + estadoCivil + ", naturalidade="
			+ naturalidade + ", sexo=" + sexo + ", valor=" + valor + ", cpfok="
			+ cpfok + "]";
}


}
