package org.roguepanda.mod.client.framework.prefs;

import java.awt.Container;
import java.util.prefs.Preferences;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckboxPrefWidget implements PreferenceWidget
{
	private String key;
	private String label;
	private boolean def;
	
	private JCheckBox check = new JCheckBox();
	
	public CheckboxPrefWidget(String key, String label, boolean def) {
		super();
		this.key = key;
		this.label = label;
		this.def = def;
	}

	public void init(Container container)
	{
		JPanel panel = new JPanel();
		panel.add(new JLabel(label));
		panel.add(check);
		container.add(panel);
	}

	public void store(Preferences prefs)
	{
		prefs.putBoolean(key, check.isSelected());
	}

	public void load(Preferences prefs)
	{
		check.setSelected(prefs.getBoolean(key, def));
	}

}
