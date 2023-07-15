package SysLogApi.SysLogApi.Entities;

import SysLogApi.SysLogApi.Keys.RoleRuleKey;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles_rules_rl")
public class RoleRule implements Serializable {
    @EmbeddedId
    @JsonIgnore
    RoleRuleKey id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "role_id", insertable=false, updatable=false)
    private Role role;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "rule_id", insertable=false, updatable=false)
    private Rule rule;

    @Formula("(select rules.name from rules where rules.id = rule_id)")
    private String rule_name;

    @Formula("(select roles.name from roles where roles.id = role_id)")
    private String role_name;

    @Column(name = "is_allowed")
    private boolean isAllowed;
}
