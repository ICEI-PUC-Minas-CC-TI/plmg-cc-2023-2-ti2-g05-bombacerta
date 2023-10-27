package com.ti2cc;

import static spark.Spark.*;
import java.io.File;

import java.util.Scanner;

import com.ti2cc.service.AbastecimentoService;
import com.ti2cc.service.PostoService;
import com.ti2cc.service.UsuarioService;

public class App {

    private static UsuarioService usuarioService = new UsuarioService();
    private static PostoService postoService = new PostoService();
    private static AbastecimentoService abastecimentoService = new AbastecimentoService();

 
    /**
     * Retorna o conteúdo de uma página a partir do caminho especificado.
     * 
     * @param path O caminho da página a ser lida.
     * @return O conteúdo da página lida.
     */
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

    /**
     * Método responsável por iniciar a página de início da aplicação.
     * @return página HTML da página de início.
     */
    public static void inicioPage(){
        get("/inicio", (request, response) -> {
            return App.getPage("Codigo\\bombacerta\\src\\main\\java\\com\\ti2cc\\resources\\public\\index.html");
        });
    }

    /**
     * Método responsável por definir as rotas do usuário.
     * 
     * @return void
     */
    public static void usuariosActions(){
        post("/usuario/cadastro", (request, response) -> usuarioService.insert(request, response));

        // Redireciona para abastecimentos:
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
    }

    /**
     * Método responsável por definir as rotas de postos.
     * 
     * @return void
     */
    public static void postoActions(){
        get("/posto/cadastro", (request, response) -> {
            return App.getPage("Codigo\\bombacerta\\src\\main\\java\\com\\ti2cc\\resources\\public\\addPosto.html");
        });

        post("/posto/cadastro", (request, response) -> {
            return postoService.insert(request, response);
        });

        get("/postos", (request, response) -> {
            return postoService.getAllPostos(request, response);
        });

        get("/posto/ver", (request, response) -> {
            return App.getPage("Codigo\\bombacerta\\src\\main\\java\\com\\ti2cc\\resources\\public\\gasolina.html");
        });
    }

    public static void abastecimentoActions(){
        post("/usuario/abastecimento/cadastro", (request, response) -> {
            return abastecimentoService.insert(request, response);
        });

        get("/usuario/abastecimentos", (request, response) -> {
            return abastecimentoService.getAllAbastecimentos(request, response);
        });
    }

    /**
     * Método principal da aplicação.
     * 
     * @param args
     */
    public static void main(String[] args) {
        port(1111);

        staticFiles.location("/public");
        
        inicioPage();

        usuariosActions();

        postoActions();

       abastecimentoActions();
    }
}
