package k8exam.platform.api.answers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "answers")
@NoArgsConstructor
@ToString
public class Answer {

    @Id
    @Column(name = "uuid", updatable = false, nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID uuid;

    private String value;
    private String description;

    public Answer(UUID uuid, String value, String description) {

        this.uuid = uuid;
        this.value = value;
        this.description = description;

    }

}
