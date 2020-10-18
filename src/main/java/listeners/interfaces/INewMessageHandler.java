package listeners.interfaces;

import javax.mail.Message;

/**
 * <p>INewMessageHandler interface.</p>
 *
 * @author jonathannewton
 * @version $Id: $Id
 */
public interface INewMessageHandler {
    /**
     * <p>NewMessage.</p>
     *
     * @param newMessage a {@link javax.mail.Message} object.
     */
    void NewMessage(Message newMessage);
}
