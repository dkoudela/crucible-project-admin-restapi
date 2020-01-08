package com.davidkoudela.crucible.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dkoudela
 * @since 2019-03-22
 */
public class ElapsedExecTime {
	private static final Logger log = LoggerFactory.getLogger(ElapsedExecTime.class);

	static private String message;
	static private String permaId;
	static private long start;
	static private long stop;

	static public void start(String message, String permaId) {
		ElapsedExecTime.permaId = permaId;
		ElapsedExecTime.message = message;
		log.debug("DaKo: " + message);
		start = System.currentTimeMillis();
	}

	static public void stop() {
		stop = System.currentTimeMillis();
		long timeElapsed = stop - start;
		if (100 < timeElapsed) {
			log.error("DaKo: permaId: " + permaId + " : " + message + " elapsed time: " + timeElapsed + " ms");
		} else {
			log.debug("DaKo: permaId: " + permaId + " : " + message + " elapsed time: " + timeElapsed + " ms");
		}
	}
}
