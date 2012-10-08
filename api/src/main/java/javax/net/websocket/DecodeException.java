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
import java.nio.*;

/**
 * A general exception that occurs when trying to decode a custom object from a text or binary message.
 * @author dannycoward
 * @since DRAFT 002
 */
public class DecodeException extends Exception {
    private ByteBuffer bb;
    private String encodedString;
    private static final long serialVersionUID = 006;
    
        /* Constructor with the data being decoded, and the reason why it failed to be, and the cause. The buffer may represent the whole message,
     * or part of the message, depending whether the application is using one
     * of the streaming methods or not.*/
    public DecodeException(ByteBuffer bb, String message, Throwable cause) {
        super(message, cause);
        this.bb = bb;
    }
    
            /* Constructor with the data being decoded, and the reason why it failed to be, and the cause. The encoded string may represent the whole message,
     * or part of the message, depending whether the application is using one
     * of the streaming methods or not.*/
    public DecodeException(String encodedString, String message, Throwable cause) {
        super(message, cause);
        this.encodedString = encodedString;
    }
    
    /** Constructs a DecodedException with the given ByteBuffer that cannot
     * be decoded, and reason why. The buffer may represent the whole message,
     * or part of the message, depending whether the application is using one
     * of the streaming methods or not.
     * @param bb
     * @param message 
     */
    public DecodeException(ByteBuffer bb, String message) {
        super(message);
        this.bb = bb;
    }
    /** Constructs a DecodedException with the given encoded string that cannot
     * be decoded, and reason why. The encoded string may represent the whole message,
     * or part of the message, depending whether the application is using one
     * of the streaming methods or not.
     * @param bb
     * @param message 
     */
    public DecodeException(String encodedString, String message) {
        super(message);
        this.encodedString = encodedString;
    }
    /** Return the ByteBuffer that cannot be decoded. */
    public ByteBuffer getBytes() {return this.bb;}
    /** Return the encoded string that cannot be decoded. */
    public String getText() {return this.encodedString;}
}
