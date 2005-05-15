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
		try {
			String result=(String)get(search_for.toUpperCase());
			if(result==null) throw new NotFoundException();
			else return result;
		} catch (NullPointerException npe) {
			throw new NotFoundException();
		}
	}


	private void add_Plates()
	{
            put("AA", "DESC 1");
            // ...
		if(FULL) { //if false, the code below will not be build into the final .jar
                    put("BB", "DESC 2");
                }
        }
}
