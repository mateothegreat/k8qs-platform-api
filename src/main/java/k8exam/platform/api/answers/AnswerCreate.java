package k8exam.platform.api.answers;

import lombok.Data;

@Data
public class AnswerCreate {

    private Boolean selected;
    private String  value;
    private String  description;
    private Double  score;

}
