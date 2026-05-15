package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.WasExperimental;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0084\u0001\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0019\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\bB\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a#\u0010\n\u001a\u00020\t*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a#\u0010\f\u001a\u00020\t*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\u000b\u001a&\u0010\u000f\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0086\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a&\u0010\u000f\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007H\u0086\u0002¢\u0006\u0004\b\u000f\u0010\u0011\u001a\u001c\u0010\u000f\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\n¢\u0006\u0004\b\u000f\u0010\u0014\u001a\u001f\u0010\u0015\u001a\u00020\u0007*\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001f\u0010\u0017\u001a\u00020\u0007*\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0004\b\u0017\u0010\u0016\u001a#\u0010\u0018\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0018\u0010\u0010\u001a#\u0010\u0018\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0018\u0010\u0011\u001aA\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\u001d*\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001aG\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\u001d*\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001a2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u0007H\u0002¢\u0006\u0004\b!\u0010\"\u001aA\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\u001d*\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b#\u0010\u001f\u001a\u0019\u0010%\u001a\u00020\u0007*\u00020\u00052\u0006\u0010$\u001a\u00020\u0000¢\u0006\u0004\b%\u0010&\u001aF\u0010+\u001a\u00028\u0001\"\f\b\u0000\u0010'*\u00020\u0005*\u00028\u0001\"\u0004\b\u0001\u0010(*\u00028\u00002\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00010)H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b+\u0010,\u001aF\u0010-\u001a\u00028\u0001\"\f\b\u0000\u0010'*\u00020\u0005*\u00028\u0001\"\u0004\b\u0001\u0010(*\u00028\u00002\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00010)H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b-\u0010,\u001a-\u0010.\u001a\u00020\u0000*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b.\u0010/\u001a=\u0010.\u001a\u00020\u0000*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u0007H\u0002¢\u0006\u0004\b1\u00102\u001a-\u0010.\u001a\u00020\u0000*\u00020\u00052\u0006\u00103\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b.\u00104\u001a-\u00107\u001a\u00020\u0000*\u00020\u00052\u0006\u00106\u001a\u0002052\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b7\u00108\u001a3\u00107\u001a\u00020\u0000*\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b7\u00109\u001a\u0011\u0010:\u001a\u00020\u0007*\u00020\u0005¢\u0006\u0004\b:\u0010;\u001a\u0014\u0010<\u001a\u00020\u0007*\u00020\u0005H\u0087\b¢\u0006\u0004\b<\u0010;\u001a\u0014\u0010=\u001a\u00020\u0007*\u00020\u0005H\u0087\b¢\u0006\u0004\b=\u0010;\u001a\u0014\u0010>\u001a\u00020\u0007*\u00020\u0005H\u0087\b¢\u0006\u0004\b>\u0010;\u001a'\u0010?\u001a\u00020\u0007*\u0004\u0018\u00010\u0005H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0004\b?\u0010;\u001a'\u0010@\u001a\u00020\u0007*\u0004\u0018\u00010\u0005H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0004\b@\u0010;\u001a\u0014\u0010B\u001a\u00020A*\u00020\u0005H\u0086\u0002¢\u0006\u0004\bB\u0010C\u001a-\u0010D\u001a\u00020\u0000*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\bD\u0010/\u001a-\u0010D\u001a\u00020\u0000*\u00020\u00052\u0006\u00103\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\bD\u00104\u001a-\u0010E\u001a\u00020\u0000*\u00020\u00052\u0006\u00106\u001a\u0002052\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\bE\u00108\u001a3\u0010E\u001a\u00020\u0000*\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\bE\u00109\u001a\u0017\u0010G\u001a\b\u0012\u0004\u0012\u00020\t0F*\u00020\u0005¢\u0006\u0004\bG\u0010H\u001a\u0017\u0010J\u001a\b\u0012\u0004\u0012\u00020\t0I*\u00020\u0005¢\u0006\u0004\bJ\u0010K\u001a\u001c\u0010L\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\f¢\u0006\u0004\bL\u0010\u0014\u001a\u0016\u0010M\u001a\u00020\t*\u0004\u0018\u00010\tH\u0087\b¢\u0006\u0004\bM\u0010N\u001a#\u0010Q\u001a\u00020\u0005*\u00020\u00052\u0006\u0010O\u001a\u00020\u00002\b\b\u0002\u0010P\u001a\u00020\r¢\u0006\u0004\bQ\u0010R\u001a#\u0010Q\u001a\u00020\t*\u00020\t2\u0006\u0010O\u001a\u00020\u00002\b\b\u0002\u0010P\u001a\u00020\r¢\u0006\u0004\bQ\u0010S\u001a#\u0010T\u001a\u00020\u0005*\u00020\u00052\u0006\u0010O\u001a\u00020\u00002\b\b\u0002\u0010P\u001a\u00020\r¢\u0006\u0004\bT\u0010R\u001a#\u0010T\u001a\u00020\t*\u00020\t2\u0006\u0010O\u001a\u00020\u00002\b\b\u0002\u0010P\u001a\u00020\r¢\u0006\u0004\bT\u0010S\u001aG\u0010Z\u001a\b\u0012\u0004\u0012\u00020W0F*\u00020\u00052\u000e\u0010V\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0U2\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\bX\u0010Y\u001a?\u0010Z\u001a\b\u0012\u0004\u0012\u00020W0F*\u00020\u00052\u0006\u0010V\u001a\u0002052\b\b\u0002\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\bX\u0010[\u001a;\u0010^\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\\\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010]\u001a\u00020\u00002\u0006\u0010O\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b^\u0010_\u001a\u0019\u0010a\u001a\u00020\u0005*\u00020\u00052\u0006\u0010`\u001a\u00020\u0005¢\u0006\u0004\ba\u0010b\u001a\u0019\u0010a\u001a\u00020\t*\u00020\t2\u0006\u0010`\u001a\u00020\u0005¢\u0006\u0004\ba\u0010c\u001a!\u0010d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0000¢\u0006\u0004\bd\u0010e\u001a\u0019\u0010d\u001a\u00020\u0005*\u00020\u00052\u0006\u0010f\u001a\u00020W¢\u0006\u0004\bd\u0010g\u001a$\u0010d\u001a\u00020\t*\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bd\u0010h\u001a\u001c\u0010d\u001a\u00020\t*\u00020\t2\u0006\u0010f\u001a\u00020WH\u0087\b¢\u0006\u0004\bd\u0010i\u001a\u0019\u0010j\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005¢\u0006\u0004\bj\u0010b\u001a\u0019\u0010j\u001a\u00020\t*\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0005¢\u0006\u0004\bj\u0010c\u001a\u0019\u0010l\u001a\u00020\u0005*\u00020\u00052\u0006\u0010k\u001a\u00020\u0005¢\u0006\u0004\bl\u0010b\u001a!\u0010l\u001a\u00020\u0005*\u00020\u00052\u0006\u0010`\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005¢\u0006\u0004\bl\u0010m\u001a\u0019\u0010l\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\u0005¢\u0006\u0004\bl\u0010c\u001a!\u0010l\u001a\u00020\t*\u00020\t2\u0006\u0010`\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005¢\u0006\u0004\bl\u0010n\u001a5\u0010r\u001a\u00020\t*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00122\u0014\b\b\u0010q\u001a\u000e\u0012\u0004\u0012\u00020p\u0012\u0004\u0012\u00020\u00050oH\u0087\bø\u0001\u0000¢\u0006\u0004\br\u0010s\u001a$\u0010r\u001a\u00020\t*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010t\u001a\u00020\tH\u0087\b¢\u0006\u0004\br\u0010u\u001a+\u0010w\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\r2\u0006\u0010t\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0004\bw\u0010x\u001a+\u0010w\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\t2\u0006\u0010t\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0004\bw\u0010y\u001a+\u0010z\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\r2\u0006\u0010t\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0004\bz\u0010x\u001a+\u0010z\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\t2\u0006\u0010t\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0004\bz\u0010y\u001a+\u0010{\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\r2\u0006\u0010t\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0004\b{\u0010x\u001a+\u0010{\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\t2\u0006\u0010t\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0004\b{\u0010y\u001a+\u0010|\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\r2\u0006\u0010t\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0004\b|\u0010x\u001a+\u0010|\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\t2\u0006\u0010t\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0004\b|\u0010y\u001a$\u0010}\u001a\u00020\t*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010t\u001a\u00020\tH\u0087\b¢\u0006\u0004\b}\u0010u\u001a,\u0010\u0080\u0001\u001a\u00020\t*\u00020\t2\u0012\u0010q\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0oH\u0087\bø\u0001\u0000¢\u0006\u0004\b~\u0010\u007f\u001a-\u0010\u0080\u0001\u001a\u00020\t*\u00020\t2\u0012\u0010q\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00050oH\u0087\bø\u0001\u0000¢\u0006\u0005\b\u0081\u0001\u0010\u007f\u001a,\u0010\u0082\u0001\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00002\u0006\u0010t\u001a\u00020\u0005¢\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001\u001a$\u0010\u0082\u0001\u001a\u00020\u0005*\u00020\u00052\u0006\u0010f\u001a\u00020W2\u0006\u0010t\u001a\u00020\u0005¢\u0006\u0006\b\u0082\u0001\u0010\u0084\u0001\u001a/\u0010\u0082\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00002\u0006\u0010t\u001a\u00020\u0005H\u0087\b¢\u0006\u0006\b\u0082\u0001\u0010\u0085\u0001\u001a'\u0010\u0082\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010f\u001a\u00020W2\u0006\u0010t\u001a\u00020\u0005H\u0087\b¢\u0006\u0006\b\u0082\u0001\u0010\u0086\u0001\u001aB\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\t0I*\u00020\u00052\u0012\u0010V\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0U\"\u00020\t2\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u0000¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001\u001a:\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\t0I*\u00020\u00052\n\u0010V\u001a\u000205\"\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u0000¢\u0006\u0006\b\u0087\u0001\u0010\u0089\u0001\u001a4\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\t0I*\u00020\u00052\u0006\u0010k\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001\u001a/\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\t0I*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0001\u001a\u00020\u0000H\u0087\b¢\u0006\u0006\b\u0087\u0001\u0010\u008c\u0001\u001aB\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\t0F*\u00020\u00052\u0012\u0010V\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0U\"\u00020\t2\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u0000¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001\u001a:\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\t0F*\u00020\u00052\n\u0010V\u001a\u000205\"\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u0000¢\u0006\u0006\b\u008d\u0001\u0010\u008f\u0001\u001a/\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\t0F*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0001\u001a\u00020\u0000H\u0087\b¢\u0006\u0006\b\u008d\u0001\u0010\u0090\u0001\u001a%\u0010\u0091\u0001\u001a\u00020\u0007*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0005\b\u0091\u0001\u0010\u0010\u001a%\u0010\u0091\u0001\u001a\u00020\u0007*\u00020\u00052\u0006\u0010`\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0005\b\u0091\u0001\u0010\u0011\u001a.\u0010\u0091\u0001\u001a\u00020\u0007*\u00020\u00052\u0006\u0010`\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001\u001a\u001b\u0010\u0093\u0001\u001a\u00020\u0005*\u00020\u00052\u0006\u0010f\u001a\u00020W¢\u0006\u0005\b\u0093\u0001\u0010g\u001a)\u0010\u0093\u0001\u001a\u00020\u0005*\u00020\t2\u0007\u0010\u0094\u0001\u001a\u00020\u00002\u0007\u0010\u0095\u0001\u001a\u00020\u0000H\u0087\b¢\u0006\u0006\b\u0093\u0001\u0010\u0096\u0001\u001a)\u0010\u0097\u0001\u001a\u00020\t*\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u00100\u001a\u00020\u0000H\u0087\b¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001\u001a\u001c\u0010\u0097\u0001\u001a\u00020\t*\u00020\u00052\u0006\u0010f\u001a\u00020W¢\u0006\u0006\b\u0097\u0001\u0010\u0099\u0001\u001a\u001b\u0010\u0097\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010f\u001a\u00020W¢\u0006\u0005\b\u0097\u0001\u0010i\u001a&\u0010\u009a\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\r2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001\u001a&\u0010\u009a\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0006\b\u009a\u0001\u0010\u009c\u0001\u001a&\u0010\u009d\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\r2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0006\b\u009d\u0001\u0010\u009b\u0001\u001a&\u0010\u009d\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0006\b\u009d\u0001\u0010\u009c\u0001\u001a&\u0010\u009e\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\r2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0006\b\u009e\u0001\u0010\u009b\u0001\u001a&\u0010\u009e\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0006\b\u009e\u0001\u0010\u009c\u0001\u001a&\u0010\u009f\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\r2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0006\b\u009f\u0001\u0010\u009b\u0001\u001a&\u0010\u009f\u0001\u001a\u00020\t*\u00020\t2\u0006\u0010k\u001a\u00020\t2\b\b\u0002\u0010v\u001a\u00020\t¢\u0006\u0006\b\u009f\u0001\u0010\u009c\u0001\u001a\u0016\u0010 \u0001\u001a\u00020\u0007*\u00020\tH\u0007¢\u0006\u0006\b \u0001\u0010¡\u0001\u001a\u0018\u0010¢\u0001\u001a\u0004\u0018\u00010\u0007*\u00020\tH\u0007¢\u0006\u0006\b¢\u0001\u0010£\u0001\u001a\u0014\u0010¤\u0001\u001a\u00020\u0005*\u00020\u0005¢\u0006\u0006\b¤\u0001\u0010¥\u0001\u001a/\u0010¤\u0001\u001a\u00020\u0005*\u00020\u00052\u0013\u0010¦\u0001\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070oH\u0086\bø\u0001\u0000¢\u0006\u0006\b¤\u0001\u0010§\u0001\u001a \u0010¤\u0001\u001a\u00020\u0005*\u00020\u00052\n\u00106\u001a\u000205\"\u00020\r¢\u0006\u0006\b¤\u0001\u0010¨\u0001\u001a\u0016\u0010¤\u0001\u001a\u00020\t*\u00020\tH\u0087\b¢\u0006\u0005\b¤\u0001\u0010N\u001a.\u0010¤\u0001\u001a\u00020\t*\u00020\t2\u0013\u0010¦\u0001\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070oH\u0086\bø\u0001\u0000¢\u0006\u0005\b¤\u0001\u0010\u007f\u001a \u0010¤\u0001\u001a\u00020\t*\u00020\t2\n\u00106\u001a\u000205\"\u00020\r¢\u0006\u0006\b¤\u0001\u0010©\u0001\u001a\u0014\u0010ª\u0001\u001a\u00020\u0005*\u00020\u0005¢\u0006\u0006\bª\u0001\u0010¥\u0001\u001a/\u0010ª\u0001\u001a\u00020\u0005*\u00020\u00052\u0013\u0010¦\u0001\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070oH\u0086\bø\u0001\u0000¢\u0006\u0006\bª\u0001\u0010§\u0001\u001a \u0010ª\u0001\u001a\u00020\u0005*\u00020\u00052\n\u00106\u001a\u000205\"\u00020\r¢\u0006\u0006\bª\u0001\u0010¨\u0001\u001a\u0016\u0010ª\u0001\u001a\u00020\t*\u00020\tH\u0087\b¢\u0006\u0005\bª\u0001\u0010N\u001a.\u0010ª\u0001\u001a\u00020\t*\u00020\t2\u0013\u0010¦\u0001\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070oH\u0086\bø\u0001\u0000¢\u0006\u0005\bª\u0001\u0010\u007f\u001a \u0010ª\u0001\u001a\u00020\t*\u00020\t2\n\u00106\u001a\u000205\"\u00020\r¢\u0006\u0006\bª\u0001\u0010©\u0001\u001a\u0014\u0010«\u0001\u001a\u00020\u0005*\u00020\u0005¢\u0006\u0006\b«\u0001\u0010¥\u0001\u001a/\u0010«\u0001\u001a\u00020\u0005*\u00020\u00052\u0013\u0010¦\u0001\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070oH\u0086\bø\u0001\u0000¢\u0006\u0006\b«\u0001\u0010§\u0001\u001a \u0010«\u0001\u001a\u00020\u0005*\u00020\u00052\n\u00106\u001a\u000205\"\u00020\r¢\u0006\u0006\b«\u0001\u0010¨\u0001\u001a\u0016\u0010«\u0001\u001a\u00020\t*\u00020\tH\u0087\b¢\u0006\u0005\b«\u0001\u0010N\u001a.\u0010«\u0001\u001a\u00020\t*\u00020\t2\u0013\u0010¦\u0001\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00070oH\u0086\bø\u0001\u0000¢\u0006\u0005\b«\u0001\u0010\u007f\u001a \u0010«\u0001\u001a\u00020\t*\u00020\t2\n\u00106\u001a\u000205\"\u00020\r¢\u0006\u0006\b«\u0001\u0010©\u0001\"\u0018\u0010®\u0001\u001a\u00020W*\u00020\u00058F¢\u0006\b\u001a\u0006\b¬\u0001\u0010\u00ad\u0001\"\u0018\u0010±\u0001\u001a\u00020\u0000*\u00020\u00058F¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006²\u0001"},
   d2 = {"", "limit", "", "requireNonNegativeLimit", "(I)V", "", "other", "", "ignoreCase", "", "commonPrefixWith", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Z)Ljava/lang/String;", "commonSuffixWith", "", "char", "contains", "(Ljava/lang/CharSequence;CZ)Z", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Z)Z", "Lkotlin/text/Regex;", "regex", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;)Z", "contentEqualsIgnoreCaseImpl", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z", "contentEqualsImpl", "endsWith", "suffix", "", "strings", "startIndex", "Lkotlin/Pair;", "findAnyOf", "(Ljava/lang/CharSequence;Ljava/util/Collection;IZ)Lkotlin/Pair;", "last", "findAnyOf$StringsKt__StringsKt", "(Ljava/lang/CharSequence;Ljava/util/Collection;IZZ)Lkotlin/Pair;", "findLastAnyOf", "index", "hasSurrogatePairAt", "(Ljava/lang/CharSequence;I)Z", "C", "R", "Lkotlin/Function0;", "defaultValue", "ifBlank", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "(Ljava/lang/CharSequence;CIZ)I", "endIndex", "indexOf$StringsKt__StringsKt", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;IIZZ)I", "string", "(Ljava/lang/CharSequence;Ljava/lang/String;IZ)I", "", "chars", "indexOfAny", "(Ljava/lang/CharSequence;[CIZ)I", "(Ljava/lang/CharSequence;Ljava/util/Collection;IZ)I", "isBlank", "(Ljava/lang/CharSequence;)Z", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "Lkotlin/collections/CharIterator;", "iterator", "(Ljava/lang/CharSequence;)Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "Lkotlin/sequences/Sequence;", "lineSequence", "(Ljava/lang/CharSequence;)Lkotlin/sequences/Sequence;", "", "lines", "(Ljava/lang/CharSequence;)Ljava/util/List;", "matches", "orEmpty", "(Ljava/lang/String;)Ljava/lang/String;", "length", "padChar", "padEnd", "(Ljava/lang/CharSequence;IC)Ljava/lang/CharSequence;", "(Ljava/lang/String;IC)Ljava/lang/String;", "padStart", "", "delimiters", "Lkotlin/ranges/IntRange;", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "rangesDelimitedBy", "(Ljava/lang/CharSequence;[CIZI)Lkotlin/sequences/Sequence;", "thisOffset", "otherOffset", "regionMatchesImpl", "(Ljava/lang/CharSequence;ILjava/lang/CharSequence;IIZ)Z", "prefix", "removePrefix", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;", "removeRange", "(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;", "range", "(Ljava/lang/CharSequence;Lkotlin/ranges/IntRange;)Ljava/lang/CharSequence;", "(Ljava/lang/String;II)Ljava/lang/String;", "(Ljava/lang/String;Lkotlin/ranges/IntRange;)Ljava/lang/String;", "removeSuffix", "delimiter", "removeSurrounding", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/String;Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "transform", "replace", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "replacement", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;Ljava/lang/String;)Ljava/lang/String;", "missingDelimiterValue", "replaceAfter", "(Ljava/lang/String;CLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceFirstCharWithChar", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "replaceFirstChar", "replaceFirstCharWithCharSequence", "replaceRange", "(Ljava/lang/CharSequence;IILjava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/CharSequence;Lkotlin/ranges/IntRange;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "(Ljava/lang/String;IILjava/lang/CharSequence;)Ljava/lang/String;", "(Ljava/lang/String;Lkotlin/ranges/IntRange;Ljava/lang/CharSequence;)Ljava/lang/String;", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "(Ljava/lang/CharSequence;[CZI)Ljava/util/List;", "split$StringsKt__StringsKt", "(Ljava/lang/CharSequence;Ljava/lang/String;ZI)Ljava/util/List;", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;I)Ljava/util/List;", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "(Ljava/lang/CharSequence;[CZI)Lkotlin/sequences/Sequence;", "(Ljava/lang/CharSequence;Lkotlin/text/Regex;I)Lkotlin/sequences/Sequence;", "startsWith", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;IZ)Z", "subSequence", "start", "end", "(Ljava/lang/String;II)Ljava/lang/CharSequence;", "substring", "(Ljava/lang/CharSequence;II)Ljava/lang/String;", "(Ljava/lang/CharSequence;Lkotlin/ranges/IntRange;)Ljava/lang/String;", "substringAfter", "(Ljava/lang/String;CLjava/lang/String;)Ljava/lang/String;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "substringAfterLast", "substringBefore", "substringBeforeLast", "toBooleanStrict", "(Ljava/lang/String;)Z", "toBooleanStrictOrNull", "(Ljava/lang/String;)Ljava/lang/Boolean;", "trim", "(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "predicate", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "(Ljava/lang/CharSequence;[C)Ljava/lang/CharSequence;", "(Ljava/lang/String;[C)Ljava/lang/String;", "trimEnd", "trimStart", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "indices", "getLastIndex", "(Ljava/lang/CharSequence;)I", "lastIndex", "kotlin-stdlib"},
   xs = "kotlin/text/StringsKt"
)
@SourceDebugExtension({"SMAP\nStrings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Strings.kt\nkotlin/text/StringsKt__StringsKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1499:1\n80#1,22:1500\n114#1,5:1522\n131#1,5:1527\n80#1,22:1532\n108#1:1554\n80#1,22:1555\n114#1,5:1577\n125#1:1582\n114#1,5:1583\n131#1,5:1588\n142#1:1593\n131#1,5:1594\n80#1,22:1599\n114#1,5:1621\n131#1,5:1626\n1069#2,2:1631\n12591#3,2:1633\n12591#3,2:1635\n295#4,2:1637\n295#4,2:1639\n1557#4:1642\n1628#4,3:1643\n1557#4:1646\n1628#4,3:1647\n1#5:1641\n*S KotlinDebug\n*F\n+ 1 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n108#1:1500,22\n125#1:1522,5\n142#1:1527,5\n147#1:1532,22\n152#1:1554\n152#1:1555,22\n157#1:1577,5\n162#1:1582\n162#1:1583,5\n167#1:1588,5\n172#1:1593\n172#1:1594,5\n177#1:1599,22\n188#1:1621,5\n199#1:1626,5\n312#1:1631,2\n952#1:1633,2\n976#1:1635,2\n1015#1:1637,2\n1021#1:1639,2\n1321#1:1642\n1321#1:1643,3\n1346#1:1646\n1346#1:1647,3\n*E\n"})
class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
   @NotNull
   public static final CharSequence trim(@NotNull CharSequence $this$trim, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$trim, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$trim = 0;
      int startIndex = 0;
      int endIndex = $this$trim.length() - 1;
      boolean startFound = false;

      while(startIndex <= endIndex) {
         int index = !startFound ? startIndex : endIndex;
         boolean match = (Boolean)predicate.invoke($this$trim.charAt(index));
         if (!startFound) {
            if (!match) {
               startFound = true;
            } else {
               ++startIndex;
            }
         } else {
            if (!match) {
               break;
            }

            --endIndex;
         }
      }

      return $this$trim.subSequence(startIndex, endIndex + 1);
   }

   @NotNull
   public static final String trim(@NotNull String $this$trim, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$trim, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$trim = 0;
      CharSequence $this$trim$iv = (CharSequence)$this$trim;
      int $i$f$trim = 0;
      int startIndex$iv = 0;
      int endIndex$iv = $this$trim$iv.length() - 1;
      boolean startFound$iv = false;

      while(startIndex$iv <= endIndex$iv) {
         int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
         boolean match$iv = (Boolean)predicate.invoke($this$trim$iv.charAt(index$iv));
         if (!startFound$iv) {
            if (!match$iv) {
               startFound$iv = true;
            } else {
               ++startIndex$iv;
            }
         } else {
            if (!match$iv) {
               break;
            }

            --endIndex$iv;
         }
      }

      return $this$trim$iv.subSequence(startIndex$iv, endIndex$iv + 1).toString();
   }

   @NotNull
   public static final CharSequence trimStart(@NotNull CharSequence $this$trimStart, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$trimStart, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$trimStart = 0;
      int index = 0;

      for(int var4 = $this$trimStart.length(); index < var4; ++index) {
         if (!(Boolean)predicate.invoke($this$trimStart.charAt(index))) {
            return $this$trimStart.subSequence(index, $this$trimStart.length());
         }
      }

      return (CharSequence)"";
   }

   @NotNull
   public static final String trimStart(@NotNull String $this$trimStart, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$trimStart, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$trimStart = 0;
      CharSequence $this$trimStart$iv = (CharSequence)$this$trimStart;
      int $i$f$trimStart = 0;
      int index$iv = 0;
      int var6 = $this$trimStart$iv.length();

      CharSequence var10000;
      while(true) {
         if (index$iv >= var6) {
            var10000 = (CharSequence)"";
            break;
         }

         if (!(Boolean)predicate.invoke($this$trimStart$iv.charAt(index$iv))) {
            var10000 = $this$trimStart$iv.subSequence(index$iv, $this$trimStart$iv.length());
            break;
         }

         ++index$iv;
      }

      return var10000.toString();
   }

   @NotNull
   public static final CharSequence trimEnd(@NotNull CharSequence $this$trimEnd, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$trimEnd, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$trimEnd = 0;
      int var3 = $this$trimEnd.length() + -1;
      if (0 <= var3) {
         do {
            int index = var3--;
            if (!(Boolean)predicate.invoke($this$trimEnd.charAt(index))) {
               return $this$trimEnd.subSequence(0, index + 1);
            }
         } while(0 <= var3);
      }

      return (CharSequence)"";
   }

   @NotNull
   public static final String trimEnd(@NotNull String $this$trimEnd, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$trimEnd, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$trimEnd = 0;
      CharSequence $this$trimEnd$iv = (CharSequence)$this$trimEnd;
      int $i$f$trimEnd = 0;
      int var5 = $this$trimEnd$iv.length() + -1;
      CharSequence var10000;
      if (0 <= var5) {
         do {
            int index$iv = var5--;
            if (!(Boolean)predicate.invoke($this$trimEnd$iv.charAt(index$iv))) {
               var10000 = $this$trimEnd$iv.subSequence(0, index$iv + 1);
               return var10000.toString();
            }
         } while(0 <= var5);
      }

      var10000 = (CharSequence)"";
      return var10000.toString();
   }

   @NotNull
   public static final CharSequence trim(@NotNull CharSequence $this$trim, @NotNull char... chars) {
      Intrinsics.checkNotNullParameter($this$trim, "<this>");
      Intrinsics.checkNotNullParameter(chars, "chars");
      CharSequence $this$trim$iv = $this$trim;
      int $i$f$trim = 0;
      int startIndex$iv = 0;
      int endIndex$iv = $this$trim.length() - 1;
      boolean startFound$iv = false;

      while(startIndex$iv <= endIndex$iv) {
         int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
         char it = $this$trim$iv.charAt(index$iv);
         int var9 = 0;
         it = ArraysKt.contains(chars, it);
         if (!startFound$iv) {
            if (!it) {
               startFound$iv = true;
            } else {
               ++startIndex$iv;
            }
         } else {
            if (!it) {
               break;
            }

            --endIndex$iv;
         }
      }

      return $this$trim$iv.subSequence(startIndex$iv, endIndex$iv + 1);
   }

   @NotNull
   public static final String trim(@NotNull String $this$trim, @NotNull char... chars) {
      Intrinsics.checkNotNullParameter($this$trim, "<this>");
      Intrinsics.checkNotNullParameter(chars, "chars");
      int $i$f$trim = 0;
      CharSequence $this$trim$iv$iv = (CharSequence)$this$trim;
      int $i$f$trim = 0;
      int startIndex$iv$iv = 0;
      int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
      boolean startFound$iv$iv = false;

      while(startIndex$iv$iv <= endIndex$iv$iv) {
         int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
         char it = $this$trim$iv$iv.charAt(index$iv$iv);
         int var11 = 0;
         it = ArraysKt.contains(chars, it);
         if (!startFound$iv$iv) {
            if (!it) {
               startFound$iv$iv = true;
            } else {
               ++startIndex$iv$iv;
            }
         } else {
            if (!it) {
               break;
            }

            --endIndex$iv$iv;
         }
      }

      return $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
   }

   @NotNull
   public static final CharSequence trimStart(@NotNull CharSequence $this$trimStart, @NotNull char... chars) {
      Intrinsics.checkNotNullParameter($this$trimStart, "<this>");
      Intrinsics.checkNotNullParameter(chars, "chars");
      CharSequence $this$trimStart$iv = $this$trimStart;
      int $i$f$trimStart = 0;
      int index$iv = 0;
      int var5 = $this$trimStart.length();

      CharSequence var10000;
      while(true) {
         if (index$iv >= var5) {
            var10000 = (CharSequence)"";
            break;
         }

         char it = $this$trimStart$iv.charAt(index$iv);
         int var7 = 0;
         if (!ArraysKt.contains(chars, it)) {
            var10000 = $this$trimStart$iv.subSequence(index$iv, $this$trimStart$iv.length());
            break;
         }

         ++index$iv;
      }

      return var10000;
   }

   @NotNull
   public static final String trimStart(@NotNull String $this$trimStart, @NotNull char... chars) {
      Intrinsics.checkNotNullParameter($this$trimStart, "<this>");
      Intrinsics.checkNotNullParameter(chars, "chars");
      int $i$f$trimStart = 0;
      CharSequence $this$trimStart$iv$iv = (CharSequence)$this$trimStart;
      int $i$f$trimStart = 0;
      int index$iv$iv = 0;
      int var7 = $this$trimStart$iv$iv.length();

      CharSequence var10000;
      while(true) {
         if (index$iv$iv >= var7) {
            var10000 = (CharSequence)"";
            break;
         }

         char it = $this$trimStart$iv$iv.charAt(index$iv$iv);
         int var9 = 0;
         if (!ArraysKt.contains(chars, it)) {
            var10000 = $this$trimStart$iv$iv.subSequence(index$iv$iv, $this$trimStart$iv$iv.length());
            break;
         }

         ++index$iv$iv;
      }

      return var10000.toString();
   }

   @NotNull
   public static final CharSequence trimEnd(@NotNull CharSequence $this$trimEnd, @NotNull char... chars) {
      Intrinsics.checkNotNullParameter($this$trimEnd, "<this>");
      Intrinsics.checkNotNullParameter(chars, "chars");
      CharSequence $this$trimEnd$iv = $this$trimEnd;
      int $i$f$trimEnd = 0;
      int var4 = $this$trimEnd.length() + -1;
      CharSequence var10000;
      if (0 <= var4) {
         do {
            int index$iv = var4--;
            char it = $this$trimEnd$iv.charAt(index$iv);
            int var7 = 0;
            if (!ArraysKt.contains(chars, it)) {
               var10000 = $this$trimEnd$iv.subSequence(0, index$iv + 1);
               return var10000;
            }
         } while(0 <= var4);
      }

      var10000 = (CharSequence)"";
      return var10000;
   }

   @NotNull
   public static final String trimEnd(@NotNull String $this$trimEnd, @NotNull char... chars) {
      Intrinsics.checkNotNullParameter($this$trimEnd, "<this>");
      Intrinsics.checkNotNullParameter(chars, "chars");
      int $i$f$trimEnd = 0;
      CharSequence $this$trimEnd$iv$iv = (CharSequence)$this$trimEnd;
      int $i$f$trimEnd = 0;
      int var6 = $this$trimEnd$iv$iv.length() + -1;
      CharSequence var10000;
      if (0 <= var6) {
         do {
            int index$iv$iv = var6--;
            char it = $this$trimEnd$iv$iv.charAt(index$iv$iv);
            int var9 = 0;
            if (!ArraysKt.contains(chars, it)) {
               var10000 = $this$trimEnd$iv$iv.subSequence(0, index$iv$iv + 1);
               return var10000.toString();
            }
         } while(0 <= var6);
      }

      var10000 = (CharSequence)"";
      return var10000.toString();
   }

   @NotNull
   public static final CharSequence trim(@NotNull CharSequence $this$trim) {
      Intrinsics.checkNotNullParameter($this$trim, "<this>");
      CharSequence $this$trim$iv = $this$trim;
      int $i$f$trim = 0;
      int startIndex$iv = 0;
      int endIndex$iv = $this$trim.length() - 1;
      boolean startFound$iv = false;

      while(startIndex$iv <= endIndex$iv) {
         int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
         char p0 = $this$trim$iv.charAt(index$iv);
         int var8 = 0;
         p0 = CharsKt.isWhitespace(p0);
         if (!startFound$iv) {
            if (!p0) {
               startFound$iv = true;
            } else {
               ++startIndex$iv;
            }
         } else {
            if (!p0) {
               break;
            }

            --endIndex$iv;
         }
      }

      return $this$trim$iv.subSequence(startIndex$iv, endIndex$iv + 1);
   }

   @InlineOnly
   private static final String trim(String $this$trim) {
      Intrinsics.checkNotNullParameter($this$trim, "<this>");
      return StringsKt.trim((CharSequence)$this$trim).toString();
   }

   @NotNull
   public static final CharSequence trimStart(@NotNull CharSequence $this$trimStart) {
      Intrinsics.checkNotNullParameter($this$trimStart, "<this>");
      CharSequence $this$trimStart$iv = $this$trimStart;
      int $i$f$trimStart = 0;
      int index$iv = 0;
      int var4 = $this$trimStart.length();

      CharSequence var10000;
      while(true) {
         if (index$iv >= var4) {
            var10000 = (CharSequence)"";
            break;
         }

         char p0 = $this$trimStart$iv.charAt(index$iv);
         int var6 = 0;
         if (!CharsKt.isWhitespace(p0)) {
            var10000 = $this$trimStart$iv.subSequence(index$iv, $this$trimStart$iv.length());
            break;
         }

         ++index$iv;
      }

      return var10000;
   }

   @InlineOnly
   private static final String trimStart(String $this$trimStart) {
      Intrinsics.checkNotNullParameter($this$trimStart, "<this>");
      return StringsKt.trimStart((CharSequence)$this$trimStart).toString();
   }

   @NotNull
   public static final CharSequence trimEnd(@NotNull CharSequence $this$trimEnd) {
      Intrinsics.checkNotNullParameter($this$trimEnd, "<this>");
      CharSequence $this$trimEnd$iv = $this$trimEnd;
      int $i$f$trimEnd = 0;
      int var3 = $this$trimEnd.length() + -1;
      CharSequence var10000;
      if (0 <= var3) {
         do {
            int index$iv = var3--;
            char p0 = $this$trimEnd$iv.charAt(index$iv);
            int var6 = 0;
            if (!CharsKt.isWhitespace(p0)) {
               var10000 = $this$trimEnd$iv.subSequence(0, index$iv + 1);
               return var10000;
            }
         } while(0 <= var3);
      }

      var10000 = (CharSequence)"";
      return var10000;
   }

   @InlineOnly
   private static final String trimEnd(String $this$trimEnd) {
      Intrinsics.checkNotNullParameter($this$trimEnd, "<this>");
      return StringsKt.trimEnd((CharSequence)$this$trimEnd).toString();
   }

   @NotNull
   public static final CharSequence padStart(@NotNull CharSequence $this$padStart, int length, char padChar) {
      Intrinsics.checkNotNullParameter($this$padStart, "<this>");
      if (length < 0) {
         throw new IllegalArgumentException("Desired length " + length + " is less than zero.");
      } else if (length <= $this$padStart.length()) {
         return $this$padStart.subSequence(0, $this$padStart.length());
      } else {
         StringBuilder sb = new StringBuilder(length);
         int i = 1;
         int var5 = length - $this$padStart.length();
         if (i <= var5) {
            while(true) {
               sb.append(padChar);
               if (i == var5) {
                  break;
               }

               ++i;
            }
         }

         sb.append($this$padStart);
         return (CharSequence)sb;
      }
   }

   // $FF: synthetic method
   public static CharSequence padStart$default(CharSequence var0, int var1, char var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = ' ';
      }

      return StringsKt.padStart(var0, var1, var2);
   }

   @NotNull
   public static final String padStart(@NotNull String $this$padStart, int length, char padChar) {
      Intrinsics.checkNotNullParameter($this$padStart, "<this>");
      return StringsKt.padStart((CharSequence)$this$padStart, length, padChar).toString();
   }

   // $FF: synthetic method
   public static String padStart$default(String var0, int var1, char var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = ' ';
      }

      return StringsKt.padStart(var0, var1, var2);
   }

   @NotNull
   public static final CharSequence padEnd(@NotNull CharSequence $this$padEnd, int length, char padChar) {
      Intrinsics.checkNotNullParameter($this$padEnd, "<this>");
      if (length < 0) {
         throw new IllegalArgumentException("Desired length " + length + " is less than zero.");
      } else if (length <= $this$padEnd.length()) {
         return $this$padEnd.subSequence(0, $this$padEnd.length());
      } else {
         StringBuilder sb = new StringBuilder(length);
         sb.append($this$padEnd);
         int i = 1;
         int var5 = length - $this$padEnd.length();
         if (i <= var5) {
            while(true) {
               sb.append(padChar);
               if (i == var5) {
                  break;
               }

               ++i;
            }
         }

         return (CharSequence)sb;
      }
   }

   // $FF: synthetic method
   public static CharSequence padEnd$default(CharSequence var0, int var1, char var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = ' ';
      }

      return StringsKt.padEnd(var0, var1, var2);
   }

   @NotNull
   public static final String padEnd(@NotNull String $this$padEnd, int length, char padChar) {
      Intrinsics.checkNotNullParameter($this$padEnd, "<this>");
      return StringsKt.padEnd((CharSequence)$this$padEnd, length, padChar).toString();
   }

   // $FF: synthetic method
   public static String padEnd$default(String var0, int var1, char var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = ' ';
      }

      return StringsKt.padEnd(var0, var1, var2);
   }

   @InlineOnly
   private static final boolean isNullOrEmpty(CharSequence $this$isNullOrEmpty) {
      return $this$isNullOrEmpty == null || $this$isNullOrEmpty.length() == 0;
   }

   @InlineOnly
   private static final boolean isEmpty(CharSequence $this$isEmpty) {
      Intrinsics.checkNotNullParameter($this$isEmpty, "<this>");
      return $this$isEmpty.length() == 0;
   }

   @InlineOnly
   private static final boolean isNotEmpty(CharSequence $this$isNotEmpty) {
      Intrinsics.checkNotNullParameter($this$isNotEmpty, "<this>");
      return $this$isNotEmpty.length() > 0;
   }

   public static final boolean isBlank(@NotNull CharSequence $this$isBlank) {
      Intrinsics.checkNotNullParameter($this$isBlank, "<this>");
      CharSequence $this$all$iv = $this$isBlank;
      int $i$f$all = 0;
      int var3 = 0;

      boolean var10000;
      while(true) {
         if (var3 >= $this$all$iv.length()) {
            var10000 = true;
            break;
         }

         char element$iv = $this$all$iv.charAt(var3);
         int var6 = 0;
         if (!CharsKt.isWhitespace(element$iv)) {
            var10000 = false;
            break;
         }

         ++var3;
      }

      return var10000;
   }

   @InlineOnly
   private static final boolean isNotBlank(CharSequence $this$isNotBlank) {
      Intrinsics.checkNotNullParameter($this$isNotBlank, "<this>");
      return !StringsKt.isBlank($this$isNotBlank);
   }

   @InlineOnly
   private static final boolean isNullOrBlank(CharSequence $this$isNullOrBlank) {
      return $this$isNullOrBlank == null || StringsKt.isBlank($this$isNullOrBlank);
   }

   @NotNull
   public static final CharIterator iterator(@NotNull final CharSequence $this$iterator) {
      Intrinsics.checkNotNullParameter($this$iterator, "<this>");
      return new CharIterator($this$iterator) {
         private int index;

         public char nextChar() {
            CharSequence var10000 = $this$iterator;
            int var1 = this.index++;
            return var10000.charAt(var1);
         }

         public boolean hasNext() {
            return this.index < $this$iterator.length();
         }
      };
   }

   @InlineOnly
   private static final String orEmpty(String $this$orEmpty) {
      String var10000 = $this$orEmpty;
      if ($this$orEmpty == null) {
         var10000 = "";
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Object ifEmpty(CharSequence $this$ifEmpty, Function0 defaultValue) {
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      return $this$ifEmpty.length() == 0 ? defaultValue.invoke() : $this$ifEmpty;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Object ifBlank(CharSequence $this$ifBlank, Function0 defaultValue) {
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      return StringsKt.isBlank($this$ifBlank) ? defaultValue.invoke() : $this$ifBlank;
   }

   @NotNull
   public static final IntRange getIndices(@NotNull CharSequence $this$indices) {
      Intrinsics.checkNotNullParameter($this$indices, "<this>");
      return new IntRange(0, $this$indices.length() - 1);
   }

   public static final int getLastIndex(@NotNull CharSequence $this$lastIndex) {
      Intrinsics.checkNotNullParameter($this$lastIndex, "<this>");
      return $this$lastIndex.length() - 1;
   }

   public static final boolean hasSurrogatePairAt(@NotNull CharSequence $this$hasSurrogatePairAt, int index) {
      Intrinsics.checkNotNullParameter($this$hasSurrogatePairAt, "<this>");
      return (0 <= index ? index <= $this$hasSurrogatePairAt.length() - 2 : false) && Character.isHighSurrogate($this$hasSurrogatePairAt.charAt(index)) && Character.isLowSurrogate($this$hasSurrogatePairAt.charAt(index + 1));
   }

   @NotNull
   public static final String substring(@NotNull String $this$substring, @NotNull IntRange range) {
      Intrinsics.checkNotNullParameter($this$substring, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      int var3 = range.getStart();
      int var4 = range.getEndInclusive() + 1;
      String var10000 = $this$substring.substring(var3, var4);
      Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      return var10000;
   }

   @NotNull
   public static final CharSequence subSequence(@NotNull CharSequence $this$subSequence, @NotNull IntRange range) {
      Intrinsics.checkNotNullParameter($this$subSequence, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      return $this$subSequence.subSequence(range.getStart(), range.getEndInclusive() + 1);
   }

   /** @deprecated */
   @Deprecated(
      message = "Use parameters named startIndex and endIndex.",
      replaceWith = @ReplaceWith(
   expression = "subSequence(startIndex = start, endIndex = end)",
   imports = {}
)
   )
   @InlineOnly
   private static final CharSequence subSequence(String $this$subSequence, int start, int end) {
      Intrinsics.checkNotNullParameter($this$subSequence, "<this>");
      return $this$subSequence.subSequence(start, end);
   }

   @InlineOnly
   private static final String substring(CharSequence $this$substring, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$substring, "<this>");
      return $this$substring.subSequence(startIndex, endIndex).toString();
   }

   // $FF: synthetic method
   static String substring$default(CharSequence $this$substring_u24default, int startIndex, int endIndex, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         endIndex = $this$substring_u24default.length();
      }

      Intrinsics.checkNotNullParameter($this$substring_u24default, "<this>");
      return $this$substring_u24default.subSequence(startIndex, endIndex).toString();
   }

   @NotNull
   public static final String substring(@NotNull CharSequence $this$substring, @NotNull IntRange range) {
      Intrinsics.checkNotNullParameter($this$substring, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      return $this$substring.subSequence(range.getStart(), range.getEndInclusive() + 1).toString();
   }

   @NotNull
   public static final String substringBefore(@NotNull String $this$substringBefore, char delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$substringBefore, "<this>");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.indexOf$default((CharSequence)$this$substringBefore, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var5 = 0;
         var10000 = $this$substringBefore.substring(var5, index);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String substringBefore$default(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringBefore(var0, var1, var2);
   }

   @NotNull
   public static final String substringBefore(@NotNull String $this$substringBefore, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$substringBefore, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.indexOf$default((CharSequence)$this$substringBefore, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var5 = 0;
         var10000 = $this$substringBefore.substring(var5, index);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String substringBefore$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringBefore(var0, var1, var2);
   }

   @NotNull
   public static final String substringAfter(@NotNull String $this$substringAfter, char delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$substringAfter, "<this>");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.indexOf$default((CharSequence)$this$substringAfter, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var5 = index + 1;
         int var6 = $this$substringAfter.length();
         var10000 = $this$substringAfter.substring(var5, var6);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String substringAfter$default(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringAfter(var0, var1, var2);
   }

   @NotNull
   public static final String substringAfter(@NotNull String $this$substringAfter, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$substringAfter, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.indexOf$default((CharSequence)$this$substringAfter, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var5 = index + delimiter.length();
         int var6 = $this$substringAfter.length();
         var10000 = $this$substringAfter.substring(var5, var6);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String substringAfter$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringAfter(var0, var1, var2);
   }

   @NotNull
   public static final String substringBeforeLast(@NotNull String $this$substringBeforeLast, char delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$substringBeforeLast, "<this>");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.lastIndexOf$default((CharSequence)$this$substringBeforeLast, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var5 = 0;
         var10000 = $this$substringBeforeLast.substring(var5, index);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String substringBeforeLast$default(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringBeforeLast(var0, var1, var2);
   }

   @NotNull
   public static final String substringBeforeLast(@NotNull String $this$substringBeforeLast, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$substringBeforeLast, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.lastIndexOf$default((CharSequence)$this$substringBeforeLast, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var5 = 0;
         var10000 = $this$substringBeforeLast.substring(var5, index);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String substringBeforeLast$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringBeforeLast(var0, var1, var2);
   }

   @NotNull
   public static final String substringAfterLast(@NotNull String $this$substringAfterLast, char delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$substringAfterLast, "<this>");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.lastIndexOf$default((CharSequence)$this$substringAfterLast, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var5 = index + 1;
         int var6 = $this$substringAfterLast.length();
         var10000 = $this$substringAfterLast.substring(var5, var6);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String substringAfterLast$default(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringAfterLast(var0, var1, var2);
   }

   @NotNull
   public static final String substringAfterLast(@NotNull String $this$substringAfterLast, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$substringAfterLast, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.lastIndexOf$default((CharSequence)$this$substringAfterLast, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var5 = index + delimiter.length();
         int var6 = $this$substringAfterLast.length();
         var10000 = $this$substringAfterLast.substring(var5, var6);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String substringAfterLast$default(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.substringAfterLast(var0, var1, var2);
   }

   @NotNull
   public static final CharSequence replaceRange(@NotNull CharSequence $this$replaceRange, int startIndex, int endIndex, @NotNull CharSequence replacement) {
      Intrinsics.checkNotNullParameter($this$replaceRange, "<this>");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      if (endIndex < startIndex) {
         throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
      } else {
         StringBuilder sb = new StringBuilder();
         Intrinsics.checkNotNullExpressionValue(sb.append($this$replaceRange, 0, startIndex), "append(...)");
         sb.append(replacement);
         Intrinsics.checkNotNullExpressionValue(sb.append($this$replaceRange, endIndex, $this$replaceRange.length()), "append(...)");
         return (CharSequence)sb;
      }
   }

   @InlineOnly
   private static final String replaceRange(String $this$replaceRange, int startIndex, int endIndex, CharSequence replacement) {
      Intrinsics.checkNotNullParameter($this$replaceRange, "<this>");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      return StringsKt.replaceRange((CharSequence)$this$replaceRange, startIndex, endIndex, replacement).toString();
   }

   @NotNull
   public static final CharSequence replaceRange(@NotNull CharSequence $this$replaceRange, @NotNull IntRange range, @NotNull CharSequence replacement) {
      Intrinsics.checkNotNullParameter($this$replaceRange, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      return StringsKt.replaceRange($this$replaceRange, range.getStart(), range.getEndInclusive() + 1, replacement);
   }

   @InlineOnly
   private static final String replaceRange(String $this$replaceRange, IntRange range, CharSequence replacement) {
      Intrinsics.checkNotNullParameter($this$replaceRange, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      return StringsKt.replaceRange((CharSequence)$this$replaceRange, range, replacement).toString();
   }

   @NotNull
   public static final CharSequence removeRange(@NotNull CharSequence $this$removeRange, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$removeRange, "<this>");
      if (endIndex < startIndex) {
         throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
      } else if (endIndex == startIndex) {
         return $this$removeRange.subSequence(0, $this$removeRange.length());
      } else {
         StringBuilder sb = new StringBuilder($this$removeRange.length() - (endIndex - startIndex));
         Intrinsics.checkNotNullExpressionValue(sb.append($this$removeRange, 0, startIndex), "append(...)");
         Intrinsics.checkNotNullExpressionValue(sb.append($this$removeRange, endIndex, $this$removeRange.length()), "append(...)");
         return (CharSequence)sb;
      }
   }

   @InlineOnly
   private static final String removeRange(String $this$removeRange, int startIndex, int endIndex) {
      Intrinsics.checkNotNullParameter($this$removeRange, "<this>");
      return StringsKt.removeRange((CharSequence)$this$removeRange, startIndex, endIndex).toString();
   }

   @NotNull
   public static final CharSequence removeRange(@NotNull CharSequence $this$removeRange, @NotNull IntRange range) {
      Intrinsics.checkNotNullParameter($this$removeRange, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      return StringsKt.removeRange($this$removeRange, range.getStart(), range.getEndInclusive() + 1);
   }

   @InlineOnly
   private static final String removeRange(String $this$removeRange, IntRange range) {
      Intrinsics.checkNotNullParameter($this$removeRange, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      return StringsKt.removeRange((CharSequence)$this$removeRange, range).toString();
   }

   @NotNull
   public static final CharSequence removePrefix(@NotNull CharSequence $this$removePrefix, @NotNull CharSequence prefix) {
      Intrinsics.checkNotNullParameter($this$removePrefix, "<this>");
      Intrinsics.checkNotNullParameter(prefix, "prefix");
      return StringsKt.startsWith$default($this$removePrefix, prefix, false, 2, (Object)null) ? $this$removePrefix.subSequence(prefix.length(), $this$removePrefix.length()) : $this$removePrefix.subSequence(0, $this$removePrefix.length());
   }

   @NotNull
   public static final String removePrefix(@NotNull String $this$removePrefix, @NotNull CharSequence prefix) {
      Intrinsics.checkNotNullParameter($this$removePrefix, "<this>");
      Intrinsics.checkNotNullParameter(prefix, "prefix");
      if (StringsKt.startsWith$default((CharSequence)$this$removePrefix, prefix, false, 2, (Object)null)) {
         int var3 = prefix.length();
         String var10000 = $this$removePrefix.substring(var3);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
         return var10000;
      } else {
         return $this$removePrefix;
      }
   }

   @NotNull
   public static final CharSequence removeSuffix(@NotNull CharSequence $this$removeSuffix, @NotNull CharSequence suffix) {
      Intrinsics.checkNotNullParameter($this$removeSuffix, "<this>");
      Intrinsics.checkNotNullParameter(suffix, "suffix");
      return StringsKt.endsWith$default($this$removeSuffix, suffix, false, 2, (Object)null) ? $this$removeSuffix.subSequence(0, $this$removeSuffix.length() - suffix.length()) : $this$removeSuffix.subSequence(0, $this$removeSuffix.length());
   }

   @NotNull
   public static final String removeSuffix(@NotNull String $this$removeSuffix, @NotNull CharSequence suffix) {
      Intrinsics.checkNotNullParameter($this$removeSuffix, "<this>");
      Intrinsics.checkNotNullParameter(suffix, "suffix");
      if (StringsKt.endsWith$default((CharSequence)$this$removeSuffix, suffix, false, 2, (Object)null)) {
         byte var3 = 0;
         int var4 = $this$removeSuffix.length() - suffix.length();
         String var10000 = $this$removeSuffix.substring(var3, var4);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
         return var10000;
      } else {
         return $this$removeSuffix;
      }
   }

   @NotNull
   public static final CharSequence removeSurrounding(@NotNull CharSequence $this$removeSurrounding, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
      Intrinsics.checkNotNullParameter($this$removeSurrounding, "<this>");
      Intrinsics.checkNotNullParameter(prefix, "prefix");
      Intrinsics.checkNotNullParameter(suffix, "suffix");
      return $this$removeSurrounding.length() >= prefix.length() + suffix.length() && StringsKt.startsWith$default($this$removeSurrounding, prefix, false, 2, (Object)null) && StringsKt.endsWith$default($this$removeSurrounding, suffix, false, 2, (Object)null) ? $this$removeSurrounding.subSequence(prefix.length(), $this$removeSurrounding.length() - suffix.length()) : $this$removeSurrounding.subSequence(0, $this$removeSurrounding.length());
   }

   @NotNull
   public static final String removeSurrounding(@NotNull String $this$removeSurrounding, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
      Intrinsics.checkNotNullParameter($this$removeSurrounding, "<this>");
      Intrinsics.checkNotNullParameter(prefix, "prefix");
      Intrinsics.checkNotNullParameter(suffix, "suffix");
      if ($this$removeSurrounding.length() >= prefix.length() + suffix.length() && StringsKt.startsWith$default((CharSequence)$this$removeSurrounding, prefix, false, 2, (Object)null) && StringsKt.endsWith$default((CharSequence)$this$removeSurrounding, suffix, false, 2, (Object)null)) {
         int var4 = prefix.length();
         int var5 = $this$removeSurrounding.length() - suffix.length();
         String var10000 = $this$removeSurrounding.substring(var4, var5);
         Intrinsics.checkNotNullExpressionValue(var10000, "substring(...)");
         return var10000;
      } else {
         return $this$removeSurrounding;
      }
   }

   @NotNull
   public static final CharSequence removeSurrounding(@NotNull CharSequence $this$removeSurrounding, @NotNull CharSequence delimiter) {
      Intrinsics.checkNotNullParameter($this$removeSurrounding, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      return StringsKt.removeSurrounding($this$removeSurrounding, delimiter, delimiter);
   }

   @NotNull
   public static final String removeSurrounding(@NotNull String $this$removeSurrounding, @NotNull CharSequence delimiter) {
      Intrinsics.checkNotNullParameter($this$removeSurrounding, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      return StringsKt.removeSurrounding($this$removeSurrounding, delimiter, delimiter);
   }

   @NotNull
   public static final String replaceBefore(@NotNull String $this$replaceBefore, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$replaceBefore, "<this>");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.indexOf$default((CharSequence)$this$replaceBefore, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var6 = 0;
         var10000 = StringsKt.replaceRange((CharSequence)$this$replaceBefore, var6, index, (CharSequence)replacement).toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String replaceBefore$default(String var0, char var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceBefore(var0, var1, var2, var3);
   }

   @NotNull
   public static final String replaceBefore(@NotNull String $this$replaceBefore, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$replaceBefore, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.indexOf$default((CharSequence)$this$replaceBefore, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var6 = 0;
         var10000 = StringsKt.replaceRange((CharSequence)$this$replaceBefore, var6, index, (CharSequence)replacement).toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String replaceBefore$default(String var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceBefore(var0, var1, var2, var3);
   }

   @NotNull
   public static final String replaceAfter(@NotNull String $this$replaceAfter, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$replaceAfter, "<this>");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.indexOf$default((CharSequence)$this$replaceAfter, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var6 = index + 1;
         int var7 = $this$replaceAfter.length();
         var10000 = StringsKt.replaceRange((CharSequence)$this$replaceAfter, var6, var7, (CharSequence)replacement).toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String replaceAfter$default(String var0, char var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceAfter(var0, var1, var2, var3);
   }

   @NotNull
   public static final String replaceAfter(@NotNull String $this$replaceAfter, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$replaceAfter, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.indexOf$default((CharSequence)$this$replaceAfter, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var6 = index + delimiter.length();
         int var7 = $this$replaceAfter.length();
         var10000 = StringsKt.replaceRange((CharSequence)$this$replaceAfter, var6, var7, (CharSequence)replacement).toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String replaceAfter$default(String var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceAfter(var0, var1, var2, var3);
   }

   @NotNull
   public static final String replaceAfterLast(@NotNull String $this$replaceAfterLast, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$replaceAfterLast, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.lastIndexOf$default((CharSequence)$this$replaceAfterLast, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var6 = index + delimiter.length();
         int var7 = $this$replaceAfterLast.length();
         var10000 = StringsKt.replaceRange((CharSequence)$this$replaceAfterLast, var6, var7, (CharSequence)replacement).toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String replaceAfterLast$default(String var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceAfterLast(var0, var1, var2, var3);
   }

   @NotNull
   public static final String replaceAfterLast(@NotNull String $this$replaceAfterLast, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$replaceAfterLast, "<this>");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.lastIndexOf$default((CharSequence)$this$replaceAfterLast, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var6 = index + 1;
         int var7 = $this$replaceAfterLast.length();
         var10000 = StringsKt.replaceRange((CharSequence)$this$replaceAfterLast, var6, var7, (CharSequence)replacement).toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String replaceAfterLast$default(String var0, char var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceAfterLast(var0, var1, var2, var3);
   }

   @NotNull
   public static final String replaceBeforeLast(@NotNull String $this$replaceBeforeLast, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$replaceBeforeLast, "<this>");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.lastIndexOf$default((CharSequence)$this$replaceBeforeLast, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var6 = 0;
         var10000 = StringsKt.replaceRange((CharSequence)$this$replaceBeforeLast, var6, index, (CharSequence)replacement).toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String replaceBeforeLast$default(String var0, char var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceBeforeLast(var0, var1, var2, var3);
   }

   @NotNull
   public static final String replaceBeforeLast(@NotNull String $this$replaceBeforeLast, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
      Intrinsics.checkNotNullParameter($this$replaceBeforeLast, "<this>");
      Intrinsics.checkNotNullParameter(delimiter, "delimiter");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
      int index = StringsKt.lastIndexOf$default((CharSequence)$this$replaceBeforeLast, delimiter, 0, false, 6, (Object)null);
      String var10000;
      if (index == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var6 = 0;
         var10000 = StringsKt.replaceRange((CharSequence)$this$replaceBeforeLast, var6, index, (CharSequence)replacement).toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static String replaceBeforeLast$default(String var0, String var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = var0;
      }

      return StringsKt.replaceBeforeLast(var0, var1, var2, var3);
   }

   @InlineOnly
   private static final String replace(CharSequence $this$replace, Regex regex, String replacement) {
      Intrinsics.checkNotNullParameter($this$replace, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      return regex.replace($this$replace, replacement);
   }

   @InlineOnly
   private static final String replace(CharSequence $this$replace, Regex regex, Function1 transform) {
      Intrinsics.checkNotNullParameter($this$replace, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      Intrinsics.checkNotNullParameter(transform, "transform");
      return regex.replace($this$replace, transform);
   }

   @InlineOnly
   private static final String replaceFirst(CharSequence $this$replaceFirst, Regex regex, String replacement) {
      Intrinsics.checkNotNullParameter($this$replaceFirst, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      return regex.replaceFirst($this$replaceFirst, replacement);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "replaceFirstCharWithChar"
   )
   @InlineOnly
   private static final String replaceFirstCharWithChar(String $this$replaceFirstChar, Function1 transform) {
      Intrinsics.checkNotNullParameter($this$replaceFirstChar, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      String var5;
      if (((CharSequence)$this$replaceFirstChar).length() > 0) {
         char var2 = (Character)transform.invoke($this$replaceFirstChar.charAt(0));
         byte var4 = 1;
         var5 = $this$replaceFirstChar.substring(var4);
         Intrinsics.checkNotNullExpressionValue(var5, "substring(...)");
         String var3 = var5;
         var5 = var2 + var3;
      } else {
         var5 = $this$replaceFirstChar;
      }

      return var5;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "replaceFirstCharWithCharSequence"
   )
   @InlineOnly
   private static final String replaceFirstCharWithCharSequence(String $this$replaceFirstChar, Function1 transform) {
      Intrinsics.checkNotNullParameter($this$replaceFirstChar, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      String var4;
      if (((CharSequence)$this$replaceFirstChar).length() > 0) {
         StringBuilder var10000 = (new StringBuilder()).append(transform.invoke($this$replaceFirstChar.charAt(0)));
         byte var3 = 1;
         String var10001 = $this$replaceFirstChar.substring(var3);
         Intrinsics.checkNotNullExpressionValue(var10001, "substring(...)");
         var4 = var10000.append(var10001).toString();
      } else {
         var4 = $this$replaceFirstChar;
      }

      return var4;
   }

   @InlineOnly
   private static final boolean matches(CharSequence $this$matches, Regex regex) {
      Intrinsics.checkNotNullParameter($this$matches, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      return regex.matches($this$matches);
   }

   public static final boolean regionMatchesImpl(@NotNull CharSequence $this$regionMatchesImpl, int thisOffset, @NotNull CharSequence other, int otherOffset, int length, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$regionMatchesImpl, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      if (otherOffset >= 0 && thisOffset >= 0 && thisOffset <= $this$regionMatchesImpl.length() - length && otherOffset <= other.length() - length) {
         for(int index = 0; index < length; ++index) {
            if (!CharsKt.equals($this$regionMatchesImpl.charAt(thisOffset + index), other.charAt(otherOffset + index), ignoreCase)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final boolean startsWith(@NotNull CharSequence $this$startsWith, char char, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$startsWith, "<this>");
      return $this$startsWith.length() > 0 && CharsKt.equals($this$startsWith.charAt(0), char, ignoreCase);
   }

   // $FF: synthetic method
   public static boolean startsWith$default(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.startsWith(var0, var1, var2);
   }

   public static final boolean endsWith(@NotNull CharSequence $this$endsWith, char char, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$endsWith, "<this>");
      return $this$endsWith.length() > 0 && CharsKt.equals($this$endsWith.charAt(StringsKt.getLastIndex($this$endsWith)), char, ignoreCase);
   }

   // $FF: synthetic method
   public static boolean endsWith$default(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.endsWith(var0, var1, var2);
   }

   public static final boolean startsWith(@NotNull CharSequence $this$startsWith, @NotNull CharSequence prefix, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$startsWith, "<this>");
      Intrinsics.checkNotNullParameter(prefix, "prefix");
      return !ignoreCase && $this$startsWith instanceof String && prefix instanceof String ? StringsKt.startsWith$default((String)$this$startsWith, (String)prefix, false, 2, (Object)null) : StringsKt.regionMatchesImpl($this$startsWith, 0, prefix, 0, prefix.length(), ignoreCase);
   }

   // $FF: synthetic method
   public static boolean startsWith$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.startsWith(var0, var1, var2);
   }

   public static final boolean startsWith(@NotNull CharSequence $this$startsWith, @NotNull CharSequence prefix, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$startsWith, "<this>");
      Intrinsics.checkNotNullParameter(prefix, "prefix");
      return !ignoreCase && $this$startsWith instanceof String && prefix instanceof String ? StringsKt.startsWith$default((String)$this$startsWith, (String)prefix, startIndex, false, 4, (Object)null) : StringsKt.regionMatchesImpl($this$startsWith, startIndex, prefix, 0, prefix.length(), ignoreCase);
   }

   // $FF: synthetic method
   public static boolean startsWith$default(CharSequence var0, CharSequence var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.startsWith(var0, var1, var2, var3);
   }

   public static final boolean endsWith(@NotNull CharSequence $this$endsWith, @NotNull CharSequence suffix, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$endsWith, "<this>");
      Intrinsics.checkNotNullParameter(suffix, "suffix");
      return !ignoreCase && $this$endsWith instanceof String && suffix instanceof String ? StringsKt.endsWith$default((String)$this$endsWith, (String)suffix, false, 2, (Object)null) : StringsKt.regionMatchesImpl($this$endsWith, $this$endsWith.length() - suffix.length(), suffix, 0, suffix.length(), ignoreCase);
   }

   // $FF: synthetic method
   public static boolean endsWith$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.endsWith(var0, var1, var2);
   }

   @NotNull
   public static final String commonPrefixWith(@NotNull CharSequence $this$commonPrefixWith, @NotNull CharSequence other, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$commonPrefixWith, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      int shortestLength = Math.min($this$commonPrefixWith.length(), other.length());

      int i;
      for(i = 0; i < shortestLength && CharsKt.equals($this$commonPrefixWith.charAt(i), other.charAt(i), ignoreCase); ++i) {
      }

      if (StringsKt.hasSurrogatePairAt($this$commonPrefixWith, i - 1) || StringsKt.hasSurrogatePairAt(other, i - 1)) {
         --i;
      }

      return $this$commonPrefixWith.subSequence(0, i).toString();
   }

   // $FF: synthetic method
   public static String commonPrefixWith$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.commonPrefixWith(var0, var1, var2);
   }

   @NotNull
   public static final String commonSuffixWith(@NotNull CharSequence $this$commonSuffixWith, @NotNull CharSequence other, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$commonSuffixWith, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      int thisLength = $this$commonSuffixWith.length();
      int otherLength = other.length();
      int shortestLength = Math.min(thisLength, otherLength);

      int i;
      for(i = 0; i < shortestLength && CharsKt.equals($this$commonSuffixWith.charAt(thisLength - i - 1), other.charAt(otherLength - i - 1), ignoreCase); ++i) {
      }

      if (StringsKt.hasSurrogatePairAt($this$commonSuffixWith, thisLength - i - 1) || StringsKt.hasSurrogatePairAt(other, otherLength - i - 1)) {
         --i;
      }

      return $this$commonSuffixWith.subSequence(thisLength - i, thisLength).toString();
   }

   // $FF: synthetic method
   public static String commonSuffixWith$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.commonSuffixWith(var0, var1, var2);
   }

   public static final int indexOfAny(@NotNull CharSequence $this$indexOfAny, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$indexOfAny, "<this>");
      Intrinsics.checkNotNullParameter(chars, "chars");
      if (!ignoreCase && chars.length == 1 && $this$indexOfAny instanceof String) {
         char var14 = ArraysKt.single(chars);
         return ((String)$this$indexOfAny).indexOf(var14, startIndex);
      } else {
         int index = RangesKt.coerceAtLeast(startIndex, 0);
         int var5 = StringsKt.getLastIndex($this$indexOfAny);
         if (index <= var5) {
            while(true) {
               char charAtIndex = $this$indexOfAny.charAt(index);
               char[] $this$any$iv = chars;
               int $i$f$any = 0;
               int var9 = 0;
               int var10 = chars.length;

               boolean var10000;
               while(true) {
                  if (var9 >= var10) {
                     var10000 = false;
                     break;
                  }

                  char element$iv = $this$any$iv[var9];
                  int var13 = 0;
                  if (CharsKt.equals(element$iv, charAtIndex, ignoreCase)) {
                     var10000 = true;
                     break;
                  }

                  ++var9;
               }

               if (var10000) {
                  return index;
               }

               if (index == var5) {
                  break;
               }

               ++index;
            }
         }

         return -1;
      }
   }

   // $FF: synthetic method
   public static int indexOfAny$default(CharSequence var0, char[] var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.indexOfAny(var0, var1, var2, var3);
   }

   public static final int lastIndexOfAny(@NotNull CharSequence $this$lastIndexOfAny, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$lastIndexOfAny, "<this>");
      Intrinsics.checkNotNullParameter(chars, "chars");
      if (!ignoreCase && chars.length == 1 && $this$lastIndexOfAny instanceof String) {
         char var13 = ArraysKt.single(chars);
         return ((String)$this$lastIndexOfAny).lastIndexOf(var13, startIndex);
      } else {
         for(int index = RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($this$lastIndexOfAny)); -1 < index; --index) {
            char charAtIndex = $this$lastIndexOfAny.charAt(index);
            char[] $this$any$iv = chars;
            int $i$f$any = 0;
            int var8 = 0;
            int var9 = chars.length;

            boolean var10000;
            while(true) {
               if (var8 >= var9) {
                  var10000 = false;
                  break;
               }

               char element$iv = $this$any$iv[var8];
               int var12 = 0;
               if (CharsKt.equals(element$iv, charAtIndex, ignoreCase)) {
                  var10000 = true;
                  break;
               }

               ++var8;
            }

            if (var10000) {
               return index;
            }
         }

         return -1;
      }
   }

   // $FF: synthetic method
   public static int lastIndexOfAny$default(CharSequence var0, char[] var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.lastIndexOfAny(var0, var1, var2, var3);
   }

   private static final int indexOf$StringsKt__StringsKt(CharSequence $this$indexOf, CharSequence other, int startIndex, int endIndex, boolean ignoreCase, boolean last) {
      IntProgression indices = !last ? (IntProgression)(new IntRange(RangesKt.coerceAtLeast(startIndex, 0), RangesKt.coerceAtMost(endIndex, $this$indexOf.length()))) : RangesKt.downTo(RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($this$indexOf)), RangesKt.coerceAtLeast(endIndex, 0));
      if ($this$indexOf instanceof String && other instanceof String) {
         int index = indices.getFirst();
         int var11 = indices.getLast();
         int var12 = indices.getStep();
         if (var12 > 0 && index <= var11 || var12 < 0 && var11 <= index) {
            while(true) {
               if (StringsKt.regionMatches((String)other, 0, (String)$this$indexOf, index, other.length(), ignoreCase)) {
                  return index;
               }

               if (index == var11) {
                  break;
               }

               index += var12;
            }
         }
      } else {
         int index = indices.getFirst();
         int var8 = indices.getLast();
         int var9 = indices.getStep();
         if (var9 > 0 && index <= var8 || var9 < 0 && var8 <= index) {
            while(true) {
               if (StringsKt.regionMatchesImpl(other, 0, $this$indexOf, index, other.length(), ignoreCase)) {
                  return index;
               }

               if (index == var8) {
                  break;
               }

               index += var9;
            }
         }
      }

      return -1;
   }

   // $FF: synthetic method
   static int indexOf$StringsKt__StringsKt$default(CharSequence var0, CharSequence var1, int var2, int var3, boolean var4, boolean var5, int var6, Object var7) {
      if ((var6 & 16) != 0) {
         var5 = false;
      }

      return indexOf$StringsKt__StringsKt(var0, var1, var2, var3, var4, var5);
   }

   private static final Pair findAnyOf$StringsKt__StringsKt(CharSequence $this$findAnyOf, Collection strings, int startIndex, boolean ignoreCase, boolean last) {
      if (!ignoreCase && strings.size() == 1) {
         String string = (String)CollectionsKt.single((Iterable)strings);
         int index = !last ? StringsKt.indexOf$default($this$findAnyOf, string, startIndex, false, 4, (Object)null) : StringsKt.lastIndexOf$default($this$findAnyOf, string, startIndex, false, 4, (Object)null);
         return index < 0 ? null : TuplesKt.to(index, string);
      } else {
         IntProgression indices = !last ? (IntProgression)(new IntRange(RangesKt.coerceAtLeast(startIndex, 0), $this$findAnyOf.length())) : RangesKt.downTo(RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($this$findAnyOf)), 0);
         if ($this$findAnyOf instanceof String) {
            int index = indices.getFirst();
            int var7 = indices.getLast();
            int var8 = indices.getStep();
            if (var8 > 0 && index <= var7 || var8 < 0 && var7 <= index) {
               while(true) {
                  Iterable $this$firstOrNull$iv = (Iterable)strings;
                  int $i$f$firstOrNull = 0;
                  Iterator var12 = $this$firstOrNull$iv.iterator();

                  Object var10000;
                  while(true) {
                     if (!var12.hasNext()) {
                        var10000 = null;
                        break;
                     }

                     Object element$iv = var12.next();
                     String it = (String)element$iv;
                     int var15 = 0;
                     if (StringsKt.regionMatches(it, 0, (String)$this$findAnyOf, index, it.length(), ignoreCase)) {
                        var10000 = element$iv;
                        break;
                     }
                  }

                  String matchingString = (String)var10000;
                  if (matchingString != null) {
                     return TuplesKt.to(index, matchingString);
                  }

                  if (index == var7) {
                     break;
                  }

                  index += var8;
               }
            }
         } else {
            int index = indices.getFirst();
            int var19 = indices.getLast();
            int var20 = indices.getStep();
            if (var20 > 0 && index <= var19 || var20 < 0 && var19 <= index) {
               while(true) {
                  Iterable $this$firstOrNull$iv = (Iterable)strings;
                  int $i$f$firstOrNull = 0;
                  Iterator var24 = $this$firstOrNull$iv.iterator();

                  Object var28;
                  while(true) {
                     if (!var24.hasNext()) {
                        var28 = null;
                        break;
                     }

                     Object element$iv = var24.next();
                     String it = (String)element$iv;
                     int var27 = 0;
                     if (StringsKt.regionMatchesImpl((CharSequence)it, 0, $this$findAnyOf, index, it.length(), ignoreCase)) {
                        var28 = element$iv;
                        break;
                     }
                  }

                  String matchingString = (String)var28;
                  if (matchingString != null) {
                     return TuplesKt.to(index, matchingString);
                  }

                  if (index == var19) {
                     break;
                  }

                  index += var20;
               }
            }
         }

         return null;
      }
   }

   @Nullable
   public static final Pair findAnyOf(@NotNull CharSequence $this$findAnyOf, @NotNull Collection strings, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$findAnyOf, "<this>");
      Intrinsics.checkNotNullParameter(strings, "strings");
      return findAnyOf$StringsKt__StringsKt($this$findAnyOf, strings, startIndex, ignoreCase, false);
   }

   // $FF: synthetic method
   public static Pair findAnyOf$default(CharSequence var0, Collection var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.findAnyOf(var0, var1, var2, var3);
   }

   @Nullable
   public static final Pair findLastAnyOf(@NotNull CharSequence $this$findLastAnyOf, @NotNull Collection strings, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$findLastAnyOf, "<this>");
      Intrinsics.checkNotNullParameter(strings, "strings");
      return findAnyOf$StringsKt__StringsKt($this$findLastAnyOf, strings, startIndex, ignoreCase, true);
   }

   // $FF: synthetic method
   public static Pair findLastAnyOf$default(CharSequence var0, Collection var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.findLastAnyOf(var0, var1, var2, var3);
   }

   public static final int indexOfAny(@NotNull CharSequence $this$indexOfAny, @NotNull Collection strings, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$indexOfAny, "<this>");
      Intrinsics.checkNotNullParameter(strings, "strings");
      Pair var10000 = findAnyOf$StringsKt__StringsKt($this$indexOfAny, strings, startIndex, ignoreCase, false);
      return var10000 != null ? ((Number)var10000.getFirst()).intValue() : -1;
   }

   // $FF: synthetic method
   public static int indexOfAny$default(CharSequence var0, Collection var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.indexOfAny(var0, var1, var2, var3);
   }

   public static final int lastIndexOfAny(@NotNull CharSequence $this$lastIndexOfAny, @NotNull Collection strings, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$lastIndexOfAny, "<this>");
      Intrinsics.checkNotNullParameter(strings, "strings");
      Pair var10000 = findAnyOf$StringsKt__StringsKt($this$lastIndexOfAny, strings, startIndex, ignoreCase, true);
      return var10000 != null ? ((Number)var10000.getFirst()).intValue() : -1;
   }

   // $FF: synthetic method
   public static int lastIndexOfAny$default(CharSequence var0, Collection var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.lastIndexOfAny(var0, var1, var2, var3);
   }

   public static final int indexOf(@NotNull CharSequence $this$indexOf, char char, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$indexOf, "<this>");
      int var10000;
      if (!ignoreCase && $this$indexOf instanceof String) {
         var10000 = ((String)$this$indexOf).indexOf(char, startIndex);
      } else {
         char[] var4 = new char[]{char};
         var10000 = StringsKt.indexOfAny($this$indexOf, var4, startIndex, ignoreCase);
      }

      return var10000;
   }

   // $FF: synthetic method
   public static int indexOf$default(CharSequence var0, char var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.indexOf(var0, var1, var2, var3);
   }

   public static final int indexOf(@NotNull CharSequence $this$indexOf, @NotNull String string, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$indexOf, "<this>");
      Intrinsics.checkNotNullParameter(string, "string");
      return !ignoreCase && $this$indexOf instanceof String ? ((String)$this$indexOf).indexOf(string, startIndex) : indexOf$StringsKt__StringsKt$default($this$indexOf, (CharSequence)string, startIndex, $this$indexOf.length(), ignoreCase, false, 16, (Object)null);
   }

   // $FF: synthetic method
   public static int indexOf$default(CharSequence var0, String var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.indexOf(var0, var1, var2, var3);
   }

   public static final int lastIndexOf(@NotNull CharSequence $this$lastIndexOf, char char, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$lastIndexOf, "<this>");
      int var10000;
      if (!ignoreCase && $this$lastIndexOf instanceof String) {
         var10000 = ((String)$this$lastIndexOf).lastIndexOf(char, startIndex);
      } else {
         char[] var4 = new char[]{char};
         var10000 = StringsKt.lastIndexOfAny($this$lastIndexOf, var4, startIndex, ignoreCase);
      }

      return var10000;
   }

   // $FF: synthetic method
   public static int lastIndexOf$default(CharSequence var0, char var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.lastIndexOf(var0, var1, var2, var3);
   }

   public static final int lastIndexOf(@NotNull CharSequence $this$lastIndexOf, @NotNull String string, int startIndex, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$lastIndexOf, "<this>");
      Intrinsics.checkNotNullParameter(string, "string");
      return !ignoreCase && $this$lastIndexOf instanceof String ? ((String)$this$lastIndexOf).lastIndexOf(string, startIndex) : indexOf$StringsKt__StringsKt($this$lastIndexOf, (CharSequence)string, startIndex, 0, ignoreCase, true);
   }

   // $FF: synthetic method
   public static int lastIndexOf$default(CharSequence var0, String var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getLastIndex(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.lastIndexOf(var0, var1, var2, var3);
   }

   public static final boolean contains(@NotNull CharSequence $this$contains, @NotNull CharSequence other, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      return other instanceof String ? StringsKt.indexOf$default($this$contains, (String)other, 0, ignoreCase, 2, (Object)null) >= 0 : indexOf$StringsKt__StringsKt$default($this$contains, other, 0, $this$contains.length(), ignoreCase, false, 16, (Object)null) >= 0;
   }

   // $FF: synthetic method
   public static boolean contains$default(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.contains(var0, var1, var2);
   }

   public static final boolean contains(@NotNull CharSequence $this$contains, char char, boolean ignoreCase) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return StringsKt.indexOf$default($this$contains, char, 0, ignoreCase, 2, (Object)null) >= 0;
   }

   // $FF: synthetic method
   public static boolean contains$default(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.contains(var0, var1, var2);
   }

   @InlineOnly
   private static final boolean contains(CharSequence $this$contains, Regex regex) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      return regex.containsMatchIn($this$contains);
   }

   private static final Sequence rangesDelimitedBy$StringsKt__StringsKt(CharSequence $this$rangesDelimitedBy, final char[] delimiters, int startIndex, final boolean ignoreCase, int limit) {
      StringsKt.requireNonNegativeLimit(limit);
      return new DelimitedRangesSequence($this$rangesDelimitedBy, startIndex, limit, new Function2(delimiters, ignoreCase) {
         public final Pair invoke(CharSequence $this$$receiver, int currentIndex) {
            Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
            int it = StringsKt.indexOfAny($this$$receiver, delimiters, currentIndex, ignoreCase);
            int var5 = 0;
            return it < 0 ? null : TuplesKt.to(it, 1);
         }
      });
   }

   // $FF: synthetic method
   static Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence var0, char[] var1, int var2, boolean var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if ((var5 & 8) != 0) {
         var4 = 0;
      }

      return rangesDelimitedBy$StringsKt__StringsKt(var0, var1, var2, var3, var4);
   }

   private static final Sequence rangesDelimitedBy$StringsKt__StringsKt(CharSequence $this$rangesDelimitedBy, String[] delimiters, int startIndex, final boolean ignoreCase, int limit) {
      StringsKt.requireNonNegativeLimit(limit);
      final List delimitersList = ArraysKt.asList(delimiters);
      return new DelimitedRangesSequence($this$rangesDelimitedBy, startIndex, limit, new Function2(delimitersList, ignoreCase) {
         public final Pair invoke(CharSequence $this$$receiver, int currentIndex) {
            Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
            Pair var10000 = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt($this$$receiver, (Collection)delimitersList, currentIndex, ignoreCase, false);
            if (var10000 != null) {
               Pair it = var10000;
               int var5 = 0;
               var10000 = TuplesKt.to(it.getFirst(), ((String)it.getSecond()).length());
            } else {
               var10000 = null;
            }

            return var10000;
         }
      });
   }

   // $FF: synthetic method
   static Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence var0, String[] var1, int var2, boolean var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if ((var5 & 8) != 0) {
         var4 = 0;
      }

      return rangesDelimitedBy$StringsKt__StringsKt(var0, var1, var2, var3, var4);
   }

   public static final void requireNonNegativeLimit(int limit) {
      boolean var1 = limit >= 0;
      if (!var1) {
         int var2 = 0;
         String var3 = "Limit must be non-negative, but was " + limit;
         throw new IllegalArgumentException(var3.toString());
      }
   }

   @NotNull
   public static final Sequence splitToSequence(@NotNull final CharSequence $this$splitToSequence, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
      Intrinsics.checkNotNullParameter($this$splitToSequence, "<this>");
      Intrinsics.checkNotNullParameter(delimiters, "delimiters");
      return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default($this$splitToSequence, (String[])delimiters, 0, ignoreCase, limit, 2, (Object)null), new Function1($this$splitToSequence) {
         public final String invoke(IntRange it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return StringsKt.substring($this$splitToSequence, it);
         }
      });
   }

   // $FF: synthetic method
   public static Sequence splitToSequence$default(CharSequence var0, String[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.splitToSequence(var0, var1, var2, var3);
   }

   @NotNull
   public static final List split(@NotNull CharSequence $this$split, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
      Intrinsics.checkNotNullParameter($this$split, "<this>");
      Intrinsics.checkNotNullParameter(delimiters, "delimiters");
      if (delimiters.length == 1) {
         String delimiter = delimiters[0];
         if (((CharSequence)delimiter).length() != 0) {
            return split$StringsKt__StringsKt($this$split, delimiter, ignoreCase, limit);
         }
      }

      Iterable $this$map$iv = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default($this$split, (String[])delimiters, 0, ignoreCase, limit, 2, (Object)null));
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         IntRange it = (IntRange)item$iv$iv;
         int var12 = 0;
         destination$iv$iv.add(StringsKt.substring($this$split, it));
      }

      return (List)destination$iv$iv;
   }

   // $FF: synthetic method
   public static List split$default(CharSequence var0, String[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.split(var0, var1, var2, var3);
   }

   @NotNull
   public static final Sequence splitToSequence(@NotNull final CharSequence $this$splitToSequence, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
      Intrinsics.checkNotNullParameter($this$splitToSequence, "<this>");
      Intrinsics.checkNotNullParameter(delimiters, "delimiters");
      return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default($this$splitToSequence, (char[])delimiters, 0, ignoreCase, limit, 2, (Object)null), new Function1($this$splitToSequence) {
         public final String invoke(IntRange it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return StringsKt.substring($this$splitToSequence, it);
         }
      });
   }

   // $FF: synthetic method
   public static Sequence splitToSequence$default(CharSequence var0, char[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.splitToSequence(var0, var1, var2, var3);
   }

   @NotNull
   public static final List split(@NotNull CharSequence $this$split, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
      Intrinsics.checkNotNullParameter($this$split, "<this>");
      Intrinsics.checkNotNullParameter(delimiters, "delimiters");
      if (delimiters.length == 1) {
         return split$StringsKt__StringsKt($this$split, String.valueOf(delimiters[0]), ignoreCase, limit);
      } else {
         Iterable $this$map$iv = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default($this$split, (char[])delimiters, 0, ignoreCase, limit, 2, (Object)null));
         int $i$f$map = 0;
         Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
         int $i$f$mapTo = 0;

         for(Object item$iv$iv : $this$map$iv) {
            IntRange it = (IntRange)item$iv$iv;
            int var12 = 0;
            destination$iv$iv.add(StringsKt.substring($this$split, it));
         }

         return (List)destination$iv$iv;
      }
   }

   // $FF: synthetic method
   public static List split$default(CharSequence var0, char[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.split(var0, var1, var2, var3);
   }

   private static final List split$StringsKt__StringsKt(CharSequence $this$split, String delimiter, boolean ignoreCase, int limit) {
      StringsKt.requireNonNegativeLimit(limit);
      int currentOffset = 0;
      int nextIndex = StringsKt.indexOf($this$split, delimiter, currentOffset, ignoreCase);
      if (nextIndex != -1 && limit != 1) {
         boolean isLimited = limit > 0;
         ArrayList result = new ArrayList(isLimited ? RangesKt.coerceAtMost(limit, 10) : 10);

         do {
            result.add($this$split.subSequence(currentOffset, nextIndex).toString());
            currentOffset = nextIndex + delimiter.length();
            if (isLimited && result.size() == limit - 1) {
               break;
            }

            nextIndex = StringsKt.indexOf($this$split, delimiter, currentOffset, ignoreCase);
         } while(nextIndex != -1);

         result.add($this$split.subSequence(currentOffset, $this$split.length()).toString());
         return (List)result;
      } else {
         return CollectionsKt.listOf($this$split.toString());
      }
   }

   @InlineOnly
   private static final List split(CharSequence $this$split, Regex regex, int limit) {
      Intrinsics.checkNotNullParameter($this$split, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      return regex.split($this$split, limit);
   }

   // $FF: synthetic method
   static List split$default(CharSequence $this$split_u24default, Regex regex, int limit, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         limit = 0;
      }

      Intrinsics.checkNotNullParameter($this$split_u24default, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      return regex.split($this$split_u24default, limit);
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final Sequence splitToSequence(CharSequence $this$splitToSequence, Regex regex, int limit) {
      Intrinsics.checkNotNullParameter($this$splitToSequence, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      return regex.splitToSequence($this$splitToSequence, limit);
   }

   // $FF: synthetic method
   static Sequence splitToSequence$default(CharSequence $this$splitToSequence_u24default, Regex regex, int limit, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         limit = 0;
      }

      Intrinsics.checkNotNullParameter($this$splitToSequence_u24default, "<this>");
      Intrinsics.checkNotNullParameter(regex, "regex");
      return regex.splitToSequence($this$splitToSequence_u24default, limit);
   }

   @NotNull
   public static final Sequence lineSequence(@NotNull CharSequence $this$lineSequence) {
      Intrinsics.checkNotNullParameter($this$lineSequence, "<this>");
      String[] var1 = new String[]{"\r\n", "\n", "\r"};
      return StringsKt.splitToSequence$default($this$lineSequence, var1, false, 0, 6, (Object)null);
   }

   @NotNull
   public static final List lines(@NotNull CharSequence $this$lines) {
      Intrinsics.checkNotNullParameter($this$lines, "<this>");
      return SequencesKt.toList(StringsKt.lineSequence($this$lines));
   }

   public static final boolean contentEqualsIgnoreCaseImpl(@Nullable CharSequence $this$contentEqualsIgnoreCaseImpl, @Nullable CharSequence other) {
      if ($this$contentEqualsIgnoreCaseImpl instanceof String && other instanceof String) {
         return StringsKt.equals((String)$this$contentEqualsIgnoreCaseImpl, (String)other, true);
      } else if ($this$contentEqualsIgnoreCaseImpl == other) {
         return true;
      } else if ($this$contentEqualsIgnoreCaseImpl != null && other != null && $this$contentEqualsIgnoreCaseImpl.length() == other.length()) {
         int i = 0;

         for(int var3 = $this$contentEqualsIgnoreCaseImpl.length(); i < var3; ++i) {
            if (!CharsKt.equals($this$contentEqualsIgnoreCaseImpl.charAt(i), other.charAt(i), true)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final boolean contentEqualsImpl(@Nullable CharSequence $this$contentEqualsImpl, @Nullable CharSequence other) {
      if ($this$contentEqualsImpl instanceof String && other instanceof String) {
         return Intrinsics.areEqual((Object)$this$contentEqualsImpl, (Object)other);
      } else if ($this$contentEqualsImpl == other) {
         return true;
      } else if ($this$contentEqualsImpl != null && other != null && $this$contentEqualsImpl.length() == other.length()) {
         int i = 0;

         for(int var3 = $this$contentEqualsImpl.length(); i < var3; ++i) {
            if ($this$contentEqualsImpl.charAt(i) != other.charAt(i)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   public static final boolean toBooleanStrict(@NotNull String $this$toBooleanStrict) {
      Intrinsics.checkNotNullParameter($this$toBooleanStrict, "<this>");
      boolean var10000;
      if (Intrinsics.areEqual((Object)$this$toBooleanStrict, (Object)"true")) {
         var10000 = true;
      } else {
         if (!Intrinsics.areEqual((Object)$this$toBooleanStrict, (Object)"false")) {
            throw new IllegalArgumentException("The string doesn't represent a boolean value: " + $this$toBooleanStrict);
         }

         var10000 = false;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.5"
   )
   @Nullable
   public static final Boolean toBooleanStrictOrNull(@NotNull String $this$toBooleanStrictOrNull) {
      Intrinsics.checkNotNullParameter($this$toBooleanStrictOrNull, "<this>");
      return Intrinsics.areEqual((Object)$this$toBooleanStrictOrNull, (Object)"true") ? true : (Intrinsics.areEqual((Object)$this$toBooleanStrictOrNull, (Object)"false") ? false : null);
   }

   public StringsKt__StringsKt() {
   }
}
