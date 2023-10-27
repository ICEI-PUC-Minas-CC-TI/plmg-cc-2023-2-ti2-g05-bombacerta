package com.ti2cc.models;

import java.math.BigDecimal;

import org.json.JSONObject;

public class Posto {

    private String CPNJ;
    private String nome;
    private String marca;
    private double latitude;
    private double longitude;
    private BigDecimal Preco_gasolina;
    private BigDecimal Preco_alcool;

    public Posto(String cPNJ, String nome, String marca, double latitude, double longitude,
            BigDecimal preco_gasolina, BigDecimal preco_alcool) {
        CPNJ = cPNJ;
        this.nome = nome;
        this.marca = marca;
        this.latitude = latitude;
        this.longitude = longitude;
        Preco_gasolina = preco_gasolina;
        Preco_alcool = preco_alcool;
    }

    public String getCPNJ() {
        return CPNJ;
    }

    @Override
    public String toString() {
        return "Posto [CPNJ=" + CPNJ + ", nome=" + nome + ", marca=" + marca + ", latitude=" + latitude + ", longitude="
                + longitude + ", Preco_gasolina=" + Preco_gasolina + ", Preco_alcool=" + Preco_alcool + "]";
    }

    public void setCPNJ(String CPNJ) {
        this.CPNJ = CPNJ;
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

    public JSONObject toJsonObject(){
        JSONObject obj = new JSONObject();
        obj.put("cnpj", this.CPNJ);
        obj.put("nome", this.nome);
        obj.put("marca", this.marca);
        obj.put("lat", this.latitude);
        obj.put("lng", this.longitude);
        obj.put("preco_gasolina", this.Preco_gasolina);
        obj.put("preco_alcool", this.Preco_alcool);
        return obj;
    }

}
