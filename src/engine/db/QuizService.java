package engine.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class QuizService {

    private QuizRepository quizRepository;

    public List<Quiz> list() {
        return quizRepository.findAll();
    }

    public Quiz last() {
        List<Quiz> quizzes = list();
        return quizzes.get(quizzes.size() - 1);
    }

    public void save(Quiz quiz) {
        quizRepository.save(quiz);
        var logger = Logger.getLogger("QuizService");
        logger.info("Quiz " + quiz.getTitle() + " saved");
    }

    public Quiz getQuizById(long id) {
        return quizRepository.findQuizById(id);
    }

    public void clear() {
        quizRepository.deleteAll();
    }

    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
}
