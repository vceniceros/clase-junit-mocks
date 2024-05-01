package org.algo3.modelo.invitado;

import org.algo3.modelo.Chiste;

import java.util.Random;

public class InvitadoRandom implements Invitado {
    public int puntuar(Chiste chiste) {
        Random random = new Random();

        return random.nextInt(10) + 1;
    }
}
