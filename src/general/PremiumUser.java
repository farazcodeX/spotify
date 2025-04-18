package general;

public class PremiumUser implements UserBehavior {

    private int remainMonths;

    public PremiumUser(int month) {
        this.remainMonths = month;
    }

    @Override
    public void createPlaylist(String Title, User Owner) throws InvalidOperationException {
        PlayList playList = new PlayList(Owner, Title);
            Owner.getPlayLists().add(playList);
            System.out.println("Playlist : " + Title + " has been created");
    }

    @Override
    public void playMusic(Music music) {
        music.play();
    }

    @Override
    public void buyPremium(User owner, int month) throws InvalidOperationException {
        if (month <= 0) {
            throw new InvalidOperationException("Invalid month value.");
        }
        this.remainMonths += month;
        System.out.println("your premium account has been renewed for " + month + " month");

    }
    public int getRemainMonths() {return remainMonths;}
    
}
