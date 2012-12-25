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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The DefaultClientConfiguration is a concrete implementation of a client configuration. Developers
 * may subclass this class in order to provide their own custom configuration behaviors.
 *
 * @author dannycoward
 */
public class DefaultClientConfiguration implements ClientEndpointConfiguration {
    private List<String> preferredSubprotocols = new ArrayList<String>();
    private List<String> extensions = new ArrayList<String>();
    private List<Encoder> encoders = new ArrayList<Encoder>();
    private List<Decoder> decoders = new ArrayList<Decoder>();

    /**
     * Creates a client configuration with no preferred sub protocols, extensions, decoders or encoders.
     */
    public DefaultClientConfiguration() {
    }

    /**
     * Return the protocols, in order of preference, favorite first, that this client would
     * like to use for its sessions.
     *
     * @return the preferred subprotocols.
     */
    public List<String> getPreferredSubprotocols() {
        return this.preferredSubprotocols;
    }

    /**
     * Assign the List of preferred subprotocols that this client would like to
     * use.
     *
     * @param preferredSubprotocols the preferred subprotocols.
     * @return this endpoint configuration.
     */
    public DefaultClientConfiguration setPreferredSubprotocols(List<String> preferredSubprotocols) {
        this.preferredSubprotocols = preferredSubprotocols;
        return this;
    }

    /**
     * Return the extensions, in order of preference, favorite first, that this client would
     * like to use for its sessions.
     *
     * @return the extension list.
     */
    public List<String> getExtensions() {
        return this.extensions;
    }

    /**
     * Assign the List of preferred extensions that this client would like to
     * use.
     *
     * @param extensions the extensions.
     * @return this endpoint configuration.
     */
    public ClientEndpointConfiguration setExtensions(List<String> extensions) {
        this.extensions = extensions;
        return this;
    }

    /**
     * Assign the list of encoders this client will use.
     *
     * @return the encoder list.
     */
    public List<Encoder> getEncoders() {
        return this.encoders;
    }

    /**
     * Assign the list of encoders this client will use.
     *
     * @param encoders the encoders to use.
     * @return this endpoint configuration.
     */
    public ClientEndpointConfiguration setEncoders(List<Encoder> encoders) {
        this.encoders = encoders;
        return this;
    }

    /**
     * Assign the list of decoders this client will use.
     *
     * @return the decoders to use.
     */
    public List<Decoder> getDecoders() {
        return this.decoders;
    }

    /**
     * Assign the list of decoders this client will use.
     *
     * @param decoders the extensions.
     * @return this endpoint configuration.
     */
    public ClientEndpointConfiguration setDecoders(List<Decoder> decoders) {
        this.decoders = decoders;
        return this;
    }

    /**
     * The default implementation of this method performs no actions on the HandshakeRequest headers.
     *
     * @param hr handshake request the implementation has formulated.
     */
    public void beforeRequest(Map<String, List<String>> hr) {
    }

    /**
     * The default implementation of this method performs no actions on the HandshakeResponse.
     *
     * @param hr the handshake response sent by the server.
     */
    public void afterResponse(HandshakeResponse hr) {
    }

}
