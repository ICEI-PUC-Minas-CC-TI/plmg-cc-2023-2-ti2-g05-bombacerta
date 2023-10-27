package com.ti2cc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ti2cc.dao.PostoDAO;

import com.ti2cc.models.Posto;

import spark.Request;
import spark.Response;

public class PostoService {

    /**
     * Faz uma chamada para uma API através de uma URL e retorna a resposta em formato de String.
     * @param apiUrl a URL da API que será chamada
     * @return a resposta da API em formato de String, ou "erro" caso ocorra algum erro na chamada
     */
    private static String callApi(String apiUrl) {
        try {
            URI uri = new URI(apiUrl);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            connection.disconnect();
            return response.toString();

        } catch (Exception e) {
            return "erro";
        }
    }
    private PostoDAO postoDAO = new PostoDAO();

    public Object insert(Request request, Response response) {
        System.out.println("inserting");
        String resp = "oii";
        System.out.println(request.queryParams());
        String cnpj = request.queryParams("cnpj");
        String nome = request.queryParams("nome");
        String marca = request.queryParams("marca");
        String endereco = request.queryParams("endereco");
        System.out.println(nome + marca + cnpj);

        // converter endereco pra sintaxe de url:
        endereco = endereco.replace(" ", "+");
        endereco = endereco.replace(",", "%2C");
        endereco = endereco.replace("º", "%C2%BA");
        endereco = endereco.replace("ª", "%C2%AA");
        endereco = endereco.replace("ç", "%C3%A7");
        String mapsUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + endereco
                + "&key=AIzaSyDB-hseTvhV9kWK7vBW8xzqA4Trq1TIzgk";

        JSONObject json = new JSONObject(callApi(mapsUrl));

        JSONObject json2 = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry")
                .getJSONObject("location");
        Double lat = json2.getDouble("lat");
        Double lng = json2.getDouble("lng");
        BigDecimal preco_gasolina = new BigDecimal(request.queryParams("preco_gasolina"));
        BigDecimal preco_alcool = new BigDecimal(request.queryParams("preco_alcool"));


        System.out.println(cnpj + " " + nome + " " + marca + " " + lat + " " + lng +
        " " + preco_gasolina + " " + preco_alcool);

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

    public String getAllPostos(Request request, Response response) {
        List<Posto> postos = postoDAO.getAll();

        JSONArray jsonArray = new JSONArray();

        for (Posto posto : postos) {
            JSONObject jsonObject = posto.toJsonObject();
            jsonArray.put(jsonObject);
        }

        String jsonString = jsonArray.toString();

        return jsonString;
    }

    public String getByCNPJ(Request request, Response response) {
        String cnpj = request.queryParams("cnpj");
        Posto posto = postoDAO.getByCNPJ(cnpj);
    
        if (posto != null) {
           return posto.toJsonObject().toString();
        } else {
            response.status(500); 
            return "Posto com CNPJ " + cnpj + " não encontrado.";
        }
    }
    
    

}
