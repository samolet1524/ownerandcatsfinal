package service;

import dao.CatDaoImpl;
import model.Cat;
import model.Owner;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import util.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CatServiceTest {
    @InjectMocks
    CatService catService = new CatService();
    @Mock
    CatDaoImpl catDao;


    @BeforeAll
    public void setUp() {
        catDao = Mockito.mock(CatDaoImpl.class);
    }

    @Test
    void getCatsByName() {
        when(catDao.findAll()).thenReturn(prepareDatabaseEntries());

        List<Cat> catsByName = catService.getCatsByName("Барсик");
        assertEquals(2, catsByName.size());
        List<Color> colors = new ArrayList<>();
        for (Cat cat : catsByName) {
            colors.add(cat.getColor());
        }
        assertTrue(colors.containsAll(Arrays.asList(Color.WHITE, Color.MULTICOLOR)));
    }


    @Test
    void getCatsByColor() {
        when(catDao.findAll()).thenReturn(prepareDatabaseEntries());

        List<Cat> catsByColor = catService.getCatsByColor(Color.MULTICOLOR);
        assertEquals(3, catsByColor.size());
        List<String> names = new ArrayList<>();
        for (Cat cat : catsByColor) {
            names.add(cat.getName());
        }
        assertTrue(names.containsAll(Arrays.asList("Барсик", "Мурка", "Муся")));
    }

    @Test
    void getCatOwner() {
        Owner owner = new Owner();
        owner.setName("Анна");
        owner.setBirthday(new Date());
        Cat cat = new Cat();
        cat.setCatId("1");
        cat.setName("Барсик");
        cat.setBirthday(new Date());
        cat.setColor(Color.WHITE);
        cat.setOwner(owner);
        when(catDao.findById(anyString())).thenReturn(cat);

        Owner catOwner = catService.getCatOwner("1");

        assertEquals(owner.getName(),catOwner.getName());
    }

    private List<Cat> prepareDatabaseEntries() {
        Owner owner1 = new Owner();
        owner1.setName("Анна");
        owner1.setBirthday(new Date());

        Owner owner2 = new Owner();
        owner2.setName("Иван");
        owner2.setBirthday(new Date());

        Owner owner3 = new Owner();
        owner3.setName("Лариса Петровна");
        owner3.setBirthday(new Date());

        Cat cat1 = new Cat();
        cat1.setCatId("1");
        cat1.setName("Барсик");
        cat1.setBirthday(new Date());
        cat1.setColor(Color.WHITE);
        cat1.setOwner(owner1);

        Cat cat2 = new Cat();
        cat2.setCatId("2");
        cat2.setName("Мурка");
        cat2.setBirthday(new Date());
        cat2.setColor(Color.MULTICOLOR);
        cat2.setOwner(owner1);

        Cat cat3 = new Cat();
        cat3.setCatId("3");
        cat3.setName("Матроскин");
        cat3.setBirthday(new Date());
        cat3.setColor(Color.RED);
        cat3.setOwner(owner2);

        Cat cat4 = new Cat();
        cat4.setCatId("4");
        cat4.setName("Муся");
        cat4.setBirthday(new Date());
        cat4.setColor(Color.MULTICOLOR);
        cat4.setOwner(owner3);

        Cat cat5 = new Cat();
        cat5.setCatId("5");
        cat5.setName("Рыжик");
        cat5.setBirthday(new Date());
        cat5.setColor(Color.BROWN);

        Cat cat6 = new Cat();
        cat6.setCatId("6");
        cat6.setName("Барсик");
        cat6.setBirthday(new Date());
        cat6.setColor(Color.MULTICOLOR);
        cat6.setOwner(owner3);

        List<Cat> allCatsFromDatabase = new ArrayList<>();
        allCatsFromDatabase.add(cat1);
        allCatsFromDatabase.add(cat2);
        allCatsFromDatabase.add(cat3);
        allCatsFromDatabase.add(cat4);
        allCatsFromDatabase.add(cat5);
        allCatsFromDatabase.add(cat6);
        return allCatsFromDatabase;
    }
}