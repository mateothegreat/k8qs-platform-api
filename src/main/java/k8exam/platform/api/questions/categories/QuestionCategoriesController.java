package k8exam.platform.api.questions.categories;

import k8exam.platform.api.common.Patterns;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/questions/categories")
public class QuestionCategoriesController {

    private final QuestionCategoriesService questionCategoriesService;

    public QuestionCategoriesController(final QuestionCategoriesService questionCategoriesService) {

        this.questionCategoriesService = questionCategoriesService;

    }

    @GetMapping("/list")
    ResponseEntity<Page<QuestionCategory>> findAll(Pageable pageable) {

        return new ResponseEntity<>(questionCategoriesService.findAll(pageable), HttpStatus.OK);

    }

    @GetMapping(value = Patterns.UUIDv4)
    ResponseEntity<QuestionCategory> getByUUID(@PathVariable("uuid") UUID uuid) {

        Optional<QuestionCategory> optionalCategory = questionCategoriesService.getByUUID(uuid);

        return optionalCategory.map(category -> new ResponseEntity<>(category, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping(value = Patterns.UUIDv4)
    ResponseEntity<Boolean> deleteByUUID(@PathVariable("uuid") UUID uuid) {

        return questionCategoriesService.deleteByUUID(uuid) > 0 ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    ResponseEntity<QuestionCategory> create(@RequestBody QuestionCategoryCreate questionCategoryCreate) {

        return new ResponseEntity<>(questionCategoriesService.create(questionCategoryCreate), HttpStatus.OK);

    }

}
