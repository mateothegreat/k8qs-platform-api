package k8exam.platform.api.questions;

public enum QuestionType {

    BOOLEAN("BOOLEAN"),
    SINGLE_CHOICE("SINGLE_CHOICE"),
    MULTIPLE_CHOICE("MULTIPLE_CHOICE"),
    NUMBER("NUMBER");

    private final String type;

    QuestionType(String type) {

        this.type = type;

    }

}
