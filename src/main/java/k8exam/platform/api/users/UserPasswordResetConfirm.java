package k8exam.platform.api.users;

import lombok.Data;

@Data
public class UserPasswordResetConfirm {

    private String token;
    private String password;

}
