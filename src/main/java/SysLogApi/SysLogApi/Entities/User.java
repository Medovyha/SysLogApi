package SysLogApi.SysLogApi.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    @JsonIgnore
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="is_admin")
    @JsonIgnore
    private boolean isAdmin;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private ApiKey apiKey;
}
