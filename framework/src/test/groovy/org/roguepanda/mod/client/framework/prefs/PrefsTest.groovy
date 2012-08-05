package org.roguepanda.mod.client.framework.prefs

import java.util.prefs.Preferences

import javax.swing.JFrame

import org.roguepanda.mod.client.framework.prefs.PreferencesDialogBuilder.PreferenceSavedListener

Preferences prefs = Preferences.userNodeForPackage(PrefsTest.class)

if(prefs.getBoolean('show', true))
	println prefs.get("message", "")

PreferencesDialogBuilder builder = new PreferencesDialogBuilder(prefs)

builder.addSection('Hello, World!')
builder.add(new TextPrefWidget('message', 'Message: ', 'Hello, World!'))
builder.add(new CheckboxPrefWidget('show', 'Show Message: ', true))

builder.addListener(new PreferenceSavedListener(){
	public void onPreferenceSaved(Preferences p)
	{
		if(p.getBoolean('show', true))
			println p.get('message', '')
	}
});

JFrame frame = new JFrame()
frame.add(builder.build())
frame.pack()
frame.setVisible(true)
frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE