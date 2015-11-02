package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Mensalidade;
import beans.Registro;
import controle.CalculoDatas;

public class MensalidadeDaoImplementation implements MensalidadeDao, Serializable{
CalculoDatas cp=new CalculoDatas();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean adicionar(Mensalidade m) {
		Connection con=null;
		boolean retorno;
		PreparedStatement ps;
		String sql="INSERT INTO MENSALIDADE (CONTRATO, PARCELA, VENCIMENTO, VALOR, SITUACAO, NOSSO_NUMERO, DAC_NOSSO_NUMERO, COD_BARRAS, PARCELA_CARNE_ANO, CARENCIA, PERIODICIDADE, ID_FUNCIONARIO)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, m.getContrato());
		    ps.setInt(2, m.getNumParcela());
		    ps.setDate(3,new java.sql.Date( m.getDataVencimento().getTime()));
		    ps.setDouble(4, m.getValorParcela());
		    ps.setString(5, m.getSituacao());
		    ps.setString(6, "0");
		    ps.setString(7, m.getDacNossoNumero());
		    ps.setString(8, m.getCodBarras());
		    ps.setLong(9, m.getParcela_carne_ano());
		    ps.setDate(10,null);
		    ps.setString(11, m.getPeriodicidade());
		    ps.setInt(12, m.getIdFuncionario());
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
	public boolean adicionarPagas(Mensalidade m) {
		Connection con=null;
		boolean retorno;
		PreparedStatement ps;
		String sql="INSERT INTO MENSALIDADE_PAGA (CONTRATO, PARCELA, VENCIMENTO, VALOR, SITUACAO, NOSSO_NUMERO, DAC_NOSSO_NUMERO, COD_BARRAS, PAGAMENTO, PARCELA_CARNE_ANO, CARENCIA, PERIODICIDADE, ID_FUNCIONARIO)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, m.getContrato());
		    ps.setInt(2, m.getNumParcela());
		    ps.setDate(3,new java.sql.Date( m.getDataVencimento().getTime()));
		    ps.setDouble(4, m.getValorParcela());
		    ps.setString(5, m.getSituacao());
		    ps.setString(6, m.getNossoNumero());
		    ps.setString(7, m.getDacNossoNumero());
		    ps.setDate(8, new java.sql.Date(m.getDataPagamento().getTime()));
		    ps.setString(9, m.getCodBarras());
		    ps.setLong(10, m.getParcela_carne_ano());
		    if(m.getDataCarencia()!=null){
		    	ps.setDate(11, new java.sql.Date(m.getDataCarencia().getTime()));
		    }else{
		    ps.setDate(11, null);
		    }
		    ps.setString(12, m.getPeriodicidade());
		    ps.setInt(13, m.getIdFuncionario());
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
	public boolean excluir(Mensalidade m) {
		Connection con=null;
		PreparedStatement ps;
		String sql="DELETE FROM MENSALIDADE WHERE ID_MENSALIDADE=?";System.out.println("Exclusão DAO:"+m.toString());
		boolean retorno=false;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, m.getIdMensalidade());
			ps.executeUpdate();
			ps.close();
			con.close();
			m=buscar(m.getNossoNumero());
			if(m.getNossoNumero()==null){
				retorno=true;	
			}else{
				retorno=false;
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			retorno=false;
		}
		return retorno;
	}

	@Override
	public boolean alterar(Mensalidade m) {
		Connection con=null;
		boolean retorno;
		PreparedStatement ps;
		String sql="UPDATE MENSALIDADE SET CONTRATO=?, PARCELA=?, VENCIMENTO=?, VALOR=?, SITUACAO=?, NOSSO_NUMERO=?, DAC_NOSSO_NUMERO=?, COD_BARRAS=?, PAGAMENTO=?, PARCELA_CARNE_ANO=?, CARENCIA=?,PERIODICIDADE=?, ID_FUNCIONARIO=? WHERE ID_MENSALIDADE=?";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();System.out.println(m.toString());
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, m.getContrato());
		    ps.setInt(2, m.getNumParcela());
		    if(m.getDataVencimento()==null){
		    	ps.setDate(3, null);
		    }else{
		    ps.setDate(3,new java.sql.Date( m.getDataVencimento().getTime()));}
		    ps.setDouble(4, m.getValorParcela());
		    ps.setString(5, m.getSituacao());
		    ps.setString(6, m.getNossoNumero());
		    ps.setString(7, m.getDacNossoNumero());
		    ps.setString(8, m.getCodBarras());
		    if(m.getDataPagamento()==null){
		    	ps.setDate(9, null);
		    }else{
		    ps.setDate(9, new java.sql.Date(m.getDataPagamento().getTime()));}
		    ps.setLong(10, m.getParcela_carne_ano());
		    if(m.getDataCarencia()==null){
		    	ps.setDate(11, null);
		    }else{
		    ps.setDate(11, new java.sql.Date(m.getDataCarencia().getTime()));}
		    ps.setString(12, m.getPeriodicidade());
		    ps.setInt(13, m.getIdFuncionario());
		    ps.setInt(14, m.getIdMensalidade());
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
	public Mensalidade buscar(Mensalidade m) {

		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		//ID_MENSALIDADE, CONTRATO, PARCELA, VENCIMENTO, VALOR, SITUACAO, DOCUMENTO, CNR, PAGAMENTO, PARCELA_CARNE_ANO, CARENCIA, PERIODICIDADE, ID_FUNCIONARIO
		String sql="SELECT * FROM MENSALIDADE WHERE ID_MENSALIDADE=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, m.getIdMensalidade());
			rs=ps.executeQuery();
			m=new Mensalidade();
			m.setContrato(rs.getInt("CONTRATO"));
			m.setNumParcela(rs.getInt("PARCELA"));
			m.setDataVencimento(rs.getDate("VENCIMENTO"));
			m.setValorParcela(rs.getDouble("VALOR"));
			m.setSituacao(rs.getString("SITUACAO"));
			m.setNossoNumero(rs.getString("NOSSO_NUMERO"));
			m.setDacNossoNumero(rs.getString("DAC_NOSSO_NUMERO"));
			m.setCodBarras(rs.getString("COD_BARRAS"));
			m.setDataPagamento(rs.getDate("PAGAMENTO"));
			m.setParcela_carne_ano(rs.getLong("PARCELA_CARNE_ANO"));
			m.setDataCarencia(rs.getDate("CARENCIA"));
			m.setPeriodicidade(rs.getString("PERIODICIDADE"));
			m.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			m.setIdMensalidade(rs.getInt("ID_MENSALIDADE"));
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return m;
	}
	
	@Override	
    public List<Mensalidade> buscar(Date inicio,Date fim, String situacao) {
		Connection con=null;
		PreparedStatement ps;
		Mensalidade m;
		List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
		ResultSet rs;
		if(inicio!=null&&fim!=null){
			java.sql.Date ini=new java.sql.Date(inicio.getTime());
			java.sql.Date fi=new java.sql.Date(fim.getTime());	
		
		
		String sql="SELECT * FROM MENSALIDADE WHERE VENCIMENTO BETWEEN VENCIMENTO=? AND VENCIMENTO =? AND SITUACAO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setDate(1, ini);
			ps.setDate(2, fi);
			ps.setString(3, situacao);
			rs=ps.executeQuery();
			while(rs.next()){
				m=new Mensalidade();
				m.setContrato(rs.getInt("CONTRATO"));
				m.setNumParcela(rs.getInt("PARCELA"));
				m.setDataVencimento(rs.getDate("VENCIMENTO"));
				m.setValorParcela(rs.getDouble("VALOR"));
				m.setSituacao(rs.getString("SITUACAO"));
				m.setNossoNumero(rs.getString("NOSSO_NUMERO"));
				m.setDacNossoNumero(rs.getString("DAC_NOSSO_NUMERO"));
				m.setCodBarras(rs.getString("COD_BARRAS"));
				m.setDataPagamento(rs.getDate("PAGAMENTO"));
				m.setParcela_carne_ano(rs.getLong("PARCELA_CARNE_ANO"));
				m.setDataCarencia(rs.getDate("CARENCIA"));
				m.setPeriodicidade(rs.getString("PERIODICIDADE"));
				m.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				m.setIdMensalidade(rs.getInt("ID_MENSALIDADE"));
				mensalidades.add(m);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}}
		return mensalidades;
	}
	
	@Override
	public Mensalidade buscar(String nossoNumero) {
		Mensalidade m=new Mensalidade();
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT * FROM MENSALIDADE WHERE NOSSO_NUMERO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, nossoNumero);
			rs=ps.executeQuery();
			
			if(rs.first()){
			m=new Mensalidade();
			m.setContrato(rs.getInt("CONTRATO"));
			m.setNumParcela(rs.getInt("PARCELA"));
			m.setDataVencimento(rs.getDate("VENCIMENTO"));
			m.setValorParcela(rs.getDouble("VALOR"));
			m.setSituacao(rs.getString("SITUACAO"));
			m.setNossoNumero(rs.getString("NOSSO_NUMERO"));
			m.setDacNossoNumero(rs.getString("DAC_NOSSO_NUMERO"));
			m.setCodBarras(rs.getString("COD_BARRAS"));
			m.setDataPagamento(rs.getDate("PAGAMENTO"));
			m.setParcela_carne_ano(rs.getLong("PARCELA_CARNE_ANO"));
			m.setDataCarencia(rs.getDate("CARENCIA"));
			m.setPeriodicidade(rs.getString("PERIODICIDADE"));
			m.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			m.setIdMensalidade(rs.getInt("ID_MENSALIDADE"));System.out.println("Men DAO"+m.toString());}
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return m;
	}
	
