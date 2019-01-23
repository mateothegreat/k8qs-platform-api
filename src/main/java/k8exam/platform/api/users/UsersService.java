package k8exam.platform.api.users;

import k8exam.platform.api.organizations.Organization;
import k8exam.platform.api.organizations.OrganizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    @Lazy
    private OrganizationsService organizationsService;

    public Optional<User> updatePrincipalUser(User user, Principal principal) {

        Optional<User> principalUser = getPrincipalUser(principal);

        if (principalUser.isPresent()) {

            principalUser.get().setEmail(user.getEmail());
            principalUser.get().setPhone(user.getPhone());
            principalUser.get().setFirstname(user.getFirstname());
            principalUser.get().setLastname(user.getLastname());
            principalUser.get().setStreet1(user.getStreet1());
            principalUser.get().setStreet2(user.getStreet2());
            principalUser.get().setCity(user.getCity());
            principalUser.get().setState(user.getState());
            principalUser.get().setZip(user.getZip());
            principalUser.get().setCountry(user.getCountry());

            return Optional.of(usersRepository.save(principalUser.get()));

        }

        return Optional.empty();

    }

    public Optional<User> updateUserByPrincipal(Long id, User user, Principal principal) {

        Optional<User> principalUser = getPrincipalUser(principal);

        if (principalUser.isPresent()) {

            Optional<User> optionalUser = getByPrincipalOrganizationAndId(principal, id);

            if (optionalUser.isPresent()) {

                if (user.getEmail() != null) {

                    optionalUser.get().setEmail(user.getEmail());

                }

                if (user.getFirstname() != null) {

                    optionalUser.get().setFirstname(user.getFirstname());

                }

                if (user.getLastname() != null) {

                    optionalUser.get().setLastname(user.getLastname());

                }

                if (user.getPassword() != null) {

                    optionalUser.get().setPassword(user.getPassword());

                }

                if (user.getPhone() != null) {

                    optionalUser.get().setPhone(user.getPhone());

                }

                if (user.getFirstname() != null) {

                    optionalUser.get().setFirstname(user.getFirstname());

                }

                if (user.getLastname() != null) {

                    optionalUser.get().setLastname(user.getLastname());

                }

                if (user.getStreet1() != null) {

                    optionalUser.get().setStreet1(user.getStreet1());

                }

                if (user.getStreet2() != null) {

                    optionalUser.get().setStreet2(user.getStreet2());

                }

                if (user.getCity() != null) {

                    optionalUser.get().setCity(user.getCity());

                }

                if (user.getState() != null) {

                    optionalUser.get().setState(user.getState());

                }

                if (user.getZip() != null) {

                    optionalUser.get().setZip(user.getZip());

                }

                if (user.getCountry() != null) {

                    optionalUser.get().setCountry(user.getCountry());

                }

                if (user.getPermissionUsersManage() != null) {

                    optionalUser.get().setPermissionUsersManage(user.getPermissionUsersManage());

                }

                if (user.getPermissionBillingManage() != null) {

                    optionalUser.get().setPermissionBillingManage(user.getPermissionBillingManage());

                }

                if (user.getPermissionSubaccountsManage() != null) {

                    optionalUser.get().setPermissionSubaccountsManage(user.getPermissionSubaccountsManage());

                }

                return Optional.of(usersRepository.save(optionalUser.get()));

            }

        }

        return Optional.empty();

    }

    public Optional<User> getPrincipalUser(Principal principal) {

        return usersRepository.getByEmail(principal.getName());

    }

    public Optional<User> getByUserId(Long userId) {

        return usersRepository.findById(userId);

    }

    public List<User> getByOrganization(Organization organization) {

        return usersRepository.getByOrganization(organization);

    }

    public Optional<User> getByPrincipalOrganizationAndId(Principal principal, Long userId) {

        Optional<User> principalUser = getPrincipalUser(principal);

        if (principalUser.isPresent()) {

            return usersRepository.getByOrganizationAndId(principalUser.get().getOrganization(), userId);

        }

        return Optional.empty();

    }

    public int deleteByPrincipalOrganizationAndId(Principal principal, Long userId) {

        Optional<User> principalUser = getPrincipalUser(principal);

        if (principalUser.isPresent()) {

            return usersRepository.deleteByOrganizationAndId(principalUser.get().getOrganization(), userId);

        }

        return 0;

    }

    public Optional<User> getByEmailAndPassword(String email, String password) {

        return usersRepository.getByEmailAndPassword(email, password);

    }

    public User create(User user) {

        return usersRepository.save(user);

    }

    public Optional<User> getUserByPrincipalAndIsAdmin(Principal principal) {

        Optional<User> user = getPrincipalUser(principal);

        if (user.isPresent()) {

            if (user.get().getEmail().equals("root@streamnvr.com")) {

                return user;

            }

        }

        return Optional.empty();

    }

}
