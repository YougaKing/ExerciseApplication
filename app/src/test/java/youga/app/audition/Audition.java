package youga.app.audition;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formattable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by YougaKing on 2017/3/13.
 */

public class Audition {


    @Test
    public void mainTest() {
//        smoke();
        killSelf(5, 3);
    }


    /**
     * 罗马人攻占了乔塔帕特，41人藏在一个山洞中躲过了这场浩劫。这41个人中，包括历史学家josephus和他的一个朋友。
     * 剩余的39个人为了表示不向罗马人屈服
     * ，决定集体自杀。大家决定了一个自杀方案，所有这41人围城一个圆圈，由第一个人开始顺时针报数，
     * 每报数为3的人就立刻自杀，然后由下一个人重新开始报数
     * 任然是每报数为3的人就立刻自杀,......,知道所有人都自杀死亡为止.
     * 约瑟夫和他的朋友并不像自杀，于是约瑟夫想到了一个计策，他们两个同样参数到自杀方案中，但是最后却躲过了自杀。请问是怎么做到的
     */


    public void killSelf(int number, int pre) {
        int alive = pre - 1;
        int[] args = new int[number];
        int index = 0;
        int pos = -1;
        int killNumber = 0;
        while (killNumber < number - alive) {
            do {
                pos = (pos + 1) % number;
                if (args[pos] == 0) {
                    index++;
                }
                if (index == pre) {
                    index = 0;
                    killNumber++;
                    break;
                }
            } while (true);
            args[pos] = killNumber;
        }
        for (int i = 0; i < number; i++) {
            if (args[i] == 0) {
                System.out.println("第" + i + "号存活");
            }
        }
        System.out.println(Arrays.toString(args));
    }

    /**
     * 60年代的哈尔滨。一天，一个小商店里来了一位不速之客。他对售货员说：我是南方人到哈尔滨出差，
     * 想带哈尔滨特产的“哈尔滨、迎春、葡萄”烟回去给大伙尝一尝。我现在只有3元钱，全都买烟。
     * ”当时的价格分别是0.29元、0.27元和0.23元。售货员经计算后，满足了他的要求。
     * 这位南方人每种烟买了几盒?
     */

    public void smoke() {

        double h = 0, y = 0, p = 0;
        double result = 0.29d * h + 0.27d * y + 0.23d * p;

        for (h = 0; h < 3 / 0.29; h++) {
            for (y = 0; y < 3 / 0.27; y++) {
                for (p = 0; p < 3 / 0.23; p++) {
                    if (0.29d * h + 0.27d * y + 0.23d * p == 3) {
                        System.out.println("h:" + h + "y:" + y + "p:" + p);
                    }
                }
            }
        }


    }

}
