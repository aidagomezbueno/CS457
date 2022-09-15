import java.util.ArrayList;
import java.util.Random;

public class VacuumCleaner {

	ArrayList<Square> env;
	String location;
	boolean dirty;
	String action;
	int start;

	public VacuumCleaner(){
		this.location = null;
		this.dirty = false;
		this.start = createWorld();
	}

	public boolean getRandomBool() {
		Random random = new Random();
		return random.nextBoolean();
	}

	public int createWorld() {
		Random r = new Random();
		int nsquares = r.nextInt(1, 20);
		this.env = new ArrayList<Square>();
		for(int i=0;i<nsquares;i++) {
			this.env.add(i, new Square(getRandomBool()));
		}
		Random r2 = new Random();
		int loc = r2.nextInt(0, nsquares)+1;
		System.out.println("\nThe given array of squares to evaluate is:\n"+this.env + " starting in position " + loc + ".");
		return loc;
	}

	public ArrayList<Square> getEnv() {
		return env;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
