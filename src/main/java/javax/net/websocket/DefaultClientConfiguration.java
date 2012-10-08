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

import javax.net.websocket.extensions.Extension;
import java.util.*;
import java.net.*;

/**
 * The DefaultClientConfiguration is a concrete implementation of a client configuration. Developers
 * may subclass this class in order to provide their own custom configuration behaviors. 
 * @author dannycoward
 */
public class DefaultClientConfiguration implements ClientEndpointConfiguration {
    private URI uri;
    private List<String> preferredSubprotocols = new ArrayList<String>();
    private List<Extension> extensions = new ArrayList<Extension>();
    private List<Encoder> encoders = new ArrayList<Encoder>();
    private List<Decoder> decoders = new ArrayList<Decoder>();
    /** Creates a client configuration that will attempt
     * to connect to the given URI.
     * @param uri 
     */
    public DefaultClientConfiguration(URI uri) {
    
    }
    
    public URI getURI() {
        return null;
    }
    
    /** Return the protocols, in order of preference, favorite first, that this client would
     * like to use for its sessions. 
     * @return 
     */
    public List<String> getPreferredSubprotocols() {
        return this.preferredSubprotocols;
    }
    
    /** Assign the List of preferred subprotocols that this client would like to
     * use.
     * @return 
     */
    public DefaultClientConfiguration setPreferredSubprotocols(List<String> preferredSubprotocols) {
        this.preferredSubprotocols = preferredSubprotocols;
        return this;
    }
    
    /** Return the extensions, in order of preference, favorite first, that this client would
     * like to use for its sessions. 
     * @return 
     */
    public List<Extension> getExtensions() {
        this.extensions = extensions;
        return null;
    }
    
    /** Assign the List of preferred subprotocols that this client would like to
     * use.
     * @return 
     */
    public ClientEndpointConfiguration setExtensions(List<Extension> preferredExtensions) {
        this.extensions = extensions;
        return this;
    }
    
    /** Assign the list of encoders this client will use.
     * @return 
     */
    public List<Encoder> getEncoders() {
        return this.encoders;
    }
    /** Assign the list of encoders this client will use. */
     public ClientEndpointConfiguration setEncoders(List<Encoder> encoders) {
         this.encoders = encoders;
        return this;
    }   
    /** Assign the list of decoders this client will use.
     * @return 
     */    
    public List<Decoder> getDecoders() {
        return this.decoders;
    }
    
        /** Assign the list of decoders this client will use. */
     public ClientEndpointConfiguration setDecoders(List<Decoder> decoders) {
         this.decoders = decoders;
        return this;
    } 
}
