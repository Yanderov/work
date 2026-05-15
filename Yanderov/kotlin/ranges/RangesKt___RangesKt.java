package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 5,
   xi = 49,
   d1 = {"\u0000\u0088\u0001\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b9\u001a)\u0010\u0003\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u00002\u0006\u0010\u0002\u001a\u00028\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0003\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0004\b\u0003\u0010\u0006\u001a\u0019\u0010\u0003\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0007¢\u0006\u0004\b\u0003\u0010\b\u001a\u0019\u0010\u0003\u001a\u00020\t*\u00020\t2\u0006\u0010\u0002\u001a\u00020\t¢\u0006\u0004\b\u0003\u0010\n\u001a\u0019\u0010\u0003\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b¢\u0006\u0004\b\u0003\u0010\f\u001a\u0019\u0010\u0003\u001a\u00020\r*\u00020\r2\u0006\u0010\u0002\u001a\u00020\r¢\u0006\u0004\b\u0003\u0010\u000e\u001a\u0019\u0010\u0003\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f¢\u0006\u0004\b\u0003\u0010\u0010\u001a)\u0010\u0012\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0004\b\u0012\u0010\u0004\u001a\u0019\u0010\u0012\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005¢\u0006\u0004\b\u0012\u0010\u0006\u001a\u0019\u0010\u0012\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007¢\u0006\u0004\b\u0012\u0010\b\u001a\u0019\u0010\u0012\u001a\u00020\t*\u00020\t2\u0006\u0010\u0011\u001a\u00020\t¢\u0006\u0004\b\u0012\u0010\n\u001a\u0019\u0010\u0012\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0012\u0010\f\u001a\u0019\u0010\u0012\u001a\u00020\r*\u00020\r2\u0006\u0010\u0011\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u000e\u001a\u0019\u0010\u0012\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0010\u001a5\u0010\u0013\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u00002\b\u0010\u0002\u001a\u0004\u0018\u00018\u00002\b\u0010\u0011\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a1\u0010\u0013\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0007¢\u0006\u0004\b\u0013\u0010\u0017\u001a/\u0010\u0013\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018¢\u0006\u0004\b\u0013\u0010\u0019\u001a!\u0010\u0013\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005¢\u0006\u0004\b\u0013\u0010\u001a\u001a!\u0010\u0013\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007¢\u0006\u0004\b\u0013\u0010\u001b\u001a!\u0010\u0013\u001a\u00020\t*\u00020\t2\u0006\u0010\u0002\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t¢\u0006\u0004\b\u0013\u0010\u001c\u001a!\u0010\u0013\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b¢\u0006\u0004\b\u0013\u0010\u001d\u001a\u001f\u0010\u0013\u001a\u00020\u000b*\u00020\u000b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0018¢\u0006\u0004\b\u0013\u0010\u001e\u001a!\u0010\u0013\u001a\u00020\r*\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\r¢\u0006\u0004\b\u0013\u0010\u001f\u001a\u001f\u0010\u0013\u001a\u00020\r*\u00020\r2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0018¢\u0006\u0004\b\u0013\u0010 \u001a!\u0010\u0013\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f¢\u0006\u0004\b\u0013\u0010!\u001a\u001e\u0010&\u001a\u00020%*\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010#H\u0087\n¢\u0006\u0004\b&\u0010'\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00050\u00182\u0006\u0010(\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b)\u0010*\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00050\u00182\u0006\u0010(\u001a\u00020\tH\u0087\u0002¢\u0006\u0004\b)\u0010+\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00050\u00182\u0006\u0010(\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b)\u0010,\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00050\u00182\u0006\u0010(\u001a\u00020\rH\u0087\u0002¢\u0006\u0004\b)\u0010-\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00050\u00182\u0006\u0010(\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b)\u0010.\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00070\u00182\u0006\u0010(\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b/\u00100\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00070\u00182\u0006\u0010(\u001a\u00020\tH\u0087\u0002¢\u0006\u0004\b/\u0010+\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00070\u00182\u0006\u0010(\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b/\u0010,\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00070\u00182\u0006\u0010(\u001a\u00020\rH\u0087\u0002¢\u0006\u0004\b/\u0010-\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00070\u00182\u0006\u0010(\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b/\u0010.\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\t0\u00182\u0006\u0010(\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b1\u00100\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\t0\u00182\u0006\u0010(\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b1\u0010*\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\t0\u00182\u0006\u0010(\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b1\u0010,\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\t0\u00182\u0006\u0010(\u001a\u00020\rH\u0087\u0002¢\u0006\u0004\b1\u0010-\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\t0\u00182\u0006\u0010(\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b1\u0010.\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010(\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b2\u00100\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010(\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b2\u0010*\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010(\u001a\u00020\tH\u0087\u0002¢\u0006\u0004\b2\u0010+\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010(\u001a\u00020\rH\u0087\u0002¢\u0006\u0004\b2\u0010-\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000b0\u00182\u0006\u0010(\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b2\u0010.\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\r0\u00182\u0006\u0010(\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b3\u00100\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\r0\u00182\u0006\u0010(\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b3\u0010*\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\r0\u00182\u0006\u0010(\u001a\u00020\tH\u0087\u0002¢\u0006\u0004\b3\u0010+\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\r0\u00182\u0006\u0010(\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b3\u0010,\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\r0\u00182\u0006\u0010(\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b3\u0010.\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000f0\u00182\u0006\u0010(\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b4\u00100\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000f0\u00182\u0006\u0010(\u001a\u00020\u0007H\u0087\u0002¢\u0006\u0004\b4\u0010*\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000f0\u00182\u0006\u0010(\u001a\u00020\tH\u0087\u0002¢\u0006\u0004\b4\u0010+\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000f0\u00182\u0006\u0010(\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b4\u0010,\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000f0\u00182\u0006\u0010(\u001a\u00020\rH\u0087\u0002¢\u0006\u0004\b4\u0010-\u001a\u001c\u0010&\u001a\u00020%*\u0002052\u0006\u0010(\u001a\u00020\u0005H\u0087\n¢\u0006\u0004\b&\u00106\u001a\u001e\u0010&\u001a\u00020%*\u0002052\b\u0010$\u001a\u0004\u0018\u00010\u000bH\u0087\n¢\u0006\u0004\b&\u00107\u001a\u001c\u0010&\u001a\u00020%*\u0002052\u0006\u0010(\u001a\u00020\rH\u0087\n¢\u0006\u0004\b&\u00108\u001a\u001c\u0010&\u001a\u00020%*\u0002052\u0006\u0010(\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b&\u00109\u001a\u001c\u0010&\u001a\u00020%*\u00020:2\u0006\u0010(\u001a\u00020\u0005H\u0087\n¢\u0006\u0004\b&\u0010;\u001a\u001c\u0010&\u001a\u00020%*\u00020:2\u0006\u0010(\u001a\u00020\u000bH\u0087\n¢\u0006\u0004\b&\u0010<\u001a\u001e\u0010&\u001a\u00020%*\u00020:2\b\u0010$\u001a\u0004\u0018\u00010\rH\u0087\n¢\u0006\u0004\b&\u0010=\u001a\u001c\u0010&\u001a\u00020%*\u00020:2\u0006\u0010(\u001a\u00020\u000fH\u0087\n¢\u0006\u0004\b&\u0010>\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00050?2\u0006\u0010(\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b)\u0010@\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00050?2\u0006\u0010(\u001a\u00020\rH\u0087\u0002¢\u0006\u0004\b)\u0010A\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00050?2\u0006\u0010(\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b)\u0010B\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u00070?2\u0006\u0010(\u001a\u00020\tH\u0087\u0002¢\u0006\u0004\b/\u0010C\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000b0?2\u0006\u0010(\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b2\u0010D\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000b0?2\u0006\u0010(\u001a\u00020\rH\u0087\u0002¢\u0006\u0004\b2\u0010A\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000b0?2\u0006\u0010(\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b2\u0010B\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\r0?2\u0006\u0010(\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b3\u0010D\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\r0?2\u0006\u0010(\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b3\u0010@\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\r0?2\u0006\u0010(\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b3\u0010B\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000f0?2\u0006\u0010(\u001a\u00020\u0005H\u0087\u0002¢\u0006\u0004\b4\u0010D\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000f0?2\u0006\u0010(\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b4\u0010@\u001a\"\u0010&\u001a\u00020%*\b\u0012\u0004\u0012\u00020\u000f0?2\u0006\u0010(\u001a\u00020\rH\u0087\u0002¢\u0006\u0004\b4\u0010A\u001a\u001c\u0010G\u001a\u00020F*\u00020\u00052\u0006\u0010E\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\bG\u0010H\u001a\u001c\u0010G\u001a\u00020F*\u00020\u00052\u0006\u0010E\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0004\bG\u0010I\u001a\u001c\u0010G\u001a\u00020J*\u00020\u00052\u0006\u0010E\u001a\u00020\rH\u0086\u0004¢\u0006\u0004\bG\u0010K\u001a\u001c\u0010G\u001a\u00020F*\u00020\u00052\u0006\u0010E\u001a\u00020\u000fH\u0086\u0004¢\u0006\u0004\bG\u0010L\u001a\u001c\u0010G\u001a\u00020M*\u00020#2\u0006\u0010E\u001a\u00020#H\u0086\u0004¢\u0006\u0004\bG\u0010N\u001a\u001c\u0010G\u001a\u00020F*\u00020\u000b2\u0006\u0010E\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\bG\u0010O\u001a\u001c\u0010G\u001a\u00020F*\u00020\u000b2\u0006\u0010E\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0004\bG\u0010P\u001a\u001c\u0010G\u001a\u00020J*\u00020\u000b2\u0006\u0010E\u001a\u00020\rH\u0086\u0004¢\u0006\u0004\bG\u0010Q\u001a\u001c\u0010G\u001a\u00020F*\u00020\u000b2\u0006\u0010E\u001a\u00020\u000fH\u0086\u0004¢\u0006\u0004\bG\u0010R\u001a\u001c\u0010G\u001a\u00020J*\u00020\r2\u0006\u0010E\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\bG\u0010S\u001a\u001c\u0010G\u001a\u00020J*\u00020\r2\u0006\u0010E\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0004\bG\u0010T\u001a\u001c\u0010G\u001a\u00020J*\u00020\r2\u0006\u0010E\u001a\u00020\rH\u0086\u0004¢\u0006\u0004\bG\u0010U\u001a\u001c\u0010G\u001a\u00020J*\u00020\r2\u0006\u0010E\u001a\u00020\u000fH\u0086\u0004¢\u0006\u0004\bG\u0010V\u001a\u001c\u0010G\u001a\u00020F*\u00020\u000f2\u0006\u0010E\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0004\bG\u0010W\u001a\u001c\u0010G\u001a\u00020F*\u00020\u000f2\u0006\u0010E\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0004\bG\u0010X\u001a\u001c\u0010G\u001a\u00020J*\u00020\u000f2\u0006\u0010E\u001a\u00020\rH\u0086\u0004¢\u0006\u0004\bG\u0010Y\u001a\u001c\u0010G\u001a\u00020F*\u00020\u000f2\u0006\u0010E\u001a\u00020\u000fH\u0086\u0004¢\u0006\u0004\bG\u0010Z\u001a\u0013\u0010[\u001a\u00020#*\u00020MH\u0007¢\u0006\u0004\b[\u0010\\\u001a\u0013\u0010[\u001a\u00020\u000b*\u00020FH\u0007¢\u0006\u0004\b[\u0010]\u001a\u0013\u0010[\u001a\u00020\r*\u00020JH\u0007¢\u0006\u0004\b[\u0010^\u001a\u0015\u0010_\u001a\u0004\u0018\u00010#*\u00020MH\u0007¢\u0006\u0004\b_\u0010`\u001a\u0015\u0010_\u001a\u0004\u0018\u00010\u000b*\u00020FH\u0007¢\u0006\u0004\b_\u0010a\u001a\u0015\u0010_\u001a\u0004\u0018\u00010\r*\u00020JH\u0007¢\u0006\u0004\b_\u0010b\u001a\u0013\u0010c\u001a\u00020#*\u00020MH\u0007¢\u0006\u0004\bc\u0010\\\u001a\u0013\u0010c\u001a\u00020\u000b*\u00020FH\u0007¢\u0006\u0004\bc\u0010]\u001a\u0013\u0010c\u001a\u00020\r*\u00020JH\u0007¢\u0006\u0004\bc\u0010^\u001a\u0015\u0010d\u001a\u0004\u0018\u00010#*\u00020MH\u0007¢\u0006\u0004\bd\u0010`\u001a\u0015\u0010d\u001a\u0004\u0018\u00010\u000b*\u00020FH\u0007¢\u0006\u0004\bd\u0010a\u001a\u0015\u0010d\u001a\u0004\u0018\u00010\r*\u00020JH\u0007¢\u0006\u0004\bd\u0010b\u001a\u0014\u0010e\u001a\u00020#*\u00020\"H\u0087\b¢\u0006\u0004\be\u0010f\u001a\u001b\u0010e\u001a\u00020#*\u00020\"2\u0006\u0010e\u001a\u00020gH\u0007¢\u0006\u0004\be\u0010h\u001a\u0014\u0010e\u001a\u00020\u000b*\u000205H\u0087\b¢\u0006\u0004\be\u0010i\u001a\u001b\u0010e\u001a\u00020\u000b*\u0002052\u0006\u0010e\u001a\u00020gH\u0007¢\u0006\u0004\be\u0010j\u001a\u0014\u0010e\u001a\u00020\r*\u00020:H\u0087\b¢\u0006\u0004\be\u0010k\u001a\u001b\u0010e\u001a\u00020\r*\u00020:2\u0006\u0010e\u001a\u00020gH\u0007¢\u0006\u0004\be\u0010l\u001a\u0016\u0010m\u001a\u0004\u0018\u00010#*\u00020\"H\u0087\b¢\u0006\u0004\bm\u0010n\u001a\u001d\u0010m\u001a\u0004\u0018\u00010#*\u00020\"2\u0006\u0010e\u001a\u00020gH\u0007¢\u0006\u0004\bm\u0010o\u001a\u0016\u0010m\u001a\u0004\u0018\u00010\u000b*\u000205H\u0087\b¢\u0006\u0004\bm\u0010p\u001a\u001d\u0010m\u001a\u0004\u0018\u00010\u000b*\u0002052\u0006\u0010e\u001a\u00020gH\u0007¢\u0006\u0004\bm\u0010q\u001a\u0016\u0010m\u001a\u0004\u0018\u00010\r*\u00020:H\u0087\b¢\u0006\u0004\bm\u0010r\u001a\u001d\u0010m\u001a\u0004\u0018\u00010\r*\u00020:2\u0006\u0010e\u001a\u00020gH\u0007¢\u0006\u0004\bm\u0010s\u001a\u0011\u0010t\u001a\u00020M*\u00020M¢\u0006\u0004\bt\u0010u\u001a\u0011\u0010t\u001a\u00020F*\u00020F¢\u0006\u0004\bt\u0010v\u001a\u0011\u0010t\u001a\u00020J*\u00020J¢\u0006\u0004\bt\u0010w\u001a\u001c\u0010x\u001a\u00020M*\u00020M2\u0006\u0010x\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0004\bx\u0010y\u001a\u001c\u0010x\u001a\u00020F*\u00020F2\u0006\u0010x\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0004\bx\u0010z\u001a\u001c\u0010x\u001a\u00020J*\u00020J2\u0006\u0010x\u001a\u00020\rH\u0086\u0004¢\u0006\u0004\bx\u0010{\u001a\u0015\u0010|\u001a\u0004\u0018\u00010\u0005*\u00020\u0007H\u0000¢\u0006\u0004\b|\u0010}\u001a\u0015\u0010|\u001a\u0004\u0018\u00010\u0005*\u00020\tH\u0000¢\u0006\u0004\b|\u0010~\u001a\u0015\u0010|\u001a\u0004\u0018\u00010\u0005*\u00020\u000bH\u0000¢\u0006\u0004\b|\u0010\u007f\u001a\u0016\u0010|\u001a\u0004\u0018\u00010\u0005*\u00020\rH\u0000¢\u0006\u0005\b|\u0010\u0080\u0001\u001a\u0016\u0010|\u001a\u0004\u0018\u00010\u0005*\u00020\u000fH\u0000¢\u0006\u0005\b|\u0010\u0081\u0001\u001a\u0018\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u000b*\u00020\u0007H\u0000¢\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001\u001a\u0018\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u000b*\u00020\tH\u0000¢\u0006\u0006\b\u0082\u0001\u0010\u0084\u0001\u001a\u0018\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u000b*\u00020\rH\u0000¢\u0006\u0006\b\u0082\u0001\u0010\u0085\u0001\u001a\u0018\u0010\u0086\u0001\u001a\u0004\u0018\u00010\r*\u00020\u0007H\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001\u001a\u0018\u0010\u0086\u0001\u001a\u0004\u0018\u00010\r*\u00020\tH\u0000¢\u0006\u0006\b\u0086\u0001\u0010\u0088\u0001\u001a\u0018\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u000f*\u00020\u0007H\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001\u001a\u0018\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u000f*\u00020\tH\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u008b\u0001\u001a\u0018\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u000f*\u00020\u000bH\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u008c\u0001\u001a\u0018\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u000f*\u00020\rH\u0000¢\u0006\u0006\b\u0089\u0001\u0010\u008d\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u00052\u0006\u0010E\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u00052\u0006\u0010E\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0090\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u00020:*\u00020\u00052\u0006\u0010E\u001a\u00020\rH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0091\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u00052\u0006\u0010E\u001a\u00020\u000fH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0092\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u00020\"*\u00020#2\u0006\u0010E\u001a\u00020#H\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0093\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u000b2\u0006\u0010E\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0094\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u000b2\u0006\u0010E\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0095\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u00020:*\u00020\u000b2\u0006\u0010E\u001a\u00020\rH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0096\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u000b2\u0006\u0010E\u001a\u00020\u000fH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0097\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u00020:*\u00020\r2\u0006\u0010E\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0098\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u00020:*\u00020\r2\u0006\u0010E\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u0099\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u00020:*\u00020\r2\u0006\u0010E\u001a\u00020\rH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u009a\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u00020:*\u00020\r2\u0006\u0010E\u001a\u00020\u000fH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u009b\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u000f2\u0006\u0010E\u001a\u00020\u0005H\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u009c\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u000f2\u0006\u0010E\u001a\u00020\u000bH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u009d\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u00020:*\u00020\u000f2\u0006\u0010E\u001a\u00020\rH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u009e\u0001\u001a\u001f\u0010\u008e\u0001\u001a\u000205*\u00020\u000f2\u0006\u0010E\u001a\u00020\u000fH\u0086\u0004¢\u0006\u0006\b\u008e\u0001\u0010\u009f\u0001¨\u0006 \u0001"},
   d2 = {"", "T", "minimumValue", "coerceAtLeast", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "(BB)B", "", "(DD)D", "", "(FF)F", "", "(II)I", "", "(JJ)J", "", "(SS)S", "maximumValue", "coerceAtMost", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "Lkotlin/ranges/ClosedFloatingPointRange;", "range", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "(BBB)B", "(DDD)D", "(FFF)F", "(III)I", "(ILkotlin/ranges/ClosedRange;)I", "(JJJ)J", "(JLkotlin/ranges/ClosedRange;)J", "(SSS)S", "Lkotlin/ranges/CharRange;", "", "element", "", "contains", "(Lkotlin/ranges/CharRange;Ljava/lang/Character;)Z", "value", "byteRangeContains", "(Lkotlin/ranges/ClosedRange;D)Z", "(Lkotlin/ranges/ClosedRange;F)Z", "(Lkotlin/ranges/ClosedRange;I)Z", "(Lkotlin/ranges/ClosedRange;J)Z", "(Lkotlin/ranges/ClosedRange;S)Z", "doubleRangeContains", "(Lkotlin/ranges/ClosedRange;B)Z", "floatRangeContains", "intRangeContains", "longRangeContains", "shortRangeContains", "Lkotlin/ranges/IntRange;", "(Lkotlin/ranges/IntRange;B)Z", "(Lkotlin/ranges/IntRange;Ljava/lang/Integer;)Z", "(Lkotlin/ranges/IntRange;J)Z", "(Lkotlin/ranges/IntRange;S)Z", "Lkotlin/ranges/LongRange;", "(Lkotlin/ranges/LongRange;B)Z", "(Lkotlin/ranges/LongRange;I)Z", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;)Z", "(Lkotlin/ranges/LongRange;S)Z", "Lkotlin/ranges/OpenEndRange;", "(Lkotlin/ranges/OpenEndRange;I)Z", "(Lkotlin/ranges/OpenEndRange;J)Z", "(Lkotlin/ranges/OpenEndRange;S)Z", "(Lkotlin/ranges/OpenEndRange;F)Z", "(Lkotlin/ranges/OpenEndRange;B)Z", "to", "Lkotlin/ranges/IntProgression;", "downTo", "(BB)Lkotlin/ranges/IntProgression;", "(BI)Lkotlin/ranges/IntProgression;", "Lkotlin/ranges/LongProgression;", "(BJ)Lkotlin/ranges/LongProgression;", "(BS)Lkotlin/ranges/IntProgression;", "Lkotlin/ranges/CharProgression;", "(CC)Lkotlin/ranges/CharProgression;", "(IB)Lkotlin/ranges/IntProgression;", "(II)Lkotlin/ranges/IntProgression;", "(IJ)Lkotlin/ranges/LongProgression;", "(IS)Lkotlin/ranges/IntProgression;", "(JB)Lkotlin/ranges/LongProgression;", "(JI)Lkotlin/ranges/LongProgression;", "(JJ)Lkotlin/ranges/LongProgression;", "(JS)Lkotlin/ranges/LongProgression;", "(SB)Lkotlin/ranges/IntProgression;", "(SI)Lkotlin/ranges/IntProgression;", "(SJ)Lkotlin/ranges/LongProgression;", "(SS)Lkotlin/ranges/IntProgression;", "first", "(Lkotlin/ranges/CharProgression;)C", "(Lkotlin/ranges/IntProgression;)I", "(Lkotlin/ranges/LongProgression;)J", "firstOrNull", "(Lkotlin/ranges/CharProgression;)Ljava/lang/Character;", "(Lkotlin/ranges/IntProgression;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongProgression;)Ljava/lang/Long;", "last", "lastOrNull", "random", "(Lkotlin/ranges/CharRange;)C", "Lkotlin/random/Random;", "(Lkotlin/ranges/CharRange;Lkotlin/random/Random;)C", "(Lkotlin/ranges/IntRange;)I", "(Lkotlin/ranges/IntRange;Lkotlin/random/Random;)I", "(Lkotlin/ranges/LongRange;)J", "(Lkotlin/ranges/LongRange;Lkotlin/random/Random;)J", "randomOrNull", "(Lkotlin/ranges/CharRange;)Ljava/lang/Character;", "(Lkotlin/ranges/CharRange;Lkotlin/random/Random;)Ljava/lang/Character;", "(Lkotlin/ranges/IntRange;)Ljava/lang/Integer;", "(Lkotlin/ranges/IntRange;Lkotlin/random/Random;)Ljava/lang/Integer;", "(Lkotlin/ranges/LongRange;)Ljava/lang/Long;", "(Lkotlin/ranges/LongRange;Lkotlin/random/Random;)Ljava/lang/Long;", "reversed", "(Lkotlin/ranges/CharProgression;)Lkotlin/ranges/CharProgression;", "(Lkotlin/ranges/IntProgression;)Lkotlin/ranges/IntProgression;", "(Lkotlin/ranges/LongProgression;)Lkotlin/ranges/LongProgression;", "step", "(Lkotlin/ranges/CharProgression;I)Lkotlin/ranges/CharProgression;", "(Lkotlin/ranges/IntProgression;I)Lkotlin/ranges/IntProgression;", "(Lkotlin/ranges/LongProgression;J)Lkotlin/ranges/LongProgression;", "toByteExactOrNull", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "toIntExactOrNull", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "(J)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "until", "(BB)Lkotlin/ranges/IntRange;", "(BI)Lkotlin/ranges/IntRange;", "(BJ)Lkotlin/ranges/LongRange;", "(BS)Lkotlin/ranges/IntRange;", "(CC)Lkotlin/ranges/CharRange;", "(IB)Lkotlin/ranges/IntRange;", "(II)Lkotlin/ranges/IntRange;", "(IJ)Lkotlin/ranges/LongRange;", "(IS)Lkotlin/ranges/IntRange;", "(JB)Lkotlin/ranges/LongRange;", "(JI)Lkotlin/ranges/LongRange;", "(JJ)Lkotlin/ranges/LongRange;", "(JS)Lkotlin/ranges/LongRange;", "(SB)Lkotlin/ranges/IntRange;", "(SI)Lkotlin/ranges/IntRange;", "(SJ)Lkotlin/ranges/LongRange;", "(SS)Lkotlin/ranges/IntRange;", "kotlin-stdlib"},
   xs = "kotlin/ranges/RangesKt"
)
@SourceDebugExtension({"SMAP\n_Ranges.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Ranges.kt\nkotlin/ranges/RangesKt___RangesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1538:1\n1#2:1539\n*E\n"})
class RangesKt___RangesKt extends RangesKt__RangesKt {
   @SinceKotlin(
      version = "1.7"
   )
   public static final int first(@NotNull IntProgression $this$first) {
      Intrinsics.checkNotNullParameter($this$first, "<this>");
      if ($this$first.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$first + " is empty.");
      } else {
         return $this$first.getFirst();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   public static final long first(@NotNull LongProgression $this$first) {
      Intrinsics.checkNotNullParameter($this$first, "<this>");
      if ($this$first.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$first + " is empty.");
      } else {
         return $this$first.getFirst();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   public static final char first(@NotNull CharProgression $this$first) {
      Intrinsics.checkNotNullParameter($this$first, "<this>");
      if ($this$first.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$first + " is empty.");
      } else {
         return $this$first.getFirst();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final Integer firstOrNull(@NotNull IntProgression $this$firstOrNull) {
      Intrinsics.checkNotNullParameter($this$firstOrNull, "<this>");
      return $this$firstOrNull.isEmpty() ? null : $this$firstOrNull.getFirst();
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final Long firstOrNull(@NotNull LongProgression $this$firstOrNull) {
      Intrinsics.checkNotNullParameter($this$firstOrNull, "<this>");
      return $this$firstOrNull.isEmpty() ? null : $this$firstOrNull.getFirst();
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final Character firstOrNull(@NotNull CharProgression $this$firstOrNull) {
      Intrinsics.checkNotNullParameter($this$firstOrNull, "<this>");
      return $this$firstOrNull.isEmpty() ? null : $this$firstOrNull.getFirst();
   }

   @SinceKotlin(
      version = "1.7"
   )
   public static final int last(@NotNull IntProgression $this$last) {
      Intrinsics.checkNotNullParameter($this$last, "<this>");
      if ($this$last.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$last + " is empty.");
      } else {
         return $this$last.getLast();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   public static final long last(@NotNull LongProgression $this$last) {
      Intrinsics.checkNotNullParameter($this$last, "<this>");
      if ($this$last.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$last + " is empty.");
      } else {
         return $this$last.getLast();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   public static final char last(@NotNull CharProgression $this$last) {
      Intrinsics.checkNotNullParameter($this$last, "<this>");
      if ($this$last.isEmpty()) {
         throw new NoSuchElementException("Progression " + $this$last + " is empty.");
      } else {
         return $this$last.getLast();
      }
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final Integer lastOrNull(@NotNull IntProgression $this$lastOrNull) {
      Intrinsics.checkNotNullParameter($this$lastOrNull, "<this>");
      return $this$lastOrNull.isEmpty() ? null : $this$lastOrNull.getLast();
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final Long lastOrNull(@NotNull LongProgression $this$lastOrNull) {
      Intrinsics.checkNotNullParameter($this$lastOrNull, "<this>");
      return $this$lastOrNull.isEmpty() ? null : $this$lastOrNull.getLast();
   }

   @SinceKotlin(
      version = "1.7"
   )
   @Nullable
   public static final Character lastOrNull(@NotNull CharProgression $this$lastOrNull) {
      Intrinsics.checkNotNullParameter($this$lastOrNull, "<this>");
      return $this$lastOrNull.isEmpty() ? null : $this$lastOrNull.getLast();
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final int random(IntRange $this$random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      return RangesKt.random($this$random, Random.Default);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final long random(LongRange $this$random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      return RangesKt.random($this$random, Random.Default);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final char random(CharRange $this$random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      return RangesKt.random($this$random, Random.Default);
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final int random(@NotNull IntRange $this$random, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");

      try {
         return RandomKt.nextInt(random, $this$random);
      } catch (IllegalArgumentException e) {
         throw new NoSuchElementException(e.getMessage());
      }
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final long random(@NotNull LongRange $this$random, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");

      try {
         return RandomKt.nextLong(random, $this$random);
      } catch (IllegalArgumentException e) {
         throw new NoSuchElementException(e.getMessage());
      }
   }

   @SinceKotlin(
      version = "1.3"
   )
   public static final char random(@NotNull CharRange $this$random, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$random, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");

      try {
         return (char)random.nextInt($this$random.getFirst(), $this$random.getLast() + 1);
      } catch (IllegalArgumentException e) {
         throw new NoSuchElementException(e.getMessage());
      }
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Integer randomOrNull(IntRange $this$randomOrNull) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      return RangesKt.randomOrNull($this$randomOrNull, Random.Default);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Long randomOrNull(LongRange $this$randomOrNull) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      return RangesKt.randomOrNull($this$randomOrNull, Random.Default);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @InlineOnly
   private static final Character randomOrNull(CharRange $this$randomOrNull) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      return RangesKt.randomOrNull($this$randomOrNull, Random.Default);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @Nullable
   public static final Integer randomOrNull(@NotNull IntRange $this$randomOrNull, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      return $this$randomOrNull.isEmpty() ? null : RandomKt.nextInt(random, $this$randomOrNull);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @Nullable
   public static final Long randomOrNull(@NotNull LongRange $this$randomOrNull, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      return $this$randomOrNull.isEmpty() ? null : RandomKt.nextLong(random, $this$randomOrNull);
   }

   @SinceKotlin(
      version = "1.4"
   )
   @Nullable
   public static final Character randomOrNull(@NotNull CharRange $this$randomOrNull, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$randomOrNull, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      return $this$randomOrNull.isEmpty() ? null : (char)random.nextInt($this$randomOrNull.getFirst(), $this$randomOrNull.getLast() + 1);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final boolean contains(IntRange $this$contains, Integer element) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return element != null && $this$contains.contains(element);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final boolean contains(LongRange $this$contains, Long element) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return element != null && $this$contains.contains(element);
   }

   @SinceKotlin(
      version = "1.3"
   )
   @InlineOnly
   private static final boolean contains(CharRange $this$contains, Character element) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return element != null && $this$contains.contains(element);
   }

   @JvmName(
      name = "intRangeContains"
   )
   public static final boolean intRangeContains(@NotNull ClosedRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)value);
   }

   @JvmName(
      name = "longRangeContains"
   )
   public static final boolean longRangeContains(@NotNull ClosedRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(long)value);
   }

   @JvmName(
      name = "shortRangeContains"
   )
   public static final boolean shortRangeContains(@NotNull ClosedRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(short)value);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "doubleRangeContains"
   )
   public static final boolean doubleRangeContains(ClosedRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(double)value);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "floatRangeContains"
   )
   public static final boolean floatRangeContains(ClosedRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(float)value);
   }

   @JvmName(
      name = "intRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean intRangeContains(@NotNull OpenEndRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)value);
   }

   @JvmName(
      name = "longRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean longRangeContains(@NotNull OpenEndRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(long)value);
   }

   @JvmName(
      name = "shortRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean shortRangeContains(@NotNull OpenEndRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(short)value);
   }

   @InlineOnly
   private static final boolean contains(IntRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return RangesKt.intRangeContains($this$contains, value);
   }

   @InlineOnly
   private static final boolean contains(LongRange $this$contains, byte value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return RangesKt.longRangeContains($this$contains, value);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "intRangeContains"
   )
   public static final boolean intRangeContains(ClosedRange $this$contains, double value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Integer it = RangesKt.toIntExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "longRangeContains"
   )
   public static final boolean longRangeContains(ClosedRange $this$contains, double value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Long it = RangesKt.toLongExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "byteRangeContains"
   )
   public static final boolean byteRangeContains(ClosedRange $this$contains, double value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Byte it = RangesKt.toByteExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "shortRangeContains"
   )
   public static final boolean shortRangeContains(ClosedRange $this$contains, double value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Short it = RangesKt.toShortExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @JvmName(
      name = "floatRangeContains"
   )
   public static final boolean floatRangeContains(@NotNull ClosedRange $this$contains, double value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(float)value);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "intRangeContains"
   )
   public static final boolean intRangeContains(ClosedRange $this$contains, float value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Integer it = RangesKt.toIntExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "longRangeContains"
   )
   public static final boolean longRangeContains(ClosedRange $this$contains, float value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Long it = RangesKt.toLongExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "byteRangeContains"
   )
   public static final boolean byteRangeContains(ClosedRange $this$contains, float value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Byte it = RangesKt.toByteExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "shortRangeContains"
   )
   public static final boolean shortRangeContains(ClosedRange $this$contains, float value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Short it = RangesKt.toShortExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @JvmName(
      name = "doubleRangeContains"
   )
   public static final boolean doubleRangeContains(@NotNull ClosedRange $this$contains, float value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(double)value);
   }

   @JvmName(
      name = "doubleRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean doubleRangeContains(@NotNull OpenEndRange $this$contains, float value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(double)value);
   }

   @JvmName(
      name = "longRangeContains"
   )
   public static final boolean longRangeContains(@NotNull ClosedRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(long)value);
   }

   @JvmName(
      name = "byteRangeContains"
   )
   public static final boolean byteRangeContains(@NotNull ClosedRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Byte it = RangesKt.toByteExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @JvmName(
      name = "shortRangeContains"
   )
   public static final boolean shortRangeContains(@NotNull ClosedRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Short it = RangesKt.toShortExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "doubleRangeContains"
   )
   public static final boolean doubleRangeContains(ClosedRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(double)value);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "floatRangeContains"
   )
   public static final boolean floatRangeContains(ClosedRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(float)value);
   }

   @JvmName(
      name = "longRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean longRangeContains(@NotNull OpenEndRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(long)value);
   }

   @JvmName(
      name = "byteRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean byteRangeContains(@NotNull OpenEndRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Byte it = RangesKt.toByteExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @JvmName(
      name = "shortRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean shortRangeContains(@NotNull OpenEndRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Short it = RangesKt.toShortExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @InlineOnly
   private static final boolean contains(LongRange $this$contains, int value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return RangesKt.longRangeContains($this$contains, value);
   }

   @JvmName(
      name = "intRangeContains"
   )
   public static final boolean intRangeContains(@NotNull ClosedRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Integer it = RangesKt.toIntExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @JvmName(
      name = "byteRangeContains"
   )
   public static final boolean byteRangeContains(@NotNull ClosedRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Byte it = RangesKt.toByteExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @JvmName(
      name = "shortRangeContains"
   )
   public static final boolean shortRangeContains(@NotNull ClosedRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Short it = RangesKt.toShortExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "doubleRangeContains"
   )
   public static final boolean doubleRangeContains(ClosedRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(double)value);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "floatRangeContains"
   )
   public static final boolean floatRangeContains(ClosedRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(float)value);
   }

   @JvmName(
      name = "intRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean intRangeContains(@NotNull OpenEndRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Integer it = RangesKt.toIntExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @JvmName(
      name = "byteRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean byteRangeContains(@NotNull OpenEndRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Byte it = RangesKt.toByteExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @JvmName(
      name = "shortRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean shortRangeContains(@NotNull OpenEndRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Short it = RangesKt.toShortExactOrNull(value);
      int var5 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @InlineOnly
   private static final boolean contains(IntRange $this$contains, long value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return RangesKt.intRangeContains($this$contains, value);
   }

   @JvmName(
      name = "intRangeContains"
   )
   public static final boolean intRangeContains(@NotNull ClosedRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)value);
   }

   @JvmName(
      name = "longRangeContains"
   )
   public static final boolean longRangeContains(@NotNull ClosedRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(long)value);
   }

   @JvmName(
      name = "byteRangeContains"
   )
   public static final boolean byteRangeContains(@NotNull ClosedRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Byte it = RangesKt.toByteExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "doubleRangeContains"
   )
   public static final boolean doubleRangeContains(ClosedRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(double)value);
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed."
   )
   @DeprecatedSinceKotlin(
      warningSince = "1.3",
      errorSince = "1.4",
      hiddenSince = "1.5"
   )
   @JvmName(
      name = "floatRangeContains"
   )
   public static final boolean floatRangeContains(ClosedRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(float)value);
   }

   @JvmName(
      name = "intRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean intRangeContains(@NotNull OpenEndRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)value);
   }

   @JvmName(
      name = "longRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean longRangeContains(@NotNull OpenEndRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return $this$contains.contains((Comparable)(long)value);
   }

   @JvmName(
      name = "byteRangeContains"
   )
   @SinceKotlin(
      version = "1.9"
   )
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   public static final boolean byteRangeContains(@NotNull OpenEndRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Byte it = RangesKt.toByteExactOrNull(value);
      int var4 = 0;
      return it != null ? $this$contains.contains((Comparable)it) : false;
   }

   @InlineOnly
   private static final boolean contains(IntRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return RangesKt.intRangeContains($this$contains, value);
   }

   @InlineOnly
   private static final boolean contains(LongRange $this$contains, short value) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      return RangesKt.longRangeContains($this$contains, value);
   }

   @NotNull
   public static final IntProgression downTo(int $this$downTo, byte to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final LongProgression downTo(long $this$downTo, byte to) {
      return LongProgression.Companion.fromClosedRange($this$downTo, (long)to, -1L);
   }

   @NotNull
   public static final IntProgression downTo(byte $this$downTo, byte to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final IntProgression downTo(short $this$downTo, byte to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final CharProgression downTo(char $this$downTo, char to) {
      return CharProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final IntProgression downTo(int $this$downTo, int to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final LongProgression downTo(long $this$downTo, int to) {
      return LongProgression.Companion.fromClosedRange($this$downTo, (long)to, -1L);
   }

   @NotNull
   public static final IntProgression downTo(byte $this$downTo, int to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final IntProgression downTo(short $this$downTo, int to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final LongProgression downTo(int $this$downTo, long to) {
      return LongProgression.Companion.fromClosedRange((long)$this$downTo, to, -1L);
   }

   @NotNull
   public static final LongProgression downTo(long $this$downTo, long to) {
      return LongProgression.Companion.fromClosedRange($this$downTo, to, -1L);
   }

   @NotNull
   public static final LongProgression downTo(byte $this$downTo, long to) {
      return LongProgression.Companion.fromClosedRange((long)$this$downTo, to, -1L);
   }

   @NotNull
   public static final LongProgression downTo(short $this$downTo, long to) {
      return LongProgression.Companion.fromClosedRange((long)$this$downTo, to, -1L);
   }

   @NotNull
   public static final IntProgression downTo(int $this$downTo, short to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final LongProgression downTo(long $this$downTo, short to) {
      return LongProgression.Companion.fromClosedRange($this$downTo, (long)to, -1L);
   }

   @NotNull
   public static final IntProgression downTo(byte $this$downTo, short to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final IntProgression downTo(short $this$downTo, short to) {
      return IntProgression.Companion.fromClosedRange($this$downTo, to, -1);
   }

   @NotNull
   public static final IntProgression reversed(@NotNull IntProgression $this$reversed) {
      Intrinsics.checkNotNullParameter($this$reversed, "<this>");
      return IntProgression.Companion.fromClosedRange($this$reversed.getLast(), $this$reversed.getFirst(), -$this$reversed.getStep());
   }

   @NotNull
   public static final LongProgression reversed(@NotNull LongProgression $this$reversed) {
      Intrinsics.checkNotNullParameter($this$reversed, "<this>");
      return LongProgression.Companion.fromClosedRange($this$reversed.getLast(), $this$reversed.getFirst(), -$this$reversed.getStep());
   }

   @NotNull
   public static final CharProgression reversed(@NotNull CharProgression $this$reversed) {
      Intrinsics.checkNotNullParameter($this$reversed, "<this>");
      return CharProgression.Companion.fromClosedRange($this$reversed.getLast(), $this$reversed.getFirst(), -$this$reversed.getStep());
   }

   @NotNull
   public static final IntProgression step(@NotNull IntProgression $this$step, int step) {
      Intrinsics.checkNotNullParameter($this$step, "<this>");
      RangesKt.checkStepIsPositive(step > 0, (Number)step);
      return IntProgression.Companion.fromClosedRange($this$step.getFirst(), $this$step.getLast(), $this$step.getStep() > 0 ? step : -step);
   }

   @NotNull
   public static final LongProgression step(@NotNull LongProgression $this$step, long step) {
      Intrinsics.checkNotNullParameter($this$step, "<this>");
      RangesKt.checkStepIsPositive(step > 0L, (Number)step);
      return LongProgression.Companion.fromClosedRange($this$step.getFirst(), $this$step.getLast(), $this$step.getStep() > 0L ? step : -step);
   }

   @NotNull
   public static final CharProgression step(@NotNull CharProgression $this$step, int step) {
      Intrinsics.checkNotNullParameter($this$step, "<this>");
      RangesKt.checkStepIsPositive(step > 0, (Number)step);
      return CharProgression.Companion.fromClosedRange($this$step.getFirst(), $this$step.getLast(), $this$step.getStep() > 0 ? step : -step);
   }

   @Nullable
   public static final Byte toByteExactOrNull(int $this$toByteExactOrNull) {
      return (-128 <= $this$toByteExactOrNull ? $this$toByteExactOrNull < 128 : false) ? (byte)$this$toByteExactOrNull : null;
   }

   @Nullable
   public static final Byte toByteExactOrNull(long $this$toByteExactOrNull) {
      return (-128L <= $this$toByteExactOrNull ? $this$toByteExactOrNull < 128L : false) ? (byte)((int)$this$toByteExactOrNull) : null;
   }

   @Nullable
   public static final Byte toByteExactOrNull(short $this$toByteExactOrNull) {
      return (-128 <= $this$toByteExactOrNull ? $this$toByteExactOrNull < 128 : false) ? (byte)$this$toByteExactOrNull : null;
   }

   @Nullable
   public static final Byte toByteExactOrNull(double $this$toByteExactOrNull) {
      return ((double)-128.0F <= $this$toByteExactOrNull ? $this$toByteExactOrNull <= (double)127.0F : false) ? (byte)((int)$this$toByteExactOrNull) : null;
   }

   @Nullable
   public static final Byte toByteExactOrNull(float $this$toByteExactOrNull) {
      return (-128.0F <= $this$toByteExactOrNull ? $this$toByteExactOrNull <= 127.0F : false) ? (byte)((int)$this$toByteExactOrNull) : null;
   }

   @Nullable
   public static final Integer toIntExactOrNull(long $this$toIntExactOrNull) {
      return (-2147483648L <= $this$toIntExactOrNull ? $this$toIntExactOrNull < 2147483648L : false) ? (int)$this$toIntExactOrNull : null;
   }

   @Nullable
   public static final Integer toIntExactOrNull(double $this$toIntExactOrNull) {
      return ((double)Integer.MIN_VALUE <= $this$toIntExactOrNull ? $this$toIntExactOrNull <= (double)Integer.MAX_VALUE : false) ? (int)$this$toIntExactOrNull : null;
   }

   @Nullable
   public static final Integer toIntExactOrNull(float $this$toIntExactOrNull) {
      return ((float)Integer.MIN_VALUE <= $this$toIntExactOrNull ? $this$toIntExactOrNull <= (float)Integer.MAX_VALUE : false) ? (int)$this$toIntExactOrNull : null;
   }

   @Nullable
   public static final Long toLongExactOrNull(double $this$toLongExactOrNull) {
      return ((double)Long.MIN_VALUE <= $this$toLongExactOrNull ? $this$toLongExactOrNull <= (double)Long.MAX_VALUE : false) ? (long)$this$toLongExactOrNull : null;
   }

   @Nullable
   public static final Long toLongExactOrNull(float $this$toLongExactOrNull) {
      return ((float)Long.MIN_VALUE <= $this$toLongExactOrNull ? $this$toLongExactOrNull <= (float)Long.MAX_VALUE : false) ? (long)$this$toLongExactOrNull : null;
   }

   @Nullable
   public static final Short toShortExactOrNull(int $this$toShortExactOrNull) {
      return (-32768 <= $this$toShortExactOrNull ? $this$toShortExactOrNull < 32768 : false) ? (short)$this$toShortExactOrNull : null;
   }

   @Nullable
   public static final Short toShortExactOrNull(long $this$toShortExactOrNull) {
      return (-32768L <= $this$toShortExactOrNull ? $this$toShortExactOrNull < 32768L : false) ? (short)((int)$this$toShortExactOrNull) : null;
   }

   @Nullable
   public static final Short toShortExactOrNull(double $this$toShortExactOrNull) {
      return ((double)-32768.0F <= $this$toShortExactOrNull ? $this$toShortExactOrNull <= (double)32767.0F : false) ? (short)((int)$this$toShortExactOrNull) : null;
   }

   @Nullable
   public static final Short toShortExactOrNull(float $this$toShortExactOrNull) {
      return (-32768.0F <= $this$toShortExactOrNull ? $this$toShortExactOrNull <= 32767.0F : false) ? (short)((int)$this$toShortExactOrNull) : null;
   }

   @NotNull
   public static final IntRange until(int $this$until, byte to) {
      return new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final LongRange until(long $this$until, byte to) {
      return new LongRange($this$until, (long)to - 1L);
   }

   @NotNull
   public static final IntRange until(byte $this$until, byte to) {
      return new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final IntRange until(short $this$until, byte to) {
      return new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final CharRange until(char $this$until, char to) {
      return Intrinsics.compare(to, 0) <= 0 ? CharRange.Companion.getEMPTY() : new CharRange($this$until, (char)(to - 1));
   }

   @NotNull
   public static final IntRange until(int $this$until, int to) {
      return to <= Integer.MIN_VALUE ? IntRange.Companion.getEMPTY() : new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final LongRange until(long $this$until, int to) {
      return new LongRange($this$until, (long)to - 1L);
   }

   @NotNull
   public static final IntRange until(byte $this$until, int to) {
      return to <= Integer.MIN_VALUE ? IntRange.Companion.getEMPTY() : new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final IntRange until(short $this$until, int to) {
      return to <= Integer.MIN_VALUE ? IntRange.Companion.getEMPTY() : new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final LongRange until(int $this$until, long to) {
      return to <= Long.MIN_VALUE ? LongRange.Companion.getEMPTY() : new LongRange((long)$this$until, to - 1L);
   }

   @NotNull
   public static final LongRange until(long $this$until, long to) {
      return to <= Long.MIN_VALUE ? LongRange.Companion.getEMPTY() : new LongRange($this$until, to - 1L);
   }

   @NotNull
   public static final LongRange until(byte $this$until, long to) {
      return to <= Long.MIN_VALUE ? LongRange.Companion.getEMPTY() : new LongRange((long)$this$until, to - 1L);
   }

   @NotNull
   public static final LongRange until(short $this$until, long to) {
      return to <= Long.MIN_VALUE ? LongRange.Companion.getEMPTY() : new LongRange((long)$this$until, to - 1L);
   }

   @NotNull
   public static final IntRange until(int $this$until, short to) {
      return new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final LongRange until(long $this$until, short to) {
      return new LongRange($this$until, (long)to - 1L);
   }

   @NotNull
   public static final IntRange until(byte $this$until, short to) {
      return new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final IntRange until(short $this$until, short to) {
      return new IntRange($this$until, to - 1);
   }

   @NotNull
   public static final Comparable coerceAtLeast(@NotNull Comparable $this$coerceAtLeast, @NotNull Comparable minimumValue) {
      Intrinsics.checkNotNullParameter($this$coerceAtLeast, "<this>");
      Intrinsics.checkNotNullParameter(minimumValue, "minimumValue");
      return $this$coerceAtLeast.compareTo(minimumValue) < 0 ? minimumValue : $this$coerceAtLeast;
   }

   public static final byte coerceAtLeast(byte $this$coerceAtLeast, byte minimumValue) {
      return $this$coerceAtLeast < minimumValue ? minimumValue : $this$coerceAtLeast;
   }

   public static final short coerceAtLeast(short $this$coerceAtLeast, short minimumValue) {
      return $this$coerceAtLeast < minimumValue ? minimumValue : $this$coerceAtLeast;
   }

   public static final int coerceAtLeast(int $this$coerceAtLeast, int minimumValue) {
      return $this$coerceAtLeast < minimumValue ? minimumValue : $this$coerceAtLeast;
   }

   public static final long coerceAtLeast(long $this$coerceAtLeast, long minimumValue) {
      return $this$coerceAtLeast < minimumValue ? minimumValue : $this$coerceAtLeast;
   }

   public static final float coerceAtLeast(float $this$coerceAtLeast, float minimumValue) {
      return $this$coerceAtLeast < minimumValue ? minimumValue : $this$coerceAtLeast;
   }

   public static final double coerceAtLeast(double $this$coerceAtLeast, double minimumValue) {
      return $this$coerceAtLeast < minimumValue ? minimumValue : $this$coerceAtLeast;
   }

   @NotNull
   public static final Comparable coerceAtMost(@NotNull Comparable $this$coerceAtMost, @NotNull Comparable maximumValue) {
      Intrinsics.checkNotNullParameter($this$coerceAtMost, "<this>");
      Intrinsics.checkNotNullParameter(maximumValue, "maximumValue");
      return $this$coerceAtMost.compareTo(maximumValue) > 0 ? maximumValue : $this$coerceAtMost;
   }

   public static final byte coerceAtMost(byte $this$coerceAtMost, byte maximumValue) {
      return $this$coerceAtMost > maximumValue ? maximumValue : $this$coerceAtMost;
   }

   public static final short coerceAtMost(short $this$coerceAtMost, short maximumValue) {
      return $this$coerceAtMost > maximumValue ? maximumValue : $this$coerceAtMost;
   }

   public static final int coerceAtMost(int $this$coerceAtMost, int maximumValue) {
      return $this$coerceAtMost > maximumValue ? maximumValue : $this$coerceAtMost;
   }

   public static final long coerceAtMost(long $this$coerceAtMost, long maximumValue) {
      return $this$coerceAtMost > maximumValue ? maximumValue : $this$coerceAtMost;
   }

   public static final float coerceAtMost(float $this$coerceAtMost, float maximumValue) {
      return $this$coerceAtMost > maximumValue ? maximumValue : $this$coerceAtMost;
   }

   public static final double coerceAtMost(double $this$coerceAtMost, double maximumValue) {
      return $this$coerceAtMost > maximumValue ? maximumValue : $this$coerceAtMost;
   }

   @NotNull
   public static final Comparable coerceIn(@NotNull Comparable $this$coerceIn, @Nullable Comparable minimumValue, @Nullable Comparable maximumValue) {
      Intrinsics.checkNotNullParameter($this$coerceIn, "<this>");
      if (minimumValue != null && maximumValue != null) {
         if (minimumValue.compareTo(maximumValue) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
         }

         if ($this$coerceIn.compareTo(minimumValue) < 0) {
            return minimumValue;
         }

         if ($this$coerceIn.compareTo(maximumValue) > 0) {
            return maximumValue;
         }
      } else {
         if (minimumValue != null && $this$coerceIn.compareTo(minimumValue) < 0) {
            return minimumValue;
         }

         if (maximumValue != null && $this$coerceIn.compareTo(maximumValue) > 0) {
            return maximumValue;
         }
      }

      return $this$coerceIn;
   }

   public static final byte coerceIn(byte $this$coerceIn, byte minimumValue, byte maximumValue) {
      if (minimumValue > maximumValue) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
      } else if ($this$coerceIn < minimumValue) {
         return minimumValue;
      } else {
         return $this$coerceIn > maximumValue ? maximumValue : $this$coerceIn;
      }
   }

   public static final short coerceIn(short $this$coerceIn, short minimumValue, short maximumValue) {
      if (minimumValue > maximumValue) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
      } else if ($this$coerceIn < minimumValue) {
         return minimumValue;
      } else {
         return $this$coerceIn > maximumValue ? maximumValue : $this$coerceIn;
      }
   }

   public static final int coerceIn(int $this$coerceIn, int minimumValue, int maximumValue) {
      if (minimumValue > maximumValue) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
      } else if ($this$coerceIn < minimumValue) {
         return minimumValue;
      } else {
         return $this$coerceIn > maximumValue ? maximumValue : $this$coerceIn;
      }
   }

   public static final long coerceIn(long $this$coerceIn, long minimumValue, long maximumValue) {
      if (minimumValue > maximumValue) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
      } else if ($this$coerceIn < minimumValue) {
         return minimumValue;
      } else {
         return $this$coerceIn > maximumValue ? maximumValue : $this$coerceIn;
      }
   }

   public static final float coerceIn(float $this$coerceIn, float minimumValue, float maximumValue) {
      if (minimumValue > maximumValue) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
      } else if ($this$coerceIn < minimumValue) {
         return minimumValue;
      } else {
         return $this$coerceIn > maximumValue ? maximumValue : $this$coerceIn;
      }
   }

   public static final double coerceIn(double $this$coerceIn, double minimumValue, double maximumValue) {
      if (minimumValue > maximumValue) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + maximumValue + " is less than minimum " + minimumValue + '.');
      } else if ($this$coerceIn < minimumValue) {
         return minimumValue;
      } else {
         return $this$coerceIn > maximumValue ? maximumValue : $this$coerceIn;
      }
   }

   @SinceKotlin(
      version = "1.1"
   )
   @NotNull
   public static final Comparable coerceIn(@NotNull Comparable $this$coerceIn, @NotNull ClosedFloatingPointRange range) {
      Intrinsics.checkNotNullParameter($this$coerceIn, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      if (range.isEmpty()) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
      } else {
         return range.lessThanOrEquals($this$coerceIn, range.getStart()) && !range.lessThanOrEquals(range.getStart(), $this$coerceIn) ? range.getStart() : (range.lessThanOrEquals(range.getEndInclusive(), $this$coerceIn) && !range.lessThanOrEquals($this$coerceIn, range.getEndInclusive()) ? range.getEndInclusive() : $this$coerceIn);
      }
   }

   @NotNull
   public static final Comparable coerceIn(@NotNull Comparable $this$coerceIn, @NotNull ClosedRange range) {
      Intrinsics.checkNotNullParameter($this$coerceIn, "<this>");
      Intrinsics.checkNotNullParameter(range, "range");
      if (range instanceof ClosedFloatingPointRange) {
         return RangesKt.coerceIn($this$coerceIn, (ClosedFloatingPointRange)range);
      } else if (range.isEmpty()) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
      } else {
         return $this$coerceIn.compareTo(range.getStart()) < 0 ? range.getStart() : ($this$coerceIn.compareTo(range.getEndInclusive()) > 0 ? range.getEndInclusive() : $this$coerceIn);
      }
   }

   public static final int coerceIn(int $this$coerceIn, @NotNull ClosedRange range) {
      Intrinsics.checkNotNullParameter(range, "range");
      if (range instanceof ClosedFloatingPointRange) {
         return ((Number)RangesKt.coerceIn((Comparable)$this$coerceIn, (ClosedFloatingPointRange)range)).intValue();
      } else if (range.isEmpty()) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
      } else {
         return $this$coerceIn < ((Number)range.getStart()).intValue() ? ((Number)range.getStart()).intValue() : ($this$coerceIn > ((Number)range.getEndInclusive()).intValue() ? ((Number)range.getEndInclusive()).intValue() : $this$coerceIn);
      }
   }

   public static final long coerceIn(long $this$coerceIn, @NotNull ClosedRange range) {
      Intrinsics.checkNotNullParameter(range, "range");
      if (range instanceof ClosedFloatingPointRange) {
         return ((Number)RangesKt.coerceIn((Comparable)$this$coerceIn, (ClosedFloatingPointRange)range)).longValue();
      } else if (range.isEmpty()) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: " + range + '.');
      } else {
         return $this$coerceIn < ((Number)range.getStart()).longValue() ? ((Number)range.getStart()).longValue() : ($this$coerceIn > ((Number)range.getEndInclusive()).longValue() ? ((Number)range.getEndInclusive()).longValue() : $this$coerceIn);
      }
   }

   public RangesKt___RangesKt() {
   }
}
