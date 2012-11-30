/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket;

/**
 * The EndpointFactory is used by logical endpoints that will be deployed on a server. Its purpose is
 * to create a new instance of the Endpoint each time the implementation needs one because a
 * new peer is connecting to the logical endpoint. The parametrized type T defines
 * the type of the endpoint that will be created. EndpointFactories are supplied by the developer
 * by returning an instance on the ServerEndpointConfiguration, or by referencing the EndpointFactory
 * class from the @WebSocketEndpoint annotation.
 * @author dannycoward
 */
public interface EndpointFactory<T> {

    /**
     * Create an instance of the Endpoint which is of type T.
     * @return
     */
    public T createEndpoint();
}
