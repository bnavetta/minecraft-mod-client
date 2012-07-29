package org.roguepanda.mod.client.framework;

import java.awt.Container;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ViewController implements BeanPostProcessor
{
	private Map<String, Presenter> presenters;
	
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
			if(currentPresenter.canStop())
			{
				currentPresenter.stop();
				pres.start(container, state);
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public void setContainer(Container container)
	{
		this.container = container;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
	{
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
	{
		if(bean instanceof Presenter)
		{
			register((Presenter) bean);
		}
		return bean;
	}
}
