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

import java.util.Iterator;
import java.io.*;
import java.nio.*;

/**
 * The Decoder interface holds member interfaces that define how a developer can provide 
 * the web socket container a way web socket messages into developer defined custom objects.
 * @author dannycoward
 * @since DRAFT 002
 */
public interface Decoder {
    
     /** This interface defines how a custom object (of type T) is decoded from a web socket message in
     * the form of a byte buffer.
     */
    public interface Binary<T> extends Decoder {
        
        /** Decode the given bytes into an object of type T.
         @param bytes the bytes to be decoded.
         * @return the decoded object
         */
        public T decode(ByteBuffer bytes) throws DecodeException;
        /** Answer whether the given bytes can be decoded into an object of type T.
         * @bytes the bytes to be decoded.
         * @return whether or not the bytes can be decoded by this decoder.
         */
        public boolean willDecode(ByteBuffer bytes);
    }
    
    /** This interface defines how a custom object is decoded from a web socket message in
     * the form of a binary stream. 
     */
    public interface BinaryStream<T> extends Decoder {
        
        /**
         * Decode the given bytes read from the input stream into an object of type T.
         * @param is the input stream carrying the bytes
         */
        public T decode(InputStream is) throws DecodeException, IOException;
    }
    
     /** This interface defines how a custom object is decoded from a web socket message in
     * the form of a string. */
    public interface Text<T> extends Decoder {
        /** Decode the given String into an object of type T.
         * @param the string to be decoded.
         * @return the decoded message as an object of type T
         */
        public T decode(String s) throws DecodeException;
        /** Answer whether the given String can be decoded into an object of type T. 
         *
         * @param s the string being tested for decodability
         * @return whether this decoder can decoded the supplied string.
         */
        public boolean willDecode(String s);
    }
     /** This interface defines how a custom object of type T is decoded from a web socket message in
     * the form of a character stream. */
    public interface TextStream<T> extends Decoder {
        /** Reads the websocket message from the implementation provided
         * Reader and decodes it into an instance of the supplied object type.
         * @param reader the reader from which to read the web socket message.
         * @return the instance of the object that is the decoded web socket message.
         */
        public T decode(Reader reader) throws DecodeException, IOException;
    }
    
    
}
