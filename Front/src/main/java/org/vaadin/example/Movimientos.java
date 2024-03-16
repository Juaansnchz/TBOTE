package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Route("Movimientos")
public class Movimientos extends VerticalLayout {
    public Movimientos(@Autowired GreetService service) {

        getStyle().set("background-color","rgba(40, 167, 69, 0.5)");
        VerticalLayout contenedorMov = new VerticalLayout();
        HorizontalLayout Subtitulo = new HorizontalLayout();
        contenedorMov.addClassName("contenedorMov");


        H1 titulo = new H1("Tbote");
        titulo.addClassName("titulo");
        add(titulo);

        HorizontalLayout volver = new HorizontalLayout();
        H2 subtitulo = new H2("Movimientos");
        subtitulo.addClassName("Movimientos");
        Subtitulo.add(subtitulo);



        Button Volver = new Button();
        Volver.setIcon(new Icon(VaadinIcon.ARROW_LEFT));
        Volver.addClassName("back-button");
        Volver.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(PostLogin.class));
            Notification.show("Volver atrás pulsado");
        });
        volver.addClassName("volver");
        volver.add(Volver,subtitulo);
        contenedorMov.add(volver);

            // Obtener el saldo para el DNI ingresado
        try {
            String dni = (String) UI.getCurrent().getSession().getAttribute("dni");

                Grid<DatosMovimientos> grid = new Grid<>(DatosMovimientos.class, false);
                // Columnas con encabezado y tamaño automático
                grid.addColumn(DatosMovimientos::getNombre).setHeader("Nombre").setAutoWidth(true);
                grid.addColumn(DatosMovimientos::getFecha).setHeader("Fecha").setAutoWidth(true);
                grid.addColumn(DatosMovimientos -> DatosMovimientos.getDinero() + " €").setHeader("Transacción").setAutoWidth(true);
                grid.addClassName("Mov_grid");

                grid.setItems(service.getMovs("47314357F"));

                contenedorMov.add(grid);




        }catch (Exception e){
            e.printStackTrace();
        }



        add(contenedorMov);

    }
}
