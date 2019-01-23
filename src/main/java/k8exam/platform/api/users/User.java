package k8exam.platform.api.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import k8exam.platform.api.common.LocalDateTimeJsonDeserializer;
import k8exam.platform.api.common.Status;
import k8exam.platform.api.organizations.Organization;
import k8exam.platform.api.roles.Role;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Range(max = 4294967295L)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    private UUID uuid;
    private UUID token;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private Organization organization;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    private List<Role> roles;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public String email;
    public String phone;
    public String username;

    //    @JsonIgnore
    private String password;
    public  String firstname;
    public  String lastname;

    public String street1;
    public String street2;
    public String city;
    public String state;
    public String zip;
    public String country;

    @Transient
    public Long organization_id;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @CreationTimestamp
    private LocalDateTime stampCreated;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    public LocalDateTime stampLastLogin;

    @JsonIgnore
    private String passwordResetToken;

    @JsonIgnore
    private String confirmEmailToken;

    private Boolean isConfirmed;
    private Boolean isAdmin;

    private Boolean enabled = true;

    private Boolean permissionUsersManage       = true;
    private Boolean permissionBillingManage     = true;
    private Boolean permissionSubaccountsManage = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
