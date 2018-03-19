package com.ardublock.custom;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ardublock.translator.AutoFormat;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNameDuplicatedException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.renderable.RenderableBlock;
import edu.mit.blocks.workspace.Workspace;
import edu.mit.custom.Utils;

public class CodeGenerator {

	private static final String NN_ENTRANCE = "ctrl_network";
	
	private static ResourceBundle uiMessageBundle = Utils.getLangResourceBundle();

	public static void genCode(com.ardublock.core.Context context, Workspace workspace, JFrame parentFrame) {

		
		
		

		StringBuilder code = new StringBuilder();		
		Translator translator = new Translator(workspace);
		translator.reset();
		boolean success = false;
		
		Iterable<RenderableBlock> renderableBlocks = workspace.getRenderableBlocks();
		RenderableBlock entrance = null;
		

		Set<RenderableBlock> layerDefBlockSet = new HashSet<RenderableBlock>();
		
		
		for(RenderableBlock renderableBlock:renderableBlocks){
			Block block = renderableBlock.getBlock();
			// find entrance
			if(block.getGenusName() == NN_ENTRANCE){
				if(entrance!= null){
					context.highlightBlock(renderableBlock);
					JOptionPane.showMessageDialog(parentFrame,
							uiMessageBundle.getString("ardublock.translator.exception.multipleentranceFound"), "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				entrance = renderableBlock;
			}
			// find layer definition
			if (block.getGenusName().equals("ctrl_layer_def")) {
				layerDefBlockSet.add(renderableBlock);
			}
		}

		if(entrance == null){
			JOptionPane.showMessageDialog(parentFrame,
					uiMessageBundle.getString("ardublock.translator.exception.entrance"), "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
		
		
		/* TODO **********************************************************************************************/
		Set<RenderableBlock> loopBlockSet = new HashSet<RenderableBlock>();
		Set<RenderableBlock> subroutineBlockSet = new HashSet<RenderableBlock>();
		Set<RenderableBlock> scoopBlockSet = new HashSet<RenderableBlock>();
		Set<RenderableBlock> guinoBlockSet = new HashSet<RenderableBlock>();
		/* TODO **********************************************************************************************/
		
	
		

		try {

			for (RenderableBlock renderableBlock : loopBlockSet) {
				translator.setRootBlockName("loop");
				Block loopBlock = renderableBlock.getBlock();
				code.append(translator.translate(loopBlock.getBlockID()));
			}

			for (RenderableBlock renderableBlock : scoopBlockSet) {
				translator.setRootBlockName("scoop");
				Block scoopBlock = renderableBlock.getBlock();
				code.append(translator.translate(scoopBlock.getBlockID()));
			}
			for (RenderableBlock renderableBlock : guinoBlockSet) {
				translator.setRootBlockName("guino");
				Block guinoBlock = renderableBlock.getBlock();
				code.append(translator.translate(guinoBlock.getBlockID()));
			}

			for (RenderableBlock renderableBlock : subroutineBlockSet) {
				translator.setRootBlockName("subroutine");
				Block subroutineBlock = renderableBlock.getBlock();
				code.append(translator.translate(subroutineBlock.getBlockID()));
			}

			translator.beforeGenerateHeader();
			code.insert(0, translator.genreateHeaderCommand());
		} catch (SocketNullException e1) {
			e1.printStackTrace();
			success = false;
			Long blockId = e1.getBlockId();
			Iterable<RenderableBlock> blocks = workspace.getRenderableBlocks();
			for (RenderableBlock renderableBlock2 : blocks) {
				Block block2 = renderableBlock2.getBlock();
				if (block2.getBlockID().equals(blockId)) {
					context.highlightBlock(renderableBlock2);
					break;
				}
			}
			JOptionPane.showMessageDialog(parentFrame,
					uiMessageBundle.getString("ardublock.translator.exception.socketNull"), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (BlockException e2) {
			e2.printStackTrace();
			success = false;
			Long blockId = e2.getBlockId();
			Iterable<RenderableBlock> blocks = workspace.getRenderableBlocks();
			for (RenderableBlock renderableBlock2 : blocks) {
				Block block2 = renderableBlock2.getBlock();
				if (block2.getBlockID().equals(blockId)) {
					context.highlightBlock(renderableBlock2);
					break;
				}
			}
			JOptionPane.showMessageDialog(parentFrame, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SubroutineNotDeclaredException e3) {
			e3.printStackTrace();
			success = false;
			Long blockId = e3.getBlockId();
			Iterable<RenderableBlock> blocks = workspace.getRenderableBlocks();
			for (RenderableBlock renderableBlock3 : blocks) {
				Block block2 = renderableBlock3.getBlock();
				if (block2.getBlockID().equals(blockId)) {
					context.highlightBlock(renderableBlock3);
					break;
				}
			}
			JOptionPane.showMessageDialog(parentFrame,
					uiMessageBundle.getString("ardublock.translator.exception.subroutineNotDeclared"), "Error",
					JOptionPane.ERROR_MESSAGE);

		}
		
		
		/* TODO **********************************************************************************************/
		
		
		
		

		if (success) {
			AutoFormat formatter = new AutoFormat();
			String codeOut = code.toString();

			if (context.isNeedAutoFormat) {
				codeOut = formatter.format(codeOut);
			}

			if (!context.isInArduino()) {
				System.out.println(codeOut);
			}
			context.didGenerate(codeOut);
		}
	}
}
