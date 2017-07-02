package sensori;
/**
 * 
 * classe astratta per definire nuovi sensori
 *
 */
public abstract class Sensore
{
	/**
	 * 
	 * @return una stringa nel formato - id - stringadecimale(informazioni rilevazione) - messaggio 
	 */
	public abstract String adattatore();
	/**
	 * 
	 * @return una stringa con l'id univo del sensore
	 */
	public  abstract String getID();
	/**
	 * una stringa con la posizione del sensore nell'impianto in cui è installato
	 * @return stringa posizione
	 */
	public abstract String GetPos();
}
