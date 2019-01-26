package k8exam.platform.api.answers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnswersService {

    private final AnswersRepository answersRepository;

    @Autowired
    public AnswersService(final AnswersRepository answersRepository) {

        this.answersRepository = answersRepository;

    }

    public Page<Answer> findAll(Pageable pageable) {

        return answersRepository.findAll(pageable);

    }

    public Optional<Answer> getByUUID(UUID uuid) {

        return answersRepository.getByUuid(uuid);

    }

    public Optional<Answer> getByValue(String value) {

        return answersRepository.getByValue(value);

    }

    public Answer create(AnswerCreate answerCreate) {

        Optional<Answer> optionalAnswer = answersRepository.getByValue(answerCreate.getValue());

        if (optionalAnswer.isPresent()) {

            optionalAnswer.get().setDescription(answerCreate.getDescription());

            return answersRepository.save(optionalAnswer.get());

        } else {

            return answersRepository.save(new Answer(UUID.randomUUID(), answerCreate.getValue(), answerCreate.getDescription()));

        }

    }

}
