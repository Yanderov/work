package kotlin.jvm.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlin.jvm.JvmName;

@Retention(AnnotationRetention.BINARY)
@Target(
   allowedTargets = {AnnotationTarget.CLASS}
)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target({ElementType.TYPE})
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"},
   d2 = {"Lkotlin/jvm/internal/SerializedIr;", "", "", "", "bytes", "<init>", "(Lkotlin/Array;)V", "b", "()[Ljava/lang/String;", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.6"
)
public @interface SerializedIr {
   @JvmName(
      name = "b"
   )
   String[] b() default {};
}
