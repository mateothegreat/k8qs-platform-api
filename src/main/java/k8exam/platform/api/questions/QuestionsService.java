package k8exam.platform.api.questions;

import k8exam.platform.api.answers.Answer;
import k8exam.platform.api.categories.CategoriesService;
import k8exam.platform.api.categories.Category;
import k8exam.platform.api.users.User;
import k8exam.platform.api.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionsService {

    private final QuestionsRepository questionsRepository;
    private final CategoriesService   categoriesService;
    private final UsersService        usersService;

    @Autowired
    public QuestionsService(final QuestionsRepository questionsRepository,
                            final CategoriesService categoriesService,
                            final UsersService usersService) {

        this.questionsRepository = questionsRepository;
        this.categoriesService = categoriesService;
        this.usersService = usersService;

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

    public Optional<Question> saveOrUpdate(QuestionCreate questionCreate, Principal principal) {

        Optional<Question> optionalQuestion = getByName(questionCreate.getName());
        Optional<Category> optionalCategory = categoriesService.getByUUID(questionCreate.getCategory());
        Optional<User>     optionalUser     = usersService.getPrincipalUser(principal);

        if (optionalQuestion.isPresent() && optionalCategory.isPresent() && optionalUser.isPresent()) {

            optionalQuestion.get().getCategories().add(optionalCategory.get());
            optionalQuestion.get().setQuestionType(questionCreate.getType());
            optionalQuestion.get().setDescription(questionCreate.getDescription());
            optionalQuestion.get().setCreatedBy(optionalUser.get());

            return Optional.of(questionsRepository.save(optionalQuestion.get()));

        } else {

            if (optionalCategory.isPresent() && optionalUser.isPresent()) {

                Question question = new Question();

                question.setUuid(UUID.randomUUID());
                question.setName(questionCreate.getName());
                question.setDescription(questionCreate.getDescription());
                question.getCategories().add(optionalCategory.get());
                question.setQuestionType(questionCreate.getType());
                question.setCreatedBy(optionalUser.get());

                List<Answer> answers = new ArrayList<>();

                questionCreate.getAnswers().forEach(answer -> {

                    answers.add(new Answer(UUID.randomUUID(), answer.getSelected(), answer.getValue(), answer.getDescription(), answer.getScore()));

                });

                Question saved = questionsRepository.save(question);

                saved.setAnswers(answers);

                return Optional.of(questionsRepository.save(saved));

            }

        }

        return Optional.empty();

    }

    public Boolean deleteByUUID(UUID uuid, Principal principal) {

        Optional<Question> optionalQuestion = getByUUID(uuid);

        if (optionalQuestion.isPresent()) {

            questionsRepository.delete(optionalQuestion.get());

            return true;

        }

        return false;

    }

}
