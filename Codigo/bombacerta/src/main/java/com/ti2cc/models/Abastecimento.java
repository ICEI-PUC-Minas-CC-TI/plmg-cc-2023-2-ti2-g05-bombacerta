package com.ti2cc.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.json.JSONObject;

public class Abastecimento {
    private int id;

    private String id_posto; //cnpj
    private int id_usuario;
    private BigDecimal preco;
    private double litros;
    private Tipo tipo;
    private LocalDate data; 

    public Abastecimento(String id_posto, int id_usuario, BigDecimal preco, double litros, Tipo tipo, LocalDate data) {
        this.id_posto = id_posto;
        this.id_usuario = id_usuario;
        this.preco = preco;
        this.litros = litros;
        this.tipo = tipo;
        this.data = data;
    }

    public JSONObject toJsonObject(){
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("id_posto", this.id_posto);
        json.put("id_usuario", this.id_usuario);
        json.put("preco", this.preco);
        json.put("litros", this.litros);
        json.put("tipo", this.tipo);
        json.put("data", this.data);
        return json;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_posto() {
        return id_posto;
    }

    public void setId_posto(String id_posto) {
        this.id_posto = id_posto;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if(tipo.equals("gasolina")){
            this.tipo = Tipo.GASOLINA;
        } else {
            this.tipo = Tipo.ALCOOL;
        }
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
