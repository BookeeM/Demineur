package demineur.ihm.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameFin extends JFrame
{
	
	private final JFrame frame;
	
	public FrameFin(boolean hasWin,int score, int temps, final FrameJeu frameJeu)
	{
		super("Démineur - Fin de Partie");
		
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
		this.setContentPane(contentPane);
		
		JLabel fin = new JLabel(text);
		
		JPanel buttons = new JPanel();
		JButton retourMenu = new JButton("Menu");
		JButton quitter = new JButton("Quitter");
		
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
