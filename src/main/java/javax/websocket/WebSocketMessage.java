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
 * This method level annotation can be used to make a Java method receive incoming web socket messages. Each websocket
 * endpoint may only have one message handling method for each of the native websocket message formats: text, binary and pong. Methods
 * using this annotation are allowed to have
 * parameters of types described below, otherwise the container will generate an error at deployment time. <br>
 * The allowed parameters are:
 * <ol>
 * <li>Exactly one of any of the following choices</li>
 * <ul>
 * <li> String for whole text message processing</li>
 * <li> String and boolean pair for partial text message processing</li>
 * <li> byte[] or ByteBuffer for whole binary message processing</li>
 * <li> byte[] and boolean pair, or ByteBuffer and boolean pair for partial binary message processing</li>
 * <li> any decodable object parameter (as determined by the Decoders configured for the endpoint)</li>
 * <li> PongMessage for handling Pong messages</li>
 * </ul>
 * <li> and Zero to n String or Java primitive parameters
 * annotated with the @WebSocketPathParam annotation for server endpoints.</li>
 * <li> and an optional Session parameter</li>
 * </ol>
 * <p/>
 * The parameters may be listed in any order.<br><br>
 * The method may have a non-void return type, in which case the web socket runtime must interpret this as a
 * web socket message to return to the peer. The allowed data types for this return type, other than void, are
 * String, ByteBuffer, byte[], any java primitive or class equivalent,
 * plus anything for which there is a decoder.<br><br>
 * <p/>
 * For example:
 * <pre>
 * <code>
 * &nbsp@WebSocketMessage
 * public void processGreeting(String message, Session session) {
 * &nbsp&nbspSystem.out.println("Greeting received:" + message);
 * }
 * </code>
 * </pre>
 * For example:
 * <pre>
 * <code>
 * &nbsp@WebSocketMessage
 * public void processUpload(byte[] b, boolean last, Session session) {
 * &nbsp&nbsp// process partial data here, which check on last to see if these is more on the way
 * }
 * </code>
 * </pre>
 *
 * @author dannycoward
 * @since Draft 002
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebSocketMessage {

    /**
     * Specifies the maximum size of message in bytes that the method
     * this annotates will be able to process, or -1 to indicate
     * that there is no maximum. The default is -1.
     *
     * @return the maximum size in bytes.
     */
    public long maxMessageSize() default -1;
}
