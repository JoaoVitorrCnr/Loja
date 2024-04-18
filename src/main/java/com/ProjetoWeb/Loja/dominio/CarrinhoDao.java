package com.ProjetoWeb.Loja.dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ProjetoWeb.Loja.Conexao;
import com.ProjetoWeb.Loja.Produto;

import org.springframework.stereotype.Repository;

@Repository
public class CarrinhoDao {
    
    public void adicionarProduto(int idProduto) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Conexao.getConnection();
            String sql = "INSERT INTO carrinho (id_produto) VALUES (?)";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha na conexão! Verifique o console de saída: " + e.getMessage());
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
    
    public void removerProduto(int idProduto) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = Conexao.getConnection();
            String sql = "DELETE FROM carrinho WHERE id_produto = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Falha na conexão! Verifique o console de saída: " + e.getMessage());
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
    
    public List<Produto> obterCarrinho() {
        List<Produto> produtos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = Conexao.getConnection();
            String sql = "SELECT p.id, p.nome, p.descricao, p.preco FROM carrinho c INNER JOIN produtos p ON c.id_produto = p.id";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Falha na conexão! Verifique o console de saída: " + e.getMessage());
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
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
        
        return produtos;
    }
}
