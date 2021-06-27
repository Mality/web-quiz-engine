package engine.dto;

public class SubmitAnswerDto {

    private int[] answer;

    public SubmitAnswerDto(int[] answer) {
        this.answer = answer;
    }

    public SubmitAnswerDto() {
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
