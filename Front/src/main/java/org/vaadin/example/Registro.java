package org.vaadin.example;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * A sample Vaadin view class for registration.
 */
@Route("login/registro")
public class Registro extends VerticalLayout {


    public Registro(@Autowired GreetService service) {
        addClassName("registro");

        // Contenedor para el formulario de registro
        VerticalLayout registrationContainer = new VerticalLayout();
        registrationContainer.addClassName("registration-container");

        // Botón para volver atrás
        Button backButton = new Button();
        backButton.addClassName("back-button");
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT)); // Establecer icono de flecha a la izquierda
        backButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(MainView.class));
            Notification.show("Volver atrás pulsado");
        });
        registrationContainer.add(backButton);

        // Imagen
        Image logo = new Image("./themes/img/logo.jpg", "Logo de TBOTE");
        logo.addClassName("logo");
        registrationContainer.add(logo);

        // Crear un layout horizontal para agrupar los campos en dos columnas
        HorizontalLayout fieldsLayout = new HorizontalLayout();
        fieldsLayout.addClassName("fields-layout");

        // Columna izquierda
        VerticalLayout leftColumn = new VerticalLayout();
        leftColumn.addClassName("left-column");

        // Campos de texto y contraseña...
        TextField dniField = new TextField("DNI");
        PasswordField passwordField = new PasswordField("Contraseña");
        PasswordField confirmPasswordField = new PasswordField("Confirmar contraseña");

        leftColumn.add(dniField, passwordField, confirmPasswordField);

        // Columna derecha
        VerticalLayout rightColumn = new VerticalLayout();
        rightColumn.addClassName("right-column");

        // ComboBox y campo de respuesta...
        ComboBox<String> securityQuestionComboBox = new ComboBox<>("Pregunta de seguridad");
        TextField securityAnswerField = new TextField("Respuesta");
        securityQuestionComboBox.setItems("¿Cuál es el nombre de tu mascota?", "¿En qué ciudad naciste?", "¿Cuál es tu comida favorita?");

        rightColumn.add(securityQuestionComboBox, securityAnswerField);

        // Agregar las columnas al layout horizontal
        fieldsLayout.add(leftColumn, rightColumn);
        registrationContainer.add(fieldsLayout);

        // Botón de registro
        Button button = new Button("Registrarse", e -> {
            Datos data = new Datos();
            Boolean error = true;
            String contra1 = passwordField.getValue();
            data.setPasswd(contra1);
            String contra2 = confirmPasswordField.getValue();
            data.setConfirmPassword(contra2);
            String DNI = dniField.getValue();
            data.setDNI(DNI);
            String respuesta = securityAnswerField.getValue();
            data.setRespuesta(respuesta);
            if (!formatoDniCorrecto(DNI)) {
                //throw new RuntimeException("Error al insertar datos. El formato del DNI no es válido.");
                Notification.show("Error: El formato del DNI no es válido.");
                error = false;
            }
            if (!contra1.equals(contra2)){

                Notification.show("Error: Las contraseñas no coinciden.");
                error = false;
            }
            if(passwordField.isEmpty()||confirmPasswordField.isEmpty()||dniField.isEmpty()){
                Notification.show("Error: Complete todos los campos.");
                error = false;
            }
            //if(!dniField.equals("\\d{8}[A-HJ-NP-TV-Z]")){ Notification.show("Error: Introduce un DNI correcto.");}
            try {
                if (error){
                    if(service.createUsuario(data.getDNI(), data.getPasswd(), data.getRespuesta()).equals("(POST http://localhost:8090/registro) 200"))
                    {
                        Notification.show("¡Registro exitoso para el usuario: " + data.getDNI());
                        getUI().ifPresent(ui -> ui.navigate(MainView.class));
                    } else {
                        Notification.show("Error: El usuario no se pudo registrar");
                    }}

            } catch (URISyntaxException | InterruptedException | IOException ex) {
                System.err.println("Error creando usuario: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        button.addClassName("register-button");
        registrationContainer.add(button);

        add(registrationContainer);
        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }
    private static boolean formatoDniCorrecto(String dni) {
        // Validar el formato del DNI español (8 dígitos seguidos de una letra)
        return dni.toUpperCase().matches("\\d{8}[A-HJ-NP-TV-Z]");
    }
}
