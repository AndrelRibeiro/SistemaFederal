package dao;

import java.util.List;

import beans.Contrato;


public interface ContratoDao {
	public boolean adicionar(Contrato c);
	public boolean excluir(Contrato c);
	public Contrato buscar(int contrato);
	public boolean alterar(Contrato c);
	public List<Contrato> listar();
	public List<Contrato> listar(Contrato c);
}
