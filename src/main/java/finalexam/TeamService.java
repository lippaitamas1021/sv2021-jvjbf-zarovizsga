package finalexam;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TeamService {

    private TeamRepository teamRepository;

    private PlayerService playerService;

    private ModelMapper modelMapper;

    public List<TeamDTO> listTeams() {
        Type targetListType = new TypeToken<List<TeamDTO>>(){}.getType();
        return modelMapper.map(teamRepository.findAll(), targetListType);
    }

    public TeamDTO createTeam(CreateTeamCommand command) {
        return teamRepository.save(command);
    }

    public void addPlayerToTheTeam(UpdateWithExistingPlayerCommand command) {
    }

    public void buyPlayer() {
    }
}
