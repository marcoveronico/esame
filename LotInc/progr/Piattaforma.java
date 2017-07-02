package progr;


import java.util.ArrayList;


/**
 * contiene un elenco degli impianti installati e gestiti dalla piattaforma
 * @author marco
 *
 */
public class Piattaforma 
{
	public ArrayList<Impianto> ImpList=new ArrayList<Impianto>();
	/**
	 * 
	 * @param impianto esemplare di classe Impianto da aggiugere al'array degli impianti
	 */
	public void insIm(Impianto impianto)
	{
		ImpList.add(impianto);
	}
	
}
