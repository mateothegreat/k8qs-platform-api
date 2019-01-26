package k8exam.platform.api.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionsService(final QuestionsRepository questionsRepository) {

        this.questionsRepository = questionsRepository;

    }

    public Page<Question> findAll(Pageable pageable) {

        return questionsRepository.findAll(pageable);

    }

    public Optional<Question> getByUUID(UUID uuid) {

        return questionsRepository.getByUuid(uuid);

    }

    public Optional<Question> getByName(String name) {

        return questionsRepository.getByName(name);

    }

    public Question saveOrUpdate(QuestionCreate questionCreate) {

        Optional<Question> optionalQuestion = getByName(questionCreate.getName());

        if (optionalQuestion.isPresent()) {

            optionalQuestion.get().setCategory(questionCreate.getCategory());
            optionalQuestion.get().setQuestionType(questionCreate.getType());
            optionalQuestion.get().setDescription(questionCreate.getDescription());

            return questionsRepository.save(optionalQuestion.get());

        } else {

            Question question = new Question();

            question.setUuid(UUID.randomUUID());
            question.setName(questionCreate.getName());
            question.setDescription(questionCreate.getDescription());
            question.setCategory(questionCreate.getCategory());
            question.setQuestionType(questionCreate.getType());

            return questionsRepository.save(question);

        }

    }

}
