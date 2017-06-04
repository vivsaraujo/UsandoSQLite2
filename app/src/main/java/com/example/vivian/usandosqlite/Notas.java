package com.example.vivian.usandosqlite;

public class Notas {

    private int _id;
    private String nomeDisciplina;
    private double nota;

    public Notas() {

    }

    public Notas(int _id, String nomeDisciplina, double nota) {
        this._id = _id;
        this.nomeDisciplina = nomeDisciplina;
        this.nota = nota;
    }

    public Notas(String nomeDisciplina, double nota) {
        this.nomeDisciplina = nomeDisciplina;
        this.nota = nota;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
