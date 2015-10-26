package beans;

import java.io.Serializable;

public class Estado implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -8501479888244372788L;
private int id;
private String nome;
private String uf;
private int pais;

public Estado() {
	
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + pais;
	result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
	Estado other = (Estado) obj;
	if (id != other.id)
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (pais != other.pais)
		return false;
	if (uf == null) {
		if (other.uf != null)
			return false;
	} else if (!uf.equals(other.uf))
		return false;
	return true;
}

public Estado(int id, String nome, String uf, int pais) {
	super();
	this.id = id;
	this.nome = nome;
	this.uf = uf;
	this.pais = pais;
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
public String getUf() {
	return uf;
}
public void setUf(String uf) {
	this.uf = uf;
}
public int getPais() {
	return pais;
}
public void setPais(int pais) {
	this.pais = pais;
}
@Override
public String toString() {
	return "Estado [id=" + id + ", nome=" + nome + ", uf=" + uf + ", pais="
			+ pais + "]";
}

}
