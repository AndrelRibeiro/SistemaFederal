package dao;

import java.util.List;

import beans.Atendimento;

public interface AtendimentoDao {
public boolean adicionar(Atendimento at);
public Atendimento buscar(Atendimento at);
public boolean excluir(Atendimento at);
public boolean alterar(Atendimento at);
public List<Atendimento> listar();
public List<Atendimento> buscarPorContrato(Atendimento at);
}
