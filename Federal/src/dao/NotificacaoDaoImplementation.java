package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.InformeErro;

public class NotificacaoDaoImplementation implements NotificacaoDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean adicionar(InformeErro info) {
		PreparedStatement ps;System.out.println("DAO-"+info.toString());
		boolean retorno;
		Connection con=null;
		String sql="INSERT INTO NOTIFICACAO(DESCRICAO,DATAS,CONTRATO,OBJETO,CASO_USO,ID_FUNCIONARIO) VALUES(?,?,?,?,?,?)";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, info.getDescricao());
			//Date data=new SimpleDateFormat("dd/MM/yyyy").parse(info.getData().toString());
			ps.setDate(2,new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			ps.setInt(3, info.getContrato());
			ps.setString(4, info.getObjeto());
			ps.setString(5, info.getCasoDeUso());
			ps.setInt(6, info.getIdFuncionario());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch(Exception e){
			e.printStackTrace();
			retorno=false;
		}
		
		return retorno;
	}

	@Override
	public boolean remover(InformeErro info) {
PreparedStatement ps;
		Connection con=null;
boolean retorno;
String sql="DELETE FROM NOTIFICACAO WHERE INFORMACAO=?";
try{
	new ConnectionFactory();
	con=ConnectionFactory.getConnection();
	ps=con.prepareStatement(sql);
	ps.setInt(1, info.getIdInformacao());
	ps.execute();
	ps.close();
	con.close();
	retorno=true;
}catch(Exception e){
	e.getStackTrace().toString();
	retorno=false;
}
		return retorno;
	}

	@Override
	public boolean alterar(InformeErro info) {
		PreparedStatement ps;
		boolean retorno;
		Connection con = null;
		String sql="UPDATE NOTIFICACAO SET DESCRICAO=?, DATAS=?, CONTRATO=?, OBJETO=?, CASO_USO=?, ID_FUNCIONARIO=? WHERE INFORMACAO=?";
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, info.getDescricao());
			ps.setDate(2, new java.sql.Date(info.getData().getTime()));
			ps.setInt(3, info.getContrato());
			ps.setString(4, info.getObjeto());
			ps.setString(5, info.getCasoDeUso());
			ps.setInt(6, info.getIdFuncionario());
			ps.setInt(7, info.getIdInformacao());
			ps.execute();
			ps.close();
			con.close();
			retorno=true;
		}catch(Exception e){
			e.getStackTrace().toString();
			retorno=false;
		}
		return retorno;
	}

	@Override
	public InformeErro buscar(InformeErro info) {
		String sql="SELECT * FROM NOTIFICACAO WHERE INFORMACAO =?";
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, info.getIdInformacao());
			rs=ps.executeQuery();
			info.setDescricao(rs.getString("DESCRICAO"));
			info.setData(rs.getDate("DATAS"));
			info.setContrato(rs.getInt("CONTRATO"));
			info.setObjeto(rs.getString("OBJETO"));
			info.setDescricao(rs.getString("DESCRICAO"));
			info.setCasoDeUso(rs.getString("CASO_USO"));
			info.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){
			e.getStackTrace().toString();		
		
		e.printStackTrace();
	    }catch(Exception e) {
			e.getStackTrace().toString();		
			}
	    
		return info;
	}	

	@Override
	public List<InformeErro> listar(InformeErro info) {
		String sql="SELECT * FROM NOTIFICACAO";
		List<InformeErro>informacoes=new ArrayList<InformeErro>();
		PreparedStatement ps;
		Connection con=null;
		ResultSet rs;
		try{
			new ConnectionFactory();
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				info=new InformeErro();
			info.setDescricao(rs.getString("DESCRICAO"));
			info.setData(rs.getDate("DATAS"));
			info.setContrato(rs.getInt("CONTRATO"));
			info.setObjeto(rs.getString("OBJETO"));
			info.setDescricao(rs.getString("DESCRICAO"));
			info.setCasoDeUso(rs.getString("CASO_USO"));
			info.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
			informacoes.add(info);
			}
			ps.close();
			rs.close();
			con.close();
		}catch(SQLException e){
			e.getStackTrace().toString();		
		
		e.printStackTrace();
	    }catch(Exception e) {
			e.getStackTrace().toString();		
			}
	    
		return informacoes;
	}

}
