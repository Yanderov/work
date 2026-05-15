package kotlin.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\n\n\u0002\u0010\u001f\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003B>\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0014\u0010\u0012J\u001a\u0010\u0017\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0012J\u001a\u0010\u0018\u001a\u0004\u0018\u00018\u00012\u0006\u0010\t\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00028\u00012\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ!\u0010 \u001a\u0004\u0018\u00018\u00012\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0004\b \u0010!J%\u0010$\u001a\u00020\r2\u0014\u0010#\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\"H\u0016¢\u0006\u0004\b$\u0010%J\u0019\u0010&\u001a\u0004\u0018\u00018\u00012\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0004\b&\u0010\u0019J\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)R/\u0010\n\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010*R&\u0010/\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010,0+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010.R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u00102\u001a\u0004\b3\u00104R\u0014\u00106\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010\u001dR\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00028\u0001078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00109¨\u0006;"},
   d2 = {"Lkotlin/collections/MutableMapWithDefaultImpl;", "K", "V", "Lkotlin/collections/MutableMapWithDefault;", "", "map", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "key", "default", "<init>", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "", "clear", "()V", "", "containsKey", "(Ljava/lang/Object;)Z", "value", "containsValue", "", "other", "equals", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrImplicitDefault", "", "hashCode", "()I", "isEmpty", "()Z", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "", "from", "putAll", "(Ljava/util/Map;)V", "remove", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/functions/Function1;", "", "", "getEntries", "()Ljava/util/Set;", "entries", "getKeys", "keys", "Ljava/util/Map;", "getMap", "()Ljava/util/Map;", "getSize", "size", "", "getValues", "()Ljava/util/Collection;", "values", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nMapWithDefault.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapWithDefault.kt\nkotlin/collections/MutableMapWithDefaultImpl\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,104:1\n350#2,6:105\n*S KotlinDebug\n*F\n+ 1 MapWithDefault.kt\nkotlin/collections/MutableMapWithDefaultImpl\n*L\n101#1:105,6\n*E\n"})
final class MutableMapWithDefaultImpl implements MutableMapWithDefault {
   @NotNull
   private final Map map;
   @NotNull
   private final Function1 default;

   public MutableMapWithDefaultImpl(@NotNull Map map, @NotNull Function1 default) {
      Intrinsics.checkNotNullParameter(map, "map");
      Intrinsics.checkNotNullParameter(default, "default");
      super();
      this.map = map;
      this.default = default;
   }

   @NotNull
   public Map getMap() {
      return this.map;
   }

   public boolean equals(@Nullable Object other) {
      return this.getMap().equals(other);
   }

   public int hashCode() {
      return this.getMap().hashCode();
   }

   @NotNull
   public String toString() {
      return this.getMap().toString();
   }

   public int getSize() {
      return this.getMap().size();
   }

   public boolean isEmpty() {
      return this.getMap().isEmpty();
   }

   public boolean containsKey(Object key) {
      return this.getMap().containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.getMap().containsValue(value);
   }

   @Nullable
   public Object get(Object key) {
      return this.getMap().get(key);
   }

   @NotNull
   public Set getKeys() {
      return this.getMap().keySet();
   }

   @NotNull
   public Collection getValues() {
      return this.getMap().values();
   }

   @NotNull
   public Set getEntries() {
      return this.getMap().entrySet();
   }

   @Nullable
   public Object put(Object key, Object value) {
      return this.getMap().put(key, value);
   }

   @Nullable
   public Object remove(Object key) {
      return this.getMap().remove(key);
   }

   public void putAll(@NotNull Map from) {
      Intrinsics.checkNotNullParameter(from, "from");
      this.getMap().putAll(from);
   }

   public void clear() {
      this.getMap().clear();
   }

   public Object getOrImplicitDefault(Object key) {
      Map $this$getOrElseNullable$iv = this.getMap();
      int $i$f$getOrElseNullable = 0;
      Object value$iv = $this$getOrElseNullable$iv.get(key);
      Object var10000;
      if (value$iv == null && !$this$getOrElseNullable$iv.containsKey(key)) {
         int var5 = 0;
         var10000 = this.default.invoke(key);
      } else {
         var10000 = value$iv;
      }

      return var10000;
   }
}
