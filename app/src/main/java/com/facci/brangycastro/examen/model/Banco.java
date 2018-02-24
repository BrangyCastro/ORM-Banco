package com.facci.brangycastro.examen.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by brangycastro on 24/2/18.
 */

@Table(name = "banco")

public class Banco extends Model {

    @Column(name = "cedula")
    public String cedula;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "saldo")
    public String saldo;

    public Banco(String cedula, String nombre, String saldo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public Banco() {
        super();
    }

    // Metodo que nos sirve para leer todos los datos guardado en la base de dato
    public List<Banco> leerTodos(){
        return new Select().from(Banco.class).execute();
    }

    // Metodo que nos sirve para buscar por medio del nombre un muestra los datos
    public List<Banco> leerNombre(String nombre){
        return new Select().from(Banco.class).where("nombre",nombre).execute();
    }

    // Metodo que nos sirve para buscar por medio del cedula un muestra los datos
    public static Banco leerCedula(String cedula){
        return new Select().from(Banco.class).where("cedula = ?", cedula).executeSingle();
    }
}
