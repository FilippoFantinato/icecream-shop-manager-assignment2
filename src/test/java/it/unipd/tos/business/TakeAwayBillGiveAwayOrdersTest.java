////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItemType;
import it.unipd.tos.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests che riguardano la possibilità di regalo di un ordine
 */
public class TakeAwayBillGiveAwayOrdersTest
{
    private TakeAwayBill takeAwayBill;
    private final double delta = 0.001;
    
    private List<User> users;
    
    public TakeAwayBillGiveAwayOrdersTest()
    {
        this.users = Arrays.asList(
                new User(0, "Filippo", "Fantinato", 21),
                new User(1, "Alice", "Fantinato", 17),
                new User(2, "Viola", "Fantinato", 8),
                new User(3, "Matteo", "Fantinato", 10),
                new User(4, "Lorenzo", "Fantinato", 11),
                new User(5, "Federico", "Fantinato", 34),
                new User(6, "Alessandro", "Galassi", 5),
                new User(7, "Alessandra", "Galassi", 7),
                new User(8, "Carlottina", "Adonato", 12),
                new User(9, "Giancarlo", "Fantinato", 24),
                new User(10, "Beppe", "Fantinato", 2),
                new User(11, "Pino", "Fantinato", 3),
                new User(12, "Pina", "Fantinato", 7),
                new User(13, "Gigetto", "Fantinato", 16),
                new User(14, "Osvaldo", "Fantinato", 17),
                new User(15, "Pietro", "Fantinato", 56),
                new User(16, "Piero", "Fantinato", 89),
                new User(17, "Francesco", "Fantinato", 9),
                new User(18, "Mattia", "Fantinato", 67),
                new User(19, "Summer", "Fantinato", 34),
                new User(20, "Violetta", "Fantinato", 12),
                new User(21, "Rosetta", "Fantinato", 6),
                new User(22, "Celeste", "Fantinato", 9)
        );
    }
    
    @Before
    public void before()
    {
        takeAwayBill = new TakeAwayBill();
    }
    
    /**
     * Testing del caso in cui non vengono effettuate ordinazioni tra le 18 e le 19
     * Il risultato aspettato è che nessun ordine viene regalato
     */
    @Test
    public void testGetOrderPrice_NoOneOrderBetween18And19_NoOrdersAreGifted()
    {
        List<OrderHelper> orders = Arrays.asList(
                new OrderHelper(
                        users.get(1),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(17, 0),
                        10
                ),
                new OrderHelper(
                        users.get(7),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(19, 0, 1),
                        10
                ),
                new OrderHelper(
                        users.get(8),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(17, 59),
                        10
                ),
                new OrderHelper(
                        users.get(11),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(13, 0),
                        10
                )
        );
        
        assertOrders(orders);
    }
    
