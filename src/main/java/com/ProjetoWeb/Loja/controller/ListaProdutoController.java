package com.ProjetoWeb.Loja.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProjetoWeb.Loja.Produto;
import com.ProjetoWeb.Loja.dominio.ProdutoDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ListaProdutoController {
    @RequestMapping(value = "/AdicionarProduto", method = RequestMethod.POST)
    public void adicionarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int estoque = Integer.parseInt(request.getParameter("estoque"));

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setEstoque(estoque);

        ProdutoDao produtoDao = new ProdutoDao();

        produtoDao.adicionarProduto(produto);

        response.sendRedirect("listaProdutos");
    }

    @Autowired
    private ProdutoDao produtoDao;

    @GetMapping("/listaProdutos")
public ResponseEntity<String> listarProdutos() {
    List<Produto> productList = produtoDao.obterListaProdutos();

    StringBuilder htmlBuilder = new StringBuilder();
    htmlBuilder.append("<!DOCTYPE html>");
    htmlBuilder.append("<html lang=\"en\">");
    htmlBuilder.append("<head>");
    htmlBuilder.append("<meta charset=\"UTF-8\">");
    htmlBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
    htmlBuilder.append("<title>Lista de Produtos</title>");
    htmlBuilder.append("<link rel=\"stylesheet\" href=\"Css/HomeLojista.css\">");
    htmlBuilder.append("</head>");
    htmlBuilder.append("<body>");
    htmlBuilder.append("<div class=\"container\">");
    htmlBuilder.append("<h1>Lista de Produtos</h1>");
    htmlBuilder.append("<ul class=\"product-list\">");
    for (Produto produto : productList) {
        htmlBuilder.append("<li class=\"product-item\">");
        htmlBuilder.append("<span class=\"product-name\">" + produto.getNome() + "</span>");
        htmlBuilder.append("<p class=\"product-description\">" + produto.getDescricao() + "</p>");
        htmlBuilder.append("<p class=\"product-price\">Preço: R$ " + produto.getPreco() + "</p>");
        htmlBuilder.append("<p class=\"product-quantity\">Quantidade: " + produto.getEstoque() + "</p>");
        htmlBuilder.append("<div class=\"product-actions\">");
        // Formulário para remoção do produto
        htmlBuilder.append("<form action=\"/removerProduto\" method=\"post\">");
        htmlBuilder.append("<input type=\"hidden\" name=\"id\" value=\"" + produto.getId() + "\">");
        htmlBuilder.append("<button type=\"submit\">Remover</button>");
        htmlBuilder.append("</form>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("</li>");
    }
    htmlBuilder.append("</ul>");
    htmlBuilder.append("<div class=\"add-product\">");
    htmlBuilder.append("<h2>Adicionar Produto</h2>");
    htmlBuilder.append("<form action=\"/AdicionarProduto\" method=\"post\">");
    htmlBuilder.append("<label for=\"product-name\">Nome:</label>");
    htmlBuilder.append("<input type=\"text\" id=\"product-name\" name=\"nome\"><br>");
    htmlBuilder.append("<label for=\"product-description\">Descrição:</label>");
    htmlBuilder.append("<input type=\"text\" id=\"product-description\" name=\"descricao\"><br>");
    htmlBuilder.append("<label for=\"product-price\">Preço:</label>");
    htmlBuilder.append("<input type=\"text\" id=\"product-price\" name=\"preco\"><br>");
    htmlBuilder.append("<label for=\"product-quantity\">Quantidade:</label>");
    htmlBuilder.append("<input type=\"text\" id=\"product-quantity\" name=\"estoque\"><br>");
    htmlBuilder.append("<input type=\"submit\" value=\"Adicionar Produto\">");
    htmlBuilder.append("</form>");
    htmlBuilder.append("</div>");
    htmlBuilder.append("</div>");
    htmlBuilder.append("</body>");
    htmlBuilder.append("</html>");

    return ResponseEntity.ok().body(htmlBuilder.toString());
}



    @PostMapping("/removerProduto")
    public String removerProduto(@RequestParam("id") int id) {
        try {
            Produto produto = new Produto();
            produto.setId(id);
            produtoDao.removerProduto(produto);
        } catch (SQLException e) {
            // Trate a exceção de acordo com o que for adequado para sua aplicação
            // Por exemplo, você pode registrar o erro, exibir uma mensagem de erro para o usuário, etc.
            System.err.println("Erro ao remover produto: " + e.getMessage());
        }
        return "redirect:/listaProdutos"; // Redireciona de volta para a página de lista de produtos
    }
    
}
