////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemNameException;
import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemPriceException;
import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemTypeException;

public class MenuItem
{
    private final MenuItemType itemType;
    private final String name;
    private final double price;
    
    public MenuItem(MenuItemType itemType, String name, double price)
            throws IllegalMenuItemTypeException, IllegalMenuItemNameException,
            IllegalMenuItemPriceException
    {
        this.itemType = MenuItemType.Gelato;
        this.name = "";
        this.price = 42;
    }
    
    public MenuItemType getItemType() {
        return MenuItemType.Gelato;
    }
    
    public String getName() {
        return "";
    }
    
    public int getPrice() {
        return 42;
    }
}
