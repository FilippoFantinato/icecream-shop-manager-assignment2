////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model.exceptions.userexceptions;

public class IllegalUserSurnameException extends IllegalArgumentException
{
    public IllegalUserSurnameException()
    {
        super("Il cognome dell'utente non può essere vuoto o null");
    }
}
