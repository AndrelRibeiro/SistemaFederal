package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Contrato;

public class ContratoDaoImplementation implements ContratoDao, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public boolean adicionar(Contrato c) {
		Connection con=null;
		boolean retorno=false;
		PreparedStatement ps;
		String sql="INSERT INTO CONTRATO (NUM_CONTRATO,VALOR_CONTRATO,ADESAO, PARCELAS, VALOR_PARCELAS, MENSALIDADE, QTAXA1,QTAXA2,QTAXA3,QTAXA4,QTAXA5,VTAXA1,VTAXA2,VTAXA3,VTAXA4,VTAXA5,DATA_CONTRATO,PERIODICIDADE,SITUACAO,CARENCIA,VALOR_TOTAL, PLANO, DIA_PAGAMENTO,ID_FUNCIONARIO, ID_VENDEDOR)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, c.getnContrato());
		    ps.setDouble(2, c.getValorContrato());
		    ps.setDouble(3, c.getEntrada());
		    ps.setInt(4, c.getParcEntrada());
		    ps.setDouble(5, c.getvParcelas());
		    ps.setDouble(6, c.getMensalidade());
		    ps.setInt(7, c.getnTaxa1());
		    ps.setInt(8, c.getnTaxa2());
		    ps.setInt(9, c.getnTaxa3());
		    ps.setInt(10, c.getnTaxa4());
		    ps.setInt(11, c.getnTaxa5());
		    ps.setDouble(12, c.getvTaxa1());
		    ps.setDouble(13, c.getvTaxa2());
		    ps.setDouble(14, c.getvTaxa3());
		    ps.setDouble(15, c.getvTaxa4());
		    ps.setDouble(16, c.getvTaxa5());
		    ps.setDate(17, new java.sql.Date(c.getDataContrato().getTime()));
		    ps.setString(18, c.getPeriodicidade());
		    ps.setString(19, c.getSituacao());
		    ps.setDate(20, new java.sql.Date(c.getCarencia().getTime()));
		    ps.setDouble(21, c.getValorTotal());
		    ps.setString(22, c.getPlano());
		    ps.setInt(23, c.getDiaVencimento());
		    ps.setInt(24, c.getIdFuncionario());
		    ps.setInt(25, c.getIdVendedor());
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
	public boolean adicionarCacelado(Contrato c) {
		Connection con=null;
		boolean retorno=false;
		PreparedStatement ps;
		String sql="INSERT INTO CONTRATO_CANCELADO (NUM_CONTRATO,VALOR_CONTRATO,ADESAO, PARCELAS, VALOR_PARCELAS, MENSALIDADE, QTAXA1,QTAXA2,QTAXA3,QTAXA4,QTAXA5,VTAXA1,VTAXA2,VTAXA3,VTAXA4,VTAXA5,DATA_CONTRATO,PERIODICIDADE,SITUACAO,CARENCIA,VALOR_TOTAL, PLANO, DIA_PAGAMENTO,ID_FUNCIONARIO, ID_VENDEDOR)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setInt(1, c.getnContrato());
		    ps.setDouble(2, c.getValorContrato());
		    ps.setDouble(3, c.getEntrada());
		    ps.setInt(4, c.getParcEntrada());
		    ps.setDouble(5, c.getvParcelas());
		    ps.setDouble(6, c.getMensalidade());
		    ps.setInt(7, c.getnTaxa1());
		    ps.setInt(8, c.getnTaxa2());
		    ps.setInt(9, c.getnTaxa3());
		    ps.setInt(10, c.getnTaxa4());
		    ps.setInt(11, c.getnTaxa5());
		    ps.setDouble(12, c.getvTaxa1());
		    ps.setDouble(13, c.getvTaxa2());
		    ps.setDouble(14, c.getvTaxa3());
		    ps.setDouble(15, c.getvTaxa4());
		    ps.setDouble(16, c.getvTaxa5());
		    ps.setDate(17, new java.sql.Date(c.getDataContrato().getTime()));
		    ps.setString(18, c.getPeriodicidade());
		    ps.setString(19, c.getSituacao());
		    ps.setDate(20, new java.sql.Date(c.getCarencia().getTime()));
		    ps.setDouble(21, c.getValorTotal());
		    ps.setString(22, c.getPlano());
		    ps.setInt(23, c.getDiaVencimento());
		    ps.setInt(24, c.getIdFuncionario());
		    ps.setInt(25, c.getIdVendedor());
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

	@Override
	public boolean excluir(Contrato c) {
		Connection con=null;
		PreparedStatement ps;
		String sql="DELETE FROM CONTRATO WHERE NUM_CONTRATO=?";
		boolean retorno=false;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, c.getnContrato());
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
		return retorno;
	}

