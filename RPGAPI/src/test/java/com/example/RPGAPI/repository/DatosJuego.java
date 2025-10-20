package com.example.RPGAPI.repository;

import com.example.RPGAPI.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatosJuego {

    public static List<Habilidad> Habilidades = new ArrayList<>(Arrays.asList(
        new Habilidad(1, "Espadazo", "Un Ataque poderoso con la espada.", 10, 0, -5),
        new Habilidad(2, "Escudo de Hierro", "Aumenta la def del usuario.", 0, 15, -3),
        new Habilidad(3, "Bendición Mística", "Aumenta el sp del usuario.", 0, 0, 20),
        new Habilidad(4, "Furia del Dragón", "Un ataque devastador que sacrifica defensa por poder.", 20, -10, -10),
        new Habilidad(5, "Bola de huevo", "tira una bola con mucho huevo.", 12, -1, -7)
    ));

    public static List<Personaje> Personajes = new ArrayList<>(Arrays.asList(
        new Personaje(1, "Gagh-Ar", "Guerrero", "Un valiente luchador con gran fuerza fìsica.", 80, 70, 60, Arrays.asList(1,2)),
        new Personaje(2, "Elyra", "Mago", "Hechicera con gran dominio de la energìa màgica.", 65, 40, 90, Arrays.asList(3,5)),
        new Personaje(3, "Santiago-Son", "Samurai", "El golden boy que siempre salva el dia", 75, 65, 70, Arrays.asList(1,4)),
        new Personaje(4, "NikoNi", "Mafioso", "Un bueno para nada que se quiere parecer a Kiryu kazuma", 50, 105, 80, Arrays.asList(2,4)),
        new Personaje(5, "Lautiti", "Mago", "Conocido por su increible pelo rubio y tambien conocido por el nombre: Tifanny", 70, 20, 100, Arrays.asList(3,5))
    ));

}
