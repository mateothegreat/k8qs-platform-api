package k8exam.platform.api.questions;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionsRepository extends PagingAndSortingRepository<Question, Long> {

    Optional<Question> getByUuid(UUID uuid);

    Optional<Question> getByName(String name);

}
