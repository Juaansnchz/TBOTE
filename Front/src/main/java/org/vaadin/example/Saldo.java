package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

@Route("saldo")
public class Saldo extends VerticalLayout {

    private GreetService greetService;

    public Saldo(@Autowired GreetService service) {
        this.greetService = service;

        addClassName("saldo");

        VerticalLayout saldoContainer = new VerticalLayout();
        saldoContainer.addClassName("saldo-container");

        // Botón de flecha hacia atrás
        Button backButton = new Button();
        backButton.addClassName("back-button");
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT)); // Establecer icono de flecha a la izquierda
        backButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(MainView.class));
            Notification.show("Volver atrás pulsado");
        });
        saldoContainer.setHorizontalComponentAlignment(Alignment.START, backButton);

        // Título con estilos personalizados
        H1 title = new H1("TBOTE - Saldo");
        title.addClassName("title");

        try {
            // Obtener el saldo para el DNI ingresado
            String dni = (String) UI.getCurrent().getSession().getAttribute("dni");
            String versaldo = greetService.getSaldo(dni);
            H4 saldo = new H4(versaldo);
            saldoContainer.add(saldo);
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Notification.show("Error al obtener el saldo: " + ex.getMessage());
        }

        // Agregar componentes al contenedor de saldo
        saldoContainer.add(backButton, title);

        // Agregar contenedor de saldo al diseño principal
        add(saldoContainer);
        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }
}