package buttonListener;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import progr.Impianto;

import sensori.Sensore;

public class ElimSensLyst extends Test implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final JFrame frame =new JFrame();
		frame.setSize(400,400);
		frame.setTitle("Piattaforma IOT Inc.");
		frame.setLocation(100, 100);
		final JPanel panel=new JPanel();
		JLabel lab1=new JLabel("lista Impianti");
		JTextArea text=new JTextArea(2,20);
		for(Impianto x:piat.ImpList)
		 text.append(x.getNomeIm()+"\n");
		JLabel lab2=new JLabel("inserire nome impianto da gestire");
		final JTextField lab1Field=new JTextField(15);
		JLabel lab3=new JLabel("inserire password impianto");
		final JPasswordField psw=new JPasswordField(15);
		JButton sel=new JButton("avanti");
		
		 
		class selLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{  String[] data =new String[50];
				boolean y=false;
				int i=0;
				
				for(Impianto x:piat.ImpList)
				{
				 try {
						if(x.getNomeIm().equalsIgnoreCase(lab1Field.getText())&& x.getPsw().equalsIgnoreCase(String.copyValueOf(psw.getPassword())))
						{
							
							y=true;
							ArrayList<Sensore> sen=x.contrSens();
							for(Sensore s:sen)
							{
								data[i]=(s.getID());
								i++;
							
							}
							
							
						}
					  } catch (HeadlessException e) {e.printStackTrace(); } 
					    catch (IOException e) {e.printStackTrace(); }
				  
				}
				final JList<String> j = new JList<String>(data);
				final JFrame frame =new JFrame();
				frame.setSize(400,400);
				frame.setTitle("elenco Sensori IOT Inc.");
				frame.setLocation(100, 100);
				final JPanel panel=new JPanel();
				JLabel lab1=new JLabel("lista sensori");
				JLabel lab2=new JLabel("selezionare sensore da eliminare");
				JButton back=new JButton("menù precedente");
				JButton elimina=new JButton("conferma eliminazione");
				class backLyst implements ActionListener 
				{
					public void actionPerformed(ActionEvent arg0)
					{
						frame.dispose();
					}
				}	
				class elimLyst implements ActionListener 
				{
					public void actionPerformed(ActionEvent arg0)
					{
					for(Impianto x:piat.ImpList)
					{
					 try {
							if(x.getNomeIm().equalsIgnoreCase(lab1Field.getText())&& x.getPsw().equalsIgnoreCase(String.copyValueOf(psw.getPassword())))
							{
								
								int i=0;
								ArrayList<Sensore> sen=x.contrSens();
								for(Sensore s:sen)
								{
									if(s.getID().equals(j.getSelectedValue()))
										{
										x.elimSens(i);
										JOptionPane.showMessageDialog(null, "sensore eliminato");
										frame.dispose();
										break;
										}
									i++;	
								}
								
							
							}
						  } catch (HeadlessException e) {e.printStackTrace(); } 
						    catch (IOException e) {e.printStackTrace(); }
					  
					}
					}
				}
				back.addActionListener(new backLyst());
				elimina.addActionListener(new elimLyst());
				panel.add(lab1);
				panel.add(j);
				panel.add(lab2);
				panel.add(elimina);
				panel.add(back);
				frame.add(panel);
				frame.setVisible(true);
				
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
		panel.add(psw);
		panel.add(sel);
		
		panel.add(back);
		frame.add(panel);
		frame.setVisible(true);
	}

}
