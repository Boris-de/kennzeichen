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

// The class KennzeichenHash provides a Hash with Licence Plates Code
public class KennzeichenHash extends Hashtable {
    //kind of preprocessor-variable to build with/withour old plates
    static final boolean FULL = true;

    //The default constructor adds all active Licence Plates
    public KennzeichenHash() {
        //call inherited constructor and set number of elements
        super((FULL) ? 1000 : 500);
        add_Plates();
    }

    //The function "find" searches the parameter "search_for"
    public synchronized String find(String search_for) {
        String result, strland;
        try {
            result = (String)get(search_for.toUpperCase());
            if (result == null) {
                return null;
            }
        } catch (NullPointerException npe) {
            return null;
        }

        int land;
        int pos = result.indexOf(";");
        try {
            land = Integer.parseInt(result.substring(pos + 1));
        } catch (NumberFormatException ex) {
            land = 0;
        }

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
        return result.substring(0, pos) + strland;
    }

    private synchronized void add_Plates() {
        try {
            String line;
            LinedResourceInputStream inp = new LinedResourceInputStream("/de.dat");

            while ((line = inp.readLine()) != null) {
                int pos = line.indexOf(";");
                String abbr = line.substring(0, pos);
                String value = line.substring(pos + 1);
                put(abbr, value);
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }
}
