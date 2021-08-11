package finalexam.teams;

import finalexam.EntityNotFoundException;
import finalexam.players.CreatePlayerCommand;
import finalexam.players.Player;
import finalexam.players.PlayerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    private final PlayerRepository playerRepository;

    private final ModelMapper modelMapper;

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
        Team team = teamRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id, "Team"));
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        team.addNewPlayer(player);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO buyPlayer(long id, UpdateWithExistingPlayerCommand command) {
        Player player = findPlayerById(command.getId());
        Team team = findTeamById(id);
        if (player.hasNoTeam() && certifiable(team, player)) {
            team.addNewPlayer(player);
        }
        return modelMapper.map(team, TeamDTO.class);
    }

    private Player findPlayerById(long id) {
        return playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Player"));
    }

    private Team findTeamById(long id) {
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id, "Team"));
    }

    private boolean certifiable(Team team, Player player) {
        return team.getPlayers().stream().filter(p -> p.getPosition() == player.getPosition()).count() < 2;
    }
}
