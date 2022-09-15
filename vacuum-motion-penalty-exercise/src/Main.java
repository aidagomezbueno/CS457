//import java.util.Random;

public class Main {

	//private static int nsquares;
	private static int startp;
	private static int asize;
	//private static int pos;

	public static void main(String[] args) {
		
		
		int loc;
		//nsquares = Integer.parseInt(args[0]);
		VacuumCleaner vc = new VacuumCleaner();
		startp = vc.getStart(); //final
		//vc.createWorld();
		//vc.work(vc.start);

		//Random r = new Random();
		//int startpos = r.nextInt(2); 

		loc = vc.getStart(); //punto inicial de partida - can change
		asize = vc.getEnv().size();
		//int pos = 0;
		//System.out.println(loc);
		
		for(int i=loc; i<asize; i++) {
			if(vc.getEnv().get(i).isClean()) {
				//System.out.println(vc.getEnv().get(i));
				
				//System.out.println(vc.getEnv().indexOf(vc));
				//System.out.println(vc.getEnv().get(i));
				
				loc++;
				//System.out.println(loc);
				//System.out.println(loc);
				//System.out.println(vc.getEnv().get(i));
			}else {
				vc.getEnv().get(i).setClean();
				loc++;
			}
			//pos = i;
		}
		
		System.out.println("Esto en que es la siguiente pos a considerar  " + loc);
		System.out.println(asize);
		System.out.println(startp);
		//TODO NO ENTRA
		
		if(loc==asize) {
			if(startp!=0) {
				loc--;
				System.out.println("Pos en array" +loc);
				//System.out.println("Entra");
				//System.out.println(vc.getEnv().get(loc-1).isClean());
				//System.out.println("es"+vc.getEnv().get(loc-1)+""+vc.getEnv().indexOf(vc.getEnv().get(loc-1)
						//));
				
				for(int j=loc; loc>=0; j--) {
					if(vc.getEnv().get(j).isClean()) {
						loc--;
					}else {
						vc.getEnv().get(j).setClean();
						loc--;
					}
				}
				
				/*
				
				if(vc.getEnv().get(loc).isClean()==false) {
					vc.getEnv().get(loc).setClean();
					loc--;
				}/*
				if(vc.getEnv().get(loc-1).isClean()) {
					System.out.println("Entra");
					vc.getEnv().get(loc-1).setClean();
					
					//System.out.println(loc);
				}else {
					System.out.println("Entra");
					for(int j = loc; j==0; j--) {
						if(vc.getEnv().get(j).isClean()==false) {
							System.out.println("Entra bluce");
							vc.getEnv().get(j).setClean();
							loc--;
							System.out.println(loc);
						}
					}
				}*/
			}
			
			//System.out.println(vc.getEnv());
		}
		//System.out.println(vc.getEnv());
		
		
		
		System.out.println(vc.getEnv());
		
		
		
		/*
		if(loc!=1) { //si no empiezas al principio
			while(loc<asize) {
				//pos = loc;
				if(vc.check(loc)==true) {
					vc.moveRight(loc);
					System.out.println("Position " + loc + " clean. Move right.");
				}else {
					System.out.println("Position " + loc + " dirty. Suck the dirty and move right.");
					vc.suck(loc);
					vc.moveRight(loc);
				}
			} 
			if(loc==asize) {
				while(loc>0) {
					if(vc.check(loc)) {
						vc.moveLeft(loc);
					}else {
						vc.suck(loc);
						vc.moveLeft(loc);
					}
				}
				if(loc==0) {
					System.out.println("World entirely cleaned.");
				}
			}
		}else {
			while(loc<asize) {
				if(vc.check(loc)) {
					vc.moveRight(loc);
				}else {
					vc.suck(loc);
					vc.moveRight(loc);
				}
			}if(loc==asize) {
				if(!vc.check(loc)) {
					vc.suck(loc);
				}
			}
		}
		System.out.println(vc.getEnv());*/
	}


/*public void printEnv(int nsquares) {
		switch(nsquares){
		case 1:
			System.out.println("--------\n");
			System.out.println("|        |\n");System.out.println("|        |\n");System.out.println("|        |\n");System.out.println("|        |\n");
			System.out.println("--------");

		}
	}*/

}
