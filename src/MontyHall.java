import java.util.Random;

// Lydia K Fritz
// CST105
// Date: Nov 16, 2018
// This is my own work.

public class MontyHall {

	static Random rnd = new Random();

	public static void main(String[] args) {

		int numWins = 0;

		// make an array of doors
		MysteryDoor[] doors = new MysteryDoor[3];

		for (int i = 0; i < doors.length; i++) {
			doors[i] = new MysteryDoor();
		}

		for (int j = 0; j < 100000; j++) {

			setPrize(doors);

			for (MysteryDoor o : doors) {
				if (o.hasPrize())
					System.out.print("PRIZE\t\t");
				else
					System.out.print("GOAT\t\t");
			}

			System.out.println('\n');
			int userPick, hostPick;

			userPick = rnd.nextInt(doors.length);
			System.out.println("You picked door #" + (userPick + 1));

			hostPick = hostPick(doors, userPick);

			System.out.println("The host opens door #" + (hostPick + 1));

			// user switches
			for (int i = 0; i < doors.length; i++) {
				if (!doors[i].isSelected()) {
					doors[userPick].deselect();
					doors[i].select();
					userPick = i;
					break;
				}
			}

			if (doors[userPick].hasPrize()) {
				System.out.println("You won by switching");
				System.out.println("The prize was behind door #" + (userPick + 1));
				numWins++;
			} else {
				System.out.println("You lost!");
			}
			resetDoors(doors);
		}
		
		System.out.println("you won " + numWins + " out of 100000 plays by switching.");

	}

	public static int hostPick(MysteryDoor[] doors, int userPick) {

		int hostChoice = rnd.nextInt(doors.length);

		while (hostChoice == userPick || doors[hostChoice].hasPrize())
			hostChoice = rnd.nextInt(doors.length);
		
		doors[hostChoice].select();

		return hostChoice;
	}
	
	public static void resetDoors(MysteryDoor[] doors){
		for(MysteryDoor d : doors){
			d.deselect();
			d.clearPrize();
		}
	}

	public static void setPrize(MysteryDoor[] doors) {
		
		int doorNum = rnd.nextInt(doors.length);
		doors[doorNum].setPrize();
	}

}

class MysteryDoor {
	private static Random rnd = new Random();
	private boolean prize;
	private boolean selected;

	public MysteryDoor() {
		selected = prize = false;
	}

	public void setPrize() {
		prize = true;
	}

	public boolean hasPrize() {
		return prize;
	}

	public void select() {
		selected = true;
	}

	public boolean isSelected() {
		return selected;
	}
	
	public void clearPrize(){
		prize = false;
	}

	public void deselect() {
		selected = false;
	}

}
