package com.ardublock.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ardublock.core.Context;
import com.ardublock.ui.OpenblocksFrame;

public class TestButtonListener implements ActionListener {

	private OpenblocksFrame parentFrame;
	public TestButtonListener(OpenblocksFrame frame)
	{
		Context.getContext();
		parentFrame = frame;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		parentFrame.doTest();
	}
}
