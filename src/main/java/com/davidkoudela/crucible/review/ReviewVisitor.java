package com.davidkoudela.crucible.review;

import com.atlassian.fisheye.Visitor;
import com.cenqua.crucible.model.LogItem;
import com.cenqua.crucible.model.Review;
import com.cenqua.crucible.model.managers.LogItemManager;
import com.cenqua.crucible.model.managers.LogItemSearchCriteria;
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
public class ReviewVisitor implements Visitor<Review> {
	Multimap<String, ReviewVisitorData> reviewsPerProjectKey = ArrayListMultimap.create();
	Map<String, ReviewVisitorData> reviewsWithNewestUpdateDate = new HashMap<String, ReviewVisitorData>();
	private LogItemManager logItemManager = null;

	public ReviewVisitor(LogItemManager logItemManager) {
		this.logItemManager = logItemManager;
	}

	@Override
	public void visit(Review review) {

		ReviewVisitorData reviewVisitorData = new ReviewVisitorData(
				review.getId(),
				review.getPermaId(),
				review.getProject(),
				review.getCreateDate(),
				review.getCreateDate(),
				review.getState().getStateType().getReviewDataState()
		);

		LogItemSearchCriteria logItemSearchCriteria = LogItemSearchCriteria.create().reviewIds(new Integer[]{reviewVisitorData.getId()}).order(DESC).limit(1);
		List<LogItem> logItems = logItemManager.getLogItems(logItemSearchCriteria);
		if (0 < logItems.size()) {
			reviewVisitorData.setUpdateDate(logItems.get(0).getCreateDate());
		}

		String projKey = reviewVisitorData.getProject().getProjKey();
		reviewsPerProjectKey.put(projKey, reviewVisitorData);
		if (reviewsWithNewestUpdateDate.containsKey(projKey)) {
			ReviewVisitorData theNewestReviewVisitorData = reviewsWithNewestUpdateDate.get(projKey);
			if (reviewVisitorData.getUpdateDate().after(theNewestReviewVisitorData.getUpdateDate())) {
				reviewsWithNewestUpdateDate.put(projKey, reviewVisitorData);
			}
		} else {
			reviewsWithNewestUpdateDate.put(projKey, reviewVisitorData);
		}
	}

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
}
