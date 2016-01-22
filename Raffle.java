/**
 * ITCS 3112 Assignment 1 
 * @author Dillon Mabry Student ID: 800854402
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Raffle Class: Driver program to display menu for raffle options to process
 * tickets
 * 
 * @author Dillon
 *
 */
public class Raffle {

	private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	private static long winningNumber = 0;

	/**
	 * Main method
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("------------------------------------------------");
			System.out.println("Welcome to Raffle Ticket Application 2015!");
			System.out.println("Press 0 to enter the winning number.");
			System.out.println("Press 1 to enter Ticket numbers and participants");
			System.out.println("Press 2 to list out the participants");
			System.out.println("Press 3 to calculate the winners");
			System.out.println("Press 4 to exit program");
			System.out.println("------------------------------------------------");
			try {
				int choice = sc.nextInt(); // CHOICE FOR MAIN MENU SELECTION
				switch (choice) {
				case 0:
					while (true) {
						try {
							System.out.println("Enter the winning number: ");
							winningNumber = sc.nextLong();
							String strs = String.valueOf(winningNumber);
							if (strs.length() != 6) {
								System.out.println("Please enter a valid number with 6 digits!");
							} else {
								System.out.println("The winning number is now: " + winningNumber);
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Please enter a valid number with 6 digits!");
							sc.next();
							continue;
						}
					}
					break;
				case 1:
					while (true) {
						Ticket ticket = new Ticket();
						System.out.println("Please enter the ticket holder name: ");
						String name = sc.next();
						boolean hasName = false;
						for (Ticket t : tickets) { // CHECK FOR EXISTING USERS
							if (t.getName().equals(name)) {
								hasName = true;
								break;
							}
						}

						if (hasName == true) { // CHECK IF USER ALREADY EXISTS
							System.out.println("Sorry! A ticket already has this name.");
							System.out.println("Returning to main menu....");
							break;
						}

						ticket.setName(name); // SET THE TICKET HOLDER NAME
						while (true) {
							try {
								System.out.println("Please enter the ticket raffle number: ");
								long number = sc.nextLong();
								String strs = String.valueOf(number);
								if (strs.length() == 6) { // IF NUMBER IS 6
															// DIGITS SET
									ticket.setTicketNumber(number);
									tickets.add(ticket);
									break;
								} else {
									System.out.println("Please enter a valid number with 6 digits!");
								}
							} catch (InputMismatchException e) {
								System.out.println("Input error. Please enter a " + " valid number with 6 digits!\n");
								sc.next();
								continue;
							}
						}

						System.out.println("The ticket holder name is " + ticket.getName());
						System.out.println("The ticket holder number is " + ticket.getTicketNumber());
						System.out.println("Do you wish to add another ticket? Yes or No: ");
						String input = sc.next();
						if (input.toLowerCase().equals("yes") || input.toLowerCase().equals("y")) {
							// DO NOTHING - CONTINUE LOOPING
						} else if (input.toLowerCase().equals("no") || input.toLowerCase().equals("n")) {
							System.out.println("Returning to menu...."); // BREAK
																			// AND
																			// RETURN
																			// TO
																			// MENU
							break;
						} else {
							System.out.println("Invalid input, returning to main menu....");
							break;
						}
					}
					break;
				case 2:
					if (tickets.isEmpty()) { // IF TICKETS ARRAY IS EMPTY
						System.out.println("No current participants!");
					} else {
						System.out.println(tickets.toString());
					}
					break;
				case 3:
					System.out.println("Calculating raffle winners....\n");
					String winner = String.valueOf(winningNumber);
					StringBuilder buffer = new StringBuilder(winner);
					String reverse = buffer.reverse().toString();
					String firstPlace = null, secondPlace = null, thirdPlace = null;
					for (Ticket t : tickets) {
						String string = String.valueOf(t.getTicketNumber());
						if (string.equals(winner)) {
							firstPlace = t.getName();
						} else if (string.equals(reverse)) {
							secondPlace = t.getName();
						} else if (matcher(winner, string)) {
							thirdPlace = t.getName();
						}
					}
					if (firstPlace == null && secondPlace == null && thirdPlace == null) {
						System.out.println("No winners have been found!\n");
					} else {
						if (firstPlace != null && secondPlace == null && thirdPlace == null) {
							System.out.println("First place winner: " + firstPlace);
						} else if (firstPlace != null && secondPlace != null && thirdPlace == null) {
							System.out.println("First place winner: " + firstPlace);
							System.out.println("Second place winner: " + secondPlace);
						} else if (firstPlace == null && secondPlace != null && thirdPlace == null) {
							System.out.println("Second place winner: " + secondPlace);
						} else if (firstPlace == null && secondPlace == null && thirdPlace != null) {
							System.out.println("Third place winner: " + thirdPlace);
						} else if (secondPlace != null && firstPlace == null && thirdPlace != null) {
							System.out.println("Second place winner: " + secondPlace);
							System.out.println("Third place winner: " + thirdPlace);
						} else if (secondPlace == null && firstPlace != null && thirdPlace != null) {
							System.out.println("First place winner: " + firstPlace);
							System.out.println("Third place winner: " + thirdPlace);
						} else if (firstPlace != null && secondPlace != null && thirdPlace != null) {
							System.out.println("First place winner: " + firstPlace);
							System.out.println("Second place winner: " + secondPlace);
							System.out.println("Third place winner: " + thirdPlace);
						}
					}
					System.out.println("Returning to main menu and clearing " + " all from participants list....");
					tickets.clear();
					break;
				case 4:
					System.out.println("Program exited!");
					sc.close();
					System.exit(0);
				} // END SWITCH-STATEMENT FOR MENU
			} catch (InputMismatchException e) {
				System.out.println("Input Error. Please enter a choice between 0 and 4!");
				sc.next();
				continue;
			} // END TRY-CATCH FOR MENU
		} // END WHILE LOOP

	} // END OF MAIN

	
	public static boolean matcher(String winner, String ticket) {
		
		boolean match = false;
		int i = 0, j = 0;
		for (i = 0; i < winner.length() - 1; i++) {
			for (j = 0; j < ticket.length() - 1; j++) {
				if ((winner.charAt(i) == ticket.charAt(j)) && i < 4) {
					if ((winner.charAt(i + 1) == ticket.charAt(j + 1)) && i < 4) {
						if ((winner.charAt(i + 2) == ticket.charAt(j + 2)) && i < 4) {
							match = true;
						}
					}
				}
			}
		}
		return match;
	} //MATCHER METHOD

} // END OF CLASS
