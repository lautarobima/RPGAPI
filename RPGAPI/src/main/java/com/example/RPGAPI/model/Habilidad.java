package com.example.RPGAPI.model;

public class Habilidad {
    private int id;
    private String nombre;
    private String descripcion;
    private int incrementoAtk;
    private int incrementoDef;
    private int incrementoSp;

    public Habilidad(int id, String nombre, String descripcion, int incrementoAtk, int incrementoDef, int incrementoSp) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.incrementoAtk = incrementoAtk;
        this.incrementoDef = incrementoDef;
        this.incrementoSp = incrementoSp;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getIncrementoAtk() { return incrementoAtk; }
    public int getIncrementoDef() { return incrementoDef; }
    public int getSp() { return incrementoSp; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setIncrementoAtk(int incrementoAtk) { this.incrementoAtk = incrementoAtk; }
    public void setIncrementoDef(int incrementoDef) { this.incrementoDef = incrementoDef; }
    public void setSp(int sp) { this.incrementoSp = incrementoSp; }
}


