package model;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import pojo.Adresse;
import pojo.Donneur;
import pojo.Ville;

public class TableModelDonneur extends AbstractTableModel{

	private String[] columnHeaders = new String[]{
			"Nom",
			"Prenom",
			"Num national",
			"Date de naissance",
			"Email",
			"Tel.",
			"Gsm",
			"Grp",
			"Sexe",
			"Rue",
			"N°",
			"CP",
			"Ville"
			
	};
	
	private List<Donneur> data = new LinkedList<Donneur>();
	
	public TableModelDonneur(List<Donneur> d) {
		this.data = d;
	}
	
	@Override
	public int getColumnCount() {
		return columnHeaders.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return columnHeaders[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Donneur donneur = data.get(rowIndex);
		Adresse adresse = donneur.getAdresse();
		Ville ville = adresse.getVille();
		if(donneur!=null){
			switch(columnIndex){
			case 0:
				return donneur.getNom();
			case 1:
				return donneur.getPrenom();
			case 2:
				return donneur.getNumNational();
			case 3:
				return donneur.getDateNaissance();
			case 4:
				return donneur.getEmail();
			case 5:
				return donneur.getTel();
			case 6:
				return donneur.getGsm();
			case 7:
				return donneur.getGroupesanguin().toString();
			case 8:
				if(donneur.getSexe()=='H'){
					return "Homme";
				}else{
					return "Femme";
				}
			case 9:
				return adresse.getRue();
			case 10:
				return adresse.getNumero();
			case 11:
				return ville.getCp();
			case 12:
				return ville.getNomVille();
			}
		}
		return donneur.toString();
	}

}
