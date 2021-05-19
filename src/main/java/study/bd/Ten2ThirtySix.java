package study.bd;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyanjie
 * @createTime 2021-05-19 14:47
 */
public class Ten2ThirtySix {

    private static final String X36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String[] X36_ARRAY = "0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");

    public static String tenTo36(int num) {
        StringBuffer sBuffer = new StringBuffer();
        if (num == 0) {
            sBuffer.append("0");
        }
        while (num > 0) {
            sBuffer.append(X36_ARRAY[num % 36]);
            num = num / 36;
        }
        return sBuffer.reverse().toString();
    }

    public static int thirtysixToTen(String string) {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < X36.length(); i++) {
            map.put(X36.charAt(i), i);
        }

        int size = string.length();
        int num = 0;
        for (int i = 0; i < size; i++) {
            String char2str = String.valueOf(string.charAt(i)).toUpperCase();
            num = (int) (map.get(char2str.charAt(0)) * Math.pow(36, size - i - 1) + num);
        }

        return num;
    }
}
