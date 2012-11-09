/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket;

import java.nio.ByteBuffer;
/**
 *
 * @author dannycoward
 */
public interface PongMessage {
    
   public ByteBuffer getApplicationData(); 
    
}
