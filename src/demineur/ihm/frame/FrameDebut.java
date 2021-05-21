package demineur.ihm.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import demineur.ihm.Difficulte;
import demineur.utils.OptionSliders;
import demineur.utils.Score;
import demineur.utils.Settings;

@SuppressWarnings("serial")
public class FrameDebut extends JFrame implements MouseListener
{
	
	public static final Color DARK6 = new Color(24, 24, 64);
	public static final Color DARK5 = new Color(44, 44, 84);
	public static final Color DARK4 = new Color(64, 64, 122);
	public static final Color DARK3 = new Color(71, 71, 135);
	public static final Color DARK3B = new Color(91,91,155);
	public static final Color DARK2 = new Color(142, 141, 251);
	public static final Color DARK1 = new Color(34, 112, 147);
	public static final Color WHITE = new Color(190,190,190);
	
	private boolean isInOption;
	private boolean isInBestScore;

	private JSlider volume;
	private JSlider longueur;
	private JSlider hauteur;
	private JSlider time;
	
	private CardLayout card;
	
	private Icon iconOptionHover = new ImageIcon(FrameDebut.class.getResource("/demineur/assets/img/options2.png"));
	private Icon iconOption = new ImageIcon(FrameDebut.class.getResource("/demineur/assets/img/options.png"));
	
	private Icon iconBestScoreHover = new ImageIcon(FrameDebut.class.getResource("/demineur/assets/img/bestscore2.png"));
	private Icon iconBestScore = new ImageIcon(FrameDebut.class.getResource("/demineur/assets/img/bestscore.png"));
	
	private JLabel option;
	
	private JPanel centerMenu;
	private JPanel menuOption;
	private JPanel menuContent;
	private JPanel menuScore;
	
	private JList<String> scoreList;
	private DefaultListModel<String> scoreModel;
	
	private JLabel bestScore;
	
	private DefaultListModel<String> listModel ;
	private JList<String>            liste ;
	private JPanel contentPane;
	
