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

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

/**
 * The RemoteEndpoint object is supplied by the container and represents the 'other end' of the Web Socket conversation.
 * In particular, objects of this kind include numerous ways to send web socket messages. There is no guarantee of the success
 * of receipt of a web socket message, but if the action of sending a message causes a known error, the API throws it.
 * This object includes a variety of ways to send messages to the other end of a web socket session: by whole message, in pieces
 * and asynchronously, where the point of completion is defined when all the supplied data had been written to the underlying connection.
 * The completion handlers for the asynchronous methods are always called with a different thread from that which initiated the send.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface RemoteEndpoint {


    /**
     * Indicate to the implementation that it is allowed to batch outgoing messages
     * before sending. Not all implementations support batching of outgoing messages.
     * The default mode for RemoteEndpoints is false. If the developer
     * has indicated that batching of outgoing
     * messages is permitted, then the developer must call flushBatch() in order to be
     * sure that all the messages passed into the send methods of this RemoteEndpoint
     * are sent. If batching is allowed, if the developer has called send methods
     * on this RemoteEndpoint without calling flushBatch(), then the implementation
     * may not have sent all the messages the developer has asked to be sent. If
     * the parameter value is false and the implementation has a batch of unsent messages,
     * then the implementation must immediately send the batch of unsent messages.
     *
     * @param allowed whether the implementation is allowed to batch messages.
     */
    void setBatchingAllowed(boolean allowed);

    /**
     * Return whether the implementation is allowed to batch outgoing messages
     * before sending. The default mode for RemoteEndpoints is false. The value
     * may be changed by calling {@link #setBatchingAllowed(boolean) setBatchingAllowed}.
     */
    boolean getBatchingAllowed();

    /**
     * This method is only used when batching is allowed for this RemoteEndpint. Calling
     * this method forces the implementation to send any unsent messages it has been batching.
     */
    void flushBatch();

    /**
     * Return the number of milliseconds the implementation will timeout
     * attempting to send a websocket message. A non-positive number indicates
     * the implementation will not timeout attempting to send a websocket message
     * asynchronously. This value overrides the default value assigned in the
     * WebSocketContainer.
     *
     * @return the timeout time in milliseconds.
     */
    long getAsyncSendTimeout();

    /**
     * Sets the number of milliseconds the implementation will timeout
     * attempting to send a websocket message. A non-positive number indicates
     * the implementation will not timeout attempting to send a websocket message
     * asynchronously. This value overrides the default value assigned in the
     * WebSocketContainer.
     *
     * @param timeoutmillis The number of milliseconds this RemoteEndpoint will wait before timing out
     * an incomplete asynchronous message send.
     */
    void setAsyncSendTimeout(long timeoutmillis);

    /**
     * Send a text message, blocking until all of the message has been transmitted.
     *
     * @param text the message to be sent.
     * @throws IOException if there is a problem delivering the message.
     */
    void sendString(String text) throws IOException;

    /**
     * Send a binary message, returning when all of the message has been transmitted.
     *
     * @param data the message to be sent.
     * @throws IOException if there is a problem delivering the message.
     */
    void sendBytes(ByteBuffer data) throws IOException;

    /**
     * Send a text message in pieces, blocking until all of the message has been transmitted. The runtime
     * reads the message in order. Non-final pieces are sent with isLast set to false. The final piece
     * must be sent with isLast set to true.
     *
     * @param fragment the piece of the message being sent.
     * @param isLast   Whether the fragment being sent is the last piece of the message.
     * @throws IOException if there is a problem delivering the message fragment.
     */
    void sendPartialString(String fragment, boolean isLast) throws IOException;

    /**
     * Send a binary message in pieces, blocking until all of the message has been transmitted. The runtime
     * reads the message in order. Non-final pieces are sent with isLast set to false. The final piece
     * must be sent with isLast set to true.
     *
     * @param partialByte the piece of the message being sent.
     * @param isLast      Whether the fragment being sent is the last piece of the message.
     * @throws IOException if there is a problem delivering the message fragment.
     */
    void sendPartialBytes(ByteBuffer partialByte, boolean isLast) throws IOException; // or Iterable<byte[]>

    /**
     * Opens an output stream on which a binary message may be sent. The developer must close the output stream in order
     * to indicate that the complete message has been placed into the output stream.
     *
     * @return the output stream to which the message will be written.
     * @throws IOException if there is a problem obtaining the OutputStream to write the binary message.
     */
    OutputStream getSendStream() throws IOException;

    /**
     * Opens an character stream on which a text message may be sent. The developer must close the writer in order
     * to indicate that the complete message has been placed into the character stream.
     *
     * @return the writer to which the message will be written.
     * @throws IOException if there is a problem obtaining the Writer to write the text message.
     */
    Writer getSendWriter() throws IOException;

    /**
     * Sends a custom developer object, blocking until it has been transmitted. Containers will by default be able to encode
     * java primitive types, their object equivalents, and arrays or collections thereof. The developer will have provided an encoder for this object
     * type in the endpoint configuration.
     *
     * @param o the object to be sent.
     * @throws IOException if there is a communication error sending the message object.
     * @throws EncodeException if there was a problem encoding the message object into the form of a native websocket message.
     */
    void sendObject(Object o) throws IOException, EncodeException;

    /**
     * Initiates the asynchronous transmission of a text message. This method returns before the message
     * is transmitted. Developers provide a callback to be notified when the message has been
     * transmitted. Errors
     * in transmission are given to the developer in the SendResult object.
     *
     * @param text       the text being sent.
     * @param completion the handler which will be notified of progress.
     */
    void sendStringByCompletion(String text, SendHandler completion);

    /**
     * Initiates the asynchronous transmission of a text message. This method returns before the message
     * is transmitted. Developers use the returned Future object to track progress of the transmission. Errors
     * in transmission are given to the developer in the SendResult object.
     *
     * @param text the text being sent.
     * @return the Future object representing the send operation.
     */
    Future<SendResult> sendStringByFuture(String text);

    /**
     * Initiates the asynchronous transmission of a binary message. This method returns before the message
     * is transmitted. Developers use the returned Future object to track progress of the transmission. Errors
     * in transmission are given to the developer in the SendResult object.
     *
     * @param data the data being sent.
     * @return the Future object representing the send operation.
     */
    Future<SendResult> sendBytesByFuture(ByteBuffer data);

    /**
     * Initiates the asynchronous transmission of a binary message. This method returns before the message
     * is transmitted. Developers provide a callback to be notified when the message has been
     * transmitted. Errors
     * in transmission are given to the developer in the SendResult object.
     *
     * @param data       the data being sent.
     * @param completion the handler that will be notified of progress.
     */
    void sendBytesByCompletion(ByteBuffer data, SendHandler completion);

    /**
     * Initiates the asynchronous transmission of a custom developer object. The developer will have provided an encoder for this object
     * type in the endpoint configuration. Containers will by default be able to encode
     * java primitive types, their object equivalents, and arrays or collections thereof. Progress is be tracked using the Future object.
     *
     * @param o the object being sent.
     * @return the Future object representing the send operation.
     */
    Future<SendResult> sendObjectByFuture(Object o);

    /**
     * Initiates the asynchronous transmission of a custom developer object. The developer will have provided an encoder for this object
     * type in the endpoint configuration. Containers will by default be able to encode
     * java primitive types, their object equivalents, and arrays or collections thereof. Developers are notified when transmission is complete
     * through the supplied callback object.
     *
     * @param o       the object being sent.
     * @param handler the handler that will be notified of progress.
     */
    void sendObjectByCompletion(Object o, SendHandler handler);

    /**
     * Send a Ping message containing the given application data to the remote endpoint. The corresponding Pong message may be picked
     * up using the MessageHandler.Pong handler.
     *
     * @param applicationData the data to be carried in the ping request.
     */
    void sendPing(ByteBuffer applicationData);

    /**
     * Allows the developer to send an unsolicited Pong message containing the given application
     * data in order to serve as a unidirectional
     * heartbeat for the session.
     *
     * @param applicationData the application data to be carried in the pong response.
     */
    void sendPong(ByteBuffer applicationData);
}

