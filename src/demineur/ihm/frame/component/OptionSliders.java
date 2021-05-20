package demineur.ihm.frame.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import demineur.ihm.frame.FrameDebut;

public class OptionSliders 
{
	
	/** Génère le slider pour le volume (cf partie Option FrameDebut)
	 * 
	 * @return JSlider : le slider de volume
	 */
	public static JSlider getVolumeSlider()
	{
		JSlider volumeSlider = new JSlider(0,100);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Volume");
		titledBorder.setTitleColor(Color.WHITE);
		volumeSlider.setBorder(titledBorder);
		volumeSlider.setMajorTickSpacing(50);
		volumeSlider.setMinorTickSpacing(10);
		
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		volumeSlider.setBackground(FrameDebut.DARK3);
		volumeSlider.setForeground(Color.WHITE);
		
		return volumeSlider;
	}
	
	/** Génère le slider pour choisir la largeur de la grille (cf partie Option FrameDebut)
	 * 
	 * @return JSlider : le slider de largeur
	 */
	public static JSlider getLargeurSlider()
	{
	
		JSlider largeurSlider = new JSlider(3,15);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Largeur");
		titledBorder.setTitleColor(Color.WHITE);
		largeurSlider.setBorder(titledBorder);
		largeurSlider.setMajorTickSpacing(3);
		largeurSlider.setMinorTickSpacing(1);

		largeurSlider.setPaintTicks(true);
		largeurSlider.setPaintLabels(true);
		largeurSlider.setBackground(FrameDebut.DARK3);
		largeurSlider.setForeground(Color.WHITE);
		
		return largeurSlider;
	}
	
	/** Génère le slider pour choisir la hauteur de la grille (cf partie Option FrameDebut)
	 * 
	 * @return JSlider : le slider de hauteur
	 */
	public static JSlider getHauteurSlider()
	{
		JSlider longueurSlider = new JSlider(3,15);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Hauteur");
		longueurSlider.setMajorTickSpacing(3);
		longueurSlider.setMinorTickSpacing(1);
		titledBorder.setTitleColor(Color.WHITE);
		longueurSlider.setBorder(titledBorder);

		longueurSlider.setPaintTicks(true);
		longueurSlider.setPaintLabels(true);
		longueurSlider.setBackground(FrameDebut.DARK3);
		longueurSlider.setForeground(Color.WHITE);
		
		return longueurSlider;
	}
	
	/** Génère le slider pour choisir le temps maximum pour réaliser un niveau
	 * 
	 * @return JSlider :le slider de temps
	 */
	public static JSlider getTimeSlider()
	{
		JSlider longueurSlider = new JSlider(0,360);
		TitledBorder titledBorder = BorderFactory.createTitledBorder("Contrainte de Temps (0=Aucune)");
		longueurSlider.setMajorTickSpacing(60);
		longueurSlider.setMinorTickSpacing(10);
		titledBorder.setTitleColor(Color.WHITE);
		longueurSlider.setBorder(titledBorder);

		longueurSlider.setPaintTicks(true);
		longueurSlider.setPaintLabels(true);
		longueurSlider.setBackground(FrameDebut.DARK3);
		longueurSlider.setForeground(Color.WHITE);
		
		return longueurSlider;
	}
}
