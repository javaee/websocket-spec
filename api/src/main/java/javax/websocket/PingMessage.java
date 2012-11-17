/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket;

import java.nio.ByteBuffer;
/**
 * The PingMessage interface represents a web socket ping. PingMessages may be received by using
 * a MessageHandler.Basic<PingMessage>. The payload of the PingMessage is the application data sent by the peer.
 * 
 * @since v008
 * @author dannycoward
 */
public interface PingMessage {
    /** The application data inside the ping message from the peer.
     * 
     * @return the application data.
     */
    
   public ByteBuffer getApplicationData(); 
    
}
