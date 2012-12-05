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
 * This method level annotation can be used to make a Java method receive incoming web socket messages. It is allowed to have
 * parameters of types described below, otherwise the container will generate an error at deployment time. Each websocket
 * endpoint may only have one message handling method for each of the native websocket message formats: text, binary and pong. <br>
 * a) Exactly one of any of the following choices <br>
 * - String for whole text message processing<br>
 * - String and boolean pair for partial text message processing<br>
 * - byte[] or ByteBuffer[] for whole binary message processing<br>
 * - byte[] and boolean pair, or ByteBuffer[] and boolean pair for partial binary message processing<br>
 * - any decodable object parameter (as determined by the Decoders configured for the endpoint) <br>
 * - PongMessage for handling Pong messages <br>
 * and<br>
 * b) Zero to n String parameters annotated with the @WebSocketPathParam annotation.<br>
 * c) optional Session parameter<br>
 * <p/>
 * The parameters may be listed in any order.<br><br>
 * The method may have a non-void return type, in which case the web socket runtime must interpret this as a
 * web socket message to return to the peer. The allowed data types for this return type, other than void, are
 * String, ByteBuffer, byte[], any java primitive or class equivalent,
 * plus anything for which there is a decoder.<br><br>
 * <p/>
 * For example: <br><code><br>
 * &nbsp@WebSocketMessage;<br>
 * public void processGreeting(String message, Session session) {<br>
 * &nbsp&nbspSystem.out.println("Greeting received:" + message);<br>
 * }<br></code>
 *
 * For example: <br><code><br>
 * &nbsp@WebSocketMessage;<br>
 * public void processUpload(byte[] b, boolean last, Session session) {<br>
 * &nbsp&nbsp// process partial data here, which check on last to see if these is more on the way<br>
 * }<br></code>
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
