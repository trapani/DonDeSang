package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Adresse;
import pojo.Collation;
import pojo.Collecte;
import pojo.Donneur;
import pojo.Formulaire;
import pojo.Groupesanguin;
import pojo.Infirmiere;
import pojo.Medecin;
import pojo.Poche;
import pojo.Stock;
import pojo.Ville;
import test.HibernateUtil;
import model.ComboModelAutoTransf;
import model.ComboModelGrp;
import model.ComboModelInfirmiere;
import model.ComboModelMedecin;
import model.ComboModelSexe;
import model.ComboModelTypeCol;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialogNewCollecte extends JDialog {

	private Transaction tx = null;
	
	private JPanel pnl_centre;
	private JPanel buttonPane;
	
	private JTextField txt_numnat;
	private JLabel lbl_numnat;
	
	private JComboBox combo_type;
	private ComboModelTypeCol model_type = new ComboModelTypeCol();
	private JLabel lbl_type;
	private JLabel lbl_med;
	private JComboBox combo_med;
	private ComboModelMedecin model_med = new ComboModelMedecin();
	private JLabel lbl_inf;
	private JComboBox combo_inf;
	private ComboModelInfirmiere model_inf = new ComboModelInfirmiere();
	private JLabel lbl_autot;
	private JComboBox combo_autot;
	private ComboModelAutoTransf model_autot = new ComboModelAutoTransf();
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
	

	/*public static void main(String[] args) {
		try {
			DialogNewCollecte dialog = new DialogNewCollecte();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public DialogNewCollecte() {
		this.setTitle("Nouvelle collecte");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(DialogNewCollecte.class.getResource("/img/collecte_16x16.png")));
		this.setBounds(100, 100, 600, 600);
		this.getContentPane().setLayout(new BorderLayout());
		build();
		reinitialisation();
		this.setResizable(false);
		this.setModal(true);
		this.pack();
	}
	
	public void build(){
		pnl_centre = new JPanel();
		pnl_centre.setBorder(new EmptyBorder(15, 5, 15, 5));
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		pnl_centre.setLayout(gb);
		this.getContentPane().add(pnl_centre,BorderLayout.CENTER);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		lbl_numnat = new JLabel("Numéro national : ");
		lbl_numnat.setPreferredSize(dim_lbl);
		pnl_centre.add(lbl_numnat,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		txt_numnat = new JTextField();
		txt_numnat.setPreferredSize(dim_txtfield);
		pnl_centre.add(txt_numnat,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		lbl_type = new JLabel("Type : ");
		lbl_type.setPreferredSize(dim_lbl);
		pnl_centre.add(lbl_type,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		combo_type = new JComboBox(model_type);
		combo_type.setPreferredSize(dim_txtfield);
		pnl_centre.add(combo_type,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		lbl_med = new JLabel("Medecin : ");
		lbl_med.setPreferredSize(dim_lbl);
		pnl_centre.add(lbl_med,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		combo_med = new JComboBox(model_med);
		combo_med.setPreferredSize(dim_txtfield);
		pnl_centre.add(combo_med,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		lbl_inf = new JLabel("Infirmiere : ");
		lbl_inf.setPreferredSize(dim_lbl);
		pnl_centre.add(lbl_inf,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		combo_inf = new JComboBox(model_inf);
		combo_inf.setPreferredSize(dim_txtfield);
		pnl_centre.add(combo_inf,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		lbl_autot = new JLabel("Autotransfusion : ");
		lbl_autot.setPreferredSize(dim_lbl);
		pnl_centre.add(lbl_autot,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		combo_autot = new JComboBox(model_autot);
		combo_autot.setPreferredSize(dim_txtfield);
		pnl_centre.add(combo_autot,gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		lbl_form = new JLabel("Formulaire :");
		lbl_form.setPreferredSize(dim_lbl);
		pnl_centre.add(lbl_form,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		txt_form = new JTextField();
		txt_form.setPreferredSize(dim_txtfield);
		txt_form.setEditable(false);
		txt_form.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selection = chooser.getSelectedFile();
					txt_form.setText(selection.getAbsolutePath());
				}
			}
		});
		pnl_centre.add(txt_form,gbc);
		
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
						flag = false;  //car il faut vérifier si le numéro national entré existe déjà dans la BD
						ArrayList<Donneur> listeDonneur = recupDonneur();
						Donneur donneur = new Donneur();
						for(Donneur d : listeDonneur){
							if(d.getNumNational().equalsIgnoreCase(txt_numnat.getText())){
								//donneur.setIdDonneur(d.getIdDonneur());
								donneur = d;
								flag = true;
							}
						}
						if(!flag){
							JOptionPane.showMessageDialog(null, "Le donneur n'est pas encore enregistré");
						}
						
						Medecin med = ComboModelMedecin.listeMed.get(combo_med.getSelectedIndex());
						Infirmiere inf = ComboModelInfirmiere.listeInf.get(combo_inf.getSelectedIndex());
						
						Collecte collecte = new Collecte();
						collecte.setDateCollecte(new Date());
						collecte.setInfirmiere(inf);
						collecte.setMedecin(med);
						collecte.setTypeCollecte(combo_type.getSelectedItem().toString());
						
						Poche poche = new Poche();
						if(combo_autot.getSelectedItem().toString().equalsIgnoreCase("oui")){
							poche.setAutotransfusion(true);
						}else{
							poche.setAutotransfusion(false);
						}
						poche.setDonneur(donneur);
						poche.setUtilise(false);
						poche.setCollectes(collecte);
						collecte.setPoche(poche);
						
						Collation collation = new Collation();
						collation.setCollecte(collecte);
						collecte.setCollation(collation);
						
						collecte.setStock(recupStock());
						
						//TODO formulaire
						Formulaire formulaire = new Formulaire();
						
						String pathFichierImg = txt_form.getText();
						FileInputStream fis = null;
						File fileImg = new File(pathFichierImg);
						try {
							fis=new FileInputStream(fileImg);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						byte[] bytes = new byte[(int)fileImg.length()];
						DataInputStream datais = new DataInputStream(fis);
						try {
							datais.readFully(bytes);
							formulaire.setFile(bytes);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						formulaire.setDonneur(donneur);
						
						if(flag){
							session.persist(poche);
							session.persist(collation);
							session.persist(collecte);
							session.persist(formulaire);
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
		txt_numnat.setText(null);
		combo_autot.setVisible(false);
		combo_autot.setSelectedItem(null);
		combo_autot.setVisible(true);
		combo_inf.setVisible(false);
		combo_inf.setSelectedItem(null);
		combo_inf.setVisible(true);
		combo_med.setVisible(false);
		combo_med.setSelectedItem(null);
		combo_med.setVisible(true);
		combo_type.setVisible(false);
		combo_type.setSelectedItem(null);
		combo_type.setVisible(true);
		txt_form.setText(null);
	}
	
	public boolean verification(){
		
		String verif = null;
		Matcher matcher;
		boolean flag = true;
		
		Pattern pat_numnat = Pattern.compile("^[0-9]{11}$");
		verif = txt_numnat.getText();
		if((!(matcher=pat_numnat.matcher(verif)).matches())&&flag==true){
			JOptionPane.showMessageDialog(null, "Le numéro national entré n'est pas correct");
			flag=false;
		}
		
		
		if(combo_type.getSelectedItem()==null && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le type de la collecte");
			flag=false;
		}
		
		if(combo_med.getSelectedItem()==null && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le médecin qui va s'occuper de la collecte");
			flag=false;
		}
		
		if(combo_inf.getSelectedItem()==null && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer l'infirmiere qui va s'occuper de la collecte");
			flag=false;
		}
		
		if(combo_autot.getSelectedItem()==null && flag==true){
			JOptionPane.showMessageDialog(null, "Entrer le choix pour l'autotransfusion");
			flag=false;
		}
		
		
		return flag;
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
				donneur.setIdDonneur(rs.getInt("idDonneur"));
				listeDonneur.add(donneur);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listeDonneur;
	}
	
	//récupération de la liste des donneurs
		public Stock recupStock(){
			Stock stock = null;
			String sql = "SELECT * FROM stock";
			
			ResultSet rs = null;
			
			try(Connection connection = DriverManager.getConnection(url, "root", "");
					Statement st = connection.createStatement()){
				rs = st.executeQuery(sql);
				while(rs.next()){
					stock = new Stock();
					stock.setIdSto(rs.getInt("ID_Sto"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return stock;
		}
}
