package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class NossoNumeroDaoImplementation implements NossoNumeroDao, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public boolean adicionar(int nossoNumero) { 
		Connection con=null;
		boolean retorno;
		PreparedStatement ps;
		String sql="INSERT INTO NOSSO_NUMERO (NOSSO_N)VALUES(?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, nossoNumero);		    
		    ps.execute();
		    ps.close();
			con.close();
			retorno=true;
		} catch (Exception e) {
		   
		    e.printStackTrace();
		    retorno=false;
		}
		return retorno;
	}
	
	@Override
	public boolean excluir(int nossoNumero) {
		Connection con=null;
		PreparedStatement ps;
		String sql="DELETE FROM NOSSO_NUMERO WHERE NOSSO_N=?";
		boolean retorno=false;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, nossoNumero);
			ps.executeUpdate();
			ps.close();
			con.close();			
		}catch(Exception e){
			System.out.println(e.getMessage());
			retorno=false;
		}
		return retorno;
	}

	@Override
	public boolean alterar(int nosso_numero) {
		Connection con=null;
		boolean retorno;
		PreparedStatement ps;
		String sql="UPDATE NOSSO_NUMERO SET NOSSO_N=? WHERE ID_NN=?";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, nosso_numero);
		    ps.setInt(2, 1);
		    ps.executeUpdate();
		    ps.close();
			con.close();
			retorno=true;
		} catch (Exception e) {
		  
		    e.printStackTrace();
		    retorno=false;
		}
		return retorno;
		
	}
	@Override
	public int buscar() {
int nossoNumero = 0;
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT NOSSO_N FROM NOSSO_NUMERO WHERE ID_NN=1";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
			nossoNumero=rs.getInt("NOSSO_N");
			}
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return nossoNumero;
	}
	

}
