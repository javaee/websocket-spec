/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012-2013 Oracle and/or its affiliates. All rights reserved.
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

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A Web Socket session represents a conversation between two web socket endpoints. As soon
 * as the websocket handshake completes successfully, the web socket implementation provides
 * the endpoint an open websocket session. The endpoint can then register interest in incoming
 * messages that are part of this newly created session by providing a MessageHandler to the
 * session, and can send messages to the other end of the conversation by means of the RemoteEndpoint object
 * obtained from this session.<br>
 * 
 * Once the session is closed, it is no longer valid for use by applications. Calling any of
 * its methods once the session has been closed will result in an {@link java.lang.IllegalStateException} being thrown.
 * Developers should retrieve any information from the session during the 
 * {@link Endpoint#onClose(javax.websocket.Session, javax.websocket.CloseReason) } method.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface Session extends Closeable {

    /**
     * Return the container that this session is part of.
     *
     * @return the container.
     */
    WebSocketContainer getContainer();

    /**
     * Register to handle to incoming messages in this conversation. A maximum of one message handler per
     * native websocket message type (text, binary, pong) may be added to each Session. I.e. a maximum 
     * of one message handler to handle incoming text messages a maximum of one message handler for 
     * handling incoming binary messages, and a maximum of one for handling incoming pong
     * messages. For further details of which message handlers handle which of the native websocket
     * message types please see {@link MessageHandler.Basic} and {@link MessageHandler.Async}. 
     * Adding more than one of any one type will result in a runtime exception.
     *
     * @param handler the MessageHandler to be added.
     * @throws IllegalStateException if there is already a MessageHandler registered for the same native
     *                               websocket message type as this handler.
     */
    void addMessageHandler(MessageHandler handler) throws IllegalStateException;

    /**
     * Return an unmodifiable copy of the set of MessageHandlers for this Session.
     *
     * @return the set of message handlers.
     */
    Set<MessageHandler> getMessageHandlers();

    /**
     * Remove the given MessageHandler from the set belonging to this session. This method may block
     * if the given handler is processing a message until it is no longer in use.
     *
     * @param handler the handler to be removed.
     */
    void removeMessageHandler(MessageHandler handler);

    /**
     * Returns the version of the websocket protocol currently being used. This is taken
     * as the value of the Sec-WebSocket-Version header used in the opening handshake. i.e. "13".
     *
     * @return the protocol version.
     */
    String getProtocolVersion();

    /**
     * Return the sub protocol agreed during the websocket handshake for this conversation.
     *
     * @return the negotiated subprotocol.
     */
    String getNegotiatedSubprotocol();

    /**
     * Return the list of extensions currently in use for this conversation.
     *
     * @return the negotiated extensions.
     */
    List<Extension> getNegotiatedExtensions();

    /**
     * Return true if and only if the underlying socket is using a secure transport.
     *
     * @return whether its using a secure transport.
     */
    boolean isSecure();

    /**
     * Return true if and only if the underlying socket is open.
     *
     * @return whether the session is active.
     */
    boolean isOpen();

    /**
     * Return the number of milliseconds before this conversation may be closed by the
     * container if it is inactive, ie no messages are either sent or received in that time.
     *
     * @return the timeout in milliseconds.
     */
    long getTimeout();

    /**
     * Set the non-zero number of milliseconds before this conversation will be closed by the
     * container if it is inactive, ie no messages are either sent or received. If the value passed is
     * 0 or negative, this indicates the session will never timeout due to inactivity.
     *
     * @param milliseconds the number of milliseconds.
     */
    void setTimeout(long milliseconds);

    /**
     * Sets the maximum length of incoming binary messages that this Session can buffer.
     *
     * @param length the maximum length.
     */
    void setMaxBinaryMessageBufferSize(int length);

    /**
     * The maximum length of incoming binary messages that this Session can buffer.
     *
     * @return the message size.
     */
    int getMaxBinaryMessageBufferSize();
    
    /**
     * Sets the maximum length of incoming text messages that this Session can buffer.
     *
     * @param length the maximum length.
     */
    void setMaxTextMessageBufferSize(int length);

    /**
     * The maximum length of incoming text messages that this Session can buffer.
     *
     * @return the message size.
     */
    int getMaxTextMessageBufferSize();    
    

    /**
     * Return a reference to the RemoteEndpoint object representing the other end of this conversation.
     *
     * @return the remote endpoint.
     */
    RemoteEndpoint getRemote();

    /**
     * Returns a string containing the unique identifier assigned to this session.
     * The identifier is assigned by the web socket implementation and is implementation dependent.
     *
     * @return the unique identifier for this session instance.
     */
    String getId();

    /**
     * Close the current conversation with a normal status code and no reason phrase.
     *
     * @throws IOException if there was a connection error closing the connection.
     */
    void close() throws IOException;

    /**
     * Close the current conversation, giving a reason for the closure. Note the websocket spec defines the
     * acceptable uses of status codes and reason phrases. If the application cannot
     * determine a suitable close code to use for the closeReason, it is recommended
     * to use {@link CloseReason.CloseCodes.NO_STATUS_CODE}.
     *
     * @param closeReason the reason for the closure.
     * @throws IOException if there was a connection error closing the connection
     */
    void close(CloseReason closeReason) throws IOException;

    /**
     * Return the URI under which this session was opened.
     *
     * @return the request URI.
     */
    URI getRequestURI();

    /**
     * Return the request parameters associated with the request this session
     * was opened under.
     *
     * @return the unmodifiable map of the request parameters.
     */
    Map<String, List<String>> getRequestParameterMap();

    /**
     * Return the query string associated with the request this session
     * was opened under.
     *
     * @return the query string
     */
    String getQueryString();

    /**
     * Return a map of the path parameter names and values used associated with the
     * request this session was opened under.
     *
     * @return the unmodifiable map of path parameters. The key of the map is the parameter name,
     *         the values in the map are the parameter values.
     */
    Map<String, String> getPathParameters();

    /**
     * While the session is open, this method returns a Map that the developer may
     * use to store application specific information relating to this session
     * instance. The developer may retrieve information from this Map at any time
     * between the opening of the session and during the onClose() method. But outside
     * that time, any information stored using this Map may no longer be kept by the
     * container. Web socket applications running on distributed implementations of
     * the web container should make any application specific objects stored here
     * java.io.Serializable, or the object may not be recreated after a failover.
     *
     * @return an editable Map of application data.
     */
    Map<String, Object> getUserProperties();

    /**
     * Return the authenticated user for this Session or null if no user is authenticated for this session.
     *
     * @return the user principal.
     */
    Principal getUserPrincipal();
}
