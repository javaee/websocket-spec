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
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * The handshake request represents the web socket defined Http request that for the opening
 * handshake of a web socket session. 
 * @since DRAFT 003
 * @author dannycoward
 */
public interface HandshakeRequest {
    /** Return the read only Map of Http Headers that came with the handshake request. The header names
     * are case insensitive.
     * @return the list of headers.
     */
    public Map<String,List<String>> getHeaders();
    /** Return the authenticated user or null if no user is authenticated for this handshake.
     @ @return the user principal.
     */
    public Principal getUserPrincipal();
    /** Return the request URI of the handshake request.
     * @return the request uri of the handshake request.
     */
    public URI getRequestURI();
    /** Checks whether the current user is in the given role.
     * @param role the role being checked
     * @return whether the user is in the role
     */
    public boolean isUserInRole(String role);
    /** Return a reference to the HttpSession that the web socket handshake that started this
     * conversation was part of, if applicable.
     * @return the http session.
     */
    public Object getSession();
    
     /** Return the request parameters associated with the request.
     * @return the unmodifiable map of the request parameters.
     */
    public Map<String, String[]> getParameterMap();
    
    /** Return the query string associated with the request.
     * @return 
     */
    public String getQueryString();
}
