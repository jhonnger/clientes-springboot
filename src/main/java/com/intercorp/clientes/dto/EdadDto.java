package com.intercorp.clientes.dto;

public class EdadDto {

    private int anios;

    private int meses;

    private String format;

    public EdadDto(int anios, int meses, String format) {
        this.anios = anios;
        this.meses = meses;
        this.format = format;
    }

    public int getAnios() {
        return anios;
    }

    public void setAnios(int anios) {
        this.anios = anios;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
