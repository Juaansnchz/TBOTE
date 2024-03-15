package com.tbote.tbote;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "login")
public class DatosBBDD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id ;
    @Column(name = "password")
    private String passwd;
    @Column(name = "DNI")
    private String DNI;
    @Column(name = "Saldo")
    private int saldo;
    @Column(name = "respuesta_seg")
    private String respuesta;
    @OneToMany(mappedBy = "login", cascade = CascadeType.ALL)
    private List<Movimientos> listMovs;

    public DatosBBDD(){}
    public DatosBBDD(String DNI, String passwd, String respuesta) {
        this.DNI = DNI;
        this.passwd = passwd;
        this.respuesta = respuesta;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return"{" +
                "\"id\":\"" + id + "\"," +
                "\"DNI\":\"" + DNI + "\"," +
                "\"passwd\":" + passwd + '\"' +
                "\"Saldo\":" + saldo + '\"' +
                "\"respuesta_seg\":" + respuesta + '\"' +
                '}';
    }

    public List<Movimientos> getListMovs() {
        return listMovs;
    }

    public void setListMovs(List<Movimientos> listMovs) {
        this.listMovs = listMovs;
    }
}