	@Override
	public Contrato buscar(int contrato) {
		Contrato c=new Contrato();
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		String sql="SELECT * FROM CONTRATO WHERE NUM_CONTRATO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, contrato);
			rs=ps.executeQuery();
			c=new Contrato();
			if(rs.next()){
			c.setnContrato(rs.getInt("NUM_CONTRATO"));
			c.setValorContrato(rs.getDouble("VALOR_CONTRATO"));
			c.setEntrada(rs.getDouble("ADESAO"));
			c.setParcEntrada(rs.getInt("PARCELAS"));
			c.setvParcelas(rs.getDouble("VALOR_PARCELAS"));
			c.setMensalidade(rs.getDouble("MENSALIDADE"));
			c.setnTaxa1(rs.getInt("QTAXA1"));
			c.setnTaxa2(rs.getInt("QTAXA2"));
			c.setnTaxa3(rs.getInt("QTAXA3"));
			c.setnTaxa4(rs.getInt("QTAXA4"));
			c.setnTaxa5(rs.getInt("QTAXA5"));
			c.setvTaxa1(rs.getDouble("VTAXA1"));
			c.setvTaxa2(rs.getDouble("VTAXA2"));
			c.setvTaxa3(rs.getDouble("VTAXA3"));
			c.setvTaxa4(rs.getDouble("VTAXA4"));
			c.setvTaxa5(rs.getDouble("VTAXA5"));
			c.setDataContrato(rs.getDate("DATA_CONTRATO"));
			c.setPeriodicidade(rs.getString("PERIODICIDADE"));
			c.setSituacao(rs.getString("SITUACAO"));
			c.setCarencia(rs.getDate("CARENCIA"));
			c.setValorTotal(rs.getDouble("VALOR_TOTAL"));
			c.setPlano(rs.getString("PLANO"));
			c.setDiaVencimento(rs.getInt("DIA_PAGAMENTO"));
			c.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			c.setIdVendedor(rs.getInt("ID_VENDEDOR"));
			ps.close();
			rs.close();
			con.close();}
		}catch(SQLException e){ 
			System.out.println(e.getMessage());
			
	}catch (Exception e) {
		    // TODO Auto-generated catch block
		   System.out.println(e.getMessage());
		    
		}
		return c;
	}

	@Override
	public boolean alterar(Contrato c) {
		Connection con=null;
		boolean retorno;
		PreparedStatement ps;
		String sql="UPDATE CONTRATO SET VALOR_CONTRATO=?,ADESAO=?, PARCELAS=?, VALOR_PARCELAS=?, MENSALIDADE=?, QTAXA1=? ,QTAXA2=? ,QTAXA3=? ,QTAXA4=? ,QTAXA5=? ,VTAXA1=? ,VTAXA2=? ,VTAXA3=? ,VTAXA4=? ,VTAXA5=? ,DATA_CONTRATO=? ,PERIODICIDADE=? ,SITUACAO=? ,CARENCIA=? ,VALOR_TOTAL=? , PLANO=? DIA_PAGAMENTO=?,ID_FUNCIONARIO=? ,ID_VENDEDOR=? WHERE NUM_CONTRATO=?)";
		try {
		    new ConnectionFactory();
		    con=ConnectionFactory.getConnection();
		    ps=con.prepareStatement(sql);
		    ps.setDouble(1, c.getValorContrato());
		    ps.setDouble(2, c.getEntrada());
		    ps.setInt(3, c.getParcEntrada());
		    ps.setDouble(4, c.getvParcelas());
		    ps.setDouble(5, c.getMensalidade());
		    ps.setInt(6, c.getnTaxa1());
		    ps.setInt(7, c.getnTaxa2());
		    ps.setInt(8, c.getnTaxa3());
		    ps.setInt(9, c.getnTaxa4());
		    ps.setInt(10, c.getnTaxa5());
		    ps.setDouble(11, c.getvTaxa1());
		    ps.setDouble(12, c.getvTaxa2());
		    ps.setDouble(13, c.getvTaxa3());
		    ps.setDouble(14, c.getvTaxa4());
		    ps.setDouble(15, c.getvTaxa5());
		    ps.setDate(16, new java.sql.Date(c.getDataContrato().getTime()));
		    ps.setString(17, c.getPeriodicidade());
		    ps.setString(18, c.getSituacao());
		    ps.setDate(19, new java.sql.Date(c.getCarencia().getTime()));
		    ps.setDouble(20, c.getValorTotal());
		    ps.setString(21, c.getPlano());
		    ps.setInt(22, c.getDiaVencimento());
		    ps.setInt(23, c.getIdFuncionario());
		    ps.setInt(24, c.getIdVendedor());
		    ps.setInt(25, c.getnContrato());
		    ps.execute();
		    ps.close();
			con.close();
			retorno=true;
		} catch(SQLException e){ 
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
	public List<Contrato> listar() {
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		List<Contrato>contratos=new ArrayList<Contrato>();
		Contrato c=new Contrato();
		String sql="SELECT * FROM CONTRATO";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				c=new Contrato();

				//"NUM_CONTRATO,VALOR_CONTRATO,ADESAO, PARCELAS, 
				//VALOR_PARCELAS,MENSALIDADE, QTAXA1,QTAXA2,QTAXA3,QTAXA4,QTAXA5,
				//VTAXA1,VTAXA2,VTAXA3,VTAXA4,VTAXA5,DATA_CONTRATO,   PERIODICIDADE,
				//SITUACAO,   CARENCIA,   VALOR_TOTAL,    PLANO,  DIA_PAGAMENTO,  ID_FUNCIONARIO, ID_VENDEDOR"
			c.setnContrato(rs.getInt("NUM_CONTRATO"));
			c.setValorContrato(rs.getDouble("VALOR_CONTRATO"));
			c.setEntrada(rs.getDouble("ADESAO"));
			c.setParcEntrada(rs.getInt("PARCELAS"));
			c.setvParcelas(rs.getDouble("VALOR_PARCELAS"));
			c.setMensalidade(rs.getDouble("MENSALIDADE"));
			c.setnTaxa1(rs.getInt("QTAXA1"));
			c.setnTaxa2(rs.getInt("QTAXA2"));
			c.setnTaxa3(rs.getInt("QTAXA3"));
			c.setnTaxa4(rs.getInt("QTAXA4"));
			c.setnTaxa5(rs.getInt("QTAXA5"));
			c.setvTaxa1(rs.getDouble("VTAXA1"));
			c.setvTaxa2(rs.getDouble("VTAXA2"));
			c.setvTaxa3(rs.getDouble("VTAXA3"));
			c.setvTaxa4(rs.getDouble("VTAXA4"));
			c.setvTaxa5(rs.getDouble("VTAXA5"));
			c.setDataContrato(rs.getDate("DATA_CONTRATO"));
			c.setPeriodicidade(rs.getString("PERIODICIDADE"));
			c.setSituacao(rs.getString("SITUACAO"));
			c.setCarencia(rs.getDate("CARENCIA"));
			c.setValorTotal(rs.getDouble("VALOR_TOTAL"));
			c.setPlano(rs.getString("PLANO"));
			c.setDiaVencimento(rs.getInt("DIA_PAGAMENTO"));
			c.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			c.setIdVendedor(rs.getInt("ID_VENDEDOR"));
			contratos.add(c);
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
		return contratos;
	
	}

	@Override
	public List<Contrato> listar(Contrato c) {
		// TODO Auto-generated method stub
		return null;
	}

}
