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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.Extension;


/**
 * The DefaultServerEndpointConfiguration is a concrete class that embodies all the configuration
 * parameters for an endpoint that is to be published as a server endpoint. Developers may
 * subclass this class in order to override the configuration behavior.
 *
 * @author dannycoward
 */
 final class DefaultServerEndpointConfiguration implements ServerEndpointConfiguration {
    private String path;
    private Class<?> endpointClass;
    private List<String> subprotocols; // TODO check sensible defaults...
    private List<Extension> extensions;
    private List<Encoder> encoders;
    private List<Decoder> decoders;
    private Map<String, Object> userProperties = new HashMap<String, Object>();
    private ServerEndpointConfigurator handshakeConfigurator;

    
    // The builder ensures nothing except configurator can be null.
    DefaultServerEndpointConfiguration(Class endpointClass,
                                    String path,
                                    List<String> subprotocols,
                                    List<Extension> extensions,
                                    List<Encoder> encoders,
                                    List<Decoder> decoders,
                                    ServerEndpointConfigurator handshakeConfigurator) {
        this.path = path;
        this.endpointClass = endpointClass;
        this.subprotocols = Collections.unmodifiableList(subprotocols);
        this.extensions = Collections.unmodifiableList(extensions);
        this.encoders = Collections.unmodifiableList(encoders);
        this.decoders = Collections.unmodifiableList(decoders);
        if (handshakeConfigurator == null) {
            this.handshakeConfigurator = ServerEndpointConfigurator.fetchContainerDefaultConfigurator();
        }
    }

    /**
     * Returns the class of the Endpoint that this configuration configures.
     *
     * @return the class of the Endpoint.
     */
    @Override
    public Class<?> getEndpointClass() {
        return this.endpointClass;
    }


    /**
     * Creates a server configuration with the given path
     *
     * @param path the URI or URI template.
     */
     DefaultServerEndpointConfiguration(Class<? extends Endpoint> endpointClass, String path) {
        this.path = path;
        this.endpointClass = endpointClass;
    }

    /**
     * Return the Encoder implementations configured. These
     * will be used by the container to encode outgoing messages.
     *
     * @return the encoders, in an unmodifiable list, empty if there are none.
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
     * TODO
     * @return 
     */
     @Override
    public ServerEndpointConfigurator getServerEndpointConfigurator() {
        return this.handshakeConfigurator;
    }
    
     /**
     * Editable map of user properties.
     */
     @Override
    public final Map<String, Object> getUserProperties() {
        return this.userProperties;
    }
    
     @Override
    public final List<String> getSubprotocols() {
        return this.subprotocols;
    }
    
    @Override
    public final List<Extension> getExtensions() {
        return this.extensions;
    }

}