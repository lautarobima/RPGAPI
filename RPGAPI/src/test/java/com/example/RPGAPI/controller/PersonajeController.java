package com.example.RPGAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.RPGAPI.model.Personaje;
import com.example.RPGAPI.repository.DatosJuego;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.xml.crypto.Data;


@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    // GET personajes (todos) o por nombre/clase 
    @GetMapping
    public ResponseEntity<List<Personaje>> obtenerPersonajes(
    @RequestParam(required = false) String nombre, @RequestParam(required = false) String tipo) {

        List<Personaje> personajes;
        if (nombre != null && tipo != null) {
            personajes = DatosJuego.Personajes.stream()
                .filter(p -> p.getNombre()
                    .toLowerCase()
                    .contains(nombre.toLowerCase()))
                .filter(p -> p.getTipo()
                    .toLowerCase()
                    .contains(tipo.toLowerCase()))
                .toList();
        }
        else if (nombre != null) {
            personajes = DatosJuego.Personajes.stream()
                .filter(p -> p.getNombre()
                    .toLowerCase()
                    .contains(nombre.toLowerCase()))
                .toList();
        }
        else if (tipo != null) {
            personajes = DatosJuego.Personajes.stream()
                .filter(p -> p.getTipo()
                    .toLowerCase()
                    .contains(tipo.toLowerCase()))
                .toList();
        }
        else
            return new ResponseEntity<>(DatosJuego.Personajes, HttpStatus.OK);

        if(personajes.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(personajes, HttpStatus.OK);
        
    }

    // GET personaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<Personaje> obtenerPorId(@PathVariable int id) {
        
        Personaje personaje = DatosJuego.Personajes.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
        if (personaje == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(personaje, HttpStatus.OK);
    }

    // POST personaje
    @PostMapping
    public ResponseEntity<Personaje> postPresonaje(@RequestBody String data) {
        Personaje newPersonaje = crearPersonajeDesdeJson(data.toString());
        DatosJuego.Personajes.add(newPersonaje);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private static Personaje crearPersonajeDesdeJson(String data) {
        String[] parts = new String[7];
        List<Integer> habilidades = new java.util.ArrayList<>();
        // To JSON to parts of class Personaje
        int startIndex = 0, endIndex, partIndex = 0;
        for (int i = 2; i < data.length(); i++) {
            if (data.charAt(i) == ':' && data.charAt(i+1) == '"')
                startIndex = i+1;
            else if (data.charAt(i) == ':')
                startIndex = i;
            else if (data.charAt(i) == ',' && data.charAt(i-1) == '"') {
                endIndex = i;
                parts[partIndex++] = data.substring(startIndex+1, endIndex-1);
            }
            else if(data.charAt(i) == ','){
                endIndex = i;
                parts[partIndex++] = data.substring(startIndex+1, endIndex);
            }
            // para la lista de habilidades
            else if(data.charAt(i) == '['){
                for (int j = i+1; j < data.length() && data.charAt(j) != ']'; j++)
                    if (data.charAt(j) != ' ' && data.charAt(j) != ',')
                        habilidades.add(Character.getNumericValue(data.charAt(j)));
                break;
            }
        }

        return new Personaje(
            DatosJuego.Personajes.size() + 1,
            parts[0],
            parts[1],
            parts[2],
            Integer.parseInt(parts[3]),
            Integer.parseInt(parts[4]),
            Integer.parseInt(parts[5]),
            habilidades
        );
    }

    // Delete personaje
    @DeleteMapping("/{id}")
    public ResponseEntity<Personaje> eliminarPersonaje(@PathVariable int id) {
        Personaje personaje = DatosJuego.Personajes.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
        if (personaje == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        DatosJuego.Personajes.remove(personaje);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
