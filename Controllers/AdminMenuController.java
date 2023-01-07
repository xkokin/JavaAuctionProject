package Controllers;

import Gui.ItemPreview;
import Gui.MainMenu;
import Gui.NewAuction;
import Products.Collection;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
/**
 * @author HlibKokin
 * @version 13/05/2022
 * */
public class AdminMenuController {
	/**
	 * Opens modal window to add new auction,
	 * then rereads file and making changes in the list of the available auction(actualizing data)  
	 * 
	 * @param items
	 * @throws Exception
	 */
	public static void AdAuc(FlowPane items) throws Exception {
		NewAuction.NewAuc();
		MainMenu.ReadItems();
		items.getChildren().clear();
		for(Collection o : MainMenu.GetItemslist()) {
			Button p = new Button(o.GetName());
			items.getChildren().add(p);
			p.setOnAction(e ->{

			try {
				ItemPreview.preview(o);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			});
			
		}
		
	}
}
