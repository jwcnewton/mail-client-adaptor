package builders;

import properties.PreConfiguredProperties;
import sessions.DefaultEmailSession;
import sessions.interfaces.IEmailSession;

import java.util.Properties;

/**
 * <p>ClientBuilder class.</p>
 *
 * @author jonathannewton
 * @version $Id: $Id
 */
public class ClientBuilder {
    private final Properties clientProperties;
    private final IEmailSession session;
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

        if (builder.session == null){
            builder.session = new DefaultEmailSession();
        }

        this.session = builder.session;
    }

    public static class Builder {
        private Properties clientProperties;
        private IEmailSession session;
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
                //Todo: Add more client properties
                default:
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

        public Builder session(IEmailSession session){
            this.session = session;
            return this;
        }
    }

    /**
     * <p>Getter for the field <code>clientProperties</code>.</p>
     *
     * @return a {@link java.util.Properties} object.
     */
    public Properties getclientProperties() {
        return clientProperties;
    }

    /**
     * <p>Getter for the field <code>session</code>.</p>
     *
     * @return a {@link sessions.interfaces.IEmailSession} object.
     */
    public IEmailSession getSession () {
        return session;
    }

    /**
     * <p>Getter for the field <code>protocol</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * <p>Getter for the field <code>email</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getEmail() {
        return email;
    }

    /**
     * <p>Getter for the field <code>password</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPassword() {
        return password;
    }

    /**
     * <p>Getter for the field <code>mailbox</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMailbox() {
        return mailbox;
    }

    /**
     * <p>Getter for the field <code>host</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getHost () {
        return host;
    }

}
