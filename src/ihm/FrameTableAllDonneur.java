package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import pojo.Adresse;
import pojo.Donneur;
import pojo.Groupesanguin;
import pojo.Ville;
import model.TableModelDonneur;

public class FrameTableAllDonneur extends JFrame{
	
	private ArrayList<Donneur> listeDonneur;
	private JTable table;
	private TableModelDonneur model_table;
	
	private String url = "jdbc:mysql://localhost/dondesang";
	
	/*public static void main(String[] args) {
		FrameTableAllDonneur frame = new FrameTableAllDonneur();

	}*/
	
	public FrameTableAllDonneur() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.setTitle("Liste des donneurs");
		this.setPreferredSize(new Dimension(1500, 600));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		build();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void build(){
		/*ArrayList<Donneur> listeDon = recupDonneur();
		for(Donneur d : listeDon){
			System.out.println(d.getNom() + " " + d.getNumNational() + " : " + d.getGroupesanguin().toString());
			System.out.println(d.getAdresse().getRue() + " " + d.getAdresse().getNumero() + " " + d.getAdresse().getVille().getCp() + " " + d.getAdresse().getVille().getNomVille());
		}*/
		listeDonneur = recupDonneur();
		table = new JTable();
		model_table = new TableModelDonneur(listeDonneur);
		table.setModel(model_table);
		
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setViewportView(table);
		this.getContentPane().add(scrollpane,BorderLayout.CENTER);
	}
	
	//Récupération des villes existantes
		public ArrayList<Ville> recupVille(){
			ArrayList<Ville> listeVille = new ArrayList<Ville>();
			String sql = "SELECT * FROM ville";
			
			Ville ville = null;
			ResultSet rs = null;
			try(Connection connection = DriverManager.getConnection(url, "root", "");
					Statement st = connection.createStatement()){
				rs = st.executeQuery(sql);
				while(rs.next()){
					ville = new Ville();
					ville.setNomVille(rs.getString("NomVille"));
					ville.setCp(rs.getString("CP"));
					ville.setIdVille(rs.getInt("idVille"));
					listeVille.add(ville);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return listeVille;
		}
		
		//Récupération des villes existantes
		public ArrayList<Adresse> recupAdresse(){
			ArrayList<Adresse> listeAdresse = new ArrayList<Adresse>();
			String sql = "SELECT * FROM adresse";
			ArrayList<Ville> listVille = recupVille();
			int idVille;
				
			Adresse adresse = null;
			ResultSet rs = null;
			try(Connection connection = DriverManager.getConnection(url, "root", "");
					Statement st = connection.createStatement()){
				rs = st.executeQuery(sql);
				while(rs.next()){
					adresse = new Adresse();
					adresse.setIdAdresse(rs.getInt("idAdresse"));
					adresse.setNumero(rs.getString("Numero"));
					adresse.setRue(rs.getString("Rue"));
					
					idVille = rs.getInt("idVille");
					for(Ville v : listVille){
						if(v.getIdVille() == idVille){
							adresse.setVille(v);
						}
					}

					listeAdresse.add(adresse);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
					
			return listeAdresse;
		}
					
	//récupération des groupes sanguins pour le choix
		public ArrayList<Groupesanguin> recupGrp(){
			ArrayList<Groupesanguin> listeGrp = new ArrayList<Groupesanguin>();
			String sql = "SELECT * FROM groupesanguin";
			
			Groupesanguin grp = null;
			ResultSet rs = null;
			try(Connection connection = DriverManager.getConnection(url, "root", "");
					Statement st = connection.createStatement()){
				rs = st.executeQuery(sql);
				while(rs.next()){
					grp = new Groupesanguin();
					grp.setIdGrp(rs.getInt("idGrp"));
					grp.setGrp(rs.getString("grp"));
					grp.setRhesus(rs.getString("Rhesus").charAt(0));
					listeGrp.add(grp);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return listeGrp;
		}
		
		//récupération de la liste des donneurs
		public ArrayList<Donneur> recupDonneur(){
			ArrayList<Donneur> listeDonneur = new ArrayList<Donneur>();
			String sql = "SELECT * FROM donneur";
			
			Donneur donneur = null;
			ResultSet rs = null;
			ArrayList<Groupesanguin> listgrp = recupGrp();
			ArrayList<Adresse> listadresse = recupAdresse();
			int idgrp;
			int idAdresse;
			
			try(Connection connection = DriverManager.getConnection(url, "root", "");
					Statement st = connection.createStatement()){
				rs = st.executeQuery(sql);
				while(rs.next()){
					donneur = new Donneur();
					donneur.setIdDonneur(rs.getInt("idDonneur"));
					donneur.setNom(rs.getString("Nom"));
					donneur.setPrenom(rs.getString("Prenom"));
					donneur.setNumNational(rs.getString("NumNational"));
					donneur.setDateNaissance(rs.getDate("DateNaissance"));
					donneur.setSexe(rs.getString("Sexe").charAt(0));
					if(rs.getString("email")!=null){
						donneur.setEmail(rs.getString("email"));
					}else{
						donneur.setEmail("");
					}
					if(rs.getString("tel")!=null){
						donneur.setTel(rs.getString("tel"));
					}else{
						donneur.setTel("");
					}
					if(rs.getString("gsm")!=null){
						donneur.setGsm(rs.getString("gsm"));
					}else{
						donneur.setGsm("");
					}
					
					idAdresse = rs.getInt("idAdresse");
					
					for(Adresse ad : listadresse){
						if(ad.getIdAdresse() == idAdresse){
							donneur.setAdresse(ad);
						}
					}
					
					idgrp = rs.getInt("idGrp");
					
					for(Groupesanguin g : listgrp){
						if(g.getIdGrp() == idgrp){
							donneur.setGroupesanguin(g);
						}
					}
					
					listeDonneur.add(donneur);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return listeDonneur;
		}
}
