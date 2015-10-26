package dao;

import java.util.List;

import beans.Cidade;
import beans.Estado;

public interface EstadoCidadeDao {
public List<Estado> buscarEstados();
public List<Cidade> buscarCidades(int id);
Estado buscarEstadoPorId(Long id);
Cidade buscarCidadePorId(Long id);
}
