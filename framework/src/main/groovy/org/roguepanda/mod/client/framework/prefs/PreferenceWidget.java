package org.roguepanda.mod.client.framework.prefs;

import java.awt.Container;
import java.util.prefs.Preferences;

public interface PreferenceWidget
{
	public void init(Container container);
	
	public void store(Preferences prefs);
	
	public void load(Preferences prefs);
}
