////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.exceptions.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.time.LocalTime;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class TakeAwayBill implements ITakeAwayBill
{
    private final Map<User, List<LocalTime>> orders = new HashMap<>();
    private int ordersGivenAway = 0;
    
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime orderingTime)
            throws TakeAwayBillException, IllegalArgumentException
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
        double iceCreamsAndPuddingsPrice = 0;
        List<MenuItem> iceCreams = new ArrayList<>();
    
        itemsOrdered = removeNullItems(itemsOrdered);
        if(itemsOrdered.size() > 30)
        {
            throw new TakeAwayBillException(itemsOrdered.size());
        }
        
        for(MenuItem item: itemsOrdered)
        {
            totalPrice += item.getPrice();
            
            switch(item.getItemType())
            {
                case Gelato: {
                    iceCreams.add(item);
                }
                case Budino: {
                    iceCreamsAndPuddingsPrice += item.getPrice();
                    break;
                }
            }
        }
        
        if(!orders.containsKey(user)) { orders.put(user, new ArrayList<>()); }
        
        if(checkConditionsToGiveAwayOrder(user, orderingTime) &&
                giveAwayOrder(orderingTime.toSecondOfDay()))
        {
            totalPrice = 0;
            ++ordersGivenAway;
        }
        else
        {
            totalPrice = addCommission(totalPrice);
            totalPrice = applyDiscountToIceCream(totalPrice, iceCreams);
            totalPrice = applyDiscountToTotalPrice(totalPrice, iceCreamsAndPuddingsPrice);
        }
        
        orders.get(user).add(orderingTime);
        
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
    
    private double applyDiscountToTotalPrice(double totalPrice, double iceCreamsAndPuddingsPrice)
    {
        if(iceCreamsAndPuddingsPrice > 50)
        {
            totalPrice = totalPrice * 0.9;
        }
        
        return totalPrice;
    }
    
    private double addCommission(double totalPrice)
    {
        if(totalPrice != 0 && totalPrice < 10)
        {
            totalPrice += 0.50;
        }
        
        return totalPrice;
    }
    
    private boolean checkConditionsToGiveAwayOrder(User user, LocalTime orderingTime)
    {
        boolean tryToGiveAway = false;
        
        if(ordersGivenAway <= 10)
        {
            boolean alreadyOrderedBetweenGiveAwayTime = false;
            
            for(LocalTime time: orders.get(user))
            {
                alreadyOrderedBetweenGiveAwayTime = isBetweenGiveAwayTime(time);
                
                if(alreadyOrderedBetweenGiveAwayTime) { break; }
            }
            
            tryToGiveAway = isBetweenGiveAwayTime(orderingTime) &&
                    user.isUnderage() &&
                    !alreadyOrderedBetweenGiveAwayTime;
        }
        
        return tryToGiveAway;
    }
    
    private boolean giveAwayOrder(long seed)
    {
        Random random = new Random(seed);
    
        return random.nextBoolean();
    }
    
    private boolean isBetweenGiveAwayTime(LocalTime time)
    {
        final LocalTime timeStart = LocalTime.of(17, 59, 59);
        final LocalTime timeEnd = LocalTime.of(19, 0, 1);
    
        return time.isAfter(timeStart) && time.isBefore(timeEnd);
    }
}
