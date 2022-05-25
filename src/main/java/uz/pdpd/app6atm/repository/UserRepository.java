package uz.pdpd.app6atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdpd.app6atm.entity.Role;
import uz.pdpd.app6atm.entity.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByRoles(Set<Role> roles);

    Optional<User> findByEmailAndEmailCode(String email, String emailCode);

}
