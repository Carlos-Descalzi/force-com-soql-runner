package com.soql;

import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Globals {
	public static final Preferences PREFERENCES = Preferences.userNodeForPackage(Globals.class);
	public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");
}
