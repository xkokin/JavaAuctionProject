package Controllers;





import Gui.ModalMyAuc;
import Products.Collection;
import Users.Guest;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Controller that register guest for auction
 */
public class RegisterAucController {
		/**
		 * if button yes was pressed, that means that we need to check if we can register guest for the auction
		 * and then register him
		 * Method will start checking with the list of registered auctions for guest,
		 * if there is already such auction, there will appear error 
		 * Then method checks wallet of the guest, if there is less ehtn 110% of the price, guest will not be registered
		 * Afterwards, method calsculates timer until the aucion, if timer is less or equals zero, auction will not be registered 
		 * Registered auction is stored in file for certain guest.
		 * @param guest
		 * @param item
		 * @param AlReg
		 * @param NoMon
		 * @param AlB
		 * @param Reg
		 * @throws Exception
		 */
	public static void yes(Guest guest, Collection item, Alert AlReg, Alert NoMon, Alert AlB,  Stage Reg) throws Exception {
		for(Collection o : guest.GetRegistered()) {
			if(o.GetName() == item.GetName()) {
				AlReg.showAndWait();
				return;
			}
		}
		if(guest.getWallet() < (item.GetPrice()*1.1)) {
			NoMon.showAndWait();
			return;
		}
		item.SetSchedule();
		if (item.GetSchedule()== 0) {
			AlB.showAndWait();
			Reg.close();
			return;
		}
		guest.RegisterAuction(item);
		System.out.println("You were registered for auction: " + item.GetName());
		ManageItem.StoreReg(guest, item);
		Reg.close();
		
		
		ModalMyAuc.MyAuc(guest);
	}
}
