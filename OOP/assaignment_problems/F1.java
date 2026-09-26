abstract class Question {
    String question;
    String answer;

    Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    abstract boolean checkAnswer(String ans);
}

class MCQ extends Question {
    MCQ(String question, String answer) {
        super(question, answer);
    }

    boolean checkAnswer(String ans) {
        return answer.equals(ans);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Attempt {
    Student student;
    int correct = 0;
    boolean submitted = false;

    Attempt(Student student) {
        this.student = student;
    }

    void answer(Question q, String ans) {
        if (!submitted) {
            if (q.checkAnswer(ans))
                correct++;
        }
    }

    void submit(int total) {
        submitted = true;
        System.out.println("Examination submitted successfully.");
        System.out.println("Result: " + correct + "/" + total + " correct");
    }
}

public class F1 {
    public static void main(String[] args) {
        Student s = new Student("John");

        Question q1 = new MCQ("Question 1", "A");
        Question q2 = new MCQ("Question 2", "B");

        Attempt a = new Attempt(s);

        System.out.println("Examination started by " + s.name);

        a.answer(q1, "A");
        System.out.println("Question 1 answered with 'A'");

        a.answer(q2, "C");
        System.out.println("Question 2 answered with 'C'");

        a.submit(2);
    }
}