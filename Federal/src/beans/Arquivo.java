package beans;

import java.io.Serializable;

public class Arquivo implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String nome;
private String caminho;
private String[] conteudo;
public Arquivo(){
	
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCaminho() {
	return caminho;
}
public void setCaminho(String caminho) {
	this.caminho = caminho;
}
public void setConteudo(String[] conteudo) {
	this.conteudo=conteudo;
	}
public String[] getConteudo(){
	return conteudo;
}

}
