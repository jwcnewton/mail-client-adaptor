package sessions.interfaces;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import java.util.Properties;

/**
 * <p>IEmailSession interface.</p>
 *
 * @author jonathannewton
 * @version $Id: $Id
 */
public interface IEmailSession {
    /**
     * <p>OpenSessionStore.</p>
     *
     * @param props a {@link java.util.Properties} object.
     * @param protocol a {@link java.lang.String} object.
     * @return a {@link javax.mail.Store} object.
     * @throws javax.mail.NoSuchProviderException if any.
     */
    Store OpenSessionStore(Properties props, String protocol) throws NoSuchProviderException;
    /**
     * <p>ConnectToMailBox.</p>
     *
     * @param host a {@link java.lang.String} object.
     * @param email a {@link java.lang.String} object.
     * @param password a {@link java.lang.String} object.
     * @param mailBox a {@link java.lang.String} object.
     * @param store a {@link javax.mail.Store} object.
     * @return a {@link javax.mail.Folder} object.
     * @throws javax.mail.MessagingException if any.
     */
    Folder ConnectToMailBox(String host, String email, String password, String mailBox, Store store) throws MessagingException;
}
