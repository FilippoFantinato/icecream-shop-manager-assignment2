////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemNameException;
import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemPriceException;
import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemTypeException;

import java.util.Comparator;

public class MenuItem
{
    private final MenuItemType itemType;
    private final String name;
    private final double price;
    
    public MenuItem(MenuItemType itemType, String name, double price)
            throws IllegalMenuItemTypeException, IllegalMenuItemNameException,
            IllegalMenuItemPriceException
    {
        if(itemType == null)
        {
            throw new IllegalMenuItemTypeException();
        }
        if(name == null || name.trim().isEmpty())
        {
            throw new IllegalMenuItemNameException();
        }
        if(price < 0)
        {
            throw new IllegalMenuItemPriceException(price);
        }
        this.itemType = itemType;
        this.name = name;
        this.price = price;
    }
    
    public MenuItemType getItemType()
    {
        return itemType;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public static class MenuItemComparator implements Comparator<MenuItem>
    {
        @Override
        public int compare(MenuItem o1, MenuItem o2)
        {
            double diff = o1.getPrice() - o2.getPrice();
            
            return diff < 0 ? -1 : (diff > 0 ? 1 : 0);
        }
    }
}
