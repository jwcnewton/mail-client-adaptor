# Mail Client Adaptor
A mail client written in Java

## Work in progress currently just prints the subject, only tested on gmail

## Example useage

```java
final String username = "test@gmail.com";
final String password = "*******";

ClientBuilder clientBuilder = new ClientBuilder
        .Builder()
        .email(username)
        .password(password)
        .mailbox("Notes")
        .clientProperties("gmail")
        .protocol("imaps")
        .host("smtp.gmail.com")
        .build();

try(MailClient client = new MailClient(clientBuilder)){
    //Set poll interval to 5 seconds
    client.pollInterval = 5000;

    //Simple print message listener
    client.addListener(new PrintNewMessageListener());

    //Run poll
    client.start();

    //Wait an amount of time
    Thread.sleep(100000);

    client.stop();
} catch (Exception e) {
    e.printStackTrace();
}

```
