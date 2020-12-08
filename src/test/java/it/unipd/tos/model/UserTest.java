////////////////////////////////////////////////////////////////////
// FILIPPO FANTINATO 1218816
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import it.unipd.tos.model.exceptions.userexceptions.IllegalUserAgeException;
import it.unipd.tos.model.exceptions.userexceptions.IllegalUserNameException;
import it.unipd.tos.model.exceptions.userexceptions.IllegalUserSurnameException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UserTest
{
    @Test
    public void testConstructor_ValidParameters_ReturnUserWithThoseParameters()
    {
        int id = 0;
        String name = "Filippo", surname = "Fantinato";
        int age = 21;
        
        User user = new User(id, name, surname, age);
        
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(age, user.getAge());
    }
    
    @Test(expected = IllegalUserNameException.class)
    public void testConstructor_NullName_ThrowsException()
            throws IllegalUserNameException
    {
        int id = 0;
        String name = null, surname = "Fantinato";
        int age = 21;
        
        User user = new User(id, name, surname, age);
        
        assertEquals(name, user.getName());
    }
    
    @Test(expected = IllegalUserNameException.class)
    public void testConstructor_BlankName_ThrowsException()
            throws IllegalUserNameException
    {
        int id = 0;
        String name = "    ", surname = "Fantinato";
        int age = 21;
        
        User user = new User(id, name, surname, age);
        
        assertEquals(name, user.getName());
    }
    
    @Test(expected = IllegalUserSurnameException.class)
    public void testConstructor_NullSurname_ThrowsException()
            throws IllegalUserSurnameException
    {
        int id = 0;
        String name = "Filippo", surname = null;
        int age = 21;
        
        User user = new User(id, name, surname, age);
        
        assertEquals(surname, user.getSurname());
    }
    
    @Test(expected = IllegalUserSurnameException.class)
    public void testConstructor_BlankSurname_ThrowsException()
            throws IllegalUserSurnameException
    {
        int id = 0;
        String name = "Filippo", surname = "     ";
        int age = 21;
        
        User user = new User(id, name, surname, age);
        
        assertEquals(surname, user.getSurname());
    }
    
    @Test(expected = IllegalUserAgeException.class)
    public void testConstructor_NegativeAge_ThrowsException()
            throws IllegalUserAgeException
    {
        int id = 0;
        String name = "Filippo", surname = "Fantinato";
        int age = -21;
        
        User user = new User(id, name, surname, age);
        
        assertEquals(age, user.getAge());
    }
    
    @Test
    public void testIsUnderage_UseUnderageAge_ReturnTrue()
    {
        int id = 0;
        String name = "Filippo", surname = "Fantinato";
        int age = 17;
        
        User user = new User(id, name, surname, age);
        
        assertTrue(user.isUnderage());
    }
    
    @Test
    public void testIsUnderage_UseNotUnderageAge_ReturnFalse()
    {
        int id = 0;
        String name = "Filippo", surname = "Fantinato";
        int age = 18;
        
        User user = new User(id, name, surname, age);
        
        assertFalse(user.isUnderage());
    }
    
    @Test
    public void testHashCode_NormalState_ReturnIdValue()
    {
        int id = 0;
        String name = "Filippo", surname = "Fantinato";
        int age = 21;
        
        User user = new User(id, name, surname, age);
        
        assertEquals(id, user.hashCode());
    }
}