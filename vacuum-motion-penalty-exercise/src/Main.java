public class Main {

	private static int startp;
	private static int asize;
	private static int nactions;
	private static int avgscore;

	public static void main(String[] args) {
		int cont = 0;
		avgscore = 0;
		while(cont<=100) {
			nactions = 0;
			int loc;
			VacuumCleaner vc = new VacuumCleaner();
			startp = vc.getStart(); //final
			loc = vc.getStart(); //punto inicial de partida - can change
			asize = vc.getEnv().size();
			for(int i=loc; i<asize; i++) {
				if(vc.getEnv().get(i).isClean()) {
					loc++;
					nactions++;
				}else {
					vc.getEnv().get(i).setClean();
					loc++;
					nactions+=2;
				}
			}
			if(loc==asize) {
				if(startp!=0) {
					loc--;
					//System.out.println("Pos en array" +loc);
					for(int j=loc; loc>=0; j--) {
						if(vc.getEnv().get(j).isClean()) {
							loc--;
							nactions++;
						}else {
							vc.getEnv().get(j).setClean();
							loc--;
							nactions+=2;
						}
					}
				}
			}
			cont++;
			avgscore+=nactions;
			System.out.println("\nThe ended and cleaned result of the array is:\n" + vc.getEnv());
			System.out.println("\nThe performance score of this configuration is: " + nactions + " actions.\n\n****************************");
		}
		avgscore = avgscore/(cont-1);
		System.out.println("\nThe overall average score is: " + avgscore + ".");
	}
}
