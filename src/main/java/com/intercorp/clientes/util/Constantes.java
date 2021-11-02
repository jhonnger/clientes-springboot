package com.intercorp.clientes.util;

public class Constantes {

    public static final String SCHEMA_CLIENTE = "clientes";

    public static final String SCHEMA_PARAMETROS = "parametros";

    public static final String ID_PARAMETROS = "valoresParaCalculo";

    public static final double ESPERANZA_EDAD_MAXIMA = 74.6;

    public static abstract class RESPUESTA_CONTROLADOR {

        public final static String ESTADO_ERROR = "error";
        public final static String ESTADO_EXITO = "exito";
    }
}
