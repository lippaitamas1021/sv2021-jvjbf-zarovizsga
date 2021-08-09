package finalexam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
@Tag(name = "Operations on teams")
public class TeamController {

    private TeamService teamService;

    @GetMapping
    @Operation(summary = "Listing all teams", description = "This option is for listing all the teams")
    public List<TeamDTO> listTeams() {
        return teamService.listTeams();
    }

    @PostMapping
    @Operation(summary = "Creating a team", description = "This option is for creating a team")
    @ApiResponse(responseCode = "201", description = "Team has been created")
    @ApiResponse(responseCode = "400", description = "Validation exception while creating a team")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand command) {
        return teamService.createTeam(command);
    }

    @PostMapping("/{id}/players")
    @Operation(summary = "Adding a new player", description = "This option is for adding a new player to the team")
    public TeamDTO addPlayerToTheTeam(@PathVariable long id, @Valid @RequestBody CreatePlayerCommand command) {
        return teamService.addPlayerToTheTeam(id, command);
    }

    @PutMapping("/{id}/players")
    @Operation(summary = "Buying a player", description = "This option is for buying a new player")
    @ApiResponse(responseCode = "200", description = "Player has been bought")
    @ApiResponse(responseCode = "400", description = "Buying this player is not possible")
    public TeamDTO buyPlayer(@PathVariable long id, @Valid @RequestBody UpdateWithExistingPlayerCommand command){
        return teamService.buyPlayer(id, command);
    }
}
