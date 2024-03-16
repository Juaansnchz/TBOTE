package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

@Route("saldo")
public class Saldo extends VerticalLayout {
    public Saldo(@Autowired GreetService service) {

        getStyle().set("background-color","rgba(40, 167, 69, 0.5)");
        VerticalLayout contenedorMov = new VerticalLayout();
        HorizontalLayout Subtitulo = new HorizontalLayout();
        contenedorMov.addClassName("contenedorMov");


        H1 titulo = new H1("Tbote");
        titulo.addClassName("titulo");
        add(titulo);

        H2 subtitulo = new H2("Saldo");
        subtitulo.addClassName("Saldo");
        Subtitulo.add(subtitulo);
        contenedorMov.add(Subtitulo);

        try {
            // Obtener el saldo para el DNI ingresado
            String dni = (String) UI.getCurrent().getSession().getAttribute("dni");
            String versaldo = service.getSaldo(dni);
            H4 saldo = new H4(versaldo + "€");
            saldo.addClassName("saldoN");
            contenedorMov.add(saldo);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Notification.show("Error al obtener el saldo: " + ex.getMessage());
        }

        Button VolverPost = new Button("Volver");
        VolverPost.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(PostLogin.class));
            Notification.show("Volver atrás pulsado");
        });
        contenedorMov.add(VolverPost);




        // Agregar contenedor de registro al diseño principal
        add(contenedorMov);
        setAlignItems(Alignment.CENTER);
        setSizeFull();



        add(contenedorMov);

    }
}
