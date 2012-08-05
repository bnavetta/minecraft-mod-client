package org.roguepanda.mod.client.framework.prefs;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PreferencesDialogBuilder
{
	private List<PreferenceWidget> widgets = new ArrayList<PreferenceWidget>();
	
	private List<PreferenceSavedListener> listeners = new ArrayList<PreferenceSavedListener>();
	
	private Preferences prefs;
	
	public PreferencesDialogBuilder(Preferences prefs)
	{
		this.prefs = prefs;
	}
	
	public static interface PreferenceSavedListener
	{
		public void onPreferenceSaved(Preferences prefs);
	}
	
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
	
	public void addListener(PreferenceSavedListener listener)
	{
		listeners.add(listener);
	}
	
	public void addSection(String title)
	{
		add(new Section(title));
	}
	
	public void add(PreferenceWidget widget)
	{
		widgets.add(widget);
	}
	
	public Component build()
	{
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		for(PreferenceWidget widget : widgets)
		{
			widget.init(panel);
			widget.load(prefs);
		}
		panel.add(new JSeparator(SwingConstants.HORIZONTAL));
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				for(PreferenceWidget widget : widgets)
				{
					widget.store(prefs);
				}
				for(PreferenceSavedListener listener : listeners)
				{
					listener.onPreferenceSaved(prefs);
				}
			}	
		});
		panel.add(save);
		return panel;
	}
}