	@Override
	public List<Mensalidade> listar() {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
		Mensalidade m=new Mensalidade();
		String sql="SELECT * FROM MENSALIDADE";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				m=new Mensalidade();
				m.setContrato(rs.getInt("CONTRATO"));
				m.setNumParcela(rs.getInt("PARCELA"));
				m.setDataVencimento(rs.getDate("VENCIMENTO"));
				m.setValorParcela(rs.getDouble("VALOR"));
				m.setSituacao(rs.getString("SITUACAO"));
				m.setNossoNumero(rs.getString("NOSSO_NUMERO"));
				m.setDacNossoNumero(rs.getString("DAC_NOSSO_NUMERO"));
				m.setCodBarras(rs.getString("COD_BARRAS"));
				m.setDataPagamento(rs.getDate("PAGAMENTO"));
				m.setParcela_carne_ano(rs.getLong("PARCELA_CARNE_ANO"));
				m.setDataCarencia(rs.getDate("CARENCIA"));
				m.setPeriodicidade(rs.getString("PERIODICIDADE"));
				m.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				m.setIdMensalidade(rs.getInt("ID_MENSALIDADE"));
				mensalidades.add(m);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mensalidades;
	}

	@Override
	public List<Mensalidade> listar(String situacao) {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		Mensalidade m=new Mensalidade();
		List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
		String sql="SELECT * FROM MENSALIDADE WHERE SITUACAO=?";System.out.println("Mensalidade DAO");
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, situacao);
			rs=ps.executeQuery();
			while(rs.next()){
				m=new Mensalidade();System.out.println("Mensalidade DAO 1");
				m.setContrato(rs.getInt("CONTRATO"));System.out.println("Mensalidade DAO 2");
				m.setNumParcela(rs.getInt("PARCELA"));
				m.setDataVencimento(rs.getDate("VENCIMENTO"));
				m.setValorParcela(rs.getDouble("VALOR"));
				m.setSituacao(rs.getString("SITUACAO"));
				m.setNossoNumero(rs.getString("NOSSO_NUMERO"));
				m.setDacNossoNumero(rs.getString("DAC_NOSSO_NUMERO"));
				m.setCodBarras(rs.getString("COD_BARRAS"));
				m.setDataPagamento(rs.getDate("PAGAMENTO"));
				m.setParcela_carne_ano(rs.getLong("PARCELA_CARNE_ANO"));
				m.setDataCarencia(rs.getDate("CARENCIA"));
				m.setPeriodicidade(rs.getString("PERIODICIDADE"));
				m.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				m.setIdMensalidade(rs.getInt("ID_MENSALIDADE"));System.out.println(m.toString());
				mensalidades.add(m);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mensalidades;
	}

