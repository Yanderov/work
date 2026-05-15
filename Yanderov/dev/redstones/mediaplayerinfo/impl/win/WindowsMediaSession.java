package dev.redstones.mediaplayerinfo.impl.win;

import dev.redstones.mediaplayerinfo.IMediaSession;
import dev.redstones.mediaplayerinfo.MediaInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u0096 ¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\nH\u0096 ¢\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\nH\u0096 ¢\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\nH\u0096 ¢\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\nH\u0096 ¢\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\nH\u0096 ¢\u0006\u0004\b\u0011\u0010\fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0012R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"},
   d2 = {"Ldev/redstones/mediaplayerinfo/impl/win/WindowsMediaSession;", "Ldev/redstones/mediaplayerinfo/IMediaSession;", "Ldev/redstones/mediaplayerinfo/MediaInfo;", "media", "", "owner", "", "index", "<init>", "(Ldev/redstones/mediaplayerinfo/MediaInfo;Ljava/lang/String;I)V", "", "next", "()V", "pause", "play", "playPause", "previous", "stop", "I", "Ldev/redstones/mediaplayerinfo/MediaInfo;", "getMedia", "()Ldev/redstones/mediaplayerinfo/MediaInfo;", "Ljava/lang/String;", "getOwner", "()Ljava/lang/String;", "MediaPlayerInfo"}
)
public final class WindowsMediaSession implements IMediaSession {
   @NotNull
   private final MediaInfo media;
   @NotNull
   private final String owner;
   private final int index;

   public WindowsMediaSession(@NotNull MediaInfo media, @NotNull String owner, int index) {
      Intrinsics.checkNotNullParameter(media, "media");
      Intrinsics.checkNotNullParameter(owner, "owner");
      super();
      this.media = media;
      this.owner = owner;
      this.index = index;
   }

   @NotNull
   public MediaInfo getMedia() {
      return this.media;
   }

   @NotNull
   public String getOwner() {
      return this.owner;
   }

   public native void play();

   public native void pause();

   public native void playPause();

   public native void stop();

   public native void next();

   public native void previous();
}
