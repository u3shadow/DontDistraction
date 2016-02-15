package com.u3.dontdistraction.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by U3 on 2015/5/29.
 */
public class PenaltyMessage {
    private int msgNumMin = 0;
    private static List<String> msgList = new ArrayList<String>() {
        {
            add("#勿扰机#我又看书的时候玩手机了,这个肥皂我承包了！");
            add("#勿扰机#我又看书的时候玩手机了,这个肥皂我承包了！");
        }
    };
    public String getMessage()
    {
        Random random = new Random();
        int msgNumMax = 1;
        int msgNum = random.nextInt(msgNumMax + 1);
        int msgNumber = msgNum;
        return msgList.get(msgNumber);
    }
}
