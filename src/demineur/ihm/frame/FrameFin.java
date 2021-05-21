package demineur.ihm.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FrameFin extends JFrame
{
	
	private final JFrame frame;
	
	/** Permet de générer une frame de fin 
	 * 
	 * @param hasWin : si la personne a gagné
	 * @param score : le score de la personne 
	 * @param temps : le temps mis avant la fin 
	 * @param frameJeu : la frame de jeu pour la fermer par la suite
	 */
	public FrameFin(boolean hasWin,int score, int temps, final FrameJeu frameJeu)
	{
		super("Démineur - Fin de Partie");
		this.setResizable(false);
		this.setPreferredSize(new Dimension(450,180));
		frame = this;
		
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		JLabel fin;
		if(hasWin)
		{
			fin = new JLabel("<html><div style='text-align: center;'>"+"Félicitations,vous avez gagné <br/>"+"avec un score de "+score+" en "+temps+"s"+"</div></html>");
		} else {
			fin = new JLabel("<html><div style='text-align: center;'>"+"Dommage,vous avez perdu <br/>"+"avec un score de "+score+" en "+temps+"s"+"</div></html>");
		}
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,0));
		contentPane.setBackground(FrameDebut.DARK3);
		this.setContentPane(contentPane);
		
		
		fin.setHorizontalAlignment(JLabel.CENTER);
		fin.setSize(50, 50);
		
		fin.setFont(FrameDebut.font);
		fin.setForeground(Color.WHITE);
		fin.setBackground(FrameDebut.DARK3);
		fin.setBorder(BorderFactory.createEmptyBorder());
		
		JPanel buttons = new JPanel();
		buttons.setBackground(FrameDebut.DARK5);
		
		JButton retourMenu = new JButton();
		JLabel r = new JLabel("     Menu",JLabel.CENTER);
		retourMenu.add(r);
		retourMenu.setBackground(FrameDebut.DARK3);
		r.setForeground(FrameDebut.WHITE);
		retourMenu.setPreferredSize(new Dimension(150,50));
		r.setFont(FrameDebut.font);
		retourMenu.setBorder(BorderFactory.createEtchedBorder());
		
		JButton quitter = new JButton("");
		JLabel q = new JLabel("   Quitter",JLabel.CENTER);
		quitter.add(q);
		quitter.setBackground(FrameDebut.DARK3);
		q.setForeground(FrameDebut.WHITE);
		q.setPreferredSize(new Dimension(150,50));
		quitter.setPreferredSize(new Dimension(150,50));
		q.setFont(FrameDebut.font);
		quitter.setBorder(BorderFactory.createEtchedBorder());
		
		retourMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				FrameDebut deb = new FrameDebut();
				frame.dispose();
				frameJeu.dispose();
				deb.setVisible(true);	
				
			}
		});
		
		quitter.addActionListener(new ActionListener() { @Override public void actionPerformed(ActionEvent e) {System.exit(0);}});
		
		buttons.add(retourMenu);
		buttons.add(quitter);
		
		contentPane.add(fin,BorderLayout.NORTH);
		contentPane.add(buttons,BorderLayout.SOUTH);
		
		this.pack();
	}
}
