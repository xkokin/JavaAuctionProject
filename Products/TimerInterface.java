package Products;

import javafx.scene.control.Alert;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * Interface with default implementation of the method @see TimerInterface#Notify()
 */
public interface TimerInterface {
		public void CalculateTime();
		
		
		/**
		 * Notifies guests about start of some auction
		 */
		default public void Notify() {			 //default method implementation
			Alert AucSt = new Alert(Alert.AlertType.INFORMATION);

			AucSt.setTitle("Auction notify");
			AucSt.setHeaderText("One of your auctions has been started");
			AucSt.setContentText("You can join the auction now!");
			
			AucSt.showAndWait();
			
		}
}
