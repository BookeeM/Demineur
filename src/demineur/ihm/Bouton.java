package demineur.ihm;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import demineur.metier.EtatCase;

public class Bouton 
{
	private int id;
	private JButton button;
	
	
	/** Constructeur permettant la création d'un objet bouton 
	 * 
	 * @param button : JButton associé au bouton
	 * @param id : id (place dans le tableau de boutons) du bouton.
	 */
	public Bouton(JButton button, int id)
	{
		this.id = id;
		this.button = button;
	}
	
	/** Retourne l'id d'un bouton, donc la position dans le tableau de boutons de la classe FrameJeu
	 * 
	 * @return id : int
	 */
	public int getId()
	{
		return this.id;
	}
	
	/** Permet de récupérer le composant JButton associé à l'objet Bouton
	 * 
	 * @return bouton : Bouton
	 */
	public JButton getBut()
	{
		return this.button;
	}
	
	/** Permet d'updater un bouton en fonction de son état dans le jeu.
	 * 
	 * @param etat : état du bouton
	 * @param valeurCase : valeur de la case
	 */
	public void update(EtatCase etat, int valeurCase)
	{
		this.button.setFont(Main.font);
		switch(etat)
		{
			case FERME:
				this.button.setText("F");
				this.button.setBackground(new Color(99, 110, 114));
				break;
			case DOUTE:
				this.button.setText("?");
				this.button.setBackground(new Color(108, 92, 231));
				break;
			case MINE:
				this.button.setText("X");
				this.button.setBackground(new Color(162, 155, 254));
				break;	
			case OUVERT:	
				this.button.setForeground(new Color(99, 110, 114));
				if(valeurCase>0) 
				{
					this.button.setBackground(new Color(188, 200, 215));
					this.button.setForeground(getColorFromInt(valeurCase));
					this.button.setText(""+valeurCase);
				}
				else {
					this.button.setBackground(new Color(178, 190, 195));
					this.button.setText(" ");
					this.button.setBorder(BorderFactory.createLoweredBevelBorder());
				}
					
				break;
		}
	}
	
	
	public Color getColorFromInt(int lvl)
	{
		switch(lvl)
		{
			case 1:
				return new Color(0, 152, 112);
			case 2:
				return new Color(0, 143, 144);
			case 3:
				return new Color(64, 140, 234);
			case 4:
				return new Color(75, 29, 185);
			default:
				return new Color(32, 11, 131);
		}
	}
}
