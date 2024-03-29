package org.vaadin.example;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("bizum")
public class bizum extends VerticalLayout {
    public bizum(@Autowired GreetService service) {

        getStyle().set("background-color","rgba(40, 167, 69, 0.5)");
        VerticalLayout contenedorMov = new VerticalLayout();
        HorizontalLayout Subtitulo = new HorizontalLayout();
        contenedorMov.addClassName("contenedorMov");


        H1 titulo = new H1("Tbote");
        titulo.addClassName("titulo");
        add(titulo);

        H2 subtitulo = new H2("Bizum");
        subtitulo.addClassName("bizum");
        Subtitulo.add(subtitulo);

        contenedorMov.add(Subtitulo);

        add(contenedorMov);

    }
}

