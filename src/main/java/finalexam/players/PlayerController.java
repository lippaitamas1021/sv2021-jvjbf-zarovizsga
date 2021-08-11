package finalexam.players;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@AllArgsConstructor
@Tag(name = "Operations on players")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    @Operation(summary = "List all players", description = "This option is for listing all the players")
    public List<PlayerDTO> listPlayers() {
        return playerService.listPlayers();
    }

    @PostMapping
    @Operation(summary = "Create a new player", description = "This option is for creating a new player")
    @ApiResponse(responseCode = "201", description = "Player has been created")
    @ApiResponse(responseCode = "400", description = "Validation exception while creating a player")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO createPlayer(@Valid @RequestBody CreatePlayerCommand command) {
        return playerService.createPlayer(command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a player", description = "This option is for deleting one player by ID")
    @ApiResponse(responseCode = "204", description = "Player has been deleted")
    @ApiResponse(responseCode = "404", description = "Player not found")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayerById(@Parameter(description = "ID of player", example = "21") @PathVariable("id") long id) {
        playerService.deletePlayerById(id);
    }
}
