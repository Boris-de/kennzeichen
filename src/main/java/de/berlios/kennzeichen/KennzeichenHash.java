/* Copyright (c) 2004-2008, Boris Wachtmeister
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the Boris Wachtmeister nor the names of other
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package de.berlios.kennzeichen;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** The class KennzeichenHash provides a Hash with Licence Plates Code.
 * 
 * The license plates are read from a resource-file that is packaged and
 * deployed with the .jar file.
 */
public class KennzeichenHash {
    /** Save the message-strings of the last exceptions into this */
    private final List lastErrors = new LinkedList(); // @GuardedBy("this")
    private final Map hashPlates;
    private final Map hashCounties;

    /** Adds all license Plates from a file in the jar. 
     * 
     * @param dataset Name of the data that should be read (normally an ISO country-code)
     */
    public KennzeichenHash(String dataset) {
        hashPlates = initHashFromFile('/' + dataset + ".dat", 500);
        hashCounties = initHashFromFile('/' + dataset + "-counties.dat", 30);
    }

    public void clear() {
        hashPlates.clear();
        hashCounties.clear();
    }

    /** Searches the Hash for a given plate and returns a string with the full information.
     * 
     * @param plate The plate to get information for
     * @return A String with plate-information
     * @throws NullPointerException If {@code plate == null}
     */
    public synchronized String getPlateInformation(String plate) {
        String result;

        result = (String) hashPlates.get(plate.toUpperCase());
        final String text = (result == null)? null : getPlateText(result);

        if (lastErrors.size() > 0) {
            StringBuilder message = new StringBuilder(text != null ? text : "");
            message.append("\nError while reading plates:" );
            Iterator it = lastErrors.iterator();
            while (it.hasNext()) {
                message.append('\n').append(it.next());
            }
            lastErrors.clear();

           return message.toString();
        } else {
            return text;
        }
    }

    private final String getPlateText(String plate) {
        int pos = plate.indexOf(";");
        String county = (pos != -1) ? (String) hashCounties.get(plate.substring(pos + 1)) : null;

        StringBuffer result = new StringBuffer(32);
        result.append((pos == -1) ? plate : plate.substring(0, pos));
        if (county != null) {
            result.append(" (").append(county).append(')');
        }
        return result.toString();
    }

    /** Add all plates from a file to the Hash
     * 
     * @param dataset Name of the data that should be read
     */
    private synchronized Map initHashFromFile(String path, int initialSize) {
        Map result = new Hashtable(initialSize);
        try {
            String line;
            LinedResourceInputStream inp = new LinedResourceInputStream(path);

            while ((line = inp.readLine()) != null) {
                if (line.indexOf("#") == 0) {
                    // comment-line
                    continue;
                }
                int pos = line.indexOf(";");
                if (pos == -1) {
                    lastErrors.add("At least one line was not parseable");
                } else {
                    String abbr = line.substring(0, pos);
                    String value = line.substring(pos + 1);
                    result.put(abbr, value);
                }
            }
        } catch (IOException ex) {
            lastErrors.add(ex.getMessage());
        }
        return result;
    }
}
