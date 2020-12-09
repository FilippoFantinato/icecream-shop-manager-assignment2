////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model.exceptions.menuitemexceptions;

public class IllegalMenuItemPriceException extends IllegalArgumentException
{
    public IllegalMenuItemPriceException(double price)
    {
        super("Il prezzo del prodotto deve essere >=0, invece è " + price);
    }
}
