package general;

public class RegularUser implements UserBehavior{

    private int playingLimit;

    public RegularUser() {
        playingLimit = 5;
    }

    @Override
    public void createPlaylist(String Title, User Owner) throws InvalidOperationException {
        throw new InvalidOperationException("Regular User cant create playList");
    }

    @Override
    public void playMusic(Music music) throws InvalidOperationException {
        if(playingLimit > 0) {
            music.play();
            --playingLimit;
        } else {
            throw new InvalidOperationException("you can only stream 5 music : for more : buy soptify permium");
        }

    }

    @Override
    public void buyPremium(User owner, int month) {
        if(owner != null) {
            owner.setBehavior(new PremiumUser());
            System.out.println("User :  " + owner.getUsername() + " Upgraded to Permium : time left : " + month + " months");
            

        }



    }
    
}
