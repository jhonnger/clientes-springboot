package com.intercorp.clientes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ClienteKpiDto {

    private double promedio;

    private double varianza;

    private double desviacion;

    private double cantidadRegistros;

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @JsonIgnore
    public double getVarianza() {
        return varianza;
    }

    public void setVarianza(double varianza) {
        this.varianza = varianza;
    }

    public double getDesviacion() {
        return desviacion;
    }

    public void setDesviacion(double desviacion) {
        this.desviacion = desviacion;
    }

    @JsonIgnore
    public double getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(double cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }
}

