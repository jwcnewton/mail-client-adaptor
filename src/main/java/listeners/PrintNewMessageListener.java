package listeners;

import listeners.interfaces.INewMessageHandler;

import javax.mail.Message;
import javax.mail.MessagingException;


/**
 * <p>PrintNewMessageListener class.</p>
 *
 * @author jonathannewton
 * @version $Id: $Id
 */
public class PrintNewMessageListener implements INewMessageHandler {

    /** {@inheritDoc} */
    @Override
    public void NewMessage(Message newMessage) {
        try {
            System.out.println(newMessage.getSubject());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
