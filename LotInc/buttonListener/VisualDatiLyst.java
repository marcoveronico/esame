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
import progr.utility;
import sensori.Sensore;
/**
 * 
 * classe che gestisce la visualizzazione delle rilevazioni in tempo reale dei sensori installati in un impianto
 *
 */
public class VisualDatiLyst extends Test implements ActionListener 
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
		JLabel lab2=new JLabel("inserire nome impianto da controllare");
		final JTextField lab1Field=new JTextField(10);
		JLabel lab3=new JLabel("inserire password impianto da controllare");
		final JTextField lab3Field=new JTextField(10);
		
		JButton sel=new JButton("avanti");
		class selLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				utility u=new utility();
				boolean flag=true;
				for(Impianto x:piat.ImpList)
				{
					try {
						if(x.getNomeIm().equalsIgnoreCase(lab1Field.getText())&&x.getPsw().equalsIgnoreCase(lab3Field.getText()))
						{
							flag=false;
							JFrame frame =new JFrame();
							frame.setSize(600,300);
							frame.setTitle("Impianto : "+x.getNomeIm());
							frame.setLocation(100, 100);
							JPanel panel=new JPanel();
							JLabel lab1=new JLabel("elenco sensori");
							JTextArea text=new JTextArea(2,30);
							for(Sensore s:x.contrSens())
							{
								text.append(u.calDataString(s.adattatore())+"\n");
							}
							panel.add(lab1);
							panel.add(text);
							frame.add(panel);
							frame.setVisible(true);
						  }
							
								
						} catch (HeadlessException e) {e.printStackTrace();}
						  catch (IOException e) {e.printStackTrace(); }
				}
				if(flag==true) JOptionPane.showMessageDialog(null, "impianto non trovato o password errata");
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
