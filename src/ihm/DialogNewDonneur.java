package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.ComboModelGrp;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogNewDonneur extends JDialog {

	private JPanel pnl_centre;
	private JPanel pnl_infodonneur;
	private JPanel pnl_adresse;
	private JPanel pnl_form;
	private JPanel buttonPane;
	
	private JTextField txt_nom;
	private JLabel lbl_nom;
	private JTextField txt_prenom;
	private JLabel lbl_prenom;
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
		lbl_date = new JLabel("Date de naissance : (dd/mm/yyyy) ");
		lbl_date.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_date,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		txt_date = new JTextField();
		txt_date.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_date,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		lbl_email = new JLabel("Email : ");
		lbl_email.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_email,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		txt_email = new JTextField();
		txt_email.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_email,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		lbl_tel = new JLabel("Tél. : ");
		lbl_tel.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_tel,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		txt_tel = new JTextField();
		txt_tel.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_tel,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		lbl_gsm = new JLabel("Gsm : ");
		lbl_gsm.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_gsm,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		txt_gsm = new JTextField();
		txt_gsm.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(txt_gsm,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		lbl_grp = new JLabel("Grp Sanguin : ");
		lbl_grp.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(lbl_grp,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		combo_grp = new JComboBox(model_grp);
		combo_grp.setPreferredSize(dim_txtfield);
		pnl_infodonneur.add(combo_grp,gbc);
		
		/*gbc.gridx = 0;
		gbc.gridy = 7;
		JLabel blanc = new JLabel();
		blanc.setPreferredSize(dim_lbl);
		pnl_infodonneur.add(blanc,gbc);*/
		
		
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
				// TODO Auto-generated method stub
				
			}
		});
		buttonPane.add(bt_ajout_donneur);
			
		//Add du bouton "Annuler"
		bt_annuler_fendonneur = new JButton("Annuler");
		bt_annuler_fendonneur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		buttonPane.add(bt_annuler_fendonneur);
			
		
	}

}
