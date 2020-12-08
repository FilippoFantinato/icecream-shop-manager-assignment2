////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model.exceptions.menuitemexceptions;

public class IllegalMenuItemTypeException extends IllegalArgumentException
{
    public IllegalMenuItemTypeException()
    {
        super("Il tipo del prodotto deve essere valido");
    }
}
