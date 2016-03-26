package ut.com.davidkoudela.crucible.rest.response;

import com.atlassian.fecru.user.FecruUser;
import com.cenqua.crucible.model.Project;
import com.davidkoudela.crucible.rest.response.ResponseProjectFactory;
import com.davidkoudela.crucible.rest.response.ResponseProjectOperation;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

/**
 * Description: Testing {@link ResponseProjectOperation}
 * Copyright (C) 2015 David Koudela
 *
 * @author dkoudela
 * @since 2015-05-28
 */
public class ResponseProjectOperationTest extends TestCase {
	@Test
	public void testGsonSerialization() throws JsonParseException, IOException {
		Project project = new Project();
		project.setName("Default");
		project.setAllowReviewersToJoin(true);
		project.setDefaultDuration(3600);
		project.setDefaultModerator(new FecruUser("dkoudela"));
		project.setDefaultObjectives("Default Objective");
		project.setId(666);
		project.setDefaultRepositoryName("Repository007");
		project.setProjKey("DEF");
		project.setStoreRevisions(true);
		ResponseProjectOperation responseProjectOperation = ResponseProjectFactory.constructResponse("200", "operation succeeded", "", project);
		Gson gson = new Gson();
		String responseProjectDataListAsString = gson.toJson(responseProjectOperation);

		assertNotNull(responseProjectDataListAsString);
		assertEquals("{\"relatedProject\":{\"id\":666,\"name\":\"Default\",\"projKey\":\"DEF\",\"defaultReviewerUsers\":[],\"allowedReviewerUsers\":[],\"defaultReviewerGroups\":[],\"allowedReviewerGroups\":[],\"defaultModerator\":{\"username\":\"dkoudela\",\"passwordResetTimeStamp\":0,\"failedLoginAttempts\":0},\"defaultRepositoryName\":\"Repository007\",\"allowReviewersToJoin\":true,\"storeRevisions\":true,\"suggestedReviewerConfig\":{\"allowSuggestion\":true,\"suggestRandom\":3,\"suggestLOCWeight\":4,\"suggestOpenWeight\":5,\"suggestMax\":5,\"suggestCommitsWeight\":3},\"defaultDuration\":3600,\"contentRoots\":[],\"defaultObjectives\":\"Default Objective\",\"moderatorDisabled\":false},\"response\":{\"code\":\"200\",\"message\":\"operation succeeded\",\"cause\":\"\"}}",
				responseProjectDataListAsString);
	}
}
