/**
 * ITCS 3112 Java Assignment 1
 * @author Dillon Mabry Student ID: 800854402
 *
 */

/**
 * Ticket Class: Used to generate a random ticket number for each new ticket to
 * be processed
 * 
 * @author Dillon
 *
 */
public class Ticket {

	private long ticketNumber = 0;
	private String name = null;

	/**
	 * Default constructor for tickets
	 */
	public Ticket() {

	}

	/**
	 * Getter for receiving the ticket number
	 * 
	 * @return returns the long ticket number
	 */
	public long getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * Setter to set the ticket number
	 * 
	 * @param ticketNumber
	 *            the number to set the ticket to
	 */
	public void setTicketNumber(long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * Getter to get the ticket holder name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter to set the name of the ticket holder
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Overridden toString method for the Ticket class Prints out the ticket
	 * holder name then the ticket number
	 */
	@Override
	public String toString() {
		return ("Ticket holder name: " + this.getName() + " Raffle Number: " + this.getTicketNumber() + "\n");
	}

}
