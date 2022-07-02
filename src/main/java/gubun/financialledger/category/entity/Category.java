package gubun.financialledger.category.entity;

import gubun.financialledger.user.entity.User;

import javax.persistence.*;

@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
