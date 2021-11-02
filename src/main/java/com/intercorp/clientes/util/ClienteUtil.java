package com.intercorp.clientes.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.clientes.dto.EdadDto;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

import static com.intercorp.clientes.util.Constantes.ESPERANZA_EDAD_MAXIMA;

public class ClienteUtil {

    public static EdadDto obtenerEdadDesdeFechaNac(Date fecha) {
        Date hoy;
        int anioHoy, mesHoy, diaHoy;
        int anioNac, mesNac, diaNac;
        int anios, meses;
        Calendar calendar;

        calendar= Calendar.getInstance();

        meses = 0;
        hoy = new Date();
        calendar.setTime(hoy);

        anioHoy = calendar.get(Calendar.YEAR);
        mesHoy = calendar.get(Calendar.MONTH) + 1;
        diaHoy = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(fecha);

        anioNac = calendar.get(Calendar.YEAR);
        mesNac = calendar.get(Calendar.MONTH) + 1;
        diaNac = calendar.get(Calendar.DAY_OF_MONTH);

        anios = (anioHoy + 1900) - anioNac;
        if (mesHoy < mesNac) {
            anios--;
        }
        if ((mesNac == mesHoy) && (diaHoy < diaNac)) {
            anios--;
        }
        if (anios >= 1900) {
            anios -= 1900;
        }

        if (mesHoy > mesNac && diaNac > diaHoy) {
            meses = mesHoy - mesNac - 1;
        } else if (mesHoy > mesNac) {
            meses = mesHoy - mesNac;
        }

        if (mesHoy < mesNac && diaNac <= diaHoy) {
            meses = 12 - (mesNac - mesHoy);
        } else if (mesHoy < mesNac) {
            meses = 12 - (mesNac - mesHoy + 1);
        }

        if (mesHoy == mesNac && diaNac > diaHoy) {
            meses = 11;
        }
        if (anioHoy < anioNac || (anioHoy == anioNac && mesHoy < mesNac)) {
            anios = 0;
            meses = 0;
        }

        return new EdadDto(anios, meses, anios + " años, " + meses + " meses");
    }

    public static boolean esCadenaValida(String cadena){
        return cadena != null  && !cadena.trim().equals("");
    }

    public static boolean esFechaValida(Date fecha){
        Date hoy;

        hoy = new Date();

        return hoy.after(fecha);
    }

    public static Date obtenerFechaProbableMuerte(Date fechaNac){
        EdadDto edad;
        Calendar fecha;
        int mesesEsperanzaMax;
        int mesesCumplidos;
        int mesesFaltantes;

        fecha  = Calendar.getInstance();
        edad = obtenerEdadDesdeFechaNac(fechaNac);

        mesesEsperanzaMax = (int) (ESPERANZA_EDAD_MAXIMA  * 12 + (ESPERANZA_EDAD_MAXIMA % 1) * 12);
        mesesCumplidos = edad.getAnios() * 12 + edad.getMeses();

        mesesFaltantes = mesesEsperanzaMax - mesesCumplidos;

        fecha.add(Calendar.MONTH, mesesFaltantes);

        return fecha.getTime();
    }

    public static String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = new ErrorMessage("01", errors);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
