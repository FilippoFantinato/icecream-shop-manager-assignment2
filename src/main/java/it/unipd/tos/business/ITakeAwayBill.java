////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.time.LocalTime;
import java.util.List;

public interface ITakeAwayBill
{
    double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime orderingTime)
            throws IllegalArgumentException;
}
