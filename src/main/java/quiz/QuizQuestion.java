package quiz;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuizQuestion {

    private String question;
    private Capital correctAnswer;
    private List<Capital> distractors;

    public QuizQuestion(String question, Capital correctAnswer, List<Capital> distractors) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.distractors = distractors;
    }

    public List<Capital> getAnswerOptions() {
        List<Capital> options = new ArrayList<>(distractors);
        options.add(correctAnswer);
        return options;
    }
}

