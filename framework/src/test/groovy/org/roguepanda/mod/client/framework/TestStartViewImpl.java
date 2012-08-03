package org.roguepanda.mod.client.framework;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class TestStartViewImpl implements TestStartView
{
	private Presenter presenter;
	
	private JLabel label;
	private JButton button;
	
	public TestStartViewImpl()
	{
		label = new JLabel();
		button = new JButton("Stop");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				presenter.onStop();
			}
		});
	}
	
	public void setPresenter(Presenter presenter)
	{
		this.presenter = presenter;
	}
	
	public void initialize(Container container)
	{
		container.add(label);
		container.add(button);
	}

	public void close()
	{
		//Nothing to do
	}

	public void setMessage(String message)
	{
		label.setText(message);
	}

}
