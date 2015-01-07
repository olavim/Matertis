
package com.github.tilastokeskus.matertis.audio.listener;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Implementation of {@link LineListener}. Used to block until line has
 * finished. In the sense of audio, used to wait until an audio clip has
 * finished playing.
 * 
 * @author tilastokeskus
 */
public class AudioListener implements LineListener {    
    private boolean done = false;

    /**
     * {@inheritDoc LineListener}
     * <p>
     * Stops blocking when a LineEvent with type STOP or CLOSE is received.
     * 
     * @param event {@inheritDoc LineListener}
     */
    @Override 
    public synchronized void update(LineEvent event) {
        LineEvent.Type eventType = event.getType();
        if (eventType == LineEvent.Type.STOP ||
                eventType == LineEvent.Type.CLOSE) {
            done = true;
            notifyAll();
        }
    }

    /**
     * Blocks until this listener is updated with a stop or close event.
     * 
     * @throws InterruptedException if any thread interrupted the current thread
     * before or while the current thread was waiting for a notification. The
     * interrupted status of the current thread is cleared when this exception
     * is thrown.
     */
    public synchronized void waitUntilDone() throws InterruptedException {
        while (!done) { 
            wait();
        }
    }

}