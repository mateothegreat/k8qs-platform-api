package k8exam.platform.api.questions.categories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionCategoriesRepository extends PagingAndSortingRepository<QuestionCategory, Long> {

    Optional<QuestionCategory> getByUuid(UUID uuid);

    Optional<QuestionCategory> getByName(String name);

    int deleteByUuid(UUID uuid);

}
