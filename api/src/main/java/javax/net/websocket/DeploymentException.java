/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.net.websocket;

/**
 *  http://java.net/jira/browse/WEBSOCKET_SPEC-47
 * @author dannycoward
 */
public class DeploymentException extends Exception {
    
    
    public DeploymentException(String message) {
        super(message);
    }
        
    public DeploymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
