package com.u3.dontdistraction.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by U3 on 2015/5/29.
 */
public class PenaltyMessage {
    private int msgNumMax = 19;
    private int msgNumMin = 0;
    private int msgNumber = 0;
    private static List<String> msgList = new ArrayList<String>() {
        {
            add("#勿扰机#我学习的时候就像哈登突破一样安静");
            add("#勿扰机#在看书，想当套马的汉子");
            add("#勿扰机#我要成为广场舞之王！");
            add("#勿扰机#我要成为海贼王，的男人");
            add("#勿扰机#解放区的天是晴朗的天");
            add("#勿扰机#什么样的节奏最啊最摇摆");
            add("#勿扰机#你是我心中最美的天籁，哟哟，切克闹");
            add("#勿扰机#鄙人乃隆中诸葛亮是也");
            add("#勿扰机#解放区的天是晴朗的天");
            add("#勿扰机#I'will be back(译：又玩手机了)");
            add("#勿扰机#我学习的时候就像哈登突破一样安静");
            add("#勿扰机#在看书，想当套马的汉子");
            add("#勿扰机#我要成为广场舞之王！");
            add("#勿扰机#我要成为海贼王，的男人");
            add("#勿扰机#解放区的天是晴朗的天");
            add("#勿扰机#什么样的节奏最啊最摇摆");
            add("#勿扰机#你是我心中最美的天籁，哟哟，切克闹");
            add("#勿扰机#鄙人乃隆中诸葛亮是也");
            add("#勿扰机#解放区的天是晴朗的天");
            add("#勿扰机#I'will be back(译：又玩手机了)");
        }
    };
    public String getMessage()
    {
        Random random = new Random();
        int msgNum = random.nextInt(msgNumMax + 1);
        msgNumber = msgNum;
        return msgList.get(msgNumber);
    }
}
