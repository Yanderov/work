package kotlin.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Repeatable;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlin.jvm.internal.RepeatableContainer;

@Target(
   allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.TYPEALIAS}
)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@java.lang.annotation.Repeatable(Container.class)
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0081\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\n\u001a\u00020\t8\u0006¢\u0006\u0006\u001a\u0004\b\n\u0010\rR\u0011\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u000fR\u0011\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u000fR\u0011\u0010\b\u001a\u00020\u00078\u0006¢\u0006\u0006\u001a\u0004\b\b\u0010\u0010¨\u0006\u0011"},
   d2 = {"Lkotlin/internal/RequireKotlin;", "", "", "version", "message", "Lkotlin/DeprecationLevel;", "level", "Lkotlin/internal/RequireKotlinVersionKind;", "versionKind", "", "errorCode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/DeprecationLevel;Lkotlin/internal/RequireKotlinVersionKind;I)V", "()I", "()Lkotlin/DeprecationLevel;", "()Ljava/lang/String;", "()Lkotlin/internal/RequireKotlinVersionKind;", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.2"
)
public @interface RequireKotlin {
   String version();

   String message() default "";

   DeprecationLevel level() default DeprecationLevel.ERROR;

   RequireKotlinVersionKind versionKind() default RequireKotlinVersionKind.LANGUAGE_VERSION;

   int errorCode() default -1;

   @Target(
      allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.TYPEALIAS}
   )
   @Retention(AnnotationRetention.SOURCE)
   @RepeatableContainer
   @java.lang.annotation.Retention(RetentionPolicy.SOURCE)
   @java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public @interface Container {
      RequireKotlin[] value();
   }
}
