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
 *  This method level annotation can be used to decorate a Java method that wishes to be called in order
 * to handle errors. <br><br>
 * 
 *  The method may only take the following parameters:-<br><br>
 * - optional Session parameter<br>
 * - a Throwable  parameter<br>
 * - Zero to n String parameters annotated with the @WebSocketPathParam annotation.<br>
 * in any order.
 * <br>
 * @author dannycoward
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebSocketError {
    
}
