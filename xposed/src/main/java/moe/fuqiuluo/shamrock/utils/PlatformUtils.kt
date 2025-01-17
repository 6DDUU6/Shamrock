package moe.fuqiuluo.shamrock.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageInfo
import android.os.BatteryManager
import android.os.Build
import android.provider.Settings
import kotlinx.serialization.Serializable
import mqq.app.MobileQQ
import kotlin.random.Random

internal object PlatformUtils {
    fun getVersion(context: Context): String {
        val packageInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }

    /**
     * 获取OIDB包的ClientVersion信息
     */
    fun getClientVersion(context: Context): String {
        val packageInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val versionName = packageInfo.versionName
        return "android $versionName"
    }

    /**
     * 是否处于QQ MSF协议进程
     */
    fun isMsfProcess(): Boolean {
        return MobileQQ.getMobileQQ().qqProcessName.contains("msf", ignoreCase = true)
    }

    /**
     * 是否处于QQ主进程
     */
    fun isMainProcess(): Boolean {
        return isMqq() || isTim()
    }

    fun isMqq(): Boolean {
        return MobileQQ.getMobileQQ().qqProcessName == "com.tencent.mobileqq"
    }

    fun isTim(): Boolean {
        return MobileQQ.getMobileQQ().qqProcessName == "com.tencent.tim"
    }

    fun getDeviceBattery(): DeviceBattery {
        val ctx = MobileQQ.getContext()
        return kotlin.runCatching {
            val batteryManager = ctx.getSystemService(BATTERY_SERVICE) as BatteryManager

            DeviceBattery(
                battery = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY),
                scale = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER ),
                status = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_STATUS) else -1,
            )
        }.getOrElse {
            val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val receiver = ctx.registerReceiver(null, filter)
            DeviceBattery(
                battery = receiver?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1,
                scale = receiver?.getIntExtra("scale", 0) ?: -1,
                status = receiver?.getIntExtra("status", 0) ?: -1
            )
        }
    }

    @SuppressLint("HardwareIds")
    fun getAndroidID(): String {
        var androidId = Settings.Secure.getString(MobileQQ.getContext().contentResolver, "android_id")
        if (androidId == null) {
            val sb = StringBuilder()
            for (i in 0..15) {
                sb.append(Random.nextInt(10))
            }
            androidId = sb.toString()
        }
        return androidId
    }

    @Serializable
    data class DeviceBattery(
        val battery: Int,
        val scale: Int,
        val status: Int
    )
}