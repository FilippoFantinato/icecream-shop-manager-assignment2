////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model.exceptions.userexceptions;

public class IllegalUserAgeException extends IllegalArgumentException
{
    public IllegalUserAgeException(int age)
    {
        super("L'età dell'utente deve essere >=0, invece è " + age);
    }
}
