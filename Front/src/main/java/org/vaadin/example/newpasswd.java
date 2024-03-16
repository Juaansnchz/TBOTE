package org.vaadin.example;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.io.IOException;

@Route("newpasswd")
public class newpasswd extends VerticalLayout {

    public newpasswd(@Autowired GreetService service) {
        addClassName("recuperar-contraseña");
        VerticalLayout recuperarcontainer = new VerticalLayout();
        recuperarcontainer.addClassName("recuperarcontainer");

        // Botón de flecha hacia atrás
        Button backButton = new Button();
        backButton.addClassName("back-button");
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT)); // Establecer icono de flecha a la izquierda
        backButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(Login.class));
            Notification.show("Volver atrás pulsado");
        });
        recuperarcontainer.setHorizontalComponentAlignment(Alignment.START, backButton);

        // Título con estilos personalizados
        H1 title = new H1("Recuperar Contraseña");
        title.addClassName("title");

        // Crear un layout horizontal para agrupar los campos en dos columnas
        HorizontalLayout fieldsLayout = new HorizontalLayout();
        fieldsLayout.addClassName("fields-layout");
        // Columna izquierda
        VerticalLayout leftColumn = new VerticalLayout();
        leftColumn.addClassName("left-column");
        // Campos de texto
        TextField usernameField = new TextField("Usuario (DNI)");
        usernameField.addClassName("username-field");

        PasswordField newPasswordField = new PasswordField("Nueva Contraseña");
        newPasswordField.addClassName("new-password-field");

        PasswordField confirmPasswordField = new PasswordField("Confirmar Nueva Contraseña");
        confirmPasswordField.addClassName("confirm-new-password-field");
        leftColumn.add(usernameField, newPasswordField, confirmPasswordField);
        // Columna derecha
        VerticalLayout rightColumn = new VerticalLayout();
        rightColumn.addClassName("right-column");

        // ComboBox y campo de respuesta...
        ComboBox<String> securityQuestionComboBox = new ComboBox<>("Pregunta de seguridad");
        TextField securityAnswerField = new TextField("Respuesta");
        securityQuestionComboBox.setItems("¿Cuál es el nombre de tu mascota?", "¿En qué ciudad naciste?", "¿Cuál es tu comida favorita?");

        rightColumn.add(securityQuestionComboBox, securityAnswerField);

        // Botón con estilos personalizados
        Button button = new Button("Recuperar Contraseña", e -> {
            String username = usernameField.getValue();
            String newPassword = newPasswordField.getValue();
            String respuesta = securityAnswerField.getValue();
            String confirmPassword = confirmPasswordField.getValue();

            // Verificar si algún campo está vacío
            if (username.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty() || respuesta.isEmpty()) {
                Notification.show("Por favor, complete todos los campos.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Notification.show("Las contraseñas no coinciden.");
                return;
            }

            Datos data = new Datos();
            data.setDNI(username);
            data.setPasswd(newPassword);
            data.setConfirmPassword(confirmPassword);
            data.setRespuesta(respuesta);

            try {
                if (service.updateUsuario(data.getDNI(), data.getPasswd(), data.getRespuesta()).equals("(POST http://localhost:8090/newpasswd) 200")) {
                    Notification.show("Contraseña recuperada exitosamente para el usuario: " + username);
                } else {
                    Notification.show("Error: No se pudo recuperar la contraseña para el usuario: " + username);
                }
            } catch (URISyntaxException | InterruptedException | IOException ex) {
                Notification.show("Error al intentar recuperar la contraseña.");
                ex.printStackTrace();
            }
        });
        button.addClassName("recover-password-button");


        // Agregar componentes al diseño principal
        fieldsLayout.add(leftColumn, rightColumn);
        recuperarcontainer.add(backButton, title, fieldsLayout, button);
        add(recuperarcontainer);
        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }
}