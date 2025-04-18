package general;

import java.util.ArrayList;

import javax.imageio.plugins.tiff.TIFFDirectory;

public class Music {

    private String title;
    private User singer;
    private int numberOfStream;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) throws InvalidOperationException {
        setSinger(singer);
        setTitle(title);
        ifMusicExist(title, singer.getUsername());
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
    public String getTitle() {return title;}
    public User getSinger() {return singer;}

    public void play() {
        ++numberOfStream;
        System.out.println("---------------");
        System.out.println("Music :  " + title + "\n Singer : " + singer.getUsername() + "\n  Streams : " + numberOfStream);
    }

    private void ifMusicExist(String title, String singer) throws InvalidOperationException {
        for(Music music : allMusics) {
            if(music.getTitle().equals(title) && music.getSinger().getUsername().equals(singer)) {
                throw new InvalidOperationException("Music with title : " + title + "  singer Name : " + singer + "  is already exist");
            }
        }
    }
    public ArrayList<Music> search(String title) {
        ArrayList<Music> musics = new ArrayList<>();
        
        allMusics.stream().filter(music -> music.getTitle().equals(title)).forEach(music -> musics.add(music));

        return musics;
    }
    public Music search(String title, String singer) {
       return allMusics.stream().filter(music -> music.getTitle().equals(title) && music.getSinger().getUsername().equals(singer)).findAny().orElse(null);
    }    
}
