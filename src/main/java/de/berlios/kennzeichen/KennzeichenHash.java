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

/** The class KennzeichenHash provides a Hash with Licence Plates Code.
 * 
 * The license plates are read from a resource-file that is packaged and
 * deployed with the .jar file.
 */
public class KennzeichenHash {
    /** Save the message-strings of the last exceptions into this */
    private final List lastErrors = new LinkedList(); // @GuardedBy("this")
    private final Hashtable hash = new Hashtable(500);

    /** Adds all license Plates from a file in the jar. 
     * 
     * @param dataset Name of the data that should be read (normally an ISO country-code)
     */
    public KennzeichenHash(String dataset) {
        addPlates(dataset);
    }

    public void clear() {
        hash.clear();
    }

    /** Searches the Hash for a given plate and returns a string with the full information.
     * 
     * @param plate The plate to get information for
     * @return A String with plate-information
     * @throws NullPointerException If {@code plate == null}
     */
    public synchronized String getPlateInformation(String plate) {
        String result;

        result = (String) hash.get(plate.toUpperCase());
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

    private final String getPlateText(String result) {
        int land;
        int pos = result.indexOf(";");
        try {
            // if found generate an integer, otherwise set to 0
            land = (pos == -1) ? 0 : Integer.parseInt(result.substring(pos + 1));
        } catch (NumberFormatException ex) {
            land = 0;
        }

        final String strland;
        switch (land) {
            case 1:
                strland = " (Bayern)";
                break;
            case 2:
                strland = " (Baden-W\u00FCrttemberg)";
                break;
            case 3:
                strland = " (Berlin)";
                break;
            case 4:
                strland = " (Brandenburg)";
                break;
            case 5:
                strland = " (Bremen)";
                break;
            case 6:
                strland = " (Hamburg)";
                break;
            case 7:
                strland = " (Hessen)";
                break;
            case 8:
                strland = " (Mecklenburg-Vorpommern)";
                break;
            case 9:
                strland = " (Niedersachsen)";
                break;
            case 10:
                strland = " (Nordrhein-Westfalen)";
                break;
            case 11:
                strland = " (Rheinland-Pfalz)";
                break;
            case 12:
                strland = " (Sachsen)";
                break;
            case 13:
                strland = " (Sachsen-Anhalt)";
                break;
            case 14:
                strland = " (Saarland)";
                break;
            case 15:
                strland = " (Schleswig-Holstein)";
                break;
            case 16:
                strland = " (Th\u00FCringen)";
                break;
            case 20:
                strland = " (Sonderkennzeichen)";
                break;
            default:
                strland = "";
        }

        String city = (pos == -1) ? result : result.substring(0, pos);
        return city + strland;
    }

    /** Add all plates from a file to the Hash
     * 
     * @param dataset Name of the data that should be read
     */
    private synchronized void addPlates(String dataset) {
        try {
            String line;
            LinedResourceInputStream inp = new LinedResourceInputStream("/" + dataset + ".dat");

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
                    hash.put(abbr, value);
                }
            }
        } catch (IOException ex) {
            lastErrors.add(ex.getMessage());
        }
    }
}
