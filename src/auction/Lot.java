package auction;

public class Lot {
    
    private String artist;
    private String title;
    private int hammerPrice;
    
//    private Lot STATUE = new Lot("Felix W. de Weldon", "US MARINE MEMORIAL", 50000); 

    private Lot(String artist, String title, int upSetPrice) {
        this.artist = artist;
        this.title = title;
        this.hammerPrice = upSetPrice;
    }
    
    public static Lot make(String artist, String title, int upSetPrice){

        if (artist == null){
            return null;
        }
    
        if (title == null || title.length()<2){
            return null;
        }

        for (int i = 0; i <title.length(); i++) {
            if (Character.isLetter(title.charAt(i)) && !Character.isUpperCase(title.charAt(i))){
                return null;
            }
            if (title.charAt(i) == ',' || title.charAt(i) == '.'){ //lehet további írásjeleket is ellenőrízni!
                return null;
            }
        }

       if (upSetPrice <= 0){
            return null;
        }
        return new Lot(artist,title,upSetPrice);
       
    }

    public String getArtist() {
        return this.artist;
    }

    public String getTitle() {
        return this.title;
    }

    public int getHammerPrice() {
        return this. hammerPrice;
    }
    
    public void bid(int newLicit){
        if (newLicit > hammerPrice){
            hammerPrice = newLicit;
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%d GBP)", this.artist, this.title, this.hammerPrice);
    }
    
    public boolean moreExpensiveThan(Lot otherLot){
        if (otherLot != null && this.hammerPrice > otherLot.hammerPrice){
            return true;
        }
        return false;
    }
    
    
    
    
}
