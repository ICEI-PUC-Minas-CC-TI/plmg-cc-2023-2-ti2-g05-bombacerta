package com.ti2cc.models;

import java.math.BigDecimal;

public class Posto {

    private String CPNJ;
    private String posto;
    private String nome;
    private String marca;
    private double latitude; 
    private double longitude;
    private BigDecimal Preco_gasolina;
    private BigDecimal Preco_alcool;


    public String getCPNJ() {
        return CPNJ;
    }

    public void setCPNJ(String CPNJ) {
        this.CPNJ = CPNJ;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getPreco_gasolina() {
        return Preco_gasolina;
    }

    public void setPreco_gasolina(BigDecimal preco_gasolina) {
        Preco_gasolina = preco_gasolina;
    }

    public BigDecimal getPreco_alcool() {
        return Preco_alcool;
    }

    public void setPreco_alcool(BigDecimal preco_alcool) {
        Preco_alcool = preco_alcool;
    }

}
