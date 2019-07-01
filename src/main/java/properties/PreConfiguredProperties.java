package properties;

import java.util.Properties;

public class PreConfiguredProperties {
    public static Properties gmailPop3Properties(){
        Properties props = new Properties();

        props.put("mail.store.protocol", "pop3");
        props.put("mail.pop3.host", "pop.gmail.com");
        props.put("mail.pop3.port", "995");
        props.put("mail.pop3.starttls.enable", "true");

        return props;
    }

    public static Properties gmailSmtpProperties(){
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "avax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        return props;
    }
}
