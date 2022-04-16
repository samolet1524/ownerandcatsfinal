package service;

import dao.CatDaoImpl;
import model.Cat;
import model.Owner;
import util.Color;

import java.util.ArrayList;
import java.util.List;

public class CatService {
    CatDaoImpl catDao = new CatDaoImpl();

    public void createCat(Cat cat){
        catDao.create(cat);
    }

    public void updateCat(Cat cat){
        catDao.update(cat);
    }

    public List<Cat> getAllCatsFromDatabase(){
        return catDao.findAll();
    }

    public Cat getCatById(String id){
        return catDao.findById(id);
    }

    public void makeFriends(Cat c1, Cat c2){
        c1.getFriends().add(c2);
        c2.getFriends().add(c1);
        catDao.update(c1);
        catDao.update(c2);
    }

    public List<Cat> getCatsByName(String name){
        List<Cat> allCatsFromDatabase = getAllCatsFromDatabase();
        List<Cat> result = new ArrayList<>();
        for (Cat cat : allCatsFromDatabase) {
            if(cat.getName().equals(name)){
                result.add(cat);
            }
        }
        return result;
    }

    public List<Cat> getCatsByColor(Color color){
        List<Cat> allCatsFromDatabase = getAllCatsFromDatabase();
        List<Cat> result = new ArrayList<>();
        for (Cat cat : allCatsFromDatabase) {
            if(cat.getColor().equals(color)){
                result.add(cat);
            }
        }
        return result;
    }

    public Owner getCatOwner(String id){
        Cat catById = getCatById(id);
        return catById.getOwner();
    }

}
