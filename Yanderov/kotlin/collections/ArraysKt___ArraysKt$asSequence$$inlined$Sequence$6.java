package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006¸\u0006\u0005"},
   d2 = {"kotlin/sequences/SequencesKt__SequencesKt.Sequence.1", "Lkotlin/sequences/Sequence;", "", "iterator", "()Ljava/util/Iterator;", "kotlin/sequences/SequencesKt__SequencesKt$Sequence$1", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nSequences.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sequences.kt\nkotlin/sequences/SequencesKt__SequencesKt$Sequence$1\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,22:1\n24090#2:23\n*E\n"})
public final class ArraysKt___ArraysKt$asSequence$$inlined$Sequence$6 implements Sequence {
   // $FF: synthetic field
   final float[] $this_asSequence$inlined;

   public ArraysKt___ArraysKt$asSequence$$inlined$Sequence$6(float[] var1) {
      this.$this_asSequence$inlined = var1;
   }

   public Iterator iterator() {
      int var1 = 0;
      return ArrayIteratorsKt.iterator(this.$this_asSequence$inlined);
   }
}
