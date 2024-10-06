package org.algo3.modelo.proveedor;

import org.algo3.modelo.Chiste;
import java.nio.file.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ProveedorLocal implements Proveedor{
    Path rutaChistes = Paths.get("src", "main", "resources", "chistes.txt");

    
    @Override
    public Chiste solicitarChiste(String categoria, String idioma) {
        List<Chiste> chistesCoincidentes = new ArrayList<Chiste>();

        try {

            ClassLoader classLoader = getClass().getClassLoader();
            List<String> lineas = Files.readAllLines(rutaChistes);
            for (String linea : lineas) {
                String[] lineaDividida = linea.split(";");
                    String categoriaRecibida = lineaDividida[0];
                    String lenguajeRecibido = lineaDividida[1];
                    String setup = lineaDividida[2];
                    String delivery = lineaDividida[3];
                if(categoriaRecibida.equals(categoria) && lenguajeRecibido.equals(idioma)){
                    Chiste chiste = new Chiste(categoriaRecibida, setup, delivery);
                    chistesCoincidentes.add(chiste);
                }
            }
        } catch (NoSuchFileException e) {
           throw new ElArchivoNoExiste();
        } catch (IOException e){
            throw new ErrorDeLectura();
        }
       
        int indiceAleatorio = (int) (Math.random() * chistesCoincidentes.size());
        return chistesCoincidentes.get(indiceAleatorio);

    }


    
}
