package DAO;

import ENTITY.*;
import java.util.ArrayList;

public class userDAO {
    ArrayList<user> ls = new ArrayList<>();
    
    public userDAO() {
        ls.add(new user("haha", "1", "admin"));
        ls.add(new user("hehe", "2", "admin"));
        ls.add(new user("hihi", "3", "admin"));
        ls.add(new user("huhu", "4", "admin"));
        ls.add(new user("hoho", "5", "admin"));
    }
    
    public boolean checkLogin(String username, String password) {
        for (user u : ls) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
