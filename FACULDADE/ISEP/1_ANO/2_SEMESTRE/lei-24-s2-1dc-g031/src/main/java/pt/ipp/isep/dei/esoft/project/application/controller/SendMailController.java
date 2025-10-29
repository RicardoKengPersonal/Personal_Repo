package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is responsible for controlling the operations related to sending emails.
 * It interacts with the JavaMail API to send emails to team members.
 */
public class SendMailController {
    private Properties mailProperties;

    /**
     * Default constructor for the SendMailController class.
     * Initializes the mailProperties from the config.properties file.
     */
    public SendMailController() {
        mailProperties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            mailProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Sends an email to all members of a team.
     * @param team The team to send the email to.
     * @param subject The subject of the email.
     * @param message The message of the email.
     */
    public void sendMail(Team team, String subject, String message) {
        String smtpServer = mailProperties.getProperty("smtpServer");
        String smtpPort = mailProperties.getProperty("smtpPort");
        String smtpUser = mailProperties.getProperty("smtpUser");
        String smtpPassword = mailProperties.getProperty("smtpPassword");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpServer);
        properties.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUser, smtpPassword);
            }
        });

        try {
            for (Collaborator collaborator : team.getCollaborators()) {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(smtpUser));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(collaborator.getEmail()));
                msg.setSubject(subject);
                msg.setText(message);

                Transport.send(msg);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}