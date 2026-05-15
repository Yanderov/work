package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0086\u0001\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000f\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aQ\u0010\u0007\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a+\u0010\t\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\t\u0010\n\u001aQ\u0010\t\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b\t\u0010\b\u001a@\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u000b\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0087\b¢\u0006\u0004\b\f\u0010\r\u001a=\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u000e\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a.\u0010\u0012\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0087\b¢\u0006\u0004\b\u0012\u0010\u0013\u001aQ\u0010\u0012\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0014\u001a]\u0010\u0018\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\b\b\u0002\u0010\u0016*\u00020\u0015*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022 \u0010\u0017\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00018\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a_\u0010\u001a\u001a\u0004\u0018\u00018\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\b\b\u0002\u0010\u0016*\u00020\u0015*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022 \u0010\u0017\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00018\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0019\u001ac\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00020\u001b\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022$\u0010\u0017\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000b0\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001ac\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00020\u001b\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022$\u0010\u0017\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000e0\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001d\u001aw\u0010\"\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016\"\u0010\b\u0003\u0010 *\n\u0012\u0006\b\u0000\u0012\u00028\u00020\u001f*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010!\u001a\u00028\u00032$\u0010\u0017\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000b0\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001aw\u0010\"\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016\"\u0010\b\u0003\u0010 *\n\u0012\u0006\b\u0000\u0012\u00028\u00020\u001f*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010!\u001a\u00028\u00032$\u0010\u0017\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u000e0\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b$\u0010#\u001aQ\u0010'\u001a\u00020%\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010&\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00020%0\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a]\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00020\u001b\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010\u0017\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001d\u001ac\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00020\u001b\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\b\b\u0002\u0010\u0016*\u00020\u0015*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022 \u0010\u0017\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00018\u00020\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u001d\u001aw\u0010+\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\b\b\u0002\u0010\u0016*\u00020\u0015\"\u0010\b\u0003\u0010 *\n\u0012\u0006\b\u0000\u0012\u00028\u00020\u001f*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010!\u001a\u00028\u00032 \u0010\u0017\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00018\u00020\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b+\u0010#\u001aq\u0010,\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016\"\u0010\b\u0003\u0010 *\n\u0012\u0006\b\u0000\u0012\u00028\u00020\u001f*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010!\u001a\u00028\u00032\u001e\u0010\u0017\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\b,\u0010#\u001am\u00101\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00020-*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b/\u00100\u001ao\u00102\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00020-*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b2\u00100\u001aa\u00103\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00020-*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b3\u00104\u001aQ\u00103\u001a\u000205\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u0002050\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b3\u00106\u001aQ\u00103\u001a\u000207\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u0002070\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b3\u00108\u001ac\u00109\u001a\u0004\u0018\u00018\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00020-*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u00104\u001aS\u00109\u001a\u0004\u0018\u000105\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u0002050\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001aS\u00109\u001a\u0004\u0018\u000107\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u0002070\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010;\u001as\u0010?\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001a\u0010>\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00020<j\n\u0012\u0006\b\u0000\u0012\u00028\u0002`=2\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001au\u0010A\u001a\u0004\u0018\u00018\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001a\u0010>\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00020<j\n\u0012\u0006\b\u0000\u0012\u00028\u0002`=2\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bA\u0010@\u001an\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000222\u0010>\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040<j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004`=H\u0087\b¢\u0006\u0004\bB\u0010C\u001ap\u0010E\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000222\u0010>\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040<j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004`=H\u0087\b¢\u0006\u0004\bE\u0010C\u001am\u0010G\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00020-*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bF\u00100\u001ao\u0010H\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00020-*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bH\u00100\u001aa\u0010I\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00020-*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bI\u00104\u001aQ\u0010I\u001a\u000205\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u0002050\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bI\u00106\u001aQ\u0010I\u001a\u000207\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u0002070\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bI\u00108\u001ac\u0010J\u001a\u0004\u0018\u00018\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u000e\b\u0002\u0010\u0016*\b\u0012\u0004\u0012\u00028\u00020-*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bJ\u00104\u001aS\u0010J\u001a\u0004\u0018\u000105\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u0002050\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bJ\u0010:\u001aS\u0010J\u001a\u0004\u0018\u000107\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u0002070\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bJ\u0010;\u001as\u0010K\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001a\u0010>\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00020<j\n\u0012\u0006\b\u0000\u0012\u00028\u0002`=2\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bK\u0010@\u001au\u0010L\u001a\u0004\u0018\u00018\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0016*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001a\u0010>\u001a\u0016\u0012\u0006\b\u0000\u0012\u00028\u00020<j\n\u0012\u0006\b\u0000\u0012\u00028\u0002`=2\u001e\u0010.\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00028\u00020\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bL\u0010@\u001an\u0010N\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000222\u0010>\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040<j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004`=H\u0087\b¢\u0006\u0004\bM\u0010C\u001ap\u0010O\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000222\u0010>\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040<j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004`=H\u0087\b¢\u0006\u0004\bO\u0010C\u001a+\u0010P\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\bP\u0010\n\u001aQ\u0010P\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u001e\u0010\u0006\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0086\bø\u0001\u0000¢\u0006\u0004\bP\u0010\b\u001a[\u0010R\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0016\b\u0002\u0010Q*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002*\u00028\u00022\u001e\u0010&\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00020%0\u0003H\u0087\bø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001ap\u0010X\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0016\b\u0002\u0010Q*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002*\u00028\u000223\u0010&\u001a/\u0012\u0013\u0012\u00110\u0011¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0004\u0012\u00020%0TH\u0087\bø\u0001\u0000¢\u0006\u0004\bX\u0010Y\u001a=\u0010[\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010Z0\u001b\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b[\u0010\\\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006]"},
   d2 = {"K", "V", "", "Lkotlin/Function1;", "", "", "predicate", "all", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Z", "any", "(Ljava/util/Map;)Z", "", "asIterable", "(Ljava/util/Map;)Ljava/lang/Iterable;", "Lkotlin/sequences/Sequence;", "asSequence", "(Ljava/util/Map;)Lkotlin/sequences/Sequence;", "", "count", "(Ljava/util/Map;)I", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)I", "", "R", "transform", "firstNotNullOf", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstNotNullOfOrNull", "", "flatMap", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "flatMapSequence", "", "C", "destination", "flatMapTo", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "flatMapSequenceTo", "", "action", "forEach", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "map", "mapNotNull", "mapNotNullTo", "mapTo", "", "selector", "maxByOrThrow", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map$Entry;", "maxBy", "maxByOrNull", "maxOf", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)D", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)F", "maxOfOrNull", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Double;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Float;", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "maxOfWith", "(Ljava/util/Map;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxOfWithOrNull", "maxWithOrThrow", "(Ljava/util/Map;Ljava/util/Comparator;)Ljava/util/Map$Entry;", "maxWith", "maxWithOrNull", "minByOrThrow", "minBy", "minByOrNull", "minOf", "minOfOrNull", "minOfWith", "minOfWithOrNull", "minWithOrThrow", "minWith", "minWithOrNull", "none", "M", "onEach", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "index", "onEachIndexed", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "Lkotlin/Pair;", "toList", "(Ljava/util/Map;)Ljava/util/List;", "kotlin-stdlib"},
   xs = "kotlin/collections/MapsKt"
)
@SourceDebugExtension({"SMAP\n_Maps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,595:1\n97#1,5:596\n112#1,5:601\n153#1,3:606\n144#1:609\n216#1:610\n217#1:612\n145#1:613\n216#1:614\n217#1:616\n1#2:611\n1#2:615\n1948#3,14:617\n1971#3,14:631\n2318#3,14:645\n2341#3,14:659\n1872#3,3:673\n*S KotlinDebug\n*F\n+ 1 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n77#1:596,5\n90#1:601,5\n126#1:606,3\n136#1:609\n136#1:610\n136#1:612\n136#1:613\n144#1:614\n144#1:616\n136#1:611\n231#1:617,14\n242#1:631,14\n391#1:645,14\n402#1:659,14\n575#1:673,3\n*E\n"})
class MapsKt___MapsKt extends MapsKt___MapsJvmKt {
   @SinceKotlin(
      version = "1.5"
   )
   @InlineOnly
   private static final Object firstNotNullOf(Map $this$firstNotNullOf, Function1 transform) {
      Intrinsics.checkNotNullParameter($this$firstNotNullOf, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      Iterator var2 = $this$firstNotNullOf.entrySet().iterator();

      Object var10000;
      do {
         if (!var2.hasNext()) {
            var10000 = null;
            break;
         }

         var10000 = transform.invoke((Map.Entry)var2.next());
      } while(var10000 == null);

      if (var10000 == null) {
         throw new NoSuchElementException("No element of the map was transformed to a non-null value.");
      } else {
         return var10000;
      }
   }

   @SinceKotlin(
      version = "1.5"
   )
   @InlineOnly
   private static final Object firstNotNullOfOrNull(Map $this$firstNotNullOfOrNull, Function1 transform) {
      Intrinsics.checkNotNullParameter($this$firstNotNullOfOrNull, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");

      for(Map.Entry element : $this$firstNotNullOfOrNull.entrySet()) {
         Object result = transform.invoke(element);
         if (result != null) {
            return result;
         }
      }

      return null;
   }

   @NotNull
   public static final List toList(@NotNull Map $this$toList) {
      Intrinsics.checkNotNullParameter($this$toList, "<this>");
      if ($this$toList.size() == 0) {
         return CollectionsKt.emptyList();
      } else {
         Iterator iterator = $this$toList.entrySet().iterator();
         if (!iterator.hasNext()) {
            return CollectionsKt.emptyList();
         } else {
            Map.Entry first = (Map.Entry)iterator.next();
            if (!iterator.hasNext()) {
               return CollectionsKt.listOf(new Pair(first.getKey(), first.getValue()));
            } else {
               ArrayList result = new ArrayList($this$toList.size());
               result.add(new Pair(first.getKey(), first.getValue()));

               do {
                  Map.Entry var4 = (Map.Entry)iterator.next();
                  result.add(new Pair(var4.getKey(), var4.getValue()));
               } while(iterator.hasNext());

               return (List)result;
            }
         }
      }
   }

   @NotNull
   public static final List flatMap(@NotNull Map $this$flatMap, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$flatMap, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$flatMap = 0;
      Collection destination$iv = (Collection)(new ArrayList());
      int $i$f$flatMapTo = 0;

      for(Map.Entry element$iv : $this$flatMap.entrySet()) {
         Iterable list$iv = (Iterable)transform.invoke(element$iv);
         CollectionsKt.addAll(destination$iv, list$iv);
      }

      return (List)destination$iv;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "flatMapSequence"
   )
   @NotNull
   public static final List flatMapSequence(@NotNull Map $this$flatMap, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$flatMap, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$flatMapSequence = 0;
      Collection destination$iv = (Collection)(new ArrayList());
      int $i$f$flatMapSequenceTo = 0;

      for(Map.Entry element$iv : $this$flatMap.entrySet()) {
         Sequence list$iv = (Sequence)transform.invoke(element$iv);
         CollectionsKt.addAll(destination$iv, list$iv);
      }

      return (List)destination$iv;
   }

   @NotNull
   public static final Collection flatMapTo(@NotNull Map $this$flatMapTo, @NotNull Collection destination, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$flatMapTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$flatMapTo = 0;

      for(Map.Entry element : $this$flatMapTo.entrySet()) {
         Iterable list = (Iterable)transform.invoke(element);
         CollectionsKt.addAll(destination, list);
      }

      return destination;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @JvmName(
      name = "flatMapSequenceTo"
   )
   @NotNull
   public static final Collection flatMapSequenceTo(@NotNull Map $this$flatMapTo, @NotNull Collection destination, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$flatMapTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$flatMapSequenceTo = 0;

      for(Map.Entry element : $this$flatMapTo.entrySet()) {
         Sequence list = (Sequence)transform.invoke(element);
         CollectionsKt.addAll(destination, list);
      }

      return destination;
   }

   @NotNull
   public static final List map(@NotNull Map $this$map, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$map, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$map = 0;
      Collection destination$iv = (Collection)(new ArrayList($this$map.size()));
      int $i$f$mapTo = 0;

      for(Map.Entry item$iv : $this$map.entrySet()) {
         destination$iv.add(transform.invoke(item$iv));
      }

      return (List)destination$iv;
   }

   @NotNull
   public static final List mapNotNull(@NotNull Map $this$mapNotNull, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$mapNotNull, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$mapNotNull = 0;
      Collection destination$iv = (Collection)(new ArrayList());
      int $i$f$mapNotNullTo = 0;
      int $i$f$forEach = 0;

      for(Map.Entry element$iv$iv : $this$mapNotNull.entrySet()) {
         int var11 = 0;
         Object var10000 = transform.invoke(element$iv$iv);
         if (var10000 != null) {
            Object it$iv = var10000;
            int var14 = 0;
            destination$iv.add(it$iv);
         }
      }

      return (List)destination$iv;
   }

   @NotNull
   public static final Collection mapNotNullTo(@NotNull Map $this$mapNotNullTo, @NotNull Collection destination, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$mapNotNullTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$mapNotNullTo = 0;
      int $i$f$forEach = 0;

      for(Map.Entry element$iv : $this$mapNotNullTo.entrySet()) {
         int var9 = 0;
         Object var10000 = transform.invoke(element$iv);
         if (var10000 != null) {
            Object it = var10000;
            int var12 = 0;
            destination.add(it);
         }
      }

      return destination;
   }

   @NotNull
   public static final Collection mapTo(@NotNull Map $this$mapTo, @NotNull Collection destination, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$mapTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$mapTo = 0;

      for(Map.Entry item : $this$mapTo.entrySet()) {
         destination.add(transform.invoke(item));
      }

      return destination;
   }

   public static final boolean all(@NotNull Map $this$all, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$all, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$all = 0;
      if ($this$all.isEmpty()) {
         return true;
      } else {
         for(Map.Entry element : $this$all.entrySet()) {
            if (!(Boolean)predicate.invoke(element)) {
               return false;
            }
         }

         return true;
      }
   }

   public static final boolean any(@NotNull Map $this$any) {
      Intrinsics.checkNotNullParameter($this$any, "<this>");
      return !$this$any.isEmpty();
   }

   public static final boolean any(@NotNull Map $this$any, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$any, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$any = 0;
      if ($this$any.isEmpty()) {
         return false;
      } else {
         for(Map.Entry element : $this$any.entrySet()) {
            if ((Boolean)predicate.invoke(element)) {
               return true;
            }
         }

         return false;
      }
   }

   @InlineOnly
   private static final int count(Map $this$count) {
      Intrinsics.checkNotNullParameter($this$count, "<this>");
      return $this$count.size();
   }

   public static final int count(@NotNull Map $this$count, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$count, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$count = 0;
      if ($this$count.isEmpty()) {
         return 0;
      } else {
         int count = 0;

         for(Map.Entry element : $this$count.entrySet()) {
            if ((Boolean)predicate.invoke(element)) {
               ++count;
            }
         }

         return count;
      }
   }

   @HidesMembers
   public static final void forEach(@NotNull Map $this$forEach, @NotNull Function1 action) {
      Intrinsics.checkNotNullParameter($this$forEach, "<this>");
      Intrinsics.checkNotNullParameter(action, "action");
      int $i$f$forEach = 0;

      for(Map.Entry element : $this$forEach.entrySet()) {
         action.invoke(element);
      }

   }

   @SinceKotlin(
      version = "1.7"
   )
   @JvmName(
      name = "maxByOrThrow"
   )
   @InlineOnly
   private static final Map.Entry maxByOrThrow(Map $this$maxBy, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterable $this$maxBy$iv = (Iterable)$this$maxBy.entrySet();
      int $i$f$maxByOrThrow = 0;
      Iterator iterator$iv = $this$maxBy$iv.iterator();
      if (!iterator$iv.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Object maxElem$iv = iterator$iv.next();
         Object var10000;
         if (!iterator$iv.hasNext()) {
            var10000 = maxElem$iv;
         } else {
            Comparable maxValue$iv = (Comparable)selector.invoke(maxElem$iv);

            do {
               Object e$iv = iterator$iv.next();
               Comparable v$iv = (Comparable)selector.invoke(e$iv);
               if (maxValue$iv.compareTo(v$iv) < 0) {
                  maxElem$iv = e$iv;
                  maxValue$iv = v$iv;
               }
            } while(iterator$iv.hasNext());

            var10000 = maxElem$iv;
         }

         return (Map.Entry)var10000;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Map.Entry maxByOrNull(Map $this$maxByOrNull, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxByOrNull, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterable $this$maxByOrNull$iv = (Iterable)$this$maxByOrNull.entrySet();
      int $i$f$maxByOrNull = 0;
      Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
      Object var10000;
      if (!iterator$iv.hasNext()) {
         var10000 = null;
      } else {
         Object maxElem$iv = iterator$iv.next();
         if (!iterator$iv.hasNext()) {
            var10000 = maxElem$iv;
         } else {
            Comparable maxValue$iv = (Comparable)selector.invoke(maxElem$iv);

            do {
               Object e$iv = iterator$iv.next();
               Comparable v$iv = (Comparable)selector.invoke(e$iv);
               if (maxValue$iv.compareTo(v$iv) < 0) {
                  maxElem$iv = e$iv;
                  maxValue$iv = v$iv;
               }
            } while(iterator$iv.hasNext());

            var10000 = maxElem$iv;
         }
      }

      return (Map.Entry)var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final double maxOf(Map $this$maxOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$maxOf.entrySet()).iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         double var3;
         double var5;
         for(var3 = ((Number)selector.invoke(var2.next())).doubleValue(); var2.hasNext(); var3 = Math.max(var3, var5)) {
            var5 = ((Number)selector.invoke(var2.next())).doubleValue();
         }

         return var3;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final float maxOf(Map $this$maxOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$maxOf.entrySet()).iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         float var3;
         float var4;
         for(var3 = ((Number)selector.invoke(var2.next())).floatValue(); var2.hasNext(); var3 = Math.max(var3, var4)) {
            var4 = ((Number)selector.invoke(var2.next())).floatValue();
         }

         return var3;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Comparable maxOf(Map $this$maxOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$maxOf.entrySet()).iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Comparable var3 = (Comparable)selector.invoke(var2.next());

         while(var2.hasNext()) {
            Comparable var4 = (Comparable)selector.invoke(var2.next());
            if (var3.compareTo(var4) < 0) {
               var3 = var4;
            }
         }

         return var3;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Double maxOfOrNull(Map $this$maxOfOrNull, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxOfOrNull, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$maxOfOrNull.entrySet()).iterator();
      Double var10000;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         double var3;
         double var5;
         for(var3 = ((Number)selector.invoke(var2.next())).doubleValue(); var2.hasNext(); var3 = Math.max(var3, var5)) {
            var5 = ((Number)selector.invoke(var2.next())).doubleValue();
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Float maxOfOrNull(Map $this$maxOfOrNull, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxOfOrNull, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$maxOfOrNull.entrySet()).iterator();
      Float var10000;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         float var3;
         float var4;
         for(var3 = ((Number)selector.invoke(var2.next())).floatValue(); var2.hasNext(); var3 = Math.max(var3, var4)) {
            var4 = ((Number)selector.invoke(var2.next())).floatValue();
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Comparable maxOfOrNull(Map $this$maxOfOrNull, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxOfOrNull, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$maxOfOrNull.entrySet()).iterator();
      Comparable var10000;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         Comparable var3 = (Comparable)selector.invoke(var2.next());

         while(var2.hasNext()) {
            Comparable var4 = (Comparable)selector.invoke(var2.next());
            if (var3.compareTo(var4) < 0) {
               var3 = var4;
            }
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Object maxOfWith(Map $this$maxOfWith, Comparator comparator, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxOfWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var3 = ((Iterable)$this$maxOfWith.entrySet()).iterator();
      if (!var3.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Object var4 = selector.invoke(var3.next());

         while(var3.hasNext()) {
            Object var5 = selector.invoke(var3.next());
            if (comparator.compare(var4, var5) < 0) {
               var4 = var5;
            }
         }

         return var4;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Object maxOfWithOrNull(Map $this$maxOfWithOrNull, Comparator comparator, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$maxOfWithOrNull, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var3 = ((Iterable)$this$maxOfWithOrNull.entrySet()).iterator();
      Object var10000;
      if (!var3.hasNext()) {
         var10000 = null;
      } else {
         Object var4 = selector.invoke(var3.next());

         while(var3.hasNext()) {
            Object var5 = selector.invoke(var3.next());
            if (comparator.compare(var4, var5) < 0) {
               var4 = var5;
            }
         }

         var10000 = var4;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.7"
   )
   @JvmName(
      name = "maxWithOrThrow"
   )
   @InlineOnly
   private static final Map.Entry maxWithOrThrow(Map $this$maxWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return (Map.Entry)CollectionsKt.maxWithOrThrow((Iterable)$this$maxWith.entrySet(), comparator);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Map.Entry maxWithOrNull(Map $this$maxWithOrNull, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$maxWithOrNull, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return (Map.Entry)CollectionsKt.maxWithOrNull((Iterable)$this$maxWithOrNull.entrySet(), comparator);
   }

   @SinceKotlin(
      version = "1.7"
   )
   @JvmName(
      name = "minByOrThrow"
   )
   @InlineOnly
   private static final Map.Entry minByOrThrow(Map $this$minBy, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minBy, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterable $this$minBy$iv = (Iterable)$this$minBy.entrySet();
      int $i$f$minByOrThrow = 0;
      Iterator iterator$iv = $this$minBy$iv.iterator();
      if (!iterator$iv.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Object minElem$iv = iterator$iv.next();
         Object var10000;
         if (!iterator$iv.hasNext()) {
            var10000 = minElem$iv;
         } else {
            Comparable minValue$iv = (Comparable)selector.invoke(minElem$iv);

            do {
               Object e$iv = iterator$iv.next();
               Comparable v$iv = (Comparable)selector.invoke(e$iv);
               if (minValue$iv.compareTo(v$iv) > 0) {
                  minElem$iv = e$iv;
                  minValue$iv = v$iv;
               }
            } while(iterator$iv.hasNext());

            var10000 = minElem$iv;
         }

         return (Map.Entry)var10000;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Map.Entry minByOrNull(Map $this$minByOrNull, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minByOrNull, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterable $this$minByOrNull$iv = (Iterable)$this$minByOrNull.entrySet();
      int $i$f$minByOrNull = 0;
      Iterator iterator$iv = $this$minByOrNull$iv.iterator();
      Object var10000;
      if (!iterator$iv.hasNext()) {
         var10000 = null;
      } else {
         Object minElem$iv = iterator$iv.next();
         if (!iterator$iv.hasNext()) {
            var10000 = minElem$iv;
         } else {
            Comparable minValue$iv = (Comparable)selector.invoke(minElem$iv);

            do {
               Object e$iv = iterator$iv.next();
               Comparable v$iv = (Comparable)selector.invoke(e$iv);
               if (minValue$iv.compareTo(v$iv) > 0) {
                  minElem$iv = e$iv;
                  minValue$iv = v$iv;
               }
            } while(iterator$iv.hasNext());

            var10000 = minElem$iv;
         }
      }

      return (Map.Entry)var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final double minOf(Map $this$minOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$minOf.entrySet()).iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         double var3;
         double var5;
         for(var3 = ((Number)selector.invoke(var2.next())).doubleValue(); var2.hasNext(); var3 = Math.min(var3, var5)) {
            var5 = ((Number)selector.invoke(var2.next())).doubleValue();
         }

         return var3;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final float minOf(Map $this$minOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$minOf.entrySet()).iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         float var3;
         float var4;
         for(var3 = ((Number)selector.invoke(var2.next())).floatValue(); var2.hasNext(); var3 = Math.min(var3, var4)) {
            var4 = ((Number)selector.invoke(var2.next())).floatValue();
         }

         return var3;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Comparable minOf(Map $this$minOf, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minOf, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$minOf.entrySet()).iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Comparable var3 = (Comparable)selector.invoke(var2.next());

         while(var2.hasNext()) {
            Comparable var4 = (Comparable)selector.invoke(var2.next());
            if (var3.compareTo(var4) > 0) {
               var3 = var4;
            }
         }

         return var3;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Double minOfOrNull(Map $this$minOfOrNull, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minOfOrNull, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$minOfOrNull.entrySet()).iterator();
      Double var10000;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         double var3;
         double var5;
         for(var3 = ((Number)selector.invoke(var2.next())).doubleValue(); var2.hasNext(); var3 = Math.min(var3, var5)) {
            var5 = ((Number)selector.invoke(var2.next())).doubleValue();
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Float minOfOrNull(Map $this$minOfOrNull, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minOfOrNull, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$minOfOrNull.entrySet()).iterator();
      Float var10000;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         float var3;
         float var4;
         for(var3 = ((Number)selector.invoke(var2.next())).floatValue(); var2.hasNext(); var3 = Math.min(var3, var4)) {
            var4 = ((Number)selector.invoke(var2.next())).floatValue();
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Comparable minOfOrNull(Map $this$minOfOrNull, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minOfOrNull, "<this>");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var2 = ((Iterable)$this$minOfOrNull.entrySet()).iterator();
      Comparable var10000;
      if (!var2.hasNext()) {
         var10000 = null;
      } else {
         Comparable var3 = (Comparable)selector.invoke(var2.next());

         while(var2.hasNext()) {
            Comparable var4 = (Comparable)selector.invoke(var2.next());
            if (var3.compareTo(var4) > 0) {
               var3 = var4;
            }
         }

         var10000 = var3;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Object minOfWith(Map $this$minOfWith, Comparator comparator, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minOfWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var3 = ((Iterable)$this$minOfWith.entrySet()).iterator();
      if (!var3.hasNext()) {
         throw new NoSuchElementException();
      } else {
         Object var4 = selector.invoke(var3.next());

         while(var3.hasNext()) {
            Object var5 = selector.invoke(var3.next());
            if (comparator.compare(var4, var5) > 0) {
               var4 = var5;
            }
         }

         return var4;
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   private static final Object minOfWithOrNull(Map $this$minOfWithOrNull, Comparator comparator, Function1 selector) {
      Intrinsics.checkNotNullParameter($this$minOfWithOrNull, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      Intrinsics.checkNotNullParameter(selector, "selector");
      Iterator var3 = ((Iterable)$this$minOfWithOrNull.entrySet()).iterator();
      Object var10000;
      if (!var3.hasNext()) {
         var10000 = null;
      } else {
         Object var4 = selector.invoke(var3.next());

         while(var3.hasNext()) {
            Object var5 = selector.invoke(var3.next());
            if (comparator.compare(var4, var5) > 0) {
               var4 = var5;
            }
         }

         var10000 = var4;
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.7"
   )
   @JvmName(
      name = "minWithOrThrow"
   )
   @InlineOnly
   private static final Map.Entry minWithOrThrow(Map $this$minWith, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWith, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return (Map.Entry)CollectionsKt.minWithOrThrow((Iterable)$this$minWith.entrySet(), comparator);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Map.Entry minWithOrNull(Map $this$minWithOrNull, Comparator comparator) {
      Intrinsics.checkNotNullParameter($this$minWithOrNull, "<this>");
      Intrinsics.checkNotNullParameter(comparator, "comparator");
      return (Map.Entry)CollectionsKt.minWithOrNull((Iterable)$this$minWithOrNull.entrySet(), comparator);
   }

   public static final boolean none(@NotNull Map $this$none) {
      Intrinsics.checkNotNullParameter($this$none, "<this>");
      return $this$none.isEmpty();
   }

   public static final boolean none(@NotNull Map $this$none, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$none, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$none = 0;
      if ($this$none.isEmpty()) {
         return true;
      } else {
         for(Map.Entry element : $this$none.entrySet()) {
            if ((Boolean)predicate.invoke(element)) {
               return false;
            }
         }

         return true;
      }
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map onEach(@NotNull Map $this$onEach, @NotNull Function1 action) {
      Intrinsics.checkNotNullParameter($this$onEach, "<this>");
      Intrinsics.checkNotNullParameter(action, "action");
      int $i$f$onEach = 0;
      int var5 = 0;

      for(Map.Entry element : $this$onEach.entrySet()) {
         action.invoke(element);
      }

      return $this$onEach;
   }

   @SinceKotlin(
      version = "1.4"
   )
   @NotNull
   public static final Map onEachIndexed(@NotNull Map $this$onEachIndexed, @NotNull Function2 action) {
      Intrinsics.checkNotNullParameter($this$onEachIndexed, "<this>");
      Intrinsics.checkNotNullParameter(action, "action");
      int $i$f$onEachIndexed = 0;
      int var5 = 0;
      Iterable $this$forEachIndexed$iv = (Iterable)$this$onEachIndexed.entrySet();
      int $i$f$forEachIndexed = 0;
      int index$iv = 0;

      for(Object item$iv : $this$forEachIndexed$iv) {
         int var11 = index$iv++;
         if (var11 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         action.invoke(var11, item$iv);
      }

      return $this$onEachIndexed;
   }

   @InlineOnly
   private static final Iterable asIterable(Map $this$asIterable) {
      Intrinsics.checkNotNullParameter($this$asIterable, "<this>");
      return (Iterable)$this$asIterable.entrySet();
   }

   @NotNull
   public static final Sequence asSequence(@NotNull Map $this$asSequence) {
      Intrinsics.checkNotNullParameter($this$asSequence, "<this>");
      return CollectionsKt.asSequence((Iterable)$this$asSequence.entrySet());
   }

   public MapsKt___MapsKt() {
   }
}
