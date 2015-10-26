package dao;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controle.Confirmacao;
import controle.CriptoUtils;
import beans.Funcionario;

public class FuncionarioDaoImplementation implements FuncionarioDao, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Confirmacao c=new Confirmacao();
	@Override
	public boolean adicionar(Funcionario f) {
		Connection con=null;
		boolean retorno;
		String criptografada;
		PreparedStatement ps;
		String sql="INSERT INTO FUNCIONARIO (NOME,LOGIN,SENHA,REPSENHA,DICA_SENHA,EMAIL,CELULAR,TELEFONE,NIVEL)VALUES(?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setString(1, f.getNome());
		    ps.setString(2, f.getLogin());
		    try {  
			    byte[] b = CriptoUtils.digest(f.getSenha().getBytes(), "md5");  
				criptografada = CriptoUtils.byteArrayToHexString(b); 
			} catch (NoSuchAlgorithmException e) {  
			    e.printStackTrace();  
			    return false;  
			}
		    ps.setString(3, criptografada);
		    ps.setString(4, criptografada);
		    ps.setString(5,f.getDicaSenha());
		    ps.setString(6, f.getEmail());
		    ps.setString(7,f.getCelular());
		    ps.setString(8,f.getTelefone());
		    ps.setString(9,f.getNivel());
		    ps.execute();
		    ps.close();
			con.close();
			retorno=true;
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    c.erro(null, e.getMessage());
		    retorno=false;
		}
		return retorno;
		
	}

	@Override
	public boolean excluir(Funcionario f) {
		Connection con=null;
		PreparedStatement ps;
		String sql="DELETE FROM FUNCIONARIO WHERE ID_FUNCIONARIO=?";
		boolean retorno=false;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, f.getIdFuncionario());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch(Exception e){
			c.erro(null, e.getMessage());
		}
		return retorno;
	}

	@Override
	public Funcionario buscar(Funcionario f) {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT * FROM FUNCIONARIO WHERE ID_FUNCIONARIO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, f.getIdFuncionario());
			rs=ps.executeQuery();
			while(rs.next()){
				f.setNome(rs.getString("NOME"));
				f.setLogin(rs.getString("LOGIN"));
				f.setSenha(rs.getString("SENHA"));
				f.setRepsenha(rs.getString("REPSENHA"));
				f.setDicaSenha(rs.getString("DICA_SENHA"));
				f.setEmail(rs.getString("EMAIL"));
				f.setCelular(rs.getString("CELULAR"));
				f.setTelefone(rs.getString("TELEFONE"));
				f.setNivel(rs.getString("NIVEL"));
				if(f.getNivel().equalsIgnoreCase("1")){
					f.setDescricao("Administrador");
				}else if(f.getNivel().equalsIgnoreCase("2")){
					f.setDescricao("Vendedor");
				}else if(f.getNivel().equalsIgnoreCase("3")){
					f.setDescricao("Operador");
				}else{
					f.setDescricao("Convidado");
				}
				f.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));}
				ps.close();
				rs.close();
				con.close();
		}catch(Exception e){
			c.erro(null, e.getMessage());
		}
		return f;
	}

	@Override
	public boolean alterar(Funcionario f) {
		Connection con=null;
		boolean retorno;
		PreparedStatement ps;
		String sql="UPDATE FUNCIONARIO SET NOME=?,LOGIN=?,SENHA=?,REPSENHA=?,DICA_SENHA=?,EMAIL=?,CELULAR=?,TELEFONE=?,NIVEL=? WHERE ID_FUNCIONARIO=?;";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setString(1, f.getNome());
		    ps.setString(2, f.getLogin());
		    ps.setString(3, f.getSenha());
		    ps.setString(4, f.getRepsenha());
		    ps.setString(5,f.getDicaSenha());
		    ps.setString(6, f.getEmail());
		    ps.setString(7,f.getCelular());
		    ps.setString(8,f.getTelefone());
		    ps.setString(9,f.getNivel());
		    ps.setInt(10, f.getIdFuncionario());
		    ps.execute();
		    ps.close();
			con.close();
			retorno=true;
		} catch (Exception e) {
			c.erro(null, e.getMessage());
		    retorno=false;
		}
		return retorno;
		
	}

	@Override
	public List <Funcionario> listar() {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		List<Funcionario>funcionarios=new ArrayList<Funcionario>();
		Funcionario f;
		String sql="SELECT * FROM FUNCIONARIO";
		try{
			new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				f=new Funcionario();
				f.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				f.setNome(rs.getString("NOME"));
				f.setLogin(rs.getString("LOGIN"));				
				f.setEmail(rs.getString("EMAIL"));
				f.setCelular(rs.getString("CELULAR"));
				f.setTelefone(rs.getString("TELEFONE"));
				f.setNivel(rs.getString("NIVEL"));
				if(f.getNivel()==null){
					f.setDescricao("Indisponível");
				}else{
				if(f.getNivel().equalsIgnoreCase("1")){
					f.setDescricao("Administrador");
				}else if(f.getNivel().equalsIgnoreCase("2")){
					f.setDescricao("Vendedor");
				}else if(f.getNivel().equalsIgnoreCase("3")){
					f.setDescricao("Operador");
				}else if(f.getNivel().equalsIgnoreCase("4")){
					f.setDescricao("Convidado");
				}}
				funcionarios.add(f);				
			}
			rs.close();
			ps.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();System.out.println(e.getMessage());
			
		}
		return funcionarios;
	}

	@Override
	public boolean validar(Funcionario f) {
		Connection con=null;
		String senhaCriptografada,valida;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT * FROM FUNCIONARIO WHERE LOGIN=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, f.getLogin());
			rs=ps.executeQuery();
				f.setLogin(rs.getString("LOGIN"));
				f.setSenha(rs.getString("SENHA"));
				f.setNivel(rs.getString("NIVEL"));
				ps.close();
				rs.close();
				con.close();
		}catch(Exception e){
			c.erro(null, e.getMessage());
		}
		try {  
			valida=f.getSenha();
		    byte[] b = CriptoUtils.digest(f.getSenha().getBytes(), "md5");  
			senhaCriptografada = CriptoUtils.byteArrayToHexString(b); 
		} catch (NoSuchAlgorithmException e) {  
			c.erro(null, e.getMessage());
		    return false;  
		}  
		   
		  
		if (valida.equalsIgnoreCase(senhaCriptografada )) {  
		    return true;  
		} else {  
		    return false;  
		} 
	}
}
