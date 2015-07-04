package com.u3.dontdistraction.other;

import com.u3.dontdistraction.model.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by U3 on 2015/5/29.
 */
public class Problems {
    private int promNumMax = 19;
    private int promNumMin = 0;
    private int promNumber = 0;
    private static List<Problem> problemList = new ArrayList<Problem>() {
        {
            add(new Problem("1 + 2", "3"));
            add(new Problem("白日依山尽", "黄河入海流"));
            add(new Problem("提出引力的科学家叫什么名字？", "牛顿"));
            add(new Problem("2 + 2", "4"));
            add(new Problem("2 * 2", "4"));
            add(new Problem("10 * 11", "110"));
            add(new Problem("锄禾日当午", "汗滴禾下土"));
            add(new Problem("相对论是谁提出的？", "爱因斯坦"));
            add(new Problem("劝君更尽一杯酒", "西出阳关无故人"));
            add(new Problem("中华人民共和国的建国时间是哪一年？", "1949"));
            add(new Problem("1 + 2", "3"));
            add(new Problem("白日依山尽", "黄河入海流"));
            add(new Problem("提出引力的科学家叫什么名字？", "牛顿"));
            add(new Problem("2 + 2", "4"));
            add(new Problem("2 * 2", "4"));
            add(new Problem("10 * 11", "110"));
            add(new Problem("锄禾日当午", "汗滴禾下土"));
            add(new Problem("相对论是谁提出的？", "爱因斯坦"));
            add(new Problem("劝君更尽一杯酒", "西出阳关无故人"));
            add(new Problem("中华人民共和国的建国时间是哪一年？", "1949"));
        }
    };
public Problems()
{
    Random random = new Random();
    int proNum = random.nextInt(promNumMax + 1);
    promNumber = proNum;
}
    public String getProblem() {

        return problemList.get(promNumber).getProblem();
    }

    public String getAnswer() {
        return problemList.get(promNumber).getAnswer();
    }
    public boolean isAnswerRight(String answer)
    {
        if(answer.equals(getAnswer()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
