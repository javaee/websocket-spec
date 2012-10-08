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

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;

/**
 * The Encoder interfaces defines how developers can provide a way to convert
 * their custom objects into web socket messages. The Encoder interface contains
 * subinterfaces that allow encoding algorithms to encode custom objects to: text, 
 * binary data, character
 * stream and write to an output stream.
 * @author dannycoward
 * @since DRAFT 002
 */
public interface Encoder {
    /** This interface defines how to provide a way to convert a custom
     * object into a text message.
     * @param <T> 
     */
    public interface Text<T> extends Encoder {
        /** Encode the given object into a String. 
         * @param object the object being encoded.
         * @return the encoded object as a string.
         */
        public String encode(T object) throws EncodeException;
    }
    
    /** This interface may be implemented by encoding algorithms
     * that want to write the encoded object to a character stream. 
     * @since DRAFT 006 / EDR
     * @param <T> the type of the object this encoder can encode.
     */
    public interface TextStream<T> extends Encoder {
        /** Encode the given object to a character stream writing it
         * to the supplied Writer. Implementations of this method may use the EncodeException
         * to indicate a failure to convert the supplied object to an encoded form, and may
         * use the IOException to indicate a failure to write the data to the supplied
         * stream.
         * @param object - the object to be encoded
         * @param writer - the writer provided by the web socket runtime to write the encoded data
         * @throws EncodeException
         * @throws IOException 
         */
        public void encode(T object, Writer writer) throws EncodeException, IOException;
        
    }
    /** This interface defines how to provide a way to convert a custom
     * object into a binary message.
     * @param <T> 
     */
    public interface Binary<T> extends Encoder {
        /** Encode the given object into a byte array.
         @param object the object being encoded
         * @return the binary data
         */
        public ByteBuffer encode(T object) throws EncodeException;

    }
    
     /** This interface may be implemented by encoding algorithms
     * that want to write the encoded object to a binary stream. 
     * @since DRAFT 006 / EDR
     * @param <T> the type of the object this encoder can encode.
     */
    public interface BinaryStream<T> extends Encoder {
        /** Encode the given object into a binary stream written to the
         * implementation provided OutputStream. 
         @param object the object being encoded
         @param os the output stream where the encoded data is written
         */
        public void encode(T object, OutputStream os) throws EncodeException, IOException;

    }
}
