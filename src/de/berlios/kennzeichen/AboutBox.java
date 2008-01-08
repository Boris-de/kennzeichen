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


import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;


public class AboutBox extends Form implements CommandListener {
    private Displayable oldForm;
    private Display mainDisp;

    /** Creates a new instance of AboutBox */
    public AboutBox(Display disp) {
        super("Hilfe"); //call inherited constructor
        append(new StringItem(null, "Zur Suche einfach das Kennzeichen in die Box eingeben und mit OK best\u00E4tigen.\n"));
        append(new StringItem(null, "\nMehr Informationen unter\nhttp://kennzeichen.berlios.de"));
        addCommand(new Command("OK", Command.OK, 0));
        setCommandListener(this);
        oldForm = disp.getCurrent();
        disp.setCurrent(this);
        mainDisp = disp;
    }

    /** Implements the CommandListener functionality of this Form */
    public void commandAction(Command c, Displayable s) {
        switch (c.getCommandType()) {
            case Command.OK:
                mainDisp.setCurrent(oldForm);
                break;
        }
    }
}
