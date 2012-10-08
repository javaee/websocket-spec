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

import java.util.Set;
import javax.net.websocket.extensions.Extension;

/**
 * A ClientContainer is an implementation provided object that allows the developer to
 * initiate a web socket handshake from the provided endpoint.
 * @author dannycoward
 * @since DRAFT 001
 */
public interface ClientContainer {
    /** Connect the supplied endpoint to its server using the supplied handshake
     * parameters
     * @param endpoint the endpoint which will be connected to the server
     * @param olc  the client configuration used to connect the client
     */
    public void connectToServer(Endpoint endpoint, ClientEndpointConfiguration olc);
    /** Return a copy of the Set of the currently active web socket sessions. These
     * sessions may not still be active at any point after the return of this method, for
     * example, Iterating over the set at a later time may yield closed session. Use
     * session.isActive() to check.
     * @return the set of sessions, active at the time of return.  
     */
    public Set<Session> getActiveSessions();
    
    /** Return the maximum time in seconds that a web socket session may be idle before
     * the container may close it.
     * @return the number of seconds idle wed socket sessions are active
     */
    public long getMaxSessionIdleTimeout();
    /** Sets the maximum time that a web socket session may be idle before
     * the container may close it.
     * @param the maximum time in seconds.
     */
    public void setMaxSessionIdleTimeout(long timeout);
     /** Returns the maximum size of binary message that this container 
      * will buffer. 
      * @return the maximum size of binary message in number of bytes
      */
    public long getMaxBinaryMessageBufferSize();
    /** Sets the maximum size of binary message that this container 
      * will buffer. 
      * @param  the maximum size of binary message in number of bytes
      */
    public void setMaxBinaryMessageBufferSize(long max);
    /** Sets the maximum size of text message that this container 
     * will buffer. 
     * @return the maximum size of text message in number of bytes
     */
    public long getMaxTextMessageBufferSize();
     /** Sets the maximum size of text message that this container 
      * will buffer. 
      * @param the maximum size of text message in number of bytes
      */
    public void setMaxTextMessageBufferSize(long max);
    
    /** Return the set of Extensions installed in the container. 
     @return the set of extensions. */
    public Set<Extension> getInstalledExtensions();
}



