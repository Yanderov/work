package kotlin.sequences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010\u001c\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\u001a5\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00002\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000¢\u0006\u0004\b\u0007\u0010\b\u001ai\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f2\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u00020\u000fH\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a-\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\b\b\u0000\u0010\u0000*\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0001¢\u0006\u0004\b\u0014\u0010\u0006\u001aC\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\b\b\u0000\u0010\u0000*\u00020\u00122\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000f¢\u0006\u0004\b\u0014\u0010\u0016\u001a?\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\b\b\u0000\u0010\u0000*\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00018\u00002\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000fH\u0007¢\u0006\u0004\b\u0014\u0010\u0018\u001a-\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00002\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0019\"\u00028\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a#\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u001d\u0010\u001e\u001a#\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u001f\u0010 \u001aE\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u00028\u00000\u00042\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00020\u000fH\u0002¢\u0006\u0004\b!\u0010\"\u001a+\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$0\u0004H\u0007¢\u0006\u0004\b%\u0010 \u001a)\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0004¢\u0006\u0004\b#\u0010 \u001a9\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00042\u0012\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0001H\u0007¢\u0006\u0004\b'\u0010(\u001a(\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004H\u0087\b¢\u0006\u0004\b)\u0010 \u001a%\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0007¢\u0006\u0004\b*\u0010 \u001a-\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010,\u001a\u00020+H\u0007¢\u0006\u0004\b*\u0010-\u001aG\u00100\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000/\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010/0.\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\n*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010.0\u0004¢\u0006\u0004\b0\u00101\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00062"},
   d2 = {"T", "Lkotlin/Function0;", "", "iterator", "Lkotlin/sequences/Sequence;", "Sequence", "(Lkotlin/jvm/functions/Function0;)Lkotlin/sequences/Sequence;", "emptySequence", "()Lkotlin/sequences/Sequence;", "C", "R", "source", "Lkotlin/Function2;", "", "transform", "Lkotlin/Function1;", "flatMapIndexed", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "", "nextFunction", "generateSequence", "seedFunction", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "seed", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "", "elements", "sequenceOf", "([Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "asSequence", "(Ljava/util/Iterator;)Lkotlin/sequences/Sequence;", "constrainOnce", "(Lkotlin/sequences/Sequence;)Lkotlin/sequences/Sequence;", "flatten$SequencesKt__SequencesKt", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "flatten", "", "flattenSequenceOfIterable", "defaultValue", "ifEmpty", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function0;)Lkotlin/sequences/Sequence;", "orEmpty", "shuffled", "Lkotlin/random/Random;", "random", "(Lkotlin/sequences/Sequence;Lkotlin/random/Random;)Lkotlin/sequences/Sequence;", "Lkotlin/Pair;", "", "unzip", "(Lkotlin/sequences/Sequence;)Lkotlin/Pair;", "kotlin-stdlib"},
   xs = "kotlin/sequences/SequencesKt"
)
class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {
   @InlineOnly
   private static final Sequence Sequence(final Function0 iterator) {
      Intrinsics.checkNotNullParameter(iterator, "iterator");
      return new Sequence(iterator) {
         public Iterator iterator() {
            return (Iterator)iterator.invoke();
         }
      };
   }

   @NotNull
   public static final Sequence asSequence(@NotNull Iterator $this$asSequence) {
      Intrinsics.checkNotNullParameter($this$asSequence, "<this>");
      return SequencesKt.constrainOnce(new SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1($this$asSequence));
   }

   @NotNull
   public static final Sequence sequenceOf(@NotNull Object... elements) {
      Intrinsics.checkNotNullParameter(elements, "elements");
      return ArraysKt.asSequence(elements);
   }

