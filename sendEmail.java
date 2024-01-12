import org.apache.commons.mail.*;

public class sendEmail {
    public static void main(String[] args) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
                        email.setAuthentication("nicholasu750@lpsk12.org", "");
        email.setSSLOnConnect(true);
        email.setFrom("nicholasu750@lpsk12.org");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("nicholasu750@lpsk12.org");
        email.send();
    }
}
