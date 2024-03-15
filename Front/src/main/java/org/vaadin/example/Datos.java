package org.vaadin.example;

public class Datos {
    private Long id;
    private String DNI ;
    private String passwd;
    private String respuesta;
    private int saldo;


    private String confirmPassword;

    public Datos(){

    }

    public Datos(String DNI, String passwd, String respuesta) {
        this.DNI = DNI;
        this.passwd = passwd;
        this.respuesta = respuesta;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public Long getId() {
        return id;
    }
    public String getDNI() {
        return DNI;
    }

    public String getPasswd() {
        return passwd;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
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



}
