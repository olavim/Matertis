
package com.github.tilastokeskus.matertis.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;


/**
 * A list populated with Pair objects.
 * 
 * @author tilastokeskus
 */
public class PairedList<T, E> implements List<Pair<T, E>> {    
    private final List<Pair<T, E>> list;

    public PairedList() {
        this.list = new ArrayList<>();
    }
    
    /**
     * Appends the specified elements as a {@link Pair} to the end of this list
     * (optional operation).
     * <p>
     * Lists that support this operation may place limitations on 
     * what elements may be added to this list. In particular, some lists will 
     * refuse to add null elements, and others will impose restrictions on the 
     * type of elements that may be added. List classes should clearly specify 
     * in their documentation any restrictions on what elements may be added.
     * 
     * @param t First element of the pair.
     * @param e Second element of the pair.
     * @return  true (as specified by {@link Collection#add})
     */
    public boolean add(T t, E e) {
        return this.list.add(new Pair<>(t, e));
    }    
    
    /**
     * Inserts the specified elements at the specified position in this list
     * (optional operation). Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     * 
     * @param index index at which the specified elements are to be inserted.
     * @param t     First element of the pair.
     * @param e     Second element of the pair.
     */
    public void add(int index, T t, E e) {
        this.list.add(index, new Pair<>(t, e));
    }
    
    /**
     * Returns a PairedList of such pairs that have the specified element as
     * that pair's first element.
     * 
     * @param t Element to search with.
     * @return  A list of matching pairs.
     */
    public PairedList<T, E> getByFirst(T t) {
        PairedList<T, E> pairs = new PairedList<>();
        
        for (Pair<T, E> pair : this.list) {
            if (pair.first.equals(t)) {
                pairs.add(pair);
            }
        }
        
        return pairs;
    }
    
    /**
     * Returns a PairedList of such pairs that have the specified element as
     * that pair's second element.
     * 
     * @param e Element to search with.
     * @return  A list of matching pairs.
     */
    public PairedList<T, E> getBySecond(E e) {
        PairedList<T, E> pairs = new PairedList<>();
        
        for (Pair<T, E> pair : this.list) {
            if (pair.second.equals(e)) {
                pairs.add(pair);
            }
        }
        
        return pairs;
    }
    
    /**
     * Returns a list consisting of all first elements of this list's pairs, in
     * the order they appear in this list.
     * 
     * @return A list of this list's first elements.
     */
    public List<T> getFirstElements() {
        List<T> l = new ArrayList<>();
        for (Pair<T, E> pair : this.list) {
            l.add(pair.first);
        }
        
        return l;
    }
    
    /**
     * Returns a list consisting of all second elements of this list's pairs, in
     * the order they appear in this list.
     * 
     * @return A list of this list's second elements.
     */
    public List<E> getSecondElements() {
        List<E> l = new ArrayList<>();
        for (Pair<T, E> pair : this.list) {
            l.add(pair.second);
        }
        
        return l;
    }

    @Override
    public boolean add(Pair<T, E> e) {
        return this.list.add(e);
    }

    @Override
    public void add(int index, Pair<T, E> element) {
        this.list.add(index, element);
    }

    @Override
    public Pair<T, E> get(int index) {
        return this.list.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.list.contains(o);
    }

    @Override
    public Iterator<Pair<T, E>> iterator() {
        return this.list.iterator();
    }

    @Override
    public Pair<T, E>[] toArray() {
        return this.list.toArray(new Pair[] {});
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.list.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.list.retainAll(c);
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public Pair<T, E> set(int index, Pair<T, E> element) {
        return this.list.set(index, element);
    }

    @Override
    public Pair<T, E> remove(int index) {
        return this.list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return this.list.lastIndexOf(o);
    }

    @Override
    public boolean addAll(Collection<? extends Pair<T, E>> c) {
        return this.list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Pair<T, E>> c) {
        return this.list.addAll(index, c);
    }

    @Override
    public ListIterator<Pair<T, E>> listIterator() {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<Pair<T, E>> listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override
    public PairedList<T, E> subList(int fromIndex, int toIndex) {
        PairedList<T, E> l = new PairedList<>();
        l.addAll(this.list.subList(fromIndex, toIndex));
        return l;
    }
    
    @Override
    public String toString() {
        return this.list.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PairedList<?, ?> other = (PairedList<?, ?>) obj;
        if (!Objects.equals(this.list, other.list)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.list);
        return hash;
    }
    
}
