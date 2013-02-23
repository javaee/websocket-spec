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

import java.util.List;
import java.util.Map;

/**
 * The ClientEndpointConfiguration is a special kind of endpoint configuration object that contains
 * web socket configuration information specific only to client endpoints. Developers deploying 
 * programmatic client endpoints can create instances of this configuration by
 * using a {@link ClientEndpointConfigurationBuilder}. 
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface ClientEndpointConfiguration extends EndpointConfiguration {


    /**
     * Return the ordered list of sub protocols a client endpoint would like to use,
     * in order of preference, favorite first that this client would
     * like to use for its sessions.
     * This list is used to generate the Sec-WebSocket-Protocol header in the opening
     * handshake for clients using this configuration. The first protocol name is the most preferred.
     * See <a href="http://tools.ietf.org/html/rfc6455#section-4.1">Client Opening Handshake</a>.
     *
     * @return the list of the preferred subprotocols, the empty list if there are none
     */
    List<String> getPreferredSubprotocols();

    /**
     * Return the extensions, in order of preference, favorite first, that this client would
     * like to use for its sessions. These are the extensions that will
     * be used to populate the Sec-WebSocket-Extensions header in the opening handshake for clients
     * using this configuration. The first extension in the list is the most preferred extension.
     * See <a href="http://tools.ietf.org/html/rfc6455#section-9.1">Negotiating Extensions</a>.
     *
     * @return the list of extensions, the empty list if there are none.
     */
    List<Extension> getExtensions();
    
    
    /**
     * Return the custom configurator for this configuration. If the developer
     * did not provide one, the platform default configurator is returned.
     * 
     * @return the configurator in use with this configuration.
     */
    public ClientEndpointConfigurator getClientEndpointConfigurator();




}


