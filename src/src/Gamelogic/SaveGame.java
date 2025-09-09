package Gamelogic;

import TeamManagement.Team;
import Gamelogic.Ball;
import java.io.Serializable;

public class SaveGame implements Serializable {
    private static final long serialVersionUID = 1L;

    // Die beiden Teams mit allen Spielern, Positionen, Energie etc.
    public Team team1;
    public Team team2;

    // Ball inklusive Position und evtl. Besitzer
    public Ball ball;

    // Die Rundenzahl und maximale Rundenzahl
    public int currentRound;
    public int maxRounds;

    // true = Team1 ist am Zug, false = Team2
    public boolean isTeam1Turn;

    // Standard-Konstruktor (wird für das Laden genutzt)
    public SaveGame() {}

    // Komfort-Konstruktor für explizites Setzen beim Speichern
    public SaveGame(
        Team team1,
        Team team2,
        Ball ball,
        int currentRound,
        int maxRounds,
        boolean isTeam1Turn
    ) {
        this.team1 = team1;
        this.team2 = team2;
        this.ball = ball;
        this.currentRound = currentRound;
        this.maxRounds = maxRounds;
        this.isTeam1Turn = isTeam1Turn;
    }
}