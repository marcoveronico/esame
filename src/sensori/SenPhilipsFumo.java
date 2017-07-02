package sensori;

import java.util.Date;
import java.util.Random;

import progr.utility;

public class SenPhilipsFumo extends Sensore implements SensoreInt 
{
	//variabili locali
	private  String id;
	private String marca;
	@SuppressWarnings("unused")
	private String protocolloTrasm;
	private String rilevazione;
	@SuppressWarnings("unused")
	private Date data;
	private String codErrore;
	private int valoreRilevazione=0;
	private String tipoSensore;
	private String posizione;

	//costruttore
	/**
	 * 
	 * @param idsen id sensore univoco
	 * @param pos posizione sensore nell'impianto associato
	 */
	public SenPhilipsFumo(int idsen,String pos)
	{
		
		marca="philips";
		protocolloTrasm="AB2";
		
		tipoSensore="Rilevatore_di_fumo";
		id=marca+tipoSensore+idsen;
		posizione=pos;
	}
	/**
	 * 
	 * @return valore della rilevazione
	 */
	private int valoreril()
	{
		
		valoreRilevazione=new Random().nextInt(2);
		return valoreRilevazione;
		
	}
	@Override
	public String adattatore()
		{
		int err =new Random().nextInt(100);
		if(err<=80) codErrore="00";
		else    codErrore="01"; 
		utility u=new utility();
		if(codErrore.equalsIgnoreCase("00"))
			{
				data=new Date();
				
				String dataString=u.calStringaData(new Date());
				String stringadec=dataString+valoreril();
				rilevazione="<id:"+id+">"+"<"+stringadec+">"+"<Rilevatore_Fumo>"; 
			}
			else
			{
				String dataString=u.calStringaData(new Date());
			rilevazione="<id"+id+">"+"<"+dataString+">"+" < errore :"+ codErrore+ "<Rilevazione_Fumo>";	
			}
		return rilevazione;	
	}
    @Override
	public String getID() {
		
		return id;
	}
	@Override
	public String GetPos() {
		
		return posizione;
	}
}