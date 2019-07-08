package client;

import builders.ClientBuilder;
import client.base.BaseMailClient;
import sessions.interfaces.IEmailSession;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import java.util.concurrent.atomic.AtomicBoolean;


public class MailClient extends BaseMailClient implements AutoCloseable, Runnable {
    private IEmailSession session;
    private Folder mailBox;
    private Store store;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread worker;

    public int pollInterval = 5000;
    public int currentMessageCount;

    public MailClient(ClientBuilder client) throws Exception {
        if(client == null){
            throw new Exception("MailClient() : client cannot be null");
        }

        session = client.getSession();

        store = session.OpenSessionStore(client.getclientProperties(),
                client.getProtocol());

        mailBox = session.ConnectToMailBox(client.getHost(),
                client.getEmail(),
                client.getPassword(),
                client.getMailbox(),
                store);
    }

    public void run() {
        running.set(true);

        try {
            currentMessageCount = mailBox.getMessageCount();
        } catch (MessagingException e) {
            System.out.println("Failed fetching initial mailbox size");
        }

        while (running.get()) {
            try {
                currentMessageCount = checkMail();
                worker.sleep(pollInterval);
            } catch (InterruptedException e){
                worker.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            finally {
                running.set(false);
            }
        }
    }

    public int checkMail() throws MessagingException {
        int currentCount = mailBox.getMessageCount();

        if(currentCount > currentMessageCount){
            pushNewMailEvent(currentCount);
        }

        return currentCount;
    }

    private void pushNewMailEvent(int newMailIndex) throws MessagingException {
        newMessageReceived(mailBox.getMessage(newMailIndex));

        mailBox.getMessage(newMailIndex)
                .setFlag(Flags.Flag.SEEN, true);
    }

    @Override
    public void close() throws Exception {
        running.set(false);
        mailBox.close(true);
        store.close();
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void interrupt() throws Exception {
        running.set(false);
        worker.interrupt();
        this.close();
    }
}