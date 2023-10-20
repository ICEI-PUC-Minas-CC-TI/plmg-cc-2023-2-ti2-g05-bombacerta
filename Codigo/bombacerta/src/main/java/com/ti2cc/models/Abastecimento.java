package com.ti2cc.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Abastecimento {
    private int id;

    private int id_posto;
    private int id_usuario;
    private BigDecimal preco;
    private String litros;
    private Tipo tipo;
    private LocalDate data; 

    public Abastecimento(int id_posto, int id_usuario, BigDecimal preco, String litros, Tipo tipo, LocalDate data) {
        this.id_posto = id_posto;
        this.id_usuario = id_usuario;
        this.preco = preco;
        this.litros = litros;
        this.tipo = tipo;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_posto() {
        return id_posto;
    }

    public void setId_posto(int id_posto) {
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

    public String getLitros() {
        return litros;
    }

    public void setLitros(String litros) {
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
