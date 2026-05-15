package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.EmptyIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\nH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\r\u0010\t¨\u0006\u000e"},
   d2 = {"Lkotlin/sequences/EmptySequence;", "Lkotlin/sequences/Sequence;", "", "Lkotlin/sequences/DropTakeSequence;", "<init>", "()V", "", "n", "drop", "(I)Lkotlin/sequences/EmptySequence;", "", "iterator", "()Ljava/util/Iterator;", "take", "kotlin-stdlib"}
)
final class EmptySequence implements Sequence, DropTakeSequence {
   @NotNull
   public static final EmptySequence INSTANCE = new EmptySequence();

   private EmptySequence() {
   }

   @NotNull
   public Iterator iterator() {
      return (Iterator)EmptyIterator.INSTANCE;
   }

   @NotNull
   public EmptySequence drop(int n) {
      return INSTANCE;
   }

   @NotNull
   public EmptySequence take(int n) {
      return INSTANCE;
   }
}
