package com.davidkoudela.crucible.review;

import java.util.Collection;

/**
 *
 * @author dkoudela
 * @since 2019-03-27
 */
public interface ReviewVisitorCollector {
	ReviewVisitorData getTheNewestReviewVisitorDataByProject(String projectKey);
	Collection<ReviewVisitorData> getReviewVisitorDataCollectionByProject(String projectKey);
}
