package com.ti2cc.service;

import java.io.File;
import java.util.Scanner;

import com.ti2cc.dao.UsuarioDAO;

import com.ti2cc.models.Usuario;

import spark.Request;
import spark.Response;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public Object insert(Request request, Response response){
        System.out.println("inserting");
        String resp;
        String nome = request.queryParams("nome");
        String email = request.queryParams("email");
        String senha = request.queryParams("senha");
        System.out.println(nome + " " + email + " " + senha);
                Usuario usuario = new Usuario(nome, senha, email);
        if(usuarioDAO.insert(usuario) == true){
            response.status(200);
            resp = "Usuário inserido com sucesso";
        } else {
            response.status(500);
            resp = "Erro ao inserir usuário";
        }
        System.out.println(resp);
        return resp;
    }

    public Object login(Request request, Response response){
        System.out.println("login");
        String resp;
        String email = request.queryParams("email");
        String senha = request.queryParams("senha");
        System.out.println(email + " " + senha);
        int userId = usuarioDAO.login(email, senha);
        if( userId != -1){
            response.status(200);

        } else {
            response.status(500);
 
        }

        return userId;
    }

}
