package finalexam;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWithExistingPlayerCommand {

    private long id;

    @NotBlank(message = "Player's name must be given")
    @Length(max = 255)
    @Schema(description = "Existing player's name", example = "Király Gábor")
    private String name;

    @Schema(description = "Player's ate of birth")
    private LocalDate birthDate;

    @Schema(description = "Position of the player")
    private PositionType position;

    @Schema(description = "Team of the player")
    private Team team;

    public UpdateWithExistingPlayerCommand(long id) {
        this.id = id;
    }
}
