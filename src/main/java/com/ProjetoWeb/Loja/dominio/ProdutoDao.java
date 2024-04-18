package com.ProjetoWeb.Loja.dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ProjetoWeb.Loja.Conexao;
import com.ProjetoWeb.Loja.Produto;

@Repository
public class ProdutoDao {
    
    public void adicionarProduto(Produto p){
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            
            connection = Conexao.getConnection();
            stmt = connection.prepareStatement(
                    "insert into produtos (nome, descricao, preco, estoque) values (?,?,?,?)");

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getEstoque());
            
            stmt.executeUpdate();

        } catch (SQLException  ex) {
            System.out.println("Falha na conexão! Verifique o console de saída: " + ex.getMessage());
        }finally {
            // Fechar a conexão no bloco finally para garantir que seja fechada, independentemente de ocorrer uma exceção
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

    public List<Produto> obterListaProdutos() {
        List<Produto> produtoList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = Conexao.getConnection();
            String query = "SELECT id, nome, descricao, preco, estoque FROM produtos";
            stmt = connection.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) { //Enquanto tiver uma proxima linha de dados no banco
                int id = rs.getInt("id");   //Pegar os dados que estão no rs(var que guarda a linha do banco)
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                double preco = rs.getDouble("preco");
                int estoque = rs.getInt("estoque");

                Produto produto = new Produto(); //Preenche o objeto   
                produto.setId(id);
                produto.setNome(nome);
                produto.setDescricao(descricao);
                produto.setPreco(preco);
                produto.setEstoque(estoque);

                produtoList.add(produto); //Adiciona na lista
            }
        } catch (SQLException ex) {
            System.out.println("Falha na conexão! Verifique o console de saída: " + ex.getMessage());
        } finally {
            // Fecha as conexões e recursos
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

        return produtoList; //Retorna a lista com todos os dados do banco
    }
    
    public void removerProduto(Produto p) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Conexao.getConnection();
            String query = "DELETE FROM produtos WHERE id = (?)";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, p.getId()); // Substitua getId pelo método que retorna o id do produto
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            // Feche as conexões e recursos
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    
}

