package service;

import dao.OwnerDaoImpl;
import model.Cat;
import model.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import util.Color;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OwnerServiceTest {
    @InjectMocks
    OwnerService ownerService = new OwnerService();
    @Mock
    OwnerDaoImpl ownerDao;


    @BeforeAll
    public void setUp() {
        ownerDao = Mockito.mock(OwnerDaoImpl.class);
    }

    @Test
    void getOwnerCats() {
        Owner owner = new Owner();
        owner.setName("Лариса Петровна");
        owner.setBirthday(new Date());

        Cat cat1 = new Cat();
        cat1.setCatId("1");
        cat1.setName("Барсик");
        cat1.setBirthday(new Date());
        cat1.setColor(Color.WHITE);
        cat1.setOwner(owner);

        Cat cat2 = new Cat();
        cat2.setCatId("2");
        cat2.setName("Мурка");
        cat2.setBirthday(new Date());
        cat2.setColor(Color.MULTICOLOR);
        cat2.setOwner(owner);
        Set<Cat> cats = new HashSet<>();
        cats.add(cat1);
        cats.add(cat2);
        owner.setCats(cats);
        when(ownerDao.findById(any())).thenReturn(owner);

        Owner ownerById = ownerService.getOwnerById("12345");
        assertEquals(2, ownerById.getCats().size());

    }
}