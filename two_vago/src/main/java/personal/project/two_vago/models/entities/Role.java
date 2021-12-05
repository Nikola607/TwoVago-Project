package personal.project.two_vago.models.entities;

import personal.project.two_vago.models.entities.enums.RoleNameEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "roles")
public class Role extends BaseEntity{
    private RoleNameEnum roleName;

    public Role() {
    }

    @Enumerated(EnumType.STRING)
    public RoleNameEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleNameEnum roleName) {
        this.roleName = roleName;
    }
}
