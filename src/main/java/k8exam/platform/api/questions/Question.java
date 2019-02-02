package k8exam.platform.api.questions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import k8exam.platform.api.answers.Answer;
import k8exam.platform.api.categories.Category;
import k8exam.platform.api.tags.Tag;
import k8exam.platform.api.users.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "questions")
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Question {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Range(max = 4294967295L)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID uuid;

    @CreationTimestamp
    private LocalDateTime stampCreated;

    //    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "categories")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
//    @JsonIgnoreProperties("questions")
    @JoinTable(name = "questions_categories_links", joinColumns = {@JoinColumn(name = "question")}, inverseJoinColumns = {@JoinColumn(name = "category")})
    private List<Category> categories = new ArrayList<>();

    //    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    List<Tag> tags = new ArrayList<>();

    private QuestionType questionType;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    List<Answer> answers = new ArrayList<>();

    private String name;
    private String description;
    private Double score;
    private int    weight;

    @OneToOne
    private User createdBy;

}
