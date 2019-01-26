package k8exam.platform.api.answers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
public class AnswersController {

    private final AnswersService answersService;

    public AnswersController(final AnswersService answersService) {

        this.answersService = answersService;

    }

    @GetMapping
    ResponseEntity<Page<Answer>> findAll(Pageable pageable) {

        return new ResponseEntity<>(answersService.findAll(pageable), HttpStatus.OK);

    }

    @PostMapping
    ResponseEntity<Answer> create(@RequestBody AnswerCreate answerCreate) {

        return new ResponseEntity<>(answersService.create(answerCreate), HttpStatus.OK);

    }

}
