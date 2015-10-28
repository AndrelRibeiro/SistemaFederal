package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Atendimento;

public class AtendimentoDaoImplementation implements AtendimentoDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean adicionar(Atendimento at) {
		PreparedStatement ps;int i=0;
		Connection con=null;
		boolean retorno = false;
		String sql="INSERT INTO ATENDIMENTO(ID_CONTRATO,ID_BENEFICIARIO,FALECIMENTO,AGENCIA,DATA_ATENDIMENTO,NUM_NOTA,RESSARCIMENTO,OBSERVACAO,ID_FUNCIONARIO)VALUES(?,?,?,?,?,?,?,?,?);";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);System.out.println("-"+i++);
			ps.setInt(1, at.getContrato());System.out.println("-"+i++);
			ps.setInt(2, at.getIdBeneficiario());System.out.println("-"+i++);
			ps.setString(3, at.getAgencia());System.out.println("-"+i++);
			ps.setDate(4, new java.sql.Date(at.getFalecimento().getTime()));System.out.println("-"+i++);
			ps.setDate(5, new java.sql.Date(at.getData_atendimento().getTime()));System.out.println("-"+i++);
			ps.setInt(6, at.getNumNota());System.out.println("-"+i++);
			ps.setDouble(7, at.getRessarcimento());System.out.println("-"+i++);
			ps.setString(8, at.getObservacao());System.out.println("-"+i++);
			ps.setInt(9, at.getIdFuncionario());System.out.println("-"+i++);
			ps.execute();System.out.println("-"+i++);
			ps.close();
			con.close();
			retorno=true;
		}catch(Exception e){
			System.out.println(e.toString());
			retorno=false;
		}
		return retorno;
	}

	@Override
	public Atendimento buscar(Atendimento at) {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT * FROM ATENDIMENTO WHERE ID_ATENDIMENTO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, at.getIdAtendimento());
			rs=ps.executeQuery();
				at.setContrato(rs.getInt("ID_CONTRATO"));
				at.setIdAtendimento(rs.getInt("ID_ATENDIMENTO"));
				at.setIdBeneficiario(rs.getInt("ID_BENEFICIARIO"));
				at.setAgencia(rs.getString("AGENCIA"));
				at.setFalecimento(rs.getDate("FALECIMENTO"));
				at.setData_atendimento(rs.getDate("DATA_ATENDIMENTO"));
				at.setNumNota(rs.getInt("NUM_NOTA"));
				at.setRessarcimento(rs.getDouble("RESSARCIMENTO"));
				at.setObservacao(rs.getString("OBSERVACAO"));
				at.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				rs.close();
				ps.close();
				con.close();	
		}
		catch(Exception e){
			
		}
		return at;
	}
	@Override
	public List<Atendimento> buscarPorContrato(Atendimento at) {
		List<Atendimento>atendimentos=new ArrayList<Atendimento>();
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT * FROM ATENDIMENTO WHERE ID_CONTRATO=? ORDER BY DATA_ATENDIMENTO";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, at.getContrato());
			rs=ps.executeQuery();
			while(rs.next()){
				at=new Atendimento();
				at.setContrato(rs.getInt("ID_CONTRATO"));
				at.setIdAtendimento(rs.getInt("ID_ATENDIMENTO"));
				at.setIdBeneficiario(rs.getInt("ID_BENEFICIARIO"));
				at.setAgencia(rs.getString("AGENCIA"));
				at.setFalecimento(rs.getDate("FALECIMENTO"));
				at.setData_atendimento(rs.getDate("DATA_ATENDIMENTO"));
				at.setNumNota(rs.getInt("NUM_NOTA"));
				at.setRessarcimento(rs.getDouble("RESSARCIMENTO"));
				at.setObservacao(rs.getString("OBSERVACAO"));
				at.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				atendimentos.add(at);
			}
			rs.close();
			ps.close();
			con.close();
			
		}catch(Exception e){
			
		}
		return atendimentos;
	}

	@Override
	public boolean excluir(Atendimento at) {
		Connection con=null;
		PreparedStatement ps;
		String sql="DELETE FROM ATENDIMENTO WHERE ID_ATENDIMENTO=?";
		boolean retorno=false;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, at.getIdAtendimento());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch(Exception e){
			
		}
		return retorno;
		
	}

	@Override
	public boolean alterar(Atendimento at) {
		PreparedStatement ps;
		Connection con=null;
		boolean retorno = false;
		String sql="UPDATE ATENDIMENTO SET ID_CONTRATO=? , ID_BENEFICIARIO=? , ID_AGENCIA=? , DATA_ATENDIMENTO=? , NUM_NOTA=?,RESSARCIMENTO=?, OBSERVACAO=?,ID_FUNCIONARIO=? WHERE ID_ATENDIMENTO=?;";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, at.getContrato());
			ps.setInt(2, at.getIdBeneficiario());
			ps.setString(3, at.getAgencia());
			ps.setDate(4, new java.sql.Date(at.getFalecimento().getTime()));
			ps.setDate(4, new java.sql.Date(at.getData_atendimento().getTime()));
			ps.setInt(5, at.getNumNota());
			ps.setDouble(6, at.getRessarcimento());
			ps.setInt(7, at.getIdFuncionario());
			ps.setString(8, at.getObservacao());
			ps.setInt(9, at.getIdAtendimento());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch(Exception e){
			retorno=false;
		}
		return retorno;
		
	}

	@Override
	public List<Atendimento> listar() {
		List<Atendimento>atendimentos=new ArrayList<Atendimento>();
		Atendimento at=new Atendimento();
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT * FROM ATENDIMENTO ORDER BY ID_CONTRATO";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, at.getIdAtendimento());
			rs=ps.executeQuery();
			while(rs.next()){
				at=new Atendimento();
				at.setContrato(rs.getInt("ID_CONTRATO"));
				at.setIdAtendimento(rs.getInt("ID_ATENDIMENTO"));
				at.setIdBeneficiario(rs.getInt("ID_BENEFICIARIO"));
				at.setAgencia(rs.getString("AGENCIA"));
				at.setFalecimento(rs.getDate("FALECIMENTO"));
				at.setData_atendimento(rs.getDate("DATA_ATENDIMENTO"));
				at.setNumNota(rs.getInt("NUM_NOTA"));
				at.setRessarcimento(rs.getDouble("RESSARCIMENTO"));
				at.setObservacao(rs.getString("OBSERVACAO"));
				at.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				atendimentos.add(at);
			}
			rs.close();
			ps.close();
			con.close();
			
		}catch(Exception e){
			
		}
		return atendimentos;
	}

}
