package general;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayList {
    public ArrayList<Music> playlist = new ArrayList<>();
    private User owner;
    private String title;

    Scanner scanner = new Scanner(System.in);

    public PlayList(User owner, String title) throws InvalidOperationException {
        if(owner == null) {
            throw new InvalidOperationException("Provided user Owner is null");
        }
        if(title == null || title.isEmpty()) {
            throw new InvalidOperationException("Provided title is empty");
        }
        

        this.owner = owner;
        this.title = title;
        playlist = new ArrayList<>();
    }


    public void editTitle(String password, String newTitle) throws InvalidOperationException {
        if(newTitle == null || newTitle.isEmpty()) {
            throw new InvalidOperationException("Provided title is empty");
        }
        if(!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("password is not correct");
        }

        this.title = title;
  
    }

    public String getTitle() {return title;}


    public void addMusic(Music music, String password) throws InvalidOperationException {
        if(!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Provided User Password : doese not match the owner of this playList");
        } 
       for(int i = 0; i < playlist.size(); ++i) {
        if(playlist.get(i).getTitle().equals(music.getTitle()) && playlist.get(i).getSinger().getUsername().equals(music.getSinger().getUsername())) {
          throw new InvalidOperationException("Music : " + music.getTitle() + "  By : " + music.getSinger().getUsername() + "  is already in this playlist");
        }
       }
  
       playlist.add(music);
    }
    public void removeMusic(String password, String musicTitle, String singerName) throws InvalidOperationException {
        if(!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Incorrect password");
        }
        if(!playlist.removeIf(music -> music.getTitle().equals(musicTitle) && music.getSinger().getUsername().equals(singerName))) {
            throw new InvalidOperationException("music : " + musicTitle + " by : " + singerName + " is not in this playList");  
        }
    }
    public ArrayList<Music> searchInPlaylist(String title) {
        ArrayList<Music> result = new ArrayList<>();
        for (Music music : playlist) {
            if (music.getTitle().equals(title)) {
                result.add(music);
            }
        }
        return result;
    }
    public Music searchInPlaylist(String title, String singer) {
        return playlist.stream().filter(music  -> music.getTitle().equals(title) && music.getSinger().getUsername().equals(singer))
        .findFirst().orElse(null);
    }
    public void playPlayList() {
        for(Music music : playlist) {
            owner.playMusic(music);
            System.out.println("Stop : press space / next music : any key");
            String choice = scanner.nextLine();
            if(choice.equals(" ")) {
                System.out.println("Paused");
                return;
            }
        }
    }
}
