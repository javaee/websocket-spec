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
 * This method level annotation can be used to make a Java method receive incoming web socket messages. It must have 
 * parameters of the following types otherwise the container will generate an error at deployment time<br>
 * String / byte[] / or decodable (as determined by the Decoders configured for the endpoint) parameter<br>
 * Optional Session parameter<br>
 * Zero to n String parameters annotated with the @WebSocketPathParam annotation.<br><br>
 * 
 * The parameters may be listed in any order.<br><br>
 * The method may have a non-void return type, in which case the web socket runtime must interpret this as a 
 * web socket message to return to the peer. The allowed data types for this return type, other than void, are
 * String, ByteBuffer, byte[], any java primitive or class equivalent, and array or Collection of any of the previous types, 
 * plus anything for which there is a decoder.<br><br>
 * 
 * For example: <br><code><br>
 * &nbsp@WebSocketMessage;<br>
 * public void processGreeting(String message, Session session) {<br>
 * &nbsp&nbspSystem.out.println("Greeting received:" + message);<br>
 * }<br></code>
 * 
 * 
 * 
 * 
 * @author dannycoward
 * @since Draft 002
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebSocketMessage {
    /** Specifies the maximum size of message in bytes that the method
     * this annotates will be able to process, or -1 to indicate
     * that there is no maximum. The default is -1.
     * @return the maximum size in bytes.
     */
    public long maxMessageSize() default -1;
}
