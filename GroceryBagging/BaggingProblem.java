
import java.util.*;
import java.io.*;
import java.lang.reflect.ParameterizedType;

/**
 * This class is responsible for loading/storing the definition of a grocery
 * bagging problem,
 * and finding solutions to that problem.
 */
public class BaggingProblem {
    private int numBags;
    /** the number of available bags */
    private int maxBagSize;
    /** the maximum amount that a bag can contain */
    private HashMap<String, Item> items;
    /** the list of items to bag */

    Bag[] bags;

    PriorityQueue<Item> unpackedItems = new PriorityQueue<Item>();

    boolean[][] canpackwith;
    
    
    //private Integer emptyBag;

    /** This class provides the functionality of a grocery bag. */
    public class Bag {
        int id;
        int[] forbidden = new int[items.size()];
        ArrayList<Item> packedItems = new ArrayList<Item>();
        int weight = 0;

        public Bag() {
            this.id = id;
        }

        public Bag(Bag b){
            System.arraycopy(b.forbidden, 0, this.forbidden, numBags, maxBagSize);
            
        }
    }

    /** This class provides the functionality of an item */
    public class Item implements Comparable<Item> {
        String name;
        int size;
        int bag;
        int id;
        StringTokenizer conflicts;

        LinkedHashSet<Bag> canPackIn = new LinkedHashSet<Bag>();

        public Item(String name, int size, int id, StringTokenizer strtok) {
            this.name = name;
            this.size = size;
            this.id = id;
            this.conflicts = strtok;
            for (int x = 0; x < bags.length; x++) {
                canPackIn.add(bags[x]);
            }
        }

        public int compareTo(Item a) {
            return canPackIn.size() - a.canPackIn.size();
        }

    }

    void createBags() {
        bags = new Bag[numBags];
        for (int x = 0; x < bags.length; x++) {
            bags[x] = new Bag();
        }
    }

    public void printProblem() {
        System.out.println(numBags);
        System.out.println(maxBagSize);
        for (String iname : items.keySet()) {
            System.out.println(items.get(iname));
        }
    }

    /**
     * Parse the data in the file.
     * 
     * @param filename must be the name of a file that contains a valid spec for a
     *                 bagging problem.
     */
    void readData(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        numBags = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        createBags();
        maxBagSize = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        items = new HashMap<String, Item>();

        String line;
        // Read the items in
        while ((line = br.readLine()) != null) {
            StringTokenizer strtok = new StringTokenizer(line);
            Item i = new Item(strtok.nextToken(), Integer.parseInt(strtok.nextToken()), items.size(), strtok);
            items.put(i.name, i);
            unpackedItems.add(i);
        }

        canpackwith = new boolean[items.size()][items.size()];
        for (int i = 0; i < canpackwith.length; i++) {
            Arrays.fill(canpackwith[i], true);
        }

        for (String key : items.keySet()) {
            Item i = items.get(key);
            while (i.conflicts.hasMoreTokens()) {
                if (i.conflicts.nextToken().equals("+")) {
                    LinkedList<String> itemNames = new LinkedList<String>();
                    while (i.conflicts.hasMoreTokens()) {
                        itemNames.add(i.conflicts.nextToken());
                    }
                    for (String name : items.keySet()) {
                        if (!itemNames.contains(name)) {
                            canpackwith[i.id][items.get(name).id] = false;
                            canpackwith[items.get(name).id][i.id] = false;
                        }
                    }
                } else // negative case
                {
                    while (i.conflicts.hasMoreTokens()) {
                        String str = i.conflicts.nextToken();
                        canpackwith[i.id][items.get(str).id] = false;
                        canpackwith[items.get(str).id][i.id] = false;
                    }
                }
            }
        }

    }

    /**
     * @param filename must be the name of a file that contains a valid spec for a
     *                 bagging problem.
     */
    public BaggingProblem(String filename) throws Exception {
        readData(filename);
    }

    static public BaggingProblem runSearch(BaggingProblem startState){
        LinkedList<BaggingProblem> st = new LinkedList<BaggingProblem>();
        st.add(startState);    
    
        while(!st.isEmpty()){
            BaggingProblem curr = st.remove();
            if(curr.unpackedItems.size() == 0){
                return curr;
            }
            PriorityQueue<BaggingProblem> pqbp = new PriorityQueue<BaggingProblem>();
            for(Integer x : curr.unpackedItems.peek().canPackIn){
                if(x > curr.emptyBag){
                    break;
                }
                BaggingProblem newbp = new BaggingProblem(curr);
                Item nextItem = newbp.unpackedItems.remove();
                newbp.bags[x].packItemInBag(nextItem);
                
                System.out.println("Adding bp to queue with " + newbp.unpackedItems.size() + " left to pack.");
                System.out.println("newpb :\n" + newbp);

            }

            //runSearch(pqbp.remove());
            while(!pqbp.isEmpty()){
                System.out.println("Pushing on stack " + pqbp.peek().sumItemDomains());
                st.add(pqbp.remove());
            }
        }

        return null;
    }
}
