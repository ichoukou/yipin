package com.ytoxl.module.yipin.base.common.utils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * 本机系统信息
 *
 */

public class SystemUtils {

    //private static final Logger logger = Logger.getLogger(SystemUtils.class);

    // 获得系统属性集
    public static Properties props = System.getProperties();
    // 操作系统名称
    public static String OS_NAME = getPropertery("os.name");
    // 行分页符
    public static String OS_LINE_SEPARATOR = getPropertery("line.separator");
    // 文件分隔符号
    public static String OS_FILE_SEPARATOR = getPropertery("file.separator");

    // tomcat 容器的标识，在启动虚拟机时，通过 -D加入
    public static String TOMCAT_ID = getPropertery("tomcat.id");

    
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
    
    /**
     * 根据系统的类型获取本服务器的ip地址
     * <p/>
     * InetAddress inet = InetAddress.getLocalHost(); 但是上述代码在Linux下返回127.0.0.1。
     * 主要是在linux下返回的是/etc/hosts中配置的localhost的ip地址，
     * 而不是网卡的绑定地址。后来改用网卡的绑定地址，可以取到本机的ip地址：）：
     *
     * @throws java.net.UnknownHostException
     */
    public static InetAddress getSystemLocalIp() throws UnknownHostException {
        InetAddress inet = null;
        String osname = getSystemOSName();
        try {
            // 针对window系统
            if (osname.equalsIgnoreCase("Windows XP")) {
                inet = getWinLocalIp();
                // 针对linux系统
            } else if (osname.equalsIgnoreCase("Linux")) {
                inet = getUnixLocalIp();
            }
            if (null == inet) {
                throw new UnknownHostException("主机的ip地址未知");
            }
        } catch (SocketException e) {
            //logger.debug("获取本机ip错误" + e.getMessage());
            throw new UnknownHostException("获取本机ip错误" + e.getMessage());
        }
        return inet;
    }

    /**
     * 获取FTP的配置操作系统
     *
     * @return
     */
    public static String getSystemOSName() {
        // 获得系统属性集
        Properties props = System.getProperties();
        // 操作系统名称
        String osname = props.getProperty("os.name");
       // if (logger.isDebugEnabled()) {
            //logger.info("the ftp client system os Name " + osname);
        //}
        return osname;
    }

    /**
     * 获取属性的值
     *
     * @param propertyName
     * @return
     */
    public static String getPropertery(String propertyName) {
        return props.getProperty(propertyName);
    }

    /**
     * 获取window 本地ip地址
     *
     * @return
     * @throws java.net.UnknownHostException
     */
    private static InetAddress getWinLocalIp() throws UnknownHostException {
        InetAddress inet = InetAddress.getLocalHost();
        //logger.debug("本机的ip=" + inet.getHostAddress());
        return inet;
    }

    /**
     * 可能多多个ip地址只获取一个ip地址 获取Linux 本地IP地址
     *
     * @return
     * @throws java.net.SocketException
     */
    private static InetAddress getUnixLocalIp() throws SocketException {
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                .getNetworkInterfaces();
        InetAddress ip = null;
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) netInterfaces
                    .nextElement();
            ip = (InetAddress) ni.getInetAddresses().nextElement();
            if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                    && ip.getHostAddress().indexOf(":") == -1) {
                return ip;
            } else {
                ip = null;
            }
        }
        return null;
    }

    /**
     * 获取当前运行程序的内存信息
     *
     * @return
     */
    public static final String getRAMinfo() {
        Runtime rt = Runtime.getRuntime();
        return "RAM: " + rt.totalMemory() + " bytes total, " + rt.freeMemory()
                + " bytes free.";
    }

    public static String getLinuxIP() throws SocketException {
        // 根据网卡取本机配置的IP
        Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ipAddress = null;
        String ip = "";
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) netInterfaces
                    .nextElement();
            if (!ni.getName().equals("eth0")) {
                continue;
            } else {
                Enumeration<?> e2 = ni.getInetAddresses();
                while (e2.hasMoreElements()) {
                    ipAddress = (InetAddress) e2.nextElement();
                    if (ipAddress instanceof Inet6Address)
                        continue;
                    ip = ipAddress.getHostAddress();
                    //logger.debug("getLinuxIp:" + ip);
                }
                break;
            }
        }
        return ip;
    }

    /**
     * 取单号明细时的唯一Id
     *
     * @return
     */
    public static Long getLockId() {
        long _threadId = Thread.currentThread().getId();
        long _tomcatId = StringUtils.isEmpty(TOMCAT_ID) ? 0 : Long.valueOf(TOMCAT_ID);
        // 由于timestamp的前几位变更不是很频繁故把前几位去掉
        long _time = Long.valueOf(String.valueOf(System.currentTimeMillis()).substring(3));

        //lockId 算法
        Long lockId = _time * 1000 + _threadId * 100 + _tomcatId;
        return lockId;
    }

}
