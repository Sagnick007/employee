package utility;

import java.util.regex.*;

public class PatternChecker {
	public boolean checkDigit(String check_string) 
	{
		return Pattern.matches("[0-9]+", check_string);
	}
}
