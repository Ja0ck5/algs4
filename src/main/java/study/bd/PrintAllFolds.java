package study.bd;

/**
 * 折纸，向上折，打印出第N 次折痕
 * N=1; down
 * N=2; down down up
 */
public class PrintAllFolds {

    public void printAllFolds(int n) {
        printProcess(1, n, true);
    }

    private void printProcess(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        printProcess(i + 1, n, true);
        System.out.println(down ? "down" : "up");
        printProcess(i + 1, n, false);
    }

}
