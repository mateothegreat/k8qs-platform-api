package k8exam.platform.api.questions;

import lombok.Data;

@Data
public class QuestionCreateAnswer {

    private String  value;
    private String  description;
    private Boolean selected;
    private Double  score;
    
}
