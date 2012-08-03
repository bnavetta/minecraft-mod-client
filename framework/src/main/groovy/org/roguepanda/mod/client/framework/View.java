package org.roguepanda.mod.client.framework;

import java.awt.Container;

public interface View
{
	/**
	 * Attaches the view's components to the provided container
	 * @param container
	 */
	public void initialize(Container container);
	
	/**
	 * Called when the view is changed to allow the view to clean up (i.e. close dialogs).
	 * The view does not need to remove components from the container, as that will be done by the {@link ViewController}
	 */
	public void close();
}
