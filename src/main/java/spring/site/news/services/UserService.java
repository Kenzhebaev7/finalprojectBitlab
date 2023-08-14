package spring.site.news.services;

import spring.site.news.dto.UserDTO;
import spring.site.news.mapper.UserMapper;
import spring.site.news.models.Permission;
import spring.site.news.models.User;
import spring.site.news.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        if (user != null){
            if (user.getIsBanned()){
                throw new UsernameNotFoundException("User is banned");
            }
            return user;
        } else {
            throw new UsernameNotFoundException("User not found");
        }

    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User addUser(User user){
        User userFromDB = userRepository.findByEmail(user.getEmail());

        if (userFromDB != null){
            return null;
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPermissions(new HashSet<>());
            Permission userRolePermission = permissionService.userRolePermission();
            user.getPermissions().add(userRolePermission);
            return userRepository.save(user);
        }

    }

    public User getCurrentSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public boolean isUserPremium(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getIsPremium();
    }

    public void toggleBan(Long id, boolean banned) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setIsBanned(banned);
            userRepository.save(user);
        }
    }

    public boolean isAdmin(User user) {
        return user.getPermissions().stream().anyMatch(permission -> permission.getRole().equals("ROLE_ADMIN"));
    }


    public List<UserDTO> getAllUsersDTO() {
        return userMapper.toUserDTOList(userRepository.findAll());
    }

}
