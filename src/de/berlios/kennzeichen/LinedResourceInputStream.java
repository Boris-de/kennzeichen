package de.berlios.kennzeichen;

//import com.sun.cldc.io.ResourceInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/** Class that wraps around InputStreamReader to provide readLine()
 * 
 */
public class LinedResourceInputStream {
    private InputStream inp;
    private InputStreamReader reader;

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
        inp = getClass().getResourceAsStream(resource);
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
            int c = reader.read(ch);
            if (ch[0] == '\n') {
                done = true;
            } else if (c == -1) {
                return null;
            } else if (ch[0] != '\r') {
                buffer.append(ch[0]);
            }
        }

        return buffer.toString();
    }
}
