package com.davidkoudela.crucible.review;

import com.cenqua.crucible.model.Review;
import com.cenqua.crucible.model.managers.LogItemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author dkoudela
 * @since 2019-03-27
 */
public class ReviewVisitorWorkerImpl implements ReviewVisitorWorker {
	private static final Logger log = LoggerFactory.getLogger(ReviewVisitorWorkerImpl.class);

	private LogItemManager logItemManager = null;
	private List<ReviewVisitorData> reviewList;
	private ReviewVisitor reviewVisitor = new ReviewVisitor(logItemManager);
	private boolean isRunning;

	public ReviewVisitorWorkerImpl(LogItemManager logItemManager, List<ReviewVisitorData> reviewList) {
		this.logItemManager = logItemManager;
		this.reviewList = reviewList;
		isRunning = true;
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public ReviewVisitor getReviewVisitor() {
		return reviewVisitor;
	}

	@Override
	public void run() {
		try {
			isRunning = true;
			reviewVisitor = new ReviewVisitor(logItemManager);
			reviewList.forEach(reviewVisitor::visit);
		} catch (Throwable e) {
			log.error("ReviewVisitorWorkerImpl caught an exception: " + e);
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error("ReviewVisitorWorkerImpl stacktrace: " + sw.toString());
		} finally {
			isRunning = false;
		}
	}

	@Override
	public ReviewVisitorData getTheNewestReviewVisitorDataByProject(String projectKey) {
		return reviewVisitor.getTheNewestReviewVisitorDataByProject(projectKey);
	}

	@Override
	public Collection<ReviewVisitorData> getReviewVisitorDataCollectionByProject(String projectKey) {
		return reviewVisitor.getReviewVisitorDataCollectionByProject(projectKey);
	}
}
