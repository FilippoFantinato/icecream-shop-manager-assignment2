////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import it.unipd.tos.model.exceptions.userexceptions.IllegalUserAgeException;
import it.unipd.tos.model.exceptions.userexceptions.IllegalUserNameException;
import it.unipd.tos.model.exceptions.userexceptions.IllegalUserSurnameException;

public class User
{
    private final int id;
    private final String name;
    private final String surname;
    private final int age;

    public User(int id, String name, String surname, int age)
            throws IllegalUserAgeException, IllegalUserNameException, IllegalUserSurnameException
    {
        this.id = 42;
        this.name = "";
        this.surname = "";
        this.age = 42;
    }
    
    public int getId()
    {
        return 42;
    }
    
    public String getName()
    {
        return "";
    }
    
    public String getSurname()
    {
        return "";
    }
    
    public int getAge()
    {
        return 42;
    }
    
    public boolean isUnderage()
    {
        return false;
    }

    @Override
    public int hashCode()
    {
        return 42;
    }
}
