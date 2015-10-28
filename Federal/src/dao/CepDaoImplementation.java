package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Endereco;

public class CepDaoImplementation implements CepDao{

	@Override
	public Endereco buscar(String cep) {
		Endereco endereco=new Endereco();
		Connection con=null;
		ResultSet rs;
		PreparedStatement ps;
		String sql="SELECT ED.ENDERECO_CEP, ED.ENDERECO_LOGRADOURO, B.BAIRRO_DESCRICAO, C.CIDADE_DESCRICAO, U.UF_DESCRICAO FROM ENDERECO ED"
				+ " INNER JOIN BAIRRO B ON (B.BAIRRO_CODIGO=ED.BAIRRO_CODIGO)"
				+ " INNER JOIN CIDADE C ON (C.CIDADE_CODIGO=B.CIDADE_CODIGO)"
				+ " INNER JOIN UF U ON (U.UF_CODIGO=C.UF_CODIGO) WHERE ED.ENDERECO_CEP=?";
		new ConnectionFactory();
		try {
			con=ConnectionFactory.getConnection();
			ps=con.prepareStatement(sql);
			ps.setString(1, cep);
			rs=ps.executeQuery();
			if(rs.next()){
				endereco.setEstado(rs.getString("UF_DESCRICAO"));
				endereco.setCidade(rs.getString("CIDADE_DESCRICAO"));
				endereco.setBairro(rs.getString("BAIRRO_DESCRICAO"));
				endereco.setLogradouro(rs.getString("ENDERECO_LOGRADOURO"));
				endereco.setCep(rs.getString("ENDERECO_CEP"));
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return endereco;
	}

}
