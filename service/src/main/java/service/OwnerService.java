package service;

import dao.OwnerDaoImpl;
import model.Cat;
import model.Owner;

import java.util.List;
import java.util.Set;

public class OwnerService {

    OwnerDaoImpl ownerDao = new OwnerDaoImpl();

    public void createOwner(Owner owner) {
        ownerDao.create(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDao.update(owner);
    }

    public List<Owner> getAllOwnersFromDatabase() {
        return ownerDao.findAll();
    }

    public Owner getOwnerById(String id) {
        return ownerDao.findById(id);
    }

    public Set<Cat> getOwnerCats(String id){
        Owner ownerById = getOwnerById(id);
        return ownerById.getCats();
    }
}
