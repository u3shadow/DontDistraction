package com.u3.dontdistraction.model;

/**
 * Created by U3 on 2015/5/29.
 */
public class ProblemEntity {
    private String problem;
    private String answer;

    public ProblemEntity(String problem, String answer) {
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