   @NotNull
   public static final Sequence emptySequence() {
      return EmptySequence.INSTANCE;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Sequence orEmpty(Sequence $this$orEmpty) {
      Sequence var10000 = $this$orEmpty;
      if ($this$orEmpty == null) {
         var10000 = SequencesKt.emptySequence();
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @NotNull
   public static final Sequence ifEmpty(@NotNull final Sequence $this$ifEmpty, @NotNull final Function0 defaultValue) {
      Intrinsics.checkNotNullParameter($this$ifEmpty, "<this>");
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      return SequencesKt.sequence(new Function2($this$ifEmpty, defaultValue, (Continuation)null) {
         int label;
         // $FF: synthetic field
         private Object L$0;

         public final Object invokeSuspend(Object $result) {
            Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  SequenceScope $this$sequence = (SequenceScope)this.L$0;
                  Iterator iterator = $this$ifEmpty.iterator();
                  if (iterator.hasNext()) {
                     Continuation var5 = this;
                     this.label = 1;
                     if ($this$sequence.yieldAll(iterator, var5) == var4) {
                        return var4;
                     }
                  } else {
                     Sequence var10001 = (Sequence)defaultValue.invoke();
                     Continuation var10002 = this;
                     this.label = 2;
                     if ($this$sequence.yieldAll(var10001, var10002) == var4) {
                        return var4;
                     }
                  }
                  break;
               case 1:
                  ResultKt.throwOnFailure($result);
                  break;
               case 2:
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            return Unit.INSTANCE;
         }

         public final Continuation create(Object value, Continuation $completion) {
            Function2 var3 = new <anonymous constructor>($this$ifEmpty, defaultValue, $completion);
            var3.L$0 = value;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope p1, Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      });
   }

   @NotNull
   public static final Sequence flatten(@NotNull Sequence $this$flatten) {
      Intrinsics.checkNotNullParameter($this$flatten, "<this>");
      return flatten$SequencesKt__SequencesKt($this$flatten, null.INSTANCE);
   }

   @JvmName(
      name = "flattenSequenceOfIterable"
   )
   @NotNull
   public static final Sequence flattenSequenceOfIterable(@NotNull Sequence $this$flatten) {
      Intrinsics.checkNotNullParameter($this$flatten, "<this>");
      return flatten$SequencesKt__SequencesKt($this$flatten, null.INSTANCE);
   }

   private static final Sequence flatten$SequencesKt__SequencesKt(Sequence $this$flatten, Function1 iterator) {
      return $this$flatten instanceof TransformingSequence ? ((TransformingSequence)$this$flatten).flatten$kotlin_stdlib(iterator) : (Sequence)(new FlatteningSequence($this$flatten, null.INSTANCE, iterator));
   }

   @NotNull
   public static final Pair unzip(@NotNull Sequence $this$unzip) {
      Intrinsics.checkNotNullParameter($this$unzip, "<this>");
      ArrayList listT = new ArrayList();
      ArrayList listR = new ArrayList();

      for(Pair pair : $this$unzip) {
         listT.add(pair.getFirst());
         listR.add(pair.getSecond());
      }

      return TuplesKt.to(listT, listR);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final Sequence shuffled(@NotNull Sequence $this$shuffled) {
      Intrinsics.checkNotNullParameter($this$shuffled, "<this>");
      return SequencesKt.shuffled($this$shuffled, Random.Default);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final Sequence shuffled(@NotNull final Sequence $this$shuffled, @NotNull final Random random) {
      Intrinsics.checkNotNullParameter($this$shuffled, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      return SequencesKt.sequence(new Function2($this$shuffled, random, (Continuation)null) {
         Object L$1;
         int label;
         // $FF: synthetic field
         private Object L$0;

         public final Object invokeSuspend(Object $result) {
            Object var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            SequenceScope $this$sequence;
            List buffer;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  $this$sequence = (SequenceScope)this.L$0;
                  buffer = SequencesKt.toMutableList($this$shuffled);
                  break;
               case 1:
                  buffer = (List)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            while(!((Collection)buffer).isEmpty()) {
               int j = random.nextInt(buffer.size());
               Object last = CollectionsKt.removeLast(buffer);
               Object value = j < buffer.size() ? buffer.set(j, last) : last;
               Continuation var10002 = this;
               this.L$0 = $this$sequence;
               this.L$1 = buffer;
               this.label = 1;
               if ($this$sequence.yield(value, var10002) == var7) {
                  return var7;
               }
            }

            return Unit.INSTANCE;
         }

         public final Continuation create(Object value, Continuation $completion) {
            Function2 var3 = new <anonymous constructor>($this$shuffled, random, $completion);
            var3.L$0 = value;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope p1, Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      });
   }

   @NotNull
   public static final Sequence flatMapIndexed(@NotNull final Sequence source, @NotNull final Function2 transform, @NotNull final Function1 iterator) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(transform, "transform");
      Intrinsics.checkNotNullParameter(iterator, "iterator");
      return SequencesKt.sequence(new Function2(source, transform, iterator, (Continuation)null) {
         Object L$1;
         int I$0;
         int label;
         // $FF: synthetic field
         private Object L$0;

         public final Object invokeSuspend(Object $result) {
            Object var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            SequenceScope $this$sequence;
            int index;
            Iterator var4;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  $this$sequence = (SequenceScope)this.L$0;
                  index = 0;
                  var4 = source.iterator();
                  break;
               case 1:
                  index = this.I$0;
                  var4 = (Iterator)this.L$1;
                  $this$sequence = (SequenceScope)this.L$0;
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            while(var4.hasNext()) {
               Object element = var4.next();
               Function2 var10000 = transform;
               int var7 = index++;
               if (var7 < 0) {
                  CollectionsKt.throwIndexOverflow();
               }

               Object result = var10000.invoke(Boxing.boxInt(var7), element);
               Iterator var9 = (Iterator)iterator.invoke(result);
               Continuation var10002 = this;
               this.L$0 = $this$sequence;
               this.L$1 = var4;
               this.I$0 = index;
               this.label = 1;
               if ($this$sequence.yieldAll(var9, var10002) == var8) {
                  return var8;
               }
            }

            return Unit.INSTANCE;
         }

         public final Continuation create(Object value, Continuation $completion) {
            Function2 var3 = new <anonymous constructor>(source, transform, iterator, $completion);
            var3.L$0 = value;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope p1, Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      });
   }

   @NotNull
   public static final Sequence constrainOnce(@NotNull Sequence $this$constrainOnce) {
      Intrinsics.checkNotNullParameter($this$constrainOnce, "<this>");
      return $this$constrainOnce instanceof ConstrainedOnceSequence ? $this$constrainOnce : (Sequence)(new ConstrainedOnceSequence($this$constrainOnce));
   }

   @NotNull
   public static final Sequence generateSequence(@NotNull final Function0 nextFunction) {
      Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
      return SequencesKt.constrainOnce(new GeneratorSequence(nextFunction, new Function1(nextFunction) {
         public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return nextFunction.invoke();
         }
      }));
   }

   @LowPriorityInOverloadResolution
   @NotNull
   public static final Sequence generateSequence(@Nullable final Object seed, @NotNull Function1 nextFunction) {
      Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
      return seed == null ? (Sequence)EmptySequence.INSTANCE : (Sequence)(new GeneratorSequence(new Function0(seed) {
         public final Object invoke() {
            return seed;
         }
      }, nextFunction));
   }

   @NotNull
   public static final Sequence generateSequence(@NotNull Function0 seedFunction, @NotNull Function1 nextFunction) {
      Intrinsics.checkNotNullParameter(seedFunction, "seedFunction");
      Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
      return new GeneratorSequence(seedFunction, nextFunction);
   }

   public SequencesKt__SequencesKt() {
   }
}
