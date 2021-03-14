package study.binarytree;

import java.util.List;

/**
 * Party最大快乐值
 * 1. 如果某员工要来，其直接下级不会来
 * 2. Party 整体快乐值是所有到场员工之和
 * 3. 目标： 快乐值最大
 */
public class MaxHappy {

    public void getMaxHappy(Employee boss) {

        process(boss);
    }

    private Info process(Employee boss) {
        if (boss.subs.isEmpty()) {
            return new Info(boss.happy, 0);
        }
        int yes = boss.happy;
        int no = 0;

        for (Employee sub : boss.subs) {
            Info subInfo = process(sub);
            // 当前员工来的最大快乐值 + 直接下属不来的最大快乐值
            yes += subInfo.no;
            // 当前员工不来 + 直接下属不来或者来的最大快乐值
            no += Math.max(subInfo.yes, subInfo.no);
        }
        return new Info(yes, no);
    }

    class Employee {
        public int happy;

        /**
         * 直接下级
         */
        public List<Employee> subs;

    }

    class Info {
        /**
         * 来参加的最大快乐值
         */
        public int yes;

        /**
         * 不来参加的最大快乐值
         */
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }
}
