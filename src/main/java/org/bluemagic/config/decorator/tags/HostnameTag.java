package org.bluemagic.config.decorator.tags;

import java.net.InetAddress;

public class HostnameTag extends SingleTag {

	@Override
	public String getValue() {
		
		if ((value == null) || (value.isEmpty())) {
			try {
				value = InetAddress.getLocalHost().getHostName();
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
		return prefix + value + suffix;
	}
}
