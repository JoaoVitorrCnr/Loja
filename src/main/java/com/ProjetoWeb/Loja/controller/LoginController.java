package com.ProjetoWeb.Loja.controller;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import com.ProjetoWeb.Loja.Cliente;
import com.ProjetoWeb.Loja.Lojista;
import com.ProjetoWeb.Loja.dominio.ClienteDao;
import com.ProjetoWeb.Loja.dominio.LojistaDao;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class LoginController {

    
    private final ClienteDao clienteDao;
    private final LojistaDao lojistaDao;

    public LoginController(ClienteDao clienteDao, LojistaDao lojistaDao){
        this.clienteDao = clienteDao;
        this.lojistaDao = lojistaDao;
    }

    @PostMapping("/login")
    public String login (@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        Cliente cliente = new Cliente();
        Lojista lojista = new Lojista();
        cliente.setEmail(email);
        cliente.setSenha(password);
        lojista.setEmail(email);
        lojista.setSenha(password);
    
        try {
            Boolean clienteLoginSucesso = clienteDao.buscarCliente(cliente);
            
           
            if(clienteLoginSucesso){
                session.setAttribute("cliente", cliente);
                return "redirect:/listaProdutosC";
            }

            Boolean lojistaLoginSucesso = lojistaDao.buscarLojista(lojista);

            if(lojistaLoginSucesso){
                session.setAttribute("lojista", lojista);
                return "redirect:/listaProdutos";
            }
    
            return "redirect:/Login.html?error";
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            return "redirect:/Login.html?error";
        }
    }
   
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
     @GetMapping("/Logout")
    public String logout(HttpSession session) {
  
        session.invalidate();
        return "redirect:/Home.html";
    }

    @GetMapping("/cadastro")
    public String exibirPaginaCadastro() {
        return "Cadastro.html"; // Nome do arquivo HTML da página de cadastro
    }
}