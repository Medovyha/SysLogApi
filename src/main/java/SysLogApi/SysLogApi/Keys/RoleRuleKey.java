package SysLogApi.SysLogApi.Keys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RoleRuleKey implements Serializable {
    @Column(name = "role_id")

    int roleId;

    @Column(name = "rule_id")
    int ruleId;
}
