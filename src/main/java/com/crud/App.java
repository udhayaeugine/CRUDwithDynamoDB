package com.crud;

import com.crud.model.User;
import com.crud.service.UserService;

import static java.lang.System.out;

public class App {
    public static void main(String[] args) {

        UserService service = new UserService();

        // CREATE
      /*  User u1 = new User("u3", "User 3", "user3@example.com");
        service.create(u1);
        out.println("Created user u3");
*/
        // READ
        User fetched = service.read("u2");
        out.println("Fetched: " + fetched.getName());
        out.println("Fetched: " + fetched.getUserId());
        out.println("Fetched: " + fetched.getEmail());

        // UPDATE
       /* fetched.setName("Alice Updated");
        fetched.setEmail("alice.updated@example.com");
        service.update(fetched);
        out.println("Updated user u1");

        // DELETE
        service.delete("u1");
        out.println("Deleted user u1");

        */
    }
}