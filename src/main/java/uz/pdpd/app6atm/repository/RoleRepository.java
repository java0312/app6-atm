package uz.pdpd.app6atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdpd.app6atm.entity.Role;
import uz.pdpd.app6atm.entity.enums.RoleName;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findByRoleName(RoleName roleName);

}
