package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0010(\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006¸\u0006\u0005"},
   d2 = {"kotlin/collections/CollectionsKt__IterablesKt.Iterable.1", "", "", "iterator", "()Ljava/util/Iterator;", "kotlin/collections/CollectionsKt__IterablesKt$Iterable$1", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nIterables.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Iterables.kt\nkotlin/collections/CollectionsKt__IterablesKt$Iterable$1\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,17:1\n2921#2:18\n*E\n"})
public final class SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 implements Iterable, KMappedMarker {
   // $FF: synthetic field
   final Sequence $this_asIterable$inlined;

   public SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(Sequence var1) {
      this.$this_asIterable$inlined = var1;
   }

   public Iterator iterator() {
      int var1 = 0;
      return this.$this_asIterable$inlined.iterator();
   }
}
