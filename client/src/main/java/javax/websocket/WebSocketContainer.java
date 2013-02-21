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

import java.net.URI;
import java.util.Set;
import java.io.*;

/**
 * A WebSocketContainer is an implementation provided object that allows the developer to
 * initiate a web socket handshake from the provided endpoint.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface WebSocketContainer {

    /**
     * Return the number of milliseconds the implementation will timeout
     * attempting to send a websocket message for all RemoteEndpoints associated
     * with this container. A non-positive number indicates
     * the implementation will not timeout attempting to send a websocket message
     * asynchronously. Note this default may be overridden in each RemoteEndpoint.
     *
     * @return the timeout time in millsenconds.
     */
    long getDefaultAsyncSendTimeout();

    /**
     * Sets the number of milliseconds the implementation will timeout
     * attempting to send a websocket message  for all RemoteEndpoints associated
     * with this container. A non-positive number indicates
     * the implementation will not timeout attempting to send a websocket message
     * asynchronously. Note this default may be overridden in each RemoteEndpoint.
     */
    void setAsyncSendTimeout(long timeoutmillis);

    /**
     * Connect the supplied annotated object to its server. The supplied object must be a
     * class decorated with the class level
     * {@link javax.websocket.server.WebSocketEndpoint javax.websocket.server.WebSocketEndpoint} annotation. This method blocks until the connection
     * is established, or throws an error if either the connection could not be made or there
     * was a problem with the supplied endpoint class.
     *
     * @param annotatedEndpointClass the annotated websocket client endpoint with {@link WebSocketClient} annotation.
     * @param path                   the complete path to the server endpoint.
     * @return the Session created if the connection is successful.
     * @throws DeploymentException if the annotated endpoint class is not valid.
     * @throws IOException if there was a network or protocol problem that prevented the client endpoint being connected to its server.
     */
    Session connectToServer(Class<?> annotatedEndpointClass, URI path) throws DeploymentException, IOException;

    /**
     * Connect the supplied programmatic endpoint to its server with the given configuration. This method blocks until the connection
     * is established, or throws an error if the connection could not be made.
     *
     * @param endpointClass the programmatic client endpoint class {@link Endpoint}.
     * @param path          the complete path to the server endpoint.
     * @param cec           the configuration used to configure the programmatic endpoint.
     * @return the Session created if the connection is successful.
     * @throws DeploymentException if the configuration is not valid
     * @throws IOException if there was a network or protocol problem that prevented the client endpoint being connected to its server
     */
    Session connectToServer(Class<? extends Endpoint> endpointClass, ClientEndpointConfiguration cec, URI path) throws DeploymentException, IOException;



    /**
     * Return the default time in milliseconds after which any web socket sessions in this
     * container will be closed if it has been inactive. A value that is
     * 0 or negative indicates the sessions will never timeout due to inactivity.
     * The value may be overridden on a per session basis using
     * {@link Session#setMaxIdleTimeout(long) 
     *
     * @return the default number of milliseconds after which an idle session in this container
     * will be closed
     */
    long getDefaultMaxSessionIdleTimeout();

    /**
     * Sets the default time in milliseconds after which any web socket sessions in this
     * container will be closed if it has been inactive. A value that is
     * 0 or negative indicates the sessions will never timeout due to inactivity.
     * The value may be overridden on a per session basis using
     * {@link Session#setMaxIdleTimeout(long) 
     *
     * @param timeout the maximum time in milliseconds.
     */
    void setDefaultMaxSessionIdleTimeout(long timeout);

    /**
     * Returns the default maximum size of incoming binary message that this container
     * will buffer. This default may be overridden on a per session basis using
     * {@link Session#setMaxBinaryMessageBufferSize(int) }
     *
     * @return the maximum size of incoming binary message in number of bytes.
     */
    int getDefaultMaxBinaryMessageBufferSize();

    /**
     * Sets the default maximum size of incoming binary message that this container
     * will buffer.
     *
     * @param max the maximum size of binary message in number of bytes.
     */
    void setDefaultMaxBinaryMessageBufferSize(int max);

    /**
     * Returns the default maximum size of incoming text message that this container
     * will buffer. This default may be overridden on a per session basis using
     * {@link Session#setMaxTextMessageBufferSize(int) }
     *
     * @return the maximum size of incoming text message in number of bytes.
     */
    int getDefaultMaxTextMessageBufferSize();

    /**
     * Sets the maximum size of incoming text message that this container
     * will buffer.
     *
     * @param max the maximum size of text message in number of bytes.
     */
    void setDefaultMaxTextMessageBufferSize(int max);

    /**
     * Return the set of Extensions installed in the container.
     *
     * @return the set of extensions.
     */
    Set<Extension> getInstalledExtensions();
}



