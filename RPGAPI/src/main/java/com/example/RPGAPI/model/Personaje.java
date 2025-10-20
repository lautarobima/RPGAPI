package com.example.RPGAPI.model;

import java.util.List;

public class Personaje {
    private int id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private int atk;
    private int def;
    private int sp;
    private List<Integer> habilidades;

    public Personaje(int id, String nombre, String tipo, String descripcion, int atk, int def, int sp, List<Integer> habilidades) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.atk = atk;
        this.def = def;
        this.sp = sp;
        this.habilidades = habilidades;
    }

    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getTipo() {return tipo;}
    public String getDescripcion() {return descripcion;}
    public int getAtk() {return atk;}
    public int getDef() {return def;}
    public int getSp() {return sp;}
    public List<Integer> getHabilidades() {return habilidades;}
}
