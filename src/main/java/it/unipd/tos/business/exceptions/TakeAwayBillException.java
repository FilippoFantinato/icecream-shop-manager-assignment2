////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exceptions;

public class TakeAwayBillException extends RuntimeException
{
    public TakeAwayBillException(int size)
    {
        super("Il massimo numero di prodotti che si possono ordinare è 30, invece sono " + size);
    }
}
