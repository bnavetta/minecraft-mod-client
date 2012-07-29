package org.roguepanda.mod.client.framework

import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.InputEvent

import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.KeyStroke

class MenuBuilder extends BuilderSupport
{
	private JMenuBar menuBar = new JMenuBar();
	
	private List<JMenu> menus = new ArrayList<JMenu>();
	
	@Override
	protected void setParent(Object parent, Object child)
	{
		if(child.equals('separator') && parent != null && parent instanceof JMenu)
		{
			parent.addSeparator()
		}
		else if(parent != null)
		{
			menus.remove(child)
			parent.add(child);
		}
	}

	@Override
	protected Object createNode(Object name)
	{
		return createNode(name, null, null)
	}

	@Override
	protected Object createNode(Object name, Object value)
	{
		return createNode(name, null, value)
	}

	@Override
	protected Object createNode(Object name, Map attributes)
	{
		return createNode(name, attributes, null)
	}

	@Override
	protected Object createNode(Object name, Map attributes, Object value)
	{
		switch(name)
		{
			case 'separator':
				return 'separator'
				break
			case 'item':
				return buildItem(name, attributes, value)
				break
			case 'menu':
				return buildMenu(name, attributes, value)
				break
			default:
				throw new IllegalArgumentException("Unknown menu component: $name");
		}
	}
	
	private JMenuItem buildItem(name, Map attributes, value)
	{
		String text = value?.toString()
		if(!text || text.equals('null'))
		{
			text = getRequired(attributes, 'text').toString()
		}
		JMenuItem item = new JMenuItem(text)
		
		def accelerator = attributes['accelerator']
		if(accelerator && accelerator instanceof String)
		{
			item.setAccelerator(parseKeyStroke(accelerator))
		}
		else if(accelerator && accelerator instanceof KeyStroke)
		{
			item.setAccelerator(accelerator)
		}
		char mnemonic = attributes['mnemonic']
		if(mnemonic != null && mnemonic != 0)
		{
			item.setMnemonic(mnemonic);
		}
		Closure action = getRequired(attributes, 'action')
		item.addActionListener(new ClosureActionListener(action));
		
		return item
	}
	
	/**
	 * Stroke syntax:
	 * 	series of elements joined by a '+'
	 * 	elements can be: a single character (i.e. 's' in a Ctrl+s)
	 * 	or one of the following (case-insensitive):
	 * 		'alt' - Alt key
	 * 		'ctrl' - Control key
	 * 		'shift' - Shift key
	 * 		'menu' - Menu shortcut key (Ctrl on Win/Linux, Cmd on Mac)
	 * 		'meta' - Meta key (whatever that is)
	 * 
	 * @param stroke
	 * @return
	 */
	private KeyStroke parseKeyStroke(String stroke)
	{
		def alt = ~/(?i)alt/
		def ctrl = ~/(?i)ctrl/
		def shift = ~/(?i)shift/
		def menu = ~/(?i)menu/
		def meta = ~/(?i)meta/
		
		//only one letter is allowed
		int key = 0
		int modifiers = 0
		
		stroke.split(/\+/).each {String part ->
			boolean matched = false
			switch(part)
			{
				case alt:
					modifiers |= InputEvent.ALT_DOWN_MASK
					matched = true
					break
				case ctrl:
					modifiers |= InputEvent.CTRL_DOWN_MASK
					matched = true
					break
				case shift:
					modifiers |= InputEvent.SHIFT_DOWN_MASK
					matched = true
					break
				case menu:
					modifiers |= Toolkit.defaultToolkit.menuShortcutKeyMask
					matched = true
					break
				case meta:
					modifiers |= InputEvent.META_DOWN_MASK
					matched = true
					break
				default:
					matched = false
					break
			}
			if(!matched)
			{
				Integer code = KeyMappingUtil.getCode(part.toUpperCase());
				if(code == null)
				{
					throw new IllegalArgumentException("No such key: $part")
				}
				else
				{
					key = code
				}
			}
		}
		
		return KeyStroke.getKeyStroke(key, modifiers); //greclipse thinks it's getKeyStroke(char, boolean)
	}
	
	private static class ClosureActionListener implements ActionListener
	{
		private Closure action;
		
		ClosureActionListener(Closure action)
		{
			this.action = action;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			action(e);
		}
	}
	
	private JMenu buildMenu(name, attributes, value)
	{
		String text = value?.toString()
		if(!text)
		{
			text = getRequired(attributes, 'text').toString()
		}
		JMenu menu = new JMenu(text)
		menus << menu
		return menu
	}
	
	private def getRequired(Map map,key)
	{
		def obj = map[key]
		if(obj)
			return obj
		else
			throw new IllegalArgumentException("Missing required entry: $key")
	}
	
	public JMenuBar build()
	{
		menus.each {
			menuBar.add(it)
		}
		return menuBar
	}
}
