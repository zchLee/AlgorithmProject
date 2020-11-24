package com.lea.algorithm.interview;

/**
 * @author lzc
 * @create 2020-11-17 21:51
 * 有5座金矿，没座金矿的储量不同，需要参与挖掘的工人人数也不同。
 * 假如  400kg/5人  500kg/5人 200kg/3人   300kg/4人  350kg/3人
 * 挖掘工人人数一定 10人，问怎么求最优解
 * 思路：
 *  对于问题中的金矿来说，每一个都存在着 挖 和 不挖 两种选择。
 *  假设最后一个金矿注定不被挖，那么问题会转化成什么样子呢？
 *      显然问题会简化成 10个工人 在前4个金矿中做选择
 *  相应的，假设最后一个金矿一定会被挖，那么问题转化成什么样子呢？
 *      由于最后一个金矿一定会被挖，会消耗3个工人，问题就简化成了 7个人在前四个金矿做出最优选择
 *  这两种简化情况，被称为全局问题的两个最优子结构
 *  究竟哪一种最优子结构可以通向全局最优解呢？话句话说，最后一个镜框到底该不该挖？
 *  那就要看10个工人在前4个金矿的收益，和7个人在前4个金矿的收益+最后一个金矿的收益 谁大谁小了
 *
 *  同样的道理，对于前4个金矿的选择，还可以做进一步简化
 *  首先针对的10个工人4个金矿这个子结构，第四个金矿（300kg/4人 ） 可以选择挖与不挖。问题又简化成了两种更小的子结构
 *      1. 【不挖第4个金矿】    10个人在前3个金矿中做出最优选择
 *      2. 【挖第4个金矿】     （10 - 4 = 6） 6个人在前3个金矿中做出最优选择
 *  相应的对于7个工人4个金矿这个子结构，第四个金矿同样可以选择挖与不挖，根据第4个金矿的选择，可以简化成两种更小的子结构
 *      1. 7个人在前三个金矿中做出最优选择     【不挖第4个】
 *      2. 7-4=3  3个人在前3个金矿中做出最优选择
 *      ..........
 *      就这样，问题从1分成2，二分为四，一直把问题简化到0个工人或0个金矿的时候，这个时候的最优收益注定是0，这就确定了问题的边界。
 *  从上可以看出动态规划的要点：
 *      确定全局最优解和最优子结构之间的关系，以及问题的边界
 *  这个关系用数学公式来表示，就叫做状态转移方程式
 *  金矿数量设为n，工人数量设为w，金矿含金量设为g[]，金矿需要开采的人数设为数组p[],设F(w,n)为n个金矿、w个工人时的最优解
 *
 *  问题边界，金矿为0，工人数量为0的时候
 *      F(n,w) = 0 (n=0或w=0)
 *  当所剩下的工人不够挖掘当前金矿时，只有一种最优子结构
 *      F(n-1,w) (n>0,w<p[n-1])
 *  常规情况下，具有两种最优子结构（挖还是不挖）
 *      不挖最后一座金矿 和 挖最后一座金矿 哪个大 哪个就是最优解
 *      F(n,w)=max(F(n-1,w), F(n-1,w - p[n-1]) + g[n-1]) (n>0,w>=p[n-1])
 */
public class V10 {

    /**
     * 这种接法，会有重复的最优子结构，随着金矿增多，重复的次数也会增多
     *      从而引出另一种动态规划的另一种核心要点: 自底向上求解
     * @author: lzc
     * @date: 2020-11-23 22:13
     * @param workNum     工人数量
     * @param goldNum     可选金矿数量
     * @param needPerson     金矿开采所需的工人数量
     * @param golds     金矿储量
     * @return: int
     */
    public static int getBestGoldMining(int workNum, int goldNum, int[] needPerson, int[] golds) {
        if (workNum == 0 || goldNum == 0)
            return 0;
        if (workNum < needPerson[goldNum - 1])
            return getBestGoldMining(workNum, goldNum - 1, needPerson, golds);  // 工人人数小于 金矿所需人数，找下个金矿
        return Math.max(getBestGoldMining(workNum, goldNum - 1, needPerson, golds),
                getBestGoldMining(workNum - needPerson[goldNum - 1], goldNum - 1, needPerson, golds) + golds[goldNum - 1]);
    }

    /**
     * 此解法除第1行之外，每一行的结果都是由上一行数据推导出来的，，所以保存并不需要保存整个表格，无论金矿有多少座，我们只保存1行的数据，
     * 由此引出，空间最优解
     * 时间和空间复杂度是O(nw)
     * @author: lzc
     * @date: 2020-11-24 21:42
     * @param w     工人人数
     * @param p     开采金矿所需要的工人数量
     * @param g     金矿储量
     * @return: int
     */
    public static int getBestGoldMining(int w, int[] p, int[] g) {
//        创建表格
        int[][] resultTable = new int[g.length+1][w+1];
        // 填充表格
        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i-1]) {
                    resultTable[i][j] = resultTable[i-1][j];
                } else {
                    // 求常规最优子结构
                    resultTable[i][j] = Math.max(resultTable[i-1][j],
                            resultTable[i-1][j-p[i-1]] + g[i-1]);
                }
            }
        }
        return resultTable[g.length][w];
    }

    /**
     * 获得金矿收益最优解
     * 时间O(nw)
     * 空间复杂度O(n) 优化了空间复杂度
     * @author: lzc
     * @date: 2020-11-24 21:51
     * @param w
     * @param p
     * @param g
     * @return: int
     */
    public static int getBestGoldMiningMax(int w, int[] p, int[] g) {
//      创建当前结果
        int[] results = new int[w + 1];
        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 1; j--) {
//                j=代表人数
//                p[i-1] 要挖金矿人数
//                results[j-p[i-1]]+g[i-1]
                if (j >= p[i-1])        // 这个条件不能丢
                    results[j] = Math.max(results[j], results[j-p[i-1]]+g[i-1]);
            }
        }
        return results[w];
    }

    public static void main(String[] args) {
        int workNum = 10;
        int[] needPerson = {5,5,3,4,3};
        int[] golds = {400,500,200,300,350};
        System.out.println("搞明白思路的解法");
        System.out.println(getBestGoldMining(workNum, golds.length, needPerson, golds));;
        // 第二优接法
        System.out.println("第二优接法:");
        System.out.println(getBestGoldMining(workNum, needPerson, golds));
        // 第一优接法
        System.out.println("第一优接法:");
        System.out.println(getBestGoldMiningMax(workNum, needPerson, golds));;
    }

}
