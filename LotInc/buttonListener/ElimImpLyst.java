package buttonListener;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import progr.Impianto;
import progr.Test;

/**
 * 
 * classe che gestisce  l'eliminazione di un impianto dalla piattaforma
 *
 */
public class ElimImpLyst extends Test implements ActionListener 
{
	public void actionPerformed(ActionEvent arg0)
	{
		final JFrame frame =new JFrame();
		frame.setSize(400,400);
		frame.setTitle("Piattaforma IOT Inc.");
		frame.setLocation(100, 100);
		JPanel panel=new JPanel();
		JLabel lab1=new JLabel("lista Impianti");
		JTextArea text=new JTextArea(2,20);
		for(Impianto x:piat.ImpList)
		 text.append(x.getNomeIm()+"\n");
		JLabel lab2=new JLabel("inserire nome impianto da eliminare");
		final JTextField lab1Field=new JTextField(10);
		JLabel lab3=new JLabel("inserire password impianto");
		final JTextField lab3Field=new JTextField(10);
		JButton sel=new JButton("avanti");
		class selLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean y=false;
				int i=0;
				
				for(Impianto x:piat.ImpList)
				{
				 try {
						if(x.getNomeIm().equalsIgnoreCase(lab1Field.getText())&& x.getPsw().equalsIgnoreCase(lab3Field.getText()))
						{
							piat.ImpList.remove(i);
							JOptionPane.showMessageDialog(null, "impinato eliminato");
							frame.dispose();
							y=true;
							break;
						}
					  } catch (HeadlessException e) {e.printStackTrace(); } 
					    catch (IOException e) {e.printStackTrace(); }
				  i++;
				}
				if(y!=true) JOptionPane.showMessageDialog(null, "impianto non trovato o password errata");
			}
		}
		sel.addActionListener(new selLyst());
		JButton back=new JButton("menù precedente");
		class backLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
			}
		}	
		back.addActionListener(new backLyst());
		panel.add(lab1);
		panel.add(text);
		panel.add(lab2);
		panel.add(lab1Field);
		panel.add(lab3);
		panel.add(lab3Field);
		panel.add(sel);
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}

}
