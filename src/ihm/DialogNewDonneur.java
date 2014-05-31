package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Adresse;
import pojo.Donneur;
import pojo.Groupesanguin;
import pojo.Ville;
import test.HibernateUtil;
import model.ComboModelGrp;
import model.ComboModelSexe;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialogNewDonneur extends JDialog {

	private Transaction tx = null;
	
	private JPanel pnl_centre;
	private JPanel pnl_infodonneur;
	private JPanel pnl_adresse;
	private JPanel pnl_form;
	private JPanel buttonPane;
	
	private JTextField txt_nom;
	private JLabel lbl_nom;
	private JTextField txt_prenom;
	private JLabel lbl_prenom;
	private JTextField txt_numnat;
	private JLabel lbl_numnat;
	private JTextField txt_date;
	private JLabel lbl_date;
	private JTextField txt_email;
	private JLabel lbl_email;
	private JTextField txt_tel;
	private JLabel lbl_tel;
	private JTextField txt_gsm;
	private JLabel lbl_gsm;
	private JComboBox combo_grp;
	private ComboModelGrp model_grp = new ComboModelGrp();
	private JLabel lbl_grp;
	private JLabel lbl_sexe;
	private JComboBox combo_sexe;
	private ComboModelSexe model_sexe = new ComboModelSexe();
	private JTextField txt_rue;
	private JLabel lbl_rue;
	private JTextField txt_num;
	private JLabel lbl_num;
	private JTextField txt_cp;
	private JLabel lbl_cp;
	private JTextField txt_ville;
	private JLabel lbl_ville;
	private JTextField txt_form;
	private JLabel lbl_form;
	
	private Dimension dim_lbl = new Dimension(200,25);
	private Dimension dim_txtfield = new Dimension(150,25);
	
	private JButton bt_ajout_donneur;
	private JButton bt_annuler_fendonneur;
	
	//variable pour la date de naissance
	private int jour;
	private int mois;
	private int annee;
	
	private String url = "jdbc:mysql://localhost/dondesang";
	

	public static void main(String[] args) {
		try {
			DialogNewDonneur dialog = new DialogNewDonneur();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DialogNewDonneur() {
		this.setTitle("Nouveau donneur");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 600, 600);
		this.getContentPane().setLayout(new BorderLayout());
		build();
		reinitialisation();
		this.setResizable(false);
		this.pack();
	}
	
	public void build(){
		pnl_centre = new JPanel();
		pnl_centre.setLayout(new BoxLayout(pnl_centre, BoxLayout.Y_AXIS));
		this.getContentPane().add(pnl_centre, BorderLayout.CENTER);
		//Création du JPanel contenant tous les infos à encoder
		pnl_infodonneur = new JPanel();
		pnl_infodonneur.setBorder(new EmptyBorder(15, 5, 15, 5));
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		pnl_infodonneur.setLayout(gb);
		pnl_centre.add(pnl_infodonneur);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		lbl_nom = new JLabel("Nom : ");
		lbl_nom.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_nom,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		txt_nom = new JTextField();
		txt_nom.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_nom,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		lbl_prenom = new JLabel("Prenom : ");
		lbl_prenom.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_prenom,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		txt_prenom = new JTextField();
		txt_prenom.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_prenom,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		lbl_numnat = new JLabel("Numéro national : ");
		lbl_numnat.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_numnat,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		txt_numnat = new JTextField();
		txt_numnat.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_numnat,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		lbl_date = new JLabel("Date de naissance : (dd/mm/yyyy) ");
		lbl_date.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_date,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		txt_date = new JTextField();
		txt_date.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_date,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		lbl_email = new JLabel("Email : ");
		lbl_email.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_email,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		txt_email = new JTextField();
		txt_email.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_email,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		lbl_tel = new JLabel("Tél. : ");
		lbl_tel.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_tel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		txt_tel = new JTextField();
		txt_tel.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_tel,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		lbl_gsm = new JLabel("Gsm : ");
		lbl_gsm.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_gsm,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		txt_gsm = new JTextField();
		txt_gsm.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_gsm,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		lbl_grp = new JLabel("Grp Sanguin : ");
		lbl_grp.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_grp,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		combo_grp = new JComboBox(model_grp);
		combo_grp.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(combo_grp,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 8;
		lbl_sexe = new JLabel("Sexe : ");
		lbl_sexe.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_sexe,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		combo_sexe = new JComboBox(model_sexe);
		combo_sexe.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(combo_sexe,gbc);
		
		
		//Ajout du panel pour l'adresse
		pnl_adresse = new JPanel();
		TitledBorder tb = new TitledBorder("Adresse");
		pnl_adresse.setBorder(tb);
		pnl_centre.add(pnl_adresse);
		
		GridBagLayout gb_adresse = new GridBagLayout();
		GridBagConstraints gbc_adresse = new GridBagConstraints();
		pnl_adresse.setLayout(gb_adresse);
		
		gbc_adresse.gridx = 0;
		gbc_adresse.gridy = 0;
		lbl_rue = new JLabel("Rue :");
		lbl_rue.setPreferredSize(dim_lbl);
		pnl_adresse.add(lbl_rue,gbc_adresse);
		
		gbc_adresse.gridx = 1;
		gbc_adresse.gridy = 0;
		txt_rue = new JTextField();
		txt_rue.setPreferredSize(dim_txtfield);
		pnl_adresse.add(txt_rue,gbc_adresse);
		
		gbc_adresse.gridx = 0;
		gbc_adresse.gridy = 1;
		lbl_num = new JLabel("N° :");
		lbl_num.setPreferredSize(dim_lbl);
		pnl_adresse.add(lbl_num,gbc_adresse);
		
		gbc_adresse.gridx = 1;
		gbc_adresse.gridy = 1;
		txt_num = new JTextField();
		txt_num.setPreferredSize(dim_txtfield);
		pnl_adresse.add(txt_num,gbc_adresse);
		
		gbc_adresse.gridx = 0;
		gbc_adresse.gridy = 2;
		lbl_cp = new JLabel("CP :");
		lbl_cp.setPreferredSize(dim_lbl);
		pnl_adresse.add(lbl_cp,gbc_adresse);
		
		gbc_adresse.gridx = 1;
		gbc_adresse.gridy = 2;
		txt_cp = new JTextField();
		txt_cp.setPreferredSize(dim_txtfield);
		pnl_adresse.add(txt_cp,gbc_adresse);
		
		gbc_adresse.gridx = 0;
		gbc_adresse.gridy = 3;
		lbl_ville = new JLabel("Ville :");
		lbl_ville.setPreferredSize(dim_lbl);
		pnl_adresse.add(lbl_ville,gbc_adresse);
		
		gbc_adresse.gridx = 1;
		gbc_adresse.gridy = 3;
		txt_ville = new JTextField();
		txt_ville.setPreferredSize(dim_txtfield);
		pnl_adresse.add(txt_ville,gbc_adresse);
		
		//panel formulaire
		pnl_form = new JPanel();
		GridBagLayout gb_form = new GridBagLayout();
		GridBagConstraints gbc_form = new GridBagConstraints();
		pnl_form.setLayout(gb_form);
		pnl_centre.add(pnl_form);
		pnl_form.setBorder(new EmptyBorder(15, 5, 15, 5));
				
		gbc_form.gridx = 0;
		gbc_form.gridy = 1;
		lbl_form = new JLabel("Formulaire :");
		lbl_form.setPreferredSize(dim_lbl);
		pnl_form.add(lbl_form,gbc_form);
		
		gbc_form.gridx = 1;
		gbc_form.gridy = 1;
		txt_form = new JTextField();
		txt_form.setPreferredSize(dim_txtfield);
		pnl_form.add(txt_form,gbc_form);
		
		//Creation d'un JPanel pour les boutons "Ajouter" et "Annuler"
		//Un Layout est défini afin de permettre un écart de 30 px d'écart entre les boutons
		buttonPane = new JPanel();
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(30);
		buttonPane.setLayout(fl_buttonPane);
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		//Add du bouton "Ajouter"
		bt_ajout_donneur = new JButton("Ajouter");
		bt_ajout_donneur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = verification();
				if(flag){
					Session session = null;
					session = HibernateUtil.instance().openSession();
					
					
					
					try{
						tx = session.beginTransaction();
						
						//ajout de la ville si elle n'existe pas encore dans la db
					 	Ville ville = new Ville();
						ville.setNomVille(txt_ville.getText());
						ville.setCp(txt_cp.getText());
						
						ArrayList<Ville> liste = chargerVille();
						boolean ajout = true;
						for(Ville v : liste){
							if (v.getNomVille().equalsIgnoreCase(ville.getNomVille()) && v.getCp().equalsIgnoreCase(ville.getCp())){
								ville.setIdVille(v.getIdVille());
								ajout = false;
							}
						}
						
						//ajout de l'adresse
						Adresse adresse = new Adresse();
						adresse.setRue(txt_rue.getText());
						adresse.setNumero(txt_num.getText());
						adresse.setVille(ville);
						
						//choix du groupe sanguin
						Groupesanguin grp  = new Groupesanguin();
						if(combo_grp.getSelectedItem().toString().equalsIgnoreCase("A +")){
							grp.setGrp("A");
							grp.setRhesus('+');
						}else if(combo_grp.getSelectedItem().toString().equalsIgnoreCase("A -")){
							grp.setGrp("A");
							grp.setRhesus('-');
						}else if(combo_grp.getSelectedItem().toString().equalsIgnoreCase("B +")){
							grp.setGrp("B");
							grp.setRhesus('+');
						}else if(combo_grp.getSelectedItem().toString().equalsIgnoreCase("B -")){
							grp.setGrp("B");
							grp.setRhesus('-');
						}else if(combo_grp.getSelectedItem().toString().equalsIgnoreCase("AB +")){
							grp.setGrp("AB");
							grp.setRhesus('+');
						}else if(combo_grp.getSelectedItem().toString().equalsIgnoreCase("AB -")){
							grp.setGrp("AB");
							grp.setRhesus('-');
						}else if(combo_grp.getSelectedItem().toString().equalsIgnoreCase("O +")){
							grp.setGrp("O");
							grp.setRhesus('+');
						}else if(combo_grp.getSelectedItem().toString().equalsIgnoreCase("O -")){
							grp.setGrp("O");
							grp.setRhesus('-');
						}
						
						ArrayList<Groupesanguin> listegrp = recupGrp();
						for(Groupesanguin gr : listegrp){
							if (gr.getGrp().equalsIgnoreCase(grp.getGrp()) && gr.getRhesus()==grp.getRhesus()){
								grp.setIdGrp(gr.getIdGrp());
							}
						}
						
						//ajout du donneur
						Donneur donneur = new Donneur();
						donneur.setNom(txt_nom.getText());
						donneur.setPrenom(txt_prenom.getText());
						donneur.setNumNational(txt_numnat.getText());
						donneur.setDateNaissance(new Date(annee-1900, mois-1, jour));
						if(!txt_email.getText().isEmpty()){
							donneur.setEmail(txt_email.getText());
						}
						if(!txt_tel.getText().isEmpty()){
							donneur.setTel(txt_tel.getText());
						}
						if(!txt_gsm.getText().isEmpty()){
							donneur.setGsm(txt_gsm.getText());
						}
						donneur.setGroupesanguin(grp);
						donneur.setAdresse(adresse);
						if(combo_sexe.getSelectedItem().toString().equalsIgnoreCase("Homme")){
							donneur.setSexe('H');
						}else{
							donneur.setSexe('F');
						}
						
						ajout=true;
						ArrayList<Donneur> listeDonneur = recupDonneur();
						for(Donneur don : listeDonneur){
							if (don.getNumNational().equalsIgnoreCase(donneur.getNumNational())){
								JOptionPane.showMessageDialog(null, "Le donneur est déjà enregistré");
								ajout=false;
							}
						}
						
						//TODO
						if(ajout){
							session.persist(ville);
							session.persist(adresse);
							session.persist(donneur);
							tx.commit();
							JOptionPane.showMessageDialog(null, "Enregistrement effectué avec succés");
						}
						
					}catch(HibernateException he){
						if (tx != null) tx.rollback(); 
						he.printStackTrace(); 
					}finally{
						session.close();
					}
				}
				chargerVille();
			}
		});
		buttonPane.add(bt_ajout_donneur);
			
		//Add du bouton "Annuler"
		bt_annuler_fendonneur = new JButton("Annuler");
		bt_annuler_fendonneur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reinitialisation();
			}
		});
		buttonPane.add(bt_annuler_fendonneur);
			
		
	}
	
	public void reinitialisation(){
		txt_nom.setText(null);
		txt_prenom.setText(null);
		txt_numnat.setText(null);
		txt_date.setText(null);
		txt_email.setText(null);
		txt_tel.setText(null);
		txt_gsm.setText(null);
		combo_grp.setVisible(false);
		combo_grp.setSelectedItem(null);
		combo_grp.setVisible(true);
		combo_sexe.setVisible(false);
		combo_sexe.setSelectedItem(null);
		combo_sexe.setVisible(true);
		txt_rue.setText(null);
		txt_num.setText(null);
		txt_cp.setText(null);
		txt_ville.setText(null);
		txt_form.setText(null);
	}
	
	public boolean verification(){
		
		String verif = null;
		Matcher matcher;
		boolean flag = true;
		
		if(txt_nom.getText().isEmpty() && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le nom du nouveau donneur");
			flag=false;
		}
		
		if(txt_prenom.getText().isEmpty() && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le prénom du nouveau donneur");
			flag=false;
		}
		
		
		Pattern pat_numnat = Pattern.compile("^[0-9]{11}$");
		verif = txt_numnat.getText();
		if((!(matcher=pat_numnat.matcher(verif)).matches())&&flag==true){
			JOptionPane.showMessageDialog(null, "Le numéro national entré n'est pas correct");
			flag=false;
		}
		
		Pattern pat_date = Pattern.compile("^([0-9]{2})(/)([0-9]{2})(/)([0-9]{4})$");
		verif = txt_date.getText();
		if((!(matcher=pat_date.matcher(verif)).matches()) && flag==true){
			JOptionPane.showMessageDialog(null, "La date de naissance entrée n'est pas correcte");
			flag=false;
		}
		
		//vérification des jours, mois ainsi que l'année
		jour = Integer.parseInt(matcher.group(1));
		mois  = Integer.parseInt(matcher.group(3));
		annee = Integer.parseInt(matcher.group(5));
		
		switch (mois){
		case 1 : case 3 :
		case 5 : case 7 :
		case 8 : case 10:
		case 12 :
			if(jour<1 || jour>31){
				JOptionPane.showMessageDialog(null, "Le jour de la date de naissance entrée n'est pas correct");
				flag = false;
			}
			break;
			
		case 4 : case 6 : 
		case 9 : case 11:
			if(jour<1 || jour>30){
				JOptionPane.showMessageDialog(null, "Le jour de la date de naissance entrée n'est pas correct");
				flag = false;
			}
			break;
		
		case 2 :
			if((annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0){
				if(jour<1 || jour>29){
					JOptionPane.showMessageDialog(null, "Le jour de la date de naissance entrée n'est pas correct");
					flag = false;
				}
			}else{
				if(jour<1 || jour>28){
					JOptionPane.showMessageDialog(null, "Le jour de la date de naissance entrée n'est pas correct");
					flag = false;
				}
			}
			break;
		
		default :
			JOptionPane.showMessageDialog(null, "Le mois de la date de naissance entrée n'est pas correct");
			flag = false;
			break;
		}
		
		//collecte de sang uniquement chez les personnes agées de 18 à 65 ans
		int annee_auj = new Date().getYear() + 1900;
		
		if(annee>annee_auj-18 || annee<annee_auj-65){
			JOptionPane.showMessageDialog(null, "L'année de la date de naissance entrée n'est pas correcte");
			flag = false;
		}
		
		
		
		if(combo_grp.getSelectedItem()==null && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le groupe sanguin du nouveau donneur");
			flag=false;
		}
		
		if(combo_sexe.getSelectedItem()==null && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le sexe du nouveau donneur");
			flag=false;
		}
		
		if(txt_rue.getText().isEmpty() && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer la rue du nouveau donneur");
			flag=false;
		}
		
		if(txt_num.getText().isEmpty() && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le numéro de la rue du nouveau donneur");
			flag=false;
		}
		
		if(txt_cp.getText().isEmpty() && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le code postal du nouveau donneur");
			flag=false;
		}
		
		if(txt_ville.getText().isEmpty() && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer la ville du nouveau donneur");
			flag=false;
		}
		
		return flag;
	}
	
	//Récupération des villes existantes
	public ArrayList<Ville> chargerVille(){
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
		for(Groupesanguin g : listeGrp){
			System.out.println(g.getIdGrp() + " : " + g.getGrp() + " " + g.getRhesus());
		}
		return listeGrp;
	}
	
	//récupération de la liste des donneurs
	public ArrayList<Donneur> recupDonneur(){
		ArrayList<Donneur> listeDonneur = new ArrayList<Donneur>();
		String sql = "SELECT * FROM donneur";
		
		Donneur donneur = null;
		ResultSet rs = null;
		
		try(Connection connection = DriverManager.getConnection(url, "root", "");
				Statement st = connection.createStatement()){
			rs = st.executeQuery(sql);
			while(rs.next()){
				donneur = new Donneur();
				donneur.setNumNational(rs.getString("NumNational"));
				listeDonneur.add(donneur);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listeDonneur;
	}
}
