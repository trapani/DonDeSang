package model;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ComboModelSexe extends AbstractListModel implements ComboBoxModel{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> list = null;
	private Object selection = null;
	
	public ComboModelSexe() {
		this.list = new ArrayList<String>();
		list.add("Homme");
		list.add("Femme");
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
