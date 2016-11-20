package com.u3.dontdistraction.screenlock.model;

/**
 * Created by U3 on 2015/5/29.
 */
public class Problem {
    private String problem;
    private String answer;
    public Problem()
    {}
    public Problem(String problem, String answer) {
        this.problem = problem;
        this.answer = answer;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
