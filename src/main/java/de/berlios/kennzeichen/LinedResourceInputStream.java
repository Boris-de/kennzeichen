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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/** Class that wraps around InputStreamReader to provide readLine()
 * 
 */
public class LinedResourceInputStream {
    private final InputStream inp;
    private final InputStreamReader reader;

    /** Constructor for an UTF-8 resource.
     * 
     * @param resource The file to open
     * @throws java.io.IOException if File was not found
     */
    public LinedResourceInputStream(String resource) throws IOException {
        this(resource, "UTF-8");
    }

    /** Constructor for any textresource.
     * 
     * @param resource The file to open
     * @param charset Charset of the file
     * @throws java.io.IOException if File was not found
     * @throws java.io.UnsupportedEncodingException  if <em>charset</em> was not valid
     */
    public LinedResourceInputStream(String resource, String charset) throws IOException, UnsupportedEncodingException {
        inp = LinedResourceInputStream.class.getResourceAsStream(resource);
        if (inp == null) {
            throw new IOException("File not found!");
        }
        reader = new InputStreamReader(inp, charset);
    }

    /** Read one line from the reader.
     * 
     * Iterates char by char and writes them into a String Buffer
     * 
     * @return The next line in the reader or null if end of file
     * @throws java.io.IOException If an I/O-error occurs in reader.read()
     */
    public String readLine() throws IOException {
        char[] ch = new char[1];
        StringBuffer buffer = new StringBuffer(60);
        boolean done = false;

        while (!done) {
            // read one character
            int c = reader.read(ch);
            if (ch[0] == '\n') {
                done = true;
            } else if (c == -1) {
                // EOF occured. if buffer is empty return null
                if (buffer.length() == 0) {
                    return null;
                } else {
                    done = true;
                }
            } else if (ch[0] != '\r') {
                // append everything that is not \r
                buffer.append(ch[0]);
            }
        }

        return buffer.toString();
    }
}
