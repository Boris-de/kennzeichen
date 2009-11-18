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
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;


public class KennzeichenMIDlet extends MIDlet implements CommandListener {
    private Form myMainForm;
    private Display disp;
    private boolean paused = false;
    private TextField field_Kennzeichen;
    private StringItem field_Output;
    private KennzeichenHash myKennzeichenHash;

    /** Creates the GUI-elements and the Hash */
    public KennzeichenMIDlet() {
        // Create Fields
        field_Output = new StringItem(null, "");
        field_Kennzeichen = new TextField("", "", 3, TextField.ANY);

        // Create GUI
        myMainForm = new Form("Kennzeichen");
        myMainForm.append(new StringItem(null, "Kennzeichen"));
        myMainForm.append(field_Kennzeichen);
        myMainForm.append(field_Output);
        myMainForm.addCommand(new Command("OK", Command.OK, 1));
        myMainForm.addCommand(new Command("Beenden", Command.EXIT, 3));
        myMainForm.addCommand(new Command("Hilfe", Command.HELP, 2));
        myMainForm.setCommandListener(this);

        myKennzeichenHash = new KennzeichenHash("de");
    }

    /** Implemented method for MIDlet */
    public void startApp() {
        disp = Display.getDisplay(this);

        if (paused) {
            paused = false;
        }

        disp.setCurrent(myMainForm);
    }

    /** Implemented method for MIDlet
     * 
     * @param unconditional unused
     */
    public void destroyApp(boolean unconditional) {
        myKennzeichenHash.clear();
    }

    /** Implemented method for MIDlet
     * 
     * Sets paused. This is cleared in startApp()
     */
    public void pauseApp() {
        paused = true;
    }

    /** Implements the CommandListener functionality of this Form
     * 
     * @param command The command that was put into the Display
     * @param s A displayable (for override-completness)
     */
    public void commandAction(Command command, Displayable s) {
        switch (command.getCommandType()) {
            case Command.OK:
                String Kennzeichen = field_Kennzeichen.getString();
                if (Kennzeichen.length() > 0) {
                    // diplay status-message
                    field_Output.setText("Suche nach: " + Kennzeichen);

                    // search and display
                    String result = myKennzeichenHash.getPlateInformation(Kennzeichen);
                    if (result == null) {
                        field_Output.setText("Nicht gefunden!");
                    } else {
                        field_Output.setText(result);
                    }
                } else {
                    field_Output.setText("Kein Kennzeichen eingegeben!");
                }
                break;
            case Command.HELP:
                // Open AboutBox
                new AboutBox(disp);
                break;
            case Command.EXIT:
                destroyApp(true);
                notifyDestroyed();
                break;
        }
    }
}
