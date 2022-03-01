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
 * 			
 * 
 * 
 */

//imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import java.util.Scanner;

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
	public static HashMap<String, ArrayList<String>> teams;

	/*All other HashMaps are <String, String>,
	 *keys are player names mapped to specific stats for each player
	 */
	public static HashMap<String, String> ages;
	public static HashMap<String, String> countries;
	public static HashMap<String, String> positions;
	public static HashMap<String, String> jerseys;
	public static HashMap<String, String> goals;
	public static HashMap<String, String> saves;
	public static HashMap<String, String> assists;
	public static HashMap<String, String> shots_on_goal;
	public static HashMap<String, String> shots_on_goalie;
	public static ArrayList<HashMap<String, String>> all_maps;

	/**
	  * Adds a team to the Teams HashMap
	  *
	  * @param team_name
	  */
	public static void add_team(String team_name) {

		// if team_name string value is invalid dont add
		if(team_name == null || team_name.equals("")){
			System.out.println("ERROR! Team name must be a proper string");
			return;
		}
		// if team already exists dont add
		if(teams.containsKey(team_name)){
			System.out.println("Error! This team already exists!");
			return;
		}


		//Create a key, value pair mapping teamname to empty arraylist for players
		teams.put(team_name, new ArrayList<String>());
	}

	/**
	 * Adds a player to the data
	 *
	 *
	 * @param team_name ddf
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
			System.out.println("ERROR! Player already exists in this team!");
			return;
		}

		String lower_case_position = position.toLowerCase();
		//for goalies, add to team and add relevant goalie statistics
		if ("goalie".equals(lower_case_position)){
			teams.get(team_name).add(player_name);
			ages.put(player_name, age);
			countries.put(player_name, country);
			positions.put(player_name, position);
			jerseys.put(player_name, jersey);
			saves.put(player_name, "0");
			shots_on_goalie.put(player_name, "0");
		}

		//for non goalie players, add to team and add relevant non goalie statistics
		if(!"goalie".equals(lower_case_position)){
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
		if(num_goals < 0){
			System.out.println("ERROR! Number of goals must be positive!");
			return;
		}
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
	 * Prints the top 'n' scorers in the league
	 * 
	 * 
	 * @param number_of - number of top scorers to display
	 */

	public static void top_scorers(int number_of) {
		System.out.println(String.format("Top %d scorers:\n-----------------------------------\n", number_of));

			//Arraylists to store all goal counts, and then top goal counts
			ArrayList<Integer> all_counts = new ArrayList<Integer>();
			ArrayList<Integer> top_counts = new ArrayList<Integer>();

			//iterate through all pairs in goals
			for (Map.Entry<String, String> set : goals.entrySet()) {
				//add each player's goals to arraylist
				all_counts.add(Integer.parseInt(set.getValue()));
			}

			//sort all_counts from high to low
			Collections.sort(all_counts);
			Collections.reverse(all_counts);

			//if there are less scorers than input, just add all players goals as top goals
			if (all_counts.size() < number_of) {
				for (int count : all_counts) {
					top_counts.add(count);
				}
			}

			//otherwise, just add top 'n' players goals to top goals
			else {
				for (int i = 0; i < number_of; ++i) {
					top_counts.add(all_counts.get(all_counts.size() - 1 - i));
				}
			}

			//for all goals in top goals, print of player stats that have that many goals
			for (int count : top_counts) {
				for (Map.Entry<String, String> key : goals.entrySet()) {
					if (Integer.parseInt(goals.get(key.getKey())) == count) {
						player_summary(key.getKey());
					}
				}
			}
	}


	/**
	 * 
	 * Displays top 'n' assister player stats in the league
	 * 
	 * 
	 * @param number_of - 'n' number of assisters to display
	 */
	public static void top_assisters(int number_of) {
		System.out.println(String.format("Top %d Assisters:\n-----------------------------------\n", number_of));

		//arraylists to store all assist counts and top 'n' assist counts
		ArrayList<Integer> all_counts = new ArrayList<Integer>();
		ArrayList<Integer> top_counts = new ArrayList<Integer>();

		//add each player's assist stats into arraylist
		for (Map.Entry<String, String> set : assists.entrySet()) {
			all_counts.add(Integer.parseInt(set.getValue()));
		}

		//sort arraylist from high to low
		Collections.sort(all_counts);
		Collections.reverse(all_counts);


		//if less players than 'n', add all players assist counts to array list
		if (all_counts.size() < number_of) {
			for (int count : all_counts) {
				top_counts.add(count);
			}
		}

		//othersise, add top 'n' assist counts to array list
		else {
			for (int i = 0; i < number_of; ++i) {
				top_counts.add(all_counts.get(all_counts.size() - 1 - i));
			}
		}
		
		//for each assist count in top count array list, print each player summary with corresponding count
		for (int count : top_counts) {
			for (Map.Entry<String, String> key : assists.entrySet()) {
				if (Integer.parseInt(assists.get(key.getKey())) == count) {
					player_summary(key.getKey());
				}
			}
		}
	}

	/**
	 * Dispay top 'n' goalie summaries
	 * 
	 * 
	 * @param number_of - 'n' goalies to display
	 */
	public static void top_goalis(int number_of) {
		System.out.println(String.format("Top %d Goalies:\n-----------------------------------\n", number_of));

		//Arraylists to store all save counts and top save counts
		ArrayList<Integer> all_counts = new ArrayList<Integer>();
		ArrayList<Integer> top_counts = new ArrayList<Integer>();

		//add each goalie's save counts to arraylist
		for (Map.Entry<String, String> set : saves.entrySet()) {
			all_counts.add(Integer.parseInt(set.getValue()));
		}

		//sort all save counts from high to low
		Collections.sort(all_counts);
		Collections.reverse(all_counts);

		//if less goalies than 'n', just add all goalies saves to top saves list
		if (all_counts.size() < number_of) {
			for (int count : all_counts) {
				top_counts.add(count);
			}
		}

		//otherwise, add top 'n' goalies saves to top saves list
		else {
			for (int i = 0; i < number_of; ++i) {
				top_counts.add(all_counts.get(all_counts.size() - 1 - i));
			}
		}

		//print player summaries for all players who have one of top save counts
		for (int count : top_counts) {
			for (Map.Entry<String, String> key : saves.entrySet()) {
				if (Integer.parseInt(saves.get(key.getKey())) == count) {
					player_summary(key.getKey());
				}
			}
		}
	}


	/**
	 * Calculates and displays individual players score/save percentage
	 * @param player_name
	 */
	public static void score_and_save_percent(String player_name) {
		//get player position ,store in variable
		String position = positions.get(player_name).toLowerCase();
		//set player name to lower case to avoid having same player with different capitalization
		// if player position is goalie, calculate total saves divided by
		if ("goalie".equals(position)) {
			System.out.println(player_name + "'s save percentage is:");
			float save_percent = (Float.parseFloat((saves.get(player_name))) / (Float.parseFloat(shots_on_goalie.get(player_name)))) * 100;
			System.out.println(save_percent);
		}

		if (!"goalie".equals(position)){
			System.out.println(player_name + "'s goal percentage is:");
			float goal_percent = ((Float.parseFloat(goals.get(player_name))) / (Float.parseFloat(shots_on_goal.get(player_name)))) * 100;
			System.out.println(goal_percent);
		}
	}

	/**
	 * Displays the one of mean goals, assists, shots on goal, saves, shots on goalie, for each team
	 * 
	 * 
	 * @param map - desired stat hashmap
	 * @param table_name - desired name to display for statistics
	 */
	public static void mean_table(HashMap<String, String> map, String table_name) {
		//if table is empty, print error message and run method again
		if (map.values().size() == 0) {
			System.out.println("ERROR! Table is empty\n");
			mean_table(HashMap<String, String> map, String table_name);
		}

		//iterate through each team in table
		for (String team : teams.keySet()) {
			System.out.println(team + "\n-----------------------------------\n");
			//track sum of team values
			float sum_of_team_values = 0;
			//iterate through each player in team
			for (String player : teams.get(team)) {
				//increment total sum by player stat
				sum_of_team_values += Integer.parseInt(map.get(player));
			}
			//calculate mean of stat
			float mean = sum_of_team_values / (float)map.values().size();
			//print mean of team stat
			System.out.println(String.format("       -Mean of %s %s: %f", team, table_name, mean));
		}
		System.out.println("\n");

	}

	/**
	 * 
	 * Display median of desired stat for each team
	 * 
	 * @param map - desired stat hashmap
	 * @param table_name - desired name of stat to display
	 */
	public static void median_table(HashMap<String, String> map, String table_name) {
		//if table is empty, print error message and try again
		if (map.values().size() == 0) {
			System.out.println("ERROR! Table is empty\n");
			median_table(map, table_name);
		}

		//iterate through each team
		for (String team : teams.keySet()) {
			System.out.println(team + "\n-----------------------------------\n");
			//values arraylist to add stats
			ArrayList<Integer> values = new ArrayList<Integer>();
			//iterate through each player
			for (String player : teams.get(team)) {
				//if player has stat, append to values
				if (Integer.parseInt(map.get(player)) > 0) {
					values.add(Integer.parseInt(map.get(player)));
				}
			}

			//sort values
			Collections.sort(values);
			
			//if uneven length of values, get single median
			if (values.size() % 2 != 0) {
				int index = (int)Math.ceil((float)values.size() / 2f);
				System.out.println(index);
				System.out.println(String.format("       -Median of %s %s: %s", team, table_name, index));
			}

			//if even length of values, get two median values
			else {
				int first_median = values.size() / 2;
				int second_median = first_median + 1;
				System.out.println(String.format("       -Median of %s %s: %d, %d", team, table_name, values.get(first_median), values.get(second_median)));
			}
			System.out.println("\n");
		}
	}

	/**
	 * 
	 * Display sum of desired stat for each team
	 * 
	 * @param table
	 * @param table_name
	 */
	public static void sum_table(HashMap<String, String> table, String table_name) {

		//if table is empty, print error message and try again.
		if (table.values().size() == 0) {
			System.out.println("ERROR! That table is empty");
			sum_table(table, table_name);
		}

		//iterate through each team
		for (String team : teams.keySet()) {
			//track sum of stat
			int team_sum = 0;
			System.out.println(team + "\n-----------------------------------\n");
			//for each player in team, add count of stat to sum
			for (String player : teams.get(team)) {
				team_sum += Integer.parseInt(table.get(player));
			}
			//print sum for each team
			System.out.println(String.format("       -Sum of %s %s: %s", team, table_name, team_sum));
		}
		System.out.println("\n");




	}

	public static void min_table(HashMap<String, String> map, String table_name){
		if(map.values().size() == 0){
			System.out.println("ERROR! That table is empty");
		}

		int min_value =Integer.parseInt(Collections.min(map.values()));
		System.out.println(min_value);
		System.out.println("   is the minimum value from" + "\n" + table_name);
		}


	public static void max_table(HashMap<String, String> map, String table_name){
		if(map.values().size() == 0){
			System.out.println("ERROR! That table is empty");
		}

		int max_value =Integer.parseInt(Collections.max(map.values()));
		System.out.println(max_value);
		System.out.println("   is the maximum value from" + "\n" + table_name);
	}




