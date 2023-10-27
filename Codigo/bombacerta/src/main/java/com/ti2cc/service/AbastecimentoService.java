package com.ti2cc.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ti2cc.dao.AbastecimentoDAO;
import com.ti2cc.models.Abastecimento;
import com.ti2cc.models.Posto;
import com.ti2cc.models.Tipo;

import spark.Request;
import spark.Response;

public class AbastecimentoService {
    
    private AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO();

    public Object insert(Request request, Response response) {
        //converter pra localdate:
        LocalDate dataInserida = LocalDate.parse(request.queryParams("data"));
        String tipo = request.queryParams("combustivel");
        Tipo tipoInserido;
        if(tipo == "Gasolina"){
            tipoInserido = Tipo.GASOLINA;
        } else {
            tipoInserido = Tipo.ALCOOL;
        }
        double valorLitro = Double.parseDouble(request.queryParams("valor-litro"));
        double total = Double.parseDouble(request.queryParams("total"));
        BigDecimal totalInserido = BigDecimal.valueOf(total);
        double litros = total/valorLitro;
        int userId = Integer.parseInt(request.queryParams("userid"));
        System.out.println(dataInserida + " " + tipoInserido + " " + valorLitro + " " + total + " " + userId); // Log de teste
        

        Abastecimento abastecimento = new Abastecimento("0193509247", userId, totalInserido, litros, tipoInserido, dataInserida);

        if(abastecimentoDAO.insert(abastecimento)){
            response.status(201); // 201 Created
            return "Abastecimento inserido com sucesso";
        } else {
            response.status(500); // Internal Server Error
            return "Erro ao inserir abastecimento";
        }
    }


    public String getAllAbastecimentos(Request request, Response response) {
        int userid = Integer.parseInt(request.queryParams("userid"));
        List<Abastecimento> abastecimentos = abastecimentoDAO.getAll(userid);

        JSONArray jsonArray = new JSONArray();

        for (Abastecimento abastecimento : abastecimentos) {
            JSONObject jsonObject = abastecimento.toJsonObject();
            jsonArray.put(jsonObject);
        }

        String jsonString = jsonArray.toString();

        return jsonString;
    }
}
