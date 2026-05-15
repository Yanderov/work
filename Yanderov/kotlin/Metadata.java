package kotlin;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlin.jvm.JvmName;

@Retention(AnnotationRetention.RUNTIME)
@Target(
   allowedTargets = {AnnotationTarget.CLASS}
)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.TYPE})
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0018\b\u0087\u0002\u0018\u00002\u00020\u0001Bc\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u00048\u0007X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\r\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\b8\u0007¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0003\u001a\u00020\u00028\u0007¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u00048\u0007¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0011R\u001a\u0010\f\u001a\u00020\b8\u0007X\u0087\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010\u0013\u001a\u0004\b\u001e\u0010\u001b¨\u0006 "},
   d2 = {"Lkotlin/Metadata;", "", "", "kind", "", "metadataVersion", "bytecodeVersion", "", "", "data1", "data2", "extraString", "packageName", "extraInt", "<init>", "(I[I[ILkotlin/Array;Lkotlin/Array;Ljava/lang/String;Ljava/lang/String;I)V", "bv", "()[I", "bv$annotations", "()V", "d1", "()[Ljava/lang/String;", "d2", "xi", "()I", "xi$annotations", "xs", "()Ljava/lang/String;", "k", "mv", "pn", "pn$annotations", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.3"
)
public @interface Metadata {
   @JvmName(
      name = "k"
   )
   int k() default 1;

   @JvmName(
      name = "mv"
   )
   int[] mv() default {};

   /** @deprecated */
   @JvmName(
      name = "bv"
   )
   int[] bv() default {1, 0, 3};

   @JvmName(
      name = "d1"
   )
   String[] d1() default {};

   @JvmName(
      name = "d2"
   )
   String[] d2() default {};

   @JvmName(
      name = "xs"
   )
   String xs() default "";

   @JvmName(
      name = "pn"
   )
   String pn() default "";

   @JvmName(
      name = "xi"
   )
   int xi() default 0;

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      /** @deprecated */
      // $FF: synthetic method
      @Deprecated(
         message = "Bytecode version had no significant use in Kotlin metadata and it will be removed in a future version.",
         level = DeprecationLevel.WARNING
      )
      public static void bv$annotations() {
      }

      /** @deprecated */
      // $FF: synthetic method
      @SinceKotlin(
         version = "1.2"
      )
      public static void pn$annotations() {
      }

      /** @deprecated */
      // $FF: synthetic method
      @SinceKotlin(
         version = "1.1"
      )
      public static void xi$annotations() {
      }
   }
}
