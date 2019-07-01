package listeners;

import listeners.interfaces.INewMessageHandler;

import javax.mail.Message;
import javax.mail.MessagingException;


public class NewMessageListener implements INewMessageHandler {

    @Override
    public void NewMessage(Message newMessage) {
        try {
            System.out.println(newMessage.getSubject());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
