public class Square {

	private String state;

	public Square(boolean clean) {
		if(clean) {
			this.state = "Clean";
		}else {
			this.state = "Dirty";
		}
	}

	public boolean isClean() {
		if(this.state.equals("Clean")) {
			return true;
		}else {
			return false;
		}
	}

	public void setClean() {
		this.state = "Clean";
	}

	@Override
	public String toString() {
		return this.state;
	}
}
