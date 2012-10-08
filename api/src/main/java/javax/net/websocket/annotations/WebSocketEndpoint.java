/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package javax.net.websocket.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class level annotation declares that the class it decorates
 * is a web socket endpoint. The annotation allows the developer to
 * define the URL (or URI template) which this endpoint must be published, and other
 * important properties of the endpoint to the websocket runtime, such as the encoders 
 * it uses to send messages. <br><br>The annotated class
 * must have a public no-arg constructor.<br>
 * 
 * For example: <br><code><br>
 * 
 * &nbsp@WebSocketEndpoint("/hello");<br>
 * public class HelloServer {<br><br>
 * 
 * &nbsp&nbsp@WebSocketMessage<br>
 * &nbsppublic void processGreeting(String message, Session session) {<br>
 * &nbsp&nbsp&nbspSystem.out.println("Greeting received:" + message);<br>
 * &nbsp}<br>
 * }
 * </code>
 * 
 * 
 * @since Draft 002
 * @author dannycoward
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebSocketEndpoint {
    /** The URI or URI-template (level-1) where the endpoint will be deployed. The URI us relative to the
     * root of the web socket container. Examples:<br><code>
     * &nbsp@WebSocketEndpoint("/chat") <br>
     * &nbsp@WebSocketEndpoint("/chat/{user}") <br>
     * &nbsp@WebSocketEndpoint("/booking/{privilege-level}") <br>
     * </code>
     * 
     */
    public String value();
    /** The ordered array of web socket protocols this endpoint supports. For example, {'superchat', 'chat'}.*/
    public String[] subprotocols() default {};
    /** The ordered array of decoder classes this endpoint will use. For example,
     if the developer has provided a MysteryObject decoder, this endpoint will be able to
     receive MysteryObjects as web socket messages. The websocket runtime will use the first
     decoder in the list able to decode a message, ignoring the remaining decoders.*/
    public Class[] decoders() default {};
    /** The ordered array of encoder classes this endpoint will use. For example,
     if the developer has provided a MysteryObject encoder, this class will be able to
     send web socket messages in the form of MysteryObjects. The websocket runtime will use the first
     encoder in the list able to encode a message, ignoring the remaining encoders. */
    public Class[] encoders() default {};
}
