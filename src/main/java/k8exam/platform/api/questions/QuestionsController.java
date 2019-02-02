package k8exam.platform.api.questions;

import k8exam.platform.api.common.Patterns;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionsService questionsService;

    public QuestionsController(final QuestionsService questionsService) {

        this.questionsService = questionsService;

    }

    @GetMapping
    ResponseEntity<Page<Question>> findAll(Pageable pageable) {

        return new ResponseEntity<>(questionsService.findAll(pageable), HttpStatus.OK);

    }

    @GetMapping(value = Patterns.UUIDv4)
    ResponseEntity<Question> getByUUID(@PathVariable("uuid") UUID uuid) {

        Optional<Question> optionalQuestion = questionsService.getByUUID(uuid);

        return optionalQuestion.map(question -> new ResponseEntity<>(question, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }

    @PostMapping
    ResponseEntity<Question> create(@RequestBody QuestionCreate questionCreate, Principal principal) {

        Optional<Question> optionalQuestion = questionsService.saveOrUpdate(questionCreate, principal);

        return optionalQuestion.map(question -> new ResponseEntity<>(question, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED));

    }

    @DeleteMapping(value = Patterns.UUIDv4)
    ResponseEntity<Boolean> delete(@PathVariable("uuid") UUID uuid, Principal principal) {

        return questionsService.deleteByUUID(uuid, principal) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
