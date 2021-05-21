package demineur.ihm.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
		this.setPreferredSize(new Dimension(550,250));
		frame = this;
		
		
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		String text;
		if(hasWin)
		{
			text = "Félicitations, vous avez gagné avec un score de : "+score+" en "+temps+"s";
		} else {
			text = "Dommage, vous ferez mieux la prochaine fois, votre temps : "+temps+"s";
		}
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,0));
		contentPane.setBackground(FrameDebut.DARK3);
		this.setContentPane(contentPane);
		
		JTextArea fin = new JTextArea(text);
		
		fin.setFont(FrameDebut.font);
		fin.setEditable(false);
		fin.setForeground(FrameDebut.WHITE);
		fin.setBackground(FrameDebut.DARK3);
		fin.setBorder(BorderFactory.createEmptyBorder());

		
		JPanel buttons = new JPanel();
		JButton retourMenu = new JButton("Menu");
		retourMenu.setFont(FrameDebut.font);
		JButton quitter = new JButton("Quitter");
		quitter.setFont(FrameDebut.font);
		
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
