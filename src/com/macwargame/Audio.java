
package com.macwargame;

import java.applet.*;
import java.net.URL;

public class Audio {
    private URL battle = Audio.class.getResource("sound/Battle.wav");
    private URL menu = Audio.class.getResource("sound/Menu.wav");
    private URL victory = Audio.class.getResource("sound/Victory.wav");
    
    private URL throwing = Audio.class.getResource("sound/throw.wav");
    private URL hSurface = Audio.class.getResource("sound/MhitW.wav");
    private URL hurt = Audio.class.getResource("sound/playerHurt.wav");
    private URL bell = Audio.class.getResource("sound/bell.wav");
    
    AudioClip battle_music = Applet.newAudioClip(battle);
    AudioClip menu_music = Applet.newAudioClip(menu);
    AudioClip victory_music = Applet.newAudioClip(victory);
    
    AudioClip throwing_sound = Applet.newAudioClip(throwing);
    AudioClip hitSurface = Applet.newAudioClip(hSurface);
    AudioClip playerHurt = Applet.newAudioClip(hurt);
    AudioClip timeUp = Applet.newAudioClip(bell);
    
    // for Animation sound
//    public void throwing() {
//        throwing_sound.play();
//        System.out.println("THROWING SOUND ACTIVE!!!");
//    }
//    
    
    
//    //for menu scene
//    public void playMenuMusic() {
//        menu_music.loop();
//    }
//
//    public void stopMenuMusic() {
//        menu_music.stop();
//    }
//    
//    public void loopMenuMusic() {
//        menu_music.loop();
//    }
//    
//
//    // for battle scene
//    public void playBattleMusic() {
//        battle_music.play();
//    }
//    
//    public void stopBattleMusic() {
//        battle_music.stop();
//    }
//    
//    public void loopBattleMusic() {
//        battle_music.loop();
//    }
//    
//    //for winner scene
//    public void playVictoryMusic() {
//        victory_music.play();
//    }
//    
//    public void stopVictoryMusic() {
//        victory_music.stop();
//    }
}
