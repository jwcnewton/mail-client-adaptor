package utils;

import properties.PreConfiguredProperties;

import java.util.Properties;

public class ClientBuilder {
    private final Properties clientProperties;
    private final String protocol;
    private final String email;
    private final String password;
    private final String mailbox;
    private final String host;

    private ClientBuilder(Builder builder) {
        this.clientProperties = builder.clientProperties;
        this.protocol = builder.protocol;
        this.email = builder.email;
        this.password = builder.password;
        this.mailbox = builder.mailbox;
        this.host = builder.host;
    }

    public static class Builder {
        private Properties clientProperties;
        private String protocol;
        private String email;
        private String password;
        private String mailbox;
        private String host;

        public Builder() {}

        public ClientBuilder build(){
            return new ClientBuilder(this);
        }

        public Builder clientProperties(String clientType){
            switch (clientType.toLowerCase()) {
                case "gmail":
                    this.clientProperties = PreConfiguredProperties.gmailSmtpProperties();
            }

            return this;
        }

        public Builder protocol(String protocol){
            this.protocol = protocol;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder mailbox(String mailbox){
            this.mailbox = mailbox;
            return this;
        }

        public Builder host(String host){
            this.host = host;
            return this;
        }
    }

    public Properties getclientProperties() {
        return clientProperties;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMailbox() {
        return mailbox;
    }

    public String getHost () {
        return host;
    }

}
