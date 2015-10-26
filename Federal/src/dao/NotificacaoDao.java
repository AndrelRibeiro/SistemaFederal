package dao;

import java.util.List;

import beans.InformeErro;

public interface NotificacaoDao {

	boolean adicionar(InformeErro informacaoNova);

	boolean remover(InformeErro informacaoNova);

	boolean alterar(InformeErro informacaoNova);

	InformeErro buscar(InformeErro informacaoNova);

	List<InformeErro> listar(InformeErro informacaoNova);

}
