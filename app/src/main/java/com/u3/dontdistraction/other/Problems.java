package com.u3.dontdistraction.other;



import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.u3.dontdistraction.model.Problem;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by U3 on 2015/5/29.
 */
public class Problems {
    public static String PROBLEM = "problem";
    private int promNumMax;
    private int promNumMin = 0;
    private int promNumber = 0;
    private Context mContext;
    private  List<Problem> problemList = new ArrayList<Problem>() {
        {
            add(new Problem("牛虻的父亲是什么职业？", "神父"));
            add(new Problem("雅典王子忒修斯勇闯克里特岛斩杀米诺牛的时候采用了以下哪种算法？\n（A: 动态规划；B: 穷举；C: 记忆化搜索；D: Dijkstra算法。）", "A"));
            add(new Problem("以下哪个概念和公孙龙的《指物论》中的“指”字含义相近？\nA，变量；B，数组；C，对象；D，指针", "A"));
            add(new Problem("蔺相如，司马相如；魏无忌，长孙无忌。下列哪一组对应关系与此类似\nA，PHP，Python；B，JSP，servlet；C，Java，Javascript；D，C，C++", "C"));
            add(new Problem("《公孙龙子》记载：“齐王之谓尹文曰：‘寡人甚好士，以齐国无士，何也？’尹文曰：‘愿闻大王之所谓士者。’齐王无以应。”这说明了齐王：\na，昏庸无道；b，是个结巴；c，不会下定义；d，不会定义自己的需求。", "d"));
            add(new Problem("惠施曾提出过“卵有毛”的命题，以下哪一项是导致这个错误命题的原因：\na，混淆了命名空间；b，引入了错误的包；c，衍生类未重载；d，调用了危险的指针。", "c"));
            add(new Problem("明朝时期张居正改革的一条鞭法的主要思想是：\na，面向过程；b，万物皆数；c，统一接口；d，泛型编程。", "c"));
            add(new Problem("下面哪种面向对象的方法可以让你变得富有？\na，继承；b，封装；c，多态；d，抽象。", "a"));
            add(new Problem("秦始皇吞并六国采用了以下哪种算法思想？\na， 递归；b，分治；c，迭代；d，模拟。", "b"));
            add(new Problem("印度电影《宝莱坞机器人之恋》中的机器人七弟采用的智能算法最有可能是以下哪一种？\na，神经网络；b，遗传算法；c，模拟退火；d，穷举算法。", "a"));
            add(new Problem("以下谁是二进制思想的最早提出者？\na，伏羲； b，姬昌； c，莱布尼茨；d，柏拉图。", "a"));
            add(new Problem("提出引力的科学家叫什么名字？", "牛顿"));
            add(new Problem("相对论是谁提出的？", "爱因斯坦"));
        }
    };
    private void setProblemList(List<Problem> problemList)
    {
        this.problemList = problemList;
    }
public Problems(Context context)
{
    mContext = context;
    try {
        getList();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    Random random = new Random();
    promNumber = problemList.size();
    int proNum = random.nextInt()%(promNumber);
    if(proNum < 0)
    {
     proNum *= -1;
    }
    promNumber = proNum;
}
    private void getList() throws JSONException {
        SharedPreferences preferences  = mContext.getSharedPreferences(Problems.PROBLEM, Context.MODE_PRIVATE);
        String json = preferences.getString(Problems.PROBLEM,"");
        if(!json.equals(""))
        {
            JSONArray arr = new JSONArray(json);
            Gson gson = new Gson();
            List<Problem> list = new ArrayList<Problem>();
            for(int i = 0;i < arr.length();i++)
            {
                list.add(gson.fromJson(arr.get(i).toString(),Problem.class));
            }
            setProblemList(list);
        }
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
