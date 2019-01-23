package k8exam.platform.api.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TagsService {

    private final TagsRepository tagsRepository;

    @Autowired
    public TagsService(final TagsRepository tagsRepository) {

        this.tagsRepository = tagsRepository;

    }

    public Optional<Tag> getByUUID(UUID uuid) {

        return tagsRepository.getByUuid(uuid);

    }

    public Optional<Tag> getByName(String name) {

        return tagsRepository.getByName(name);

    }

    public Page<Tag> getAll(Pageable pageable) {

        return tagsRepository.findAll(pageable);

    }

    public Optional<Tag> create(TagCreate tagCreate) {

        Optional<Tag> optionalTag = getByName(tagCreate.getName());

        if (!optionalTag.isPresent()) {

            Tag tag = new Tag();

            tag.setUuid(UUID.randomUUID());
            tag.setName(tagCreate.getName());
            tag.setDescription(tagCreate.getDescription());

            return Optional.of(tagsRepository.save(tag));

        }

        return Optional.empty();

    }

}
