package engine;

import engine.db.QuizService;
import engine.dto.QuizDTO;
import engine.dto.QuizResponseDto;
import engine.dto.SubmitAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RESTController {

    private QuizService quizService;

    @PostMapping(value = "/api/quizzes")
    public QuizDTO postApiQuizzes(@RequestBody @Valid QuizDTO quiz) {
        quizService.save(QuizConverter.quizDtoToQuiz(quiz));
        return QuizConverter.quizToQuizDto(quizService.last());
    }

    @GetMapping(value = "/api/quizzes")
    public List<QuizDTO> getApiQuizzes() {
        return quizService.list().stream().map(QuizConverter::quizToQuizDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/quizzes/{id}")
    public QuizDTO getApiQuizzesId(@PathVariable int id, HttpServletResponse response) {
        try {
            return QuizConverter.quizToQuizDto(quizService.getQuizById(id));
        } catch (NullPointerException nullPointerException) {
            try {
                response.sendError(404);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
    }

    @PostMapping(value = "/api/quizzes/{id}/solve")
    public QuizResponseDto postApiQuizzesIdSolve(@PathVariable int id, @RequestBody SubmitAnswerDto answer, HttpServletResponse response) {
        var quiz = quizService.getQuizById(id);
        var correctAnswer = new int[quiz.getAnswer().size()];
        for (var i = 0; i < quiz.getAnswer().size(); i++) {
            correctAnswer[i] = quiz.getAnswer().get(i).getAnswer();
        }
        if (Arrays.equals(correctAnswer, answer.getAnswer())) {
            return new QuizResponseDto(true, "Congratulations, you're right!");
        } else {
            return new QuizResponseDto(false, "Wrong answer! Please, try again.");
        }
    }

    @PostMapping(value = "/api/quizzes/clear")
    public void postApiQuizzesClear() {
        quizService.clear();
    }

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }
}
