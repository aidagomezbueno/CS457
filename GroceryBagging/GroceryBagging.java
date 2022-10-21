import java.io.*;
import java.time.*;
import java.util.*;

public class GroceryBagging{

    private int numBags;
    private int maxBagSize;
    private HashMap<String, Item> items;

    Bag[] bags;

    public class Bag{
        int remainingSize;
        HashMap<String, Item>  PackedItems = new HashMap<String, Item>();
        int id;

        public Bag(int id){
            this.remainingSize = maxBagSize;
            this.id = id;
        }

        public boolean canPack(Bag b, Item item){
            boolean canPack = true;
            for (String key: b.PackedItems.keySet()) {
                Item i = b.PackedItems.get(key);
                
                if(i.cannotPackWith.contains(item.name)){
                    return false;
                }
            }
            return canPack;
        }

        public void updateSize(int itemSize){
            this.remainingSize = this.remainingSize - itemSize;
        }
    }
    
    public class Item {
        String name;
        int size;
        int bag;
        int id;
        
        StringTokenizer conflicts;
        
        LinkedHashSet<String> cannotPackWith = new LinkedHashSet<String>();

        public Item(String name, int size, int id, StringTokenizer strtok){
            this.size = size;
            this.name = name;
            this.id = id;
            this.conflicts = strtok;

            for (int i = 0; i < bags.length; i++){
                if(size > maxBagSize){
                    System.out.println("failure");
                    return;
                }                
            }
        }
        
    }

    void createBags(){
        bags = new Bag[numBags];
        for(int i = 0; i < bags.length; i++){
            bags[i] = new Bag(i+1);
        }
    }

    void readData(String fileName) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        numBags = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        createBags();

        maxBagSize = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        items = new HashMap<String, Item>();

        String line;
        while((line = br.readLine()) != null){
            StringTokenizer strtok = new StringTokenizer(line);
            Item i = new Item(strtok.nextToken(), Integer.parseInt(strtok.nextToken()), items.size(), strtok);
            items.put(i.name, i);
        }

        for (String key: items.keySet()) {
            Item i = items.get(key);
            while(i.conflicts.hasMoreTokens()){
                if(i.conflicts.nextToken().equals("+")){
                    LinkedList<String> itemNames = new LinkedList<String>();
                    while(i.conflicts.hasMoreTokens()){
                        itemNames.add(i.conflicts.nextToken());
                    }
                    for(String name: items.keySet()){
                        if(!itemNames.contains(name)){
                            if(!i.name.equals(items.get(name).name)){
                                i.cannotPackWith.add(items.get(name).name);
                                items.get(name).cannotPackWith.add(i.name);
                            }
                        }
                    }
                }else{
                    while(i.conflicts.hasMoreTokens()){
                        String str = i.conflicts.nextToken();
                        if(!i.name.equals(items.get(str).name)){
                            i.cannotPackWith.add(items.get(str).name);
                            items.get(str).cannotPackWith.add(i.name);
                        }
                    }
                }
            }
        }
    }

    public Item Search(HashMap<String, Item> items){
        Item mostConstrained = items.get(items.keySet().toArray()[0]);

        for (String key: items.keySet()) {
            Item i = items.get(key);
            if(i.cannotPackWith.size() > mostConstrained.cannotPackWith.size()){
                mostConstrained = i;
            }else if(i.cannotPackWith.size() == mostConstrained.cannotPackWith.size()){
                if(i.size > mostConstrained.size){
                    mostConstrained = i;
                }
            }
        }
        
        return mostConstrained;
    }

    boolean Bagging(HashMap<String, Item> items){
        boolean packed = false;
        Item item = Search(items);
        
        for(int i = 0; i < numBags; i++){
            if(bags[i].canPack(bags[i], item)){
                if(bags[i].remainingSize < item.size){
                    //break;
                    continue;
                }else{
                    bags[i].PackedItems.put(item.name, item);
                    bags[i].updateSize(item.size);
                    packed = true;
                    break;
                }
            }
        }
        items.remove(item.name, item);
        return packed;
    }

    public static void main(String args[]){
        GroceryBagging gb = new GroceryBagging();
        try{
            gb.readData(args[0]);
        }catch (Exception e){
            System.out.println(e);
        }
        for(int i = 0; i < gb.numBags; i++){
            gb.bags[i] = gb.new Bag(i);
        }
        boolean bagged = false;

        Instant start = Instant.now();    
        
        while(!gb.items.isEmpty()){
            bagged = gb.Bagging(gb.items);
            if(bagged == false){
                break;
            }
        }

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toNanos();
        System.out.println("Time elapsed: " + (double)timeElapsed/1000000 + " ms");

        if(bagged == false){
            System.out.println("failure");
        }else{            
            System.out.println("success");
            
            for(int i = 0; i < gb.bags.length; i++){
                System.out.println(gb.bags[i].PackedItems.keySet().toString()
                        .replace(", ", "\t")
                        .replace("[", "")
                        .replace("]", ""));                
            }
        }

        // try{
        //     FileWriter myWriter = new FileWriter("output.txt");
        //     if(bagged == false){
        //         myWriter.write("failure");
        //     }else{            
        //         myWriter.write("success\n");
                
        //         for(int i = 0; i < gb.bags.length; i++){
                    
        //                 myWriter.write(gb.bags[i].PackedItems.keySet().toString()
        //                     .replace(", ", "\t")
        //                     .replace("[", "")
        //                     .replace("]", ""));
        //                 myWriter.write("\n");
                    
        //         }
        //         myWriter.close();
        //     }
        // }catch (IOException e) {
        //     System.out.println("An error occurred.");
        //     e.printStackTrace();
        // }
    }
}    
