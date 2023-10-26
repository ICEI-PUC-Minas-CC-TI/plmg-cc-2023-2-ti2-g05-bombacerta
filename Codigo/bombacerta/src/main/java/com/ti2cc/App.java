package com.ti2cc;

import static spark.Spark.*;
import java.io.File;

import java.util.Scanner;

import com.ti2cc.service.PostoService;
import com.ti2cc.service.UsuarioService;

public class App {

    private static UsuarioService usuarioService = new UsuarioService();
    private static PostoService postoService = new PostoService();

    public static String getPage(String path) {
        String page = "";
        try {
            Scanner entrada = new Scanner(new File(path));
            while (entrada.hasNextLine()) {
                page += entrada.nextLine();
            }
            entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return page;
    }

    public static void main(String[] args) {
        port(1111);

        staticFiles.location("/public");
        get("/inicio", (request, response) -> {
            return App.getPage("Codigo\\bombacerta\\src\\main\\java\\com\\ti2cc\\resources\\public\\index.html");
        });

        post("/usuario/cadastro", (request, response) -> usuarioService.insert(request, response));

        post("usuario/login", (request, response) -> {
            int userId = (int) usuarioService.login(request, response);
            if (response.status() == 200) {
                 return (App.getPage("Codigo\\bombacerta\\src\\main\\java\\com\\ti2cc\\resources\\public\\login.html") +
                "<html><head></head><body><script>localStorage.setItem(\"logado\", \""+ userId + "\"); </script></body></html>");
            } else {
                response.redirect("/inicio/erroLogin");
            }
            return null;
        });

        get("/inicio/erroLogin", (request, response) -> {
            return (App.getPage("Codigo\\bombacerta\\src\\main\\java\\com\\ti2cc\\resources\\public\\index.html")) +
                    "<html><head></head><body><script> alert('E-mail ou senha incorretos.'); window.location.href = 'http://localhost:1111/inicio'    </script></body></html>";
        });

        get("/posto/cadastro", (request, response) -> {
            return App.getPage("Codigo\\bombacerta\\src\\main\\java\\com\\ti2cc\\resources\\public\\addPosto.html");
        });

        post("/posto/cadastro", (request, response) -> {
            return postoService.insert(request, response);
        });
    }
}
