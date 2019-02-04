package k8exam.platform.api.questions.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionCategoriesService {

    private final QuestionCategoriesRepository questionCategoriesRepository;

    @Autowired
    public QuestionCategoriesService(final QuestionCategoriesRepository questionCategoriesRepository) {

        this.questionCategoriesRepository = questionCategoriesRepository;

    }

    public Page<QuestionCategory> findAll(Pageable pageable) {

        return questionCategoriesRepository.findAll(pageable);

    }

    public Optional<QuestionCategory> getByUUID(UUID uuid) {

        return questionCategoriesRepository.getByUuid(uuid);

    }

    public Optional<QuestionCategory> getByName(String name) {

        return questionCategoriesRepository.getByName(name);

    }

    public QuestionCategory create(QuestionCategoryCreate questionCategoryCreate) {

        QuestionCategory questionCategory = new QuestionCategory();

        questionCategory.setUuid(UUID.randomUUID());
        questionCategory.setName(questionCategoryCreate.getName());
        questionCategory.setDescription(questionCategoryCreate.getDescription());

        return questionCategoriesRepository.save(questionCategory);

    }

    public QuestionCategory update(QuestionCategory questionCategory) {

        Optional<QuestionCategory> optionalCategory = questionCategoriesRepository.getByName(questionCategory.getName());

        if (optionalCategory.isPresent()) {

            optionalCategory.get().setDescription(questionCategory.getDescription());

            return questionCategoriesRepository.save(optionalCategory.get());

        } else {

            questionCategory.setUuid(UUID.randomUUID());

            return questionCategoriesRepository.save(questionCategory);

        }

    }

    @Transactional
    public int deleteByUUID(UUID uuid) {

        return questionCategoriesRepository.deleteByUuid(uuid);

    }

}
