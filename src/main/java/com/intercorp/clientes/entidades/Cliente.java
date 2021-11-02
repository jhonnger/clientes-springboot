package com.intercorp.clientes.entidades;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

public class Cliente {

    private String id;

    @NotEmpty(message = "El nombre no puede ser vacío")
    private String nombre;

    @NotEmpty(message = "El apellido no puede ser vacío")
    private String apellido;

    @PositiveOrZero(message = "La edad no puede ser negativa")
    private int edad;

    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    @NotNull(message = "Debe ingresar la fecha de nacimiento")
    private Date fechaNacimiento;

    public Cliente() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
