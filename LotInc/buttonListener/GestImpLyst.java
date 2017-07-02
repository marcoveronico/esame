package buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 *gestisce l'interfaccia grafica della piattaforma inerente la gestione degli impianti
 */
public class GestImpLyst implements ActionListener 
{
	public void actionPerformed(ActionEvent arg0)
	{
		final JFrame frame =new JFrame();
		frame.setSize(400,400);
		frame.setTitle("Piattaforma IOT Inc.");
		frame.setLocation(100, 100);
		JPanel panel=new JPanel();
		JButton ins=new JButton("Inserisci impianto");
		JButton gest=new JButton("Inserisci sensori");
		JButton elim=new JButton("Elimina  impianto");
		ins.addActionListener(new InsImpiLyst());
		gest.addActionListener(new gestImLyst());
		elim.addActionListener(new ElimImpLyst());
		JButton back=new JButton("menù precedente");
		class backLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
			}
		}	
		back.addActionListener(new backLyst());
		panel.add(ins);
		panel.add(gest);
		panel.add(elim);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}

}

