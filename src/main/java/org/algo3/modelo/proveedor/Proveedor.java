package org.algo3.modelo.proveedor;

import org.algo3.modelo.Chiste;

public interface Proveedor {
    Chiste solicitarChiste(String categoria, String idioma);
}
