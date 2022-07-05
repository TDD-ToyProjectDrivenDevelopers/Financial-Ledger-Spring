package gubun.financialledger.user.entity;

import gubun.financialledger.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder
    public User(UserRole role, String email, String password, String username, Boolean isDeleted) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.username = username;
        this.isDeleted = isDeleted;
    }
}
