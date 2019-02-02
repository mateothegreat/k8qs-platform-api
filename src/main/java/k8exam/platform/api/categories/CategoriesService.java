package k8exam.platform.api.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(final CategoriesRepository categoriesRepository) {

        this.categoriesRepository = categoriesRepository;

    }

    public Page<Category> findAll(Pageable pageable) {

        return categoriesRepository.findAll(pageable);

    }

    public Optional<Category> getByUUID(UUID uuid) {

        return categoriesRepository.getByUuid(uuid);

    }

    public Optional<Category> getByName(String name) {

        return categoriesRepository.getByName(name);

    }

    public Category create(CategoryCreate categoryCreate) {

        Category category = new Category();

        category.setUuid(UUID.randomUUID());
        category.setName(categoryCreate.getName());
        category.setDescription(categoryCreate.getDescription());
        
        return categoriesRepository.save(category);

    }

    public Category update(Category category) {

        Optional<Category> optionalCategory = categoriesRepository.getByName(category.getName());

        if (optionalCategory.isPresent()) {

            optionalCategory.get().setDescription(category.getDescription());

            return categoriesRepository.save(optionalCategory.get());

        } else {

            category.setUuid(UUID.randomUUID());

            return categoriesRepository.save(category);

        }

    }

}
