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

	/*public void reflexVacuumAgent(int location) {

	}*/

	public boolean getRandomBool() {
		Random random = new Random();
		return random.nextBoolean();
	}

	//crea el mundo y devuelve posicion de arranque
	public int createWorld() {
		Random r = new Random();
		int nsquares = r.nextInt(1, 20);
		/*Random r = new Random();
		int low = 10;
		int high = 100;
		int result = r.nextInt(high-low) + low;*/
		this.env = new ArrayList<Square>();
		for(int i=0;i<nsquares;i++) {
			this.env.add(i, new Square(getRandomBool()));
		}
		System.out.println(this.env);
		Random r2 = new Random();
		int loc = r2.nextInt(nsquares);
		//System.out.println("Starting position: " + loc);
		return loc;
	}


	public void suck(int loc) {
		int pos = loc++;
		System.out.println(this.env.get(pos));
		this.env.get(pos).setClean();
		System.out.println("Position"+ pos +this.env.get(pos));
	}

	public int moveRight(int loc) {
		return loc++;
	}

	public int moveLeft(int loc) {
		return loc--;
	}

	public boolean check(int loc) {
		if(this.env.get(loc).isClean()) {
			return true;
		}else {
			return false;
		}
	}

	public ArrayList<Square> getEnv() {
		return env;
	}

	public void setEnv(ArrayList<Square> env) {
		this.env = env;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	

}
