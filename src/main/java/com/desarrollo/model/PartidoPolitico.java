package com.desarrollo.model;

public class PartidoPolitico {
    private int id;
    private String nombre;
    private String eslogan;
    private String presidente;
    private String secretario;
    private String tesorero;
    private String pais;
    private int numPresidentes;
    private int numGobernadores;
    private int numAlcaldes;
    private int numConcejales;
    private int numCongresistas;

    public PartidoPolitico() {}

    public PartidoPolitico(int id, String nombre, String eslogan, String presidente, String secretario, String tesorero, String pais, int numPresidentes, int numGobernadores, int numAlcaldes, int numConcejales, int numCongresistas) {
        this.id = id;
        this.nombre = nombre;
        this.eslogan = eslogan;
        this.presidente = presidente;
        this.secretario = secretario;
        this.tesorero = tesorero;
        this.pais = pais;
        this.numPresidentes = numPresidentes;
        this.numGobernadores = numGobernadores;
        this.numAlcaldes = numAlcaldes;
        this.numConcejales = numConcejales;
        this.numCongresistas = numCongresistas;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEslogan() { return eslogan; }
    public void setEslogan(String eslogan) { this.eslogan = eslogan; }
    public String getPresidente() { return presidente; }
    public void setPresidente(String presidente) { this.presidente = presidente; }
    public String getSecretario() { return secretario; }
    public void setSecretario(String secretario) { this.secretario = secretario; }
    public String getTesorero() { return tesorero; }
    public void setTesorero(String tesorero) { this.tesorero = tesorero; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public int getNumPresidentes() { return numPresidentes; }
    public void setNumPresidentes(int numPresidentes) { this.numPresidentes = numPresidentes; }
    public int getNumGobernadores() { return numGobernadores; }
    public void setNumGobernadores(int numGobernadores) { this.numGobernadores = numGobernadores; }
    public int getNumAlcaldes() { return numAlcaldes; }
    public void setNumAlcaldes(int numAlcaldes) { this.numAlcaldes = numAlcaldes; }
    public int getNumConcejales() { return numConcejales; }
    public void setNumConcejales(int numConcejales) { this.numConcejales = numConcejales; }
    public int getNumCongresistas() { return numCongresistas; }
    public void setNumCongresistas(int numCongresistas) { this.numCongresistas = numCongresistas; }
}