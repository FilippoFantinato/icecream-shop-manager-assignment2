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
            throws IllegalUserNameException, IllegalUserSurnameException, IllegalUserAgeException
    {
        if(name == null || name.trim().isEmpty())
        {
            throw new IllegalUserNameException();
        }
        if(surname == null || surname.trim().isEmpty())
        {
            throw new IllegalUserSurnameException();
        }
        if(age < 0)
        {
            throw new IllegalUserAgeException(age);
        }
        
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getSurname()
    {
        return surname;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public boolean isUnderage()
    {
        return age < 18;
    }

    @Override
    public int hashCode()
    {
        return id;
    }
}
