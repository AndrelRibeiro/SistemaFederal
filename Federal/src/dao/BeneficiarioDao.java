package dao;

import java.util.List;

import beans.Beneficiario;

public interface BeneficiarioDao {
	public boolean adicionar (Beneficiario ben);
	public boolean adicionarCancelado (Beneficiario ben);	
	public boolean remover (Beneficiario ben);
	public boolean atualizar(Beneficiario ben);
	public List<Beneficiario> listarInativos ();
	public List<Beneficiario> listarAtivos();
	public List<Beneficiario> buscarPorNome(Beneficiario ben);
	public Beneficiario buscarPorId(int idBeneficiario); 
	public List<Beneficiario> buscar (int contrato);
	public boolean registrar(Beneficiario ben);
	public boolean pesquisa(Beneficiario ben);
}