	@Override
	public List<Mensalidade> listar(int contrato) {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		Mensalidade m=new Mensalidade();
		List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
		String sql="SELECT * FROM MENSALIDADE WHERE CONTRATO=? ORDER BY VENCIMENTO ASC";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, contrato);
			rs=ps.executeQuery();
			while(rs.next()){
				m=new Mensalidade();
				//CONTRATO, PARCELA, VENCIMENTO, VALOR, SITUACAO, CNR, COD_BARRAS, PAGAMENTO, PARCELA_CARNE_ANO, CARENCIA, PERIODICIDADE, ID_FUNCIONARIO
				m.setContrato(rs.getInt("CONTRATO"));
				m.setNumParcela(rs.getInt("PARCELA"));
				m.setDataVencimento(rs.getDate("VENCIMENTO"));
				m.setValorParcela(rs.getDouble("VALOR"));
				m.setSituacao(rs.getString("SITUACAO"));
				m.setNossoNumero(rs.getString("NOSSO_NUMERO"));
				m.setDacNossoNumero(rs.getString("DAC_NOSSO_NUMERO"));
				m.setCodBarras(rs.getString("COD_BARRAS"));
				m.setDataPagamento(rs.getDate("PAGAMENTO"));
				m.setParcela_carne_ano(rs.getLong("PARCELA_CARNE_ANO"));
				m.setDataCarencia(rs.getDate("CARENCIA"));
				m.setPeriodicidade(rs.getString("PERIODICIDADE"));
				m.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				m.setIdMensalidade(rs.getInt("ID_MENSALIDADE"));
				mensalidades.add(m);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mensalidades;
	}
	
