package model;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ComboModelTypeCol extends AbstractListModel implements ComboBoxModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> list = null;
	private Object selection = null;
	
	public ComboModelTypeCol() {
		this.list = new ArrayList<String>();
		list.add("Sang");
		list.add("Plasma");
	}
	
	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public Object getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

}
