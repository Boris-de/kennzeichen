package de.berlios.kennzeichen;

//import com.sun.cldc.io.ResourceInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class LinedResourceInputStream {
    InputStream inp;
    InputStreamReader reader;

    public LinedResourceInputStream(String resource) throws IOException {
        this(resource, "UTF-8");
    }
    
    public LinedResourceInputStream(String resource, String charset) throws IOException {
        inp = getClass().getResourceAsStream(resource);
        if (inp==null) {
            throw new IOException("File not found!");
        }
        reader = new InputStreamReader(inp, charset);
    }

    public String readLine() throws IOException {
        char[] ch = new char[1];
        StringBuffer buffer = new StringBuffer(60);
        boolean done = false;

        while (!done) {
            int c = reader.read(ch);
            if (ch[0] == '\n') {
                done = true;
            } else if  (c == -1) {
                return null;
            } else if (ch[0] != '\r') {
                buffer.append(ch[0]);
            }
        }

        return buffer.toString();
    }
}
