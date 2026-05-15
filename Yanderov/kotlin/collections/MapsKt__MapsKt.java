package kotlin.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.BuilderInference;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0088\u0001\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\u0010'\n\u0002\b\n\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\u001ag\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0003\u001a\u00020\u00022%\b\u0001\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\n\u0010\u000b\u001a_\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012%\b\u0001\u0010\b\u001a\u001f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\b\n\u0010\f\u001a%\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001¢\u0006\u0004\b\r\u0010\u000e\u001a8\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0010\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001H\u0087\b¢\u0006\u0004\b\u0011\u0010\u0012\u001aa\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fj\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0010\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012*\u0010\u0015\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013\"\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014¢\u0006\u0004\b\u0011\u0010\u0016\u001a8\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0017j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0018\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001H\u0087\b¢\u0006\u0004\b\u0019\u0010\u001a\u001aa\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0017j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001`\u0018\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012*\u0010\u0015\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013\"\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014¢\u0006\u0004\b\u0019\u0010\u001b\u001a(\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001H\u0087\b¢\u0006\u0004\b\u001c\u0010\u000e\u001aQ\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012*\u0010\u0015\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013\"\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014¢\u0006\u0004\b\u001c\u0010\u001d\u001a(\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001H\u0087\b¢\u0006\u0004\b\u001e\u0010\u000e\u001aQ\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012*\u0010\u0015\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013\"\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014¢\u0006\u0004\b\u001e\u0010\u001d\u001a,\u0010 \u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001fH\u0087\n¢\u0006\u0004\b \u0010!\u001a,\u0010\"\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001fH\u0087\n¢\u0006\u0004\b\"\u0010!\u001a;\u0010&\u001a\u00020%\"\t\b\u0000\u0010\u0000¢\u0006\u0002\b#\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010$\u001a\u00028\u0000H\u0087\n¢\u0006\u0004\b&\u0010'\u001a3\u0010(\u001a\u00020%\"\t\b\u0000\u0010\u0000¢\u0006\u0002\b#*\u000e\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0002\b\u00030\t2\u0006\u0010$\u001a\u00028\u0000H\u0087\b¢\u0006\u0004\b(\u0010'\u001a9\u0010*\u001a\u00020%\"\u0004\b\u0000\u0010\u0000\"\t\b\u0001\u0010\u0001¢\u0006\u0002\b#*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010)\u001a\u00028\u0001H\u0087\b¢\u0006\u0004\b*\u0010'\u001a]\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u001e\u0010+\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0004\u0012\u00020%0\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001aQ\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020%0\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b.\u0010-\u001a]\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u001e\u0010+\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0004\u0012\u00020%0\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b/\u0010-\u001as\u00102\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0018\b\u0002\u00100*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u00101\u001a\u00028\u00022\u001e\u0010+\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0004\u0012\u00020%0\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b2\u00103\u001as\u00104\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0018\b\u0002\u00100*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u00101\u001a\u00028\u00022\u001e\u0010+\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0004\u0012\u00020%0\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b4\u00103\u001aQ\u00105\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020%0\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\b5\u0010-\u001a=\u00106\u001a\u0004\u0018\u00018\u0001\"\t\b\u0000\u0010\u0000¢\u0006\u0002\b#\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010$\u001a\u00028\u0000H\u0087\n¢\u0006\u0004\b6\u00107\u001aR\u0010:\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010$\u001a\u00028\u00002\f\u00109\u001a\b\u0012\u0004\u0012\u00028\u000108H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000¢\u0006\u0004\b:\u0010;\u001aE\u0010<\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010$\u001a\u00028\u00002\f\u00109\u001a\b\u0012\u0004\u0012\u00028\u000108H\u0080\bø\u0001\u0000¢\u0006\u0004\b<\u0010;\u001aE\u0010=\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010$\u001a\u00028\u00002\f\u00109\u001a\b\u0012\u0004\u0012\u00028\u000108H\u0086\bø\u0001\u0000¢\u0006\u0004\b=\u0010;\u001a3\u0010>\u001a\u00028\u0001\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010$\u001a\u00028\u0000H\u0007¢\u0006\u0004\b>\u00107\u001aN\u0010@\u001a\u00028\u0001\"\u0014\b\u0000\u00100*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\t*\u00028\u0001\"\u0004\b\u0001\u0010?*\u00028\u00002\f\u00109\u001a\b\u0012\u0004\u0012\u00028\u000108H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b@\u0010A\u001a.\u0010B\u001a\u00020%\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0087\b¢\u0006\u0004\bB\u0010C\u001aA\u0010D\u001a\u00020%\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\tH\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0004\bD\u0010C\u001a@\u0010F\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f0E\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0087\n¢\u0006\u0004\bF\u0010G\u001a>\u0010F\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010I0H\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005H\u0087\n¢\u0006\u0004\bJ\u0010G\u001ac\u0010L\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010?*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u001e\u0010K\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0004\u0012\u00028\u00020\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\bL\u0010-\u001ay\u0010M\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010?\"\u0018\b\u0003\u00100*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0002\u0012\u0006\b\u0000\u0012\u00028\u00010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u00101\u001a\u00028\u00032\u001e\u0010K\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0004\u0012\u00028\u00020\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\bM\u00103\u001ac\u0010N\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010?*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u001e\u0010K\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0004\u0012\u00028\u00020\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\bN\u0010-\u001ay\u0010O\u001a\u00028\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010?\"\u0018\b\u0003\u00100*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00020\u0005*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u00101\u001a\u00028\u00032\u001e\u0010K\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f\u0012\u0004\u0012\u00028\u00020\u0004H\u0086\bø\u0001\u0000¢\u0006\u0004\bO\u00103\u001aB\u0010P\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010$\u001a\u00028\u0000H\u0087\u0002¢\u0006\u0004\bP\u0010Q\u001aJ\u0010P\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u000e\u0010R\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0013H\u0087\u0002¢\u0006\u0004\bP\u0010S\u001aH\u0010P\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\f\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000TH\u0087\u0002¢\u0006\u0004\bP\u0010U\u001aH\u0010P\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\f\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000VH\u0087\u0002¢\u0006\u0004\bP\u0010W\u001a4\u0010X\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010$\u001a\u00028\u0000H\u0087\n¢\u0006\u0004\bX\u0010Y\u001a<\u0010X\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\u000e\u0010R\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0013H\u0087\n¢\u0006\u0004\bX\u0010Z\u001a:\u0010X\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\f\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000TH\u0087\n¢\u0006\u0004\bX\u0010[\u001a:\u0010X\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\f\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000VH\u0087\n¢\u0006\u0004\bX\u0010\\\u001a7\u0010]\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0000¢\u0006\u0004\b]\u0010^\u001a:\u0010_\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\tH\u0087\b¢\u0006\u0004\b_\u0010^\u001aV\u0010`\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u001a\u0010\u0015\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013H\u0086\u0002¢\u0006\u0004\b`\u0010a\u001aN\u0010`\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0012\u0010b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014H\u0086\u0002¢\u0006\u0004\b`\u0010c\u001aT\u0010`\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140TH\u0086\u0002¢\u0006\u0004\b`\u0010U\u001aP\u0010`\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0014\u0010d\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0086\u0002¢\u0006\u0004\b`\u0010e\u001aT\u0010`\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140VH\u0086\u0002¢\u0006\u0004\b`\u0010W\u001aL\u0010f\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00052\u001a\u0010\u0015\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013H\u0087\n¢\u0006\u0004\bf\u0010g\u001aD\u0010f\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00052\u0012\u0010b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014H\u0087\n¢\u0006\u0004\bf\u0010h\u001aJ\u0010f\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00052\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140TH\u0087\n¢\u0006\u0004\bf\u0010[\u001aD\u0010f\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00052\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0087\n¢\u0006\u0004\bf\u0010i\u001aJ\u0010f\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00052\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140VH\u0087\n¢\u0006\u0004\bf\u0010\\\u001aI\u0010j\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00052\u001a\u0010\u0015\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013¢\u0006\u0004\bj\u0010g\u001aG\u0010j\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00052\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140T¢\u0006\u0004\bj\u0010[\u001aG\u0010j\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00052\u0018\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140V¢\u0006\u0004\bj\u0010\\\u001a=\u0010k\u001a\u0004\u0018\u00018\u0001\"\t\b\u0000\u0010\u0000¢\u0006\u0002\b#\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010$\u001a\u00028\u0000H\u0087\b¢\u0006\u0004\bk\u00107\u001a<\u0010l\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010$\u001a\u00028\u00002\u0006\u0010)\u001a\u00028\u0001H\u0087\n¢\u0006\u0004\bl\u0010m\u001a=\u0010n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u0013¢\u0006\u0004\bn\u0010\u001d\u001aS\u0010n\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0018\b\u0002\u00100*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u0005*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140\u00132\u0006\u00101\u001a\u00028\u0002¢\u0006\u0004\bn\u0010o\u001a;\u0010n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140T¢\u0006\u0004\bn\u0010p\u001aQ\u0010n\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0018\b\u0002\u00100*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140T2\u0006\u00101\u001a\u00028\u0002¢\u0006\u0004\bn\u0010q\u001a9\u0010n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0007¢\u0006\u0004\bn\u0010^\u001aO\u0010n\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0018\b\u0002\u00100*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u00101\u001a\u00028\u0002H\u0007¢\u0006\u0004\bn\u0010e\u001a;\u0010n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140V¢\u0006\u0004\bn\u0010r\u001aQ\u0010n\u001a\u00028\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0018\b\u0002\u00100*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00140V2\u0006\u00101\u001a\u00028\u0002¢\u0006\u0004\bn\u0010s\u001a9\u0010t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0007¢\u0006\u0004\bt\u0010^\u001a8\u0010u\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001fH\u0087\b¢\u0006\u0004\bu\u0010v\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006w"},
   d2 = {"K", "V", "", "capacity", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "", "buildMap", "(ILkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "emptyMap", "()Ljava/util/Map;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "hashMapOf", "()Ljava/util/HashMap;", "", "Lkotlin/Pair;", "pairs", "([Lkotlin/Pair;)Ljava/util/HashMap;", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "linkedMapOf", "()Ljava/util/LinkedHashMap;", "([Lkotlin/Pair;)Ljava/util/LinkedHashMap;", "mapOf", "([Lkotlin/Pair;)Ljava/util/Map;", "mutableMapOf", "", "component1", "(Ljava/util/Map$Entry;)Ljava/lang/Object;", "component2", "Lkotlin/internal/OnlyInputTypes;", "key", "", "contains", "(Ljava/util/Map;Ljava/lang/Object;)Z", "containsKey", "value", "containsValue", "predicate", "filter", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "filterKeys", "filterNot", "M", "destination", "filterNotTo", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "filterTo", "filterValues", "get", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/Function0;", "defaultValue", "getOrElse", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getOrElseNullable", "getOrPut", "getValue", "R", "ifEmpty", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "(Ljava/util/Map;)Z", "isNullOrEmpty", "", "iterator", "(Ljava/util/Map;)Ljava/util/Iterator;", "", "", "mutableIterator", "transform", "mapKeys", "mapKeysTo", "mapValues", "mapValuesTo", "minus", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/util/Map;", "keys", "(Ljava/util/Map;[Ljava/lang/Object;)Ljava/util/Map;", "", "(Ljava/util/Map;Ljava/lang/Iterable;)Ljava/util/Map;", "Lkotlin/sequences/Sequence;", "(Ljava/util/Map;Lkotlin/sequences/Sequence;)Ljava/util/Map;", "minusAssign", "(Ljava/util/Map;Ljava/lang/Object;)V", "(Ljava/util/Map;[Ljava/lang/Object;)V", "(Ljava/util/Map;Ljava/lang/Iterable;)V", "(Ljava/util/Map;Lkotlin/sequences/Sequence;)V", "optimizeReadOnlyMap", "(Ljava/util/Map;)Ljava/util/Map;", "orEmpty", "plus", "(Ljava/util/Map;[Lkotlin/Pair;)Ljava/util/Map;", "pair", "(Ljava/util/Map;Lkotlin/Pair;)Ljava/util/Map;", "map", "(Ljava/util/Map;Ljava/util/Map;)Ljava/util/Map;", "plusAssign", "(Ljava/util/Map;[Lkotlin/Pair;)V", "(Ljava/util/Map;Lkotlin/Pair;)V", "(Ljava/util/Map;Ljava/util/Map;)V", "putAll", "remove", "set", "(Ljava/util/Map;Ljava/lang/Object;Ljava/lang/Object;)V", "toMap", "([Lkotlin/Pair;Ljava/util/Map;)Ljava/util/Map;", "(Ljava/lang/Iterable;)Ljava/util/Map;", "(Ljava/lang/Iterable;Ljava/util/Map;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;)Ljava/util/Map;", "(Lkotlin/sequences/Sequence;Ljava/util/Map;)Ljava/util/Map;", "toMutableMap", "toPair", "(Ljava/util/Map$Entry;)Lkotlin/Pair;", "kotlin-stdlib"},
   xs = "kotlin/collections/MapsKt"
)
@SourceDebugExtension({"SMAP\nMaps.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,813:1\n412#1:823\n423#1:828\n520#1,6:833\n545#1,6:839\n1#2:814\n1246#3,4:815\n1246#3,4:819\n1246#3,4:824\n1246#3,4:829\n*S KotlinDebug\n*F\n+ 1 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n462#1:823\n477#1:828\n535#1:833,6\n560#1:839,6\n412#1:815,4\n423#1:819,4\n462#1:824,4\n477#1:829,4\n*E\n"})
class MapsKt__MapsKt extends MapsKt__MapsJVMKt {
   @NotNull
   public static final Map emptyMap() {
      EmptyMap var10000 = EmptyMap.INSTANCE;
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
      return var10000;
   }

   @NotNull
   public static final Map mapOf(@NotNull Pair... pairs) {
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      return pairs.length > 0 ? MapsKt.toMap(pairs, (Map)(new LinkedHashMap(MapsKt.mapCapacity(pairs.length)))) : MapsKt.emptyMap();
   }

   @InlineOnly
   private static final Map mapOf() {
      return MapsKt.emptyMap();
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final Map mutableMapOf() {
      return (Map)(new LinkedHashMap());
   }

   @NotNull
   public static final Map mutableMapOf(@NotNull Pair... pairs) {
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      LinkedHashMap $this$mutableMapOf_u24lambda_u240 = new LinkedHashMap(MapsKt.mapCapacity(pairs.length));
      int var3 = 0;
      MapsKt.putAll((Map)$this$mutableMapOf_u24lambda_u240, pairs);
      return (Map)$this$mutableMapOf_u24lambda_u240;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final HashMap hashMapOf() {
      return new HashMap();
   }

   @NotNull
   public static final HashMap hashMapOf(@NotNull Pair... pairs) {
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      HashMap $this$hashMapOf_u24lambda_u241 = new HashMap(MapsKt.mapCapacity(pairs.length));
      int var3 = 0;
      MapsKt.putAll((Map)$this$hashMapOf_u24lambda_u241, pairs);
      return $this$hashMapOf_u24lambda_u241;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final LinkedHashMap linkedMapOf() {
      return new LinkedHashMap();
   }

   @NotNull
   public static final LinkedHashMap linkedMapOf(@NotNull Pair... pairs) {
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      return (LinkedHashMap)MapsKt.toMap(pairs, (Map)(new LinkedHashMap(MapsKt.mapCapacity(pairs.length))));
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final Map buildMap(@BuilderInference Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      Map var1 = MapsKt.createMapBuilder();
      builderAction.invoke(var1);
      return MapsKt.build(var1);
   }

   @SinceKotlin(
      version = "1.6"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @InlineOnly
   private static final Map buildMap(int capacity, @BuilderInference Function1 builderAction) {
      Intrinsics.checkNotNullParameter(builderAction, "builderAction");
      Map var2 = MapsKt.createMapBuilder(capacity);
      builderAction.invoke(var2);
      return MapsKt.build(var2);
   }

   @InlineOnly
   private static final boolean isNotEmpty(Map $this$isNotEmpty) {
      Intrinsics.checkNotNullParameter($this$isNotEmpty, "<this>");
      return !$this$isNotEmpty.isEmpty();
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final boolean isNullOrEmpty(Map $this$isNullOrEmpty) {
      return $this$isNullOrEmpty == null || $this$isNullOrEmpty.isEmpty();
   }

   @InlineOnly
   private static final Map orEmpty(Map $this$orEmpty) {
      Map var10000 = $this$orEmpty;
      if ($this$orEmpty == null) {
         var10000 = MapsKt.emptyMap();
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final Object ifEmpty(Map $this$ifEmpty, Function0 defaultValue) {
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      return $this$ifEmpty.isEmpty() ? defaultValue.invoke() : $this$ifEmpty;
   }

   @InlineOnly
   private static final boolean contains(Map $this$contains, Object key) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.containsKey(key);
   }

   @InlineOnly
   private static final Object get(Map $this$get, Object key) {
      Intrinsics.checkNotNullParameter($this$get, "<this>");
      return $this$get.get(key);
   }

   @InlineOnly
   private static final void set(Map $this$set, Object key, Object value) {
      Intrinsics.checkNotNullParameter($this$set, "<this>");
      $this$set.put(key, value);
   }

   @InlineOnly
   private static final boolean containsKey(Map $this$containsKey, Object key) {
      Intrinsics.checkNotNullParameter($this$containsKey, "<this>");
      return $this$containsKey.containsKey(key);
   }

   @InlineOnly
   private static final boolean containsValue(Map $this$containsValue, Object value) {
      Intrinsics.checkNotNullParameter($this$containsValue, "<this>");
      return $this$containsValue.containsValue(value);
   }

   @InlineOnly
   private static final Object remove(Map $this$remove, Object key) {
      Intrinsics.checkNotNullParameter($this$remove, "<this>");
      return TypeIntrinsics.asMutableMap($this$remove).remove(key);
   }

   @InlineOnly
   private static final Object component1(Map.Entry $this$component1) {
      Intrinsics.checkNotNullParameter($this$component1, "<this>");
      return $this$component1.getKey();
   }

   @InlineOnly
   private static final Object component2(Map.Entry $this$component2) {
      Intrinsics.checkNotNullParameter($this$component2, "<this>");
      return $this$component2.getValue();
   }

   @InlineOnly
   private static final Pair toPair(Map.Entry $this$toPair) {
      Intrinsics.checkNotNullParameter($this$toPair, "<this>");
      return new Pair($this$toPair.getKey(), $this$toPair.getValue());
   }

   @InlineOnly
   private static final Object getOrElse(Map $this$getOrElse, Object key, Function0 defaultValue) {
      Intrinsics.checkNotNullParameter($this$getOrElse, "<this>");
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      Object var10000 = $this$getOrElse.get(key);
      if (var10000 == null) {
         var10000 = defaultValue.invoke();
      }

      return var10000;
   }

   public static final Object getOrElseNullable(@NotNull Map $this$getOrElseNullable, Object key, @NotNull Function0 defaultValue) {
      Intrinsics.checkNotNullParameter($this$getOrElseNullable, "<this>");
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      int $i$f$getOrElseNullable = 0;
      Object value = $this$getOrElseNullable.get(key);
      return value == null && !$this$getOrElseNullable.containsKey(key) ? defaultValue.invoke() : value;
   }

   @SinceKotlin(
      version = "1.1"
   )
   public static final Object getValue(@NotNull Map $this$getValue, Object key) {
      Intrinsics.checkNotNullParameter($this$getValue, "<this>");
      return MapsKt.getOrImplicitDefaultNullable($this$getValue, key);
   }

   public static final Object getOrPut(@NotNull Map $this$getOrPut, Object key, @NotNull Function0 defaultValue) {
      Intrinsics.checkNotNullParameter($this$getOrPut, "<this>");
      Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
      int $i$f$getOrPut = 0;
      Object value = $this$getOrPut.get(key);
      Object var10000;
      if (value == null) {
         Object answer = defaultValue.invoke();
         $this$getOrPut.put(key, answer);
         var10000 = answer;
      } else {
         var10000 = value;
      }

      return var10000;
   }

   @InlineOnly
   private static final Iterator iterator(Map $this$iterator) {
      Intrinsics.checkNotNullParameter($this$iterator, "<this>");
      return $this$iterator.entrySet().iterator();
   }

   @JvmName(
      name = "mutableIterator"
   )
   @InlineOnly
   private static final Iterator mutableIterator(Map $this$iterator) {
      Intrinsics.checkNotNullParameter($this$iterator, "<this>");
      return $this$iterator.entrySet().iterator();
   }

   @NotNull
   public static final Map mapValuesTo(@NotNull Map $this$mapValuesTo, @NotNull Map destination, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$mapValuesTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$mapValuesTo = 0;
      Iterable $this$associateByTo$iv = (Iterable)$this$mapValuesTo.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv : $this$associateByTo$iv) {
         Map.Entry it = (Map.Entry)element$iv;
         int var9 = 0;
         destination.put(it.getKey(), transform.invoke(element$iv));
      }

      return destination;
   }

   @NotNull
   public static final Map mapKeysTo(@NotNull Map $this$mapKeysTo, @NotNull Map destination, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$mapKeysTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$mapKeysTo = 0;
      Iterable $this$associateByTo$iv = (Iterable)$this$mapKeysTo.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv : $this$associateByTo$iv) {
         Object var10001 = transform.invoke(element$iv);
         Map.Entry it = (Map.Entry)element$iv;
         Object var11 = var10001;
         int var9 = 0;
         Object var12 = it.getValue();
         destination.put(var11, var12);
      }

      return destination;
   }

   public static final void putAll(@NotNull Map $this$putAll, @NotNull Pair[] pairs) {
      Intrinsics.checkNotNullParameter($this$putAll, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      int var2 = 0;

      for(int var3 = pairs.length; var2 < var3; ++var2) {
         Pair var4 = pairs[var2];
         Object key = var4.component1();
         Object value = var4.component2();
         $this$putAll.put(key, value);
      }

   }

   public static final void putAll(@NotNull Map $this$putAll, @NotNull Iterable pairs) {
      Intrinsics.checkNotNullParameter($this$putAll, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");

      for(Pair var3 : pairs) {
         Object key = var3.component1();
         Object value = var3.component2();
         $this$putAll.put(key, value);
      }

   }

   public static final void putAll(@NotNull Map $this$putAll, @NotNull Sequence pairs) {
      Intrinsics.checkNotNullParameter($this$putAll, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");

      for(Pair var3 : pairs) {
         Object key = var3.component1();
         Object value = var3.component2();
         $this$putAll.put(key, value);
      }

   }

   @NotNull
   public static final Map mapValues(@NotNull Map $this$mapValues, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$mapValues, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$mapValues = 0;
      Map destination$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity($this$mapValues.size())));
      int $i$f$mapValuesTo = 0;
      Iterable $this$associateByTo$iv$iv = (Iterable)$this$mapValues.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv : $this$associateByTo$iv$iv) {
         Map.Entry it$iv = (Map.Entry)element$iv$iv;
         int var12 = 0;
         destination$iv.put(it$iv.getKey(), transform.invoke(element$iv$iv));
      }

      return destination$iv;
   }

   @NotNull
   public static final Map mapKeys(@NotNull Map $this$mapKeys, @NotNull Function1 transform) {
      Intrinsics.checkNotNullParameter($this$mapKeys, "<this>");
      Intrinsics.checkNotNullParameter(transform, "transform");
      int $i$f$mapKeys = 0;
      Map destination$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity($this$mapKeys.size())));
      int $i$f$mapKeysTo = 0;
      Iterable $this$associateByTo$iv$iv = (Iterable)$this$mapKeys.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv : $this$associateByTo$iv$iv) {
         Object var10001 = transform.invoke(element$iv$iv);
         Map.Entry it$iv = (Map.Entry)element$iv$iv;
         Object var11 = var10001;
         int var13 = 0;
         Object var14 = it$iv.getValue();
         destination$iv.put(var11, var14);
      }

      return destination$iv;
   }

   @NotNull
   public static final Map filterKeys(@NotNull Map $this$filterKeys, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$filterKeys, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$filterKeys = 0;
      LinkedHashMap result = new LinkedHashMap();

      for(Map.Entry entry : $this$filterKeys.entrySet()) {
         if ((Boolean)predicate.invoke(entry.getKey())) {
            result.put(entry.getKey(), entry.getValue());
         }
      }

      return (Map)result;
   }

   @NotNull
   public static final Map filterValues(@NotNull Map $this$filterValues, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$filterValues, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$filterValues = 0;
      LinkedHashMap result = new LinkedHashMap();

      for(Map.Entry entry : $this$filterValues.entrySet()) {
         if ((Boolean)predicate.invoke(entry.getValue())) {
            result.put(entry.getKey(), entry.getValue());
         }
      }

      return (Map)result;
   }

   @NotNull
   public static final Map filterTo(@NotNull Map $this$filterTo, @NotNull Map destination, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$filterTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$filterTo = 0;

      for(Map.Entry element : $this$filterTo.entrySet()) {
         if ((Boolean)predicate.invoke(element)) {
            destination.put(element.getKey(), element.getValue());
         }
      }

      return destination;
   }

   @NotNull
   public static final Map filter(@NotNull Map $this$filter, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$filter, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$filter = 0;
      Map destination$iv = (Map)(new LinkedHashMap());
      int $i$f$filterTo = 0;

      for(Map.Entry element$iv : $this$filter.entrySet()) {
         if ((Boolean)predicate.invoke(element$iv)) {
            destination$iv.put(element$iv.getKey(), element$iv.getValue());
         }
      }

      return destination$iv;
   }

   @NotNull
   public static final Map filterNotTo(@NotNull Map $this$filterNotTo, @NotNull Map destination, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$filterNotTo, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$filterNotTo = 0;

      for(Map.Entry element : $this$filterNotTo.entrySet()) {
         if (!(Boolean)predicate.invoke(element)) {
            destination.put(element.getKey(), element.getValue());
         }
      }

      return destination;
   }

   @NotNull
   public static final Map filterNot(@NotNull Map $this$filterNot, @NotNull Function1 predicate) {
      Intrinsics.checkNotNullParameter($this$filterNot, "<this>");
      Intrinsics.checkNotNullParameter(predicate, "predicate");
      int $i$f$filterNot = 0;
      Map destination$iv = (Map)(new LinkedHashMap());
      int $i$f$filterNotTo = 0;

      for(Map.Entry element$iv : $this$filterNot.entrySet()) {
         if (!(Boolean)predicate.invoke(element$iv)) {
            destination$iv.put(element$iv.getKey(), element$iv.getValue());
         }
      }

      return destination$iv;
   }

   @NotNull
   public static final Map toMap(@NotNull Iterable $this$toMap) {
      Intrinsics.checkNotNullParameter($this$toMap, "<this>");
      if ($this$toMap instanceof Collection) {
         Map var10000;
         switch (((Collection)$this$toMap).size()) {
            case 0:
               var10000 = MapsKt.emptyMap();
               break;
            case 1:
               var10000 = MapsKt.mapOf($this$toMap instanceof List ? (Pair)((List)$this$toMap).get(0) : (Pair)$this$toMap.iterator().next());
               break;
            default:
               var10000 = MapsKt.toMap($this$toMap, (Map)(new LinkedHashMap(MapsKt.mapCapacity(((Collection)$this$toMap).size()))));
         }

         return var10000;
      } else {
         return MapsKt.optimizeReadOnlyMap(MapsKt.toMap($this$toMap, (Map)(new LinkedHashMap())));
      }
   }

   @NotNull
   public static final Map toMap(@NotNull Iterable $this$toMap, @NotNull Map destination) {
      Intrinsics.checkNotNullParameter($this$toMap, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      int var4 = 0;
      MapsKt.putAll(destination, $this$toMap);
      return destination;
   }

   @NotNull
   public static final Map toMap(@NotNull Pair[] $this$toMap) {
      Intrinsics.checkNotNullParameter($this$toMap, "<this>");
      Map var10000;
      switch ($this$toMap.length) {
         case 0:
            var10000 = MapsKt.emptyMap();
            break;
         case 1:
            var10000 = MapsKt.mapOf($this$toMap[0]);
            break;
         default:
            var10000 = MapsKt.toMap($this$toMap, (Map)(new LinkedHashMap(MapsKt.mapCapacity($this$toMap.length))));
      }

      return var10000;
   }

   @NotNull
   public static final Map toMap(@NotNull Pair[] $this$toMap, @NotNull Map destination) {
      Intrinsics.checkNotNullParameter($this$toMap, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      int var4 = 0;
      MapsKt.putAll(destination, $this$toMap);
      return destination;
   }

   @NotNull
   public static final Map toMap(@NotNull Sequence $this$toMap) {
      Intrinsics.checkNotNullParameter($this$toMap, "<this>");
      return MapsKt.optimizeReadOnlyMap(MapsKt.toMap($this$toMap, (Map)(new LinkedHashMap())));
   }

   @NotNull
   public static final Map toMap(@NotNull Sequence $this$toMap, @NotNull Map destination) {
      Intrinsics.checkNotNullParameter($this$toMap, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      int var4 = 0;
      MapsKt.putAll(destination, $this$toMap);
      return destination;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map toMap(@NotNull Map $this$toMap) {
      Intrinsics.checkNotNullParameter($this$toMap, "<this>");
      Map var10000;
      switch ($this$toMap.size()) {
         case 0:
            var10000 = MapsKt.emptyMap();
            break;
         case 1:
            var10000 = MapsKt.toSingletonMap($this$toMap);
            break;
         default:
            var10000 = MapsKt.toMutableMap($this$toMap);
      }

      return var10000;
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map toMutableMap(@NotNull Map $this$toMutableMap) {
      Intrinsics.checkNotNullParameter($this$toMutableMap, "<this>");
      return (Map)(new LinkedHashMap($this$toMutableMap));
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map toMap(@NotNull Map $this$toMap, @NotNull Map destination) {
      Intrinsics.checkNotNullParameter($this$toMap, "<this>");
      Intrinsics.checkNotNullParameter(destination, "destination");
      int var4 = 0;
      destination.putAll($this$toMap);
      return destination;
   }

   @NotNull
   public static final Map plus(@NotNull Map $this$plus, @NotNull Pair pair) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(pair, "pair");
      Map var10000;
      if ($this$plus.isEmpty()) {
         var10000 = MapsKt.mapOf(pair);
      } else {
         LinkedHashMap $this$plus_u24lambda_u2413 = new LinkedHashMap($this$plus);
         int var4 = 0;
         $this$plus_u24lambda_u2413.put(pair.getFirst(), pair.getSecond());
         var10000 = (Map)$this$plus_u24lambda_u2413;
      }

      return var10000;
   }

   @NotNull
   public static final Map plus(@NotNull Map $this$plus, @NotNull Iterable pairs) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      Map var10000;
      if ($this$plus.isEmpty()) {
         var10000 = MapsKt.toMap(pairs);
      } else {
         LinkedHashMap $this$plus_u24lambda_u2414 = new LinkedHashMap($this$plus);
         int var4 = 0;
         MapsKt.putAll((Map)$this$plus_u24lambda_u2414, pairs);
         var10000 = (Map)$this$plus_u24lambda_u2414;
      }

      return var10000;
   }

   @NotNull
   public static final Map plus(@NotNull Map $this$plus, @NotNull Pair[] pairs) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      Map var10000;
      if ($this$plus.isEmpty()) {
         var10000 = MapsKt.toMap(pairs);
      } else {
         LinkedHashMap $this$plus_u24lambda_u2415 = new LinkedHashMap($this$plus);
         int var4 = 0;
         MapsKt.putAll((Map)$this$plus_u24lambda_u2415, pairs);
         var10000 = (Map)$this$plus_u24lambda_u2415;
      }

      return var10000;
   }

   @NotNull
   public static final Map plus(@NotNull Map $this$plus, @NotNull Sequence pairs) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      LinkedHashMap $this$plus_u24lambda_u2416 = new LinkedHashMap($this$plus);
      int var4 = 0;
      MapsKt.putAll((Map)$this$plus_u24lambda_u2416, pairs);
      return MapsKt.optimizeReadOnlyMap((Map)$this$plus_u24lambda_u2416);
   }

   @NotNull
   public static final Map plus(@NotNull Map $this$plus, @NotNull Map map) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(map, "map");
      LinkedHashMap $this$plus_u24lambda_u2417 = new LinkedHashMap($this$plus);
      int var4 = 0;
      $this$plus_u24lambda_u2417.putAll(map);
      return (Map)$this$plus_u24lambda_u2417;
   }

   @InlineOnly
   private static final void plusAssign(Map $this$plusAssign, Pair pair) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(pair, "pair");
      $this$plusAssign.put(pair.getFirst(), pair.getSecond());
   }

   @InlineOnly
   private static final void plusAssign(Map $this$plusAssign, Iterable pairs) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      MapsKt.putAll($this$plusAssign, pairs);
   }

   @InlineOnly
   private static final void plusAssign(Map $this$plusAssign, Pair[] pairs) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      MapsKt.putAll($this$plusAssign, pairs);
   }

   @InlineOnly
   private static final void plusAssign(Map $this$plusAssign, Sequence pairs) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(pairs, "pairs");
      MapsKt.putAll($this$plusAssign, pairs);
   }

   @InlineOnly
   private static final void plusAssign(Map $this$plusAssign, Map map) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(map, "map");
      $this$plusAssign.putAll(map);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map minus(@NotNull Map $this$minus, Object key) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Map $this$minus_u24lambda_u2418 = MapsKt.toMutableMap($this$minus);
      int var4 = 0;
      $this$minus_u24lambda_u2418.remove(key);
      return MapsKt.optimizeReadOnlyMap($this$minus_u24lambda_u2418);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map minus(@NotNull Map $this$minus, @NotNull Iterable keys) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(keys, "keys");
      Map $this$minus_u24lambda_u2419 = MapsKt.toMutableMap($this$minus);
      int var4 = 0;
      CollectionsKt.removeAll((Collection)$this$minus_u24lambda_u2419.keySet(), keys);
      return MapsKt.optimizeReadOnlyMap($this$minus_u24lambda_u2419);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map minus(@NotNull Map $this$minus, @NotNull Object[] keys) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(keys, "keys");
      Map $this$minus_u24lambda_u2420 = MapsKt.toMutableMap($this$minus);
      int var4 = 0;
      CollectionsKt.removeAll((Collection)$this$minus_u24lambda_u2420.keySet(), keys);
      return MapsKt.optimizeReadOnlyMap($this$minus_u24lambda_u2420);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Map minus(@NotNull Map $this$minus, @NotNull Sequence keys) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(keys, "keys");
      Map $this$minus_u24lambda_u2421 = MapsKt.toMutableMap($this$minus);
      int var4 = 0;
      CollectionsKt.removeAll((Collection)$this$minus_u24lambda_u2421.keySet(), keys);
      return MapsKt.optimizeReadOnlyMap($this$minus_u24lambda_u2421);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final void minusAssign(Map $this$minusAssign, Object key) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      $this$minusAssign.remove(key);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final void minusAssign(Map $this$minusAssign, Iterable keys) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      Intrinsics.checkNotNullParameter(keys, "keys");
      CollectionsKt.removeAll((Collection)$this$minusAssign.keySet(), keys);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final void minusAssign(Map $this$minusAssign, Object[] keys) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      Intrinsics.checkNotNullParameter(keys, "keys");
      CollectionsKt.removeAll((Collection)$this$minusAssign.keySet(), keys);
   }

   @SinceKotlin(
      version = "1.1"
   )
   @InlineOnly
   private static final void minusAssign(Map $this$minusAssign, Sequence keys) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      Intrinsics.checkNotNullParameter(keys, "keys");
      CollectionsKt.removeAll((Collection)$this$minusAssign.keySet(), keys);
   }

   @NotNull
   public static final Map optimizeReadOnlyMap(@NotNull Map $this$optimizeReadOnlyMap) {
      Intrinsics.checkNotNullParameter($this$optimizeReadOnlyMap, "<this>");
      Map var10000;
      switch ($this$optimizeReadOnlyMap.size()) {
         case 0:
            var10000 = MapsKt.emptyMap();
            break;
         case 1:
            var10000 = MapsKt.toSingletonMap($this$optimizeReadOnlyMap);
            break;
         default:
            var10000 = $this$optimizeReadOnlyMap;
      }

      return var10000;
   }

   public MapsKt__MapsKt() {
   }
}
