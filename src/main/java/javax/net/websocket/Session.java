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
package javax.net.websocket;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.websocket.extensions.*;

/**
 * A Web Socket session represents a conversation between two web socket endpoints. As soon 
 * as the websocket handshake completes successfully, the web socket implementation provides
 * the endpoint an active websocket session. The endpoint can then register interest in incoming 
 * messages that are part of this newly created conversation by providing a MessageHandler to the
 * session, and can send messages to the other end of the conversation by means of the RemoteEndpoint object
 * obtained from this session.
 * @author dannycoward
 * @since DRAFT 001
 */
public interface Session<T> {
    
    /** Return the container that this session is part of.
     @return the container
     */
    public ClientContainer getContainer();
    
    /** Sets the list of encoders to be used in this session in order of preference.
     * The first element in the list that matches for a given type
     * will be used rather than a later element in the list that matches for a given type.
     * @param encoders the list of encoders.
     */
    public void setEncoders(List<Encoder> encoders);
    /** Register to handle to incoming messages in this conversation.
     * @listener the MessageHandler to be added.
     */
    public void addMessageHandler(MessageHandler listener);
    /** Return an unmodifiable copy of the set of MessageHandlers for this Session.
     * @return the set of message handlers.
     */
    public Set<MessageHandler> getMessageHandlers();
    /** Remove the given MessageHandler from the set belonging to this session. 
     * @param listener the handler to be removed.
     * <bold>TBD</bold> Threading issues wrt handler invocations vs removal
     */
    public void removeMessageHandler(MessageHandler listener);
    /** Returns the version of the websocket protocol currently being used. This is taken
     * as the value of the Sec-WebSocket-Version header used in the opening handshake. i.e. "13".
     * @return the protocol version.
     */
    public String getProtocolVersion();
    /** Return the sub protocol agreed during the websocket handshake for this conversation. 
     * @return the negotiated subprotocol. 
     */
    public String getNegotiatedSubprotocol();
    /** Return the list of extensions currently in use for this conversation.
     * @return the negotiated extensions.
     */
    public List<Extension> getNegotiatedExtensions();
    /** Return true if and only if the underlying socket is using a secure transport.
     * @return whether its using a secure transport.
     */
    public boolean isSecure();
    /** Return the number of seconds since the underlying connection had any activity. 
     * @return the inactive time.
     */
    public long getInactiveTime();
    /** Return true if and only if the underlying socket is open. 
     @return whether the session is active.
     */
    public boolean isActive();
    /** Return the number of seconds before this conversation will be closed by the 
     * container if it is inactive, ie no messages are either sent or received in that time. 
     * @return the timeout in seconds.
     */
    public long getTimeout();
    /** Set the number of seconds before this conversation will be closed by the 
     * container if it is inactive, ie no messages are either sent or received. 
     @param seconds the number of seconds.
     */
    public void setTimeout(long seconds);
    /** Sets the maximum total length of messages, text or binary, that this Session can handle.
     * @length the maximum length
     */
    public void setMaximumMessageSize(long length);
    /** The maximum total length of messages, text or binary, that this Session can handle.
     @return the message size.
     */
    public long getMaximumMessageSize();
    /** Return a reference to the RemoteEndpoint object representing the other end of this conversation.
     @return the remote endpoint.
     */
    public RemoteEndpoint getRemote();

     /** Return a reference to the RemoteEndpoint that can send messages in the form of objects of class c.
     @param c the class of the RemoteEndpoint.
     @return the remote endpoint instance.
     */
    public RemoteEndpoint<T> getRemoteL(Class<T> c);

    /** Close the current conversation with a normal status code and no reason phrase. */
    public void close() throws IOException;
    
    /** Close the current conversation, giving a reason for the closure. Note the websocket spec defines the
     * acceptable uses of status codes and reason phrases. 
     * @param closeStatus the reason for the closure.
     */
    public void close(CloseReason closeStatus) throws IOException;

    /** Return the URI that this session was opened under.
     * @return the request URI.
     */
    public URI getRequestURI();
    
    /** Return the request parameters associated with the request this session
     * was opened under.
     * @return the unmodifiable map of the request parameters.
     */
    public Map<String, String[]> getParameterMap();
    
    /** Return the query string associated with the request this session
     * was opened under.
     * @return 
     */
    public String getQueryString();

    
}
