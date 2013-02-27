/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ClientEndpointConfigBuilder is a class used for creating
 * {@link ClientEndpointConfig} objects for the purposes of
 * deploying a client endpoint.
 * <br><br>Here are some examples:<br>
 * <br>Building a plain configuration with no encoders, decoders, subprotocols or extensions.<br><br>
 * <code>
 * ClientEndpointConfig cec = ClientEndpointConfigBuilder.create().build();<br>
 *</code>
 * 
 *<br><br>Building a configuration with no subprotocols and a custom configurator.<br><br>
 * <code>
 * ClientEndpointConfig customCec = ClientEndpointConfigBuilder.create()<br>
 *  &nbsp;&nbsp;             .preferredSubprotocols(mySubprotocols)<br>
 *  &nbsp;&nbsp;             .configurator(new MyClientConfigurator())<br>
 *  &nbsp;&nbsp;             .build();<br>
 * </code>
 * 
 * 
 * @author dannycoward
 */
public class ClientEndpointConfigBuilder {
    private List<String> preferredSubprotocols = new ArrayList<String>();
    private List<Extension> extensions = new ArrayList<Extension>();
    private List<Encoder> encoders = new ArrayList<Encoder>();
    private List<Decoder> decoders = new ArrayList<Decoder>();
    private ClientEndpointConfig.Configurator clientEndpointConfigurator = new ClientEndpointConfig.Configurator() {

    };
    
    /**
     * Creates a new builder object with no subprotocols, extensions, encoders,
     * decoders and a null configurator.
     * 
     * @return a new builder object.
     */
    public static ClientEndpointConfigBuilder create() {
        return new ClientEndpointConfigBuilder();
    }
    
    /**
     * Builds a configuration object using the attributes set
     * on this builder.
     * 
     * @return a new configuration object.
     */
    public ClientEndpointConfig build() {
        return new DefaultClientEndpointConfig(
            Collections.unmodifiableList(this.preferredSubprotocols),
            Collections.unmodifiableList(this.extensions),
            Collections.unmodifiableList(this.encoders),
            Collections.unmodifiableList(this.decoders),
            this.clientEndpointConfigurator);
    }
    

    
    /**
     * Sets the configurator object for the configuration this builder will build.
     * 
     * @param clientEndpointConfigurator the configurator
     * @return the builder instance
     */
    public ClientEndpointConfigBuilder configurator(ClientEndpointConfig.Configurator clientEndpointConfigurator) {
        this.clientEndpointConfigurator = clientEndpointConfigurator;
        return this;
    }


    /**
     * Set the preferred sub protocols for the configuration this builder will build. The
     * list is treated in order of preference, favorite first, that this client would
     * like to use for its sessions.
     * 
     * @param preferredSubprotocols the preferred subprotocol names.
     * @return the builder instance
     */
    public ClientEndpointConfigBuilder preferredSubprotocols(List<String> preferredSubprotocols) {
        this.preferredSubprotocols = (preferredSubprotocols == null) ? new ArrayList<String>() : preferredSubprotocols;
        return this;
    }


    /**
     * Set the extensions for the configuration this builder will build. The 
     * list is treated in order of preference, favorite first, that the 
     * client would like to use for its sessions.
     * 
     * @param extensions the extensions
     * @return the builder instance
     */
    public ClientEndpointConfigBuilder extensions(List<Extension> extensions) {
        this.extensions = (extensions == null) ? new ArrayList<Extension>() : extensions;
        return this;
    }

    /**
     * Assign the list of encoders the client will use.
     *
     * @param encoders the encoders
     * @return the builder instance
     */
    public ClientEndpointConfigBuilder encoders(List<Encoder> encoders) {
        this.encoders = (encoders == null) ? new ArrayList<Encoder>() : encoders;
        return this;
    }

    /**
     * Assign the list of decoders the client will use.
     *
     * @param decoders the decoders
     * @return this builder instance
     */
    public ClientEndpointConfigBuilder decoders(List<Decoder> decoders) {
        this.decoders = (decoders == null) ? new ArrayList<Decoder>() : decoders;
        return this;
    }
    
   
}
