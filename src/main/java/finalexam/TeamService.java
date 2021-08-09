package finalexam;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TeamService {

    private TeamRepository teamRepository;

    private PlayerRepository playerRepository;

    private ModelMapper modelMapper;

    public List<TeamDTO> listTeams() {
        Type targetListType = new TypeToken<List<TeamDTO>>(){}.getType();
        return modelMapper.map(teamRepository.findAll(), targetListType);
    }

    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = teamRepository.save(new Team(command.getName()));
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO addPlayerToTheTeam(long id, CreatePlayerCommand command) {
        Team team = findPlayerById(id);
        Player player = playerRepository.save(new Player(command.getName(), command.getBirthDate(), command.getPosition()));
        team.addNewPlayer(player);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO buyPlayer(long id, UpdateWithExistingPlayerCommand command) {
        Player player = playerRepository.findById(command.getId()).orElseThrow(()-> new PlayerOrTeamNotFoundException("Player"));
        Team team = findPlayerById(id);
        if (player.getTeam() == null) {
            team.addNewPlayer(player);
        }
        return modelMapper.map(team, TeamDTO.class);
    }

    private Team findPlayerById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new PlayerOrTeamNotFoundException("Player"));
    }
}
