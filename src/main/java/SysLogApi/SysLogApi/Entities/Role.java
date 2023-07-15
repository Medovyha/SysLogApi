package SysLogApi.SysLogApi.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.JoinFormula;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    @JsonIgnore
    private int id;

    @Column(name="name")
    private String name;

//    @Column(name="application_id")
    @ManyToOne
    @JoinColumn(name="application_id", nullable=false)
    @JsonBackReference
    private Application application;

    @OneToMany(mappedBy = "role")
    private List<RoleRule> rules = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinTable(name = "user_role_rl",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<User> admins;
}
