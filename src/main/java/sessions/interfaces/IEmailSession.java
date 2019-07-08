package sessions.interfaces;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import java.util.Properties;

public interface IEmailSession {
    Store OpenSessionStore(Properties props, String protocol) throws NoSuchProviderException;
    Folder ConnectToMailBox(String host, String email, String password, String mailBox, Store store) throws MessagingException;
}
