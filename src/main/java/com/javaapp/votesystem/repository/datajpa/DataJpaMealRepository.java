package com.javaapp.votesystem.repository.datajpa;

import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

//    private final CrudMealRepository crudMealRepository;
//    private final CrudUserRepository crudUserRepository;

//    public DataJpaMealRepository(CrudMealRepository crudMealRepository, CrudUserRepository crudUserRepository) {
//        this.crudMealRepository = crudMealRepository;
//        this.crudUserRepository = crudUserRepository;
//    }

    @Override
   // @Transactional
    public Meal save(Meal meal, int userId) {
//        if (!meal.isNew() && get(meal.getId(), userId) == null) {
//            return null;
//        }
//        meal.setUser(crudUserRepository.getOne(userId));
//        return crudMealRepository.save(meal);
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
       // return crudMealRepository.delete(id, userId) != 0;
        return true;
    }

    @Override
    public Meal get(int id, int userId) {
//        return crudMealRepository.findById(id)
//                .filter(meal -> meal.getUser().getId() == userId)
//                .orElse(null);
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return null; //crudMealRepository.getAll(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null; //crudMealRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
    }

    @Override
    public Meal getWithUser(int id, int userId) {
        return null; // crudMealRepository.getWithUser(id, userId);
    }
}
