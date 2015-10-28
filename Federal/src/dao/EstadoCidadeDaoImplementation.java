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
		String sql="SELECT * FROM UF";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				estado=new Estado();
				estado.setId(rs.getInt("UF_CODIGO"));
				estado.setNome(rs.getString("UF_DESCRICAO"));
				estado.setUf(rs.getString("UF_SIGLA"));
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
		String sql="SELECT * FROM UF WHERE UF_CODIGO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, i);
			rs=ps.executeQuery();
				estado.setId(rs.getInt("UF_CODIGO"));
				estado.setNome(rs.getString("UF_DESCRICAO"));
				estado.setUf(rs.getString("UF_SIGLA"));		
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
		String sql="SELECT * FROM CIDADE WHERE CIDADE_CODIGO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, i);
			rs=ps.executeQuery();
				cidade.setId(rs.getInt("CIDADE_CODIGO"));
				cidade.setNome(rs.getString("CIDADE_DESCRICAO"));
				cidade.setEstado(rs.getInt("UF_CODIGO"));			
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
		String sql="SELECT * FROM CIDADE  WHERE UF_CODIGO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			while(rs.next()){
				cidade.setId(rs.getInt("CIDADE_CODIGO"));
				cidade.setNome(rs.getString("CIDADE_DESCRICAO"));
				cidade.setEstado(rs.getInt("UF_CODIGO"));
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
