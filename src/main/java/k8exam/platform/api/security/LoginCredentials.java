package k8exam.platform.api.security;

import lombok.Data;

@Data
public class LoginCredentials {

    private String username;
    private String password;

}
