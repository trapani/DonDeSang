package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import pojo.Infirmiere;

public class ComboModelInfirmiere extends AbstractListModel implements ComboBoxModel{

	private static final long serialVersionUID = 1L;
	public static ArrayList<Infirmiere> listeInf = new ArrayList<Infirmiere>();
	private ArrayList<String> list = null;
	private Object selection = null;
	
	public ComboModelInfirmiere() {
		this.list = new ArrayList<String>();
		ArrayList<Infirmiere> listeInf = recupInfirmiere();
		for(Infirmiere inf : listeInf){
			this.list.add(inf.toString());
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
	
	public ArrayList<Infirmiere> recupInfirmiere(){
		
		String url = "jdbc:mysql://localhost/dondesang";
		String sql = "SELECT * FROM infirmiere";
		
		Infirmiere inf = null;
		ResultSet rs = null;
		
		try(Connection connection = DriverManager.getConnection(url, "root", "");
				Statement st = connection.createStatement()){
			rs = st.executeQuery(sql);
			while(rs.next()){
				inf = new Infirmiere();
				inf.setIdInfirmiere(rs.getInt("idInfirmiere"));
				inf.setNom(rs.getString("Nom"));
				inf.setPrenom(rs.getString("Prenom"));
				listeInf.add(inf);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listeInf;
	}
}
