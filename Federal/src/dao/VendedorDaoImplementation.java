package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Vendedor;

public class VendedorDaoImplementation implements VendedorDao,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean adicionar(Vendedor vendedor) {
		//NOME EMAIL, CELULAR, TELEFONE, CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, ESTADO, CIDADE.
		Connection con=null;
		boolean retorno;
		PreparedStatement ps;
		String sql="INSERT INTO VENDEDOR(NOME,EMAIL,CELULAR,TELEFONE,CEP, LOGRADOURO,COMPLEMENTO,BAIRRO,ESTADO,CIDADE)VALUES(?,?,?,?,?,?,?,?,?,?)";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, vendedor.getNome().toUpperCase());
			ps.setString(2, vendedor.getEmail());
			ps.setString(3, vendedor.getCelular());
			ps.setString(4, vendedor.getTelefone());
			ps.setString(5, vendedor.getCep());
			ps.setString(6, vendedor.getLogradouro().toUpperCase());
			ps.setString(7, vendedor.getComplemento().toUpperCase());
			ps.setString(8, vendedor.getBairro().toUpperCase());
			ps.setString(9, vendedor.getEstado().toUpperCase());
			ps.setString(10, vendedor.getCidade().toUpperCase());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			retorno=false;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			retorno=false;
		}
		return retorno;
	}

	@Override
	public boolean remover(Vendedor vendedor) {
		PreparedStatement ps;
		Connection con = null;
		boolean retorno;
		String sql="DELETE FROM VENDEDOR WHERE ID_VENDEDOR=?";
		try {
			new ConnectionFactory();
			con = ConnectionFactory.getConnection();
		    ps = con.prepareStatement(sql);
			ps.setInt(1, vendedor.getIdVendedor());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch (Exception e) {		
			System.out.println(e.getMessage());		
			retorno=false;
		}
		return retorno;
	}

	@Override
	public boolean alterar(Vendedor vendedor) {
		//NOME EMAIL, CELULAR, TELEFONE, CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, ESTADO, CIDADE.
				Connection con=null;
				boolean retorno;
				PreparedStatement ps;
				String sql="UPDATE VENDEDOR SET NOME=? ,EMAIL=? ,CELULAR=? ,TELEFONE=? ,CEP=? , LOGRADOURO=? ,COMPLEMENTO=? ,BAIRRO=? ,ESTADO=? ,CIDADE=? WHERE ID_VENDEDOR=?";
				try{
					new ConnectionFactory();
					con=ConnectionFactory.getConnection();
					ps=con.prepareStatement(sql);
					ps.setString(1, vendedor.getNome().toUpperCase());
					ps.setString(2, vendedor.getEmail());
					ps.setString(3, vendedor.getCelular());
					ps.setString(4, vendedor.getTelefone());
					ps.setString(5, vendedor.getCep());
					ps.setString(6, vendedor.getLogradouro().toUpperCase());
					ps.setString(7, vendedor.getComplemento().toUpperCase());
					ps.setString(8, vendedor.getBairro().toUpperCase());
					ps.setString(9, vendedor.getEstado().toUpperCase());
					ps.setString(10, vendedor.getCidade().toUpperCase());
					ps.setInt(11, vendedor.getIdVendedor());
					ps.execute();
					ps.close();
					con.close();
					retorno=true;
				}catch(SQLException e){
					System.out.println(e.getMessage());
					retorno=false;
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
					retorno=false;
				}
				return retorno;
	}

	@Override
	public Vendedor buscar(Vendedor vendedor) {
		//NOME EMAIL, CELULAR, TELEFONE, CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, ESTADO, CIDADE.
				Connection con=null;
				PreparedStatement ps;
				ResultSet rs;
				String sql="SELECT * FROM VENDEDOR";
				try{
					new ConnectionFactory();
					con=ConnectionFactory.getConnection();
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					    vendedor.setIdVendedor(rs.getInt("ID_VENDEDOR"));
						vendedor.setNome(rs.getString("NOME"));
						vendedor.setEmail(rs.getString("EMAIL"));
						vendedor.setCelular(rs.getString("CELULAR"));
						vendedor.setTelefone(rs.getString("TELEFONE"));
						vendedor.setCep(rs.getString("CEP"));
						vendedor.setLogradouro(rs.getString("LOGRADOURO"));
						vendedor.setComplemento(rs.getString("COMPLEMENTO"));
						vendedor.setBairro(rs.getString("BAIRRO"));
						vendedor.setEstado(rs.getString("ESTADO"));
						vendedor.setCidade(rs.getString("CIDADE"));
					ps.close();
					rs.close();
					con.close();
				}catch(SQLException e){
					e.getStackTrace();
					System.out.println(e.getMessage());
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}	
				return vendedor;
	}

	@Override
	public List<Vendedor> listar() {
		//NOME EMAIL, CELULAR, TELEFONE, CEP, LOGRADOURO, COMPLEMENTO, BAIRRO, ESTADO, CIDADE.
		Vendedor vendedor;
		List<Vendedor>vendedores=new ArrayList<Vendedor>();
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT * FROM VENDEDOR";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				vendedor=new Vendedor();
				vendedor.setIdVendedor(rs.getInt("ID_VENDEDOR"));
				vendedor.setNome(rs.getString("NOME"));
				vendedor.setEmail(rs.getString("EMAIL"));
				vendedor.setCelular(rs.getString("CELULAR"));
				vendedor.setTelefone(rs.getString("TELEFONE"));
				vendedor.setCep(rs.getString("CEP"));
				vendedor.setLogradouro(rs.getString("LOGRADOURO"));
				vendedor.setComplemento(rs.getString("COMPLEMENTO"));
				vendedor.setBairro(rs.getString("BAIRRO"));
				vendedor.setEstado(rs.getString("ESTADO"));
				vendedor.setCidade(rs.getString("CIDADE"));
				vendedores.add(vendedor);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){
			e.getStackTrace();
			System.out.println(e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}	
		return vendedores;
	}

}
