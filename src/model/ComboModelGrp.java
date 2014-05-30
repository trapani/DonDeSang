package model;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ComboModelGrp extends AbstractListModel implements ComboBoxModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> list = null;
	private Object selection = null;
	
	public ComboModelGrp() {
		this.list = new ArrayList<String>();
		list.add("A +");
		list.add("A -");
		list.add("B +");
		list.add("B -");
		list.add("AB +");
		list.add("AB -");
		list.add("O +");
		list.add("O -");
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
