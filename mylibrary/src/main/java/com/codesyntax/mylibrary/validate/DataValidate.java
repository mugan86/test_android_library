package com.codesyntax.mylibrary.validate;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidate {

	//Validate data with regular expresions

	//USER pattern
	private static final String PATTERN_USER = "^[A-Za-z0-9\\.\\_]{1,50}$";

	//PASSWORDS
	private static final String PATTERN_PASSWORD = "^[A-Za-z0-9\\.\\,\\)\\(\\-]{1,50}$";

	//PHONE
	private static final String PATTERN_TELEFONOA = "^[0-9]{9}$";

	//EMAIL
	private static final String PATTERN_POSTA = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    //Method to valid email
    public static boolean isValidEmail(String email) {

        // Compile email pattern
        Pattern pattern = Pattern.compile(PATTERN_POSTA);

        // Check email and pattern
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches())
		{
			return true;
		}
		else
		{
		    return false;
		}
    }
	//Check valid telephone
	public static boolean isValidPhone(String telefonoa)
	{
		Pattern patroia = Pattern.compile(PATTERN_TELEFONOA);
		Matcher mat = patroia.matcher(telefonoa);
		if (mat.matches())
		{
			return true;
		}
		else
		{
		    return false;
		}
	}

	//User Data (Chracters, numbers, '.' and '_' true
	public static boolean isValidText(String testua)
	{
		Pattern patroia = Pattern.compile(PATTERN_USER);
		Matcher mat = patroia.matcher(testua);
		if (mat.matches())
		{
			return true;
		}
		else
		{
		         return false;
		}
	}

	//Pasahitza konprobatzeko
	public static boolean isValidTextWithNumbers(String testua)
	{
		String patroiString = PATTERN_PASSWORD;
		Log.i("Pattern: ",patroiString);
		Pattern patroia = Pattern.compile(PATTERN_PASSWORD);
		Matcher mat = patroia.matcher(testua);
		if (mat.matches())
		{
			return true;
		}
		else

		{
			return false;
		}
	}

	public static boolean isContainsBlankSpace(String user,String email,String password)
	{
		//If one of the all inputtext contains " ", no validate form information
		if (user.contains(" ") || email.contains(" ") || password.contains(" "))
		{
			return false;
		}
		return true;
	}
}
