package org.roguepanda.mod.client.framework.prefs;

import java.awt.Container;
import java.util.prefs.Preferences;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextPrefWidget implements PreferenceWidget
{
	private String key;
	private String label;
	private String def;
	
	public TextPrefWidget(String key, String label, String def)
	{
		super();
		this.key = key;
		this.label = label;
		this.def = def;
	}

	private JTextField text = new JTextField();
	
	public TextPrefWidget(String key, String label) {
		super();
		this.key = key;
		this.label = label;
		this.def = "";
	}

	public void init(Container container)
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel(label));
		panel.add(text);
		container.add(panel);
	}

	public void store(Preferences prefs)
	{
		prefs.put(key, text.getText());
	}

	public void load(Preferences prefs)
	{
		text.setText(prefs.get(key, def));
	}

}
