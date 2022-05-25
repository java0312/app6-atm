package uz.pdpd.app6atm.my;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdpd.app6atm.entity.Role;
import uz.pdpd.app6atm.entity.User;
import uz.pdpd.app6atm.entity.enums.RoleName;

import java.util.Set;

@Component
public class KnowRole {

    public boolean isNobody(){
        try {
            return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser");
        }catch (Exception e){
            return false;
        }
    }

    public User getAuthUser(){
        if (isNobody())
            return null;
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isDirector(){
        if (getAuthUser() != null) {
            User authUser = getAuthUser();
            Set<Role> roles = authUser.getRoles();
            for (Role role : roles)
                if (role.getRoleName().equals(RoleName.DIRECTOR))
                    return true;
        }
        return false;
    }

    public boolean isEmployee(){
        return !(isDirector() || isNobody());
    }
}
