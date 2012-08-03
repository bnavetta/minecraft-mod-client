package org.roguepanda.mod.client.framework;

import java.util.Map;

import org.roguepanda.mod.client.framework.Application.FrameProvider;
import org.roguepanda.mod.client.framework.Application.MenuProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public abstract class FrameworkConfig
{
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private FrameProvider frameProvider;
	
	@Autowired
	private MenuProvider menuProvider;
	
	@Bean
	public ViewController viewController()
	{
		ViewController controller = new ViewController();
		for(Presenter presenter : context.getBeansOfType(Presenter.class).values())
		{
			controller.register(presenter);
		}
		return controller;
	}
	
	@Bean(destroyMethod="stop")
	public Application application()
	{
		viewInjector();
		Application app = new Application();
		app.setFrameProvider(frameProvider);
		app.setMenuProvider(menuProvider);
		app.setViewController(viewController());
		app.setStartPresenter(getStartPresenter());
		app.setStartState(getStartState());
		return app;
	}
	
	@Bean
	public ViewInjector viewInjector()
	{
		return new ViewInjector();
	}
	
	protected abstract String getStartPresenter();
	protected abstract Map<String, Object> getStartState();
}
