import model.Cat;
import model.Owner;
import service.CatService;
import service.OwnerService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceMain {
    public static void main(String[] args) {
        Cat barsik = new Cat();
        barsik.setName("Barsik");
        Cat mursik = new Cat();
        mursik.setName("Mursik");
        Cat murka = new Cat();
        murka.setName("Murka");

        Set<Cat> cats = new HashSet<>();
        cats.add(murka);
        cats.add(mursik);

        Owner o1 = new Owner();
        o1.setName("Ivan");
        o1.setBirthday(new Date());
        o1.setCats(cats);
        murka.setOwner(o1);
        mursik.setOwner(o1);


        CatService catService = new CatService();
        OwnerService ownerService = new OwnerService();
        catService.createCat(barsik);
        catService.createCat(murka);
        ownerService.createOwner(o1);
        catService.makeFriends(barsik, murka);
        Cat       catById1             = catService.getCatById("7051b467-f802-465e-b011-ed3c2f642626");
        Owner     ownerById            = ownerService.getOwnerById("d01466e1-e801-4613-a501-7aac0c4397a1");
        List<Owner> allOwnersFromDatabase = ownerService.getAllOwnersFromDatabase();
        List<Cat> allCatsFromDatabase = catService.getAllCatsFromDatabase();
        int i=0;
    }
}
