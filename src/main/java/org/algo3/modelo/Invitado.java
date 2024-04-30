package org.algo3.modelo;
import java.util.Random;

public class Invitado {
    public int puntuar(Chiste chiste) {
        Random random = new Random();

        return random.nextInt(10) + 1;
    }
}
