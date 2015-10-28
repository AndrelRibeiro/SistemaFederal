package beans;

import java.util.Date;

public class Remessa {
private String nome;
private int contrato;
private String endereco;
private String bairro;
private String cidade;
private String estado;
private String cpf;
private String cep;
private Date vencimento;
private double valor;
public Remessa(){
	
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public int getContrato() {
	return contrato;
}
public void setContrato(int contrato) {
	this.contrato = contrato;
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
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
public String getCep() {
	return cep;
}
public void setCep(String cep) {
	this.cep = cep;
}
public Date getVencimento() {
	return vencimento;
}
public void setVencimento(Date vencimento) {
	this.vencimento = vencimento;
}
public double getValor() {
	return valor;
}
public void setValor(double valor) {
	this.valor = valor;
}

}
