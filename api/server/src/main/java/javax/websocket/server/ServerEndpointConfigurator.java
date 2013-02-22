/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;

   /** 
    * The ServerEndpointConfigurator interface may be implemented by developers who want to
    * provide custom configuration algorithms, such as intercepting the opening handshake, or
    * providing arbitrary methods and algorithms that can be accessed from each endpoint
    * instance configured with this configurator.
    * 
    * The implementation must provide a platform default configurator loading using the service
    * loader.
    */
public abstract class ServerEndpointConfigurator {
    private ServerEndpointConfigurator containerDefaultConfigurator;
    
    static ServerEndpointConfigurator fetchContainerDefaultConfigurator() {
        for (ServerEndpointConfigurator impl : ServiceLoader.load(ServerEndpointConfigurator.class)) {
            return impl;
        }
        throw new RuntimeException("Cannot load platform configurator");
    }
    
    ServerEndpointConfigurator getContainerDefaultConfigurator() {
        if (this.containerDefaultConfigurator == null) {
            this.containerDefaultConfigurator = fetchContainerDefaultConfigurator();
        }
        return this.containerDefaultConfigurator;
        
    }
    
    /**
     * Return the subprotocol the server endpoint has chosen from the requested
     * list supplied by a client who wishes to connect, or none if there wasn't one
     * this server endpoint liked. See
     * <a href="http://tools.ietf.org/html/rfc6455#section-4.2.2">Sending the 
     * Server's Opening Handshake</a>. Subclasses may provide custom algorithms 
     * based on other factors.
     * <br><br>
     * The default platform implementation of this method returns the first 
     * subprotocol in the list sent by the client that the server supports, 
     * or the empty string if there isn't one.
     * 
     * @param requested the requested subprotocols from the client endpoint
     * @param supported the subprotocols supported by the server endpoint
     * @return the negotiated subprotocol or the empty string if there isn't one.
     */
    
    public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
        return this.getContainerDefaultConfigurator().getNegotiatedSubprotocol(supported, requested);
    } 
    
    /**
     * Return the ordered list of extensions that t server endpoint will support 
     * given the requested extension list passed in, the empty list if none. See 
     * <a href="http://tools.ietf.org/html/rfc6455#section-9.1">Negotiating Extensions</a>
     * <br><br>
     * The default platform implementation of this method returns a list 
     * containing all of the requested extensions passed to this method that 
     * it supports, using the order in the requested extensions, the empty 
     * list if none.
     * 
     * @param installed the installed extensions on the implementation.
     * @param requested the requested extensions, in the order they were 
     * requested by the client
     * @return the list of extensions negotiated, the empty list if none.
     */
    public List<Extension> getNegotiatedExtensions(List<Extension> installed, List<Extension> requested) {
        return this.getContainerDefaultConfigurator().getNegotiatedExtensions(installed, requested);
    }
    
    
    

    /**
     * Check the value of the Origin header (<a href="http://tools.ietf.org/html/rfc6454">See Origin Header</a>) the client passed during the opening
     * handshake.
     * <br><br>
     * The platform default implementation of this method makes a check of the 
     * validity of the Origin header sent along with 
     * the opening handshake following the recommendation at: 
     * <a href="http://tools.ietf.org/html/rfc6455#section-4.2">Sending 
     * the Server's Opening Handshake<a>.
     * 
     * @param originHeaderValue the value of the origin header passed
     * by the client.
     * @return whether the check passed or not
     */
    public boolean checkOrigin(String originHeaderValue) {
        return this.getContainerDefaultConfigurator().checkOrigin(originHeaderValue);
    }
    
    /**
     * This default implementation matches the incoming path to the configuration's URI or URI template if and only if
     * it is an exact match in the case the configuration is a URI, and if and only if it is a valid
     * expansion of the configuration URI template, in the case where the configuration is a URI template. Subclasses may override this method to provide
     * different matching policies.
     *
     * @param uri the URL of the incoming request
     * @return whether it matched this configuration or not.
     */
     /**
     * Answers whether the incoming handshake request uri matches the
     * path of the server endpoint. This method may be overridden
     * by implementations with any number of algorithms for determining a match.
     * If the path is a URI-template, and the implementation of this method
     * determines there is a match of some kind, the implementation must add
     * the path variable and values to the template expansion map passed into 
     * this method.
     * <br><br>
     * The platform default implementation matches the incoming uri to the 
     * configuration's path if and only if it is an exact match 
     * in the case the path is a URI, and if and only if it is a valid
     * URI-template expansion of the path, in the case where the 
     * path is a URI template. In this latter case, all the path variables
     *  and values are added to the template expansion map.
     *
     * @param templateExpansion an empty Map to hold URI-template expansion
     * names and values.
     * @param path the path (URI or URI template) of the server endpoint
     * @param requestUri the uri of the incoming handshake.
     * @return whether there was a match
     */
    public boolean matchesURI(String path, URI requestUri, Map<String, String> templateExpansion) {
        return this.getContainerDefaultConfigurator().matchesURI(path, requestUri, templateExpansion);
    }
    
     /**
     * The default server configuration does not make any changes to the response. 
     * Subclasses may override this method in order to inspect the Http request 
     * headers of the opening handshake, for example to track cookies
     * sent by the client. Additionally subclasses may choose to override this 
     * method to modify the outgoing handshake response.
     * the outgoing handshake response
     *
     * @param request  the handshake request from the client
     * @param response the handshake response formulated by the container.
     */
    
    /**
     * Called by the container after it has formulated a handshake response resulting from
     * a well-formed handshake request. The container has already has already 
     * checked that this configuration has a matching URI, determined the 
     * validity of the origin using the checkOrigin method, and filled
     * out the negotiated subprotocols and extensions based on this configuration.
     * Custom configurations may override this method in order to inspect
     * the request parameters and modify the handshake response that the server has formulated.
     * and the URI checking also.
     * <br><br>
     * If the developer does not override this method, no further
     * modification of the request and response are made by the implementation.
     * 
     * @param request  the opening handshake request.
     * @param response the proposed opening handshake response
     */
    public void modifyHandshake(ServerEndpointConfiguration sec, HandshakeRequest request, HandshakeResponse response) {
        // nothing.
    }

}
