package com.davidkoudela.crucible.review;

import com.atlassian.fisheye.Visitor;
import com.cenqua.crucible.model.LogItem;
import com.cenqua.crucible.model.Review;
import com.cenqua.crucible.model.managers.LogItemManager;
import com.cenqua.crucible.model.managers.LogItemSearchCriteria;
import com.davidkoudela.crucible.stub.ElapsedExecTime;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

import static com.cenqua.crucible.model.managers.Order.DESC;

/**
 * Description: A Visitor class for collecting necessary information about Crucible Reviews.
 * Copyright (C) 2019 David Koudela
 *
 * @author dkoudela
 * @since 2019-03-05
 */
public class ReviewVisitor implements Visitor<ReviewVisitorData>, ReviewVisitorCollector {
	Multimap<String, ReviewVisitorData> reviewsPerProjectKey = ArrayListMultimap.create();
	Map<String, ReviewVisitorData> reviewsWithNewestUpdateDate = new HashMap<String, ReviewVisitorData>();
	private LogItemManager logItemManager = null;

	public ReviewVisitor(LogItemManager logItemManager) {
		this.logItemManager = logItemManager;
	}
/*
	@Override
	public void visit(Review review) {

		ElapsedExecTime.start("new ReviewVisitorData", review.getPermaId());
		ReviewVisitorData reviewVisitorData = new ReviewVisitorData(
				review.getId(),
				review.getPermaId(),
				review.getProject(),
				review.getCreateDate(),
				review.getCreateDate(),
				review.getState().getStateType().getReviewDataState()
		);
		ElapsedExecTime.stop();

		visit(reviewVisitorData);
	}
*/
	@Override
	public void visit(ReviewVisitorData reviewVisitorData) {
		Collection<String> reviewActions = new ArrayList<String>();
		reviewActions.add("REVIEW_STATE_CHANGE");
		reviewActions.add("REVIEW_CREATED");
		LogItemSearchCriteria logItemSearchCriteria = LogItemSearchCriteria.create().reviewIds(new Integer[]{reviewVisitorData.getId()})
				.actions(reviewActions)
				.order(DESC)
				.limit(1);
		ElapsedExecTime.start("getLogItems", reviewVisitorData.getPermaId());
		List<LogItem> logItems = logItemManager.getLogItems(logItemSearchCriteria);
		ElapsedExecTime.stop();
		ElapsedExecTime.start("setUpdateDate", reviewVisitorData.getPermaId());
		if (0 < logItems.size()) {
			reviewVisitorData.setUpdateDate(logItems.get(0).getCreateDate());
		}
		ElapsedExecTime.stop();

		ElapsedExecTime.start("getProjKey", reviewVisitorData.getPermaId());
		String projKey = reviewVisitorData.getProject().getProjKey();
		ElapsedExecTime.stop();
		ElapsedExecTime.start("reviewsPerProjectKey.put", reviewVisitorData.getPermaId());
		reviewsPerProjectKey.put(projKey, reviewVisitorData);
		ElapsedExecTime.stop();
		ElapsedExecTime.start("reviewsWithNewestUpdateDate.containsKey", reviewVisitorData.getPermaId());
		if (reviewsWithNewestUpdateDate.containsKey(projKey)) {
			ElapsedExecTime.stop();
			ElapsedExecTime.start("reviewsWithNewestUpdateDate.get", reviewVisitorData.getPermaId());
			ReviewVisitorData theNewestReviewVisitorData = reviewsWithNewestUpdateDate.get(projKey);
			ElapsedExecTime.stop();
			if (reviewVisitorData.getUpdateDate().after(theNewestReviewVisitorData.getUpdateDate())) {
				ElapsedExecTime.start("reviewsWithNewestUpdateDate.put", reviewVisitorData.getPermaId());
				reviewsWithNewestUpdateDate.put(projKey, reviewVisitorData);
				ElapsedExecTime.stop();
			}
		} else {
			ElapsedExecTime.stop();
			ElapsedExecTime.start("reviewsWithNewestUpdateDate.put", reviewVisitorData.getPermaId());
			reviewsWithNewestUpdateDate.put(projKey, reviewVisitorData);
			ElapsedExecTime.stop();
		}
	}

	@Override
	public ReviewVisitorData getTheNewestReviewVisitorDataByProject(String projectKey) {
		try {
			ReviewVisitorData reviewVisitorData = reviewsWithNewestUpdateDate.get(projectKey);
			if (null == reviewVisitorData)
				return new ReviewVisitorData();
			return reviewVisitorData;
		} catch (Exception e) {
			return new ReviewVisitorData();
		}
	}

	@Override
	public Collection<ReviewVisitorData> getReviewVisitorDataCollectionByProject(String projectKey) {
		try {
			Collection<ReviewVisitorData> reviewVisitorDataCollection = reviewsPerProjectKey.get(projectKey);
			if (null == reviewVisitorDataCollection)
				return new ArrayList<ReviewVisitorData>();
			return reviewVisitorDataCollection;
		} catch (Exception e) {
			return new ArrayList<ReviewVisitorData>();
		}
	}

	public static ReviewVisitor join(ReviewVisitor reviewVisitor1, ReviewVisitor reviewVisitor2) {
		ReviewVisitor reviewVisitorResult = new ReviewVisitor(reviewVisitor1.logItemManager);
		reviewVisitorResult.reviewsPerProjectKey.putAll(reviewVisitor1.reviewsPerProjectKey);
		reviewVisitorResult.reviewsPerProjectKey.putAll(reviewVisitor2.reviewsPerProjectKey);
		reviewVisitorResult.reviewsWithNewestUpdateDate.putAll(reviewVisitor1.reviewsWithNewestUpdateDate);
		for (String projKey : reviewVisitor2.reviewsWithNewestUpdateDate.keySet()) {
			ReviewVisitorData reviewVisitorData2 = reviewVisitor2.reviewsWithNewestUpdateDate.get(projKey);
			if (reviewVisitorResult.reviewsWithNewestUpdateDate.containsKey(projKey)) {
				ReviewVisitorData theNewestReviewVisitorData = reviewVisitorResult.reviewsWithNewestUpdateDate.get(projKey);
				if (reviewVisitorData2.getUpdateDate().after(theNewestReviewVisitorData.getUpdateDate())) {
					reviewVisitorResult.reviewsWithNewestUpdateDate.put(projKey, reviewVisitorData2);
				}
			} else {
				reviewVisitorResult.reviewsWithNewestUpdateDate.put(projKey, reviewVisitorData2);
			}
		}

		return reviewVisitorResult;
	}
}
