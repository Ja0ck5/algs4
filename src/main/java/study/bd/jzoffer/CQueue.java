package study.bd.jzoffer;

import java.util.Stack;

/**
 * //用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
 * //功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * //
 * //
 * //
 * // 示例 1：
 * //
 * // 输入：
 * //["CQueue","appendTail","deleteHead","deleteHead"]
 * //[[],[3],[],[]]
 * //输出：[null,null,3,-1]
 * //
 * //
 * // 示例 2：
 * //
 * // 输入：
 * //["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * //[[],[],[5],[2],[],[]]
 * //输出：[null,-1,null,null,5,2]
 * //
 * //
 * // 提示：
 * //
 * //
 * // 1 <= values <= 10000
 * // 最多会对 appendTail、deleteHead 进行 10000 次调用
 * //
 * // Related Topics 栈 设计
 * // 👍 225 👎 0
 *
 *
 *  * Your CQueue object will be instantiated and called as such:
 *  * CQueue obj = new CQueue();
 *  * obj.appendTail(value);
 *  *
 *  https://github.com/afatcoder/LeetcodeTop/blob/master/bytedance/backend.md
 *  int param_2 = obj.deleteHead();
 *  */

class CQueue {

    Stack<Integer> s1;
    Stack<Integer> s2;

    public CQueue() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }
    
    public void appendTail(int value) {
        s1.push(value);
    }
    
    public int deleteHead() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        if(s2.isEmpty()){
            return -1;
        }
        return s2.pop();
    }
}