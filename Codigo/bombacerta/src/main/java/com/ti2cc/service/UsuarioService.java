package com.ti2cc.service;

import java.io.File;
import java.util.Scanner;

import com.ti2cc.dao.UsuarioDAO;
import com.ti2cc.models.Usuario;

import spark.Request;
import spark.Response;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private String form;

    public void makeFormLogin() {
        String nomeArquivo = "index.html";
        form = "";
        try {
            Scanner entrada = new Scanner(new File(nomeArquivo));
            while (entrada.hasNext()) {
                form += (entrada.nextLine() + "\n");
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(Request request, Response response){
        String resp;
        String nome = request.queryParams("nome");
        String email = request.queryParams("email");
        String senha = request.queryParams("senha");
        Usuario usuario = new Usuario(nome, senha, email);
        if(usuarioDAO.insert(usuario) == true){
            response.status(200);
            resp = "Usuário inserido com sucesso";
        } else {
            response.status(500);
            resp = "Erro ao inserir usuário";
        }
        makeFormLogin();
    }
}
