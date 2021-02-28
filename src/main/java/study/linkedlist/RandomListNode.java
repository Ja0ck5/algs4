package study.linkedlist;

public class RandomListNode {

    public int label;

    public RandomListNode next;

    public RandomListNode random;

    public RandomListNode(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public RandomListNode getNext() {
        return next;
    }

    public void setNext(RandomListNode next) {
        this.next = next;
    }

    public RandomListNode getRandom() {
        return random;
    }

    public void setRandom(RandomListNode random) {
        this.random = random;
    }
}
