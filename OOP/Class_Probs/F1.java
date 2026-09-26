import java.util.*;

abstract class Question {
    String question;
    String correctAnswer;

    Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    abstract boolean checkAnswer(String answer);
}

class MCQ extends Question {
    MCQ(String question, String correctAnswer) {
        super(question, correctAnswer);
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Attempt {
    private boolean submitted = false;
    private int correct = 0;
    private int total = 0;

    void answer(Question q, String answer) {
        if (!submitted) {
            total++;
            if (q.checkAnswer(answer))
                correct++;
            System.out.println("Question answered with '" + answer + "'.");
        }
    }

    void submit() {
        submitted = true;
    }

    void result() {
        System.out.println("Result: " + correct + "/" + total + " correct");
    }
}

class Examination {
    String name;
    Question[] questions;

    Examination(String name, Question[] questions) {
        this.name = name;
        this.questions = questions;
    }

    Attempt start() {
        System.out.println("Examination '" + name + "' started.");
        return new Attempt();
    }
}

public class F1 {
    public static void main(String[] args) {

        Question[] q = {
            new MCQ("Q1", "A"),
            new MCQ("Q2", "B")
        };

        Examination exam =
            new Examination("Math Quiz", q);

        Attempt attempt = exam.start();

        attempt.answer(q[0], "A");
        attempt.answer(q[1], "C");

        attempt.submit();

        System.out.println(
            "Examination '" + exam.name +
            "' submitted successfully."
        );

        attempt.result();
    }
}