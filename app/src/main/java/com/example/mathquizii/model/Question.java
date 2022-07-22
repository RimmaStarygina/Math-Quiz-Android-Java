package com.example.mathquizii.model;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {
    String question;
    int enteredAnswer;
    Boolean right;

    public Question(String question, int enteredAnswer, Boolean right) {
        this.question = question;
        this.enteredAnswer = enteredAnswer;
        this.right = right;
    }

    public Boolean getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(right, question.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(right);
    }

    @Override
    public String toString() {
        String result;
        if(this.right == true) result = "right";
        else result = "wrong";
        return "\nQuestion: " + question +", your answer: " + enteredAnswer +" - "+ result+"\n\n";
    }
}
