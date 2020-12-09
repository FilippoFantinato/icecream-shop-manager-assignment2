////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model.exceptions.menuitemexceptions;

public class IllegalMenuItemNameException extends IllegalArgumentException
{
    public IllegalMenuItemNameException()
    {
        super("Il nome del prodotto non può essere vuoto o null");
    }
}
