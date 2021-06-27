package engine.dto;

public class QuizResponseDto {

    private boolean success;
    private String feedback;

    public QuizResponseDto(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public QuizResponseDto() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
