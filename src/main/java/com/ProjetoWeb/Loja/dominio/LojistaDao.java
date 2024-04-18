package com.ProjetoWeb.Loja.dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ProjetoWeb.Loja.Conexao;
import com.ProjetoWeb.Loja.Lojista;

@Repository
public class LojistaDao {
   
    public Boolean buscarLojista(Lojista lojista) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            connection = Conexao.getConnection();
            String query = "SELECT nome, email, senha FROM lojistas WHERE email = ? AND senha = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, lojista.getEmail());
            stmt.setString(2, lojista.getSenha());
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                return true;
            }   
        }catch(SQLException ex){
            System.out.println(ex);
        } finally {
            // Feche as conexões e recursos
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        
        return false;
    }
}
