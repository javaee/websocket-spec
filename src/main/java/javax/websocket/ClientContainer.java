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

import java.util.Set;
import java.net.*;

/**
 * A ClientContainer is an implementation provided object that allows the developer to
 * initiate a web socket handshake from the provided endpoint.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface ClientContainer {

    /**
     * Connect the supplied annotated object to its server using the supplied handshake
     * parameters. The supplied object must be a class decorated with the class level
     * {@link WebSocketEndpoint} annotation. This method blocks until the connection
     * is established, or throws an error if the connection could not be made.
     *
     * @param endpoint either subclass of {@link Endpoint} or a POJO annotated with {@link WebSocketClient} annotation.
     * @param path     the complete path to the server endpoint
     * @return the Session created when the connection is successful
     */
    Session connectToServer(Object endpoint, URI path) throws DeploymentException;

    /**
     * Return a copy of the Set of the currently open web socket sessions. These
     * sessions may not still be open at any point after the return of this method. For
     * example, iterating over the set at a later time may yield one or more closed sessions. Use
     * session.isOpen() to check.
     *
     * @return the set of sessions, active at the time of return.
     */
    Set<Session> getOpenSessions();

    /**
     * Return the maximum time in milliseconds that a web socket session may be idle before
     * the container may close it.
     *
     * @return the number of milliseconds idle web socket sessions are active
     */
    long getMaxSessionIdleTimeout();

    /**
     * Sets the maximum time that a web socket session may be idle before
     * the container may close it.
     *
     * @param timeout the maximum time in milliseconds.
     */
    void setMaxSessionIdleTimeout(long timeout);

    /**
     * Returns the maximum size of binary message that this container
     * will buffer.
     *
     * @return the maximum size of binary message in number of bytes
     */
    long getMaxBinaryMessageBufferSize();

    /**
     * Sets the maximum size of binary message that this container
     * will buffer.
     *
     * @param max the maximum size of binary message in number of bytes
     */
    void setMaxBinaryMessageBufferSize(long max);

    /**
     * Sets the maximum size of text message that this container
     * will buffer.
     *
     * @return the maximum size of text message in number of bytes
     */
    long getMaxTextMessageBufferSize();

    /**
     * Sets the maximum size of text message that this container
     * will buffer.
     *
     * @param max the maximum size of text message in number of bytes
     */
    void setMaxTextMessageBufferSize(long max);

    /**
     * Return the set of Extensions installed in the container.
     *
     * @return the set of extensions.
     */
    Set<String> getInstalledExtensions();
}



