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

import beans.Cliente;
import controle.CalculoDatas;
import controle.Erro;

public class ClienteDaoImplementation implements ClienteDao, Serializable{
Erro er =new Erro();
CalculoDatas cd=new CalculoDatas();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean adicionar (Cliente cliente){
		Connection con=null;
		boolean retorno=false;
		PreparedStatement ps;
		//ID_CLIENTE,		NUM_CONTRATO,		NOME,		CPF,		RG,		NASCIMENTO,		CELULAR,		TELEFONE,		ENDERECO,		BAIRRO,		CEP,		COMPLEMENTO,		CIDADE,		ESTADO,		RELIGIAO,		PROFISSAO,		PONTO_REFE,		EMAIL,		SITUACAO,		ID_FUNCIONARIO,		OBSERVACAO,		ESTADO_CIVIL,		NATURALIDADE,		SEXO
		String sql="INSERT INTO CLIENTE(CONTRATO, NOME,	CPF, RG, NASCIMENTO, CELULAR, TELEFONE,	ENDERECO, BAIRRO, CEP, COMPLEMENTO, CIDADE,	ESTADO,	RELIGIAO, PROFISSAO, PONTO_REFE, EMAIL,	SITUACAO, ID_FUNCIONARIO, OBSERVACAO, ESTADO_CIVIL,	NATURALIDADE, SEXO)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, cliente.getNumeroContrato());
		    ps.setString(2, cliente.getNome().toUpperCase());
		    ps.setString(3, cliente.getCpf());
		    ps.setString(4, cliente.getRg());
		    ps.setDate(5,new java.sql.Date(cliente.getNascimento().getTime()));
		    ps.setString(6, cliente.getCelular());
		    ps.setString(7, cliente.getTelefone());
		    ps.setString(8, cliente.getLogradouro().toUpperCase());
		    ps.setString(9, cliente.getBairro().toUpperCase());
		    ps.setString(10, cliente.getCep());
		    ps.setString(11, cliente.getComplemento());
		    ps.setString(12, cliente.getCidade());
		    ps.setString(13, cliente.getEstado());
		    ps.setString(14, cliente.getReligiao().toUpperCase());
		    ps.setString(15, cliente.getProfissao().toUpperCase());
		    ps.setString(16, cliente.getPontoRef().toUpperCase());
		    if(cliente.getEmail()!=null){
		    	ps.setString(17, cliente.getEmail());
		    }else{
		    ps.setString(17, cliente.getEmail());}
		    ps.setString(18, null);
		    ps.setInt(19, cliente.getIdFuncionario());
		    ps.setString(20, cliente.getObservacao().toUpperCase());
		    ps.setString(21, cliente.getEstadoCivil());
		    ps.setString(22, cliente.getNaturalidade().toUpperCase());
		    ps.setString(23,cliente.getSexo().toUpperCase());
		    
		    ps.execute();
		    ps.close();
			con.close();
			retorno=true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			retorno= false;
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    System.out.println(e.getCause());
		    retorno=false;
		}
		return retorno;
	}
	public boolean adicionarCancelado (Cliente cliente){
		Connection con=null;
		boolean retorno=false;
		PreparedStatement ps;
		//ID_CLIENTE,		NUM_CONTRATO,		NOME,		CPF,		RG,		NASCIMENTO,		CELULAR,		TELEFONE,		ENDERECO,		BAIRRO,		CEP,		COMPLEMENTO,		CIDADE,		ESTADO,		RELIGIAO,		PROFISSAO,		PONTO_REFE,		EMAIL,		SITUACAO,		ID_FUNCIONARIO,		OBSERVACAO,		ESTADO_CIVIL,		NATURALIDADE,		SEXO
		String sql="INSERT INTO CLIENTE_CANCELADO(CONTRATO, NOME,	CPF, RG, NASCIMENTO, CELULAR, TELEFONE,	ENDERECO, BAIRRO, CEP, COMPLEMENTO, CIDADE,	ESTADO,	RELIGIAO, PROFISSAO, PONTO_REFE, EMAIL,	SITUACAO, ID_FUNCIONARIO, OBSERVACAO, ESTADO_CIVIL,	NATURALIDADE, SEXO,DATA_CANCELAMENTO)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, cliente.getNumeroContrato());
		    ps.setString(2, cliente.getNome().toUpperCase());
		    ps.setString(3, cliente.getCpf());
		    ps.setString(4, cliente.getRg());
		    ps.setDate(5,new java.sql.Date(cliente.getNascimento().getTime()));
		    ps.setString(6, cliente.getCelular());
		    ps.setString(7, cliente.getTelefone());
		    ps.setString(8, cliente.getLogradouro().toUpperCase());
		    ps.setString(9, cliente.getBairro().toUpperCase());
		    ps.setString(10, cliente.getCep());
		    ps.setString(11, cliente.getComplemento());
		    ps.setString(12, cliente.getCidade());
		    ps.setString(13, cliente.getEstado());
		    ps.setString(14, cliente.getReligiao().toUpperCase());
		    ps.setString(15, cliente.getProfissao().toUpperCase());
		    ps.setString(16, cliente.getPontoRef().toUpperCase());
		    if(cliente.getEmail()!=null){
		    	ps.setString(17, cliente.getEmail());
		    }else{
		    ps.setString(17, cliente.getEmail());}
		    ps.setString(18, null);
		    ps.setInt(19, cliente.getIdFuncionario());
		    ps.setString(20, cliente.getObservacao().toUpperCase());
		    ps.setString(21, cliente.getEstadoCivil());
		    ps.setString(22, cliente.getNaturalidade().toUpperCase());
		    ps.setString(23,cliente.getSexo().toUpperCase());
		    Calendar c=Calendar.getInstance();
		    ps.setDate(24, (Date) c.getTime());
		    ps.execute();
		    ps.close();
			con.close();
			retorno=true;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			retorno= false;
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    System.out.println(e.getCause());
		    retorno=false;
		}
		return retorno;
	}
	public boolean remover (Cliente cliente){
		boolean retorno=adicionarCancelado(cliente);
		if(retorno){
		PreparedStatement ps;
		Connection con = null;		
		String sql="DELETE FROM CLIENTE WHERE ID_CLIENTE=?";
		try {
			new ConnectionFactory();
			con = ConnectionFactory.getConnection();
		    ps = con.prepareStatement(sql);
			ps.setInt(1, cliente.getIdCliente());
			ps.executeUpdate();
			ps.close();
			con.close();
			retorno=true;
		}catch (Exception e) {		
			String erro=e.getStackTrace().toString();		
			er.RetornoErro(erro);
			retorno=false;
		}
		}else{
			retorno=false;
		}
		return retorno;
	}
	public boolean atualizar(Cliente cliente){
		PreparedStatement ps;
		boolean retorno;
		Connection con = null;
		//NUM_CONTRATO=?, NOME=?, CPF=?, RG=?, NASCIMENTO=?, CELULAR=?, TELEFONE=?, ENDERECO=?, BAIRRO=?, CEP=?, COMPLEMENTO=?, CIDADE=?, ESTADO=?, RELIGIAO=?, PROFISSAO=?, PONTO_REFE=?, EMAIL=?, SITUACAO=?, ID_FUNCIONARIO=?, OBSERVACAO=?, ESTADO_CIVIL=?, NATURALIDADE=?, SEXO=? WHERE ID_CLIENTE=?"
		String sql="UPDATE CLIENTE SET CONTRATO=?, NOME=?, CPF=?, RG=?, NASCIMENTO=?, CELULAR=?, TELEFONE=?, ENDERECO=?, BAIRRO=?, CEP=?, COMPLEMENTO=?, CIDADE=?, ESTADO=?, RELIGIAO=?, PROFISSAO=?, PONTO_REFE=?, EMAIL=?, SITUACAO=?, ID_FUNCIONARIO=?, OBSERVACAO=?, ESTADO_CIVIL=?, NATURALIDADE=?, SEXO=? WHERE ID_CLIENTE=?";
					
		try{
			new ConnectionFactory();
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cliente.getNumeroContrato());
		    ps.setString(2, cliente.getNome().toUpperCase());
		    ps.setString(3, cliente.getCpf());
		    ps.setString(4, cliente.getRg());
		    ps.setDate(5,new java.sql.Date(cliente.getNascimento().getTime()));
		    ps.setString(6, cliente.getCelular());
		    ps.setString(7, cliente.getTelefone());
		    ps.setString(8, cliente.getLogradouro());
		    ps.setString(9, cliente.getBairro());
		    ps.setString(10, cliente.getCep());
		    ps.setString(11, cliente.getComplemento());
		    ps.setString(12, cliente.getCidade());
		   	ps.setString(13, cliente.getEstado());
		    ps.setString(14, cliente.getReligiao());
		    ps.setString(15, cliente.getProfissao());
		    ps.setString(16, cliente.getPontoRef());
		    ps.setString(17, cliente.getEmail());
		    ps.setString(18, cliente.getSituacao());
		    ps.setInt(19, cliente.getIdFuncionario());
		    ps.setString(20, cliente.getObservacao());
		    ps.setString(21, cliente.getEstadoCivil());
		    ps.setString(22, cliente.getNaturalidade());
		    ps.setString(23, cliente.getSexo());
		    ps.setInt(24, cliente.getIdCliente());System.out.println("Cliente dao: "+cliente.toString());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch (SQLException e) {
			retorno=false;
			System.out.println(e.getMessage());
		}catch (Exception e) {
			retorno=false;
			System.out.println(e.getMessage()+e.toString()+e.getStackTrace().toString());
					}
		return retorno;
	}
	public List<Cliente> listarInativos (){
		List<Cliente> clientes=new ArrayList<Cliente>();
		String sql="SELECT * FROM CLIENTE_CANCELADO ";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		Cliente cliente=new Cliente();
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				cliente=new  Cliente();
				//"ID_CLIENTE ,NUM_CONTRATO , NOME ,CPF ,RG ,NASCIMENTO, CELULAR,TELEFONE ,ENDERECO , BAIRRO ,CEP ,COMPLEMENTO ,CIDADE ,ESTADO"
				// ",RELIGIAO , PROFISSAO ,PONTO_REFE,EMAIL,SITUACAO, DATA_CANCELAMENTO ,ID_FUNCIONARIO ,DATA_CONTRATO,OBSERVACAO,ESTADO_CIVIL,"
				// "NATURALIDADE ,SEXO "
				cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
				cliente.setNumeroContrato(rs.getInt("CONTRATO"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setRg(rs.getString("RG"));
				cliente.setNascimento(rs.getDate("NASCIMENTO"));
				cliente.setCelular(rs.getString("CELULAR"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				cliente.setLogradouro(rs.getString("ENDERECO"));
				cliente.setBairro(rs.getString("BAIRRO"));
				cliente.setCep(rs.getString("CEP"));
				cliente.setComplemento(rs.getString("COMPLEMENTO"));
				cliente.setCidade(rs.getString("CIDADE"));
				cliente.setEstado(rs.getString("ESTADO"));
				cliente.setReligiao(rs.getString("RELIGIAO"));
				cliente.setProfissao(rs.getString("PROFISSAO"));
				cliente.setPontoRef(rs.getString("PONTO_REFE"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setSituacao(rs.getString("SITUACAO"));
				cliente.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				cliente.setObservacao(rs.getString("OBSERVACAO"));
				cliente.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				cliente.setNaturalidade(rs.getString("NATURALIDADE"));
				cliente.setSexo(rs.getString("SEXO"));
				clientes.add(cliente);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){
			String erro=e.getStackTrace().toString();		
			er.RetornoErro(erro);
			e.printStackTrace();
		    }catch(Exception e) {
				String erro=e.getStackTrace().toString();		
				er.RetornoErro(erro);}
		
		return clientes;
		}
	public List<Cliente> listarAtivos(){
		List<Cliente> clientes=new ArrayList<Cliente>();
		String sql="SELECT * FROM CLIENTE ORDER BY CONTRATO";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		Cliente cliente;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				cliente=new Cliente();
				cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setNascimento(rs.getDate("NASCIMENTO"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setRg(rs.getString("RG"));
				cliente.setLogradouro(rs.getString("ENDERECO"));
				cliente.setCep(rs.getString("CEP"));
				cliente.setBairro(rs.getString("BAIRRO"));
				cliente.setComplemento(rs.getString("COMPLEMENTO"));
				cliente.setCidade(rs.getString("CIDADE"));
				cliente.setEstado(rs.getString("ESTADO"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				cliente.setCelular(rs.getString("CELULAR"));
				cliente.setReligiao(rs.getString("RELIGIAO"));
				cliente.setProfissao(rs.getString("PROFISSAO"));
				cliente.setPontoRef(rs.getString("PONTO_REFE"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setSituacao(rs.getString("SITUACAO"));
				cliente.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				cliente.setObservacao(rs.getString("OBSERVACAO"));
				cliente.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				cliente.setSexo(rs.getString("SEXO"));
				cliente.setNumeroContrato(rs.getInt("CONTRATO"));
				cliente.setNaturalidade(rs.getString("NATURALIDADE"));
				clientes.add(cliente);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){
			System.out.println(e.getStackTrace().toString());
		    }catch(Exception e) {
		    	System.out.println(e.getStackTrace().toString());
		    	}		
		return clientes;
	}
	public List<Cliente> buscarPorNome(Cliente cliente){
		List<Cliente> clientes=new ArrayList<Cliente>();
		String sql="SELECT * FROM CLIENTE WHERE NOME=?";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			rs=ps.executeQuery();
			while(rs.next()){
				cliente=new Cliente();
				cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setNascimento(rs.getDate("NASCIMENTO"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setRg(rs.getString("RG"));
				cliente.setLogradouro(rs.getString("ENDERECO"));
				cliente.setCep(rs.getString("CEP"));
				cliente.setBairro(rs.getString("BAIRRO"));
				cliente.setComplemento(rs.getString("COMPLEMENTO"));
				cliente.setCidade(rs.getString("CIDADE"));
				cliente.setEstado(rs.getString("ESTADO"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				cliente.setCelular(rs.getString("CELULAR"));
				cliente.setReligiao(rs.getString("RELIGIAO"));
				cliente.setProfissao(rs.getString("PROFISSAO"));
				cliente.setPontoRef(rs.getString("PONTO_REFE"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setSituacao(rs.getString("SITUACAO"));
				cliente.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				cliente.setObservacao(rs.getString("OBSERVACAO"));
				cliente.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				cliente.setSexo(rs.getString("SEXO"));
				cliente.setNumeroContrato(rs.getInt("CONTRATO"));
				cliente.setNaturalidade(rs.getString("NATURALIDADE"));
			clientes.add(cliente);
		}
		ps.close();
		rs.close();
		con.close();
		
		}catch(SQLException e){
			String erro=e.getStackTrace().toString();		
		er.RetornoErro(erro);
		e.printStackTrace();
	    }catch(Exception e){
			String erro=e.getStackTrace().toString();
			er.RetornoErro(erro);
		}
		return clientes;
	}
	public Cliente buscar (int contrato){
		String sql="SELECT * FROM CLIENTE WHERE CONTRATO =?";
		Cliente cliente=new Cliente();
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, contrato);
			rs=ps.executeQuery();
			if(rs.next()){
			cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
			cliente.setNome(rs.getString("NOME"));
			cliente.setNascimento(rs.getDate("NASCIMENTO"));
			cliente.setCpf(rs.getString("CPF"));
			cliente.setRg(rs.getString("RG"));
			cliente.setLogradouro(rs.getString("ENDERECO"));
			cliente.setCep(rs.getString("CEP"));
			cliente.setBairro(rs.getString("BAIRRO"));
			cliente.setComplemento(rs.getString("COMPLEMENTO"));
			cliente.setCidade(rs.getString("CIDADE"));
			cliente.setEstado(rs.getString("ESTADO"));
			cliente.setTelefone(rs.getString("TELEFONE"));
			cliente.setCelular(rs.getString("CELULAR"));
			cliente.setReligiao(rs.getString("RELIGIAO"));
			cliente.setProfissao(rs.getString("PROFISSAO"));
			cliente.setPontoRef(rs.getString("PONTO_REFE"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setSituacao(rs.getString("SITUACAO"));
			cliente.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			cliente.setObservacao(rs.getString("OBSERVACAO"));
			cliente.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
			cliente.setSexo(rs.getString("SEXO"));
			cliente.setNumeroContrato(rs.getInt("CONTRATO"));
			cliente.setNaturalidade(rs.getString("NATURALIDADE"));
			ps.close();
			rs.close();
			con.close();}
		}catch(SQLException e){
			System.out.println(e.getMessage());	
		
	    }catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	}
	    
		return cliente;
	}	
	
	public Cliente buscarPorCpf (Cliente cliente){
		String sql="SELECT * FROM CLIENTE WHERE CPF =?";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, cliente.getCpf());
			rs=ps.executeQuery();
			cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
			cliente.setNome(rs.getString("NOME"));
			cliente.setNascimento(rs.getDate("NASCIMENTO"));
			cliente.setCpf(rs.getString("CPF"));
			cliente.setRg(rs.getString("RG"));
			cliente.setLogradouro(rs.getString("ENDERECO"));
			cliente.setCep(rs.getString("CEP"));
			cliente.setBairro(rs.getString("BAIRRO"));
			cliente.setComplemento(rs.getString("COMPLEMENTO"));
			cliente.setCidade(rs.getString("CIDADE"));
			cliente.setEstado(rs.getString("ESTADO"));
			cliente.setTelefone(rs.getString("TELEFONE"));
			cliente.setCelular(rs.getString("CELULAR"));
			cliente.setReligiao(rs.getString("RELIGIAO"));
			cliente.setProfissao(rs.getString("PROFISSAO"));
			cliente.setPontoRef(rs.getString("PONTO_REFE"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setSituacao(rs.getString("SITUACAO"));
			cliente.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			cliente.setObservacao(rs.getString("OBSERVACAO"));
			cliente.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
			cliente.setSexo(rs.getString("SEXO"));
			cliente.setNumeroContrato(rs.getInt("CONTRATO"));
			cliente.setNaturalidade(rs.getString("NATURALIDADE"));
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){
			String erro=e.getStackTrace().toString();		
		er.RetornoErro(erro);
		e.printStackTrace();
	    }catch(Exception e) {
			String erro=e.getStackTrace().toString();		
			er.RetornoErro(erro);}
	    
		return cliente;
	}	
}
