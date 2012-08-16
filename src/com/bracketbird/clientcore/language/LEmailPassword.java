package com.bracketbird.clientcore.language;

/**
 *
 */
public class  LEmailPassword extends LanguagePage{
    private static final long serialVersionUID = -6195082573786326950L;

    public LEmailPassword() {
    }

    public LEmailPassword(String danish, String english, String german, String french) {
        super(danish, english, german, french);
    }


    //0 = dansk, 1 = engelsk, 2 = tysk, 3 = fransk.

    public static LEmailPassword YOUR_PASSWORD = new LEmailPassword("Din adgangskode", "Your password", "", "");
    public static LEmailPassword YOUR_PASSWORD_IS = new LEmailPassword("Din adgangskode er: ", "Your password is: ", "", "");

    public static LEmailPassword EMAIL_ALREADY_IN_USE_FIRM = new LEmailPassword("Kunne ikke tilmelde firma - email er allerede i brug.", "Unable to register company - email already in use.", "", "");
    public static LEmailPassword EMAIL_ALREADY_IN_USE_ACCOUNT = new LEmailPassword("Kunne ikke oprette brugerkonto - email er allerede i brug.", "Unable to create account - email already in use.", "", "");

    public static LEmailPassword LOGIN_EMAIL_ERROR = new LEmailPassword("Ku' ikke logge p\u00E5 - email kunne ikke identificeres.", "Unable to log in - email not valid.", "", "");
    public static LEmailPassword LOGIN_PASSWORD_ERROR = new LEmailPassword("Ku' ikke logge p\u00E5 - forkert password.", "Unable to log in - password not valid.", "", "");


    public static LEmailPassword EMAIL_PASSWORD_ERROR = new LEmailPassword("Den angivne email kunne ikke findes i systemet.", "The given mail was not identified in the system.", "", "");
    public static LEmailPassword EMAIL_NOT_VALID = new LEmailPassword("Email er ugyldig.", "The email is not valid.", "", "");

    public static LEmailPassword YOU_ARE_LOGGED_OUT = new LEmailPassword("Du er logget ud.", "You have logged out.", "", "");


}
