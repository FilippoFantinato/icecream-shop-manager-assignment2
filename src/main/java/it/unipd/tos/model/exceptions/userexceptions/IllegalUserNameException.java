////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model.exceptions.userexceptions;

public class IllegalUserNameException extends IllegalArgumentException
{
    public IllegalUserNameException()
    {
        super("Il nome dell'utente non può essere vuoto o null");
    }
}
