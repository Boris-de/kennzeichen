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

import java.util.Hashtable;

// The class KennzeichenHash provides a Hash with Licence Plates Code
public class KennzeichenHash extends Hashtable
{
	//kind of preprocessor-variable to build with/withour old plates
	static final boolean FULL = true;

	//NotFoundException is thrown by "find" if a value is not found
	//or a NullPointerException is caught by "find"
	public static class NotFoundException extends Exception {};


	//The default constructor adds all active Licence Plates
	public KennzeichenHash()
	{
		//call inherited constructor and set number of elements
		super((FULL)? 1000 : 500);
		add_Plates();
	}

	//The function "find" searches the parameter "search_for"
	public String find(String search_for) throws NotFoundException
	{
		String result, strland="";
		try {
			result=(String)get(search_for.toUpperCase());
			if(result==null) {
				throw new NotFoundException();
			}
		} catch (NullPointerException npe) {
			throw new NotFoundException();
		}
		int pos = result.indexOf(";");
		int land = Integer.parseInt(result.substring(pos+1));
		switch (land) {
			case  1: strland = " (Bayern)"; break;
			case  2: strland = " (Baden-W?rttemberg)"; break;
			case  3: strland = " (Berlin)"; break;
			case  4: strland = " (Brandenburg)"; break;
			case  5: strland = " (Bremen)"; break;
			case  6: strland = " (Hamburg)"; break;
			case  7: strland = " (Hessen)"; break;
			case  8: strland = " (Mecklenburg-Vorpommern)"; break;
			case  9: strland = " (Niedersachsen)"; break;
			case 10: strland = " (Nordrhein-Westfalen)"; break;
			case 11: strland = " (Rheinland-Pfalz)"; break;
			case 12: strland = " (Sachsen)"; break;
			case 13: strland = " (Sachsen-Anhalt)"; break;
			case 14: strland = " (Saarland)"; break;
			case 15: strland = " (Schleswig-Holstein)"; break;
			case 16: strland = " (Th?ringen)"; break;
			case 20: strland = " (Sonderkennzeichen)"; break;
			default: strland = "";
		}
		return result.substring(0, pos)+strland;
	}

