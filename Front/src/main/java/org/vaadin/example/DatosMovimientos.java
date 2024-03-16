package org.vaadin.example;


import java.time.LocalDate;

public class DatosMovimientos {
    private Long id_movs;

    private String nombre;

    private float dinero;

    private LocalDate fecha;

    private int id;

    public DatosMovimientos() {
    }

    public Long getId_movs() {
        return id_movs;
    }

    public void setId_movs(Long id_movs) {
        this.id_movs = id_movs;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
