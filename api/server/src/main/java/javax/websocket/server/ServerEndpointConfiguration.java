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

import java.util.List;
import javax.websocket.EndpointConfiguration;
import javax.websocket.Extension;

/**
 * The ServerEndpointConfiguration is a special kind of endpoint configuration object that contains
 * web socket configuration information specific only to server endpoints. For developers
 * deploying programmatic endpoints, ServerEndpointConfiguration objects
 * can be created using a {@link ServerEndpointConfigurationBuilder}.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public interface ServerEndpointConfiguration extends EndpointConfiguration {

    /**
     * Returns the Class of the endpoint this configuration is configuring. If 
     * the endpoint is an annotated endpoint, the value is the class of the Java class
     * annotated with @ServerEndpoint. if the endpoint is a programmatic, the value
     * is the class of the subclass of Endpoint.
     *
     * @return the class of the endpoint, annotated or programmatic.
     */
    Class<?> getEndpointClass();

    /**
     * Return the path for this endpoint configuration. The path is the URI or 
     * URI-template relative to the websocket root of the server to which the 
     * endpoint using this configuration will be mapped. The path is always non-null
     * and always begins with a leading "/". 
     *
     * @return the relative path for this configuration.
     */
    String getPath();
    
    /**
     * Return the websocket subprotocols configured. 
     *
     * @return the list of subprotocols, the empty list if none
     */
    List<String> getSubprotocols();
    
    /**
     * Return the websocket extensions configured. 
     *
     * @return the list of extensions, the empty list if none.
     */
    List<Extension> getExtensions();
    
    /** 
     * Return the {@link ServerEndpointConfigurator} this configuration
     * is using. If none was set by calling 
     * {@link ServerEndpointConfigurationBuilder#setServerEndpointConfigurator(javax.websocket.server.ServerEndpointConfigurator)}
     * this methods returns the platform default configurator.
     * 
     * @return the configurator in use.
     */
    ServerEndpointConfigurator getServerEndpointConfigurator();
}
