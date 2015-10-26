package dao;

import java.util.List;

import beans.Vendedor;

public interface VendedorDao {
public boolean adicionar(Vendedor vendedor);
public boolean remover(Vendedor vendedor);
public boolean alterar(Vendedor vendedor);
public Vendedor buscar(Vendedor vendedor);
public List<Vendedor> listar();
}
