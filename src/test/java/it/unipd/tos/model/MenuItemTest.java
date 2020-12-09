////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemNameException;
import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemPriceException;
import it.unipd.tos.model.exceptions.menuitemexceptions.IllegalMenuItemTypeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MenuItemTest
{
    private final double delta = 0.001;
    
    @Test
    public void testConstructor_ValidParameters_ReturnMenuItemWithThoseParameters()
    {
        MenuItemType menuItemType = MenuItemType.Budino;
        String name = "Biancaneve";
        double price = 20.50;
    
        MenuItem menuItem = new MenuItem(menuItemType, name, price);
    
        assertEquals(menuItemType, menuItem.getItemType());
        assertEquals(name, menuItem.getName());
        assertEquals(price, menuItem.getPrice(), delta);
    }
    
    @Test(expected = IllegalMenuItemTypeException.class)
    public void testConstructor_NullMenuItemType_ThrowsException()
            throws IllegalMenuItemTypeException
    {
        MenuItemType menuItemType = null;
        String name = "Biancaneve";
        double price = 20.50;
    
        MenuItem menuItem = new MenuItem(menuItemType, name, price);
        
        assertEquals(menuItemType, menuItem.getItemType());
    }
    
    @Test(expected = IllegalMenuItemNameException.class)
    public void testConstructor_NullName_ThrowsException()
            throws IllegalMenuItemNameException
    {
        MenuItemType menuItemType = MenuItemType.Budino;
        String name = null;
        double price = 20.50;
        
        MenuItem menuItem = new MenuItem(menuItemType, name, price);
        
        assertEquals(name, menuItem.getName());
    }
    
    @Test(expected = IllegalMenuItemNameException.class)
    public void testConstructor_BlankName_ThrowsException()
            throws IllegalMenuItemNameException
    {
        MenuItemType menuItemType = MenuItemType.Budino;
        String name = "        ";
        double price = 20.50;
        
        MenuItem menuItem = new MenuItem(menuItemType, name, price);
        
        assertEquals(name, menuItem.getName());
    }
    
    @Test(expected = IllegalMenuItemPriceException.class)
    public void testConstructor_NegativePrice_ThrowsException()
            throws IllegalMenuItemPriceException
    {
        MenuItemType menuItemType = MenuItemType.Budino;
        String name = "Biancaneve";
        double price = -20.50;
        
        MenuItem menuItem = new MenuItem(menuItemType, name, price);
        
        assertEquals(price, menuItem.getPrice(), delta);
    }
}