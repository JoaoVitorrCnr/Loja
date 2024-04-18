package com.ProjetoWeb.Loja.dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ProjetoWeb.Loja.Cliente;
import com.ProjetoWeb.Loja.Conexao;



@Repository
public class ClienteDao {
    
    public void cadastrarCliente(Cliente c) {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = Conexao.getConnection();
            stmt = connection.prepareStatement(
                    "insert into clientes (nome, email, senha) values (?,?,?)");
    
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getSenha());
    
            stmt.executeUpdate();
        } catch (SQLException  ex) {
            System.out.println("Falha na conexão! Verifique o console de saída: " + ex.getMessage());
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    
    public Boolean buscarCliente(Cliente cliente) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            connection = Conexao.getConnection();
            String query = "SELECT nome, email, senha FROM clientes WHERE email = ? AND senha = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, cliente.getEmail());
            stmt.setString(2, cliente.getSenha());
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                return true;
            }   
        }catch(SQLException ex){
            System.out.println(ex);
        } finally {
   
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
