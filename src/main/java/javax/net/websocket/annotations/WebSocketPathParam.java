/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.net.websocket.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation may be used to annotate method parameters on web socket POJOs
 * where a URI-template has been used in the path-mapping of the WebSocketEndpoint
 * annotation.<br> For example:-
 * * For example: <br><code><br>
 * 
 * &nbsp@WebSocketEndpoint("/bookings/{guest-id}");<br>
 * public class BookingServer {<br><br>
 * 
 * &nbsp&nbsp@WebSocketMessage<br>
 * &nbsppublic void processBookingRequest(@WebSocketPathParam("guest-id") String guestID, String message, Session session) {<br>
 * &nbsp&nbsp&nbsp// process booking from the given guest here<br>
 * &nbsp}<br>
 * }
 * </code>
 * 
 * @author dannycoward
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebSocketPathParam {
    /** The name of the variable used in the URI-template. If the name does
     not match a path variable in the URI-template, the value of the method parameter
     this annotation annotates is null.
     @return the value */
    public String value();
  
}
