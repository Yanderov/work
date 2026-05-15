package kotlin.properties;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00028\u00000\u0005\"\b\b\u0000\u0010\u0004*\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u0082\u0001\u0010\u0012\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00042\u0006\u0010\b\u001a\u00028\u00002Q\b\u0004\u0010\u0011\u001aK\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\tH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0082\u0001\u0010\u0015\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00042\u0006\u0010\b\u001a\u00028\u00002Q\b\u0004\u0010\u0011\u001aK\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00140\tH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0016"},
   d2 = {"Lkotlin/properties/Delegates;", "", "<init>", "()V", "T", "Lkotlin/properties/ReadWriteProperty;", "notNull", "()Lkotlin/properties/ReadWriteProperty;", "initialValue", "Lkotlin/Function3;", "Lkotlin/reflect/KProperty;", "Lkotlin/ParameterName;", "name", "property", "oldValue", "newValue", "", "onChange", "observable", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlin/properties/ReadWriteProperty;", "", "vetoable", "kotlin-stdlib"}
)
public final class Delegates {
   @NotNull
   public static final Delegates INSTANCE = new Delegates();

   private Delegates() {
   }

   @NotNull
   public final ReadWriteProperty notNull() {
      return new NotNullVar();
   }

   @NotNull
   public final ReadWriteProperty observable(Object initialValue, @NotNull final Function3 onChange) {
      Intrinsics.checkNotNullParameter(onChange, "onChange");
      int $i$f$observable = 0;
      return new ObservableProperty(initialValue, onChange) {
         protected void afterChange(KProperty property, Object oldValue, Object newValue) {
            Intrinsics.checkNotNullParameter(property, "property");
            onChange.invoke(property, oldValue, newValue);
         }
      };
   }

   @NotNull
   public final ReadWriteProperty vetoable(Object initialValue, @NotNull final Function3 onChange) {
      Intrinsics.checkNotNullParameter(onChange, "onChange");
      int $i$f$vetoable = 0;
      return new ObservableProperty(initialValue, onChange) {
         protected boolean beforeChange(KProperty property, Object oldValue, Object newValue) {
            Intrinsics.checkNotNullParameter(property, "property");
            return (Boolean)onChange.invoke(property, oldValue, newValue);
         }
      };
   }
}
