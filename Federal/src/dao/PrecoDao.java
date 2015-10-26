package dao;

import java.util.List;

import beans.Preco;

public interface PrecoDao {
public boolean adicionar(Preco preco);
public boolean excluir(Preco preco);
public boolean atualizar(Preco preco);
public Preco buscar(Preco preco);
public List<Preco> listar();
}
