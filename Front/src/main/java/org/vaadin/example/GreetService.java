package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GreetService implements Serializable {



    public String createUsuario(String dni, String passwd, String respuesta) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.createUsuario(dni, passwd, respuesta);
    }

    public String loginUsuario(String dni, String passwd) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.login(dni, passwd);
    }

    public String updateUsuario(String DNI, String new_passwd, String respuesta) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.updateUsuario(DNI,new_passwd, respuesta);

    }
    public String getUsuario(String DNI) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.getUsuario(DNI);

    }
    public String getSaldo(String DNI) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.saldo(DNI);

    }

    public List<DatosMovimientos> getMovs(String DNI){
        API api = new API();
        return api.movimientos(DNI);
    }
}

