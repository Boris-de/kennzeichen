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
import junit.framework.TestCase;

/**
 * Tests for the {@link LinedResourceInputStream}.
 *
 * @author boris
 */
public class LinedResourceInputStreamTest extends TestCase {
    public void test1() throws Exception {
        LinedResourceInputStream in = new LinedResourceInputStream("/testfile1");
        assertEquals("aaä", in.readLine());
        assertEquals("bbb", in.readLine()); // this line contained a \r in the file
        assertEquals("ccc", in.readLine());
        assertEquals(null, in.readLine());
        assertEquals(null, in.readLine()); // following reads have to return null too
    }

    public void test2() throws Exception {
        LinedResourceInputStream in = new LinedResourceInputStream("/testfile2", "ISO-8859-1");
        assertEquals("xxä", in.readLine());
    }

    public void testNotExisting() {
        try {
            new LinedResourceInputStream("/does_not_exist");
            fail("no exception was thrown");
        } catch (IOException e) {
            assertEquals(IOException.class, e.getClass()); // check if it is no subclass
        }
    }
}