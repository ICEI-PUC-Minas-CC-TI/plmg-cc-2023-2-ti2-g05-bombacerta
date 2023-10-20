package com.ti2cc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ti2cc.models.Abastecimento;

public class AbastecimentoDAO extends DAO {

    public AbastecimentoDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean insert(Abastecimento abastecimento){
        boolean status = false;
        try {
            String sql = "insert into abastecimentos(preco, tipo, litros, data, usuarioid, postoid) values(" + abastecimento.getPreco() + ", '" + abastecimento.getTipo() + "', '" + abastecimento.getLitros() + "', '" + abastecimento.getData() + "', " + abastecimento.getId_usuario() + ", " + abastecimento.getId_posto() + ")";
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
