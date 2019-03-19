package ut.com.davidkoudela.crucible.rest.response;

import com.atlassian.crucible.spi.data.ReviewData;
import com.cenqua.crucible.model.PermissionScheme;
import com.cenqua.crucible.model.Project;
import com.davidkoudela.crucible.rest.response.ProjectProperties;
import com.davidkoudela.crucible.rest.response.ResponseProjectFactory;
import com.davidkoudela.crucible.rest.response.ResponseProjectDataList;
import com.davidkoudela.crucible.review.ReviewVisitorData;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Description: Testing {@link ResponseProjectDataList}
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 2015-05-27
 */
public class ResponseProjectDataListTest extends TestCase {
	@Test
	public void testGsonSerializationShortProjectProperties() throws JsonParseException, IOException {
		List<ProjectProperties> projectPropertiesList = new ArrayList<ProjectProperties>();
		Project project = new Project();
		project.setName("Default");
		PermissionScheme permissionScheme = new PermissionScheme("MyOwnPermSchema");
		permissionScheme.setId(666);
		project.setPermissionScheme(permissionScheme);
		Date date = new Date(1551865213L);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String lastUpdatedReviewDate = dateFormat.format(date);
		ReviewVisitorData reviewVisitorData = new ReviewVisitorData(1, "D-1", project, date, date, ReviewData.State.Review);
		Collection<ReviewVisitorData> reviewVisitorDataCollection = new ArrayList<ReviewVisitorData>();
		reviewVisitorDataCollection.add(reviewVisitorData);
		projectPropertiesList.add(new ProjectProperties(project, null));
		ResponseProjectDataList responseProjectDataList = ResponseProjectFactory.constructResponseWithList("200", "operation succeeded", "", projectPropertiesList);
		Gson gson = new Gson();
		String responseProjectDataListAsString = gson.toJson(responseProjectDataList);

		assertNotNull(responseProjectDataListAsString);
		assertEquals("{\"response\":{\"code\":\"200\",\"message\":\"operation succeeded\",\"cause\":\"\"},\"projectList\":[{\"name\":\"Default\",\"storeRevisions\":false,\"permissionSchemeId\":666,\"moderatorEnabled\":true}]}",
				responseProjectDataListAsString);
	}

	@Test
	public void testGsonSerializationFullProjectProperties() throws JsonParseException, IOException {
		List<ProjectProperties> projectPropertiesList = new ArrayList<ProjectProperties>();
		Project project = new Project();
		project.setName("Default");
		PermissionScheme permissionScheme = new PermissionScheme("MyOwnPermSchema");
		permissionScheme.setId(666);
		project.setPermissionScheme(permissionScheme);
		Date date = new Date(1551865213L);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		String lastUpdatedReviewDate = dateFormat.format(date);
		ReviewVisitorData reviewVisitorData = new ReviewVisitorData(1, "D-1", project, date, date, ReviewData.State.Review);
		Collection<ReviewVisitorData> reviewVisitorDataCollection = new ArrayList<ReviewVisitorData>();
		reviewVisitorDataCollection.add(reviewVisitorData);
		projectPropertiesList.add(new ProjectProperties(project, 1));
		ResponseProjectDataList responseProjectDataList = ResponseProjectFactory.constructResponseWithList("200", "operation succeeded", "", projectPropertiesList);
		Gson gson = new Gson();
		String responseProjectDataListAsString = gson.toJson(responseProjectDataList);

		assertNotNull(responseProjectDataListAsString);
		assertEquals("{\"response\":{\"code\":\"200\",\"message\":\"operation succeeded\",\"cause\":\"\"},\"projectList\":[{\"name\":\"Default\",\"storeRevisions\":false,\"permissionSchemeId\":666,\"moderatorEnabled\":true,\"numberOfReviews\":1}]}",
				responseProjectDataListAsString);
	}
}
