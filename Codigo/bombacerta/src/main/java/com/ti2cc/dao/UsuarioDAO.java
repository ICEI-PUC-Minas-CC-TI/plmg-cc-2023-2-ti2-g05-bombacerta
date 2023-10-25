package com.ti2cc.dao;

import com.ti2cc.models.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO extends DAO {

    public UsuarioDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    // Método para gerar um hash bcrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para verificar a senha
    public static boolean verifyPassword(String inputPassword, String hashedPassword) {
        return BCrypt.checkpw(inputPassword, hashedPassword);
    }

    public boolean insert(Usuario usuario) {
        // criar random id:
        while (true) {
            int id = (int) (Math.random() * 100000);
            if (getById(id) == null) {
                usuario.setId(id);
                break;
            }
        }

        boolean status = false;
        String hashed = hashPassword(usuario.getSenha());
        try {
            String sql = "INSERT INTO usuarios(id, nome, email, senha) VALUES(" + usuario.getId() + ", '"
                    + usuario.getNome() + "', '" + usuario.getEmail() + "', '" + hashed + "')";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public int login(String email, String senha){
        Usuario user = getByEmail(email);
        int id = -1;
        if(user == null){
            return id;
        } 
        if(verifyPassword(senha, user.getSenha())){
            return user.getId();
        }
        return id;
    }

    public Usuario getByEmail(String email){
        Usuario usuario = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM usuarios WHERE email = '" + email + "'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"),
                        rs.getString("email"));
            }
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Usuario getById(int id) {
        Usuario usuario = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM usuarios WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"),
                        rs.getString("email"));
            }
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public boolean updateById(int id, String nome, String email, String senha) {
        boolean status = false;
        try {
            String sql = "UPDATE usuarios SET nome = '" + nome + "', email = '" + email + "', senha = '" + senha
                    + "' WHERE id = " + id;
            PreparedStatement st = conexao.prepareStatement(sql);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public boolean deleteById(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM usuarios WHERE id = " + id;
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
