package it.com.davidkoudela.crucible;

import com.davidkoudela.crucible.model.RepositoryAdminModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;
import com.atlassian.sal.api.ApplicationProperties;

import static org.junit.Assert.assertEquals;

@RunWith(AtlassianPluginsTestRunner.class)
public class RepositoryListRestApiWiredTest
{
    private final ApplicationProperties applicationProperties;
    //private final RepositoryAdminModel repositoryAdminModel;

    public RepositoryListRestApiWiredTest(ApplicationProperties applicationProperties/*, RepositoryAdminModel repositoryAdminModel*/)
    {
        this.applicationProperties = applicationProperties;
        //this.repositoryAdminModel = repositoryAdminModel;
    }

    @Test
    public void testMyName()
    {
        System.out.println("!!! GOTCHA !!!!");
//        assertEquals("!!! GOTCHA !!!!", true, false);
//        assertEquals("names do not match!", "myComponent:" + applicationProperties.getDisplayName(), advancedLdapUserManager.getClass().toString());
    }
}