package finalexam.players;

import finalexam.teams.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private PositionType position;

    @ManyToOne
    private Team team;

    public Player(String name, LocalDate birthDate, PositionType position) {
        this.name = name;
        this.birthDate = birthDate;
        this.position = position;
    }

    public boolean hasNoTeam() {
        return team == null;
    }
}
