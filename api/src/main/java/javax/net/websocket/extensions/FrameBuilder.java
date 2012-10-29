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
package javax.net.websocket.extensions;

import java.nio.ByteBuffer;

/**
 * A factory class for building Frames. 
 * @since DRAFT 003
 * @author dannycoward
 */
public class FrameBuilder {
    
    public static FrameBuilder getBuilder() {
        return null;
    }
    /** Create a text frame with the given string data. */
    public Frame.Data.Text createTextFrame(String s) { return null; }
    /** Create a partial text frame with the given string fragment, and indication of whether this is the last or not
     * of a series.
     * @param s
     * @param isLast
     * @return 
     */
    public Frame.Data.Text.Continuation createTextContinuationFrame(String s, boolean isLast) { return null; }
    /** Create a binary data frame with the given bytes. */
    public Frame.Data.Binary createBinaryFrame(ByteBuffer bb) { return null; }
    /** Create a partial binary frame with the given string fragment, and indication of whether this is the last or not
     * of a series.
     * @param s
     * @param isLast
     * @return 
     */
    public Frame.Data.Binary.Continuation createBinaryContinuationFrame(ByteBuffer bb, boolean isLast) { return null; }
    
    
}
