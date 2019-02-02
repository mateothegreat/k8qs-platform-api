package k8exam.platform.api.categories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(final CategoriesService categoriesService) {

        this.categoriesService = categoriesService;

    }

    @GetMapping
    ResponseEntity<Page<Category>> findAll(Pageable pageable) {

        return new ResponseEntity<>(categoriesService.findAll(pageable), HttpStatus.OK);

    }

    @PostMapping
    ResponseEntity<Category> create(@RequestBody CategoryCreate categoryCreate) {

        return new ResponseEntity<>(categoriesService.create(categoryCreate), HttpStatus.OK);

    }

}
