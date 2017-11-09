package com.u3.dontdistraction.result;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by u3-linux on 11/9/17.
 */

public class ResultEntity extends BaseObservable{
    private String resultPicId;
    private String learnResultString;
    private String okButtonString;
    private String noButtonString;
    private boolean isSuccessLearn;

    @Bindable
    public String getResultPicId() {
        return resultPicId;
    }

    public void setResultPicId(String resultPicId) {
        this.resultPicId = resultPicId;
    }

    @Bindable
    public String getLearnResultString() {
        return learnResultString;
    }

    public void setLearnResultString(String learnResultString) {
        this.learnResultString = learnResultString;
    }

    @Bindable
    public String getOkButtonString() {
        return okButtonString;
    }

    public void setOkButtonString(String okButtonString) {
        this.okButtonString = okButtonString;
    }

    @Bindable
    public String getNoButtonString() {
        return noButtonString;
    }

    public void setNoButtonString(String noButtonString) {
        this.noButtonString = noButtonString;
    }

    public boolean isSuccessLearn() {
        return isSuccessLearn;
    }

    @Bindable
    public void setSuccessLearn(boolean successLearn) {
        isSuccessLearn = successLearn;
    }
}
