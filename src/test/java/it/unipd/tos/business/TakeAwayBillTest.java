////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.exceptions.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItemType;
import it.unipd.tos.model.User;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TakeAwayBillTest {
    private final TakeAwayBill takeAwayBill = new TakeAwayBill();
    private final double delta = 0.001;
    private final LocalTime normalOrderingTime = LocalTime.of(15, 0);
    private final User user = new User(0, "Filippo", "Fantinato", 21);
    
    @Test
    public void testGetOrderPrice_NoItemsOrdered_Return0() {
        List<MenuItem> itemsOrdered = new ArrayList<>();
        
        double expected = 0;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso in cui la lista delle ordinazioni sia vuota.\n" +
                "Il risultato aspettato è che il prezzo totale sia 0.";
        
        assertEquals(message, expected, actual, delta);
    }
    
    @Test
    public void testGetOrderPrice_OrderNotNullItemsWithoutAnyDiscountOrCommissionAddition_ReturnSumOfAllItemsPrice() {
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
    public void testGetOrderPrice_OrderSomeNullItemsWithoutAnyDiscountOrCommissionAddition_ReturnSumOfAllNotNullItemsPrice() {
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
    public void testGetOrderPrice_OrderOnlyNullItems_Return0() {
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
     *
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetOrderPrice_ItemsOrderedListIsNull_ThrowsException()
            throws IllegalArgumentException {
        List<MenuItem> itemsOrdered = null;
    
        double expected = 20;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        assertEquals(expected, actual, delta);
    }
    
    /**
     * Testing del caso in cui l'utente abbia valore null
     * Il risultato aspettato è il lancio di un'eccezione
     *
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetOrderPrice_UserIsNull_ThrowsException()
            throws IllegalArgumentException {
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
     *
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetOrderPrice_OrderingTimeIsNull_ThrowsException()
            throws IllegalArgumentException {
        List<MenuItem> itemsOrdered = Arrays.asList(
                new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 10),
                new MenuItem(MenuItemType.Bevanda, "Coca cola", 10)
        );
        LocalTime normalOrderingTime = null;
        
        double expected = 20;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        assertEquals(expected, actual, delta);
    }
    
    @Test
    public void testGetOrderPrice_OrderedMoreThan5IceCreams_50percentDiscountOnCheaperIceCream() {
        List<MenuItem> itemsOrdered = Arrays.asList(
                new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 2),
                new MenuItem(MenuItemType.Gelato, "Cono alla banana", 3),
                new MenuItem(MenuItemType.Gelato, "Cono alla pesca", 3),
                new MenuItem(MenuItemType.Gelato, "Cono al melone", 7),
                new MenuItem(MenuItemType.Gelato, "Cono alla ciliega", 9),
                new MenuItem(MenuItemType.Gelato, "Cono alla nutella", 5),
                new MenuItem(MenuItemType.Bevanda, "Acqua", 0.50),
                new MenuItem(MenuItemType.Budino, "Biancaneve", 3)
        );
        
        double expected = 31.50;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso in cui siano stati ordinati più di 5 gelati.\n" +
                "Il risultato aspettato è che il prezzo totale sia la somma di tutti i prezzi dei prodotti + " +
                "il prezzo del gelato meno caro scontato del 50%";
        
        assertEquals(message, expected, actual, delta);
    }
    
    @Test
    public void testGetOrderPrice_IceCreamsPlusPuddingsTotalPriceOver50_10percentDiscountOnTotalPrice() {
        List<MenuItem> itemsOrdered = Arrays.asList(
                new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 10),
                new MenuItem(MenuItemType.Gelato, "Cono alla banana", 12),
                new MenuItem(MenuItemType.Gelato, "Cono alla nutella", 15),
                new MenuItem(MenuItemType.Bevanda, "Acqua", 0.50),
                new MenuItem(MenuItemType.Budino, "Biancaneve", 29)
        );
        
        double expected = 59.85;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso in cui l'importo di budini e gelati supera i 50€" +
                "e non si siano ordinati più di 5 gelati.\n" +
                "Il risultato aspettato è che il prezzo totale sia scontato del 10%";
        
        assertEquals(message, expected, actual, delta);
    }
    
    @Test
    public void
    testGetOrderPrice_IceCreamsPlusPuddingsTotalPriceOver50AndOrderedMoreThan5IceCreams_50percentDiscountOnCheaperIceCreamAnd10percentDiscountOnTotalPrice() {
        List<MenuItem> itemsOrdered = Arrays.asList(
                new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 10),
                new MenuItem(MenuItemType.Gelato, "Cono alla banana", 12),
                new MenuItem(MenuItemType.Gelato, "Cono alla nutella", 15),
                new MenuItem(MenuItemType.Gelato, "Cono al melone", 10),
                new MenuItem(MenuItemType.Gelato, "Cono alla prugna", 12),
                new MenuItem(MenuItemType.Gelato, "Cono alla pesca", 15),
                new MenuItem(MenuItemType.Bevanda, "Acqua", 0.50),
                new MenuItem(MenuItemType.Budino, "Biancaneve", 29)
        );
        
        double expected = 88.65;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso in cui l'importo di budini e gelati supera i 50€" +
                "e si siano ordinati più di 5 gelati.\n" +
                "Il risultato aspettato è che il prezzo totale sia la somma di tutti i prezzi dei prodotti + " +
                "il prezzo del gelato meno caro scontato del 50%, per poi applicare un'ulteriore sconto del 10%" +
                "al prezzo totale risultante";
        
        assertEquals(message, expected, actual, delta);
    }
    
    /**
     * Testing del caso in cui l'ordinazione ha un numero di elementi non null superiore a 30
     * Il risultato aspettato è il lancio di un'eccezione
     *
     * @throws TakeAwayBillException
     */
    @Test(expected = TakeAwayBillException.class)
    public void testGetOrderPrice_OrderWithMoreThan30NotNullItems_ThrowException()
            throws TakeAwayBillException
    {
        final int itemsOrderedNumber = 39;
        List<MenuItem> itemsOrdered = new ArrayList<>();
    
        for(int i = 0; i < itemsOrderedNumber; ++i)
        {
            itemsOrdered.add(new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 2));
        }
        
        double expected = 69.3;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        assertEquals(expected, actual, delta);
    }
    
    @Test
    public void testGetOrderPrice_OrderWithMoreThan30NullItemsToo_CalculateNormalPriceWithoutThrowException()
            throws TakeAwayBillException
    {
        final int itemsOrderedNumber = 29;
        List<MenuItem> itemsOrdered = new ArrayList<>();
        
        for(int i = 0; i < itemsOrderedNumber; ++i)
        {
            itemsOrdered.add(new MenuItem(MenuItemType.Gelato, "Cono alla fragola", 2));
        }
        
        itemsOrdered.add(null); itemsOrdered.add(null);
    
        double expected = 51.3;
        double actual = takeAwayBill.getOrderPrice(itemsOrdered, user, normalOrderingTime);
        
        String message = "Testing del caso in cui l'ordine abbia un numero di elementi superiore a 30, " +
                "ma sono presenti dei valori null.\n" +
                "Il risultato aspettato è che il prezzo totale sia calcolato seguendo la logica normale, " +
                "visto che gli elementi con valore null non devono essere considerati";
        
        assertEquals(expected, actual, delta);
    }
}