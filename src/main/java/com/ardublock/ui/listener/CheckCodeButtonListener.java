package com.ardublock.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.ardublock.core.Context;

import edu.mit.blocks.workspace.Workspace;

public class CheckCodeButtonListener implements ActionListener {

	private JFrame parentFrame;
	private Context context;
	private Workspace workspace; 
	
	public CheckCodeButtonListener(JFrame frame, Context context)
	{
		this.parentFrame = frame;
		this.context = context;
		workspace = context.getWorkspaceController().getWorkspace();
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("check code");
	}

}