	@Override
	public List<Mensalidade> listarSemCNR() {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		Mensalidade m=new Mensalidade();
		List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
		String sql="SELECT * FROM MENSALIDADE WHERE NOSSO_NUMERO=null";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				m=new Mensalidade();
				m.setContrato(rs.getInt("CONTRATO"));
				m.setNumParcela(rs.getInt("PARCELA"));
				m.setDataVencimento(rs.getDate("VENCIMENTO"));
				m.setValorParcela(rs.getDouble("VALOR"));
				m.setSituacao(rs.getString("SITUACAO"));
				m.setNossoNumero(rs.getString("NOSSO_NUMERO"));
				m.setDacNossoNumero(rs.getString("DAC_NOSSO_NUMERO"));
				m.setCodBarras(rs.getString("COD_BARRAS"));
				m.setDataPagamento(rs.getDate("PAGAMENTO"));
				m.setParcela_carne_ano(rs.getLong("PARCELA_CARNE_ANO"));
				m.setDataCarencia(rs.getDate("CARENCIA"));
				m.setPeriodicidade(rs.getString("PERIODICIDADE"));
				m.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
				m.setIdMensalidade(rs.getInt("ID_MENSALIDADE"));
				mensalidades.add(m);System.out.println(m.toString());
			}
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mensalidades;
	}
	
	@Override
    public boolean geraCarne(Mensalidade m){
	Connection con=null;
	boolean retorno;
	PreparedStatement ps;
	
	String sql="INSERT INTO GERACARNE(CONTRATO,TIPO_PARCELA,VALOR, SITUACAO, DATA_PARCELA, IMPRESSO, CARNE_CRIADO)VALUES(?,?,?,?,?,?,?)";
	try {
	    new ConnectionFactory();
	    con=ConnectionFactory.getConnection();
	    ps=con.prepareStatement(sql);
	    ps.setInt(1, m.getContrato());
	    ps.setString(2, m.getPeriodicidade());
	    ps.setDouble(3, m.getValorParcela());
	    ps.setString(4, "CADASTRAR");
	    ps.setDate(5, new java.sql.Date(m.getDataVencimento().getTime()));
	    ps.setBoolean(6, false);
	    ps.setBoolean(7, true);
	    ps.execute();
	    ps.close();
		con.close();
		retorno=true;
	} catch (Exception e) {
	  
	    e.printStackTrace();
	    System.out.println(e.getMessage());
	    retorno=false;
	}
	return retorno;
}
	@Override
	public List<Mensalidade> listarCarnes() {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
		Mensalidade m=new Mensalidade();
		String sql="SELECT * FROM GERACARNE";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				m=new Mensalidade();
				m.setContrato(rs.getInt("CONTRATO"));
				boolean carne=rs.getBoolean("CARNE_CRIADO");
				boolean imp=rs.getBoolean("IMPRESSO");
				if(carne){
					m.setSituacao("Imprimir carnê!");
				}else if(imp){
					m.setSituacao("Excluir carnê da fila de impressão");
				}else if(!carne){
					m.setSituacao("não foi gerado");
				}
				mensalidades.add(m);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return mensalidades;
	}
