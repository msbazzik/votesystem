package com.javaapp.votesystem.repository.datajpa;

//import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.javaapp.votesystem.model.User;
import com.javaapp.votesystem.repository.UserRepository;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {
   // private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

//    private final CrudUserRepository crudRepository;
//
//    public DataJpaUserRepository(CrudUserRepository crudRepository) {
//        this.crudRepository = crudRepository;
//    }

    @Override
    public User save(User user) {
        return null;// crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return true; //crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return null; //crudRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return null;//crudRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return null;// crudRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User getWithMeals(int id) {
        return null;// crudRepository.getWithMeals(id);
    }
}
