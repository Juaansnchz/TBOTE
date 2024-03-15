package com.tbote.tbote;

import jakarta.persistence.*;

@Entity
@Table(name = "movs")
public class Movimientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movs")
    private Long id_movs;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "dinero")
    private float dinero;
    @ManyToOne
    @JoinColumn(name = "ID", referencedColumnName = "login")
    private DatosBBDD tablaLogin;


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

    public DatosBBDD getTablaLogin() {
        return tablaLogin;
    }

    public void setTablaLogin(DatosBBDD tablaLogin) {
        this.tablaLogin = tablaLogin;
    }
}
