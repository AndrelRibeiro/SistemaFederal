package dao;

import java.util.List;

import beans.Funcionario;

public interface FuncionarioDao {
public boolean adicionar(Funcionario f);
public boolean excluir(Funcionario f);
public Funcionario buscar(Funcionario f);
public boolean alterar(Funcionario f);
public List<Funcionario> listar();
public boolean validar(Funcionario f);
}
