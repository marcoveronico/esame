package progr;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.EncryptedPrivateKeyInfo;

import sensori.*;

/**
 * gestisce un impianto contenente un insieme di sensori
 */



public class Impianto 
{
	@SuppressWarnings("unused")
	private int idImpianto;
	private String nomeImp;
	private String propr;
	private EncryptedPrivateKeyInfo password;
	private int idSen=0;
	private Sensore s;
	private ArrayList<Sensore> sensList=new ArrayList<Sensore>();
	/**
	 * 
	 * @param idImpianto2 ID univoco Impianto
	 * @param nome nome dell'impianto
	 * @param prop nome del proprietario dell'impianto
	 * @param psw password associata all'impianto
	 * @throws IOException  eccezione
	 * @throws NoSuchAlgorithmException  eccezione
	 */
	public Impianto(int idImpianto2,String nome,String prop,String psw) throws IOException, NoSuchAlgorithmException
	{
		nomeImp=nome;
		idImpianto=idImpianto2;
		byte[] pswCript=psw.getBytes();
		 password=new EncryptedPrivateKeyInfo("MD2",pswCript);
		propr=prop;
	}
		public String getNomeIm()
		{
			return nomeImp;
		}
		public String getNomePropr()
		{
			return propr;
		}
		public String getPsw() throws IOException
		{
			String psw = "";
			for(byte x:password.getEncryptedData())
			{
				for(char c:Character.toChars(x))
				psw=psw+String.valueOf(c);	
			}
			return psw;
		}
	/**
	 * inserisce nell'impianto un preset di sensori. utilizzato per i casi di test
	 */
	  public void insSensore() 
	  {	
		String pos=null;
		pos="magazzino";
		s=new SenPhilipsUmidità(idSen,pos);
	    sensList.add(s);
		idSen=idSen+1;
		pos="caldaia";
		s=new SenSamsungCalore(idSen,pos);
		sensList.add(s);
		idSen=idSen+1;
		pos="falegnameria";
		s=new SenPhilipsFumo(idSen,pos);
		sensList.add(s);
		idSen=idSen+1;
		pos="cisterna";
		s=new SenSamsungFumo(idSen,pos);
		sensList.add(s);
	   }
	  public void insSensorePhilFumo(String posizione) 
	   {
		sensList.add(new SenPhilipsFumo(idSen,posizione));
		idSen=idSen+1;
	   }
	  public void insSensorePhilUmidità(String posizione) 
	   {
		sensList.add(new SenPhilipsUmidità(idSen,posizione));
		idSen=idSen+1;
	   }
	  public void insSensoreSamsuFumo(String posizione) 
	   {
		sensList.add(new SenSamsungFumo(idSen,posizione));
		idSen=idSen+1;
	   }
	  public void insSensoreSamsunCalore(String posizione) 
	   {
		sensList.add(new SenSamsungCalore(idSen,posizione));
		idSen=idSen+1;
	   }
		/**
		 * 
		 * @return elenco dei sensori installati nell'impianto
		 */
	   public ArrayList<Sensore> contrSens()
	   {
		return sensList;
	   }
	   public void elimSens(int i)
	   {
		   sensList.remove(i);
	   }
}
