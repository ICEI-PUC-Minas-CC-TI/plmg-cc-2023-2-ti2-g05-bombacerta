package com.ti2cc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ti2cc.models.Abastecimento;
import com.ti2cc.models.Posto;
import com.ti2cc.models.Tipo;

public class AbastecimentoDAO extends DAO {

    public AbastecimentoDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Abastecimento abastecimento) {
        boolean status = false;
        try {
            String sql = "insert into abastecimentos(preco, tipo, litros, data, usuarioid, postoid) values("
                    + abastecimento.getPreco() + ", '" + abastecimento.getTipo() + "', '" + abastecimento.getLitros()
                    + "', '" + abastecimento.getData() + "', " + abastecimento.getId_usuario() + ", "
                    + abastecimento.getId_posto() + ")";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public List<Abastecimento> getAll(int userid) {
        List<Abastecimento> abastecimentos = new ArrayList<Abastecimento>();

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM abastecimentos where usuarioid = " + userid + "";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Tipo tipoInserido;
                if (tipo == "Gasolina") {
                    tipoInserido = Tipo.GASOLINA;
                } else {
                    tipoInserido = Tipo.ALCOOL;
                }
                Abastecimento a = new Abastecimento(rs.getString("postoid"), rs.getInt("usuarioid"),
                        rs.getBigDecimal("preco"), rs.getDouble("litros"), tipoInserido,
                        LocalDate.parse(rs.getString("data")));
                abastecimentos.add(a);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return abastecimentos;
    }
}
