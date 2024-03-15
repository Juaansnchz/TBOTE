package com.tbote.tbote;

import com.google.gson.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataHandling {
    public boolean createUsuario(String dni, String passwd, String respuesta) {
        try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(DatosBBDD.class).addAnnotatedClass(Movimientos.class).buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            // Verificar si la contraseña no es null antes de intentar cifrarla
            if (passwd != null) {
                // Utilizamos HQL (Hibernate Query Language) para verificar si el usuario ya existe
                Query query = session.createQuery("FROM DatosBBDD WHERE DNI = :dni");
                query.setParameter("dni", dni);
                DatosBBDD datosUsuario = (DatosBBDD) query.uniqueResult();

                if (datosUsuario == null) {
                    // Si el usuario no existe, creamos uno nuevo
                    DatosBBDD nuevoUsuario = new DatosBBDD(dni, hashPassword(passwd), hashPassword(respuesta));
                    session.save(nuevoUsuario);
                    session.getTransaction().commit();
                    return true;
                }
            } else {
                // La contraseña es null, puedes manejar esto según tus necesidades
                System.out.println("La contraseña proporcionada es null");
                session.getTransaction().rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




    public DatosBBDD autenticarUsuario(String dni, String passwd) {
        try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(DatosBBDD.class).addAnnotatedClass(Movimientos.class).buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            // Utilizamos HQL (Hibernate Query Language) para buscar el usuario por DNI
            Query query = session.createQuery("FROM DatosBBDD WHERE DNI = :dni");
            query.setParameter("dni", dni);
            DatosBBDD datosUsuario = (DatosBBDD) query.uniqueResult();

            if (datosUsuario != null) {
                // Se encontró un usuario con el DNI proporcionado
                String contrasenaAlmacenada = datosUsuario.getPasswd();

                String contrseñaCifrada = hashPassword(passwd);

                // Comparar la contraseña almacenada con la proporcionada
                if (contrasenaAlmacenada.equals(contrseñaCifrada)) {
                    return datosUsuario;
                }
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    public boolean updateUsuario(String dni, String new_passwd, String respuesta) {
        try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(DatosBBDD.class).addAnnotatedClass(Movimientos.class).buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            // Verificar si la contraseña no es null antes de intentar cifrarla
            if (new_passwd != null) {
                String respuesta_hash = hashPassword(respuesta);
                // Consulta para obtener al usuario por DNI y verificar la respuesta
                Query query = session.createQuery("FROM DatosBBDD WHERE DNI = :dni AND respuesta = :respuesta_hash");
                query.setParameter("dni", dni);
                query.setParameter("respuesta_hash", respuesta_hash);
                DatosBBDD usuario = (DatosBBDD) query.uniqueResult();

                if (usuario != null) {
                    // Actualizar la contraseña con la nueva contraseña proporcionada
                    usuario.setPasswd(hashPassword(new_passwd));
                    session.getTransaction().commit();
                    return true;
                }
            } else {
                // La contraseña es null, puedes manejar esto según tus necesidades
                System.out.println("La contraseña proporcionada es null");
                session.getTransaction().rollback();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public DatosBBDD getUsuario(String dni) {
        try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(DatosBBDD.class).addAnnotatedClass(Movimientos.class).buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();


            // Utilizamos HQL (Hibernate Query Language) para buscar el usuario por DNI
            Query query = session.createQuery("FROM DatosBBDD WHERE DNI = :dni");
            query.setParameter("dni", dni);
            DatosBBDD usuario = (DatosBBDD) query.uniqueResult();


            session.getTransaction().commit();

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convertir el resultado del hash a una cadena hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente según tus necesidades
            return null;
        }
    }

    public int verSaldo(String dni) {
        try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(DatosBBDD.class).addAnnotatedClass(Movimientos.class).buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();


            // Utilizamos HQL (Hibernate Query Language) para buscar el usuario por DNI
            Query query = session.createQuery("FROM DatosBBDD WHERE DNI = :dni");
            query.setParameter("dni", dni);
            DatosBBDD usuario = (DatosBBDD) query.uniqueResult();

            session.getTransaction().commit();

            if (usuario != null) {
                return usuario.getSaldo();
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String verMovimientos(String dni){
        try (SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(DatosBBDD.class).addAnnotatedClass(Movimientos.class).buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();


            // Utilizamos HQL (Hibernate Query Language) para buscar el usuario por DNI
            Query query = session.createQuery("FROM Movimientos WHERE tablaLogin.id = (Select id FROM DatosBBDD WHERE DNI = :dni)");
            query.setParameter("dni", dni);
            List<Movimientos> ListaMovimientos = (List<Movimientos>) query.list();

            session.getTransaction().commit();

            if (!ListaMovimientos.isEmpty()) {
                DatosBBDD aux = new DatosBBDD();
                aux.setListMovs(ListaMovimientos);

                return aux.movsListToJson();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}