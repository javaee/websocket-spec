/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.net.websocket;

/**
 *  http://java.net/jira/browse/WEBSOCKET_SPEC-47
 * @author dannycoward
 */
public class ConnectException extends Exception {
    
    
    public ConnectException(String message) {
        super(message);
    }
        
    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }
}
