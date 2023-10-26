package com.ti2cc.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.Scanner;

import com.ti2cc.dao.PostoDAO;
import com.ti2cc.models.Posto;


import spark.Request;
import spark.Response;

public class PostoService {
    private PostoDAO postoDAO = new PostoDAO();

    public Object insert (Request request, Response response){  
        System.out.println("inserting");
        String resp;
        String cnpj = request.queryParams("cnpj");
        String nome = request.queryParams("nome");
        String marca = request.queryParams("marca");
        Double lat = Double.parseDouble(request.queryParams("lat"));
        Double lng = Double.parseDouble(request.queryParams("lng"));
        BigDecimal preco_gasolina = new BigDecimal(request.queryParams("preco_gasolina"));
        BigDecimal preco_alcool = new BigDecimal(request.queryParams("preco_alcool"));
        System.out.println(cnpj + " " + nome + " " + marca + " " + lat + " " + lng + " " + preco_gasolina + " " + preco_alcool);

        Posto posto = new Posto(cnpj, nome, marca, lat, lng, preco_gasolina, preco_alcool);
        if(postoDAO.insert(posto)){
            response.status(200);
            resp = "Posto inserido com sucesso";
        } else {
            response.status(500);
            resp = "Erro ao inserir posto";
        }
        System.out.println(resp);
        return resp;

    }  

        
}
