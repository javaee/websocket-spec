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

import java.net.URI;
import javax.net.websocket.extensions.Extension;
import java.util.*;
/**
 * The DefaultServerConfiguration is a concrete class that embodies all the configuration
 * parameters for an endpoint that is to be published as a server endpoint. Developers may
 * subclass this class in order to override the configuration behavior. 
 * @author dannycoward
 */
public class DefaultServerConfiguration implements ServerEndpointConfiguration {
    private URI uri;
    private List<String> subprotocols = new ArrayList<String>();
    private List<Extension> extensions = new ArrayList<Extension>();
    private List<Encoder> encoders = new ArrayList<Encoder>();
    private List<Decoder> decoders = new ArrayList<Decoder>();
    
    /** For subclass implementations. */
    protected DefaultServerConfiguration() {
        
    }
    /** Creates a server configuration with the given URI. */
    public DefaultServerConfiguration(URI uri) {
        this.uri = uri;
    }
    /* Sets all the encoders that this configuration will support.*/
    public DefaultServerConfiguration setEncoders(List<Encoder> encoders) {
        this.encoders = encoders;
        return this;
    }
    
    /** Sets all the decoders that this configuration will support. */
    public DefaultServerConfiguration setDecoders(List<Decoder> decoders) {
        this.decoders = decoders;
        return this;
    }
    
    /** Sets all the subprotocols that this configuration will support. */
    public DefaultServerConfiguration setSubprotocols(List<String> subprotocols) {
        this.subprotocols = subprotocols;
        return this;
    }
    
    /** Sets all the extensions that this configuration will support.
     */ 
    public DefaultServerConfiguration setExtensions(List<Extension> extensions) {
        this.extensions = extensions;
        return this;
    }
    /** Return the Decoder implementations configured. These
     will be used by the container to decode incoming messages
     * into the expected custom objects on MessageListener.onMessage()
     * callbacks.
     */ 
    public List<Encoder> getEncoders() {
        return this.encoders;
    }
     /** Return the Decoder implementations configured. These
     will be used by the container to decode incoming messages
     * into the expected custom objects on MessageListener.onMessage()
     * callbacks.
     */    
    public List<Decoder> getDecoders() {
        return this.decoders;
    }
    
    
    /** Return the subprotocol this server endpoint has chosen from the requested
     * list supplied by a client who wishes to connect, or none if there wasn't one
     * this server endpoint liked. See <a href="http://tools.ietf.org/html/rfc6455#section-4.2.2">Sending the Server's Opening Handshake</a>
     * @param clientSubprotocolList
     * @return 
     */
    public String getNegotiatedSubprotocol(List<String> requestedSubprotocols) {
        throw new RuntimeException("To implement");
    }
    
    /** Provides a simple algorithm to return the list of extensions this server will
     * use for the web socket session: the configuration returns a list containing all of the requested 
     * extensions passed to this method that it supports, using the order in the requested
     * extensions. Subclasses may provide custom algorithms based on other factors.
     * @param requestedExtensions
     * @return 
     */

    public List<Extension> getNegotiatedExtensions(List<Extension> requestedExtensions) {
        throw new RuntimeException("To implement");
    }
    
    /** Makes a check of the validity of the Origin header sent along with the opening
     * handshake. 
     * 
     * @param originHeaderValue
     * @return 
     */
    public boolean checkOrigin(String originHeaderValue) {
        throw new RuntimeException("To implement");
    }
    
    /** A URI is a match if and only if it is an exact match (.equals()) the URI used
     * to create this configuration. Subclasses may override this method to provide
     * different matching policies.
     * @param uri
     * @return 
     */
    public boolean matchesURI(URI uri) {
        return this.uri.equals(uri);
    }
    
    
    /** The default server configuration does not make any changes to the response. Subclasses may
     * override this method in order to inspect the Http request headers of the openinghandshake, for example to track cookies
     * sent by the client. Additionally subclasses may choose to override this method to modify the outgoing
     * handshake response.
     * the outgoing handshake response
     * @param request
     * @param response 
     */
    public void modifyHandshake(HandshakeRequest request, HandshakeResponse response) {}
    
    
}