	private void add_Plates()
	{
		put("A", "Augsburg;1");
		put("AA", "Ostalbkreis;2");
		put("AB", "Aschaffenburg;1");
		put("ABG", "Altenburger Land;16");
		put("AC", "Aachen;10");
		put("AIC", "Aichach-Friedberg;1");
		put("AK", "Altenkirchen;11");
		put("AM", "Amberg;1");
		put("AN", "Ansbach;1");
		put("ANA", "Annaberg;12");
		put("A�", "Alt�tting;1");
		put("AP", "Weimarer Land;16");
		put("AS", "Amberg-Sulzbach;1");
		put("ASL", "Aschersleben;13");
		put("ASZ", "Aue-Schwarzenberg;12");
		put("AUR", "Aurich;9");
		put("AW", "Bad Neuenahr Ahrweiler;11");
		put("AZ", "Alzey-Worms;11");
		put("AZE", "Anhalt-Zerbst;13");
		put("B", "Berlin;3");
		put("BA", "Bamberg;1");
		put("BAD", "Baden-Baden;2");
		put("BAR", "Barnim;4");
		put("BB", "B�blingen;2");
		put("BBG", "Bernburg;13");
		put("BC", "Biberach an der Ri�;2");
		put("BGL", "Berchtesgadener Land;1");
		put("BIR", "Birkenfeld an der Nahe;11");
		put("BL", "Zollernalbkreis;2");
		put("BLK", "Burgenlandkreis;13");
		put("B�", "B�rdekreis;13");
		put("BOR", "Borken;10");
		put("BRA", "Wesermarsch;9");
		put("BRB", "Brandenburg;4");
		put("BS", "Braunschweig;9");
		put("BT", "Bayreuth;1");
		put("BTF", "Bitterfeld;13");
		put("B�S", "B�singen am Hochrhein;2");
		put("BZ", "Bautzen;12");
		put("BI", "Bielefeld;10");
		put("BIT", "Bitburg;11");
		put("BM", "Erftkreis;10");
		put("BN", "Bonn;10");
		put("BO", "Bochum;10");
		put("BOT", "Bottrop;10");
		put("C", "Chemnitz;12");
		put("CB", "Cottbus;4");
		put("CE", "Celle;9");
		put("CHA", "Cham;1");
		put("CLP", "Cloppenburg;9");
		put("CO", "Coburg;1");
		put("COC", "Cochem;11");
		put("COE", "Coesfeld;10");
		put("CUX", "Cuxhaven;9");
		put("CW", "Calw;2");
		put("D", "D�sseldorf;10");
		put("DAU", "Daun;11");
		put("DD", "Dresden;12");
		put("DN", "D�ren;10");
		put("DO", "Dortmund;10");
		put("DU", "Duisburg;10");
		put("D�W", "Bad D�rkheim (Weinstra�e);11");
		put("DA", "Darmstadt;7");
		put("DAH", "Dachau;1");
		put("DAN", "L�chow-Dannenberg;9");
		put("DBR", "Bad Doberan;8");
		put("DE", "Dessau;13");
		put("DEG", "Deggendorf;1");
		put("DEL", "Delmenhorst;9");
		put("DGF", "Dingolfing-Landau;1");
		put("DH", "Diepholz;9");
		put("DL", "D�beln;12");
		put("DLG", "Dillingen an der Donau;1");
		put("DM", "Demmin;8");
		put("DON", "Donau-Ries;1");
		put("DW", "Wei�eritzkreis;12");
		put("DZ", "Delitzsch;12");
		put("E", "Essen;10");
		put("EU", "Euskirchen;10");
		put("EA", "Eisenach;16");
		put("EBE", "Ebersberg;1");
		put("ED", "Erding;1");
		put("EE", "Elbe-Elster;4");
		put("EF", "Erfurt;16");
		put("EI", "Eichst�tt;1");
		put("EIC", "Eichsfeld;16");
		put("EL", "Emsland;9");
		put("EM", "Emmendingen;2");
		put("EMD", "Emden;9");
		put("EMS", "Rhein-Lahn-Kreis;11");
		put("EN", "Ennepe-Ruhr-Kreis;10");
		put("ER", "Erlangen;1");
		put("ERB", "Odenwaldkreis;7");
		put("ERH", "Erlangen-H�chstadt;1");
		put("ES", "Esslingen an der Neckar;2");
		put("ESW", "Werra-Mei�ner-Kreis;7");
		put("F", "Frankfurt am Main;7");
		put("FR", "Freiburg im Breisgau und Breisgau-Hochschwarzwald;2");
		put("FB", "Wetteraukreis;7");
		put("FD", "Fulda;7");
		put("FDS", "Freudenstadt;2");
		put("FF", "Frankfurt an der Oder;4");
		put("FFB", "F�rstenfeldbruck;1");
		put("FG", "Freiberg;12");
		put("FL", "Flensburg;15");
		put("FN", "Bodenseekreis;2");
		put("FO", "Forchheim;1");
		put("FRG", "Freyung-Grafenau;1");
		put("FRI", "Friesland;9");
		put("FS", "Freising;1");
		put("FT", "Frankenthal in der Pfalz;11");
		put("F�", "F�rth;1");
		put("GE", "Gelsenkirchen;10");
		put("GL", "Rheinisch-Bergischer Kreis;10");
		put("G", "Gera;16");
		put("GAP", "Garmisch-Partenkirchen;1");
		put("GC", "Chemnitzer Land;12");
		put("GER", "Germersheim;11");
		put("GF", "Gifhorn;9");
		put("GG", "Gro�-Gerau;7");
		put("GI", "Gie�en;7");
		put("GM", "Oberbergischer Kreis;10");
		put("G�", "G�ttingen;9");
		put("GP", "G�ppingen;2");
		put("GR", "G�rlitz;12");
		put("GRZ", "Greiz;16");
		put("GS", "Goslar;9");
		put("GT", "G�tersloh;10");
		put("GTH", "Gotha;16");
		put("G�", "G�strow;8");
		put("GZ", "G�nzburg;1");
		put("H", "Hannover;9");
		put("HS", "Heinsberg;10");
		put("HSK", "Hochsauerlandkreis;10");
		put("HA", "Hagen;10");
		put("HAL", "Halle (Saale);13");
		put("HAM", "Hamm;10");
		put("HAS", "Ha�berge;1");
		put("HB", "Hansestadt Bremen;5");
		put("HBN", "Hildburghausen;16");
		put("HBS", "Halberstadt;13");
		put("HD", "Heidelberg und Rhein-Neckar-Kreis;2");
		put("HDH", "Heidenheim;2");
		put("HE", "Helmstedt;9");
		put("HEF", "Hersfeld-Rotenburg an der Fulda;7");
		put("HEI", "Dithmarschen;15");
		put("HER", "Herne;10");
		put("HF", "Herford;10");
		put("HG", "Hochtaunuskreis;7");
		put("HGW", "Hansestadt Greifswald;8");
		put("HH", "Hansestadt Hamburg;6");
		put("HI", "Hildesheim;9");
		put("HL", "Hansestadt L�beck;15");
		put("HM", "Hameln-Pyrmont;9");
		put("HN", "Heilbronn;2");
		put("HO", "Hof;1");
		put("HOL", "Holzminden;9");
		put("HOM", "Saar-Pfalz-Kreis;14");
		put("HP", "Bergstra�e;7");
		put("HR", "Schwalm-Eder-Kreis;7");
		put("HRO", "Hansestadt Rostock;8");
		put("HST", "Hansestadt Stralsund;8");
		put("HU", "Main-Kinzig-Kreis;7");
		put("HVL", "Havelland;4");
		put("HWI", "Hansestadt Wismar;8");
		put("HX", "H�xter;10");
		put("HY", "Hoyerswerda;12");
		put("IGB", "St. Ingbert;14");
		put("IK", "Ilm-Kreis;16");
		put("IN", "Ingolstadt;1");
		put("IZ", "Steinburg;15");
		put("J", "Jena;16");
		put("JL", "Jerichower Land;13");
		put("K", "K�ln;10");
		put("KA", "Karlsruhe;2");
		put("KLE", "Kleve;10");
		put("KO", "Koblenz;11");
		put("KR", "Krefeld;10");
		put("KB", "Waldeck-Frankenberg;7");
		put("KC", "Kronach;1");
		put("KE", "Kempten imAllg�u;1");
		put("KEH", "Kelheim;1");
		put("KF", "Kaufbeuren;1");
		put("KG", "Bad Kissingen;1");
		put("KH", "Bad Kreuznach;11");
		put("KI", "Kiel;15");
		put("KIB", "Donnersberg-Kreis;11");
		put("KL", "Kaiserslautern;11");
		put("KM", "Kamenz;12");
		put("KN", "Konstanz;2");
		put("K�T", "K�then;13");
		put("KS", "Kassel;7");
		put("KT", "Kitzingen;1");
		put("KU", "Kulmbach;1");
		put("K�N", "Hohenlohe-Kreis;2");
		put("KUS", "Kusel;11");
		put("KYF", "Kyffh�userkreis;16");
		put("L", "Leipzig;12");
		put("LEV", "Leverkusen;10");
		put("L�", "L�rrach;2");
		put("LA", "Landshut;1");
		put("LAU", "N�rnberger Land;1");
		put("LB", "Ludwigsburg;2");
		put("LD", "Landau;11");
		put("LDK", "Lahn-Dill-Kreis;7");
		put("LDS", "Dahme-Spreewald;4");
		put("LER", "Leer;9");
		put("LG", "L�neburg;9");
		put("LI", "Lindau;1");
		put("LIF", "Lichtenfels;1");
		put("LIP", "Lippe;10");
		put("LL", "Landsberg am Lech;1");
		put("LM", "Limburg-Weilburg;7");
		put("LOS", "Oder-Spree-Kreis;4");
		put("LU", "Ludwigshafen am Rhein;11");
		put("LWL", "Ludwigslust;8");
		put("M", "M�nchen;1");
		put("MG", "M�nchengladbach;10");
		put("MK", "M�rkischer Kreis;10");
		put("MS", "M�nster;10");
		put("MYK", "Mayen-Koblenz-Kreis;11");
		put("MA", "Mannheim;2");
		put("MB", "Miesbach;1");
		put("MD", "Magdeburg;13");
		put("ME", "Mettmann;10");
		put("MEI", "Mei�en;12");
		put("MEK", "Mittlerer Erzgebirgskreis;12");
		put("MH", "M�lheim an der Ruhr;10");
		put("MI", "Minden-L�bbecke;10");
		put("MIL", "Miltenberg;1");
		put("ML", "Mansfelder Land;13");
		put("MM", "Memmingen;1");
		put("MN", "Unterallg�u;1");
		put("MOL", "M�rkisch-Oderland;4");
		put("MOS", "Neckar-Odenwald-Kreis;2");
		put("MQ:Merseburg-Querfurt", "13");
		put("MR", "Marburg-Biedenkopf;7");
		put("MSP", "Main-Spessart-Kreis;1");
		put("MST", "Mecklenburg-Strelitz;8");
		put("MTK", "Main-Taunus-Kreis;7");
		put("M�", "M�hldorf am Inn;1");
		put("M�R", "M�ritz;8");
		put("MW", "Mittweida;12");
		put("MZ", "Mainz-Bingen;11");
		put("MZG", "Merzig-Wadern;14");
		put("N", "N�rnberg;1");
		put("NE", "Neuss;10");
		put("NB", "Neu-Brandenburg;8");
		put("ND", "Neuburg-Schrobenhausen;1");
		put("NDH", "Nordhausen;16");
		put("NEA", "Neustadt-Bad Windsheim;1");
		put("NES", "Rh�n-Grabfeld in Bad Neustadt;1");
		put("NEW", "Neustadt an der Waldnaab;1");
		put("NF", "Nordfrieslandkreis;15");
		put("NI", "Nienburg / Weser;9");
		put("NK", "Neunkirchen / Saar;14");
		put("NM", "Neumarkt in der Oberpfalz;1");
		put("NMS", "Neum�nster;15");
		put("NOH", "Grafschaft Bentheim;9");
		put("NOL", "Niederschlesischer Oberlausitzkreis;12");
		put("NOM", "Northeim;9");
		put("NR", "Neuwied/Rhein;11");
		put("NU", "Neu-Ulm;1");
		put("NVP", "Nordvorpommern;8");
		put("NW", "Neustadt an der Weinstra�e;11");
		put("NWM", "Nordwestmecklenburg;8");
		put("OE", "Olpe;10");
		put("OA", "Oberallg�u;1");
		put("OAL", "Ostallg�u;1");
		put("OB", "Oberhausen;10");
		put("OD", "Stormarn;15");
		put("OF", "Offenbach;7");
		put("OG", "Ortenaukreis;2");
		put("OH", "Ostholstein;15");
		put("OHA", "Osterode im Harz;9");
		put("OHV", "Oberhavel;4");
		put("OHZ", "Osterholz;9");
		put("OK", "Ohre-Kreis;13");
		put("OL", "Oldenburg;9");
		put("OPR", "Ostprignitz-Ruppin;4");
		put("OS", "Osnabr�ck;9");
		put("OSL", "Oberspreewald-Lausitz;4");
		put("OVP", "Ostvorpommern;8");
		put("P", "Potsdam;4");
		put("PA", "Passau;1");
		put("PAF", "Pfaffenhofen an der Ilm;1");
		put("PAN", "Rottal-Inn;1");
		put("PB", "Paderborn;10");
		put("PCH", "Parchim;8");
		put("PE", "Peine;9");
		put("PF", "Pforzheim und Enzkreis;2");
		put("PI", "Pinneberg;15");
		put("PIR", "S�chsische Schweiz;12");
		put("PL", "Plauen;12");
		put("PL�", "Pl�n;15");
		put("PM", "Potsdam-Mittelmark;4");
		put("PR", "Prignitz;4");
		put("PS", "Pirmasens;11");
		put("QLB", "Quedlinburg;13");
		put("R", "Regensburg;1");
		put("RA", "Rastatt;2");
		put("RD", "Rendsburg-Eckernf�rde;15");
		put("RE", "Recklinghausen;10");
		put("REG", "Regen;1");
		put("RG", "Riesa-Gro�enhain;12");
		put("RH", "Roth;1");
		put("RO", "Rosenheim;1");
		put("ROW", "Rotenburg (W�mme);9");
		put("RS", "Remscheid;10");
		put("RSL", "Ro�lau (Elbe);13");
		put("RT", "Reutlingen;2");
		put("R�D", "Rheingau-Taunus-Kreis;7");
		put("R�G", "R�gen;8");
		put("RV", "Ravensburg;2");
		put("RW", "Rottweil;2");
		put("RZ", "Herzogtum Lauenburg;15");
		put("ST", "Steinfurt;10");
		put("S", "Stuttgart;2");
		put("SAD", "Schwandorf;1");
		put("SAW", "Altmarkkreis Salzwedel;13");
		put("SB", "Saarbr�cken;14");
		put("SBK", "Sch�nebeck;13");
		put("SC", "Schwabach;1");
		put("SDL", "Stendal;13");
		put("SE", "Segeberg;15");
		put("SFA", "Soltau-Fallingbostel;9");
		put("SG", "Solingen;10");
		put("SGH", "Sangerhausen;13");
		put("SHA", "Schw�bisch Hall;2");
		put("SHG", "Schaumburg;9");
		put("SHK", "Saale-Holzlandkreis;16");
		put("SHL", "Suhl;16");
		put("SI", "Siegen-Wittgenstein;10");
		put("SIG", "Sigmaringen;2");
		put("SIM", "Rhein-Hunsr�ck-Kreis;11");
		put("SK", "Saalkreis;13");
		put("SL", "Schleswig-Flensburg;15");
		put("SLF", "Saalfeld-Rudolstadt;16");
		put("SLS", "Saarlouis;14");
		put("SM", "Schmalkalden-Meiningen;16");
		put("SN", "Schwerin;8");
		put("SO", "Soest;10");
		put("SOK", "Saale-Orla-Kreis;16");
		put("S�M", "S�mmerda;16");
		put("SON", "Sonneberg;16");
		put("SP", "Speyer;11");
		put("SPN", "Spree-Nei�e;4");
		put("SR", "Straubing und Straubing-Bogen;1");
		put("STA", "Starnberg;1");
		put("STD", "Stade;9");
		put("STL", "Stollberg;12");
		put("S�W", "S�dliche Weinstra�e;11");
		put("SW", "Schweinfurt;1");
		put("SZ", "Salzgitter;9");
		put("SU", "Rhein-Sieg-Kreis;10");
		put("TBB", "Main-Tauber-Kreis;2");
		put("TF", "Teltow-Fl�ming;4");
		put("TIR", "Tirschenreuth;1");
		put("TO", "Torgau-Oschatz;12");
		put("T�L", "Bad T�lz-Wolfratshausen;1");
		put("TR", "Trier und Trier-Saarburg;11");
		put("TS", "Traunstein;1");
		put("T�", "T�bingen;2");
		put("TUT", "Tuttlingen;2");
		put("UE", "Uelzen;9");
		put("UER", "Uecker-Randow;8");
		put("UH", "Unstrut-Hainich-Kreis;16");
		put("UL", "Ulm und Alb-Donau-Kreis;2");
		put("UM", "Uckermark;4");
		put("UN", "Unna;10");
		put("VIE", "Viersen;10");
		put("V", "Vogtlandkreis;12");
		put("VB", "Vogelsbergkreis;7");
		put("VEC", "Vechta;9");
		put("VER", "Verden an der Aller;9");
		put("VK", "V�lklingen;14");
		put("VS", "Schwarzwald-Baar-Kreis;2");
		put("W", "Wuppertal;10");
		put("WAF", "Warendorf;10");
		put("WAK", "Wartburgkreis;16");
		put("WB", "Wittenberg;13");
		put("WE", "Weimar;16");
		put("WEN", "Weiden in der Oberpfalz;1");
		put("WES", "Wesel;10");
		put("WF", "Wolfenb�ttel;9");
		put("WHV", "Wilhelmshaven;9");
		put("WI", "Wiesbaden;7");
		put("WIL", "Bernkastel-Wittlich;11");
		put("WL", "Harburg;9");
		put("WM", "Weilheim-Schongau;1");
		put("WN", "Rems-Murr-Kreis;2");
		put("WND", "St. Wendel;14");
		put("WO", "Worms;11");
		put("WOB", "Wolfsburg;9");
		put("WR", "Wernigerode;13");
		put("WSF", "Wei�enfels;13");
		put("WST", "Ammerland;9");
		put("WT", "Waldshut;2");
		put("WTM", "Wittmund;9");
		put("W�", "W�rzburg;1");
		put("WUG", "Wei�enburg-Gunzenhausen;1");
		put("WUN", "Wunsiedel;1");
		put("WW", "Westerwaldkreis;11");
		put("Z", "Zwickau und Zwickauer Land;12");
		put("ZI", "L�bau-Zittau;12");
		put("ZW", "Zweibr�cken;11");
	}
}
