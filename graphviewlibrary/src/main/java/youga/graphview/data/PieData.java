package youga.graphview.data;

import android.support.annotation.ColorInt;

/**
 * Created by YougaKing on 2017/3/22.
 */

public class PieData {

    String name;
    int[] values;
    int[] colors;

    public PieData() {
    }

    public PieData(String name, int[] values, @ColorInt int... colors) {
        this.name = name;
        this.values = values;
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(@ColorInt int... colors) {
        this.colors = colors;
    }

    public float sumValue() {
        float sumValue = 0;
        for (float i : values) {
            sumValue += i;
        }
        return sumValue;
    }

    public float getAngel(int index) {
        float percentage = values[index] / sumValue();   // 百分比
        return percentage * 360;                 // 对应的角度
    }
}
