package k8exam.platform.api.tags;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagsController {

    private final TagsService tagsService;

    public TagsController(final TagsService tagsService) {

        this.tagsService = tagsService;

    }

    @GetMapping
    ResponseEntity<Page<Tag>> getAll(Pageable pageable) {

        return new ResponseEntity<>(tagsService.getAll(pageable), HttpStatus.OK);

    }

    @PostMapping
    ResponseEntity<Tag> create(@RequestBody TagCreate tagCreate) {

        Optional<Tag> optionalTag = tagsService.create(tagCreate);

        return optionalTag.map(t -> new ResponseEntity<>(t, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.ALREADY_REPORTED));

    }

}
