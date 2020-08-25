package com.javaapp.votesystem.repository.datajpa;

import com.javaapp.votesystem.model.Meal;
import com.javaapp.votesystem.repository.MealRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.transaction.annotation.Transactional;

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
    public Meal save(Meal meal, int restaurantId) {
//        if (!meal.isNew() && get(meal.getId(), userId) == null) {
//            return null;
//        }
//        meal.setUser(crudUserRepository.getOne(userId));
//        return crudMealRepository.save(meal);
        return null;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        // return crudMealRepository.delete(id, userId) != 0;
        return true;
    }

    @Override
    public Meal get(int id, int restaurantId) {
//        return crudMealRepository.findById(id)
//                .filter(meal -> meal.getUser().getId() == userId)
//                .orElse(null);
        return null;
    }

//    @Override
//    public List<Meal> getAll() {
//        return null;
//    }
}
