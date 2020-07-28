package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import com.muheda.mhdsystemkit.sytemUtil.SystemKit;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.SEND_SMS;

public class PhoneUtils {

    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static TelephonyManager telephonyManager;

    static {
        telephonyManager = (TelephonyManager) SystemKit.getContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * Return whether the device is phone.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isPhone() {
        //noinspection ConstantConditions
        return telephonyManager.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    /**
     * Return the unique device id.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.READ_PHONE_STATE" />}</p>
     *
     * @return the unique device id
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(READ_PHONE_STATE)
    public static String getDeviceId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //noinspection ConstantConditions
            String iMei = telephonyManager.getImei();
            if (!TextUtils.isEmpty(iMei)) return iMei;
            String meid = telephonyManager.getMeid();
            return TextUtils.isEmpty(meid) ? "" : meid;

        }
        //noinspection ConstantConditions
        return telephonyManager.getDeviceId();
    }

    private static boolean permission;

    public static String getAppUnique() {
        if (!permission) {
            PackageManager pm = SystemKit.getContext().getPackageManager();
            permission = (PackageManager.PERMISSION_GRANTED ==
                    pm.checkPermission(Manifest.permission.READ_PHONE_STATE, PackageUtil.getCurrentVersion(SystemKit.getContext()).packageName));
        }
        if (permission && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return getDeviceId();
        } else {
            String m_szDevIDShort = "35" +
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
            return m_szDevIDShort;
        }
    }

    /**
     * Return the serial of device.
     *
     * @return the serial of device
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(READ_PHONE_STATE)
    public static String getSerial() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL;
    }

    /**
     * Return the IMEI.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.READ_PHONE_STATE" />}</p>
     *
     * @return the IMEI
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(READ_PHONE_STATE)
    public static String getIMEI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //noinspection ConstantConditions
            return telephonyManager.getImei();
        }
        //noinspection ConstantConditions
        return telephonyManager.getDeviceId();
    }

    /**
     * Return the MEID.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.READ_PHONE_STATE" />}</p>
     *
     * @return the MEID
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(READ_PHONE_STATE)
    public static String getMEID() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //noinspection ConstantConditions
            return telephonyManager.getMeid();
        }
        //noinspection ConstantConditions
        return telephonyManager.getDeviceId();
    }

    /**
     * Return the IMSI.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.READ_PHONE_STATE" />}</p>
     *
     * @return the IMSI
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(READ_PHONE_STATE)
    public static String getIMSI() {
        //noinspection ConstantConditions
        return telephonyManager.getSubscriberId();
    }

    /**
     * Returns the current phone type.
     *
     * @return the current phone type
     * <ul>
     * <li>{@link TelephonyManager#PHONE_TYPE_NONE}</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_GSM }</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_CDMA}</li>
     * <li>{@link TelephonyManager#PHONE_TYPE_SIP }</li>
     * </ul>
     */
    public static int getPhoneType() {
        //noinspection ConstantConditions
        return telephonyManager.getPhoneType();
    }

    /**
     * Return whether sim card state is ready.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isSimCardReady() {
        //noinspection ConstantConditions
        return telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    /**
     * Return the sim operator name.
     *
     * @return the sim operator name
     */
    public static String getSimOperatorName() {
        //noinspection ConstantConditions
        return telephonyManager.getSimOperatorName();
    }

    /**
     * Return the sim operator using mnc.
     *
     * @return the sim operator
     */
    public static String getSimOperatorByMnc() {
        //noinspection ConstantConditions
        String operator = telephonyManager.getSimOperator();
        if (operator == null) return "";
        switch (operator) {
            case "46000":
            case "46002":
            case "46007":
            case "46020":
                return "中国移动";
            case "46001":
            case "46006":
            case "46009":
                return "中国联通";
            case "46003":
            case "46005":
            case "46011":
                return "中国电信";
            default:
                return operator;
        }
    }

    /**
     * Return the phone status.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.READ_PHONE_STATE" />}</p>
     *
     * @return DeviceId = 99000311726612<br>
     * DeviceSoftwareVersion = 00<br>
     * Line1Number =<br>
     * NetworkCountryIso = cn<br>
     * NetworkOperator = 46003<br>
     * NetworkOperatorName = 中国电信<br>
     * NetworkType = 6<br>
     * PhoneType = 2<br>
     * SimCountryIso = cn<br>
     * SimOperator = 46003<br>
     * SimOperatorName = 中国电信<br>
     * SimSerialNumber = 89860315045710604022<br>
     * SimState = 5<br>
     * SubscriberId(IMSI) = 460030419724900<br>
     * VoiceMailNumber = *86<br>
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(READ_PHONE_STATE)
    public static String getPhoneStatus() {
        String str = "";
        //noinspection ConstantConditions
        str += "DeviceId(IMEI) = " + telephonyManager.getDeviceId() + "\n";
        str += "DeviceSoftwareVersion = " + telephonyManager.getDeviceSoftwareVersion() + "\n";
        str += "Line1Number = " + telephonyManager.getLine1Number() + "\n";
        str += "NetworkCountryIso = " + telephonyManager.getNetworkCountryIso() + "\n";
        str += "NetworkOperator = " + telephonyManager.getNetworkOperator() + "\n";
        str += "NetworkOperatorName = " + telephonyManager.getNetworkOperatorName() + "\n";
        str += "NetworkType = " + telephonyManager.getNetworkType() + "\n";
        str += "PhoneType = " + telephonyManager.getPhoneType() + "\n";
        str += "SimCountryIso = " + telephonyManager.getSimCountryIso() + "\n";
        str += "SimOperator = " + telephonyManager.getSimOperator() + "\n";
        str += "SimOperatorName = " + telephonyManager.getSimOperatorName() + "\n";
        str += "SimSerialNumber = " + telephonyManager.getSimSerialNumber() + "\n";
        str += "SimState = " + telephonyManager.getSimState() + "\n";
        str += "SubscriberId(IMSI) = " + telephonyManager.getSubscriberId() + "\n";
        str += "VoiceMailNumber = " + telephonyManager.getVoiceMailNumber() + "\n";
        return str;
    }

    /**
     * Skip to dial.
     *
     * @param phoneNumber The phone number.
     * @return {@code true}: operate successfully<br>{@code false}: otherwise
     */
    public static boolean dial(final String phoneNumber, final Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            return true;
        }
        return false;
    }

    /**
     * Make a phone call.
     * <p>Must hold {@code <uses-permission android:name="android.permission.CALL_PHONE" />}</p>
     *
     * @param phoneNumber The phone number.
     * @return {@code true}: operate successfully<br>{@code false}: otherwise
     */
    @RequiresPermission(CALL_PHONE)
    public static boolean call(final String phoneNumber, final Context context) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            return true;
        }
        return false;
    }

    /**
     * Send sms.
     *
     * @param phoneNumber The phone number.
     * @param content     The content.
     * @return {@code true}: operate successfully<br>{@code false}: otherwise
     */
    public static boolean sendSms(final String phoneNumber, final String content, final Context context) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        if (isIntentAvailable(intent, context)) {
            intent.putExtra("sms_body", content);
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            return true;
        }
        return false;
    }

    /**
     * Send sms silently.
     * <p>Must hold {@code <uses-permission android:name="android.permission.SEND_SMS" />}</p>
     *
     * @param phoneNumber The phone number.
     * @param content     The content.
     */
    @RequiresPermission(SEND_SMS)
    public static void sendSmsSilent(final String phoneNumber, final String content, final Context context) {
        if (TextUtils.isEmpty(content)) return;
        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent("send"), 0);
        SmsManager smsManager = SmsManager.getDefault();
        if (content.length() >= 70) {
            List<String> ms = smsManager.divideMessage(content);
            for (String str : ms) {
                smsManager.sendTextMessage(phoneNumber, null, str, sentIntent, null);
            }
        } else {
            smsManager.sendTextMessage(phoneNumber, null, content, sentIntent, null);
        }
    }

    private static boolean isIntentAvailable(final Intent intent, final Context context) {
        return context
                .getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
                .size() > 0;
    }


    /**
     * Return whether device is rooted.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
                "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return whether ADB is enabled.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAdbEnabled(final Context context) {
        return Settings.Secure.getInt(
                context.getContentResolver(),
                Settings.Global.ADB_ENABLED, 0
        ) > 0;
    }

    /**
     * Return the version name of device's system.
     *
     * @return the version name of device's system
     */
    public static String getSDKVersionName() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * Return version code of device's system.
     *
     * @return version code of device's system
     */
    public static int getSDKVersionCode() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * Return the android id of device.
     *
     * @return the android id of device
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID(final Context context) {
        String id = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
        return id == null ? "" : id;
    }

    /**
     * Return the MAC address.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />},
     * {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @return the MAC address
     */
    @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
    public static String getMacAddress(final Context context) {
        return getMacAddress(context, (String[]) null);
    }

    /**
     * Return the MAC address.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />},
     * {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @return the MAC address
     */
    @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
    public static String getMacAddress(final Context context, final String... excepts) {
        String macAddress = getMacAddressByWifiInfo(context);
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        macAddress = getMacAddressByNetworkInterface();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        macAddress = getMacAddressByInetAddress();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        macAddress = getMacAddressByFile();
        if (isAddressNotInExcepts(macAddress, excepts)) {
            return macAddress;
        }
        return "";
    }

    private static boolean isAddressNotInExcepts(final String address, final String... excepts) {
        if (excepts == null || excepts.length == 0) {
            return !"02:00:00:00:00:00".equals(address);
        }
        for (String filter : excepts) {
            if (address.equals(filter)) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static String getMacAddressByWifiInfo(final Context context) {
        try {
            WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null) return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByNetworkInterface() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni == null || !ni.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : macBytes) {
                        sb.append(String.format("%02x:", b));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static String getMacAddressByInetAddress() {
        try {
            InetAddress inetAddress = getInetAddress();
            if (inetAddress != null) {
                NetworkInterface ni = NetworkInterface.getByInetAddress(inetAddress);
                if (ni != null) {
                    byte[] macBytes = ni.getHardwareAddress();
                    if (macBytes != null && macBytes.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : macBytes) {
                            sb.append(String.format("%02x:", b));
                        }
                        return sb.substring(0, sb.length() - 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    private static InetAddress getInetAddress() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                // To prevent phone of xiaomi return "10.0.2.15"
                if (!ni.isUp()) continue;
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (hostAddress.indexOf(':') < 0) return inetAddress;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMacAddressByFile() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("getprop wifi.interface", false);
        if (result.result == 0) {
            String name = result.successMsg;
            if (name != null) {
                result = ShellUtils.execCmd("cat /sys/class/net/" + name + "/address", false);
                if (result.result == 0) {
                    String address = result.successMsg;
                    if (address != null && address.length() > 0) {
                        return address;
                    }
                }
            }
        }
        return "02:00:00:00:00:00";
    }

    /**
     * Return the manufacturer of the product/hardware.
     * <p>e.g. Xiaomi</p>
     *
     * @return the manufacturer of the product/hardware
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * Return the model of device.
     * <p>e.g. MI2SC</p>
     *
     * @return the model of device
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * Return an ordered list of ABIs supported by this device. The most preferred ABI is the first
     * element in the list.
     *
     * @return an ordered list of ABIs supported by this device
     */
    public static String[] getABIs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS;
        } else {
            if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
                return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
            }
            return new String[]{Build.CPU_ABI};
        }
    }

    @SuppressLint("MissingPermission")
    public static void phone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);

    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String[] getImei(Context context) {
        String[] iMeiArray = new String[2];
        if (Build.VERSION.SDK_INT >= 23) {
            String iMei1 = telephonyManager.getDeviceId(0);
            String iMei2 = telephonyManager.getDeviceId(1);
            iMeiArray[0] = iMei1;
            if (TextUtils.isEmpty(iMei2)) {
                iMeiArray[1] = "";
            } else {
                iMeiArray[1] = iMei2;
            }
        } else {
            String deviceId = telephonyManager.getDeviceId();
            iMeiArray[0] = deviceId;
            iMeiArray[1] = "";
        }
        return iMeiArray;
    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> ipAddr = networkInterface.getInetAddresses(); ipAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    // ipv4地址
                    if (!inetAddress.isLoopbackAddress()
                            && InternetAddressUtils.isIPv4Address(inetAddress
                            .getHostAddress())) {

                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return "";
    }
}
