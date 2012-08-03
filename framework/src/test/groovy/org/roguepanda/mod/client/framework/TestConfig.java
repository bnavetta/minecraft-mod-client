package org.roguepanda.mod.client.framework;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig extends FrameworkConfig
{
	@Override
	protected String getStartPresenter()
	{
		return "testStartPresenter";
	}

	@Override
	protected Map<String, Object> getStartState()
	{
		Map<String, Object> state = new HashMap<String, Object>();
		state.put("message", "Hello, World!");
		return state;
	}

}
