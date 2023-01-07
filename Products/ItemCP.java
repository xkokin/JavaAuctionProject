package Products;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Specific collection of NFT
 */
public class ItemCP extends Collection {

	/**
	 * sets name and certain collection name
	 */
	public void SetName (String name) {
		super.SetName(name);
		this.collection = "Crypto Punk";
		
	}
	
}
