package org.roguepanda.mod.client.framework.prefs;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PreferencesDialogBuilder
{
	List<PreferenceWidget> widgets = new ArrayList<PreferenceWidget>();
	
	private static class Section implements PreferenceWidget
	{
		public Section(String name)
		{
			this.name = name;
		}
		
		private String name;
		
		public void init(Container container)
		{
			container.add(new JLabel(name));
			container.add(new JSeparator(SwingConstants.HORIZONTAL));
		}

		public void store(Preferences prefs) {}

		public void load(Preferences prefs) {}
	}
	
	public Component build()
	{
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		return panel;
	}
}
