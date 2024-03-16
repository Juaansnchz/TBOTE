package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("login")
public class Login extends VerticalLayout {

    public Login(@Autowired GreetService service) {
        addClassName("login");

        VerticalLayout loginContainer = new VerticalLayout();
        loginContainer.addClassName("login-container");

        // Botón de flecha hacia atrás
        Button backButton = new Button();
        backButton.addClassName("back-button");
        backButton.setIcon(new Icon(VaadinIcon.ARROW_LEFT)); // Establecer icono de flecha a la izquierda
        backButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(MainView.class));
            Notification.show("Volver atrás pulsado");
        });
        loginContainer.setHorizontalComponentAlignment(Alignment.START, backButton);

        // Título con estilos personalizados
        H1 title = new H1("TBOTE");
        title.addClassName("title");

        // Campos de texto
        TextField usernameField = new TextField("Usuario");
        usernameField.addClassName("username-field");

        PasswordField passwordField = new PasswordField("Contraseña");
        passwordField.addClassName("password-field");

        // Botón con estilos personalizados
        Button button = new Button("Iniciar Sesión", e -> {
            String username = usernameField.getValue();
            String passwd = passwordField.getValue();
            Datos data = new Datos();
            data.setDNI(username);
            data.setPasswd(passwd);
            try {
                // Validate username and password
                if (username.isEmpty() || passwd.isEmpty()) {
                    Notification.show("Por favor ingresa usuario y contraseña");
                    return;
                } else{
                    if(service.loginUsuario(data.getDNI(), data.getPasswd()).equals("(POST http://localhost:8090/login) 200"))
                    {
                        Notification.show("¡Inicio de sesión exitoso para el usuario: " + username);
                        getUI().ifPresent(ui -> {
                            ui.getSession().setAttribute("dni", data.getDNI()); // Guardar el DNI en la sesión
                            ui.navigate(PostLogin.class);
                        });
                    } else {
                        Notification.show("Error: Usuario no registrado");
                    }

                }

            }catch (Exception ex) {}



        });
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClassName("login-button");
        // Link to registration page
        Anchor registrationLink = new Anchor("newpasswd", "Olvide mi contraseña");
        registrationLink.getStyle().set("text-align", "center");
        registrationLink.getStyle().set("display", "block");
        registrationLink.getStyle().set("margin-top", "20px");



        // Agregar componentes al contenedor de inicio de sesión
        loginContainer.add(backButton, title, usernameField, passwordField, button, registrationLink);

        // Agregar contenedor de inicio de sesión al diseño principal
        add(loginContainer);
        setAlignItems(Alignment.CENTER);
        setSizeFull();
    }
}
