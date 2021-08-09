package finalexam;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private PlayerRepository playerRepository;
    private ModelMapper modelMapper;

    public List<PlayerDTO> listPlayers() {
        Type targetListType = new TypeToken<List<PlayerDTO>>() {}.getType();
        return modelMapper.map(playerRepository.findAll(), targetListType);
    }

    public Optional<Player> findPlayerById(long id) {
        return playerRepository.findById(id);
    }

    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = playerRepository.save(new Player(command.getName(), command.getBirthDate(), command.getPosition()));
        return modelMapper.map(player, PlayerDTO.class);
    }

    public void deletePlayerById(long id) {
        playerRepository.deleteById(id);
    }
}
