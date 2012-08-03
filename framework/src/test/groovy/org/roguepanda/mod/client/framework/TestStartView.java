package org.roguepanda.mod.client.framework;

public interface TestStartView extends View
{
	public void setMessage(String message);
	
	public void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		public void onStop();
	}
}
