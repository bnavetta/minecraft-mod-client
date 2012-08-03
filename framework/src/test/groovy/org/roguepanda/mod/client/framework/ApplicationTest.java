package org.roguepanda.mod.client.framework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/test-config.xml")
public class ApplicationTest
{
	@Autowired
	private Application application;
	
	@Autowired
	private TestStartPresenter presenter;
	
	@Test
	public void test() throws InterruptedException
	{
		//TODO: add actual assertions one I figure out how to test Swing
		application.start();
		while(!presenter.stopped)
		{
			Thread.sleep(10);
		}
		application.stop();
	}

}
