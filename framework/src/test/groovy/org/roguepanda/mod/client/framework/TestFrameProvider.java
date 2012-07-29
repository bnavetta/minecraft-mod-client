package org.roguepanda.mod.client.framework;

import javax.swing.JFrame;

import org.roguepanda.mod.client.framework.Application.FrameProvider;

public class TestFrameProvider implements FrameProvider
{

	public JFrame createFrame() 
	{
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("Mod-Client Framework Test");
		return frame;
	}

}
