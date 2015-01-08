
package com.github.tilastokeskus.matertis.audio;

import com.github.tilastokeskus.matertis.Main;
import com.github.tilastokeskus.matertis.audio.listener.AudioListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

/**
 * A global audio manager that can play music and sound effects. 
 * 
 * @author tilastokeskus
 */
public class AudioManager {    
    private static final Logger LOGGER =
            Logger.getLogger(AudioManager.class.getName());
    
    /**
     * Identifier for the music played when the game is running.
     */
    public static final int SOUND_MUSIC = 0;
    
    /**
     * Identifier for the sound effect of a tetromino hitting the ground.
     */
    public static final int SOUND_DROP_PIECE = 1;
    
    /**
     * Identifier for the sound effect of rows being cleared.
     */
    public static final int SOUND_CLEAR_ROW = 2;
    
    /**
     * Identifier for the game over sound effect.
     */
    public static final int SOUND_GAME_OVER = 3;    
    
    private static final String[] AUDIO_LOCATIONS = {
        "audio/tetris.wav",
        "audio/drop.wav",
        "audio/clear.wav",
        "audio/gameover.wav"
    };
    
    private static Clip musicClip;
    private static boolean playMusic = false;
    private static boolean playSounds = false;
    
    /**
     * Plays the default music clip, if music is enabled.
     */
    public static void playMusic() {
        if (playMusic) {
            musicClip = getClip(SOUND_MUSIC);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    /**
     * Stops any playing music.
     */
    public static void stopMusic() {
        if (musicClip != null) {
            musicClip.close();
        }
    }
    
    /**
     * Plays a sound once, if sounds are enabled.
     * 
     * @param sound sound to play.
     */
    public static void playSound(int sound) {
        if (playSounds) {
            SoundClip clip = new SoundClip(sound);
            new Thread(clip).start();
        }
    }
    
    /**
     * Enables or disables music.
     * 
     * @param enable true to enable, false to disable.
     */
    public static void enableMusic(boolean enable) {
        playMusic = enable;
    }
    
    /**
     * Enables or disables sound effects.
     * 
     * @param enable true to enable, false to disable.
     */
    public static void enableSounds(boolean enable) {
        playSounds = enable;
    }
    
    public static boolean isMusicEnabled() {
        return playMusic;
    }
    
    public static boolean isSoundsEnabled() {
        return playSounds;
    }
    
    private static Clip getClip(int sound) {
        Clip clip = null;
        String location = AUDIO_LOCATIONS[sound];
        URL url = AudioManager.class.getClassLoader().getResource(location);
        
        try {
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip.open(ais);
        } catch (LineUnavailableException | IOException | 
                UnsupportedAudioFileException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        
        return clip;
    }
    
    private static class SoundClip implements Runnable {        
        private final int sound;
        private final AudioListener listener;
        
        public SoundClip(int sound) {
            this.sound = sound;
            this.listener = new AudioListener();
        }

        @Override
        public void run() {
            Clip clip = getClip(this.sound);
            clip.addLineListener(this.listener);

            try {
                clip.start();
                this.listener.waitUntilDone();
            } catch (InterruptedException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            } finally {
                clip.close();
            }            
        }
        
    }

}