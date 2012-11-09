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

/** A FrameHandler is a link in the chain of handlers associated with the web socket extensions
 * configured for an endpoint. Each framehandler belongs to an extension, either as the handler for
 * all the incoming web socket frames, of as the handler for all the outgoing web socket frames.
 * on per connection.
 * @since DRAFT 003
 * @author dannycoward
 */
public abstract class FrameHandler {
    private FrameHandler nextHandler;

    /** Constructor that creates a FrameHandler with the given framehandler
     * as the next frame handler in the chain.
     * @param nextHandler
     */
    public FrameHandler(FrameHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    /** The next handler in the handler chain. */
    public FrameHandler getNextHandler() {
        return this.nextHandler;
    }

    /** This method is invoked whenever the implementation is ready to invoke this framehandler
     as part of the framehandler chain. The defauly implementation in this class is a no-op: i.e. it
     simply invokes the next handler in the chain with the frame passed in.
     */
    public void handleFrame(Frame f) {
        this.nextHandler.handleFrame(f);
    }



}
