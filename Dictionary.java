import java.util.Iterator;
import java.util.Vector;
/**
   An interface for a dictionary with distinct search keys.
   @author Frank M. Carrano
   @version 3.0
*/
public class Dictionary<K,V>
{
    protected Vector<K> keys;
    protected Vector<V> values;
    
    public Dictionary()
    {
        keys = new Vector<K>();
        values = new Vector<V>();
    }

   /** Adds a new entry to this dictionary. If the given search
       key already exists in the dictionary, replaces the
       corresponding value.
       @param key an object search key of the new entry
       @param value an object associated with the search key
       @return either null if the new entry was added to the dictionary
               or the value that was associated with key if that value
               was replaced */
   public V add(K key, V value)
   {
      if (keys.contains(key)) {
          int location = keys.indexOf(key);
          V temp = values.get(location);  //old value
          values.set(location, value);
          return temp;
      } 
      else {
          keys.add(key);
          values.add(value);
          return null;  //there was no prior value for this key
      }
       
   }
   
   /** Removes a specific entry from this dictionary.
       @param key an object search key of the entry to be removed
       @return either the value that was associated with the search key
               or null if no such object exists */
   public V remove(K key)
   {
      if (keys.contains(key)) {
          int location = keys.indexOf(key);
          V temp = values.get(location);  //old value
          values.remove(location);
          keys.remove(location);
          return temp;
      } 
      else {
          return null;  //there was no prior value for this key
      }             
   }
   
   /** Retrieves from this dictionary the value associated with a given
       search key.
       @param key an object search key of the entry to be retrieved
       @return either the value that is associated with the search key
               or null if no such object exists */
   public V getValue(K key)
   {
      if (keys.contains(key)) {
          int location = keys.indexOf(key);
          V temp = values.get(location);  //old value
          return temp;
      } 
      else {
          return null;  //there was no prior value for this key
      }            
   }
   
   /** Sees whether a specific entry is in this dictionary.
       @param key an object search key of the desired entry
       @return true if key is associated with an entry in the
               dictionary */
   public boolean contains(K key)
   {
      return keys.contains(key);         
   }
   
   /** Creates an iterator that traverses all search keys in this
       dictionary.
       @return an iterator that provides sequential access to the search
               keys in the dictionary */
   public Iterator<K> getKeyIterator()
   {
      return keys.iterator();          
   }
   
   /** Creates an iterator that traverses all values in this dictionary.
       @return an iterator that provides sequential access to the values
               in this dictionary */
   public Iterator<V> getValueIterator()
   {
      return values.iterator();           
   }
   
   /** Sees whether this dictionary is empty.
       @return true if the dictionary is empty */
   public boolean isEmpty()
   {
      return keys.isEmpty();      
   }
   
   /** Gets the size of this dictionary.
       @return the number of entries (key-value pairs) currently
               in the dictionary */
   public int getSize()
   {
      return keys.size();          
   }
   /** Removes all entries from this dictionary. */
   public void clear()
   {
       keys.clear();
       values.clear();
   }
} // end DictionaryInterface