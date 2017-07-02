package buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import progr.Dashboard;
import progr.Impianto;
import progr.Test;
import sensori.Sensore;
/**
 * 
 * gestisce la crazione della dashboard contenente un riassunto di tutte le rilevazioni dei sensori per ogni impianto
 */
public class ProdDashbLyst extends Test implements ActionListener 
{
   @Override
	public void actionPerformed(ActionEvent arg0) 
	{
		for(Impianto x:piat.ImpList)
		{
			ArrayList<String> elen=new ArrayList<String>();
			for(Sensore s:x.contrSens())
			{
				elen.add(s.adattatore());
			}
			try {
				dashb.add(new Dashboard(elen,x.getNomeIm(),x.getPsw()));
			    } catch (IOException e) { e.printStackTrace();}
		}
	}
}