@Override
	public Mensalidade buscaUltimoPgm(int contrato){
		
		Connection con=null;
		PreparedStatement ps;
		Mensalidade m=null;
		ResultSet rs;
		String sql="SELECT * FROM MENSALIDADE WHERE CONTRATO=? ORDER BY VENCIMENTO DESC";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, contrato);
			rs=ps.executeQuery();
			if(rs.next()){
				m=new Mensalidade();
				m.setContrato(rs.getInt("CONTRATO"));
				m.setNumParcela(rs.getInt("PARCELA"));
				m.setDataVencimento(rs.getDate("VENCIMENTO"));
				m.setValorParcela(rs.getDouble("VALOR"));
				m.setSituacao(rs.getString("SITUACAO"));
				m.setParcela_carne_ano(rs.getLong("PARCELA_CARNE_ANO"));
				
			}
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){
		System.out.println(e.getMessage()+"---"+e.getStackTrace().toString());
		}catch(Exception e){
			System.out.println(e.getMessage()+"---"+e.getStackTrace().toString());
		}
		
		return m;
	}
public List<Registro> gerarRemessa(){
	Registro registro=new Registro();
	List<Registro>registros=new ArrayList<Registro>();
	Connection con=null;
	PreparedStatement ps;
	ResultSet rs;
	String sql="SELECT C.NOME, C.CPF, C.ENDERECO, C.BAIRRO, C.CIDADE, C.ESTADO, C.CEP, C.CONTRATO, M.VALOR, M.VENCIMENTO FROM CLIENTE C INNER JOIN MENSALIDADE M 	ON (C.CONTRATO=M.CONTRATO) WHERE M.SITUACAO='CADASTRAR' ORDER BY CONTRATO";
	try {
		con=ConnectionFactory.getConnection();
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			registro=new Registro();
			registro.setContrato(String.valueOf(rs.getInt("CONTRATO")));
			registro.setNome(rs.getString("NOME"));
			registro.setCPF(rs.getString("CPF"));
			registro.setEndereco(rs.getString("ENDERECO"));
			registro.setBairro(rs.getString("BAIRRO"));
			registro.setCidade(rs.getString("CIDADE"));
			registro.setEstado(rs.getString("ESTADO"));
			registro.setCep(rs.getString("CEP"));
			String dat=new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("VENCIMENTO"));
			registro.setVencimento(dat);
			registro.setValorTitulo(String.valueOf(rs.getDouble("VALOR")));
			registros.add(registro);
		}
		rs.close();
		ps.close();
		con.close();
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return registros;
}
}
