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

import junit.framework.TestCase;

/**
 * Test for the {@link  KennzeichenHash}
 *
 * @author boris
 */
public class KennzeichenHashTest extends TestCase {
    public void testNormalBehavior() {
        KennzeichenHash kh = new KennzeichenHash("de");
        assertEquals("Ostalbkreis (Baden-Württemberg)", kh.getPlateInformation("AA"));
        assertEquals("Ostalbkreis (Baden-Württemberg)", kh.getPlateInformation("aA"));
        assertEquals("Zweibrücken (Rheinland-Pfalz)", kh.getPlateInformation("ZW"));
        assertEquals("Zweibrücken (Rheinland-Pfalz)", kh.getPlateInformation("zw"));
        assertNull(kh.getPlateInformation("XX"));
        kh.clear();
        assertNull(kh.getPlateInformation("ZW")); // should be missing now
    }

    public void testMissingInputData() {
        KennzeichenHash kh = new KennzeichenHash("xx");
        assertEquals("\nError while reading plates:\nFile not found!", kh.getPlateInformation("AA"));
        assertNull(kh.getPlateInformation("XX")); // cleared now
    }

    public void testPlatesDefect1() {
        KennzeichenHash kh = new KennzeichenHash("plates_defect1");
        assertEquals("Test", kh.getPlateInformation("AA")); // with invalid county-code
        assertEquals("Test2", kh.getPlateInformation("BB")); // without county
    }

    public void testPlatesDefect2() {
        KennzeichenHash kh = new KennzeichenHash("plates_defect2");
        assertEquals("TestX\nError while reading plates:\nAt least one line was not parseable", kh.getPlateInformation("XX"));
        assertEquals("TestX", kh.getPlateInformation("XX"));
    }
}
