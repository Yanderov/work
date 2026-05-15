package kotlin.jvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Target;

/** @deprecated */
@Target(
   allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY}
)
@Deprecated(
   message = "Switch to new -Xjvm-default modes: `all` or `all-compatibility`",
   level = DeprecationLevel.ERROR
)
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.METHOD})
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0003\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"},
   d2 = {"Lkotlin/jvm/JvmDefault;", "", "<init>", "()V", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.2"
)
public @interface JvmDefault {
}
