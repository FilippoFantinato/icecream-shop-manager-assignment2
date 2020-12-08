////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItemType;
import it.unipd.tos.model.User;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TakeAwayBill implements ITakeAwayBill
{
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime orderingTime)
            throws IllegalArgumentException
    {
        if(itemsOrdered == null)
        {
            throw new IllegalArgumentException("La lista delle ordinazioni non può avere valore null");
        }
        if(user == null)
        {
            throw new IllegalArgumentException("L'utente non può avere valore null");
        }
        if(orderingTime == null)
        {
            throw new IllegalArgumentException("L'orario di ordinazione non può avere valore null");
        }
        
        double totalPrice = 0;
        
        itemsOrdered = removeNullItems(itemsOrdered);
        
        List<MenuItem> iceCreams = new ArrayList<>();
        
        for(MenuItem item: itemsOrdered)
        {
            totalPrice += item.getPrice();
            
            if(item.getItemType() == MenuItemType.Gelato) { iceCreams.add(item); }
        }
        
        totalPrice = applyDiscountToIceCream(totalPrice, iceCreams);
        
        return totalPrice;
    }
    
    private List<MenuItem> removeNullItems(List<MenuItem> itemsOrdered)
    {
        List<MenuItem> tmp = new ArrayList<>();
        
        for(MenuItem item: itemsOrdered)
        {
            if(item != null) { tmp.add(item); }
        }
        
        return tmp;
    }
    
    private double applyDiscountToIceCream(double totalPrice, List<MenuItem> iceCreams)
    {
        if(iceCreams.size() > 5)
        {
            iceCreams.sort(new MenuItem.MenuItemComparator());
            
            MenuItem cheaperIceCream = iceCreams.get(0);
            totalPrice -= cheaperIceCream.getPrice();
            totalPrice += cheaperIceCream.getPrice() * 0.5;
        }
        
        return totalPrice;
    }
}
