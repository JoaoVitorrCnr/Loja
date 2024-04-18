package com.ProjetoWeb.Loja.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ProjetoWeb.Loja.Cliente;
import com.ProjetoWeb.Loja.dominio.ClienteDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ClienteController {

    @RequestMapping(value = "/CadastroCliente", method = RequestMethod.POST)
    public void CadastroCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);


        ClienteDao clienteDao = new ClienteDao();

        clienteDao.cadastrarCliente(cliente);

        response.sendRedirect("Login.html");

    }
}

