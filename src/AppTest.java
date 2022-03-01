import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class AppTest {

    @Test
    void add_team_key_test() {
        String team_name_test = "man united";
        App.add_team(team_name_test);
        assertTrue(App.teams.containsKey(team_name_test));

    }
    void add_team_is_empty() {
        String team_name_test = "man united";
        App.add_team(team_name_test);
        assertFalse(App.teams.isEmpty());


    }

    @Test
    void add_player_key_test() {
        App.add_player("man united", "Ronaldo", "38", "Portugal", "Striker", "9");
        assertTrue(App.teams.containsValue("Ronaldo"));
    }
    @Test
    void add_player_is_empty() {
        String player_name_test = "Ronaldo";
        App.add_player("man united", "Ronaldo", "38", "Portugal", "Striker", "9");
        assertFalse(App.teams.isEmpty());
    }

    @Test
    void add_goal_key_test() {

        String player_name_test = "Ronaldo";
        App.add_goal(player_name_test, 5);
        assertTrue(App.goals.containsKey(player_name_test));
    }
    @Test
    void add_goal_is_empty() {
        String player_name_test = "Ronaldo";
        App.add_goal(player_name_test, 5);
        assertFalse(App.goals.isEmpty());
    }

    @Test
    void add_save_key_test() {
        String player_name_test = "Neuer";
        App.add_save(player_name_test, 5);
        assertTrue(App.saves.containsKey(player_name_test));
    }
    @Test
    void add_save_is_empty() {
        String player_name_test = "Neuer";
        App.add_save(player_name_test, 5);
        assertFalse(App.saves.isEmpty());
    }

    @Test
    void add_shot_on_goal_key_test() {
        String player_name_test = "Ronaldo";
        App.add_shot_on_goal(player_name_test, 5);
        assertTrue(App.shots_on_goal.containsKey(player_name_test));
    }
    @Test
    void add_shot_on_goal_empty() {
        String player_name_test = "Ronaldo";
        App.add_shot_on_goal(player_name_test, 5);
        assertFalse(App.shots_on_goal.isEmpty());
    }

    @Test
    void add_assist_empty() {
        String player_name_test = "Ronaldo";
        App.add_assist(player_name_test, 5);
        assertFalse(App.assists.isEmpty());

    }
    @Test
    void add_assist_key_test() {
        String player_name_test = "Ronaldo";
        App.add_assist(player_name_test, 5);
        assertTrue(App.assists.containsKey(player_name_test));

    }

    @Test
    void add_shot_on_goalie_empty() {
        String player_name_test = "Neuer";
        App.add_shot_on_goalie(player_name_test, 5);
        assertFalse(App.shots_on_goalie.isEmpty());
    }@Test
    void add_shot_on_goalie_key_test() {
        String player_name_test = "Neuer";
        App.add_assist(player_name_test, 5);
        assertTrue(App.assists.containsKey(player_name_test));
    }

    @Test
    void player_summary() {
    }

    @Test
    void print_players_by_team() {
    }

    @Test
    void print_league() {
    }

    @Test
    void print__teams() {
    }

    @Test
    void highest_goals() {
    }

    @Test
    void top_scorers() {
    }

    @Test
    void top_assisters() {
    }

    @Test
    void top_goalis() {
    }

    @Test
    void score_and_save_percent() {
    }

    @Test
    void mean_table() {
    }

    @Test
    void median_table() {
    }

    @Test
    void sum_table() {
    }

    @Test
    void min_table() {
    }

    @Test
    void max_table() {
    }

    @Test
    void run() {
    }

    @Test
    void main() {
    }
}