package Controlador;

import Modelo.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;

import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Key;
import java.sql.*;
import java.util.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

public class UserDao extends DAO {

    protected boolean login(String email, String password) {
        boolean logeado = false;
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT nombre,contrasena FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE email='" + email + "' AND contrasena='" + password + "';");
        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();


            if (!rs.next()) {
                logger.info("INFO: No logeado: " + email);
                logeado = false;

            } else {
                logger.info("INFO: Logeado: " + email);
                logeado = true;
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logeado;
    }

    //buscar por email en la base de datos
    public void select(String email) {
        Connection con = getConnection();
        StringBuffer query = new StringBuffer("SELECT * FROM ");
        query.append(this.getClass().getSimpleName());
        query.append(" WHERE email='");
        query.append(email);
        query.append("';");

        try {
            PreparedStatement ps = con.prepareStatement(query.toString());
            logger.info("INFO: Select by email  statement: " + ps.toString());
            ;
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                setClassFields(rs, rsmd, this);
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------

    public boolean Recuperar(User u) {
        TrippleDes td=null;
        try {
            td = new TrippleDes();
        }catch (Exception e){}
        boolean bool;
        final String username = u.getEmail();
        final String password = td.decrypt(u.getContrasena());

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(u.getEmail()));
            message.setSubject("Recuperar contraseña");
            message.setText("Hola, " + u.getNombre()
                    + "\n\n Tu contraseña es: " + password);

            Transport transport = session.getTransport("smtp");

            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password

            transport.connect("smtp.gmail.com", "DSAproyecto@gmail.com", "aleixguillemmikel");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Done");
            bool = true;

        } catch (MessagingException e) {
            bool = false;
            throw new RuntimeException(e);
        }
        return bool;

    }
}
