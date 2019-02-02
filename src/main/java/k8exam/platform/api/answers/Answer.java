package k8exam.platform.api.answers;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import k8exam.platform.api.questions.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "answers")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Answer {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Range(max = 4294967295L)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID uuid;

    @JsonBackReference
    @ManyToOne
    private Question question;

    private Boolean selected;
    private String  value;
    private String  description;
    private Double  score;

    public Answer(UUID uuid, Boolean selected, String value, String description, Double score) {

        this.uuid = uuid;
        this.selected = selected;
        this.value = value;
        this.description = description;
        this.score = score;

    }

}
