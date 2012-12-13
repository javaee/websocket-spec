/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket.server;

/**
 * IN PROGRESS
 * @author dannycoward
 */
public interface ServerApplicationConfiguration {

    Class<? extends ServerEndpointConfiguration>[] getEndpoints();
    Class[] getPojos();

}
