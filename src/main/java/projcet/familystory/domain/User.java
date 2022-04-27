package projcet.familystory.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name="uId")
    private Long uId;
    @NotNull
    private String userId;
    @NotNull
    private String passWord;
    @NotNull
    private String name;
    @NotNull
    private String nickName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    private String birthday;
//  private LocalDate birthday;
    private String image;
    private String mainGroupId;


    @OneToMany(mappedBy = "user")  //membergroup에 있는 member
    private List<UserTeam> memberGroup1 = new ArrayList<>();


}