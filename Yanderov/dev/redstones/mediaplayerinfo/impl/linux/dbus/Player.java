package dev.redstones.mediaplayerinfo.impl.linux.dbus;

import kotlin.Metadata;
import org.freedesktop.dbus.TypeRef;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.annotations.DBusProperties;
import org.freedesktop.dbus.annotations.DBusProperty;
import org.freedesktop.dbus.annotations.DBusProperty.Access;
import org.freedesktop.dbus.interfaces.DBusInterface;

@DBusInterfaceName("org.mpris.MediaPlayer2.Player")
@DBusProperties({@DBusProperty(
   name = "Metadata",
   type = PropertyMetadataType.class,
   access = Access.READ
), @DBusProperty(
   name = "PlaybackStatus",
   type = String.class,
   access = Access.READ
), @DBusProperty(
   name = "LoopStatus",
   type = String.class,
   access = Access.READ_WRITE
), @DBusProperty(
   name = "Volume",
   type = double.class,
   access = Access.READ_WRITE
), @DBusProperty(
   name = "Shuffle",
   type = double.class,
   access = Access.READ_WRITE
), @DBusProperty(
   name = "Position",
   type = Integer.class,
   access = Access.READ
), @DBusProperty(
   name = "Rate",
   type = double.class,
   access = Access.READ_WRITE
), @DBusProperty(
   name = "MinimumRate",
   type = double.class,
   access = Access.READ_WRITE
), @DBusProperty(
   name = "MaximumRate",
   type = double.class,
   access = Access.READ_WRITE
), @DBusProperty(
   name = "CanControl",
   type = boolean.class,
   access = Access.READ
), @DBusProperty(
   name = "CanPlay",
   type = boolean.class,
   access = Access.READ
), @DBusProperty(
   name = "CanPause",
   type = boolean.class,
   access = Access.READ
), @DBusProperty(
   name = "CanSeek",
   type = boolean.class,
   access = Access.READ
)})
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001:\u0001\nJ\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\b\u001a\u00020\u0002H&¢\u0006\u0004\b\b\u0010\u0004J\u000f\u0010\t\u001a\u00020\u0002H&¢\u0006\u0004\b\t\u0010\u0004¨\u0006\u000b"},
   d2 = {"Ldev/redstones/mediaplayerinfo/impl/linux/dbus/Player;", "Lorg/freedesktop/dbus/interfaces/DBusInterface;", "", "Next", "()V", "Pause", "Play", "PlayPause", "Previous", "Stop", "PropertyMetadataType", "MediaPlayerInfo"}
)
public interface Player extends DBusInterface {
   void Play();

   void Pause();

   void PlayPause();

   void Stop();

   void Next();

   void Previous();

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u001e\u0012\u001a\u0012\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0004\u0018\u00010\u00020\u0001¨\u0006\u0005"},
      d2 = {"Ldev/redstones/mediaplayerinfo/impl/linux/dbus/Player$PropertyMetadataType;", "Lorg/freedesktop/dbus/TypeRef;", "", "", "Lorg/freedesktop/dbus/types/Variant;", "MediaPlayerInfo"}
   )
   public interface PropertyMetadataType extends TypeRef {
   }
}
