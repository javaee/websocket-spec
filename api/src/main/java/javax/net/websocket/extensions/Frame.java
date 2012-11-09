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
package javax.net.websocket.extensions;

import javax.net.websocket.CloseReason;

/**
 * Frame is the top level interface that represents some kind of web socket frame.
 * @since DRAFT 003
 * @author dannycoward
 */
public interface Frame {
    /** Common super-type for all the web socket frames that carry application data. */
    public interface Data extends Frame {

        /** Return data used by a web socket extension in this frame. */
        public byte[] getExtensionData();
        /** A text data frame. */
        public interface Text extends Frame.Data {
            /** Return the textual data in this text frame. */
            public String getText();
            /** A kind of text frame that represents a fragment of a message in a series of such frames
             * that, re-assembled, form a complete text message.
             */
            public interface Continuation extends Frame.Data.Text {

                /** Indicates whether this text message fragment
                 * is the last in the series or not.
                 * @return
                 */
                public boolean isLast();
            }
        }
        /** A binary data frame  */
        public interface Binary extends Frame.Data {
            /** The application data in the binary frame. */
            public byte[] getData();
             /** A kind of binary frame that represents a fragment of a message in a series of such frames
             * that, re-assembled, form a complete text message.
             */
            public interface Continuation extends Frame.Data.Binary {
                 /** Indicates whether this text message fragment
                 * is the last in the series or not.
                 * @return
                 */
                public boolean isLast();
            }
        }
    }
    /** Super type for all the websocket control frames.*/
    public interface Control extends Frame {
        /** A web socket Ping frame.*/
        public interface Ping extends Frame.Control  {
             /** The application data within the Ping frame.*/
            public byte[] getApplicationData();
        }
        /** A web socket Pong frame.*/
        public interface Pong extends Frame.Control {
            /** The application data within the Pong frame.*/
            public byte[] getApplicationData();
        }
         /** A web socket Close frame.*/
        public interface Close extends Frame.Control {
            /** The reason phrase for this close.*/
            public String getReasonPhrase();
            /** The close code for this close.*/
            public CloseReason.CloseCode getCloseCode();
        }
    }
}
