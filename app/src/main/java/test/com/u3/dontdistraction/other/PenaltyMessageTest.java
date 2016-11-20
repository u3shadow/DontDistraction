package test.com.u3.dontdistraction.other;

import com.u3.dontdistraction.result.PenaltyMessage;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ${U3} on 2016/3/15.
 */
public class PenaltyMessageTest {
    @Test
    public void get_penalty_message(){
        PenaltyMessage penaltyMessage = new PenaltyMessage();
       String msg =  penaltyMessage.getMessage();
        Assert.assertNotNull(msg);
    }
}
