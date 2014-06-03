package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import pojo.Medecin;

public class ComboModelMedecin extends AbstractListModel implements ComboBoxModel{

	private static final long serialVersionUID = 1L;
	public static ArrayList<Medecin> listeMed = new ArrayList<Medecin>();
	private ArrayList<String> list = null;
	private Object selection = null;
	
	public ComboModelMedecin() {
		this.list = new ArrayList<String>();
		ArrayList<Medecin> listeMed = recupMedecin();
		for(Medecin med : listeMed){
			this.list.add(med.toString());
		}
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
	
	public ArrayList<Medecin> recupMedecin(){
		
		String url = "jdbc:mysql://localhost/dondesang";
		String sql = "SELECT * FROM medecin";
		
		Medecin med = null;
		ResultSet rs = null;
		
		try(Connection connection = DriverManager.getConnection(url, "root", "");
				Statement st = connection.createStatement()){
			rs = st.executeQuery(sql);
			while(rs.next()){
				med = new Medecin();
				med.setIdMedecin(rs.getInt("idMedecin"));
				med.setNom(rs.getString("Nom"));
				med.setPrenom(rs.getString("Prenom"));
				listeMed.add(med);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listeMed;
	}
}
