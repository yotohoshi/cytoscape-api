package org.cytoscape.util.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

/**
 * Button with drop down menu.
 * 
 * @CyAPI.InModule swing-util-api
 */
@SuppressWarnings("serial")
public class DropDownMenuButton extends JButton {

	private final Icon buttonIcon = new MenuArrowIcon();
	
	private ActionListener popupMenuActionListener;

	
	/**
	 * Creates a new DropDownMenuButton object.
	 * @param action  The action that this button represents.
	 */
	public DropDownMenuButton(AbstractAction action) {
		this();
		setAction(action);
	}
	
	public DropDownMenuButton(JPopupMenu popupMenu) {
		this();
		setPopupMenu(popupMenu);
	}
	
	public DropDownMenuButton() {
		setFocusPainted(false);
		setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4 + buttonIcon.getIconWidth()));
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		final Dimension dim = getSize();
		final Insets ins = getInsets();
		final int x = dim.width - ins.right;
		final int y = ins.top + ((dim.height - ins.top - ins.bottom - buttonIcon.getIconHeight()) / 2);
		buttonIcon.paintIcon(this, g, x, y);
	}

	public void setPopupMenu(JPopupMenu popupMenu) {
		if(popupMenuActionListener != null)
			removeActionListener(popupMenuActionListener);
		
		if(popupMenu != null) {
			popupMenuActionListener = e -> {
				popupMenu.show(DropDownMenuButton.this, 0, getHeight());
				popupMenu.requestFocusInWindow();
			};
			addActionListener(popupMenuActionListener);
		}
		
//		updateEnabled();
	}
	
	
	private static final class MenuArrowIcon implements Icon {
		
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g;
			// Turn AA on
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setColor(UIManager.getColor("Label.foreground"));
			g2.translate(x, y);
			g2.drawLine(2, 3, 6, 3);
			g2.drawLine(3, 4, 5, 4);
			g2.drawLine(4, 5, 4, 5);
			g2.translate(-x, -y);
		}

		@Override
		public int getIconWidth() {
			return 9;
		}

		@Override
		public int getIconHeight() {
			return 9;
		}
	}
}
