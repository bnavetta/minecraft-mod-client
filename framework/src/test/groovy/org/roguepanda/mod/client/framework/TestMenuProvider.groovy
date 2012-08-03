package org.roguepanda.mod.client.framework

import javax.swing.JMenuBar;

import org.roguepanda.mod.client.framework.Application.MenuProvider

class TestMenuProvider implements MenuProvider
{

	public JMenuBar createMenu()
	{
		MenuBuilder builder = new MenuBuilder();
		builder.with {
			menu('File') {
				item(text:'Open', action: { println 'Open' })
				item(text: 'Save', action: { println 'Save' })
				separator()
				item(text:'Exit', action: { System.exit(0) })
			}
			menu('Edit') {
				item(text: 'Undo', action: { println 'Undo' })
				item(text:'Redo', action: { println 'Redo' })
			}
		}
		return builder.build()
	}
	
}