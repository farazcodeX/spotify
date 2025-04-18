package general;

public class PremiumUser implements UserBehavior {

    private int remainMonths = 0;

    @Override
    public void createPlaylist(String Title, User Owner) {
        PlayList playList = new PlayList();
        if(Owner.getPlayLists() != null) {
            Owner.getPlayLists().add(playList);
            System.out.println("Playlist : " + Title + " has been created");
        }

    }

    @Override
    public void playMusic(Music music) {
        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) {
        this.remainMonths += month;
    }
    
}
