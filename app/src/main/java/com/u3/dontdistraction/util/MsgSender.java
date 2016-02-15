package com.u3.dontdistraction.util;

import android.content.Context;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.StatusesAPI;
import com.sina.weibo.sdk.openapi.models.Status;
import com.u3.dontdistraction.R;
import com.u3.dontdistraction.other.PenaltyMessage;

/**
 * Created by U3 on 2015/5/29.
 */
public class MsgSender {
    private StatusesAPI mStatusesAPI;
    private Context mContext;
    private PenaltyMessage penaltyMessage;

    public MsgSender(Context context) {
        mContext = context;
        Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(mContext);
        mStatusesAPI = new StatusesAPI(mContext, Constants.APP_KEY, mAccessToken);
        penaltyMessage = new PenaltyMessage();
    }

    public void sendMsg() {
        mStatusesAPI.update(penaltyMessage.getMessage(), null, null, mListener);
    }

    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            if (response.startsWith("{\"created_at\"")) {
                // 调用 Status#parse 解析字符串成微博对象
                Status status = Status.parse(response);
                Toast.makeText(mContext,
                        mContext.getResources().getString(R.string.send_success),
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }
    };
}
