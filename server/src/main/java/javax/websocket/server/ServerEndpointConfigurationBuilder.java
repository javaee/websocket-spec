/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

import java.util.*;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Extension;


/**
 * The ServerEndpointConfigurationBuilder is a class used for creating
 * {@link ServerEndpointConfigurationBuilder} objects for the purposes of
 * deploying a client endpoint.
 * <br><br>Here are some examples:<br>
 * <br>Building a plain configuration for an endpoint with just a path.<br><br>
 * <code>
 * ServerEndpointConfiguration config = ServerEndpointConfigurationBuilder.create(ProgrammaticEndpoint.class, "/foo").build();
<br>
 *</code>
 * 
 *<br><br>Building a configuration with no subprotocols and a custom configurator.<br><br>
 * <code>
 * ServerEndpointConfiguration config = ServerEndpointConfigurationBuilder.create(ProgrammaticEndpoint.class, "/bar")<br>
                .setSubprotocols(subprotocols)<br>
                .setServerEndpointConfigurator(new MyServerConfigurator())<br>
                .build();<br>
 * </code>
 * 
 * @author dannycoward
 */
public final class ServerEndpointConfigurationBuilder {
    private String path;
    private Class endpointClass;
    private List<String> subprotocols = new ArrayList<String>();
    private List<Extension> extensions = new ArrayList<Extension>();
    private List<Encoder> encoders = new ArrayList<Encoder>();
    private List<Decoder> decoders = new ArrayList<Decoder>();
    private ServerEndpointConfigurator serverEndpointConfigurator;
    
    /**
     * Creates the builder with the mandatory information of the endpoint class 
     * (programmatic or annotated), the relative URI or URI-template to use,
     * and with no subprotocols, extensions, encoders, decoders or custom
     * configurator.
     * @param endpointClass the class of the endpoint to configure
     * @param path The URI or URI template where the endpoint will be deployed.
     * A trailing "/" will be ignored.
     * @return a new instance of ServerEndpointConfigurationBuilder
     */
    public static ServerEndpointConfigurationBuilder create(Class endpointClass, String path) {
        return new ServerEndpointConfigurationBuilder(endpointClass, path);
    }
    
    // only one way to build them
    private ServerEndpointConfigurationBuilder() {
        
    }
    /**
     * Builds the configuration object using the current attributes
     * that have been set on this builder object.
     * 
     * @return a new ServerEndpointConfiguration object.
     */
    public ServerEndpointConfiguration build() {
        return new DefaultServerEndpointConfiguration(
                this.endpointClass,
                this.path,
                Collections.unmodifiableList(this.subprotocols),
                Collections.unmodifiableList(this.extensions),
                Collections.unmodifiableList(this.encoders),
                Collections.unmodifiableList(this.decoders),
                this.serverEndpointConfigurator
             );
    }
    
    private ServerEndpointConfigurationBuilder(Class endpointClass, String path) {
        if (endpointClass == null) {
            throw new IllegalArgumentException("endpointClass cannot be null");
        }
        this.endpointClass = endpointClass;
        this.setPath(path);
    }
    
    /**
     * Returns the class of the programmatic or annotated endpoint.
     *
     * @return the class of the endpoint.
     */
    public Class getEndpointClass() {
        return this.endpointClass;
    }

    /**
     * Return the encoders for this builder. These
     * will be used by the container to encode outgoing messages.
     *
     * @return the encoders, an empty list if none.
     */
    public List<Encoder> getEncoders() {
        return this.encoders;
    }
    
    /** 
     * Sets the list of encoders for this builder.
     * 
     * @param encoders the encoders
     * @return this builder instance
     */
    public ServerEndpointConfigurationBuilder setEncoders(List<Encoder> encoders) {
        this.encoders = (encoders == null) ? new ArrayList<Encoder>() : encoders;
        return this;
    }

    /** 
     * Return the decoders to use in the configuration.
     * 
     * @return the decoders, the empty list if none.
     */ 
    public List<Decoder> getDecoders() {
        return this.decoders;
    }
    
    /**
     * Sets the decoders to use in the configuration.
     * 
     * @param decoders the decoders
     * @return this builder instance.
     */
    public ServerEndpointConfigurationBuilder setDecoders(List<Decoder> decoders) {
        this.decoders = (decoders == null) ? new ArrayList<Decoder>() : decoders;
        return this;
    }

    /** 
     * Returns the path (URI or URI-template) to use n the configuration.
     * 
     * @return the path, which is always non-null.
     */
    public String getPath() {
        return path;
    }
    
    /** 
     * Resets the path to use in the configuration. A trailing "/" will be ignored. 
     * 
     * @param path a string path representing the URI or URI-template
     * @return this builder instance
     * 
     * @throws IllegalArgumentException if the path parameter is null.
     */
    public ServerEndpointConfigurationBuilder setPath(String path) {
        if (path == null) {
            throw new IllegalStateException("Path cannot be null");
        }
        this.path = path;
        return this;
    }
    
    /**
     * Sets the subprotocols to use in the configuration.
     * 
     * @param subprotocols the subprotocols.
     * @return this builder instance
     */
    public ServerEndpointConfigurationBuilder setSubprotocols(List<String> subprotocols) {
        this.subprotocols = (subprotocols == null) ? new ArrayList<String>() : subprotocols;
        return this;
    }
    /**
     * Returns the subprotocols to use.
     * 
     * @return the subprotocols, the empty list if none.
     */
    public List<String> getSubprotocols() {
        return this.subprotocols;
    }
    
    /**
     * Sets the extensions to use in the configuration.
     * 
     * @param extensions the extensions to use.
     * @return this builder instance.
     */
    public ServerEndpointConfigurationBuilder setExtensions(List<Extension> extensions) {
        this.extensions = (extensions == null) ? new ArrayList<Extension>() : extensions;
        return this;
    }
    
    /**
     * Gets the extensions to use.
     * 
     * @return the extensions, the empty list if none.
     */
    public List<Extension> getExtensions() {
        return this.extensions;
    }
    
    /** 
     * 
     * Returns the custom configurator set on this builder.
     * 
     * @return the configurator, or null if none was set.
     */
    public ServerEndpointConfigurator getServerEndpointConfigurator() {
        return this.serverEndpointConfigurator;
    } 
    /** 
     * Sets the custom configurator to use on the configuration
     * object built by this builder.
     * 
     * @param serverEndpointConfigurator the configurator
     * @return this builder instance
     */
    public ServerEndpointConfigurationBuilder setServerEndpointConfigurator(ServerEndpointConfigurator serverEndpointConfigurator) {
        this.serverEndpointConfigurator = serverEndpointConfigurator;
        return this;
    }
    


}
