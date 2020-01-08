package com.davidkoudela.crucible.review;

/**
 *
 * @author dkoudela
 * @since 2019-03-27
 */
public interface ReviewVisitorWorker extends Runnable, ReviewVisitorCollector {
	boolean isRunning();
	ReviewVisitor getReviewVisitor();
}
