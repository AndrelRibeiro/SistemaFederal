package beans;

import java.io.Serializable;

public class Cidade implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int id;
private String nome;
private int estado;
public Cidade(){
	
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public int getEstado() {
	return estado;
}
public void setEstado(int estado) {
	this.estado = estado;
}
@Override
public String toString() {
	return "Cidade [id=" + id + ", nome=" + nome + ", estado=" + estado + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + estado;
	result = prime * result + id;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
	Cidade other = (Cidade) obj;
	if (estado != other.estado)
		return false;
	if (id != other.id)
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	return true;
}

}
