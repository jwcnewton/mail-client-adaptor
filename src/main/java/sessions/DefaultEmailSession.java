package sessions;

import sessions.interfaces.IEmailSession;

import javax.mail.*;
import java.util.Properties;

/**
 * <p>DefaultEmailSession class.</p>
 *
 * @author jonathannewton
 * @version $Id: $Id
 */
public class DefaultEmailSession implements IEmailSession {
    /** {@inheritDoc} */
    @Override
    public Store OpenSessionStore(Properties props, String protocol) throws NoSuchProviderException {
        Session emailSession = Session.getDefaultInstance(props);
        return emailSession.getStore(protocol);
    }

    /** {@inheritDoc} */
    @Override
    public Folder ConnectToMailBox(String host, String email, String password, String mailBox, Store store) throws MessagingException {
        store.connect(host, email, password);
        Folder mailbox = store.getFolder(mailBox);
        mailbox.open(Folder.READ_WRITE);
        return mailbox;
    }
}
