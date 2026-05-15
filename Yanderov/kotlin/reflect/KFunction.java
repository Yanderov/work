package kotlin.reflect;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.SinceKotlin;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\r\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003R\u001a\u0010\u0005\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\n\u0010\b\u001a\u0004\b\t\u0010\u0006R\u001a\u0010\u000b\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\f\u0010\b\u001a\u0004\b\u000b\u0010\u0006R\u001a\u0010\r\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\b\u001a\u0004\b\r\u0010\u0006R\u001a\u0010\u000f\u001a\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\b\u001a\u0004\b\u000f\u0010\u0006¨\u0006\u0011"},
   d2 = {"Lkotlin/reflect/KFunction;", "R", "Lkotlin/reflect/KCallable;", "Lkotlin/Function;", "", "isExternal", "()Z", "isExternal$annotations", "()V", "isInfix", "isInfix$annotations", "isInline", "isInline$annotations", "isOperator", "isOperator$annotations", "isSuspend", "isSuspend$annotations", "kotlin-stdlib"}
)
public interface KFunction extends KCallable, Function {
   boolean isInline();

   boolean isExternal();

   boolean isOperator();

   boolean isInfix();

   boolean isSuspend();

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      /** @deprecated */
      // $FF: synthetic method
      @SinceKotlin(
         version = "1.1"
      )
      public static void isInline$annotations() {
      }

      /** @deprecated */
      // $FF: synthetic method
      @SinceKotlin(
         version = "1.1"
      )
      public static void isExternal$annotations() {
      }

      /** @deprecated */
      // $FF: synthetic method
      @SinceKotlin(
         version = "1.1"
      )
      public static void isOperator$annotations() {
      }

      /** @deprecated */
      // $FF: synthetic method
      @SinceKotlin(
         version = "1.1"
      )
      public static void isInfix$annotations() {
      }

      /** @deprecated */
      // $FF: synthetic method
      @SinceKotlin(
         version = "1.1"
      )
      public static void isSuspend$annotations() {
      }
   }
}
