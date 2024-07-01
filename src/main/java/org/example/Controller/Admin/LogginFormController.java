package org.example.Controller.Admin;

import jakarta.mail.BodyPart;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.db.dbConnection;
import org.example.entity.EmployeeEntity;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import jakarta.mail.Multipart;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;


public class LogginFormController implements Initializable {

    public TextField txtUserName;
    public TextField txtpassword;


    private SessionFactory sessionFactory; // Declare sessionFactory as an instance variable
    private Session session;


    public TextField txtUsername;
    public TextField txtPw;

    public String Email;
    public String OTP;

    public void btnlogOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        Email = txtUserName.getText();
        String Password = txtpassword.getText();
        Connection connection = dbConnection.getInstance().getConnection();
        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            statement.setString(1, Email);
            statement.setString(2, Password);
//            ResultSet resultSet = statement.executeQuery();
//
//
            boolean isavilble = statement.execute();
            System.out.println(isavilble + "0000000000000000000");

//orderDetailList
            if (!isavilble) {
                System.out.println("noooooooooooooooooooo45678##################5%%%%%%%%");
               this.OTP = generateOtp();


            } else {
                if (Email.equals("sapu@gmail.com") && Password.equals("sapu")) {
                    Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/Admin/AdminDashBoardForm.fxml"));
                    Scene updateScene = new Scene(updateRoot);

                    Stage updateStage = new Stage();
                    updateStage.setTitle("Employee Add From");
                    updateStage.setScene(updateScene);
                    updateStage.setResizable(false);
                    updateStage.show();
                } else {
                    Parent updateRoot = FXMLLoader.load(getClass().getResource("/view/Employee/EmployeeDashBord.fxml"));
                    Scene updateScene = new Scene(updateRoot);

                    Stage updateStage = new Stage();
                    updateStage.setTitle("Employee Add From");
                    updateStage.setScene(updateScene);
                    updateStage.setResizable(false);
                    updateStage.show();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(false);
        }

    }

    private String otp = generateOtp();

    private String generateOtp() {
        Random rnd = new Random();
        int num = rnd.nextInt(999999);
        System.out.println("OTP    "+num);

        return String.format("%06d", num);
    }


    private boolean sendOTP(String Email,String otp) {

        System.out.println("setOtp method ekata awa    ");
        boolean test = false;
        String Otp=otp;

        String toEmail = Email;
        String forEmail = "sapuninethmini888@gmail.com";
        String password = "jkil hlzc qgap dhjx";
        try {
            Properties prop = new Properties();

            prop.setProperty("mail.smtp.host", "smtp.gmail.com");
            prop.setProperty("mail.smtp.port", "587");
            prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.smtp.starttls.enable", "true");
            prop.setProperty("mail.smtp.socketFactory.port", "587");
            prop.setProperty("mail.smtp.socketFactory.port", "javax.net.ssl.SSLSocketFactory");

            javax.mail.Session session = javax.mail.Session.getInstance(prop, new Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(forEmail,password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(forEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));/////to email
            message.setSubject("Clothify Store - Change Password");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("Your OTP is: " + otp);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();



            // Add part to multipart
            multipart.addBodyPart(messageBodyPart);

            // Add multipart to message
//            message.setContent(multipart);


            Transport.send(message);
            System.out.println("Email sent successfully!");


            test = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
        return test;
    }


    public void btnFrogotPwOnAction (ActionEvent event){
        sendOTP(Email,OTP);
        System.out.println("Forgot password button clicked!");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        sessionFactory = HibernateUtil.getSessionFactory(); // Initialize sessionFactory
//        session = sessionFactory.openSession();

    }


}