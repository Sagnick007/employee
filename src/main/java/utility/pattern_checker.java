package utility;

import java.util.regex.*;

public class pattern_checker {
	public boolean check_digit(String check_string) 
	{
		return Pattern.matches("[0-9]+", check_string);
	}
}
