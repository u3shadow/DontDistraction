package com.u3.dontdistraction.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by U3 on 2015/5/29.
 */
public class PenaltyMessage {
    private int msgNumMax = 1;
    private int msgNumMin = 0;
    private int msgNumber = 0;
    private static List<String> msgList = new ArrayList<String>() {
        {
            add("#勿扰机#我是傻逼，我又看书的时候玩手机了");
            add("#勿扰机#我是傻逼，我又看书的时候玩手机了");
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
