package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.Beneficiario;
import controle.CalculoDatas;
import controle.Confirmacao;

public class BeneficiarioDaoImplementation implements Serializable,BeneficiarioDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Confirmacao conf=new Confirmacao();
	CalculoDatas comp=new CalculoDatas();
	
	public boolean adicionar (Beneficiario ben){
		Connection con=null;
		boolean retorno=false;
		PreparedStatement ps;
		String sql="INSERT INTO BENEFICIARIO(CONTRATO, NOME, NASCIMENTO,RELIGIAO,PROFISSAO,EMAIL,ATENDIDO,CADASTRO,OBSERVACAO, ESTADO_CIVIL,ID_FUNCIONARIO,SEXO,CLASSIFICA)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, ben.getContrato());System.out.println("1");
		    ps.setString(2, ben.getNome().toUpperCase());		 System.out.println("2");   
		    ps.setDate(3, new java.sql.Date( ben.getDataNascimento().getTime()));System.out.println("3");
		    ps.setString(4, ben.getReligiao());System.out.println("4");
		    ps.setString(5, ben.getProfissao());System.out.println("5");
		    ps.setString(6, ben.getEmail());System.out.println("6");
		    ps.setString(7, ben.getSituacao());System.out.println("7");
		    ps.setDate(8,new java.sql.Date( Calendar.getInstance().getTimeInMillis()));System.out.println("8");
		    ps.setString(9, ben.getObservacao());System.out.println("9");
		    ps.setString(10, ben.getEstadoCivil());System.out.println("10");
		    ps.setInt(11, ben.getIdFuncionario());System.out.println("11");
		    ps.setString(12, ben.getSexo());System.out.println("12");
		    ps.setString(13, ben.getClassificacao());System.out.println("13");System.out.println("Dao: "+ben.toString());
		    ps.execute();System.out.println("execute");
		    ps.close();
			con.close();
			retorno=true;
		}catch(SQLException e){
			System.out.println("Erro SQL:"+e.getCause());
			retorno=false;
		
		}catch (Exception e) {
			System.out.println("Erro Exception: "+e.toString()+e.getMessage()+e.getCause()+e.getStackTrace().toString());
			retorno=false;
		}
		return retorno;
	}
	public boolean adicionarCancelado (Beneficiario ben){
		Connection con=null;
		boolean retorno=false;
		PreparedStatement ps;
		String sql="INSERT INTO BENEFICIARIO_CANCELADO(CONTRATO, NOME, NASCIMENTO,RELIGIAO,PROFISSAO,EMAIL,ATENDIDO,CADASTRO,OBSERVACAO, ESTADO_CIVIL,ID_FUNCIONARIO,SEXO,CLASSIFICA)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, ben.getContrato());System.out.println("1");
		    ps.setString(2, ben.getNome().toUpperCase());		 System.out.println("2");   
		    ps.setDate(3, new java.sql.Date( ben.getDataNascimento().getTime()));System.out.println("3");
		    ps.setString(4, ben.getReligiao());System.out.println("4");
		    ps.setString(5, ben.getProfissao());System.out.println("5");
		    ps.setString(6, ben.getEmail());System.out.println("6");
		    ps.setString(7, ben.getSituacao());System.out.println("7");
		    ps.setDate(8,new java.sql.Date( Calendar.getInstance().getTimeInMillis()));System.out.println("8");
		    ps.setString(9, ben.getObservacao());System.out.println("9");
		    ps.setString(10, ben.getEstadoCivil());System.out.println("10");
		    ps.setInt(11, ben.getIdFuncionario());System.out.println("11");
		    ps.setString(12, ben.getSexo());System.out.println("12");
		    ps.setString(13, ben.getClassificacao());System.out.println("13");System.out.println("Dao: "+ben.toString());
		    ps.execute();System.out.println("execute");
		    ps.close();
			con.close();
			retorno=true;
		}catch(SQLException e){
			System.out.println("Erro SQL:"+e.getCause());
			retorno=false;
		
		}catch (Exception e) {
			System.out.println("Erro Exception: "+e.toString()+e.getMessage()+e.getCause()+e.getStackTrace().toString());
			retorno=false;
		}
		return retorno;
	}
	public boolean remover (Beneficiario ben){
		boolean retorno =adicionarCancelado(ben);
		if(retorno){
		PreparedStatement ps;
		Connection con = null;		
		String sql="DELETE FROM BENEFICIARIO WHERE ID_BENEFICIARIO=?";		
		try {
			new ConnectionFactory();
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, ben.getIdBeneficiario());
			ps.executeUpdate();
			ps.close();
			con.close();
			retorno=true;
		}catch(SQLException e){ 
			System.out.println(e.getMessage());
			retorno=false;
	}catch (Exception e) {
		    // TODO Auto-generated catch block
		   System.out.println(e.getMessage());
		    retorno=false;
		}
		}else{
			retorno=false;
		}
		return retorno;
	}
	public boolean atualizar(Beneficiario ben){
		PreparedStatement ps;
		boolean retorno;
		Connection con = null;
		String sql="UPDATE BENEFICIARIO SET CONTRATO=?, NOME=?, NASCIMENTO=?,RELIGIAO=?,PROFISSAO=?,EMAIL=?,ATENDIDO=?,CADASTRO=?,OBSERVACAO=?, ESTADO_CIVIL=?,FALECIMENTO=?,ID_FUNCIONARIO=?,SEXO=?,CLASSIFICA=? WHERE ID_BENEFICIARIO=?";
		
		try{
			new ConnectionFactory();
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, ben.getContrato());
		    ps.setString(2, ben.getNome());
		    ps.setDate(3, (Date)ben.getDataNascimento());
		    ps.setString(4, ben.getReligiao());
		    ps.setString(5, ben.getProfissao());
		    ps.setString(6, ben.getEmail());
		    ps.setString(7, ben.getSituacao());
		    if(ben.getDataCadastro()!=null){
		    ps.setDate(8,new java.sql.Date( ben.getDataCadastro().getTime()));
		    }else{
		    	ps.setDate(8, null);
		    }
		    ps.setString(9, ben.getObservacao());
		    ps.setString(10, ben.getEstadoCivil());
		    ps.setDate(11, comp.converteStringToSQL(ben.getFalecimento()));
		    ps.setInt(12, ben.getIdFuncionario());
		    ps.setString(13, ben.getSexo());
		    ps.setString(14, ben.getClassificacao());
		    ps.setInt(15, ben.getIdBeneficiario());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch(SQLException e){ 
			System.out.println(e.getMessage());
			retorno=false;
	}catch (Exception e) {
		    // TODO Auto-generated catch block
		   System.out.println(e.getMessage());
		    retorno=false;
		}
		return retorno;
	}
	public List<Beneficiario> listarInativos (){
		List<Beneficiario> bens=new ArrayList<Beneficiario>();
		String sql="SELECT * FROM BENEFICIARIO_CANCELADO ORDER BY CONTRATO";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		Beneficiario ben=new Beneficiario();
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				ben=new Beneficiario();
				
				ben.setIdBeneficiario(rs.getInt("ID_BENEFICIARIO"));
				ben.setNome(rs.getString("NOME"));
			    ben.setDataNascimento(rs.getDate("NASCIMENTO"));
				ben.setReligiao(rs.getString("RELIGIAO"));
				ben.setProfissao(rs.getString("PROFISSAO"));
				ben.setEmail(rs.getString("EMAIL"));
				ben.setSituacao(rs.getString("SITUACAO"));
				ben.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				ben.setDataCadastro(rs.getDate("DT_CADASTRO"));
				ben.setObservacao(rs.getString("OBSERVACAO"));
				ben.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				ben.setSexo(rs.getString("SEXO"));
				ben.setContrato(rs.getInt("NUM_CONTRATO"));
				ben.setClassificacao(rs.getString("CLASSIFICACAO"));
				bens.add(ben);
			}
			ps.close();
			rs.close();
			con.close();
			}catch(SQLException e){ 
				System.out.println(e.getMessage());
				
		}catch (Exception e) {
			    // TODO Auto-generated catch block
			   System.out.println(e.getMessage());
			   
			}
		
		return bens;
		}
	public List<Beneficiario> listarAtivos(){
		List<Beneficiario> bens=new ArrayList<Beneficiario>();
		String sql="SELECT * FROM BENEFICIARIO ORDER BY CONTRATO";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		Beneficiario ben=new Beneficiario();
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				    ben=new Beneficiario();
				    ben.setContrato(rs.getInt("CONTRATO"));
				    ben.setIdBeneficiario(rs.getInt("ID_BENEFICIARIO"));
				    ben.setNome(rs.getString("NOME"));
				    ben.setDataNascimento(rs.getDate("NASCIMENTO"));				    
				    ben.setReligiao(rs.getString("RELIGIAO"));
				    ben.setProfissao(rs.getString("PROFISSAO"));
				    ben.setEmail(rs.getString("EMAIL"));
				    ben.setSituacao(rs.getString("ATENDIDO"));
				    ben.setDataCadastro(rs.getDate("CADASTRO"));
				    ben.setObservacao(rs.getString("OBSERVACAO"));
				    ben.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				    ben.setDataNascimento(rs.getDate("FALECIMENTO"));	
				    ben.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				    ben.setSexo(rs.getString("SEXO"));
				    ben.setClassificacao(rs.getString("CLASSIFICA"));
				bens.add(ben);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){ 
			System.out.println(e.getMessage());
			
	}catch (Exception e) {
		    // TODO Auto-generated catch block
		   System.out.println(e.getMessage());
		   
		}
		
		return bens;
	}
	public List<Beneficiario> buscarPorNome(Beneficiario ben){
		List<Beneficiario> bens=new ArrayList<Beneficiario>();
		String sql="SELECT * FROM BENEFICIARIO WHERE NOME=?";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, ben.getNome());
			rs=ps.executeQuery();
			while(rs.next()){
				ben=new Beneficiario();
			    ben.setContrato(rs.getInt("CONTRATO"));
				 ben.setIdBeneficiario(rs.getInt("ID_BENEFICIARIO"));
				    ben.setNome(rs.getString("NOME"));
				    ben.setDataNascimento(rs.getDate("NASCIMENTO"));				    
				    ben.setReligiao(rs.getString("RELIGIAO"));
				    ben.setProfissao(rs.getString("PROFISSAO"));
				    ben.setEmail(rs.getString("EMAIL"));
				    ben.setSituacao(rs.getString("ATENDIDO"));
				    ben.setDataCadastro(rs.getDate("CADASTRO"));
				    ben.setObservacao(rs.getString("OBSERVACAO"));
				    ben.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				    ben.setDataNascimento(rs.getDate("FALECIMENTO"));	
				    ben.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				    ben.setSexo(rs.getString("SEXO"));
				    ben.setClassificacao(rs.getString("CLASSIFICA"));
				bens.add(ben);
		}
		ps.close();
		rs.close();
		con.close();
		
		}catch(SQLException e){ 
			System.out.println(e.getMessage());
			
	}catch (Exception e) {
		    // TODO Auto-generated catch block
		   System.out.println(e.getMessage());
		   
		}
		return bens;
	}
	public List<Beneficiario> buscar (int contrato){
		String sql="SELECT * FROM BENEFICIARIO WHERE CONTRATO =?";
		List<Beneficiario>bens=new ArrayList<Beneficiario>();
		Beneficiario ben=new Beneficiario();
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, contrato);
			rs=ps.executeQuery();
			while(rs.next()){
				ben=new Beneficiario();
			    ben.setContrato(rs.getInt("CONTRATO"));
				 ben.setIdBeneficiario(rs.getInt("ID_BENEFICIARIO"));
				    ben.setNome(rs.getString("NOME"));
				    ben.setDataNascimento(rs.getDate("NASCIMENTO"));				    
				    ben.setReligiao(rs.getString("RELIGIAO"));
				    ben.setProfissao(rs.getString("PROFISSAO"));
				    ben.setEmail(rs.getString("EMAIL"));
				    ben.setSituacao(rs.getString("ATENDIDO"));
				    ben.setDataCadastro(rs.getDate("CADASTRO"));
				    ben.setObservacao(rs.getString("OBSERVACAO"));
				    ben.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				    ben.setDataNascimento(rs.getDate("FALECIMENTO"));	
				    ben.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				    ben.setSexo(rs.getString("SEXO"));
				    ben.setClassificacao(rs.getString("CLASSIFICA"));
			
			bens.add(ben);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){ 
			System.out.println(e.getMessage());
	}catch (Exception e) {
		    // TODO Auto-generated catch block
		   System.out.println(e.getMessage());
		}
		return bens;
	}	
	public Beneficiario buscarPorId (int idBeneficiario){
		String sql="SELECT * FROM BENEFICIARIO WHERE ID_BENEFICIARIO =?";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		Beneficiario ben=new Beneficiario();
		try{
			
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, idBeneficiario);
			rs=ps.executeQuery();
			rs.first();			
			    ben.setContrato(rs.getInt("CONTRATO"));
				 ben.setIdBeneficiario(rs.getInt("ID_BENEFICIARIO"));
				    ben.setNome(rs.getString("NOME"));
				    ben.setDataNascimento(rs.getDate("NASCIMENTO"));			    
				    ben.setReligiao(rs.getString("RELIGIAO"));
				    ben.setProfissao(rs.getString("PROFISSAO"));
				    ben.setEmail(rs.getString("EMAIL"));
				    ben.setSituacao(rs.getString("ATENDIDO"));
				    ben.setDataCadastro(rs.getDate("CADASTRO"));
				    ben.setObservacao(rs.getString("OBSERVACAO"));
				    ben.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				    ben.setDataNascimento(rs.getDate("FALECIMENTO"));		
		            ben.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				    ben.setSexo(rs.getString("SEXO"));
				    ben.setClassificacao(rs.getString("CLASSIFICA"));
			
			
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){ 
			System.out.println(e.getMessage());
	}catch (Exception e) {
		    // TODO Auto-generated catch block
		   System.out.println(e.getMessage());
		}
	    
		return ben;
	}

	@Override
	public boolean registrar(Beneficiario ben) {
		PreparedStatement ps;
		boolean retorno;
		Connection con = null;
		String sql="UPDATE BENEFICIARIO SET FALECIMENTO=? SITUACAO=? WHERE ID_BENEFICIARIO=?";
					
		try{
			new ConnectionFactory();
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
		    java.sql.Date data2 = comp.converteStringToSQL(ben.getFalecimento());
		    ps.setDate(1, data2);
		    ps.setString(2, ben.getSituacao());
		    ps.setInt(3, ben.getIdBeneficiario());
			ps.execute(sql);
			ps.close();
			con.close();
			retorno=true;
		}catch(SQLException e){ 
			System.out.println(e.getMessage());
			retorno=false;
	}catch (Exception e) {
		    // TODO Auto-generated catch block
		   System.out.println(e.getMessage());
		    retorno=false;
		}
		return retorno;
	}

	@Override
	public boolean pesquisa(Beneficiario ben) {
		List<Beneficiario> beneficiarios=new ArrayList<Beneficiario>();
		beneficiarios=buscar(ben.getContrato());
		boolean retorno=false;
		for(Beneficiario b:beneficiarios){
			if(b.getNome().equalsIgnoreCase(ben.getNome())&&b.getContrato()==ben.getContrato()){
				retorno=true;
			}
		}
		return retorno;
	}	
	
}
