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

import java.util.List;
import java.util.Map;

/**
 * The ClientEndpointConfiguration is a special kind of endpoint configuration object that contains
 * web socket configuration information specific only to client endpoints.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface ClientEndpointConfiguration extends EndpointConfiguration {

    /**
     * The ordered list of sub protocols a client endpoint would like to use, the empty list if there are none.
     * This list is used to generate the Sec-WebSocket-Protocol header in the opening
     * handshake for clients using this configuration. The first protocol name is the most preferred.
     * See <a href="http://tools.ietf.org/html/rfc6455#section-4.1">Client Opening Handshake</a>.
     *
     * @return a list of the preferred subprotocols.
     */
    List<String> getPreferredSubprotocols();

    /**
     * Return the list of all the extensions that this client supports, the empty list if there are none. These are the extensions that will
     * be used to populate the Sec-WebSocket-Extensions header in the opening handshake for clients
     * using this configuration. The first extension in the list is the most preferred extension.
     * See <a href="http://tools.ietf.org/html/rfc6455#section-9.1">Negotiating Extensions</a>.
     *
     * @return a list of extensions.
     */
    List<String> getExtensions();

    /**
     * This method is called by the implementation after it has formulated the handshake
     * request that will be used to initiate the connection to the server, but before it has
     * sent any part of the request. This allows the developer to inspect and modify the
     * handshake request headers prior to the start of the handshake interaction.
     *
     * @param headers the mutable map of handshake request headers the implementation is about to send to
     *                start the handshake interaction.
     */
    void beforeRequest(Map<String, List<String>> headers);

    /**
     * This method is called by the implementation after it has received a handshake response
     * from the server as a result of a handshake interaction it initiated. The developer may implement
     * this method in order to inspect the returning handshake response.
     *
     * @param hr the handshake response sent by the server.
     */
    void afterResponse(HandshakeResponse hr);
}


