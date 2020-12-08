////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItemType;
import it.unipd.tos.model.User;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TakeAwayBillTest
{
    private final TakeAwayBill takeAwayBill = new TakeAwayBill();
    private final double delta = 0.001;
    private final LocalTime normalOrderingTime = LocalTime.of(15, 0);
    private final User user = new User(0, "Filippo", "Fantinato", 21);
    
    @Test
    public void testGetOrderPrice_NoItemsOrdered_Return0()
    {
        List<MenuItem> itemsOrdered = new ArrayList<>();
        
        double expected = 0;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso in cui la lista delle ordinazioni sia vuota.\n" +
                "Il risultato aspettato è che il prezzo totale sia 0.";
        
        assertEquals(message, expected, actual, delta);
    }
    
    @Test
    public void testGetOrderPrice_OrderNotNullItemsWithoutAnyDiscountOrCommissionAddition_ReturnSumOfAllItemsPrice()
    {
        List<MenuItem> itemsOrdered = Arrays.asList(
                new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 10),
                new MenuItem(MenuItemType.Budino, "Biancaneve", 5),
                new MenuItem(MenuItemType.Bevanda, "Coca cola", 10)
        );
        
        double expected = 25;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso base con nessuno sconto applicato e tutti i parametri validi, quindi" +
                "un'ordinazione con meno di 5 gelati, il prezzo totale <= 50€ e >= 10€ e ha meno di 30 elementi non null." +
                "Il risultato aspettato è che il prezzo totale sia la somma dei prezzi di ogni prodotto ordinato.";
        
        assertEquals(message, expected, actual, delta);
    }
    
    @Test
    public void testGetOrderPrice_OrderSomeNullItemsWithoutAnyDiscountOrCommissionAddition_ReturnSumOfAllNotNullItemsPrice()
    {
        List<MenuItem> itemsOrdered = Arrays.asList(
                new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 10),
                null,
                new MenuItem(MenuItemType.Bevanda, "Coca cola", 10)
        );
        
        double expected = 20;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso base con nessuno sconto applicato e tutti i parametri validi, ma con qualche" +
                "prodotto che ha valore null." +
                "Il risultato aspettato è che il prezzo totale sia la somma dei prezzi dei prodotti non nulli";
        
        assertEquals(message, expected, actual, delta);
    }
    
    @Test
    public void testGetOrderPrice_OrderOnlyNullItems_Return0()
    {
        List<MenuItem> itemsOrdered = Arrays.asList(
                null,
                null,
                null
        );
        
        double expected = 0;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso in cui tutti i prodotti ordinati hanno valore null." +
                "Il risultato aspettato è che il prezzo totale sia uguale a 0";
        
        assertEquals(message, expected, actual, delta);
    }
    
    /**
     * Testing del caso in cui la lista di prodotti ordinati abbia valore null
     * Il risultato aspettato è il lancio di un'eccezione
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetOrderPrice_ItemsOrderedListIsNull_ThrowsException()
            throws IllegalArgumentException
    {
        List<MenuItem> itemsOrdered = null;

        double expected = 20;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso in cui la lista di prodotti ordinati abbia valore null." +
                "Il risultato aspettato è il lancio di un'eccezione.";
        
        assertEquals(message, expected, actual, delta);
    }
    
    /**
     * Testing del caso in cui l'utente abbia valore null
     * Il risultato aspettato è il lancio di un'eccezione
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetOrderPrice_UserIsNull_ThrowsException()
            throws IllegalArgumentException
    {
        List<MenuItem> itemsOrdered = Arrays.asList(
                new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 10),
                new MenuItem(MenuItemType.Bevanda, "Coca cola", 10)
        );
        User user = null;
        
        double expected = 20;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        assertEquals(expected, actual, delta);
    }
    
    /**
     * Testing del caso in cui l'orario di ordinazione abbia valore null
     * Il risultato aspettato è il lancio di un'eccezione
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetOrderPrice_OrderingTimeIsNull_ThrowsException()
            throws IllegalArgumentException
    {
        List<MenuItem> itemsOrdered = Arrays.asList(
                new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 10),
                new MenuItem(MenuItemType.Bevanda, "Coca cola", 10)
        );
        LocalTime normalOrderingTime = null;
        
        double expected = 20;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        assertEquals(expected, actual, delta);
    }
}