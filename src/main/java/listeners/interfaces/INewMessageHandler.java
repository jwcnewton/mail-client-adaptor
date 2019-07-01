package listeners.interfaces;

import javax.mail.Message;

public interface INewMessageHandler {
    void NewMessage(Message newMessage);
}
