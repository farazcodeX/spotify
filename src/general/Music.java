package general;

import java.util.ArrayList;

public class Music {

    private String title;
    private User singer;
    private int numberOfStream;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) throws InvalidOperationException {
        setSinger(singer);
        setTitle(title);
        numberOfStream = 0;

        allMusics.add(this);

    }
    public void setTitle(String title) throws InvalidOperationException {
        if(title == null || title.isEmpty()) {
            throw new InvalidOperationException("Provided Title is empty");

        }
        this.title = title;
    }
    public void setSinger(User user) throws InvalidOperationException {
        if(user == null) {
            throw new InvalidOperationException("User -> Singer is empty");
        }
        this.singer = user;
    }



    
}
