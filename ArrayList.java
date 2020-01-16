import java.util.Arrays;
/**
   A class that implements the ADT list by using an array.
   The list is never full.
   @author Frank M. Carrano
   @version 3.0
*/
public class ArrayList<T> 
{
    private T[] myArray;   // array of list entries
    private int mySize;
    private static final int DEFAULT_INITIAL_CAPACITY = 25;  

    // public methods:  
    public ArrayList()
    {
        this(DEFAULT_INITIAL_CAPACITY);
    } // end default constructor

    public ArrayList(int initialCapacity)
    {
       mySize = 0;
       @SuppressWarnings("unchecked")       
       T[] tempArray = (T[]) new Object[initialCapacity];
       myArray = tempArray;
    } // end constructor

    public boolean isEmpty()
    {
        return mySize==0;
    } // end isEmpty
    
    
    public void add(T newEntry)
    {
        ensureCapacity();
        myArray[mySize] = newEntry;
        mySize++;
    } // end     add

    public boolean add(int newPosition, T newEntry)
    {
        if (newPosition >= 1 && newPosition <= mySize+1)
        {
            ensureCapacity();
            for (int i = mySize; i > newPosition-1; i--)
            {
                myArray[i] = myArray[i-1];
            }
            myArray[newPosition-1] = newEntry;
            mySize++;
            return true;
        }
        
        return false;
    } // end add

    public T remove(int givenPosition)
    {
        if (givenPosition >= 1 && givenPosition <= mySize)
        {
            T removed = myArray[givenPosition-1];
            
            for (int i = givenPosition; i < mySize; i++)
            {
                myArray[i-1] = myArray[i];
            }
            mySize--;
            return removed;
        }
        
        return null;
    } // end remove

    public void clear()
    {
        for (int k=0; k<mySize; k++)
            myArray[k] = null;
        mySize = 0;
    } // end clear

    public boolean replace(int givenPosition, T newEntry)
    {
        if (getEntry(givenPosition) != null)
        {
            myArray[givenPosition-1] = newEntry;
            return true;
        }
        
        return false;
    } // end replace

    public T getEntry(int givenPosition)
    {
        if (givenPosition <= mySize && givenPosition > 0)
            return myArray[givenPosition-1];
            
        return null;
    } // end getEntry

    public boolean contains(T anEntry)
    {
        boolean found = false;
        for (int index = 0; !found && (index < mySize); index++)
        {
            if (anEntry.equals(myArray[index]))
                found = true;
        } // end for

        return found;
    } // end contains

    public int getLength()
    {
        return mySize;
    } // end getLength

    private void ensureCapacity()
    {
       if (mySize == myArray.length)
           myArray = Arrays.copyOf(myArray, 2 * myArray.length);
    }

   public Object[] toArray()
   {
      Object[] result = new Object[mySize];
      for (int index = 0; index < mySize; index++)
      {
        result[index] = myArray[index];
      } // end for
      return result;
   } // end toArray
   

    /** Converts all the data in the bag into one big String
        @return a very long String of objects contained in bag */
    public String toString()
    {   
        int k;
        String result = "";
        for (k=0; k<mySize; k++)
          if (k<mySize-1)
            result += myArray[k] + ", "; // up to item before last
          else
            result += myArray[k];  // the last item
        return result;
    }  
    
   public int getPosition(T givenEntry)
   {
       int position = -1;
       for (int i = 0; i < mySize; i++)
       {
           if (myArray[i].equals(givenEntry))
                position = i+1;
       }
       return position;
   }
   
   public void moveToEnd(int givenPosition)
   {
       T temp = remove(givenPosition);
       add(temp);
   }
} // end AList