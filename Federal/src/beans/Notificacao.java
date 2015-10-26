package beans;

import java.io.Serializable;

public class Notificacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String tipo;
	private String titulo;
	private String descricao;
	private int quantidade;
	
	public Notificacao(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Informacao [id=" + id + ", tipo=" + tipo + ", titulo=" + titulo
				+ ", descricao=" + descricao + ", quantidade=" + quantidade
				+ "]";
	}
	

}
