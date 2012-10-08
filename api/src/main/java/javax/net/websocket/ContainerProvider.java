/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.net.websocket;

/**
 * Provider class that uses the ServiceLoader mechanism to provide
 * implementations of ServerContainer and ClientContainer.
 * @author dannycoward
 */
public class ContainerProvider {
   
    public static ServerContainer getServerContainer() {
        return null;
    }
    
    public static ClientContainer getClientContainer() {
        return null;
    }
}
