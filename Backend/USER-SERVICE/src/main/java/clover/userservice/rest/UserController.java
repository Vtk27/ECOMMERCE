package clover.userservice.rest;

import clover.userservice.DAO.UserRepository;
import clover.userservice.Entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo){
        this.repo = repo;
    }

    @PostMapping
    public User create(@RequestBody User user){
        return repo.save(user);
    }

    @GetMapping
    public List<User> getAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user){
        User oldUser = repo.findById(id).orElse(null);
        if(oldUser==null) return null;
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        repo.save(oldUser);
        return oldUser;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        repo.deleteById(id);
        return "User Deleted";
    }
}
