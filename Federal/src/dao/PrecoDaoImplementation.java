package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cliente;
import beans.Preco;

public class PrecoDaoImplementation implements PrecoDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean adicionar(Preco preco) {
		Connection con = null;
		boolean retorno;
		PreparedStatement ps;
		// private int porcentagem especial executivo crematorio urna
		// sepultamento adesao dep66 dep76;
		// dep86 dep91 avu65 avu66 avu76 avu86 avu91 minimo;
		String sql = "INSERT INTO PRECO (PORCENTAGEM, ESPECIAL, EXECUTIVO, CREMATORIO, URNA, SEPULTAMENTO, ADESAO, DEP66, DEP76, DEP86, DEP91, AVU65, AVU66, AVU76, AVU86, AVU91, MINIMO, AJUSTE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			new ConnectionFactory();
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, preco.getPorcentagem());
			ps.setDouble(2, preco.getEspecial());
			ps.setDouble(3, preco.getExecutivo());
			ps.setDouble(4, preco.getCrematorio());
			ps.setDouble(5, preco.getUrna());
			ps.setDouble(6, preco.getSepultamento());
			ps.setDouble(7, preco.getAdesao());
			ps.setDouble(8, preco.getDep66());
			ps.setDouble(9, preco.getDep76());
			ps.setDouble(10, preco.getDep86());
			ps.setDouble(11, preco.getDep91());
			ps.setDouble(12, preco.getAvu65());
			ps.setDouble(13, preco.getAvu66());
			ps.setDouble(14, preco.getAvu76());
			ps.setDouble(15, preco.getAvu86());
			ps.setDouble(16, preco.getAvu91());
			ps.setDouble(17, preco.getMinimo());
			ps.setDate(18, new java.sql.Date(new java.util.Date().getTime()));

			ps.execute();
			ps.close();
			con.close();
			retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			retorno = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getCause());
			retorno = false;
		}
		return retorno;
	}

	@Override
	public boolean excluir(Preco preco) {
		Connection con = null;
		boolean retorno = false;
		PreparedStatement ps;
		String sql = "DELETE FROM PRECO WHERE ID_PRECO=?";
		new ConnectionFactory();
		try {
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, preco.getIdPreco());
			ps.execute();
			ps.close();
			con.close();
			retorno = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			retorno = false;
		}

		return retorno;
	}

	@Override
	public boolean atualizar(Preco preco) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Preco buscar(Preco preco) {
		String sql = "SELECT * FROM PRECO";
		PreparedStatement ps;
		Connection con = null;
		ResultSet rs;
		try {
			new ConnectionFactory();
			con = ConnectionFactory.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.last();
			//PORCENTAGEM, ESPECIAL, EXECUTIVO, CREMATORIO, URNA, SEPULTAMENTO, ADESAO, DEP66, DEP76, DEP86, DEP91, AVU65, AVU66, AVU76, AVU86, AVU91, MINIMO, AJUSTE
			preco.setIdPreco(rs.getInt("ID_PRECO"));
			preco.setPorcentagem(rs.getInt("PORCENTAGEM"));
			preco.setEspecial(rs.getDouble("ESPECIAL"));
			preco.setExecutivo(rs.getDouble("EXECUTIVO"));
			preco.setCrematorio(rs.getDouble("CREMATORIO"));
			preco.setUrna(rs.getDouble("URNA"));
			preco.setSepultamento(rs.getDouble("SEPULTAMENTO"));
			preco.setAdesao(rs.getDouble("ADESAO"));
			preco.setDep66(rs.getDouble("DEP66"));
			preco.setDep76(rs.getDouble("DEP76"));
			preco.setDep86(rs.getDouble("DEP86"));
			preco.setDep91(rs.getDouble("DEP91"));
			preco.setAvu65(rs.getDouble("AVU65"));
			preco.setAvu66(rs.getDouble("AVU66"));
			preco.setAvu76(rs.getDouble("AVU76"));
			preco.setAvu86(rs.getDouble("AVU86"));
			preco.setAvu91(rs.getDouble("AVU91"));
			preco.setMinimo(rs.getDouble("MINIMO"));
			preco.setReajuste(rs.getDate("AJUSTE"));
			ps.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return preco;
	}

	@Override
	public List<Preco> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
