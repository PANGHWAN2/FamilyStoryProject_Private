package projcet.familystory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
;
import projcet.familystory.domain.Team;
import projcet.familystory.repository.TeamRepository;

import java.util.List;


@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {


    private final TeamRepository teamRepository;
    @Transactional(readOnly = false)
    public Long join(Team team){

        teamRepository.save(team);
        return team.getTId();
    }

    public List<Team> findTeam(){
        return teamRepository.findAll();
    }
}
