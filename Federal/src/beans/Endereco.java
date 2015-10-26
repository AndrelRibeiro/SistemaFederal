package beans;

import java.io.Serializable;

import dao.CepDao;
import dao.CepDaoImplementation;


public class Endereco implements Serializable{
	public static void main(String[]args){
		CepDao cd=new CepDaoImplementation();
		Endereco e=new Endereco();
		e=cd.buscar("08060220");
		System.out.println(e.toString());
	}	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
private String cidade;
private String estado;
private String bairro;
private String logradouro;
private String cep;
public Endereco(){
	
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
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
public String getBairro() {
	return bairro;
}
public void setBairro(String bairro) {
	this.bairro = bairro;
}
public String getLogradouro() {
	return logradouro;
}
public void setLogradouro(String logradouro) {
	this.logradouro = logradouro;
}
public String getCep() {
	return cep;
}
public void setCep(String cep) {
	this.cep = cep;
}

@Override
public String toString() {
	return "Endereco [id=" + id + ", cidade=" + cidade + ", estado=" + estado
			+ ", bairro=" + bairro + ", logradouro=" + logradouro + ", cep="
			+ cep + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
	result = prime * result + ((cep == null) ? 0 : cep.hashCode());
	result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
	result = prime * result + ((estado == null) ? 0 : estado.hashCode());
	result = prime * result + id;
	result = prime * result
			+ ((logradouro == null) ? 0 : logradouro.hashCode());
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
	Endereco other = (Endereco) obj;
	if (bairro == null) {
		if (other.bairro != null)
			return false;
	} else if (!bairro.equals(other.bairro))
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
	if (estado == null) {
		if (other.estado != null)
			return false;
	} else if (!estado.equals(other.estado))
		return false;
	if (id != other.id)
		return false;
	if (logradouro == null) {
		if (other.logradouro != null)
			return false;
	} else if (!logradouro.equals(other.logradouro))
		return false;
	return true;
}

}
