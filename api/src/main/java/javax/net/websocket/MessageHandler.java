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
package javax.net.websocket;

import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;


/**
 * Developers implement MessageHandlers in order to receive incoming messages
 * during a web socket conversation.
 * Each web socket session uses no more than one thread at a time to call its MessageHandlers. This means
 * that, provided each message handler instance is used to handle messages for one web socket session, at most
 * one thread at a time can be calling any of its methods. Developers who wish to handle messages from multiple
 * clients within the same message handlers may do so by adding the same instance as a handler on each of the Session
 * objects for the clients. In that case, they will need to code with the possibility of their MessageHandler
 * being called concurrently by multiple threads, each one arising from a different client session.
 * @since DRAFT 001
 * @author dannycoward
 */
public interface MessageHandler {

    /** This kind of listener listens for text messages. If the message is received in parts,
     * the container buffers it until it is has been fully received before this method is called.
     * @since DRAFT 002
     */
     interface Text extends MessageHandler {
         /** Called when the text message has been fully received.
         * @param text the binary message data.
         */
         void onMessage(String text);
    }
    /** This kind of listener listens for binary messages. If the message is received in parts,
     * the container buffers it until it is has been fully received before this method is called.
     * @since DRAFT 002
     */
     interface Binary extends MessageHandler {
        /** Called when the binary message has been fully received.
         * @param data the binary message data.
         */
         void onMessage(ByteBuffer data);
    }

    /** This kind of handler is called to process for binary messages which may arrive in multiple parts. A single binary
     * message may consist of 0 to n calls to this method where @param last is false followed by a single call with @param last set to
     * true. Messages do not interleave and the parts arrive in order.
     */

     interface AsyncBinary extends MessageHandler {
         /** Called when part of a binary message has been received.
         *
         * @param part The fragment of the message received.
         * @param last Whether or not this is last in the sequence of parts of the message.
         */
          void onMessagePart(ByteBuffer part, boolean last);
    }

     /** This kind of handler is called to process for text messages which may arrive in multiple parts. A single text
     * message may consist of 0 to n calls to this method where @param last is false followed by a single call with @param last set to
     * true. Messages do not interleave and the parts arrive in order.
     */

     interface AsyncText extends MessageHandler {
        /** Called when part of a text message has been received.
         *
         * @param part The fragment of the message received.
         * @param last Whether or not this is last in the sequence of parts of the message.
         */
         void onMessagePart(String part, boolean last);
    }

     /** This kind of listener listens for messages that the container knows how to decode into an object of type T.
      * This will involve providing the endpoint configuration a decoder for objects of type T.
      * @since DRAFT 002
     */
     interface DecodedObject<T> extends MessageHandler {
        /** Called when the container receives a message that it has been able to decode
         * into an object of type T. Containers will by default be able to encode
         * java primitive types, their object equivalents, and arrays or collections thereof.
         * @param customObject the message being sent.
         */
         void onMessage(T customObject);
    }
     /** This kind of handler is called when a new binary message arrives that is to be read using a blocking stream.
      * @since DRAFT 002
     */
     interface BinaryStream extends MessageHandler {
        /** This method is called when a new binary message has begun to arrive. The InputStream passed in allows
         * implementors of this handler to read the message in a blocking manner. The read methods on the
         * InputStream block until message data is available. A new input stream is created for each incoming
         * message.
         * @param is the input stream containing the message.
         */
         void onMessage(InputStream is);
    }
      /** This kind of handler is called when a new text message arrives that is to be read using a blocking stream.
      * @since DRAFT 002
     */
     interface CharacterStream extends MessageHandler {
         /** This method is called when a new text message has begun to arrive. The Reader passed in allows
         * implementors of this handler to read the message in a blocking manner. The read methods on the
         * Reader block until message data is available. A new reader is created for each incoming
         * message.
         * @param r the reader containing the message.
         */

         void onMessage(Reader r);
    }

    /** This handler is called back by the container when the container receives a pong message. */
     interface Pong extends MessageHandler {
        /** Called when the container receives a pong message containing the given application data. */
         void onPong(ByteBuffer applicationData);
    }


}
