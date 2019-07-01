package client;

import utils.ClientBuilder;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;


public class MailClient extends BaseMailClient implements AutoCloseable {
    private Folder mailBox;
    private Store store;
    public int pollInterval = 5000;
    private int lastCount;

    public MailClient(ClientBuilder client) throws Exception {
        if(client == null){
            throw new Exception("MailClient() : client cannot be null");
        }

        Session emailSession = Session.getDefaultInstance(client.getclientProperties());

        store = emailSession.getStore(client.getProtocol());
        store.connect(client.getHost(), client.getEmail(), client.getPassword());
        mailBox = store.getFolder(client.getMailbox());
        lastCount = mailBox.getMessageCount();
        mailBox.open(Folder.READ_WRITE);
    }

    public void pollMailBox() {
        try {
            while (true) {
                lastCount = checkMail();
                Thread.sleep(pollInterval);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int checkMail() throws MessagingException {
        int currentCount = mailBox.getMessageCount();

        if(currentCount > lastCount){
            newMessageReceived(mailBox.getMessage(currentCount));

            mailBox.getMessage(currentCount)
                    .setFlag(Flags.Flag.SEEN, true);
        }

        return currentCount;
    }

    @Override
    public void close() throws Exception {
        mailBox.close(true);
        store.close();
    }
}