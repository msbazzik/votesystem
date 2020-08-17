package com.javaapp.votesystem.model;

import java.util.Collection;
import java.util.Set;

public class User extends AbstractNamedEntity {

    private String email;

    private String password;

    // private boolean enabled = true;

    // private Date registered = new Date();

    private Set<Role> roles;

    //  private List<Meal> meals;

//    public User() {
//    }

//    public User(ru.javawebinar.topjava.model.User u) {
//        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getCaloriesPerDay(), u.isEnabled(), u.getRegistered(), u.getRoles());
//    }

//    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
//        this(id, name, email, password, true, new Date(), EnumSet.of(role, roles));
//    }

    public User(Integer id, String name, String email, String password, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
       // setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public Date getRegistered() {
//        return registered;
//    }
//
//    public void setRegistered(Date registered) {
//        this.registered = registered;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

//    public void setRoles(Collection<Role> roles) {
//        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
//    }

//    public List<Meal> getMeals() {
//        return meals;
//    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}