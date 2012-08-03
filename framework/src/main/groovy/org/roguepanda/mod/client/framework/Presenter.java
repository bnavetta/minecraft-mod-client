package org.roguepanda.mod.client.framework;

import java.util.Map;

public interface Presenter
{
	/**
	 * Return the Presenter's view so that it can be initialized by the {@link ViewController}
	 * @return
	 */
	public View getView();
	
	/**
	 * Set the presenter's view. An {@link IllegalArgumentException} should be thrown if the view is not of the correct type
	 * @param view
	 */
	public void setView(View view);
	
	/**
	 * Get the type of the view, for use in view injection
	 * @return
	 */
	public Class<? extends View> getViewType();
	
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
	public void start(Map<String, Object> state);
	
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
