package general;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followers;
    private ArrayList<User> followings;
    private UserBehavior behavior;
    private ArrayList<PlayList> playlists;

    public static ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) throws InvalidOperationException {
        setUsername(username);
        setPassword(password);

    }
    public void setUsername(String username) throws InvalidOperationException {
        if(username == null || username.isEmpty()) {
            throw new InvalidOperationException("Username is empty ");
        }
        boolean exist = allUsers.stream().anyMatch(user -> user.getUsername().equals(username));
        if(exist) {
            throw new InvalidOperationException("Username : " + username + "  is already exist");
        }
        this.username = username;

    }
    public String getUsername() {return username;}
    public void setPassword(String pass) throws InvalidOperationException {
        if(pass.length() >= 8) {
            this.password = pass;

        } else {
            throw new InvalidOperationException("Password is short : " + pass.length() + "/8" );
        }
    }
}
