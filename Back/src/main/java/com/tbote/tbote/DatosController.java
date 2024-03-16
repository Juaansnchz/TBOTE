package com.tbote.tbote;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class DatosController {
    @PostMapping("/usuario")
    public DatosBBDD user(@RequestBody Map<String, String> body){
        String dni = body.get("dni");
        DataHandling control = new DataHandling();
        DatosBBDD user = control.getUsuario(dni);
        return user;
    }

    @PostMapping(path = "/registro",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DatosBBDD> create(@RequestBody DatosBBDD newUser){
        DataHandling control = new DataHandling();
        boolean bool = control.createUsuario(newUser.getDNI(), newUser.getPasswd(), newUser.getRespuesta());
        if (bool == true) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DatosBBDD> comprobar(@RequestBody DatosBBDD newUser){
        DataHandling control = new DataHandling();
        newUser = control.autenticarUsuario(newUser.getDNI(), newUser.getPasswd());
        if (newUser != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/newpasswd",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DatosBBDD update(@RequestBody DatosBBDD newUser){
        DataHandling control = new DataHandling();
        control.updateUsuario(newUser.getDNI(), newUser.getPasswd(), newUser.getRespuesta());
        return newUser;
    }

    @PostMapping(path = "/versaldo")
    public int saldo(@RequestBody Map<String, String> body) {
        String dni = body.get("dni");
        DataHandling control = new DataHandling();
        return control.verSaldo(dni);
    }

    @PostMapping(path = "/vermovs")
    public ResponseEntity<String> verMovs(@RequestBody Map<String, String> body) {
        String dni = body.get("dni");
        DataHandling control = new DataHandling();
        return ResponseEntity.ok(control.verMovimientos(dni));
    }

}
