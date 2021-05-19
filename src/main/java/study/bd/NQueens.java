package study.bd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * x & (-x) 可以获得 x 的二进制表示中的最低位的 1 的位置
 *
 * x & (x - 1) 可以将 x 的二进制表示中的最低位的 1 置成 0
 *
 *
 * @author liyanjie
 * @createTime 2021-05-18 17:15
 */
public class NQueens {


    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> solutions = new ArrayList<>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int slash, int backslash) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            // 1<<n-1 是为了转化一个长度为n的，每位上都是1的二进制数，用于定位可以放置皇后的位置
            // 上面我们用 1 表示不可选的位置，但是这里我们取反后，用1表示可选的位置
            int availablePositions = ((1 << n) - 1) & (~(columns | slash | backslash));
            while (availablePositions != 0) {
                // 定位最后一个1的位置，这个操作可以自己手写验证一下（不要忘了把负数转成补码）
                // 这个定位的意思是，生成的这个二进制数只有最后一个1还为1，其他位都变成了0
                int position = availablePositions & (-availablePositions);
                // 将这一位从可选取的位中移除
                // 减1把最后一个1拆成后面的多个1，再经过一次与操作把这些多出来的1全部清除
                availablePositions = availablePositions & (availablePositions - 1);
                // 这个方法是统计一个二进制数中所有的“1”的个数
                int column = Integer.bitCount(position - 1);
                // 找到第几列
                // 将这个位置添加到记录数组中
                queens[row] = column;
                // 沿着这个位置向下搜索，可选行和可选列的直接在参数上变化即可，这样就不需要手动重置状态了
                // columns | position, 假设 position = 01, columns = 00, columns | position  == 01
                // (slash | position) << 1, 假设 position = 01, slash = 00, 则 (slash | position) << 1 == 10， 即 01
                //                                                                                               10 ((slash | position) << 1) 则为下一深度(下一行的 斜线(标志)位置)
                //(backslash | position) >> 1 同理，下一行反斜线上的(标志)位置
                solve(solutions, queens, n, row + 1, columns | position, (slash | position) << 1, (backslash | position) >> 1);
                // 重置状态
                queens[row] = -1;
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }


    public static void main(String[] args) {
        int slash = 0, backslash = 0, col = 0;
        int n = 4;
        int anti = ~(col | slash | backslash);
        int nums = (1 << n) - 1;
        int availablePositions = nums & anti;
        System.out.println("anti : " + anti + " " + Integer.toBinaryString(anti));
        System.out.println("nums : " + nums + " " + Integer.toBinaryString(nums));
        System.out.println("availablePositions : " + availablePositions + " " + Integer.toBinaryString(availablePositions));

        // 从最右一个合法位置开始遍历，此时去的最右的有效的一列位置，取得一个合法位置
        int currentCol = availablePositions & (-availablePositions);
        System.out.println("currentCol : " + currentCol + ", bs:" + Integer.toBinaryString(currentCol));
        System.out.println("col : " + col + " :" + Integer.toBinaryString(col));
        slash = (slash | currentCol) << 1;
        System.out.println(slash + " " + Integer.toBinaryString(slash));
        backslash = (backslash | currentCol) >> 1;
        System.out.println(backslash + " " + Integer.toBinaryString(backslash));
        availablePositions = availablePositions & (availablePositions - 1);
        System.out.println("availablePositions : " + availablePositions + " " + Integer.toBinaryString(availablePositions));

        col = (col | currentCol);

        anti = ~(col | slash | backslash);
        nums = (1 << n) - 1;
        availablePositions = nums & anti;
        System.out.println("next anti : " + anti + " " + Integer.toBinaryString(anti));
        System.out.println("next nums : " + nums + " " + Integer.toBinaryString(nums));
        System.out.println("next availablePositions : " + availablePositions + " " + Integer.toBinaryString(availablePositions));

        int columnNum=Integer.bitCount(currentCol-1);

        System.out.println(columnNum);

    }

}
