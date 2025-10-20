package com.example.RPGAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.RPGAPI.model.Habilidad;
import com.example.RPGAPI.model.Personaje;
import com.example.RPGAPI.repository.DatosJuego;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/habilidades")
public class HabilidadController {

    // GET Habilidad (todas) o ordenados por SP
    @GetMapping
    public ResponseEntity<List<Habilidad>> obtenerHabilidades(
    @RequestParam(required = false) String orden) {

        List<Habilidad> habilidades;
        if(orden != null){
            if (orden.equals("stamina")) {
                habilidades = DatosJuego.Habilidades;
                for (int i = 0; i < habilidades.size(); i++)
                    for (int j = i+1; j < habilidades.size(); j++) {
                        if (habilidades.get(i).getSp() < habilidades.get(j).getSp()){
                            Habilidad temp = habilidades.get(i);
                            habilidades.set(i, habilidades.get(j));
                            habilidades.set(j, temp);
                        }
                    }
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else
            return new ResponseEntity<>(DatosJuego.Habilidades, HttpStatus.OK);
        return new ResponseEntity<>(habilidades, HttpStatus.OK);
    }

    // GET Habilidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Habilidad> obtenerPorId(@PathVariable int id) {
        
        Habilidad habilidad = DatosJuego.Habilidades.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
        if (habilidad == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(habilidad, HttpStatus.OK);
    }


    // PUT nueva Habilidad
    @PutMapping("/{id}")
    public ResponseEntity<Habilidad> agregarHabilidad(@PathVariable int id, @RequestBody String data){
        Habilidad habilidad = crearHabilidadDesdeJson(id, data.toString());
        DatosJuego.Habilidades.set(id-1, habilidad);
        return new ResponseEntity<>(habilidad, HttpStatus.CREATED);
    }


    private Habilidad crearHabilidadDesdeJson(int id, String data) {
        String[] parts = new String[5];
        // To JSON to parts of class habilidad
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
            else if(data.charAt(i) == ',' || data.charAt(i) == '}'){
                endIndex = i;
                parts[partIndex++] = data.substring(startIndex+1, endIndex);
            }
        }

        return new Habilidad(
            id,
            parts[0],
            parts[1],
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3]),
            Integer.parseInt(parts[4])
        );
    }

}