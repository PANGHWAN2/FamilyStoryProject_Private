package projcet.familystory.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="tid")
    private Long tId;

    @Column(name="team_id")
    private String teamId;
    @Column(name="team_name")
    private String teamName;
    @Column(name="team_image")
    private String teamImage;

    @OneToMany(mappedBy = "team")  //membergroup에 있는 group
    private List<UserTeam> memberGroup2 = new ArrayList<>();
}


