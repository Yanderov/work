package kotlin.sequences;

import java.util.Enumeration;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"},
   d2 = {"T", "Ljava/util/Enumeration;", "Lkotlin/sequences/Sequence;", "asSequence", "(Ljava/util/Enumeration;)Lkotlin/sequences/Sequence;", "kotlin-stdlib"},
   xs = "kotlin/sequences/SequencesKt"
)
class SequencesKt__SequencesJVMKt extends SequencesKt__SequenceBuilderKt {
   @InlineOnly
   private static final Sequence asSequence(Enumeration $this$asSequence) {
      Intrinsics.checkNotNullParameter($this$asSequence, "<this>");
      return SequencesKt.asSequence(CollectionsKt.iterator($this$asSequence));
   }

   public SequencesKt__SequencesJVMKt() {
   }
}
