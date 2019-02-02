package k8exam.platform.api.questions;

import k8exam.platform.api.common.Status;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class QuestionCreate {

    private Status       status;
    private QuestionType type;
    private UUID         category;
    private String       name;
    private String       description;

    private List<QuestionCreateAnswer> answers;

}
