package com.ti2cc;
import static spark.Spark.*;

import com.ti2cc.service.UsuarioService;

public class App 
{

    private static UsuarioService usuarioService = new UsuarioService();
    public static void main( String[] args )
    {
        port(6789);
        
        staticFiles.location("/public");

        // post("/produto/insert", (request, response) -> produtoService.insert(request, response));
        post("usuario/cadastro", (request, response) -> usuarioService.insert(request, response));
    }
}
