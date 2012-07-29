package org.roguepanda.mod.client.framework;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class Application
{
	public static interface MenuProvider
	{
		public JMenuBar createMenu();
	}
	
	public static interface FrameProvider
	{
		public JFrame createFrame();
	}
	
	private MenuProvider menuProvider;
	
	private FrameProvider frameProvider;
	
	private ViewController viewController;
	
	private JFrame frame;
	
	private String startPresenter;
	
	private Map<String, Object> startState;

	public String getStartPresenter() {
		return startPresenter;
	}

	public void setStartPresenter(String startPresenter) {
		this.startPresenter = startPresenter;
	}

	public Map<String, Object> getStartState() {
		return startState;
	}

	public void setStartState(Map<String, Object> startState) {
		this.startState = startState;
	}

	public MenuProvider getMenuProvider() {
		return menuProvider;
	}

	public void setMenuProvider(MenuProvider menuProvider) {
		this.menuProvider = menuProvider;
	}

	public ViewController getViewController() {
		return viewController;
	}

	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public FrameProvider getFrameProvider() {
		return frameProvider;
	}

	public void setFrameProvider(FrameProvider frameProvider) {
		this.frameProvider = frameProvider;
	}

	public void start()
	{
		System.setProperty("apple.laf.useScreenMenuBar", "true"); //So Macs use the Menu Bar of Destiny (I forgot what it's actually called)
		frame = frameProvider.createFrame();
		frame.setJMenuBar(menuProvider.createMenu());
		viewController.setContainer(frame);
		viewController.goTo(startPresenter, startState);
		frame.pack();
		frame.setVisible(true);
	}
}
