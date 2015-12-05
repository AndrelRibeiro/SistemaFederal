package beans;

import java.io.Serializable;

public class Funcionario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int idFuncionario;
private String nome;
private String login;  
private String senha;
private String repsenha;
private String dicaSenha;
private String email;
private String celular;
private String telefone;
private String telefoneServ;
private String descricao;
private String nivel;

public Funcionario(){
	
}

public int getIdFuncionario() {
	return idFuncionario;
}

public void setIdFuncionario(int idFuncionario) {
	this.idFuncionario = idFuncionario;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getSenha() {
	return senha;
}

public void setSenha(String senha) {
	this.senha = senha;
}

public String getRepsenha() {
	return repsenha;
}

public void setRepsenha(String repsenha) {
	this.repsenha = repsenha;
}

public String getDicaSenha() {
	return dicaSenha;
}

public void setDicaSenha(String dicaSenha) {
	this.dicaSenha = dicaSenha;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
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

public String getTelefoneServ() {
	return telefoneServ;
}

public void setTelefoneServ(String telefoneServ) {
	this.telefoneServ = telefoneServ;
}

public String getDescricao() {
	return descricao;
}

public void setDescricao(String descricao) {
	this.descricao = descricao;
}

public String getNivel() {
	return nivel;
}

public void setNivel(String nivel) {
	this.nivel = nivel;
}

@Override
public String toString() {
	return "Funcionario [idFuncionario=" + idFuncionario + ", nome=" + nome
			+ ", login=" + login + ", senha=" + senha + ", repsenha="
			+ repsenha + ", senhaTemp=" + dicaSenha + ", email=" + email
			+ ", celular=" + celular + ", telefone=" + telefone
			+ ", telefoneServ=" + telefoneServ + ", nivel=" + nivel + "]";
}





}
