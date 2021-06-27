package engine.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @Size(min = 2)
    @NotNull
    @OneToMany
    @JoinColumn(name = "QUIZ_ID", referencedColumnName = "ID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Option> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany
    @JoinColumn(name = "QUIZ_ID", referencedColumnName = "ID")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Answer> answer = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }
}
