package ihm;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.UIManager;

public class FramePrinc extends JFrame{
	
	private DialogNewCollecte dialogCollecte = null;
	private DialogNewDonneur dialogDonneur = null;
	
	private FrameTableAllDonneur table_donneur = null;
	
	private JButton bt_New_Donneur;
	private JButton bt_New_Collecte;
	private JButton bt_Affich_tableDonneur;
	
	public static void main(String[] args) {
		
		FramePrinc frame = new FramePrinc();
	}
	
	public FramePrinc() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int fermeture = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, 2);
				if( fermeture == 0){
					System.exit(0);
				}
			}
		});
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.setTitle("Don de sang");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(FramePrinc.class.getResource("/img/sang.png")));
		build();
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}
	
	public void fermetureFen(){
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int fermeture = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, 2);
				if( fermeture == 0){
					System.exit(0);
				}
			}
		});
	}
	
	public void build() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuEnregistrement = new JMenu("Enregistrement");
		menuBar.add(menuEnregistrement);
		
		JMenu menuNew = new JMenu("Nouveau");
		menuEnregistrement.add(menuNew);
		
		JMenuItem menuItDonneur = new JMenuItem("Donneur");
		menuItDonneur.setIcon(new ImageIcon(FramePrinc.class.getResource("/img/donneur_16x16.png")));
		menuItDonneur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dialogDonneur==null){
					openDialogDonneur();
				}else{
					dialogDonneur.setVisible(true);
				}
			}
		});
		menuNew.add(menuItDonneur);
		
		JMenuItem menuItCollecte = new JMenuItem("Collecte");
		menuItCollecte.setIcon(new ImageIcon(FramePrinc.class.getResource("/img/collecte_16x16.png")));
		menuItCollecte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dialogCollecte==null){
					openDialogCollecte();
				}else{
					dialogCollecte.setVisible(true);
				}
			}
		});
		menuNew.add(menuItCollecte);
		
		JSeparator separator = new JSeparator();
		menuEnregistrement.add(separator);
		
		JMenuItem menuItQuitter = new JMenuItem("Quitter");
		menuItQuitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int fermeture = JOptionPane.showConfirmDialog(null, "Etes-vous sur de vouloir quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, 2);
				if( fermeture == 0){
					System.exit(0);
				}
			}
		});
		menuEnregistrement.add(menuItQuitter);
		
		JMenu menuPersonnel = new JMenu("Personnel");
		menuBar.add(menuPersonnel);
		
		JMenuItem menuMedecin = new JMenuItem("Médecin");
		menuMedecin.setIcon(new ImageIcon(FramePrinc.class.getResource("/img/docteur_16x16.png")));
		menuMedecin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO bouton medecin
				
			}
		});
		menuPersonnel.add(menuMedecin);
		
		JMenuItem menuInfirmiere = new JMenuItem("Infirmière");
		menuInfirmiere.setIcon(new ImageIcon(FramePrinc.class.getResource("/img/infirmiere_16x16.png")));
		menuInfirmiere.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO bouton infirmiere
				
			}
		});
		menuPersonnel.add(menuInfirmiere);
		
		
		JMenu menuHelp = new JMenu("Aide");
		menuBar.add(menuHelp);
		
		JMenuItem menuItAPropos = new JMenuItem("A propos...");
		menuItAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Affichage de l'aide
			}
		});
		menuHelp.add(menuItAPropos);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		bt_New_Donneur = new JButton("Nouveau donneur");
		bt_New_Donneur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dialogDonneur==null){
					openDialogDonneur();
				}else{
					dialogDonneur.setVisible(true);
				}
			}
		});
		bt_New_Donneur.setIcon(new ImageIcon(FramePrinc.class.getResource("/img/donneur_32x32.png")));
		toolBar.add(bt_New_Donneur);
		
		bt_New_Collecte = new JButton("Nouvelle collecte");
		bt_New_Collecte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(dialogCollecte==null){
					openDialogCollecte();
				}else{
					dialogCollecte.setVisible(true);
				}
			}
		});
		bt_New_Collecte.setIcon(new ImageIcon(FramePrinc.class.getResource("/img/collecte_32x32.png")));
		toolBar.add(bt_New_Collecte);
		
		Separator tb_separator = new Separator();
		toolBar.add(tb_separator);
		
		bt_Affich_tableDonneur = new JButton("Liste des donneurs");
		bt_Affich_tableDonneur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(table_donneur ==null){
					addTableDonneur();
				}else{
					table_donneur.setVisible(true);
				}
				
			}
		});
		bt_Affich_tableDonneur.setIcon(new ImageIcon(FramePrinc.class.getResource("/img/table_32x32.png")));
		toolBar.add(bt_Affich_tableDonneur);
		
	}
	
	public void openDialogCollecte(){
		dialogCollecte = new DialogNewCollecte();
		dialogCollecte.setVisible(true);
	}
	
	public void openDialogDonneur(){
		dialogDonneur = new DialogNewDonneur();
		dialogDonneur.setVisible(true);
	}
	
	public void addTableDonneur(){
		table_donneur = new FrameTableAllDonneur();
		table_donneur.setVisible(true);
	}
}
