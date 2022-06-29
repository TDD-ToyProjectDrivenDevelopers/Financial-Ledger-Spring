package gubun.financialledger.category;

import gubun.financialledger.common.BaseEntity;
import gubun.financialledger.user.User;

import javax.persistence.*;

@Entity
public class Category extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
