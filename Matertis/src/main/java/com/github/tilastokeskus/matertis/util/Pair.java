
package com.github.tilastokeskus.matertis.util;
    
import java.util.Objects;

public class Pair<T, E> {     
    public final T first;
    public final E second;

    /**
     * Constructs a pair with given first and second element.
     * 
     * @param t First element.
     * @param e Second element.
     */
    public Pair(T t, E e) {
        this.first = t;
        this.second = e;
    }

    @Override
    public String toString() {
        return "1: " + first.toString() + ", 2: " + second.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (!Objects.equals(this.first, other.first)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.first);
        hash = 97 * hash + Objects.hashCode(this.second);
        return hash;
    }

}