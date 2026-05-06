package com.example.EcommShop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("")
public class UserController {
    private final  UserService  userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/api/users")
public ResponseEntity <List<User>> Getallusers(){
    List <User> users  = userService.Getallusers();
    return new ResponseEntity<>(users, HttpStatus.OK);
}
@GetMapping("/api/users/{id}")
    public ResponseEntity <User> getUser(@PathVariable Long Id){
    /*User user = userService.fetchUser(Id);
    if(user == null)
        return ResponseEntity.notFound().build();
    return new ResponseEntity<>(user, HttpStatus.OK);*/


    return userService.fetchUser(Id)
            .map(ResponseEntity::ok)
            .orElseGet(()->ResponseEntity.notFound().build());

}

@PostMapping("/api/addUser")
    public  ResponseEntity<String>addUser(@RequestBody User user){
    userService.addUser(user);
    return new ResponseEntity<>("User Added succesfully",HttpStatus.CREATED);
}
@PutMapping("/api/updateuser")
    public ResponseEntity<String> updateUser(@PathVariable Long Id , @RequestBody User updateUser){
        boolean updated = userService.updateduser(Id, updateUser);
        if(updated)
            return ResponseEntity.ok("User updated succesfully");
        return ResponseEntity.notFound().build();

}





}
