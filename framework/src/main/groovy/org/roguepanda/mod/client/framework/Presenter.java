package org.roguepanda.mod.client.framework;

import java.awt.Container;
import java.util.Map;

public interface Presenter
{
	/**
	 * Returns the unique identifier string that can be used to trigger this presenter
	 * @return
	 */
	public String getId();
	
	/**
	 * Called to initialize the presenter.
	 * @param state parameters for the presenter
	 * @param container the container to initialize the view in
	 */
	public void start(Container container, Map<String, Object> state);
	
	/**
	 * Called when a change of presenter is requested. This gives the presenter an opportunity to prevent
	 * a change in presenter and prompt the user
	 * @return {@code true} if the presenter can stop, {@code false if it cannot}
	 */
	public boolean canStop();
	
	/**
	 * Called before the presenter is changed. This allows the presenter to clean up.
	 */
	public void stop();
}
