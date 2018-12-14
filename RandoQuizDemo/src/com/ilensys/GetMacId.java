package com.ilensys;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetMacId {
	
	public static void main(String a[]) throws Exception {
		GetMacId g = new GetMacId();
		System.out.println(g.getMacAddress());
	}
	
	public String getMacAddress() throws Exception {
	    String macAddress = null;
	    String command = "ifconfig";

	    String osName = System.getProperty("os.name");
	    System.out.println("Operating System is " + osName);

	    if (osName.startsWith("Windows")) {
	        command = "ipconfig /all";
	    } else if (osName.startsWith("Linux") || osName.startsWith("Mac") || osName.startsWith("HP-UX")
	            || osName.startsWith("NeXTStep") || osName.startsWith("Solaris") || osName.startsWith("SunOS")
	            || osName.startsWith("FreeBSD") || osName.startsWith("NetBSD")) {
	        command = "ifconfig -a";
	    } else if (osName.startsWith("OpenBSD")) {
	        command = "netstat -in";
	    } else if (osName.startsWith("IRIX") || osName.startsWith("AIX") || osName.startsWith("Tru64")) {
	        command = "netstat -ia";
	    } else if (osName.startsWith("Caldera") || osName.startsWith("UnixWare") || osName.startsWith("OpenUNIX")) {
	        command = "ndstat";
	    } else {// Note: Unsupported system.
	        throw new Exception("The current operating system '" + osName + "' is not supported.");
	    }

	    Process pid = Runtime.getRuntime().exec(command);
	    BufferedReader in = new BufferedReader(new InputStreamReader(pid.getInputStream()));
	    Pattern p = Pattern.compile("([\\w]{1,2}(-|:)){5}[\\w]{1,2}");
	    while (true) {
	        String line = in.readLine();
	        System.out.println("line " + line);
	        if (line == null)
	            break;

	        Matcher m = p.matcher(line);
	        if (m.find()) {
	            macAddress = m.group();
	            break;
	        }
	    }
	    in.close();
	    return macAddress;
	}

}
