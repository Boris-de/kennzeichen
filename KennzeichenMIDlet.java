/* Copyright (c) 2004, 2005, Boris Wachtmeister
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

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

public class KennzeichenMIDlet extends MIDlet implements CommandListener
{
	private Form myMainForm;
	private Form myAboutForm;
	private Display disp;
	private boolean paused=false;
	private TextField field_Kennzeichen;
	private StringItem field_Output;
	private KennzeichenHash myKennzeichenHash;

	public KennzeichenMIDlet()
	{
		//Create Fields
		field_Output = new StringItem(null,"");
		field_Kennzeichen = new TextField ("", "", 3, TextField.ANY);

		//Create GUI
		myMainForm = new Form("Kennzeichen");
		myMainForm.append(new StringItem(null,"Kennzeichen"));
		myMainForm.append(field_Kennzeichen);
		myMainForm.append(field_Output);
		myMainForm.addCommand(new Command("OK",Command.OK,1));
		myMainForm.addCommand(new Command("Beenden",Command.EXIT,3));
		myMainForm.addCommand(new Command("Hilfe",Command.HELP,2));
		myMainForm.setCommandListener(this);

		myKennzeichenHash=new KennzeichenHash();
	}

	public void startApp()
	{
		disp=Display.getDisplay(this);
		if(paused)
			paused=false;
		disp.setCurrent(myMainForm);
	}

	public void destroyApp(boolean unconditional)
	{
		myKennzeichenHash.clear();
	}

	public void pauseApp() { paused=true; }

	public void commandAction(Command c, Displayable s)
	{
		switch (c.getCommandType())
		{
		case Command.OK:
			String Kennzeichen = new String(field_Kennzeichen.getString());
                        if(Kennzeichen.length()>0) {
				//diplay status-message
				field_Output.setText("Suche nach: "+Kennzeichen);
				//search and display
				try {
					field_Output.setText(myKennzeichenHash.find(Kennzeichen));
				}
				catch (KennzeichenHash.NotFoundException nfe)
				{
					field_Output.setText(Kennzeichen+" wurde nicht gefunden.");
				}
                        } else {
				field_Output.setText("Kein Kennzeichen eingegeben!");
                        }
			break;
		case Command.HELP:
			new AboutBox(disp); //Open AboutBox
			break;
		case Command.EXIT:
			destroyApp(true);
			notifyDestroyed(); break;
		}
	}
}
