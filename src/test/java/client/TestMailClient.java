package client;

import sessions.DefaultEmailSession;
import listeners.PrintNewMessageListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import builders.ClientBuilder;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;
import java.util.Properties;

public class TestMailClient {
    MailClient mailClient;

    @Mock
    PrintNewMessageListener mockMessageCountListener;

    ClientBuilder client;
    @Mock
    DefaultEmailSession defaultEmailSession;
    @Mock
    Store mockStore;
    @Mock
    Folder mockFolder;
    @Mock
    Message mockMessage;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        Mockito.when(mockFolder.getMessage(Mockito.anyInt()))
                .thenReturn(mockMessage);

        Mockito.when(mockStore.getFolder(Mockito.anyString()))
                .thenReturn(mockFolder);

        Mockito.when(defaultEmailSession.ConnectToMailBox(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.any(Store.class)))
                .thenReturn(mockFolder);

        Mockito.when(defaultEmailSession.OpenSessionStore(Mockito.any(Properties.class),
                Mockito.anyString()))
                .thenReturn(mockStore);

        client = new ClientBuilder
                .Builder()
                .clientProperties("") //Default
                .email("test@test")
                .host("https")
                .mailbox("INBOX")
                .password("test")
                .protocol("pop3")
                .session(defaultEmailSession)
                .build();

        mailClient = Mockito.spy(new MailClient(client));

    }

    @After
    public void cleanup(){
        mailClient.stop();
    }

    @Test
    public void run_calls_checkMail_the_expected_number_of_times() throws Exception {
        //Arrange
        Mockito.mock(MailClient.class).checkMail();

        mailClient.pollInterval = 1000;

        //Act
        mailClient.start();

        Thread.sleep(500);

        //Assert
        Mockito.verify(mailClient, Mockito.times(1)).checkMail();
    }

    @Test
    public void checkMail_pushes_new_event_if_message_count_increases() throws Exception {
        //Arrange
        Mockito.when(mockFolder.getMessageCount())
                .thenReturn(2);


        mailClient.addListener(mockMessageCountListener);
        mailClient.currentMessageCount = 1;


        //Act
        mailClient.checkMail();

        //Assert
        Mockito.verify(mockMessageCountListener, Mockito.times(1))
                .NewMessage(mockMessage);
    }

    @Test
    public void checkMail_returns_current_message_count() throws Exception {
        //Arrange
        Mockito.when(mockFolder.getMessageCount())
                .thenReturn(2);

        int expectedMessageCount = 2;

        //Act
        int actualMessageCount = mailClient.checkMail();

        //Assert
        Assert.assertEquals(expectedMessageCount, actualMessageCount);
    }
}