	public static Font font;
	public static Font fontSmaller;
	
	
	/** Constructeur permettant la création des différentes fenêtre du menu principal
	 * 
	 * @author Emilien G
	 */
	public FrameDebut()
	{
		super("Démineur - Menu");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Initialisation + création du fichier de paramètre basique.
		try {
			Settings.saveOption(Settings.getVolumeOption(),Settings.getLargeurOption(),Settings.getHauteurOption(),0);
		} catch(Exception e) {
			Settings.saveOption(20, 11, 11,0);
		}
		this.isInOption = false;
		this.isInBestScore = false;
		
		FrameDebut.font = new Font(Font.SANS_SERIF, 15, 100);
		FrameDebut.font = FrameDebut.font.deriveFont(30f);
		
		FrameDebut.fontSmaller = new Font(Font.SANS_SERIF, 15, 100);
		FrameDebut.fontSmaller = FrameDebut.font.deriveFont(25f);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,0));
		this.setContentPane(contentPane);

		this.setPreferredSize(new Dimension(295,400));
		
		initNorth();
		initCenter();
		initWest();
		
		this.pack();
	}
	
	/** Permet d'initialiser le panneau du centre avec le titre, les difficultés et le bouton pour commencer
	 * 
	 */
	public void initCenter()
	{
		/*Initialisation du panel principal*/
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout(0,0));
		center.setBackground(DARK4);
		
		this.centerMenu = new JPanel();
		card = new CardLayout();
		this.centerMenu.setLayout(card);
		
		/*Création du menu central, choix des difficultés*/
		menuContent = new JPanel();
		menuContent.setLayout(new BorderLayout(0,0));
		
		this.listModel = new DefaultListModel<String>();
		ArrayList<String> difs = new ArrayList<String>(Arrays.asList(Difficulte.DEBUTANT.getLabel(),Difficulte.AMATEUR.getLabel(),Difficulte.MOYEN.getLabel(),Difficulte.HABILE.getLabel(),Difficulte.EXPERT.getLabel()));
		for(String dif : difs ) {
			this.listModel.addElement(dif);
		}
		this.liste     = new JList<String>(listModel);
		this.liste.setSelectedIndex(0);
		this.liste.setForeground(Color.WHITE);
		//this.liste.setBackground( Color.CYAN );
		this.liste.setFont(FrameDebut.font);
		this.liste.setBackground(DARK3);
	
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)liste.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		
		menuContent.add(liste,BorderLayout.CENTER);
		
		/*Définition du titre de la section choix du niveau*/
		JTextField title = new JTextField("Choix du Niveau");
	
		title.setEditable(false);
		title.setForeground(WHITE);
		title.setBackground(DARK3);
		title.setBorder(BorderFactory.createEmptyBorder());
		title.setFont(FrameDebut.font);
		menuContent.add(title,BorderLayout.NORTH);
		
		/*Positionnement du bouton pour lancer la partie*/
		
		JButton jouer = new JButton("Jouer !");
		jouer.setBackground(DARK3B);
		jouer.setFont(FrameDebut.font);
		jouer.setForeground(Color.WHITE);
		jouer.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		jouer.addMouseListener(this);
		menuContent.add(jouer,BorderLayout.SOUTH);
		centerMenu.add("1",menuContent);
		
		//Placement des différentes options et de leurssliders.
		menuOption = new JPanel();
		menuOption.setLayout(new GridLayout(5,1));
		menuOption.setBackground(DARK3);
		JLabel optionTitle = new JLabel("Options",JLabel.CENTER);
		optionTitle.setForeground(WHITE);
		optionTitle.setBackground(DARK3);
		optionTitle.setBorder(BorderFactory.createEmptyBorder());
		optionTitle.setFont(FrameDebut.font);
		menuOption.add(optionTitle);
		
		volume = OptionSliders.getVolumeSlider();
		longueur = OptionSliders.getHauteurSlider();
		hauteur = OptionSliders.getLargeurSlider();
		time = OptionSliders.getTimeSlider();
		
		try {
			this.volume.setValue(Settings.getVolumeOption());
			this.longueur.setValue(Settings.getLargeurOption());
			this.hauteur.setValue(Settings.getHauteurOption());
			this.time.setValue(Settings.getTimeConstraint());
			
		} catch(Exception e) {
			Settings.saveOption(20, 11, 11, 0);
		}

		menuOption.add(volume);
		menuOption.add(longueur);
		menuOption.add(hauteur);
		menuOption.add(time);
			
		centerMenu.add("2",menuOption);
		centerMenu.setBackground(DARK3);
		
		/*Création du menu Best Score*/
		
		this.menuScore = new JPanel();
		this.menuScore.setLayout(new BorderLayout(0,0));
		
		JLabel bestTitle = new JLabel("Meilleurs Scores",JLabel.CENTER);
		menuScore.add(bestTitle,BorderLayout.NORTH);
		bestTitle.setFont(fontSmaller);
		bestTitle.setForeground(WHITE);
		
	    JScrollPane scrollPane = new JScrollPane(menuScore,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setPreferredSize(new Dimension(200, 390));
		
		final YamlFile save = new YamlFile("scores.yaml");

		try {
            if (!save.exists()) {
                save.createNewFile(true);
            }
			save.load();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.scoreModel = new DefaultListModel<String>();
		
		this.scoreList     = new JList<String>(scoreModel);
		this.scoreList.setPreferredSize(new Dimension(200,390));
		this.scoreList.setSelectedIndex(0);
		this.scoreList.setForeground(Color.WHITE);
		//this.liste.setBackground( Color.CYAN );
		this.scoreList.setFont(FrameDebut.font);
		this.scoreList.setBackground(DARK3);

		menuScore.add(scoreList,BorderLayout.CENTER);
		
		centerMenu.add("3",scrollPane);
		menuScore.setBackground(DARK3);
		
		
		
		
		center.add(centerMenu,BorderLayout.WEST);
		contentPane.add(center,BorderLayout.CENTER);
	}
	
	public void updateScores()
	{
		this.scoreModel.clear();
		ArrayList<Score> scores = Settings.getScores();
		
		for(int i=0;i<scores.size();i++) {
			this.scoreModel.addElement(scores.get(i).toString());
		}
		
	}
	
	
	/** Initialisation du pannel gauche qui contient l'icone pour les paramètres.
	 * 
	 */
	public void initWest()
	{
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(3,1));
		/*Placement du logo des paramètres*/
		
		JPanel leftSide = new JPanel();
		leftSide.setBackground(DARK5);
		leftSide.setLayout(new GridLayout(3,1));
		
        option = new JLabel("",JLabel.CENTER);
		option.addMouseListener(this);
		option.setIcon(this.iconOption);
		
		leftSide.add(option);
        //----------------------
        /*Placement du logo meilleur score*/

		JPanel best = new JPanel();
		best.setBackground(DARK5);

        bestScore = new JLabel("",JLabel.CENTER);
        bestScore.addMouseListener(this);
        bestScore.setIcon(this.iconBestScore);
        
        bestScore.setVerticalAlignment(JLabel.CENTER);
        
   
        
        best.add(bestScore);
        //-----------------

		west.setBackground(DARK5);
		west.setPreferredSize(new Dimension(60,600));
		
		west.add(leftSide,BorderLayout.NORTH);
		west.add(best,BorderLayout.CENTER);
		
		contentPane.add(west,BorderLayout.WEST);
	}
	
	/*Initialisation du panel nord qui affiche le titre*/
	public void initNorth()
	{
		JPanel nord = new JPanel();
		nord.setBackground(DARK6);
		nord.setPreferredSize(new Dimension(600,60));
		JLabel dem = new JLabel("Jeu du Démineur",JLabel.CENTER);
		dem.setFont(font);
		dem.setForeground(Color.WHITE);
		nord.add(dem);
		contentPane.add(nord,BorderLayout.NORTH);
	}
	
	/** Permet de passer dans les options / retourner sur la vue menu
	 * Sauvegarde lors d'un passage des options -> menu
	 * Charge lors d'un passage menu -> option
	 */
	public void option()
	{
		
		if(this.isInBestScore) {
			card.next(this.centerMenu);
			this.isInBestScore = false;
		}
		
		if(this.isInOption)
		{
			Settings.saveOption(this.volume.getValue(), this.longueur.getValue(), this.hauteur.getValue(),this.time.getValue());
			card.first(this.centerMenu);
		} else {
			card.next(this.centerMenu);
			try {
				this.volume.setValue(Settings.getVolumeOption());
				this.longueur.setValue(Settings.getLargeurOption());
				this.hauteur.setValue(Settings.getHauteurOption());
				this.time.setValue(Settings.getTimeConstraint());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		this.isInOption = !this.isInOption;
	}
	
	public void bestScore()
	{
		updateScores();
		if(this.isInOption) {
			card.last(this.centerMenu);
			this.isInOption = false;
		}
		
		if(this.isInBestScore)
		{
			card.first(this.centerMenu);
		} else {
			card.last(this.centerMenu);
		}
		this.isInBestScore = !this.isInBestScore;
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(e.getSource() instanceof JLabel) {
			if(e.getSource() == bestScore)
				bestScore();
			if(e.getSource() == option)
				option();
		} else if(e.getSource() instanceof JButton) {
			System.out.println(liste);
			FrameJeu fj = new FrameJeu((Difficulte) Difficulte.fromString((String)liste.getSelectedValue()),Settings.getHauteurOption(),Settings.getLargeurOption(),Settings.getTimeConstraint());
			fj.setVisible(true);
			dispose();
		}	
	}
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(e.getSource() == option)
			option.setIcon(iconOption);
		if(e.getSource() == bestScore)
			bestScore.setIcon(iconBestScore);
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if(e.getSource() == option)
			option.setIcon(iconOptionHover);
		if(e.getSource() == bestScore)
			bestScore.setIcon(iconBestScoreHover);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
