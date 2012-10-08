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

/**
 * The result of asynchronously sending a web socket message. A SendResult is either
 * ok indicating there was no problem, or is not OK in which case there was a problem
 * and it carries an exception to indicate what the problem was.
 * @author dannycoward
 * @since DRAFT 002
 */
public class SendResult {
    private Throwable exception;
    private boolean isOK = true;
    /** Construct a SendResult carrying an exception. */
    public SendResult(Throwable exception) {
        this.exception = exception;
        this.isOK = false;
    }
    /** Construct a SendResult carrying an no exception. */
    public SendResult() {
        
    }

    
    /** The problem sending the message. */
    public Throwable getException() {
        return exception;
    }
    /** Determines if this result is ok or not. 
     @return whether the send was successful or not.
     */
    public boolean isOK() {
        return this.isOK;
    }
    
    
    
}
