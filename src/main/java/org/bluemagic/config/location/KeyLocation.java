package org.bluemagic.config.location;

import java.net.URI;
import java.util.Map;

import org.bluemagic.config.api.Location;
import org.bluemagic.config.api.MagicKey;

public class KeyLocation implements Location {

	private CommandLineLocation commandlineLocation;
	
	private LocalLocation localLocation;
	
	private RemoteLocation remoteLocation;
	
	public String locate(URI key, Map<MagicKey, Object> parameters) {
		return get(key, parameters);
	}

	public String get(URI key, Map<MagicKey, Object> parameters) {

		if (key.toASCIIString().startsWith("cmd:")) {
			return commandlineLocation.get(key, parameters);
			
		} else if (key.toASCIIString().startsWith("http")) {
			return remoteLocation.get(key, parameters);
			
		} else {
			return getLocalLocation().get(key, parameters);
		}
	}
	
	public boolean supports(URI uri) {
		
		return commandlineLocation.supports(uri) || localLocation.supports(uri) || remoteLocation.supports(uri);
	}

	public void setRemoteLocation(RemoteLocation remoteLocation) {
		this.remoteLocation = remoteLocation;
	}


	public RemoteLocation getRemoteLocation() {
		return remoteLocation;
	}

	public void setLocalLocation(LocalLocation localLocation) {
		this.localLocation = localLocation;
	}

	public LocalLocation getLocalLocation() {
		return localLocation;
	}

	public void setCommandlineLocation(CommandLineLocation commandlineLocation) {
		this.commandlineLocation = commandlineLocation;
	}

	public CommandLineLocation getCommandlineLocation() {
		return commandlineLocation;
	}
}
