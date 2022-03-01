/**
 * 
 *TESTEDDDDDD
 *
 * 
 * This is a simple Java application
 * to track statistics of teams and players in
 * the the British Premier League.
 * 
 * @Version 1.0 - Procedural Text UI
 * 
 * 
 * 
 * @author	Desmond O'Brien
 * 		    30064340
 * 
 * @author	Sean Honour Yuan An Tan
 * 			30094560
 * 			
 * 
 * 
 */

//imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;



/**
 * @class App - Single main class for procedural program
 *
 */
public class App {
	
	/*
	 * HashMaps for storing dat
	 * 
	 * Teams are stored with Team Names as Keys, mapped to an Arraylist of player names
	 */
	private static HashMap<String, ArrayList<String>> teams;
	
	/*All other HashMaps are <String, String>, 
	 *keys are player names mapped to specific stats for each player
	 */
	private static HashMap<String, String> ages;
	private static HashMap<String, String> countries;
	private static HashMap<String, String> positions;
	private static HashMap<String, String> jerseys;
	private static HashMap<String, String> goals;
	private static HashMap<String, String> saves;
	private static HashMap<String, String> assists;
	private static HashMap<String, String> shots_on_goal;
	private static HashMap<String, String> shots_on_goalie;
	private static ArrayList<HashMap<String, String>> all_maps;

	/**
	  * Adds a team to the Teams HashMap
	  * 
	  * @param team_name
	  */
	public static void add_team(String team_name) {
		//Create a key, value pair mapping teamname to empty arraylist for players
		teams.put(team_name, new ArrayList<String>());
	}
	
	/**
	 * Adds a player to the data
	 * 
	 * 
	 * @param team_name
	 * @param player_name
	 * @param age
	 * @param country
	 * @param position
	 * @param jersey
	 */
	public static void add_player(String team_name, String player_name, String age, String country, String position, String jersey) {
		//if player is in a team already, don't add
		if (teams.get(team_name).contains(player_name)) {
			//need a text message here
			return;
		}
		//for goalies, add to team and add relevant goalie statistics
		if (position == "goalie"){
			teams.get(team_name).add(player_name);
			ages.put(player_name, age);
			countries.put(player_name, country);
			positions.put(player_name, position);
			jerseys.put(player_name, jersey);
			saves.put(player_name, "0");
		}
		
		//for non goalie players, add to team and add relevant non goalie statistics
		if(position != "goalie"){
			teams.get(team_name).add(player_name);
			ages.put(player_name, age);
			countries.put(player_name, country);
			positions.put(player_name, position);
			jerseys.put(player_name, jersey);
			goals.put(player_name, "0");
			assists.put(player_name, "0");
			shots_on_goal.put(player_name, "0");
		}

		
		/**
		 * For all other stat hashmaps, add player name as key and add
		 * parameters as values
		 * 
		 * Numerical values are defaulted to a String 0
		 */

		
	}

	
	/**
	 * Increment the goal statistic tied to a player
	 * 
	 * 
	 * @param player_name
	 * @param num_goals
	 */
	public static void add_goal(String player_name, int num_goals) {
		/*
		 * parse goal stat as int from hashmap, increment it by parameter, then replace in hashmap
		 */
		int goals_as_int = Integer.parseInt(goals.get(player_name));
		goals_as_int += num_goals;
		goals.replace(player_name, String.valueOf(goals_as_int));
		
	}
	/**
	 * Increment the saves statistic tied to a goalie
	 */
	public static void add_save(String player_name, int num_saves){
		int saves_as_int = Integer.parseInt(saves.get(player_name));
		saves_as_int += num_saves;
		goals.replace(player_name, String.valueOf(saves_as_int));
	}

	/** Increment the number of shots on goal statistic tied to a player
	 *
	 */
	public static void add_shot_on_goal(String player_name, int shot_on_goal_num){
		int shot_on_goal_int = Integer.parseInt(shots_on_goal.get(player_name));
		shot_on_goal_int += shot_on_goal_num;
		shots_on_goal.replace(player_name, String.valueOf(shot_on_goal_int));
	}

	/**
	 * Increments the number of saves statistic tied to a player
	 */
	public static void add_assist(String player_name, int assists_num){
		int assist_int = Integer.parseInt(assists.get(player_name));
		assist_int += assists_num;
		assists.replace(player_name, String.valueOf(assist_int));
	}

	/**
	 * Increments number of shots made on a goalie
	 */
	public static void add_shot_on_goalie(String player_name, int shot_on_goalie_num){
		int shot_on_goalie_int = Integer.parseInt(shots_on_goalie.get(player_name));
		shot_on_goalie_int += shot_on_goalie_num;
		shots_on_goalie.replace(player_name, String.valueOf(shot_on_goalie_int));
	}
	/**
	 * Print summary of player statistic
	 * 
	 * 
	 * 
	 * @param player_name
	 */
	public static void player_summary(String player_name) {
		//array of category names for formatting
		String[] categories = {"Age", "Country of Origin", "Position", "#" , "Goals", "Saves", "Assists", "Shots on Goal"}; 
		System.out.println(player_name + "\n-----------------------------------\n");
		
		//print each stat that corresponds to player name  key in respective hashmap
		for (int i = 0; i < categories.length; ++i) {
			System.out.println(String.format("       -%s:  %s", categories[i], all_maps.get(i).get(player_name)));
		}
		System.out.println();
	}

