package general;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followers;
    private ArrayList<User> followings;
    private UserBehavior behavior;
    private ArrayList<PlayList> playlists;

    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String username, String password) throws InvalidOperationException {
        setUsername(username);
        setPassword(password);
        
        // avoiding null pointer exc
        followers = new ArrayList<>();
        followings = new ArrayList<>();
        behavior = new RegularUser();
        playlists = new ArrayList<>();
        
        allUsers.add(this);

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

    public void follow(User user) {
        if (user == null) {
            throw new InvalidOperationException("invalid Username for follow");
        }

        if (user == this) {
            throw new InvalidOperationException("You can't follow yourself");
        }

        if (!followings.contains(user)) {
            followings.add(user);
            user.followers.add(this);
        }

        else {
            throw new InvalidOperationException("You Already follow this username");
        }
  
    }
    public void unFollow(User user) {
        if (user == null) {
            throw new InvalidOperationException("invalid Username for unfollow");
        }

        if (user == this) {
            throw new InvalidOperationException("You can't unfollow yourself");
        }

        if (followings.contains(user)) {
            followings.remove(user);
            user.followers.remove(this);
        }

        else {
            throw new InvalidOperationException("This user is not your follower");
        }
  
    }

    public String getPassword() {return password;}

    public void createPlaylist(String title) throws InvalidOperationException {
            this.behavior.createPlaylist(title, this);
    }

    public void playMusic(Music music) throws InvalidOperationException {
        this.behavior.playMusic(music);
    }

    public void buyPremium(User owner, int month) throws InvalidOperationException {
        this.behavior.buyPremium(owner, month);
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public ArrayList<PlayList> getPlayLists() {
        return (new ArrayList<>(playlists));
    }

    public static ArrayList<User> getAllUsers() {
        return (new ArrayList<>(allUsers));
    }

    public PlayList getPlayListByIndex(int index) throws InvalidOperationException {
        if(playlists.size() >= index && index > 0) {
            return playlists.get(index-1);
        } else {
            throw new InvalidOperationException("Provided number is incorrect");
        }
    }
    @Override
    public String toString() {
        return this.username;
    }

}
