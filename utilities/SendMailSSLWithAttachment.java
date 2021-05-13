package utilities;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailSSLWithAttachment {
	static StringBuilder htmlStringBuilder;
	
	 // change accordingly 
    final String username = "jenkinsinfosystems@gmail.com"; 
    // change accordingly 
    final String password = "Jenkin@123"; 
	
	public static void htmlFunction() {
		
		htmlStringBuilder=new StringBuilder();
		
		String htmlStr =
                "<html>" +
                        "<head>" +
                        // "<meta name='viewport' content=width=device-width/> " +
                        "<meta http-equiv='Content-Language' content='hi'>" +
                        "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
                        "<style type='text/css'>" +
                        " table {\n" +
                        "    font-fa918382093mily: 'verdana ';\n" +
                        "    border-collapse: collapse;\n" +
                        "    width: 100%;\n" +
                        "}\n" +
                        " td, th {\n" +
                        " border: 1px solid #dddddd;\n" +
                        " text-align: center;\n" +
                        " font-size:" + 12 + ";" +
                        " padding:3px; " +
                        " color: #000000 }" +
                        "tr#INFO  {background-color:white; color:white;}"+
                        "tr#PASS  {background-color:green;}"+
                        "tr#FAIL  {background-color:red; color:white;}"+
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<table>";

	 
	htmlStringBuilder.append(htmlStr);
	htmlStringBuilder.append("<tr id ="+"INFO"+"><th><B>USER</B></th><th><B>:</B></th><th>"+"Priyankag"+"</th></tr>");
	htmlStringBuilder.append("</table></body></html>");


	}
	
	public void SendMail(String recipientAddress,String subject,String content,String htmlFilePath){
				
		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(username, password);

					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress(username));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipientAddress));
			
			//message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("abc@abc.com,abc@def.com,ghi@abc.com"));

			// Add the subject link
			message.setSubject(subject);
			message.setText("Hello, aas is sending email "); 	
	       // message.setContent("<h1>sending html mail check</h1>","text/html" );  
	       // message.setContent(htmlStringBuilder.toString(), "text/html");

			

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText("This is message body");
			//messageBodyPart1.setText(htmlStringBuilder.toString());
			messageBodyPart1.setContent(content, "text/html");



			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = htmlFilePath;

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filename);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}
	}
}
