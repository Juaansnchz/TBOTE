package org.vaadin.example;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.H1;
import org.springframework.beans.factory.annotation.Autowired;
@Route("PostLogin")
public class PostLogin extends VerticalLayout {
    public PostLogin(@Autowired GreetService service) {

        getStyle().set("background-color", "rgba(40, 167, 69, 0.5)");
        getStyle().set("background-size","auto");

        VerticalLayout contenedor = new VerticalLayout();
        HorizontalLayout contenedor1 = new HorizontalLayout();
        HorizontalLayout contenedor2= new HorizontalLayout();
        HorizontalLayout contenedor3= new HorizontalLayout();
        HorizontalLayout contenedor4= new HorizontalLayout();

        contenedor.addClassName("contenedor");
        contenedor1.addClassName("contenedor1");
        contenedor2.addClassName("contenedor2");
        contenedor3.addClassName("contenedor3");
        contenedor4.addClassName("contenedor4");



        H1 titulo = new H1("Tbote");
        titulo.addClassName("tbote");
        add(titulo);

        // Ajusta las rutas de las imágenes según tus necesidades
        H4 saldo = new H4("Saldo");
        saldo.addClassName("alinea-texto-boton");
        //saldo.setValue("saldo");
        //saldo.setReadOnly(true);

        H4 movimientos = new H4("Movimientos");
        movimientos.addClassName("alinea-texto-boton");


        H4 bizum = new H4("Bizum");
        bizum.addClassName("alinea-texto-boton");


        H4 ayuda = new H4("Ayuda");
        ayuda.addClassName("alinea-texto-boton");


        Button button1 = createButton("./themes/img/dinero.png", "btnsaldo");
        Button button2 = createButton("./themes/img/arrow.png", "btnmovs");
        Button button3 = createButton("./themes/img/enviar-dinero.png", "btnbizum");
        Button button4 = createButton("./themes/img/help.png", "btnayuda");

        contenedor1.add(button1, saldo);
        contenedor2.add(button2,movimientos);
        contenedor3.add(button3,bizum);
        contenedor4.add(button4,ayuda);


        contenedor.add(contenedor1,contenedor2,contenedor3,contenedor4);

        // Agregar contenedor de registro al diseño principal
        add(contenedor);

        setSizeFull(); // Establece el tamaño de la vista como completo para ocupar toda la pantalla
        setJustifyContentMode(JustifyContentMode.CENTER); // Centra los elementos verticalmente en el contenedor
        setAlignItems(Alignment.CENTER); // Centra los elementos horizontalmente en el contenedor
    }

    private Button createButton(String imagePath, String btn) {
        Image image = new Image(imagePath, "Imagen del botón");
        image.setWidth("100px"); // Ajusta el ancho de la imagen según tus necesidades
        image.setHeight("100px"); // Ajusta la altura de la imagen según tus necesidades

        Button button = new Button(image, event -> {
            // Lógica que se ejecutará al hacer clic en el botón
            if(btn.equals("btnsaldo")){
                Datos data = new Datos();
                String dni = (String) UI.getCurrent().getSession().getAttribute("dni");
                data.setDNI(dni);
                getUI().ifPresent(ui -> {
                    ui.getSession().setAttribute("dni", data.getDNI()); // Guardar el DNI en la sesión
                    ui.navigate(Saldo.class);
                });
            }

        });

        button.addClassName("button");
        return button;
    }
}
