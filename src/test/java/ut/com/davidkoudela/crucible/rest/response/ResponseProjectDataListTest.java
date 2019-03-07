package ut.com.davidkoudela.crucible.rest.response;

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
import java.util.ArrayList;
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
	public void testGsonSerialization() throws JsonParseException, IOException {
		List<ProjectProperties> projectPropertiesList = new ArrayList<ProjectProperties>();
		Project project = new Project();
		project.setName("Default");
		PermissionScheme permissionScheme = new PermissionScheme("MyOwnPermSchema");
		permissionScheme.setId(666);
		project.setPermissionScheme(permissionScheme);
		Date date = new Date(1551865213L);
		ReviewVisitorData reviewVisitorData = new ReviewVisitorData(1, "D-1", project, date, date);
		projectPropertiesList.add(new ProjectProperties(project, reviewVisitorData));
		ResponseProjectDataList responseProjectDataList = ResponseProjectFactory.constructResponseWithList("200", "operation succeeded", "", projectPropertiesList);
		Gson gson = new Gson();
		String responseProjectDataListAsString = gson.toJson(responseProjectDataList);

		assertNotNull(responseProjectDataListAsString);
		assertEquals("{\"response\":{\"code\":\"200\",\"message\":\"operation succeeded\",\"cause\":\"\"},\"projectList\":[{\"name\":\"Default\",\"storeRevisions\":false,\"permissionSchemeId\":666,\"moderatorEnabled\":true,\"lastUpdatedReview\":\"D-1\",\"lastUpdatedReviewDate\":\"1970-01-19T00:04:25.213+01:00\"}]}",
				responseProjectDataListAsString);
	}
}
