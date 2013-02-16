/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ClientEndpointConfigurationBuilder is a class used for creating
 * {@link ClientEndpointConfiguration} objects for the purposes of
 * deploying a client endpoint.
 * <br><br>Here are some examples:<br>
 * <br>Building a plain configuration with no encoders, decoders, subprotocols or extensions.<br><br>
 * <code>
 * ClientEndpointConfiguration cec = ClientEndpointConfigurationBuilder.create().build();<br>
 *</code>
 * 
 *<br><br>Building a configuration with no subprotocols and a custom configurator.<br><br>
 * <code>
 * ClientEndpointConfiguration customCec = ClientEndpointConfigurationBuilder.create()<br>
 *  &nbsp;&nbsp;             .setPreferredSubprotocols(mySubprotocols)<br>
 *  &nbsp;&nbsp;             .setClientHandshakeConfigurator(new MyClientConfigurator())<br>
 *  &nbsp;&nbsp;             .build();<br>
 * </code>
 * 
 * 
 * @author dannycoward
 */
public class ClientEndpointConfigurationBuilder {
    private List<String> preferredSubprotocols = new ArrayList<String>();
    private List<Extension> extensions = new ArrayList<Extension>();
    private List<Encoder> encoders = new ArrayList<Encoder>();
    private List<Decoder> decoders = new ArrayList<Decoder>();
    private ClientEndpointConfigurator clientEndpointConfigurator;
    
    /**
     * Creates a new builder object with no subprotocols, extensions, encoders,
     * decoders and a null configurator.
     * 
     * @return a new builder object.
     */
    public static ClientEndpointConfigurationBuilder create() {
        return new ClientEndpointConfigurationBuilder();
    }
    
    /**
     * Builds a configuration object using the attributes set
     * on this builder.
     * 
     * @return a new configuration object.
     */
    public ClientEndpointConfiguration build() {
        return new DefaultClientEndpointConfiguration(
            Collections.unmodifiableList(this.preferredSubprotocols),
            Collections.unmodifiableList(this.extensions),
            Collections.unmodifiableList(this.encoders),
            Collections.unmodifiableList(this.decoders),
            this.clientEndpointConfigurator);
    }
    
    /**
     * Returns the configurator object this builder will use to 
     * construct the configuration object.
     * 
     * @return the configurator object, or null if there is none.  
     */
    public ClientEndpointConfigurator getClientHandshakeConfigurator() {
        return this.clientEndpointConfigurator;
    }
    
    /**
     * Sets the configurator object for the configuration this builder will build.
     * 
     * @param clientEndpointConfigurator the configurator
     * @return the builder instance
     */
    public ClientEndpointConfigurationBuilder setClientHandshakeConfigurator(ClientEndpointConfigurator clientEndpointConfigurator) {
        this.clientEndpointConfigurator = clientEndpointConfigurator;
        return this;
    }

    /**
     * Return the protocols, in order of preference, favorite first, that this client would
     * like to use for its sessions.
     *
     * @return the preferred subprotocols, the empty list if none.
     */
    public List<String> getPreferredSubprotocols() {
        return this.preferredSubprotocols;
    }
    /**
     * set the preferred sub protocols for the configuration this builder will build. The
     * list is treated in order of preference, favorite first, that this client would
     * like to use for its sessions.
     * 
     * @param preferredSubprotocols the preferred subprotocol names.
     * @return the builder instance
     */
    public ClientEndpointConfigurationBuilder setPreferredSubprotocols(List<String> preferredSubprotocols) {
        this.preferredSubprotocols = (preferredSubprotocols == null) ? new ArrayList<String>() : preferredSubprotocols;
        return this;
    }



    /**
     * Return the extensions, in order of preference, favorite first, that this client would
     * like to use for its sessions.
     *
     * @return the extension list, the empty list if none.
     */
    public List<Extension> getExtensions() {
        return this.extensions;
    }
    /**
     * set the extensions for the configuration this builder will build. The 
     * list is treated in order of preference, favorite first, that the 
     * client would like to use for its sessions.
     * 
     * @param extensions the extensions
     * @return the builder instance
     */
    public ClientEndpointConfigurationBuilder setExtensions(List<Extension> extensions) {
        this.extensions = (extensions == null) ? new ArrayList<Extension>() : extensions;
        return this;
    }


    /**
     * Return the list of encoders this client will use.
     *
     * @return the encoder list, the empty list if none.
     */
    public List<Encoder> getEncoders() {
        return this.encoders;
    }
    
    /**
     * Assign the list of encoders the client will use.
     *
     * @param encoders the encoders
     * @return the builder instance
     */
    public ClientEndpointConfigurationBuilder setEncoders(List<Encoder> encoders) {
        this.encoders = (encoders == null) ? new ArrayList<Encoder>() : encoders;
        return this;
    }



    /**
     * Return the list of decoders this client will use.
     *
     * @return the decoders to use, the empty list if none.
     */
    public List<Decoder> getDecoders() {
        return this.decoders;
    }
    
    /**
     * Assign the list of decoders the client will use.
     *
     * @param decoders the decoders
     * @return this builder instance
     */
    public ClientEndpointConfigurationBuilder setDecoders(List<Decoder> decoders) {
        this.decoders = (decoders == null) ? new ArrayList<Decoder>() : decoders;
        return this;
    }
    
   
}
