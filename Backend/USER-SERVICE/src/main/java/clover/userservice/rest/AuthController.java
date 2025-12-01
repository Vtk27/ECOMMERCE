package clover.userservice.rest;

import clover.userservice.DAO.UserRepository;
import clover.userservice.Entity.User;
import clover.userservice.conn.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User existing = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new RuntimeException("user not found"));
        if(!passwordEncoder.matches(user.getPassword(), existing.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Creds");
        }
        String token = jwtUtil.generateToken(existing.getUsername());
        Map<String, Object> resp = Map.of(
                "token", token,
                "userId", existing.getId()
        );
        return ResponseEntity.ok(resp);
    }
}
