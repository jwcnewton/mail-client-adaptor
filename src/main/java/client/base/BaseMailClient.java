package client.base;

import listeners.interfaces.INewMessageHandler;

import javax.mail.Message;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>BaseMailClient class.</p>
 *
 * @author jonathannewton
 * @version $Id: $Id
 */
public class BaseMailClient {
    private List<INewMessageHandler> listeners = new ArrayList<>();

    /**
     * <p>newMessageReceived.</p>
     *
     * @param message a {@link javax.mail.Message} object.
     */
    public void newMessageReceived(Message message) {
        for (INewMessageHandler messageHandler : listeners)
            messageHandler.NewMessage(message);
    }

    /**
     * <p>addListener.</p>
     *
     * @param newListener a {@link listeners.interfaces.INewMessageHandler} object.
     */
    public void addListener(INewMessageHandler newListener) {
        listeners.add(newListener);
    }
}
