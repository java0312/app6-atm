package uz.pdpd.app6atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdpd.app6atm.entity.Role;
import uz.pdpd.app6atm.entity.User;
import uz.pdpd.app6atm.entity.enums.RoleName;
import uz.pdpd.app6atm.my.KnowRole;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.LoginDto;
import uz.pdpd.app6atm.payload.RegisterDto;
import uz.pdpd.app6atm.repository.RoleRepository;
import uz.pdpd.app6atm.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    KnowRole knowRole;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        return optionalUser.orElse(null);
    }

    /**
     * ______________________Register
     *
     * @param registerDto
     * @return
     */

    public ApiResponse register(RegisterDto registerDto) {

        boolean exists = userRepository.existsByEmail(registerDto.getEmail());
        if (exists)
            return new ApiResponse("This user already exists!", false);

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        user.setEmailCode(UUID.randomUUID().toString());

        if (knowRole.isNobody()) {
            user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.DIRECTOR)));
        } else
            user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.EMPLOYEE)));

        User savedUser = userRepository.save(user);
        boolean sendEmail = sendEmail(savedUser.getEmail(), savedUser.getEmailCode());
        if (sendEmail)
            return new ApiResponse("Message sent email", true);
        return new ApiResponse("Message not sent email", false);
    }

    private boolean sendEmail(String email, String code) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Alisher@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("Confirm account");
            mailMessage.setText(
                    "http://localhost:9090/api/verifyEmail?email=" + email + "&code=" + code
            );
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * ---------------------Verify_Email
     *
     * @param email
     * @param code
     * @return
     */
    public ApiResponse verifyEmail(String email, String code) {

        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(email, code);
        if (optionalUser.isEmpty())
            return new ApiResponse("User not found!", false);

        User user = optionalUser.get();
        user.setEnabled(true);
        user.setEmailCode(null);
        userRepository.save(user);

        return new ApiResponse("User confirmed!", true);
    }

    /**
     * ----------------------Login to System
     *
     * @param loginDto
     * @return
     */
    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            return new ApiResponse("You entered!", true);
        }catch (Exception e){
            return new ApiResponse("Error", false);
        }
    }
}










