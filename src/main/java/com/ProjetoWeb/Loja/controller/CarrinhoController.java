package com.ProjetoWeb.Loja.controller;

import com.ProjetoWeb.Loja.Produto;
import com.ProjetoWeb.Loja.dominio.CarrinhoDao;
import com.ProjetoWeb.Loja.dominio.ProdutoDao;

import ch.qos.logback.core.model.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private ProdutoDao produtoDao;

    @Autowired
    private CarrinhoDao carrinhoDao;

    

    
    @GetMapping("/mostrar")
    public ResponseEntity<String> mostrarCarrinho(HttpServletRequest request, HttpServletResponse response) {
        List<Produto> produtosNoCarrinho = carrinhoDao.obterCarrinho();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Carrinho de Compras</title></head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h1>Produtos no Carrinho:</h1>");
        htmlBuilder.append("<ul>");

        for (Produto produto : produtosNoCarrinho) {
            htmlBuilder.append("<li>");
            htmlBuilder.append("Nome: ").append(produto.getNome()).append(", ");
            htmlBuilder.append("Descrição: ").append(produto.getDescricao()).append(", ");
            htmlBuilder.append("Preço: ").append(produto.getPreco());
            htmlBuilder.append("</li>");
        }

        htmlBuilder.append("</ul>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return ResponseEntity.ok().body(htmlBuilder.toString());
    } 

    @PostMapping("/adicionar")
    public String adicionarAoCarrinho(@RequestParam("id") int id, HttpServletResponse response) {
        carrinhoDao.adicionarProduto(id);
        return "redirect:/carrinho/mostrar";
    }
    
    @PostMapping("/remover")
    public String removerDoCarrinho(@RequestParam("id") int id, HttpServletResponse response) {
        carrinhoDao.removerProduto(id);
        return "redirect:/carrinho/mostrar";
    }
    

}
