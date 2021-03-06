package com.example.brief3javafx.sendMail;


import com.example.brief3javafx.models.Client;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMail {

public static void sendMail(Client c) throws MessagingException {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    final String myEmail = "***************************";
    final String password = "*************";

   Session session = Session.getInstance(props,new Authenticator(){
       protected PasswordAuthentication getPasswordAuthentication(){
           return  new PasswordAuthentication(myEmail,password);
       }
   });
    Message sendMsg = prepareMessage(session,myEmail,c);
    Transport.send(sendMsg);
    System.out.println("Message envoyeés avec succes !!");
}

private static Message prepareMessage(Session session, String myEmail , Client c){

    try {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(myEmail));
        msg.setRecipient(Message.RecipientType.TO,new InternetAddress(c.getEmail()));
        msg.setSubject("Welcome to YCInsurrance ");
        msg.setContent("<table>" +
                "    <tr>" +
                "        <td class=>" +
                "            <table>" +
                "                <tr>" +
                "                    <td align='center' class='masthead'>" +
                "                        <h1>Welcome to YCInscurrance</h1>" +
                "                    </td>" +
                "                </tr>" +
                "                <tr>" +
                "                    <td class='content'>" +
                "                        <h2>Hello"+c.getFirstName() + " "+c.getLastName()+ ",</h2>" +
                "                        <p>YOU'VE BEEN ADDED TO YOUR MAILING LIST AND WILL BE NOW AMONG THE FIRST TO HEAR ABOUT UPDATE AND MORE</p>" +
                "            <table>" +
                "                <tr>" +
                "                    <td class='content footer' align='center'>" +
                "                        <p>Sent by <a >YouCode</a>, Etab Youcode Safi, OZ, 1312</p>" +
                "                        <p><a href='mailto:'>"+c.getEmail()+"</a> | <a href='#'>Unsubscribe</a></p>" +
                "                    </td>" +
                "                </tr>" +
                "            </table>" +
                "" +
                "        </td>" +
                "    </tr>" +
                "</table>", "text/html; charset=utf-8");
        //msg.setText("Hello "+c.getFirstName() +  " "+c.getLastName()+ " \n We're excites to have you get started \n If you have any questions,just reply to this email \n \n YCteam  ");
        return  msg;

    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    return  null;
}

}
