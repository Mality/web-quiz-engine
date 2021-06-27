package engine;

import engine.db.Answer;
import engine.db.Option;
import engine.db.Quiz;
import engine.dto.QuizDTO;

import java.util.ArrayList;
import java.util.List;

public class QuizConverter {

    private QuizConverter() {
    }

    public static QuizDTO quizToQuizDto(Quiz quiz) {
        List<Option> optionList = quiz.getOptions();
        var options = new String[optionList.size()];
        for (var i = 0; i < optionList.size(); i++) {
            options[i] = optionList.get(i).getOption();
        }
        List<Answer> answerList = quiz.getAnswer();
        var answer = new int[answerList.size()];
        for (var i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i).getAnswer();
        }
        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getText(), options, answer);
    }

    public static Quiz quizDtoToQuiz(QuizDTO quizDTO) {
        List<Option> optionList = new ArrayList<>();
        for (String curOption : quizDTO.getOptions()) {
            var option = new Option();
            option.setOption(curOption);
            optionList.add(option);
        }
        List<Answer> answerList = new ArrayList<>();
        for (int curAnswer : quizDTO.getAnswer()) {
            var answer = new Answer();
            answer.setAnswer(curAnswer);
            answerList.add(answer);
        }
        var quiz = new Quiz();
        quiz.setText(quizDTO.getText());
        quiz.setTitle(quizDTO.getTitle());
        quiz.setOptions(optionList);
        quiz.setAnswer(answerList);
        return quiz;
    }

}
