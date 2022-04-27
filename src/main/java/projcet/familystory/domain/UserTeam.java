package projcet.familystory.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTeam {

    @Id
    @GeneratedValue
    @Column(name = "utid")
    private Long utId;

    // gid
    @ManyToOne
    @JoinColumn(name = "tid")
    private Team team;

    //uid
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;


}
