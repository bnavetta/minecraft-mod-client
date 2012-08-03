package org.roguepanda.mod.client.framework;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class ViewInjector implements InitializingBean
{
	@Autowired
	private ApplicationContext context;
	
	public void afterPropertiesSet() throws Exception
	{
		for(Presenter presenter : context.getBeansOfType(Presenter.class).values())
		{
			View view = context.getBean(presenter.getViewType());
			presenter.setView(view);
		}
	}

}