	/**
	 * Print all players according to a team
	 * 
	 * @param team_name
	 * @param full_details - true if looking for summary of each player, false if looking just for name
	 */
	public static void print_players_by_team(String team_name, boolean full_details) {
		System.out.println(team_name + "\n-----------------------------------\n");
		
		/**
		 * Loop to print just names of players
		 */
		if (!full_details) {
			//iterate through each player in arraylist corresponding to team name, and print each namee
			for (String player : teams.get(team_name)) {
				System.out.println(String.format("       -%s", player));
			}
			System.out.println();
		}
		//
		else {
			//for each player in arraylist corresponding to team name key, summarize player data
			for (String player : teams.get(team_name)) {
				player_summary(player);
			}
			System.out.println();
		}
	}

	/**
	 * Print all players in the league, sorted into teams
	 * 
	 * @param full_details - False for just player names, true for player summaries
	 */
	public static void print_league(boolean full_details) {
		System.out.println("Full Leage Review:");
		System.out.println("-----------------------------------\n");
		
		//Print just names
		if (!full_details) {
			//iterate through each team name in teams hashmap
			for (String team : teams.keySet()) {
				//print team name
				System.out.println(team + "\n-----------------------------------\n");
				//iterate through each player name in arraylist corresponding to team name
				for (String player : teams.get(team)) {
					//print each player name
					System.out.println(String.format("       -%s", player));
				}
				System.out.println();
			}
		}
		else {
			//same nested for loop as above
			for (String team : teams.keySet()) {
				System.out.println(team + "\n-----------------------------------\n");
				for (String player : teams.get(team)) {
					//print player summary as opposed to just name
					player_summary(player);
				}
				System.out.println();
			}
		}
	}



	/**
	 * Print each team name
	 */
	public static void print__teams() {
		System.out.println("Teams:");
		System.out.println("-----------------------------------\n");
		//iterate through each team name key in teams hashmap and print each
		for (String team : teams.keySet()) {
			System.out.println(String.format("       -%s", team));
		}
		System.out.println();
	}	

		
	/**
	 * Find player in league that has the most goals
	 * 
	 * 
	 */
	public static void highest_goals() {
		System.out.println("Player with the most Goals:\n-----------------------------------\n");
		//arraylist to store each player name with highest score count
		ArrayList<String> highest_scorers = new ArrayList<String>();
		//each player starts with 0 goals, counter from 0
		int most_goals = 0;
		
		//iterate through each team name in teams hashmap
		for (String team : teams.keySet()) {
			//iterate through each player name in arraylist
			for (String player : teams.get(team)) {
				//if players goals are equal to or higher than highest count, set highest count as player's goals
				if ((Integer.parseInt(goals.get(player)) >= most_goals)) {
					most_goals = Integer.parseInt(goals.get(player));
				}
			}
		}
		
		//same for loop as above
		for (String team : teams.keySet()) {
			for (String player : teams.get(team)) {
				//if player has highest goals, add player name to highest_scorers arraylist
				if ((Integer.parseInt(goals.get(player)) == most_goals)) {
					highest_scorers.add(player);
				}
			}
		}
		
		//print summaries for each player with highest goals
		for (String player : highest_scorers) {
			player_summary(player);
		}
		
	}


	/**
	 * find and print top 5 assisters in league
	 * @param args
	 */

	public static void score_and_save_percent(String player_name) {
		String position = positions.get(player_name);
		if (position == "goalie") {
			System.out.println(player_name + "'s save percentage is:");
			float save_percent = ((saves.get(player_name)) / (shots_on_goalie.get(player_name)));
			System.out.println(save_percent);

		}

		if (position != "goalie"){
			System.out.println(player_name + "'s goal percentage is:");
			float goal_percent = ((goals.get(player_name)) / (shots_on_goal.get(player_name))) * 100;
			System.out.println(goal_percent);

		}
	}
	
	
	
	
	//********************************************************************************
	
	
	
	
    public static void main(String[] args) {
    	
		teams 			= new HashMap<String, ArrayList<String>>();
		ages 			= new HashMap<String, String>();
		countries 		= new HashMap<String, String>();
		positions 		= new HashMap<String, String>();
		jerseys 		= new HashMap<String, String>();
		goals 			= new HashMap<String, String>();
		saves 			= new HashMap<String, String>();
		assists 		= new HashMap<String, String>();
		shots_on_goal 	= new HashMap<String, String>();


		all_maps = new ArrayList<HashMap<String, String>>();
		all_maps.add(ages);
		all_maps.add(countries);
		all_maps.add(positions);
		all_maps.add(jerseys);
		all_maps.add(goals);
		all_maps.add(saves);
		all_maps.add(assists);
		all_maps.add(shots_on_goal);

		
		
		add_team("Man City");
		add_team("Man United");
		add_player("Man United", "Messi", "40", "Argentina", "Striker", "69");
		add_player("Man United", "Messi", "40", "Argentina", "Striker", "69");
		add_player("Man United", "Messi", "40", "Argentina", "Striker", "69");
		
		add_player("Man City", "Ronaldo", "40", "Porugal", "Stiker", "420");
		add_player("Man City", "Ronaldo", "40", "Porugal", "Stiker", "420");
		add_player("Man City", "Ronaldo", "40", "Porugal", "Stiker", "420");

		//print__teams();
		//print_players_by_team("Man City", true);
		//player_summary("Ronaldo");
		
		add_goal("Messi", 1);
		add_goal("Ronaldo", 2);
		highest_goals();











		add_team("Man City");







    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }

	private static void print_map(HashMap<String, ArrayList<String>> teams2) {
		// TODO Auto-generated method stub
		
	}
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

