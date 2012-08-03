package org.roguepanda.mod.client.framework;

import java.util.Map;

public class TestStartPresenter implements Presenter, TestStartView.Presenter
{
	private TestStartView view;
	
	public boolean stopped = false;
	
	public String getId()
	{
		return "testStartPresenter";
	}

	public void start(Map<String, Object> state)
	{
		stopped = false;
		view.setPresenter(this);
		view.setMessage(state.get("message").toString());
	}
	
	public View getView()
	{
		return view;
	}
	
	public void setView(View view)
	{
		if(view instanceof TestStartView)
		{
			this.view = (TestStartView) view;
		}
		else
		{
			throw new IllegalArgumentException(view + " is not a TestStartView");
		}
	}

	public boolean canStop()
	{
		return true;
	}

	public void stop()
	{
		System.out.println("Stopping");
		stopped = true;
	}

	public void onStop()
	{
		stopped = true;
	}

	public Class<? extends View> getViewType()
	{
		return TestStartView.class;
	}

}
