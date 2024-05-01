package org.algo3.modelo.tiempo;

import java.time.LocalDate;

public class TiempoProductivo implements Tiempo{
    public int obtenerDiaDeHoy(){
        LocalDate hoy = LocalDate.now();
        return hoy.getDayOfMonth();
    }
}
