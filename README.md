# mail-client-adaptor
A mail client written in Java

## Work in progress currently just prints the subject, only tested on gmail

## Example useage

```java
final String username = "Test@gmail.com";
final String password = "****";

ClientBuilder clientBuilder = new ClientBuilder
        .Builder()
        .email(username)
        .password(password)
        .mailbox("Notes")
        .clientProperties("gmail")
        .protocol("imaps")
        .host("smtp.gmail.com")
        .build();

try (MailClient client = new MailClient(clientBuilder)){
    client.addListener(new NewMessageListener());

    client.pollMailBox();
} catch (Exception ignore) {}

```
