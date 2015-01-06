package com.github.tilastokeskus.matertis.util;

/**
 * Interface defining functionality for binding an integer key to an object.
 * 
 * @author tilastokeskus
 */
public interface KeyBinder<T> {
    
    /**
     * Returns the object that the key has been bound to.
     * @return the bound object.
     */
    T getBinding();
    
    /**
     * Returns the key to which the object has been bound to.
     * @return the binding.
     */
    int getKey();
    
    /**
     * Sets the key to which the object should be bound to.
     * 
     * @param key key to bound the object to.
     */
    void setKey(int key);
}
