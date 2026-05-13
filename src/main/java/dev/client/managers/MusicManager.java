package dev.client.managers;

import net.minecraft.client.MinecraftClient;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.player.Player;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class MusicManager {
    private final List<File> musicFiles = new ArrayList<>();
    private Player player;
    private FileInputStream fileInputStream;
    private Thread playerThread;
    private int currentTrackIndex = -1;
    private boolean playing = false;
    
    private int totalFrames = 0;
    private int currentFrame = 0;
    private File currentFile;

    public MusicManager() {
        this.updatePlaylist();
    }

    public void updatePlaylist() {
        String currentPath = null;
        if (currentTrackIndex >= 0 && currentTrackIndex < musicFiles.size()) {
            currentPath = musicFiles.get(currentTrackIndex).getAbsolutePath();
        }

        musicFiles.clear();
        File musicDir = new File(MinecraftClient.getInstance().runDirectory, "Music");
        if (!musicDir.exists()) {
            musicDir.mkdirs();
        }
        File[] files = musicDir.listFiles((dir, name) -> {
            String lower = name.toLowerCase();
            return lower.endsWith(".wav") || lower.endsWith(".mp3");
        });
        
        if (files != null) {
            for (File f : files) {
                musicFiles.add(f);
            }
        }

        if (currentPath != null) {
            for (int i = 0; i < musicFiles.size(); i++) {
                if (musicFiles.get(i).getAbsolutePath().equals(currentPath)) {
                    currentTrackIndex = i;
                    return;
                }
            }
        }
        if (musicFiles.isEmpty()) currentTrackIndex = -1;
        else if (currentTrackIndex >= musicFiles.size()) currentTrackIndex = 0;
    }

    private int countFrames(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            Bitstream bitstream = new Bitstream(fis);
            int frames = 0;
            while (bitstream.readFrame() != null) {
                frames++;
                bitstream.closeFrame();
            }
            return frames;
        } catch (Exception e) {
            return 0;
        }
    }

    public void play() {
        if (musicFiles.isEmpty()) updatePlaylist();
        if (musicFiles.isEmpty()) return;

        if (currentTrackIndex == -1) currentTrackIndex = 0;
        
        if (playing && playerThread != null && playerThread.isAlive()) {
            return;
        }

        startTrack(currentFrame);
    }

    private void startTrack(int startFrame) {
        playing = false;
        
        // Start in a new thread to avoid UI freeze during skipping
        new Thread(() -> {
            try {
                stopInternal();
                
                synchronized (this) {
                    currentFile = musicFiles.get(currentTrackIndex);
                    if (totalFrames <= 0) {
                        totalFrames = countFrames(currentFile);
                    }
                    
                    fileInputStream = new FileInputStream(currentFile);
                    Bitstream bitstream = new Bitstream(fileInputStream);
                    
                    // Rapid skip using Bitstream (only reads headers, no playback)
                    int skipped = 0;
                    while (skipped < startFrame && bitstream.readFrame() != null) {
                        bitstream.closeFrame();
                        skipped++;
                    }
                    
                    currentFrame = skipped;
                    player = new Player(fileInputStream); // Start playing from current stream position
                }

                playing = true;
                playerThread = new Thread(() -> {
                    try {
                        while (playing && player != null && player.play(1)) {
                            currentFrame++;
                        }
                        if (playing && player != null && player.isComplete()) {
                            MinecraftClient.getInstance().execute(this::next);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                playerThread.setDaemon(true);
                playerThread.start();
                
            } catch (Exception e) {
                System.err.println("[MusicManager] Error: " + e.getMessage());
                playing = false;
            }
        }).start();
    }

    private void stopInternal() {
        playing = false;
        if (playerThread != null && playerThread != Thread.currentThread()) {
            // Give it a moment to exit loop
        }
        if (player != null) {
            player.close();
            player = null;
        }
        if (fileInputStream != null) {
            try { fileInputStream.close(); } catch (Exception ignored) {}
            fileInputStream = null;
        }
    }

    public void pause() {
        playing = false;
        stopInternal();
    }

    public void stop() {
        playing = false;
        currentFrame = 0;
        totalFrames = 0;
        stopInternal();
    }

    public void next() {
        updatePlaylist();
        if (musicFiles.isEmpty()) return;
        currentTrackIndex = (currentTrackIndex + 1) % musicFiles.size();
        totalFrames = 0;
        currentFrame = 0;
        startTrack(0);
    }

    public void previous() {
        updatePlaylist();
        if (musicFiles.isEmpty()) return;
        currentTrackIndex = (currentTrackIndex - 1 + musicFiles.size()) % musicFiles.size();
        totalFrames = 0;
        currentFrame = 0;
        startTrack(0);
    }

    public void setPosition(float percentage) {
        if (musicFiles.isEmpty() || currentTrackIndex == -1) return;
        int targetFrame = (int) (totalFrames * percentage);
        startTrack(targetFrame);
    }

    public float getProgress() {
        if (totalFrames <= 0) return 0;
        return (float) currentFrame / (float) totalFrames;
    }

    public boolean isPlaying() {
        return playing && playerThread != null && playerThread.isAlive();
    }

    public String getCurrentTrackName() {
        if (currentTrackIndex >= 0 && currentTrackIndex < musicFiles.size()) {
            String name = musicFiles.get(currentTrackIndex).getName();
            int lastDot = name.lastIndexOf('.');
            if (lastDot > 0) {
                return name.substring(0, lastDot);
            }
            return name;
        }
        return "No Music Found";
    }
}
