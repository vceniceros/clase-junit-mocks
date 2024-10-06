package org.algo3.modelo;

import java.util.Objects;

public class Chiste {
    private String categoria;
    private String setup;
    private String delivery;

    private int puntaje;

    public Chiste(String categoria, String delivery, String setup){
        this.categoria = categoria;
        this.delivery = delivery;
        this.setup = setup;
        this.puntaje = 0;
    }

    public void aplicarPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public boolean tieneCategoria(String c) {
        return Objects.equals(this.categoria, c);
    }

    public boolean esMalo() {
        return this.puntaje < 5;
    }

    public int compararCon(Chiste otroChiste) {
        //aclaracion: sort por algun motivo espera 1 o -1
        
        int comparador; 
        if(this.puntaje > otroChiste.puntaje){
            comparador = 1;
        }else{
            comparador = -1;
        }
        
       return comparador;
    }
}
