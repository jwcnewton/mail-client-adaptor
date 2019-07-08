package client.base;

import listeners.interfaces.INewMessageHandler;

import javax.mail.Message;
import java.util.ArrayList;
import java.util.List;

public class BaseMailClient {
    private List<INewMessageHandler> listeners = new ArrayList<>();

    public void newMessageReceived(Message message) {
        for (INewMessageHandler messageHandler : listeners)
            messageHandler.NewMessage(message);
    }

    public void addListener(INewMessageHandler newListener) {
        listeners.add(newListener);
    }
}
