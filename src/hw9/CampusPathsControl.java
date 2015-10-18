package hw9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CampusPathsControl {
	
	/**
	 * Add listeners to corresponding components in views
	 * @param view CampusPathsView
	 */
	public void addListeners(final CampusPathsView view) {
		// add listener to start buildings list
		final JComboBox<String> startList = view.getStart();
		startList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setFind(false);
				String startBuilding = (String) startList.getSelectedItem();
				if (!startBuilding.startsWith("Select")) {
					String[] start = startBuilding.split(": ");
					view.setStartBuilding(start[0]);
				} else {
					view.setStartBuilding(null);
				}
				view.repaint();
			}
		});
		
		// add listener to end buildings list
		final JComboBox<String> endList = view.getEnd();
		endList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setFind(false);
				String endBuilding = (String) endList.getSelectedItem();
				if (!endBuilding.startsWith("Select")) {
					String[] end = endBuilding.split(": ");
					view.setEndBuilding(end[0]);
				} else {
					view.setStartBuilding(null);
				}
				view.repaint();
			}
		});
		
		// add listener to find button
		JButton find = view.getFind();		
		find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setFind(true);
				view.repaint();
			}
		});
		
		// add listener to reset button
		JButton reset = view.getReset();
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setFind(false);
				view.setStartBuilding(null);
				view.setEndBuilding(null);
				view.repaint();
			}
		});
	}
}
