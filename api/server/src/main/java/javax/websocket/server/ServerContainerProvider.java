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

import java.util.ServiceLoader;
import javax.websocket.Session;

/**
 * The ServerContainerProvider class allows the developer to obtain
 * a reference to the instance of the ServerContainer for containing application. 
 * This is intended for standalone implementations to bootstrap the 
 * ServerContainer, or for implementations in the web container to obtain a 
 * reference to the ServerContainer from within a Java servlet API ServletContextListener 
 * in order to do programmatic deployment. 
 * 
 * @author dannycoward
 */


public abstract class ServerContainerProvider {
 
    /**
     * Obtain a reference to the instance of the ServerContainer during
     * application deployment. Developers wishing to access the
     * ServerContainer instance from within an active endpoint should call
     * {@link Session#getContainer()} instead.
     * 
     * @return the ServerContainer instance
     * @throws IllegalStateException if called from within an active endpoint.
     */
    public static ServerContainer getServerContainer() {
        ServerContainer sc = null;
        for (ServerContainerProvider impl : ServiceLoader.load(ServerContainerProvider.class)) {
            sc = impl.getContainer();
            if (sc != null) {
                return sc;
            } 
        }
        if (sc == null) {
            throw new RuntimeException("Could not find an implementation class.");
        } else {
            throw new RuntimeException("Could not find an implementation class with a non-null ServerContainer.");
        }
    }
 
    /**
     * Load the server container implementation.
     * @throws IllegalStateException if called from within an active websocket endpoint.
     * @return the server container class
     * 
     */
    protected abstract ServerContainer getContainer();
}



