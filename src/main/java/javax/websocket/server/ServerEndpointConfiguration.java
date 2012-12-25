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
package javax.websocket.server;

import java.net.URI;
import java.util.List;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfiguration;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;

/**
 * The ServerEndpointConfiguration is a special kind of endpoint configuration object that contains
 * web socket configuration information specific only to server endpoints. The parametrized type T is
 * the type of the Endpoint that this configures.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface ServerEndpointConfiguration extends EndpointConfiguration {

    /**
     * Returns the Class of the Endpoint this configuration is configuring.
     *
     * @return the class of the Endpoint.
     */
    Class<? extends Endpoint> getEndpointClass();

    /**
     * Return the subprotocol this server endpoint has chosen from the requested
     * list supplied by a client who wishes to connect, or none if there wasn't one
     * this server endpoint liked. See <a href="http://tools.ietf.org/html/rfc6455#section-4.2.2">Sending the Server's Opening Handshake</a>
     *
     * @param requestedSubprotocols the requested subprotocols.
     * @return the negotiated subprotocol.
     */
    String getNegotiatedSubprotocol(List<String> requestedSubprotocols);

    /**
     * Return the ordered list of extensions that this server will support given the requested
     * extension list passed in, the empty list if none. See <a href="http://tools.ietf.org/html/rfc6455#section-9.1">Negotiating Extensions</a>
     *
     * @param requestedExtensions the requested extentions, in order.
     * @return the list of extensions negotiated
     */
    List<Extension> getNegotiatedExtensions(List<Extension> requestedExtensions);

    /**
     * Check the value of the Origin header (<a href="http://tools.ietf.org/html/rfc6454">See Origin Header</a>) the client passed during the opening
     * handshake.
     *
     * @param originHeaderValue the value of the origin header.
     * @return whether the check passed or not
     */
    boolean checkOrigin(String originHeaderValue);

    /**
     * Answers whether the current configuration matches the given path. This method may be overridden
     * by implementations with any number of algorithms for determining a match.
     *
     * @param uri the uri of the incoming handshake.
     * @return whether there was a match
     */
    boolean matchesURI(URI uri);


    /**
     * Called by the container after it has formulated a handshake response resulting from
     * a well-formed handshake request. The container has already has already checked that this configuration
     * has a matching URI, determined the validity of the origin using the checkOrigin method, and filled
     * out the negotiated subprotocols and extensions based on this configuration.
     * Custom configurations may override this method in order to inspect
     * the request parameters and modify the handshake response.
     * and the URI checking also.
     *
     * @param request  the opening handshake request.
     * @param response the proposed opening handshake response
     */
    void modifyHandshake(HandshakeRequest request, HandshakeResponse response);

    /**
     * Return the path for this endpoint configuration. The path
     * is the URI or URI-template relative to the websocket root of the server to which the endpoint
     * using this configuration will be mapped. The path always begins with a leading "/". A trailing "/" will be
     * ignored.
     *
     * @return the relative path for this configuration.
     */
    String getPath();
}
