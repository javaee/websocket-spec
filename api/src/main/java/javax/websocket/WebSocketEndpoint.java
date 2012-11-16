/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package javax.websocket;

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
    public Class<?extends Decoder>[] decoders() default {};
    /** The ordered array of encoder classes this endpoint will use. For example,
     if the developer has provided a MysteryObject encoder, this class will be able to
     send web socket messages in the form of MysteryObjects. The websocket runtime will use the first
     encoder in the list able to encode a message, ignoring the remaining encoders. */
    public Class<?extends Encoder>[] encoders() default {};
}
