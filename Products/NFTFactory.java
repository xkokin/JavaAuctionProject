package Products;


/**
 * @author HlibKokin
 * @version 13/05/2022
 * Factory for different types of NFT collections
 * each type creates object of different specific class
 * 
 */
public class NFTFactory {
	public static Collection createNFT(NFTtypes type) {
		Collection item = null;
		
		switch (type) {
        case BAYC:
            item = new ItemBAYC();
            break;
        case CP:
            item = new ItemCP();
            break;
        
		}
	return item;
	}
}
