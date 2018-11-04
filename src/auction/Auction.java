package auction;

import java.util.ArrayList;
import java.util.List;

public class Auction {
    
    private List<Lot> lotList;
    
    public Auction(Lot[] lotArray){
        this.lotList = new ArrayList<>();
        
        for (int i = 0; i < lotArray.length; i++) {
            this.lotList.add(lotArray[i]);
        }
    }
 
    public int numberOfLots(){
        return this.lotList.size();
    }

    @Override
    public String toString() {
        String s = "";
        
        for (Lot l : this.lotList) {
            s += l + "\n";
        }
        
        return s;    
    }
    
    public List<Lot> browseLots(String artist){
        List<Lot> browseLotList = new ArrayList<>();
        
        for (Lot l : this.lotList) {
            if (l.getArtist().equals(artist)){
                browseLotList.add(l);
            }
        }
        return browseLotList;
    }
        
    public long priceOfCollection(String artist){
        long s = 0;
        for (Lot l : this.lotList) {
            if (l.getArtist().equals(artist)){
                s += l.getHammerPrice();
            }
        }        
        return s;
    }
    
    public Lot mostExpensive(){
        Lot max = null;
        for (int i = 1; i < this.lotList.size(); i++) {
            if (this.lotList.get(i).moreExpensiveThan(max)){
                max = this.lotList.get(i);
            }
        }
        return max;
    }
    
    
}
