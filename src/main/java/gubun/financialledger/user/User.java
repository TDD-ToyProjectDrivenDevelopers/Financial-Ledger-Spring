package gubun.financialledger.user;

import gubun.financialledger.common.BaseEntity;

import javax.persistence.*;

@Entity
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

}