    /**
     * Testing del caso in cui vengono effettuate ordinazioni tra le 18 e le 19,
     * ma solo da utenti maggiorenni
     * Il risultato aspettato è che nessun ordine viene regalato
     */
    @Test
    public void testGetOrderPrice_NoUnderageUsersOrderBetween18And19_NoOrdersAreGifted()
    {
        List<OrderHelper> orders = Arrays.asList(
                new OrderHelper(
                        users.get(9),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 5),
                        10
                ),
                new OrderHelper(
                        users.get(18),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 4),
                        10
                ),
                new OrderHelper(
                        users.get(19),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 23),
                        10
                ),
                new OrderHelper(
                        users.get(15),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 22),
                        10
                )
        );
        
        assertOrders(orders);
    }
    
    /**
     * Testing del caso in cui un utente ordina più volte tra le 18 e le 19
     * Il risultato aspettato è che il primo ordine viene regalato o no, in base al caso,
     * mentre il secondo certamente no
     */
    @Test
    public void
    testGetOrderPrice_UserOrderMultipleTimesBetween18And19_FirstOrderMaybeWillBeGiftedSecondOrderOfCourseNot()
    {
        List<OrderHelper> orders = Arrays.asList(
                new OrderHelper(
                        users.get(2),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 5),
                        0
                ),
                new OrderHelper(
                        users.get(2),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 8),
                        10
                ),
                new OrderHelper(
                        users.get(8),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 9),
                        10
                ),
                new OrderHelper(
                        users.get(8),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 22),
                        10
                ),
                new OrderHelper(
                        users.get(5),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 21),
                        10
                ),
                new OrderHelper(
                        users.get(5),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 22),
                        10
                )
        );
        
        assertOrders(orders);
    }
    
    /**
     * Testing del caso in cui vengono regalati 10 ordini
     * Il risultato aspettato è che non viene regalato più alcun ordine in seguito al decimo
     */
    @Test
    public void
    testGetOrderPrice_MultipleUsersOrderAlsoBetween18and19_Maximum10RandomOrdersWillBeGifted()
    {
        List<OrderHelper> orders = Arrays.asList(
                new OrderHelper(
                        users.get(0),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 0),
                        10
                ),
                new OrderHelper(
                        users.get(22),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 4),
                        10
                ),
                new OrderHelper(
                        users.get(22),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 13),
                        10
                ),
                new OrderHelper(
                        users.get(1),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(17, 0),
                        10
                ),
                new OrderHelper(
                        users.get(1),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 48),
                        0
                ),
                new OrderHelper(
                        users.get(1),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 6),
                        10
                ),
                new OrderHelper(
                        users.get(2),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(16, 0),
                        10
                ),
                new OrderHelper(
                        users.get(2),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 47),
                        0
                ),
                new OrderHelper(
                        users.get(3),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 30),
                        0
                ),
                new OrderHelper(
                        users.get(4),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(19, 0),
                        0
                ),
                new OrderHelper(
                        users.get(5),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(8, 0),
                        10
                ),
                new OrderHelper(
                        users.get(6),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(23, 0),
                        10
                ),
                new OrderHelper(
                        users.get(7),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(19, 0, 1),
                        10
                ),
                new OrderHelper(
                        users.get(8),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(17, 59),
                        10
                ),
                new OrderHelper(
                        users.get(9),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 24),
                        10
                ),
                new OrderHelper(
                        users.get(10),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(15, 0),
                        10
                ),
                new OrderHelper(
                        users.get(10),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(19, 0),
                        0
                ),
                new OrderHelper(
                        users.get(11),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(13, 0),
                        10
                ),
                new OrderHelper(
                        users.get(11),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 13),
                        0
                ),
                new OrderHelper(
                        users.get(12),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 23),
                        0
                ),
                new OrderHelper(
                        users.get(13),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(15, 0),
                        10
                ),
                new OrderHelper(
                        users.get(13),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 22),
                        0
                ),
                new OrderHelper(
                        users.get(14),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(13, 0),
                        10
                ),
                new OrderHelper(
                        users.get(15),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(12, 0),
                        10
                ),
                new OrderHelper(
                        users.get(16),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(11, 0),
                        10
                ),
                new OrderHelper(
                        users.get(17),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(17, 0),
                        10
                ),
                new OrderHelper(
                        users.get(17),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 19),
                        0
                ),
                new OrderHelper(
                        users.get(18),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(17, 59),
                        10
                ),
                new OrderHelper(
                        users.get(19),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 0),
                        10
                ),
                new OrderHelper(
                        users.get(20),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(16, 0),
                        10
                ),
                new OrderHelper(
                        users.get(20),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 20),
                        0
                ),
                new OrderHelper(
                        users.get(21),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(17, 0),
                        10
                ),
                new OrderHelper(
                        users.get(21),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 18),
                        0
                ),
                new OrderHelper(
                        users.get(3),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 15),
                        10
                ),
                new OrderHelper(
                        users.get(4),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 0),
                        10
                ),
                new OrderHelper(
                        users.get(7),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(19, 0),
                        10
                ),
                new OrderHelper(
                        users.get(3),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 30),
                        10
                ),
                new OrderHelper(
                        users.get(3),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 30),
                        10
                ),
                new OrderHelper(
                        users.get(3),
                        Arrays.asList(new MenuItem(MenuItemType.Gelato, "Gelato alla fragola", 10)),
                        LocalTime.of(18, 30),
                        10
                )
        );
        
        assertOrders(orders);
    }
    
    private void assertOrders(List<OrderHelper> orders)
    {
        for(OrderHelper order: orders)
        {
            double actual = takeAwayBill.getOrderPrice(order.itemsOrdered, order.user, order.orderingTime);
            assertEquals(order.expectedTotalPrice, actual, delta);
        }
    }
    
    private static class OrderHelper
    {
        public final User user;
        public final List<MenuItem> itemsOrdered;
        public final LocalTime orderingTime;
        public final double expectedTotalPrice;
    
        public OrderHelper(User user, List<MenuItem> itemsOrdered, LocalTime orderingTime, double expectedTotalPrice)
        {
            this.user = user;
            this.itemsOrdered = itemsOrdered;
            this.orderingTime = orderingTime;
            this.expectedTotalPrice = expectedTotalPrice;
        }
    }
}