/**************************************************************************************
 * UI SECTION *************************************************************************
 * ************************************************************************************
 * ************************************************************************************
 * ************************************************************************************
 * ************************************************************************************
 */


	/**
	 * Method to import team
	 * 
	 */
	public static void input_add_team() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please print a Team Name to Add");

		String response = input.nextLine();
		add_team(response);
	}

	/**
	 * 
	 * Method to import player
	 * 
	 */
	public static void input_add_player() {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter Player Data Individually:\nTeam Name\nPlayer Name\nAge\nCoutnry of Origin\nPosition\nJersey Number");
		
		//Take each value in indepentently from System.in
		String team_name = input.nextLine();
		String player = input.nextLine();
		String age = input.nextLine();
		String country = input.nextLine();
		String position = input.nextLine();
		String jersery = input.nextLine();

		//check each team to compare against desired team name
		boolean check = false;
		for (String team : teams.keySet()) {
			if (team_name.equals(team)) {
				check = true;
			}
		}
		//if team exists, add player
		if (check) {
			add_player(team_name, player, age, country, position, jersery);
		}
		//if not, print error message and try again
		else {
			System.out.println("Error. No Team with that name");
			input_add_player();
		}
		

	}

	/**
	 * 
	 * Add goal through System.in
	 * 
	 */
	public static void input_add_goal() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please Enter Player Name and Amount of Goals to Add");
		String player_name = input.nextLine();
		int goals = Integer.parseInt(input.nextLine());

		//check to see if player exists with desired name
		boolean check = false;
		for (String team : teams.keySet()) {
			for (String player : teams.get(team)) {
				if (player_name.equals(player)) {
					check = true;
				}
			}
		}

		//if player exists, add goals
		if (check) {
			add_goal(player_name, goals);
		}
		//if not, print error and try again
		else {
			System.out.println("Error. No Player with that name");
			input_add_goal();
			
		}
	}
	/**
	 * 
	 * Add saves through System.in
	 * 
	 */
	public static void input_add_save() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please Enter Player Name and Amount of Saves to Add");
		String name = input.nextLine();
		int saves = Integer.parseInt(input.nextLine());

		//see if player exists
		boolean check = false;
		for (String team : teams.keySet()) {
			for (String player : teams.get(team)) {
				if (name.equals(player)) {
					check = true;
				}
			}
		}
		//if exists, add saves
		if (check) {
			add_save(name, saves);
		}
		//if not, error message and try again
		else {
			System.out.println("Error. No Player with that name");
			input_add_save();
		}
	}

	public static void input_add_shot_on_goal() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please Enter Player Name and Amount of Shots-On-Goal to Add");
		String name = input.nextLine();
		int shots = Integer.parseInt(input.nextLine());

		boolean check = false;
		for (String team : teams.keySet()) {
			for (String player : teams.get(team)) {
				if (name.equals(player)) {
					check = true;
				}
			}
		}
		if (check) {
			add_shot_on_goal(name, shots);
		}
		else {
			System.out.println("Error. No Player with that name");
			input_add_shot_on_goal();
		}

	}


	public static void input_add_assist() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please Enter Player Name and Amount of Assists to Add");
		String name = input.nextLine();
		int assists = Integer.parseInt(input.nextLine());input.nextLine();

		boolean check = false;
		for (String team : teams.keySet()) {
			for (String player : teams.get(team)) {
				if (name.equals(player)) {
					check = true;
				}
			}
		}
		if (check) {
			add_assist(name, assists);
		}
		else {
			System.out.println("Error. No Player with that name");
			input_add_assist();
		}

	}

	public static void input_add_shot_on_goalie() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please Enter Player Name and Amount of Shots on Player to Add");
		String name = input.nextLine();
		int assists = Integer.parseInt(input.nextLine());

		boolean check = false;
		for (String team : teams.keySet()) {
			for (String player : teams.get(team)) {
				if (name.equals(player)) {
					check = true;
				}
			}
		}
		if (check) {
			add_shot_on_goalie(name, assists);
		}
		else {
			System.out.println("Error. No Player with that name");
			input_add_shot_on_goalie();
		}
		
	}

	public static void input_print_players_by_team() {
		Scanner input = new Scanner(System.in);
		boolean detail_choice;
		
		System.out.println("Please Enter (n)ame only or (s)ummarized detalils\n");
		String team = input.nextLine();
		String details = input.nextLine().toLowerCase();
		if ("n".equals(details)) {
			detail_choice = false; 
			print_players_by_team(team, detail_choice);
		}
		else if ("s".equals(details)) {
			detail_choice = true;
			print_players_by_team(team, detail_choice);

		}
		else {
			System.out.println("Error, not a valid choice\n");
			input_print_players_by_team();
		}
	}



	public static void input_print_league() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please Enter (n)ame only or (s)ummarized detalils\n");

		boolean detail_choice;
		String details = input.nextLine().toLowerCase();
		if ("n".equals(details)) {
			detail_choice = false; 
			print_league(detail_choice);
		}
		else if ("s".equals(details)) {
			detail_choice = true;
			print_league(detail_choice);

		}
		else {
			System.out.println("Error, not a valid choice\n");
			input_print_league();
		}
	}

	public static void input_top_scorers() {
		Scanner input = new Scanner(System.in);

		System.out.println("Top 'n' Scorers\n");

		int response = Integer.parseInt(input.nextLine());
		top_scorers(response);
	}

	public static void input_top_assisters() {
		Scanner input = new Scanner(System.in);

		System.out.println("Top 'n' Assisters\n");

		int response = Integer.parseInt(input.nextLine());
		top_assisters(response);
	}

	public static void input_top_goalis() {
		Scanner input = new Scanner(System.in);

		System.out.println("Top 'n' Goalis\n");

		int response = Integer.parseInt(input.nextLine());
		top_goalis(response);
	}

	public static void input_score_and_save_percent() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter Player Name");
		String player_name = input.nextLine();

		boolean check = false;
		for (String team : teams.keySet()) {
			for (String player : teams.get(team)) {
				if (player_name.equals(player)) {
					check = true;
				}
			}
		}
		if (check) {
			score_and_save_percent(player_name);
		}
		else {
			System.out.println("Error. No Player with that name");
			input_score_and_save_percent();
		}
	}

	public static void input_mean_table() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter a Stat: (g)oals, (s)aves, (a)ssists, shots_on_(goal), shots_on_(goalie)");
		String response = input.nextLine().toLowerCase();

		if ("g".equals(response)) {
			mean_table(goals, "Goals");
		}
		else if ("s".equals(response)) {
			mean_table(saves, "Saves");
		}
		else if ("a".equals(response)) {
			mean_table(assists, "Assists");
		}
		else if ("goal".equals(response)) {
			mean_table(shots_on_goal, "Shots On Goal");
		}
		else if ("goalie".equals(response)) {
			mean_table(shots_on_goalie, "Shots on Goalie;");
		}
		else {
			System.out.println("Not a Valid Input\n");
			input_mean_table();
		}
	}

	public static void input_median_table() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter a Stat: (g)oals, (s)aves, (a)ssists, shots_on_(goal), shots_on_(goalie)");
		String response = input.nextLine().toLowerCase();

		if ("g".equals(response)) {
			median_table(goals, "Goals");
		}
		else if ("s".equals(response)) {
			median_table(saves, "Saves");
		}
		else if ("a".equals(response)) {
			median_table(assists, "Assists");
		}
		else if ("goal".equals(response)) {
			median_table(shots_on_goal, "Shots On Goal");
		}
		else if ("goalie".equals(response)) {
			median_table(shots_on_goalie, "Shots on Goalie;");
		}
		else {
			System.out.println("Not a Valid Input\n");
			input_median_table();
		}
	}

	public static void input_sum_table() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter a Stat: (g)oals, (s)aves, (a)ssists, shots_on_(goal), shots_on_(goalie)");
		String response = input.nextLine().toLowerCase();

		if ("g".equals(response)) {
			sum_table(goals, "Goals");
		}
		else if ("s".equals(response)) {
			sum_table(saves, "Saves");
		}
		else if ("a".equals(response)) {
			sum_table(assists, "Assists");
		}
		else if ("goal".equals(response)) {
			sum_table(shots_on_goal, "Shots On Goal");
		}
		else if ("goalie".equals(response)) {
			sum_table(shots_on_goalie, "Shots on Goalie;");
		}
		else {
			System.out.println("Not a Valid Input\n");
			input_sum_table();
		}
	}

	public static void input_min_table() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter a Stat: (g)oals, (s)aves, (a)ssists, shots_on_(goal), shots_on_(goalie)");
		String response = input.nextLine().toLowerCase();

		if ("g".equals(response)) {
			min_table(goals, "Goals");
		}
		else if ("s".equals(response)) {
			min_table(saves, "Saves");
		}
		else if ("a".equals(response)) {
			min_table(assists, "Assists");
		}
		else if ("goal".equals(response)) {
			min_table(shots_on_goal, "Shots On Goal");
		}
		else if ("goalie".equals(response)) {
			min_table(shots_on_goalie, "Shots on Goalie;");
		}
		else {
			System.out.println("Not a Valid Input\n");
			input_min_table();
		}
	}

	
	public static void input_max_table() {
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter a Stat: (g)oals, (s)aves, (a)ssists, shots_on_(goal), shots_on_(goalie)");
		String response = input.nextLine().toLowerCase();

		if ("g".equals(response)) {
			max_table(goals, "Goals");
		}
		else if ("s".equals(response)) {
			max_table(saves, "Saves");
		}
		else if ("a".equals(response)) {
			max_table(assists, "Assists");
		}
		else if ("goal".equals(response)) {
			max_table(shots_on_goal, "Shots On Goal");
		}
		else if ("goalie".equals(response)) {
			max_table(shots_on_goalie, "Shots on Goalie;");
		}
		else {
			System.out.println("Not a Valid Input\n");
			input_max_table();
		}
	}

	

	public static void run() {
		Scanner input = new Scanner(System.in);
		System.out.println("1. Add Team\n2. Add Player\n 3. Add Goal\n 4. Add Save\n 5. Add Shot on Goal");
		System.out.println("6. Add Assist\n7. Add Shot on goalie\n 8. Players by Team");
		System.out.println("9. Print League\n10. Print Teams\n 11. Player with Most Goals");
		System.out.println("12. Top 'n' Scorers\n13. Print 'n' Top Assisters\n 14. Print 'n' Top Goalies");
		System.out.println("15. Score or Save Percentage\n16. Mean of Team Stat\n 17. Median of Team Stat");
		System.out.println("18. Sum of Team Stat\n19. Min of Team Stat\n 20. Max of Team Stat\n\n");

		String response = input.nextLine();

		if ("1".equals(response)) {
			input_add_team();
			input.nextLine();
			run();
		}
		else if ("2".equals(response)) {
			input_add_player();
			input.nextLine();
			run();
		}
		else if ("3".equals(response)) {
			input_add_goal();
			input.nextLine();
			run();
		}
		else if ("4".equals(response)) {
			input_add_save();
			input.nextLine();
			run();
		}
		else if ("5".equals(response)) {
			input_add_shot_on_goal();
			input.nextLine();
			run();
		}
		else if ("6".equals(response)) {
			input_add_assist();
			input.nextLine();
			run();
		}
		else if ("7".equals(response)) {
			input_add_shot_on_goalie();
			input.nextLine();
			run();
		}
		else if ("8".equals(response)) {
			input_print_players_by_team();
			input.nextLine();
			run();
		}
		else if ("9".equals(response)) {
			input_print_league();
			input.nextLine();
			run();
		}
		else if ("10".equals(response)) {
			print__teams();
			input.nextLine();
			run();
		}
		else if ("11".equals(response)) {
			highest_goals();
			input.nextLine();
			run();
		}
		else if ("12".equals(response)) {
			input_top_scorers();
			input.nextLine();
			run();
		}
		else if ("13".equals(response)) {
			input_top_assisters();
			input.nextLine();
			run();
		}
		else if ("14".equals(response)) {
			input_top_goalis();
			input.nextLine();
			run();
		}
		else if ("15".equals(response)) {
			input_score_and_save_percent();
			input.nextLine();
			run();
		}
		else if ("16".equals(response)) {
			input_mean_table();
			input.nextLine();
			run();
		}
		else if ("17".equals(response)) {
			input_mean_table();
			input.nextLine();
			run();
		}
		else if ("18".equals(response)) {
			input_sum_table();
			input.nextLine();
			run();
		}
		else if ("19".equals(response)) {
			input_min_table();
			input.nextLine();
			run();
		}
		else if ("20".equals(response)) {
			input_max_table();
			input.nextLine();
			run();
		}
		else {
			System.out.println("Error, not a valid response");
			run();
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
		shots_on_goalie = new HashMap<String, String>();


		all_maps = new ArrayList<HashMap<String, String>>();
		all_maps.add(ages);
		all_maps.add(countries);
		all_maps.add(positions);
		all_maps.add(jerseys);
		all_maps.add(goals);
		all_maps.add(saves);
		all_maps.add(assists);
		all_maps.add(shots_on_goal);



		run();











    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
}
