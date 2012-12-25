/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.websocket;

import java.util.Map;

/**
 * A simple representation of a websocket extension as a name and map of extension parameters.
 *
 * @author dannycoward
 */
public interface Extension {

    /**
     * The name of the extension.
     *
     * @return the name of the extension.
     */
    String getName();

    /**
     * The extension parameters for this extension.
     *
     * @return The read-only Map of extension parameters belonging to this extension.
     */
    Map<String, String> getParameters();
}
