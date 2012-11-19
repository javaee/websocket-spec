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
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Web Socket session represents a conversation between two web socket endpoints. As soon
 * as the websocket handshake completes successfully, the web socket implementation provides
 * the endpoint an active websocket session. The endpoint can then register interest in incoming
 * messages that are part of this newly created conversation by providing a MessageHandler to the
 * session, and can send messages to the other end of the conversation by means of the RemoteEndpoint object
 * obtained from this session.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface Session {

    /**
     * Return the container that this session is part of.
     *
     * @return the container
     */
    ClientContainer getContainer();

    /**
     * Sets the list of encoders to be used in this session in order of preference.
     * The first element in the list that matches for a given type
     * will be used rather than a later element in the list that matches for a given type.
     *
     * @param encoders the list of encoders.
     */
    void setEncoders(List<Encoder> encoders);

    /**
     * Register to handle to incoming messages in this conversation.
     *
     * @param listener the MessageHandler to be added.
     */
    void addMessageHandler(MessageHandler listener);

    /**
     * Return an unmodifiable copy of the set of MessageHandlers for this Session.
     *
     * @return the set of message handlers.
     */
    Set<MessageHandler> getMessageHandlers();

    /**
     * Remove the given MessageHandler from the set belonging to this session.
     *
     * @param listener the handler to be removed.
     *                 <bold>TBD</bold> Threading issues wrt handler invocations vs removal
     */
    void removeMessageHandler(MessageHandler listener);

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
    List<String> getNegotiatedExtensions();

    /**
     * Return true if and only if the underlying socket is using a secure transport.
     *
     * @return whether its using a secure transport.
     */
    boolean isSecure();

    /**
     * Return the number of seconds since the underlying connection had any activity.
     *
     * @return the inactive time.
     */
    long getInactiveTime();

    /**
     * Return true if and only if the underlying socket is open.
     *
     * @return whether the session is active.
     */
    boolean isActive();

    /**
     * Return the number of milliseconds before this conversation will be closed by the
     * container if it is inactive, ie no messages are either sent or received in that time.
     *
     * @return the timeout in milliseconds.
     */
    long getTimeout();

    /**
     * Set the number of milliseconds before this conversation will be closed by the
     * container if it is inactive, ie no messages are either sent or received.
     *
     * @param seconds the number of milliseconds.
     */
    void setTimeout(long seconds);

    /**
     * Sets the maximum total length of messages, text or binary, that this Session can handle.
     *
     * @param length the maximum length
     */
    void setMaximumMessageSize(long length);

    /**
     * The maximum total length of messages, text or binary, that this Session can handle.
     *
     * @return the message size.
     */
    long getMaximumMessageSize();

    /**
     * Return a reference to the RemoteEndpoint object representing the other end of this conversation.
     *
     * @return the remote endpoint.
     */
    RemoteEndpoint getRemote();


    /**
     * Close the current conversation with a normal status code and no reason phrase.
     */
    void close() throws IOException;

    /**
     * Close the current conversation, giving a reason for the closure. Note the websocket spec defines the
     * acceptable uses of status codes and reason phrases.
     *
     * @param closeStatus the reason for the closure.
     */
    void close(CloseReason closeStatus) throws IOException;

    /**
     * Return the URI that this session was opened under.
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
    Map<String, String[]> getRequestParameterMap();

    /**
     * Return the query string associated with the request this session
     * was opened under.
     *
     * @return
     */
    String getQueryString();


    /**
     * Return a map of the path parameter names and values used if the server
     * endpoint was deployed with a URI-template and the client connected with a
     * particular matching URL.
     *
     * @return the unmodifiable map of path parameters. The key of the map is the parameter name,
     *         the values in the map are the parameter values.
     */
    Map<String, String> getPathParameters();


}
