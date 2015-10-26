package beans;

import java.io.Serializable;

public class Vendedor implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int idVendedor;
private String nome;
private String celular;
private String telefone;
private String email;
private String logradouro;
private String numero;
private String cep;
private String bairro;
private String complemento;
private String cidade;
private String estado;
public Vendedor(){
	
}

public int getIdVendedor() {
	return idVendedor;
}

public void setIdVendedor(int idVendedor) {
	this.idVendedor = idVendedor;
}

public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCelular() {
	return celular;
}
public void setCelular(String celular) {
	this.celular = celular;
}
public String getTelefone() {
	return telefone;
}
public void setTelefone(String telefone) {
	this.telefone = telefone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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

@Override
public String toString() {
	return "Vendedor [idVendedor=" + idVendedor + ", nome=" + nome
			+ ", celular=" + celular + ", telefone=" + telefone + ", email="
			+ email + ", logradouro=" + logradouro + ", numero=" + numero
			+ ", cep=" + cep + ", bairro=" + bairro + ", complemento="
			+ complemento + ", cidade=" + cidade + ", estado=" + estado + "]";
}


}
