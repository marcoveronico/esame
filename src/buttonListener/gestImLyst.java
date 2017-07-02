package buttonListener;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

/**
 * 
 * classe che gestisce l'inserimento dei sensori in un determinato impianto
 *
 */
public class gestImLyst extends Test implements ActionListener 
{
	public void actionPerformed(ActionEvent arg0)
	{
		final JFrame frame =new JFrame();
		frame.setSize(400,600);
		frame.setTitle("Piattaforma IOT Inc.");
		frame.setLocation(100, 100);
		JPanel panel=new JPanel();
		JLabel lab1=new JLabel("lista Impianti");
		JTextArea text=new JTextArea(2,20);
		for(Impianto x:piat.ImpList)
		  text.append(x.getNomeIm()+"\n");
		JLabel lab2=new JLabel("inserire nome impianto da gestire");
		final JTextField lab1Field=new JTextField(15);
		JLabel lab3=new JLabel("inserire password impianto");
		final JPasswordField psw=new JPasswordField(15);
		final JButton conferma=new JButton("conferma dati ");
		
		final JTextArea text2=new JTextArea(2,30);
		text2.append(" PRESET SENSORI \n");
		text2.append("1) posizione: magazzino \n sensore philips umidità \n");
		text2.append("2) posizione: caldaia \n sensore samsung calore \n");
		text2.append("3) posizione: falegnameria \n sensore philips fumo \n");
		text2.append("4) posizione: cisterna \n sensore samsung fumo \n");
		
		final JButton agg=new JButton("aggiungi sensore selezionato");
		final JTextField aField=new JTextField(10);
		final JLabel label=new JLabel("posizione");
		agg.setVisible(false);
		text2.setVisible(false);
		aField.setVisible(false);
		label.setVisible(false);
		String[] data = {"sensore philips umidità", "sensore samsung calore", "sensore philips fumo", "sensore samsung fumo","preset"};
		final JList<String> j = new JList<String>(data);
		j.setVisible(false);
		JButton back=new JButton("menù precedente");
		//classe interna per definizione azione tasto back
		class backLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
			}
		}	
		back.addActionListener(new backLyst());
		//classe interna per definizione azione tasto conferma
		class confLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean flag=true;
				for(Impianto x:piat.ImpList)
				{
					try {
						if(x.getNomeIm().equalsIgnoreCase(lab1Field.getText())&& x.getPsw().equalsIgnoreCase(String.copyValueOf(psw.getPassword())))
						{
							flag=false;
							j.setVisible(true);
							agg.setVisible(true);
							text2.setVisible(true);
							aField.setVisible(true);
							label.setVisible(true);
							conferma.setVisible(false);
							JOptionPane.showMessageDialog(null, "dati  confermati");
							lab1Field.setEditable(false);
							psw.setEditable(false);
							
						}
						
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(flag==true) JOptionPane.showMessageDialog(null, "dati non confermati, impianto o password errati");

			}
		}
		//classe interna per definizione azione tasto aggiungi sensore
		class aggLyst implements ActionListener 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				
				for(Impianto x:piat.ImpList)
				{
				try {
					if(x.getNomeIm().equalsIgnoreCase(lab1Field.getText())&& x.getPsw().equalsIgnoreCase(String.copyValueOf(psw.getPassword())))
					{
					  if(j.getSelectedValue()==null)
							  JOptionPane.showMessageDialog(null, "selezionare un sensore");
					  else
					  {
							if(j.getSelectedValue().equals("sensore philips umidità") &&!aField.getText().isEmpty())
								{
								x.insSensorePhilUmidità(aField.getText());
								JOptionPane.showMessageDialog(null, "sensore inserito");
								}
						    else if(j.getSelectedValue().equals("sensore philips umidità") &&aField.getText().isEmpty()) JOptionPane.showMessageDialog(null, "compilare posizione sensore selezionato");
								
							if(j.getSelectedValue().equals("sensore philips fumo") &&!aField.getText().isEmpty())
							{
							x.insSensorePhilFumo(aField.getText());
							JOptionPane.showMessageDialog(null, "sensore inserito");
							}
						    else if(j.getSelectedValue().equals("sensore philips fumo") &&aField.getText().isEmpty()) JOptionPane.showMessageDialog(null, "compilare posizione sensore selezionato");
							
							if(j.getSelectedValue().equals("sensore samsung fumo") &&!aField.getText().isEmpty())
							{
							x.insSensoreSamsuFumo(aField.getText());
							JOptionPane.showMessageDialog(null, "sensore inserito");
							}
						    else if(j.getSelectedValue().equals("sensore samsung fumo") &&aField.getText().isEmpty()) JOptionPane.showMessageDialog(null, "compilare posizione sensore selezionato");
							
							if(j.getSelectedValue().equals("sensore samsung calore") &&!aField.getText().isEmpty())
							{
							x.insSensoreSamsunCalore(aField.getText());
							JOptionPane.showMessageDialog(null, "sensore inserito");
							}
						    else if(j.getSelectedValue().equals("sensore samsung calore") &&aField.getText().isEmpty()) JOptionPane.showMessageDialog(null, "compilare posizione sensore selezionato");
							
							if(j.getSelectedValue().equals("preset") )
							{
							x.insSensore();
							JOptionPane.showMessageDialog(null, "preset caricato");
							}
					}
							
				  }
				 } catch (HeadlessException e) {e.printStackTrace();} 
				   catch (IOException e) {e.printStackTrace();}
			  }
		   }
	  }
		
		 conferma.addActionListener(new confLyst());
		 agg.addActionListener(new aggLyst());
		 panel.add(lab1);
		 panel.add(text);
		 panel.add(lab2);
		 panel.add(lab1Field);
		 panel.add(lab3);
		 panel.add(psw);
		 panel.add(conferma);
		 panel.add(text2);
		 panel.add(j);
		 panel.add(label);
		 panel.add(aField);
		 panel.add(agg);
		 panel.add(back);
		 frame.add(panel);
		 frame.setVisible(true);
	}	
	
}
