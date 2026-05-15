package kotlin.sequences;

import java.util.Iterator;
import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\t\u001aL\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\t\u0010\n\u001aL\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\u0004\b\u0000\u0010\u00002/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\f\u0010\r\"\u0018\u0010\u0010\u001a\u00060\u000ej\u0002`\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"\u0018\u0010\u0012\u001a\u00060\u000ej\u0002`\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0011\"\u0018\u0010\u0013\u001a\u00060\u000ej\u0002`\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0013\u0010\u0011\"\u0018\u0010\u0014\u001a\u00060\u000ej\u0002`\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0011\"\u0018\u0010\u0015\u001a\u00060\u000ej\u0002`\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0011\"\u0018\u0010\u0016\u001a\u00060\u000ej\u0002`\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0011*\f\b\u0002\u0010\u0017\"\u00020\u000e2\u00020\u000e¨\u0006\u0018"},
   d2 = {"T", "Lkotlin/Function2;", "Lkotlin/sequences/SequenceScope;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "block", "", "iterator", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "Lkotlin/sequences/Sequence;", "sequence", "(Lkotlin/jvm/functions/Function2;)Lkotlin/sequences/Sequence;", "", "Lkotlin/sequences/State;", "State_Done", "I", "State_Failed", "State_ManyNotReady", "State_ManyReady", "State_NotReady", "State_Ready", "State", "kotlin-stdlib"},
   xs = "kotlin/sequences/SequencesKt"
)
class SequencesKt__SequenceBuilderKt {
   private static final int State_NotReady = 0;
   private static final int State_ManyNotReady = 1;
   private static final int State_ManyReady = 2;
   private static final int State_Ready = 3;
   private static final int State_Done = 4;
   private static final int State_Failed = 5;

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Sequence sequence(@BuilderInference @NotNull Function2 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      return new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(block);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Iterator iterator(@BuilderInference @NotNull Function2 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      SequenceBuilderIterator iterator = new SequenceBuilderIterator();
      iterator.setNextStep(IntrinsicsKt.createCoroutineUnintercepted(block, iterator, iterator));
      return iterator;
   }

   public SequencesKt__SequenceBuilderKt() {
   }
}
