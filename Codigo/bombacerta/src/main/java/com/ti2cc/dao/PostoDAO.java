package com.ti2cc.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ti2cc.models.Posto;

public class PostoDAO extends DAO {

    public PostoDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Posto posto){
        boolean status = true;
        try {
            String sql = "INSERT INTO postos(cnpj, nome, marca, lat, lng, preco_gasolina, preco_alcool) VALUES(" +
        posto.getCPNJ() + ", '" + posto.getNome() + "', '" + posto.getMarca() + "', " + posto.getLatitude() + ", " + posto.getLongitude() + ", " + posto.getPreco_gasolina() + ", " + posto.getPreco_alcool() + ")";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public Posto getByCNPJ(String cnpj){
        Posto posto = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM postos WHERE cnpj = '" + cnpj + "'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                posto = new Posto(rs.getString("cnpj"), rs.getString("nome"), rs.getString("marca"), rs.getDouble("lat"), rs.getDouble("lng"), rs.getBigDecimal("preco_gasolina"), rs.getBigDecimal("preco_alcool"));
            }
            st.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posto;
    }

    public List<Posto> getAll(){
        List<Posto> postos = new ArrayList<Posto>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM postos";
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Posto p = new Posto(rs.getString("cnpj"), rs.getString("nome"), rs.getString("marca"), rs.getDouble("lat"), rs.getDouble("lng"), rs.getBigDecimal("preco_gasolina"), rs.getBigDecimal("preco_alcool"));
                postos.add(p);                      
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return postos;
    }

    public boolean updateByCNPJ(String cnpj, String nome, String marca, double lat, double lng, BigDecimal preco_gasolina, BigDecimal preco_alcool){
        boolean status = false;
        try {
            String sql = "UPDATE postos SET nome = '" + nome + "', marca = '" + marca + "', lat = " + lat + ", lng = " + lng + ", preco_gasolina = " + preco_gasolina + ", preco_alcool = " + preco_alcool + " WHERE cnpj = " + cnpj;
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public boolean deleteByCNPJ(String cnpj){
        boolean status = false;
        try {
            String sql = "DELETE FROM postos WHERE cnpj = '" + cnpj + "'";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

}
