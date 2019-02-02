package k8exam.platform.api.categories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriesRepository extends PagingAndSortingRepository<Category, Long> {

    Optional<Category> getByUuid(UUID uuid);

    Optional<Category> getByName(String name);

}
