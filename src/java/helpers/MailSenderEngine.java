/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author yassine
 */
//clean and tested
public class MailSenderEngine {

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    private String myMail;
    private String myPassword;
    private String managementNetworkStart;

    public MailSenderEngine() {
        Vector<String> Parameters = new Vector();
        Parameters.add("EMAIL");
        Parameters.add("EMAIL_PASSWORD");
        Parameters.add("MANAGEMENT_NETWORK");

        Vector<String> params = Config.getProperties(Parameters);
        myMail = params.get(0);
        myPassword = params.get(1);
        managementNetworkStart = params.get(2);

    }

    public void generateAndSendEmail(String receiver, String link, String Name) throws AddressException, MessagingException {
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");//enable mail encryption

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        generateMailMessage.setSubject("Account Confirmation");//The Subject

        String emailBody = "Hello " + Name + " !<br>"
                + "Before you can log in, for security reasons, "
                + " we need you to confirm your account. To do so, simply open the following URL:<br><br>"
                + "<a href=" + link + "> <strong>" + link + "</strong></a><br><br><br>"
                + "Regards,<br><span style=\"color:blue;\"> Congiv GmbH team</span>";

        generateMailMessage.setContent(emailBody, "text/html");
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", myMail, myPassword);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    public String URLBuilder() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface nic = (NetworkInterface) e.nextElement();
                Enumeration addresses = nic.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress i = (InetAddress) addresses.nextElement();
                    String IP = i.getHostAddress();
                    if (IP.startsWith(managementNetworkStart)) {
                        return IP;
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
