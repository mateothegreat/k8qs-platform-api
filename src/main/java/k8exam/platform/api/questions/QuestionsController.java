package k8exam.platform.api.questions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    ResponseEntity<Question> create(@RequestBody QuestionCreate questionCreate) {

        return new ResponseEntity<>(questionsService.saveOrUpdate(questionCreate), HttpStatus.OK);

    }

}
