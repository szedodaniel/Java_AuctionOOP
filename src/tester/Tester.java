package tester;

import auction.Auction;
import auction.Lot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester {

    public static void main(String[] args) {
         Lot[] lots = new Lot[]
        { 
            Lot.make("Henri Matisse", "JACQUY", 350000),
            Lot.make("Henri Matisse", "YUQCAJ", 150000),
            Lot.make("Pablo Picasso", "PLANT DE TOMATES", 10000000),
            Lot.make("Marc Chagall", "LA TOUR EIFFEL", 500000)
        };
         
        Auction a = new Auction(lots);
        
        Lot lot = a.mostExpensive();
        
        System.out.println(a.browseLots("Henri Matisse"));
        System.out.println(a.priceOfCollection("Henri Matisse")+"GPB");
        System.out.println(lot);
        
       
        
      /*  System.out.println("Lot osztály ellenőrzése, a következő hibákat kell javítani:");
        partLotTest();
        System.out.println("");
        System.out.println("Auction osztály ellenőrzése, a következő hibákat kell javítani:");
        partAuctionTest();
        System.out.println("");
        System.out.println("Auction osztály metódusainak ellenőrzése, a következő hibákat kell javítani:");
        partAuctionMethodsTest();
        System.out.println("");
        */

       
    }

    private static void partLotTest(){
        // a muvesz nevenek ellenorzese
        Lot l1 = Lot.make(null, "PORTRAIT DE MADAME DUCAS", 400000); 
        check("Lot.make(): letrehozza az objektumot, ha a nev egy null referencia.", l1 == null);

        l1 = Lot.make("Salvador Dali", null, 400000); 
        check("Lot.make(): letrehozza az objektumot, ha a cim egy null referencia.", l1 == null);

        // a mualkotas cimenek ellenorzese
        l1 = Lot.make("Salvador Dali", "P", 400000);
        check("Lot.make(): tul rovid cim eseten is letrehozza az objektumot.", l1 == null);
        l1 = Lot.make("Salvador Dali", "Portrait de Madame Ducas", 400000);
        check("Lot.make(): akkor is letrehozza az objektumot, ha a cim nem kisbetuket is tartalmaz.", l1 == null);
        l1 = Lot.make("Salvador Dali", "PORTRAIT DE MADAM,E DUCAS", 400000);
        check("Lot.make(): vesszot tartalmazo cimnel is letrehozza az objektumot.", l1 == null);

        // az ar ellenorzese
        l1 = Lot.make("Salvador Dali", "PORTRAIT DE MADAME DUCAS", -400000);
        check("Lot.make(): negativ kikialtasi arnal is letrehozza az objektumot.", l1 == null);
        l1 = Lot.make("Salvador Dali", "PORTRAIT DE MADAME DUCAS", 0);
        check("Lot.make(): nulla kikialtasi arnal is letrehozza az objektumot.", l1 == null);

        // jol megadott parameterek eseten letre kell hozni az objektumot

        l1 = Lot.make("Salvador Dali", "PORTRAIT DE MADAME DUCAS", 400000);
        check("Lot.make(): helyes parameterekkel sem hozza letre az objektumot.", l1 != null);

        // helyes muvesznevet kapunk-e vissza
        check("getArtist(): a metodus nem adja vissza helyesen a muvesz nevet.", l1.getArtist().equals("Salvador Dali"));

        // helyesen adjuk-e vissza a leutesi arat
        check("getHammerPrice(): a metodus nem a helyes leutesi arat adja vissza, ha nem tortent licitalas.", l1.getHammerPrice() == 400000);

        int price = l1.getHammerPrice();
        // helyesen tortenik-e a licitalas
        l1.bid(l1.getHammerPrice() - 1);
        check("bid(): akkor is modosit az aron, ha a parameter alacsonyabb az arnal.", l1.getHammerPrice() == price);
        l1.bid(l1.getHammerPrice() + 1);
        check("bid(): nem modosit az aron, ha a parameter nagyobb az arnal.", l1.getHammerPrice() == price + 1);

        // a helyes szoveges reprezentaciot kapjuk-e vissza
        String s = l1.toString();
        check("toString(): a metodus nem a helyes szoveges reprezentaciot adja vissza. (szerzo)", s.contains("Salvador Dali"));

        check("toString(): a metodus nem a helyes szoveges reprezentaciot adja vissza. (cim)", s.contains("PORTRAIT DE MADAME DUCAS"));
        check("toString(): a metodus nem a helyes szoveges reprezentaciot adja vissza. (ar)", s.contains((price + 1) + " GBP"));

        // melyik a dragabb?
        Lot l2 = Lot.make("Roberto Matta", "AZUL", 120000);
        check("moreExpensiveThan(): hamisat ad vissza, amikor a parameter mutargy az olcsobb.", l1.moreExpensiveThan(l2));
        check("moreExpensiveThan(): igazat ad vissza, amikor a parameter mutargy a dragabb.", !l2.moreExpensiveThan(l1));
        check("moreExpensiveThan(): igazat ad, amikor a parameter null.", !l1.moreExpensiveThan(null));

        l2 = Lot.make("Roberto Matta", "AZUL", l1.getHammerPrice());
        check("moreExpensiveThan(): igazat ad vissza, amikor a parameter mutargy ugyanannyiba kerul.", !l2.moreExpensiveThan(l1));
    }

    private static void partAuctionTest(){
        Lot[] lots = new Lot[]
        { 
            Lot.make("Henri Matisse", "JACQUY", 350000),
            Lot.make("Pablo Picasso", "PLANT DE TOMATES", 10000000),
            Lot.make("Marc Chagall", "LA TOUR EIFFEL", 500000)
        };

        Auction a = new Auction(lots);

        check("numberOfLots: nem megfelelo szamot adja vissza.", a.numberOfLots() == lots.length);

        Lot l = lots[0];
        // adatszivargas ellenorzese
        String s = a.toString();
        lots[0] = Lot.make("Edvard Munch", "THE SCREAM", 1000000);
        check("konstruktor: szivarog a belso allapot.", !s.contains(lots[0].toString()));

        lots[0] = l;

        // helyes-e a szoveges reprezentacio
        for (Lot lot : lots)
            check("toString: hianyzik mualkotas. (" + lot.getTitle() + ")", s.contains(lot.toString()));
    }
    
    
    private static void partAuctionMethodsTest(){
        Lot[] lots = new Lot[]
        { 
            Lot.make("Salvador Dali", "MOMENT DE TRANSITION", 6000000),
            Lot.make("Pablo Picasso", "PLANT DE TOMATES", 10000000),
            Lot.make("Salvador Dali", "ARGOS", 100000),
            Lot.make("Marc Chagall", "LA TOUR EIFFEL", 500000)
        };

        Auction a = new Auction(lots);

        // browseLots tesztelese
        List<Lot> list1 = a.browseLots("Joan Miro");
        check("browseLots(): nullt adott, ha nincsenek mutargyai egy muvesznek.", list1 != null);
        check("browseLots(): nem ures listat adott, ha nincsenek mutargyai egy muvesznek.", list1.size()==0);
        List<Lot> list2 = a.browseLots("Salvador Dali");
        check("browseLots(): adott muvesz eseten (ha letezik olyan mutargy, amelyet a muvesz keszitett) nem jol vegzi a keresest.", list2.size()==2 && list2.contains(lots[0]) && list2.contains(lots[2]));

        Map<String, Long> prices = new HashMap<>();
        prices.put("Salvador Dali", 6000000l + 100000l);
        prices.put("Marc Chagall", 500000l);
        prices.put("Pablo Picasso", 10000000l);

        // osszegzes
        for (Map.Entry<String, Long> e : prices.entrySet())
            check("priceOfCollection(): rosszul osszegez, ha az alkotonak van legalabb egy muve.", a.priceOfCollection(e.getKey()) == e.getValue().longValue());
        
        check("priceOfCollection(): rosszul osszegez, ha az alkotonak nincs muve.", a.priceOfCollection("Henry Moore") == 0);

        // a legdragabb mutargy megkeresese
        Lot lot = a.mostExpensive();
        check("mostExpensive(): a metodus nem jol valasztja ki a legregebbi mutargyat, ha nincsenek mutargyak a listaban.", new Auction(new Lot[0]).mostExpensive() == null);
    }
    
    private static void check(String failMessega, boolean isWrite){
        if (!isWrite){
            System.out.println(failMessega);
        }
    }

    
}
