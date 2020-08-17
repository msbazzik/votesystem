package com.javaapp.votesystem.web.user;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController extends AbstractUserController {

    static final String REST_URL = "/admin/users";

//    @GetMapping
//    public List<User> getAll() {
//        return super.getAll();
//    }
//
//    @Override
//    @GetMapping("/{id}")
//    public User get(@PathVariable int id) {
//        return super.get(id);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
//        User created = super.create(user);
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(created.getId()).toUri();
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }
//
//    @Override
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable int id) {
//        super.delete(id);
//    }
//
//    @Override
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void update(@RequestBody User user, @PathVariable int id) {
//        super.update(user, id);
//    }
//
//    @GetMapping("/by")
//    public User getByMail(@RequestParam String email) {
//        return super.getByMail(email);
//    }
//
//    @Override
//    @PatchMapping("/{id}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
//        super.enable(id, enabled);
//    }
}