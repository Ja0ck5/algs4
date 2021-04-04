package study.jdk;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

public class Test {


    public static void main(String[] args) {

        System.out.println(tableSizeFor(15));
        System.out.println(tableSizeFor(17));
        System.out.println(tableSizeFor(31));
        System.out.println(tableSizeFor(33));

        System.out.println(1 << 30);
        System.out.println(1 << 31);

    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
