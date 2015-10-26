package dao;

import java.util.List;

import beans.Cliente;

public interface ClienteDao {
public boolean adicionar(Cliente cliente);
public boolean adicionarCancelado(Cliente cliente);
public boolean atualizar(Cliente cliente);
public boolean remover(Cliente cliente);
public Cliente buscar(int contrato);
public List<Cliente> buscarPorNome(Cliente cliente);
public List<Cliente> listarInativos();
public List<Cliente> listarAtivos();
public Cliente buscarPorCpf (Cliente cliente);
}
