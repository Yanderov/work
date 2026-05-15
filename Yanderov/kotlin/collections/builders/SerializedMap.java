package kotlin.collections.builders;

import java.io.Externalizable;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0017\u0012\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0002\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013¨\u0006\u0015"},
   d2 = {"Lkotlin/collections/builders/SerializedMap;", "Ljava/io/Externalizable;", "<init>", "()V", "", "map", "(Ljava/util/Map;)V", "Ljava/io/ObjectInput;", "input", "", "readExternal", "(Ljava/io/ObjectInput;)V", "", "readResolve", "()Ljava/lang/Object;", "Ljava/io/ObjectOutput;", "output", "writeExternal", "(Ljava/io/ObjectOutput;)V", "Ljava/util/Map;", "Companion", "kotlin-stdlib"}
)
final class SerializedMap implements Externalizable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private Map map;
   private static final long serialVersionUID = 0L;

   public SerializedMap(@NotNull Map map) {
      Intrinsics.checkNotNullParameter(map, "map");
      super();
      this.map = map;
   }

   public SerializedMap() {
      this(MapsKt.emptyMap());
   }

   public void writeExternal(@NotNull ObjectOutput output) {
      Intrinsics.checkNotNullParameter(output, "output");
      output.writeByte(0);
      output.writeInt(this.map.size());

      for(Map.Entry entry : this.map.entrySet()) {
         output.writeObject(entry.getKey());
         output.writeObject(entry.getValue());
      }

   }

   public void readExternal(@NotNull ObjectInput input) {
      Intrinsics.checkNotNullParameter(input, "input");
      int flags = input.readByte();
      if (flags != 0) {
         throw new InvalidObjectException("Unsupported flags value: " + flags);
      } else {
         int size = input.readInt();
         if (size < 0) {
            throw new InvalidObjectException("Illegal size value: " + size + '.');
         } else {
            Map var4 = MapsKt.createMapBuilder(size);
            Map $this$readExternal_u24lambda_u241 = var4;
            int var6 = 0;

            for(int var7 = 0; var7 < size; ++var7) {
               int var9 = 0;
               Object key = input.readObject();
               Object value = input.readObject();
               $this$readExternal_u24lambda_u241.put(key, value);
            }

            this.map = MapsKt.build(var4);
         }
      }
   }

   private final Object readResolve() {
      return this.map;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/collections/builders/SerializedMap$Companion;", "", "<init>", "()V", "", "serialVersionUID", "J", "kotlin-stdlib"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
