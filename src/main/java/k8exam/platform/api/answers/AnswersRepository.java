package k8exam.platform.api.answers;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswersRepository extends PagingAndSortingRepository<Answer, Long> {

    Optional<Answer> getByUuid(UUID uuid);

    Optional<Answer> getByValue(String value);

}
