package com.davidkoudela.crucible.review;

import com.cenqua.crucible.model.Review;
import com.cenqua.crucible.model.managers.LogItemManager;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author dkoudela
 * @since 2019-03-26
 */
public class DivideAndConquer {
	public static int DIVIDE_AND_CONQUER_BATCH_SIZE = 500;
	private static long WAIT_TIME_IN_MS_FOR_THREAD_COMPLETITION = 20;
	private LogItemManager logItemManager;
	private int numberOfThreads = 0;
	private List<List<ReviewVisitorData>> reviewPartitions;
	private List<ReviewVisitorWorker> threadList;
	private ReviewVisitor reviewVisitor;

	public DivideAndConquer(LogItemManager logItemManager, Collection<Review> reviewCollection) {
		this.logItemManager = logItemManager;
		reviewVisitor = new ReviewVisitor(logItemManager);
		if (reviewCollection.size() <= DIVIDE_AND_CONQUER_BATCH_SIZE)
			numberOfThreads = 1;
		else if (0 == (reviewCollection.size() % DIVIDE_AND_CONQUER_BATCH_SIZE)) {
			numberOfThreads = (reviewCollection.size() / DIVIDE_AND_CONQUER_BATCH_SIZE);
		} else {
			numberOfThreads = (reviewCollection.size() / DIVIDE_AND_CONQUER_BATCH_SIZE) + 1;
		}
		createBatches(reviewCollection);
	}

	public void run() throws InterruptedException {
		threadList = new ArrayList<ReviewVisitorWorker>();
		for (List<ReviewVisitorData> reviewList : reviewPartitions) {
			ReviewVisitorWorker reviewVisitorWorker = new ReviewVisitorWorkerImpl(logItemManager, reviewList);
			Thread reviewVisitorWorkerThread = new Thread(reviewVisitorWorker);
			threadList.add(reviewVisitorWorker);
			reviewVisitorWorkerThread.start();
		}

		while (0 < threadList.size()) {
			List<ReviewVisitorWorker> threadListStillRunning = new ArrayList<ReviewVisitorWorker>();
			for (ReviewVisitorWorker reviewVisitorWorker : threadList) {
				if (!reviewVisitorWorker.isRunning()) {
					reviewVisitor = ReviewVisitor.join(reviewVisitor, reviewVisitorWorker.getReviewVisitor());
				} else {
					threadListStillRunning.add(reviewVisitorWorker);
				}
			}
			threadList = threadListStillRunning;
			if (0 < threadList.size())
				Thread.sleep(WAIT_TIME_IN_MS_FOR_THREAD_COMPLETITION);
		}
	}

	public ReviewVisitor getReviewVisitor() {
		return reviewVisitor;
	}

	private void createBatches(Collection<Review> reviewCollection) {
		List<ReviewVisitorData> reviewList = new ArrayList<ReviewVisitorData>();
		for (Review review : reviewCollection) {
			ReviewVisitorData reviewVisitorData = new ReviewVisitorData(
					review.getId(),
					review.getPermaId(),
					review.getProject(),
					review.getCreateDate(),
					review.getCreateDate(),
					review.getState().getStateType().getReviewDataState()
			);
			reviewList.add(reviewVisitorData);
		}
		reviewPartitions = Lists.partition(reviewList, DIVIDE_AND_CONQUER_BATCH_SIZE);
	}
}
