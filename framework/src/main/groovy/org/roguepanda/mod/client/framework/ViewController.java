package org.roguepanda.mod.client.framework;

import java.awt.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewController
{
	private Map<String, Presenter> presenters = new HashMap<String, Presenter>();
	
	private Presenter currentPresenter;
	
	private Container container;
	
	public void register(Presenter presenter)
	{
		presenters.put(presenter.getId(), presenter);
	}
	
	public boolean goTo(String presenter, Map<String, Object> state)
	{
		Presenter pres = presenters.get(presenter);
		if(pres == null)
		{
			throw new IllegalArgumentException("No registered presenter: " + presenter);
		}
		else
		{
			if(currentPresenter != null)
			{
				if(currentPresenter.canStop())
				{
					currentPresenter.stop();
					currentPresenter.getView().close();
					View view = pres.getView();
					view.initialize(container);
					pres.start(state);
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				pres.getView().initialize(container);
				pres.start(state);
				return true;
			}
		}
	}
	
	public Map<String, Presenter> getPresenters() {
		return presenters;
	}

	public void setPresenters(Map<String, Presenter> presenters) {
		this.presenters = presenters;
	}

	public Presenter getCurrentPresenter() {
		return currentPresenter;
	}

	public void setContainer(Container container)
	{
		this.container = container;
	}

	public void setPresenters(List<Presenter> presenters)
	{
		for(Presenter p : presenters)
		{
			register(p);
		}
	}
}
