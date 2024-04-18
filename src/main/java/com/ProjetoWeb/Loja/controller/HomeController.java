package com.ProjetoWeb.Loja.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Home.html"; // Este é o nome do arquivo HTML que você quer retornar
    }
}
