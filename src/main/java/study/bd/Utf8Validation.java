package study.bd;

/**
 * 首先我们看一下一个整数表示的字节中有哪些部分是需要处理的。
 *
 * 如果在处理一个 UTF-8 字符的开始，我们需要提取一个字节的前 NN 比特，其中 NN 不会超过 4，之后的比特就不需要处理了。
 * 如果是在处理一个 UTF-8 字符的过程中，我们只需要检查前两位是不是 10 就可以了。
 * 我们来看一下怎么用位操作来完成以上两个任务。
 *
 *
 * mask = 1 << 7
 * while mask & num:
 *     n_bytes += 1
 *     mask = mask >> 1
 * 首先我们创建了一个掩码，其值为 10000000。接下来会用这个掩码和整数做 逻辑与 操作，这样就可以知道有多少个 1 了。（记住，整数可能非常大，但我们只需要处理前 8 比特）
 *
 * 检查前两个比特是不是 10，我们可以用下面这两个掩码。
 *
 *
 * mask1 = 1 << 7
 * mask2 = 1 << 6
 *
 * if not (num & mask1 and not (num & mask2)):
 *     return False
 * 上面的代码可以高效地判断前两个比特是不是 10。如果不是，直接返回 False。
 *
 * @author liyanjie
 * @createTime 2021-04-23 17:44
 */
public class Utf8Validation {

    public boolean validUtf8(int[] data) {

        // Number of bytes in the current UTF-8 character
        int numberOfBytesToProcess = 0;

        // Masks to check two most significant bits in a byte.
        int mask1 = 1 << 7;
        int mask2 = 1 << 6;

        // For each integer in the data array.
        for(int i = 0; i < data.length; i++) {
            // If this is the case then we are to start processing a new UTF-8 character.
            if (numberOfBytesToProcess == 0) {
                int mask = 1 << 7;
                while ((mask & data[i]) != 0) {
                    numberOfBytesToProcess += 1;
                    mask = mask >> 1;
                }

                // 1 byte characters
                if (numberOfBytesToProcess == 0) {
                    continue;
                }

                // Invalid scenarios according to the rules of the problem.
                if (numberOfBytesToProcess > 4 || numberOfBytesToProcess == 1) {
                    return false;
                }

            } else {

                // data[i] should have most significant bit set and
                // second most significant bit unset. So, we use the two masks
                // to make sure this is the case.
                if (!((data[i] & mask1) != 0 && (mask2 & data[i]) == 0)) {
                    return false;
                }
            }

            // We reduce the number of bytes to process by 1 after each integer.
            numberOfBytesToProcess -= 1;
        }

        // This is for the case where we might not have the complete data for
        // a particular UTF-8 character.
        return numberOfBytesToProcess == 0;
    }

}
