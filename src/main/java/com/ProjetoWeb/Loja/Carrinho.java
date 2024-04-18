package com.ProjetoWeb.Loja;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> produtos;

    public Carrinho() {
        this.produtos = new ArrayList<>();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(int idProduto) {
        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
                produtos.remove(produto);
                break;
            }
        }
    }
}
