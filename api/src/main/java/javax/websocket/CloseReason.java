/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
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
package javax.websocket;

/**
 * A class encapsulating the reason why a web socket has been closed, or why it is being asked to
 * close. Note the acceptable uses of codes and reason phrase defined in FRC 6455.
 *
 * @author dannycoward
 * @since DRAFT 001
 */
public class CloseReason {

    private CloseReason.CloseCode closeCode;
    private String reasonPhrase;

    /**
     * Creates a reason for closing a web socket connection with the given
     * code and reason phrase.
     *
     * @param closeCode    the close code
     * @param reasonPhrase the reason phrase
     */
    public CloseReason(CloseReason.CloseCode closeCode, String reasonPhrase) {
        this.closeCode = closeCode;
        this.reasonPhrase = reasonPhrase;
    }

    /**
     * The Close code associated with this CloseReason.
     *
     * @return the close code.
     */
    public CloseReason.CloseCode getCloseCode() {
        return this.closeCode;
    }

    /**
     * The reason phrase associated with this CloseReason.
     *
     * @return the reason phrase.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }


    /**
     * A marker interface for the close codes. This interface may be
     * implemented by enumerations that contain web socket close codes, for
     * example enumerations that contain all the in use close codes as of
     * web socket 1.0, or an enumeration that contains close codes
     * that are currently reserved for special use by the web socket
     * specification.
     */
    public interface CloseCode {
        /**
         * Returns the code number, for example the integer '1000' for normal closure.
         *
         * @return the code number
         */
        int getCode();
    }

    /**
     * An Enumeration of status codes for a web socket close that
     * are defined in the specification.
     */
    public enum CloseCodes implements CloseReason.CloseCode {
        /* 1000 */
        NORMAL_CLOSURE(1000),
        /* 1001 */
        GOING_AWAY(1001),
        /* 1002 */
        PROTOCOL_ERROR(1002),
        /* 1003 */
        CANNOT_ACCEPT(1003),
        /* 1004 */
        RESERVED(1004
                /* 1005 */),
        NO_STATUS_CODE(1005),
        /* 1006 */
        CLOSED_ABNORMALLY(1006),
        /* 1007 */
        NOT_CONSISTENT(1007),
        /* 1008 */
        VIOLATED_POLICY(1008),
        /* 1009 */
        TOO_BIG(1009),
        /* 101 */
        NO_EXTENSION(1010),
        /* 1011 */
        UNEXPECTED_CONDITION(1011),
        /* 1012 */
        SERVICE_RESTART(1012),
        /* 1013 */
        TRY_AGAIN_LATER(1013),
        /* 1015 */
        TLS_HANDSHAKE_FAILURE(1015);


        CloseCodes(int code) {
            this.code = code;
        }

        /**
         * Return the code number of this status code.
         *
         * @return the code.
         */
        public int getCode() {
            return code;
        }

        private int code;
    }
}

