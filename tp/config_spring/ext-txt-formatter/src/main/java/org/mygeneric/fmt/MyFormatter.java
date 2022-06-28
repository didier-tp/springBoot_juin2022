package org.mygeneric.fmt;

import org.mygeneric.encadreur.ext.OptionalFormatter;

public class MyFormatter  implements OptionalFormatter{
		public String format(String s) {
			return camelToSnake(s);
		}
		
		public static String camelToSnake(String str)
	    {
	        // Regular Expression
	        String regex = "([a-z])([A-Z]+)";
	  
	        // Replacement string
	        String replacement = "$1_$2";
	  
	        // Replace the given regex
	        // with replacement string
	        // and convert it to lower case.
	        str = str
	                  .replaceAll(
	                      regex, replacement)
	                  .toLowerCase();
	  
	        // return string
	        return str;
	    }
}
