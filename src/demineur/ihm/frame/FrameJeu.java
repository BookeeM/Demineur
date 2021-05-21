package demineur.ihm.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import demineur.ihm.Bouton;
import demineur.ihm.Difficulte;
import demineur.ihm.frame.component.Settings;
import demineur.metier.Demineur;
import demineur.metier.EtatCase;

@SuppressWarnings("serial")
public class FrameJeu extends JFrame implements MouseListener
{
	
	private int largeur;
	private int hauteur;
	
	private Difficulte dif;
	
	private JPanel contentPane;
	
	private JPanel gameGrid;
	
	private int timerConstraint;
	
	private JPanel infoTab;
	
	private JLabel infoNbMines;
	private JLabel infoNbCoups;
	
	private JPanel cheatPanel;
	
	private Long timeAtStart;
	
	private Timer timer;
	
	private JLabel timerLabel;	
	
	private int scoreMultiplier;
	
	private boolean isCheating;
	
	private Bouton[] bouton;
	
	private Demineur demineur;
	
	private int nbCoups;
	
	private ArrayList<Bouton> listeMines;
	
	
	/** Constructeur de la fenêtre de jeu
	 * 
	 * @param dif : difficultée choisie au préalable par l'utilisateur.
	 * @param largeur : largeur choisie au préalable par l'utilisateur.
	 * @param hauteur : hauteur choisie au préalable par l'utilisateur.
	 * 
	 * @author Emilien G
	 */
	public FrameJeu(Difficulte dif, int largeur, int hauteur, int timerConstraint)
	{
		super("Démineur - Jeu");
		
		
		this.scoreMultiplier = 1;
		
		this.isCheating = false;
		
		this.timerConstraint = timerConstraint;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.nbCoups = 0;
		
		this.setResizable(false);
		
		this.dif = dif;
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		this.demineur = new Demineur(largeur, hauteur, dif.getLvl());
		this.bouton = new Bouton[largeur*hauteur];
		
		this.contentPane = new JPanel();
		this.contentPane.setLayout(new BorderLayout(0,0));
		this.setContentPane(contentPane);
		
		
		initInfo();
		initGrid();
		initCheat();
		updateListeMine();
		
		//contentPane.add();
		this.pack();
	}
	
	
	/** Crée la partie supérieure là où seront situées les informations de jeu
	 *  
	 */
	public void initInfo()
	{
		infoTab = new JPanel();
		infoTab.setLayout(new GridLayout(1,4));
		infoTab.setPreferredSize(new Dimension(largeur*60,50));
		
		infoTab.setBackground(FrameDebut.DARK3);
		
		JLabel infoNbMineDepart = new JLabel("Mines au départ : "+this.demineur.getNbMinesATrouver(),JLabel.CENTER);
		infoNbMines = new JLabel("Nombre actuel de mines : "+(this.demineur.getNbMinesATrouver()-this.demineur.getNbMinesProposees()),JLabel.CENTER);
		infoNbCoups = new JLabel("Nombre de Coups Joués : "+0,JLabel.CENTER);
		timerLabel = new JLabel("Timer : "+0+"s",JLabel.CENTER);
		timeAtStart = System.currentTimeMillis();
		
		infoNbMineDepart.setForeground(FrameDebut.WHITE);
		infoNbMines.setForeground(FrameDebut.WHITE);
		infoNbCoups.setForeground(FrameDebut.WHITE);
		timerLabel.setForeground(FrameDebut.WHITE);
		
		timer = new Timer(100,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String show = ((System.currentTimeMillis()-timeAtStart)/1000L)+" / "+timerConstraint;
				//show = show.substring(0, 3); //Pour pas avoir 300000 de numéros parasites
				timerLabel.setText("Timer : "+show+"s");
				
				float t = (System.currentTimeMillis()-timeAtStart)/1000L;
				
				if(t>timerConstraint && timerConstraint>0 && !demineur.isPartieTerminee())
				{
					demineur.forcePerdre();
					fin(false);
				}
			}
		});
		timer.start();
		
		infoTab.add(infoNbMineDepart);
		infoTab.add(infoNbMines);
		infoTab.add(infoNbCoups);
		infoTab.add(timerLabel);
		
		this.contentPane.add(infoTab,BorderLayout.NORTH);
	}
	
	/**
	 * Crée les différents boutons qui seront positionnés sur la grille et génère la grille
	 */
	
	public void initGrid()
	{
		this.gameGrid = new JPanel();
		this.gameGrid.setLayout(new GridLayout(hauteur,largeur));
		
		for(int i=0;i<largeur*hauteur;i++)
		{
			JButton b = new JButton();
			b.addMouseListener(this);
			switch(largeur)
			{
				case 3:
					b.setPreferredSize(new Dimension(220,60));
					break;
				case 4:
					b.setPreferredSize(new Dimension(165,60));
					break;
				case 5:
					b.setPreferredSize(new Dimension(132,60));
					break;
				case 6:
					b.setPreferredSize(new Dimension(110,60));
					break;
				case 7:
					b.setPreferredSize(new Dimension(94,60));
					break;
				case 8:
					b.setPreferredSize(new Dimension(82,60));
					break;
				case 9:
					b.setPreferredSize(new Dimension(73,60));
					break;
				case 10:
					b.setPreferredSize(new Dimension(66,60));
					break;
				default:
					b.setPreferredSize(new Dimension(60,60));
					break;
			}
			
				
			Bouton but = new Bouton(b,i);
			bouton[i] = but;
			gameGrid.add(but.getBut());
		}
		this.contentPane.add(gameGrid,BorderLayout.CENTER);
		update();
	}
	
	public void initCheat()
	{
		cheatPanel = new JPanel();
		
		cheatPanel.setBackground(FrameDebut.DARK3);
		
		cheatPanel.setPreferredSize(new Dimension(0,60));
		
		JButton cheater = new JButton();
		JLabel t = new JLabel("   Tricher");
		cheater.add(t);
		cheater.setBackground(FrameDebut.DARK4);
		t.setForeground(FrameDebut.WHITE);
		cheater.setPreferredSize(new Dimension(150,50));
		cheater.setPreferredSize(new Dimension(150,50));
		t.setFont(FrameDebut.font);
		cheater.setBorder(BorderFactory.createEtchedBorder());
		
		cheatPanel.add(cheater);
		
		cheater.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isCheating = !isCheating;
				update();
			}
		});
		
		this.contentPane.add(cheatPanel,BorderLayout.SOUTH);
	}
	
	
	/** Mets à jour les cases et les informations de jeu
	 * 
	 */
	public void update()
	{
		for(int i=0;i<largeur*hauteur;i++)
		{
			this.bouton[i].update(this.demineur.getEtat(i), this.demineur.getValeur(i));
		}
		
		if(this.isCheating)
		{
			for(Bouton mine : listeMines)
			{
				mine.getBut().setBackground(Color.RED);
			}
			scoreMultiplier = 0;
		}
		
		infoNbMines.setText("Nombre actuel de mines : "+(this.demineur.getNbMinesATrouver()-this.demineur.getNbMinesProposees()));
		infoNbCoups.setText("Nombre de Coups Joués : "+nbCoups);
	}

	
	/** Permet de retrouver un objet Bouton depuis un JButton
	 * 
	 * @param but JButton
	 * @return bouton
	 */
	public Bouton getBoutonFromButton(JButton but)
	{
		for(int i=0;i<hauteur*largeur;i++)
		{
			if(but == this.bouton[i].getBut())
			{
				return this.bouton[i];
			}
		}
		return null;
	}
	
	/** Permet de finir une partie
	 * 
	 * 
	 * @param boolean : true -> Gagné, false -> Perdu;
	 **/
	public void fin(boolean hasWin)
	{
		timer.stop();
		for(Bouton mine : listeMines)
		{
			if(hasWin) {
				mine.getBut().setBackground(Color.GREEN);
			} else {
				mine.getBut().setBackground(Color.RED);
			}	
		}
		
		int score = 0;
		if(hasWin)
		{
			playSound("win");
			score = (int) ((this.listeMines.size()*10000-(nbCoups*((System.currentTimeMillis()-timeAtStart)/1000L))+(360-timerConstraint)*20)*scoreMultiplier);
		} else {
			playSound("loose");
		}

		FrameFin fin = new FrameFin(hasWin,score,(int) ((System.currentTimeMillis()-timeAtStart)/1000L),this);
		fin.setVisible(true);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(!this.demineur.isPartieTerminee())
		{
			if(e.getSource() instanceof JButton)
			{
				JButton button = (JButton) e.getSource();
				Bouton but = getBoutonFromButton(button);
				EtatCase etat = demineur.getEtat(but.getId());
				
				if(e.getButton() == 1)
				{
					playSound("click1");
					if(!this.demineur.retourner(but.getId()))
					{
						if(this.nbCoups<=0) {
							while(!this.demineur.retourner(but.getId()))
							{
								this.demineur = new Demineur(largeur, hauteur, dif.getLvl());
							}
							updateListeMine();
						}
					}
					if(etat == EtatCase.FERME)
						nbCoups++;
				} else if(e.getButton() == 3) {
					playSound("flag1");
					this.demineur.marquer(but.getId());
				}
				update();
			
				if(this.demineur.isPartiePerdue() && this.demineur.isPartieTerminee())
					fin(false);
				if(!this.demineur.isPartiePerdue() && this.demineur.isPartieTerminee())
					fin(true);
			}
		}
	}
	
	/** Permet d'updater la liste des mines dans le cas où on relance une partie
	 *  cette méthode permet d'à la fin de la partie, afficher où étaient placées
	 *  les mines.
	 */
	public void updateListeMine()
	{
		this.listeMines = new ArrayList<Bouton>();
		for(int i=0;i<largeur*hauteur;i++)
		{
			if(this.demineur.getValeur(i) == -1)
			{
				this.listeMines.add(this.bouton[i]);
			}
		}
	}
	
	/** Permet de jouer le son du nom de fichier passé en paramètre
	 * 
	 * 
	 * @param fileName : nom du fichier à jouer
	 */
	public void playSound(String fileName) 
	{	
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(FrameJeu.class.getResourceAsStream("/demineur/assets/sounds/"+fileName+".wav"));
			Clip clip = AudioSystem.getClip();    
		    clip.open(audioIn);
		    
		    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
		    gainControl.setValue(20f * (float) Math.log10(Settings.getVolumeOption()/100f));
		    
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}