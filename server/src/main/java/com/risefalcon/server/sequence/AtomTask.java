package com.risefalcon.server.sequence;

import com.risefalcon.server.exception.RadixNotEqualException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtomTask implements Iterable<AtomTask> ,Comparable<AtomTask> {

    private int radix;
    private List<Integer> index;

    @Deprecated
    public boolean lessThan(AtomTask at) throws RadixNotEqualException {
        if (at == null)
            throw new NullPointerException();
        if (this.radix != at.radix)
            throw new RadixNotEqualException();
        return true;
    }

    @Deprecated
    public boolean equals(AtomTask at) throws RadixNotEqualException {
        if (at == null || at.getRadix() == 0
                || at.getIndex() == null)
            throw new NullPointerException();
        if (this.radix != at.radix)
            throw new RadixNotEqualException();
        if (this.index.size() != at.index.size())
            return false;
        for (int i = 0; i < index.size(); i++) {
            List<Integer> toCompare = at.getIndex();
            if (!this.index.get(i).equals(toCompare.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<AtomTask> iterator() {
        return new Iterator<AtomTask>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public AtomTask next() {
                List<Integer> nextIndex = new ArrayList<>(index);
                for (int i = 0; i < nextIndex.size(); i++) {
                    // 当前位尝试自增，如果触顶则循环向上找一位尝试自增（进位）
                    // 否则结束循环
                    int num = nextIndex.get(i);
                    num++;
                    if (num == radix) {
                        nextIndex.set(i, 0);
                        // 已经进到最高位，防止溢出在index尾部追加一位
                        if (i == nextIndex.size()-1) {
                            nextIndex.add(0);
                        }
                    } else {
                        nextIndex.set(i, num);
                        break;
                    }
                }
                return new AtomTask(radix, nextIndex);
            }
        };
    }

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Unless otherwise specified by the implementing class,
     * actions are performed in the order of iteration (if an iteration order
     * is specified).  Exceptions thrown by the action are relayed to the
     * caller.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer<? super AtomTask> action) {
        Iterable.super.forEach(action);
    }

    /**
     * Creates a {@link Spliterator} over the elements described by this
     * {@code Iterable}.
     *
     * @return a {@code Spliterator} over the elements described by this
     * {@code Iterable}.
     * @implSpec The default implementation creates an
     * <em><a href="Spliterator.html#binding">early-binding</a></em>
     * spliterator from the iterable's {@code Iterator}.  The spliterator
     * inherits the <em>fail-fast</em> properties of the iterable's iterator.
     * @implNote The default implementation should usually be overridden.  The
     * spliterator returned by the default implementation has poor splitting
     * capabilities, is unsized, and does not report any spliterator
     * characteristics. Implementing classes can nearly always provide a
     * better implementation.
     * @since 1.8
     */
    @Override
    public Spliterator<AtomTask> spliterator() {
        return Iterable.super.spliterator();
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param at the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(AtomTask at) {
        if (at == null || at.getRadix() == 0
                || at.getIndex() == null)
            throw new NullPointerException();
        if (this.radix != at.radix)
            throw new RuntimeException();
        if (this.index.size() != at.index.size())
            return this.index.size() - at.index.size();
        // 每位比较
        List<Integer> toCompare = at.getIndex();
        for (int i = index.size()-1; i >= 0; i--) {
            if (this.index.get(i) > toCompare.get(i)) {
                return 1;
            } else if (this.index.get(i) < toCompare.get(i)) {
                return -1;
            }
        }
        return 0;
    }
}
