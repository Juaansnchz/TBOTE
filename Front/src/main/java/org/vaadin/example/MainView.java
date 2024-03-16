package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;

/**
 * A sample Vaadin view class for the home screen.
 */
@Route
public class MainView extends VerticalLayout {

    public MainView(@Autowired GreetService service) {
        Registro registro = new Registro(service);
        Login login = new Login(service);

        // Establecer el estilo CSS para el color de fondo del contenedor principal
        getStyle().set("background-color", "rgba(40, 167, 69, 0.5)"); // Verde con opacidad

        // Container for content
        VerticalLayout contentContainer = new VerticalLayout();
        contentContainer.getStyle().set("border", "2px solid #333"); // Border around content
        contentContainer.getStyle().set("border-radius", "10px"); // Rounded corners
        contentContainer.getStyle().set("padding", "20px"); // Padding around content
        contentContainer.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.1)"); // Add shadow to content
        contentContainer.getStyle().set("margin", "auto"); // Center content
        contentContainer.setMaxWidth("400px"); // Set max width for content
        contentContainer.setAlignItems(Alignment.CENTER); // Center items horizontally
        contentContainer.getStyle().set("background-color", "#ffffff"); // Establecer el color de fondo más claro


        // Add CSS to show background image
        contentContainer.getStyle().set("background-image", "url('frontend/images/img.png')"); // Background image
        contentContainer.getStyle().set("background-size", "cover");
        contentContainer.getStyle().set("background-position", "center");
        contentContainer.getStyle().set("background-repeat", "no-repeat");

        // Title with custom styles
        H1 title = new H1("TBOTE");
        title.getStyle().set("font-family", "Arial, sans-serif");
        title.getStyle().set("font-size", "2.5em");
        title.getStyle().set("font-weight", "bold");
        title.getStyle().set("color", "#333");
        title.getStyle().set("text-align", "center"); // Center title horizontally
        title.getStyle().set("margin-bottom", "20px");


        // Button with custom styles
        Button button = new Button("Iniciar Sesión",  e -> {
            getUI().ifPresent(ui -> ui.navigate(Login.class));
        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY); // Set button color to strong green
        button.getStyle().set("font-size", "1.2em"); // Adjust button text size
        button.getStyle().set("margin-top", "20px"); // Add margin on top of button to separate it from other elements
        button.getStyle().set("margin-left", "auto"); // Center button horizontally
        button.getStyle().set("margin-right", "auto"); // Center button horizontally
        button.getStyle().set("background-color", "rgba(40, 167, 69, 0.5)"); // Establecer color de fondo similar al del contenedor

        button.addClickShortcut(Key.ENTER);

        // Link to registration page
        Anchor registrationLink = new Anchor("login/registro", "¿No tienes cuenta? Regístrate aquí.");
        registrationLink.getStyle().set("text-align", "center");
        registrationLink.getStyle().set("display", "block");
        registrationLink.getStyle().set("margin-top", "20px");

        // Add components to content container
        contentContainer.add(title, button, registrationLink);

        // Add content container to main layout
        add(contentContainer);
        setAlignItems(Alignment.CENTER); // Center content container vertically
        setSizeFull(); // Make the main layout take full height of the viewport
    }
}
