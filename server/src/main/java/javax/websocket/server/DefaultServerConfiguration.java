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
package javax.websocket.server;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;


/**
 * The DefaultServerConfiguration is a concrete class that embodies all the configuration
 * parameters for an endpoint that is to be published as a server endpoint. Developers may
 * subclass this class in order to override the configuration behavior.
 *
 * @author dannycoward
 */
public class DefaultServerConfiguration implements ServerEndpointConfiguration {
    private String path;
    private Class<? extends Endpoint> endpointClass;
    private List<String> subprotocols = Collections.unmodifiableList(new ArrayList<String>());
    private List<Extension> extensions = Collections.unmodifiableList(new ArrayList<Extension>());
    private List<Encoder> encoders = Collections.unmodifiableList(new ArrayList<Encoder>());
    private List<Decoder> decoders = Collections.unmodifiableList(new ArrayList<Decoder>());

    private DefaultServerConfiguration() {

    }

    /**
     * Returns the class of the Endpoint that this configuration configures.
     *
     * @return the class of the Endpoint.
     */
    @Override
    public Class<? extends Endpoint> getEndpointClass() {
        return this.endpointClass;
    }


    /**
     * Creates a server configuration with the given path
     *
     * @param path the URI or URI template.
     */
    public DefaultServerConfiguration(Class<? extends Endpoint> endpointClass, String path) {
        this.path = path;
        this.endpointClass = endpointClass;
    }

    /**
     * Sets all the encoders that this configuration will support.
     *
     * @param encoders the encoders supported, may not be null.
     * @return this server configuration instance.
     */
    public DefaultServerConfiguration setEncoders(List<Encoder> encoders) {
        this.encoders = Collections.unmodifiableList(encoders);
        return this;
    }

    /**
     * Sets all the decoders that this configuration will support.
     *
     * @param decoders the encoders supported, may not be null.
     * @return this server configuration instance.
     */
    public DefaultServerConfiguration setDecoders(List<Decoder> decoders) {
        this.decoders = Collections.unmodifiableList(decoders);
        return this;
    }

    /**
     * Sets all the subprotocols that this configuration will support.
     *
     * @param subprotocols the encoders supported, may not be null.
     * @return this server configuration instance.
     */
    public DefaultServerConfiguration setSubprotocols(List<String> subprotocols) {
        this.subprotocols = Collections.unmodifiableList(subprotocols);
        return this;
    }

    /**
     * Sets all the extensions that this configuration will support.
     *
     * @param extensions the encoders supported, may not be null.
     * @return this server configuration instance.
     */
    public DefaultServerConfiguration setExtensions(List<Extension> extensions) {
        this.extensions = Collections.unmodifiableList(extensions);
        return this;
    }

    /**
     * Return the Encoder implementations configured. These
     * will be used by the container to encode outgoing messages.
     *
     * @return the encoders, in an unmodifiable list.
     */
    @Override
    public List<Encoder> getEncoders() {
        return this.encoders;
    }

    /**
     * Return the Decoder implementations configured. These
     * will be used by the container to decode incoming messages
     * into the expected custom objects on MessageHandler
     * callbacks.
     *
     * @return the encoders, in an unmodifiable list.
     */
    @Override
    public List<Decoder> getDecoders() {
        return this.decoders;
    }

    /**
     * Return the path of this server configuration. The path is a relative URI
     * or URI-template.
     *
     * @return the path
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * The default implementation of this method returns, the first subprotocol in the list sent by the client that
     * the server supports, or null if there isn't one none. Subclasses may provide custom algorithms based on other factors.
     *
     * @param requestedSubprotocols the list of requested subprotocols.
     * @return the negotiated subprotocol.
     */
    @Override
    public String getNegotiatedSubprotocol(List<String> requestedSubprotocols) {
        throw new RuntimeException("To implement");
    }

    /**
     * Provides a simple algorithm to return the list of extensions this server will
     * use for the web socket session: the configuration returns a list containing all of the requested
     * extensions passed to this method that it supports, using the order in the requested
     * extensions, the empty list if none. Subclasses may provide custom algorithms based on other factors.
     *
     * @param requestedExtensions the list of extensions requested by the client
     * @return the list of extensions that may be used.
     */
    @Override
    public List<Extension> getNegotiatedExtensions(List<Extension> requestedExtensions) {
        throw new RuntimeException("To implement");
    }

    /**
     * Makes a check of the validity of the Origin header sent along with the opening
     * handshake following the recommendation at: <a href="http://tools.ietf.org/html/rfc6455#section-4.2">Sending the Server's Opening Handshake<a>.
     *
     * @param originHeaderValue The value of the Origin header.
     * @return whether the check passed or not.
     */
    @Override
    public boolean checkOrigin(String originHeaderValue) {
        throw new RuntimeException("To implement");
    }

    /**
     * This default implementation matches the incoming path to the configuration's URI or URI template if and only if
     * it is an exact match in the case the configuration is a URI, and if and only if it is a valid
     * expansion of the configuration URI template, in the case where the configuration is a URI template. Subclasses may override this method to provide
     * different matching policies.
     *
     * @param uri the URL of the incoming request
     * @return whether it matched this configuration or not.
     */
    @Override
    public boolean matchesURI(URI uri) {
        return this.path.equals(uri.toString());
    }

    /**
     * The default server configuration does not make any changes to the response. Subclasses may
     * override this method in order to inspect the Http request headers of the openinghandshake, for example to track cookies
     * sent by the client. Additionally subclasses may choose to override this method to modify the outgoing
     * handshake response.
     * the outgoing handshake response
     *
     * @param request  the handshake request from the client
     * @param response the handshake response formulated by the container.
     */
    @Override
    public void modifyHandshake(HandshakeRequest request, HandshakeResponse response) {
    }
}
