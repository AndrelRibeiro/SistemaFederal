package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import controle.Confirmacao;
import beans.Cidade;
import beans.Estado;

public class EstadoCidadeDaoImplementation implements Serializable, EstadoCidadeDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Estado> buscarEstados() {
		List<Estado> estados=new ArrayList<Estado>();
		Estado estado=new Estado();
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		String sql="SELECT * FROM ESTADO;";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				estado=new Estado();
				estado.setId(rs.getInt("ID"));
				estado.setNome(rs.getString("NOME"));
				estado.setUf(rs.getString("UF"));
				estado.setPais(rs.getInt("PAIS"));
				estados.add(estado);}
			
			    rs.close();
				ps.close();
				con.close();
			
		}catch(Exception e){
			Confirmacao c=new Confirmacao();
			c.erro(null, e.getStackTrace().toString());
		}
		return estados;
	}
	@Override
	public Estado buscarEstadoPorId(Long id) {
		Estado estado=new Estado();
		PreparedStatement ps;
		Connection con=null;
		int i=Integer.parseInt(id.toString());
		ResultSet rs;
		String sql="SELECT * FROM ESTADO WHERE ID=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, i);
			rs=ps.executeQuery();
				estado.setId(rs.getInt("ID"));
				estado.setNome(rs.getString("NOME"));
				estado.setUf(rs.getString("UF"));
				estado.setPais(rs.getInt("PAIS"));			
			    rs.close();
				ps.close();
				con.close();
			
		}catch(Exception ex){
			Confirmacao c=new Confirmacao();
			c.erro(null, ex.getStackTrace().toString());
		}
		return estado;
	}
	@Override
	public Cidade buscarCidadePorId(Long id) {
		Cidade cidade=new Cidade();
		PreparedStatement ps;
		Connection con=null;
		int i=Integer.parseInt(id.toString());
		ResultSet rs;
		String sql="SELECT * FROM CIDADE WHERE ID=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, i);
			rs=ps.executeQuery();
				cidade.setId(rs.getInt("ID"));
				cidade.setNome(rs.getString("NOME"));
				cidade.setEstado(rs.getInt("ESTADO"));			
			    rs.close();
				ps.close();
				con.close();
			
		}catch(Exception e){
			Confirmacao cf=new Confirmacao();
			cf.erro(null, e.getStackTrace().toString());
		}
		return cidade;
	}
	@Override
	public List<Cidade> buscarCidades(int id) {
		List<Cidade>cidades=new ArrayList<Cidade>();
		Cidade cidade=new Cidade();
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		String sql="SELECT * FROM CIDADE  WHERE ESTADO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			while(rs.next()){
				cidade.setId(rs.getInt("ID"));
				cidade.setNome(rs.getString("NOME"));
				cidade.setEstado(rs.getInt("ESTADO"));
				cidades.add(cidade);
				cidade=new Cidade();
							}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception ex){
			Confirmacao c=new Confirmacao();
			c.erro(null, ex.getStackTrace().toString());
		}
		return cidades;
	}

}
