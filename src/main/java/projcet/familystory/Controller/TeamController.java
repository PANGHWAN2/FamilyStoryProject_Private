package projcet.familystory.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import projcet.familystory.domain.Team;
import projcet.familystory.form.TeamForm;
import projcet.familystory.service.TeamService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams/newTeam")
    public String createTeamForm(Model model){
        model.addAttribute("teamForm", new TeamForm());
        return "teams/createTeamForm";
    }

    @GetMapping("/team")
    public String teamplist(Model model) {
        List<Team> team = teamService.findTeam();
        model.addAttribute("teams", team);
        return "teams/teamList";
    }

    @PostMapping("/teams/newTeam")
    public String createTeam(@Valid TeamForm form, BindingResult result){

        Team team = new Team();
        team.setTeamId(form.getTeamId());
        team.setTeamImage(form.getTeamImage());
        team.setTeamName(form.getTeamName());
        teamService.join(team);
        return "users/login";
    }


}
