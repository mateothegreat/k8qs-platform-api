package k8exam.platform.api.questions;

import k8exam.platform.api.categories.Category;
import k8exam.platform.api.common.Status;
import lombok.Data;

import java.util.List;

@Data
public class QuestionCreate {

    private Status       status;
    private QuestionType type;
    private Category     category;
    private String       name;
    private String       description;

    private List<QuestionCreateAnswer> answers;

}
