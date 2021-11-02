package com.intercorp.clientes.dto;

import java.util.Date;

public class ClienteDto {

    private String nombre;

    private String apellido;

    private int edad;

    private Date fechaNacimiento;

    private Date fechaProbableMuerte;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaProbableMuerte() {
        return fechaProbableMuerte;
    }

    public void setFechaProbableMuerte(Date fechaProbableMuerte) {
        this.fechaProbableMuerte = fechaProbableMuerte;
    }
}
