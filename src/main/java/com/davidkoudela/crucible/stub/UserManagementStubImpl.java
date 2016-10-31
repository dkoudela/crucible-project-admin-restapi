package com.davidkoudela.crucible.stub;

import com.atlassian.fecru.user.EffectiveUserProvider;
import com.atlassian.fecru.user.FecruUser;
import com.atlassian.fecru.user.GroupName;
import com.cenqua.fisheye.LicensePolicyException;
import com.cenqua.fisheye.user.AdminUserConfig;
import com.cenqua.fisheye.user.UserLogin;
import com.cenqua.fisheye.user.UserManager;

import java.util.Set;

/**
 * Description: User Management Stub class for Integration test purposes.
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 19.10.2016
 */
@org.springframework.stereotype.Service("userManagementStub")
public class UserManagementStubImpl implements UserManagementStub {
	private final static String TESTUSER = "dkoudela";
	private final static String TESTDISPLAYNAME = "Koudela, David";
	private final static String TESTEMAIL = "dkoudela@example.com";

	private UserManager userManager;
	private AdminUserConfig adminUserConfig;
	private EffectiveUserProvider effectiveUserProvider;

	@org.springframework.beans.factory.annotation.Autowired
	public UserManagementStubImpl(UserManager userManager, AdminUserConfig adminUserConfig, EffectiveUserProvider effectiveUserProvider)
	{
		this.userManager = userManager;
		this.adminUserConfig = adminUserConfig;
		this.effectiveUserProvider = effectiveUserProvider;
	}

	@Override
	public void createAdminUserSession() {
		Set<String> admins = null;
		try {
			if (!this.userManager.existsEnabledUser(TESTUSER)) {
				this.userManager.addUser(TESTUSER, TESTDISPLAYNAME, TESTEMAIL, "admin", true);
				this.adminUserConfig.addUser(TESTUSER);
			}

			FecruUser fecruUser = this.userManager.getUser(TESTUSER);
			UserLogin userLogin = this.userManager.createTrustedUserLogin(fecruUser.getUsername(), true, false);
			//this.effectiveUserProvider.pushEffectivePrincipal(userLogin, fecruUser); // FECRU4.0
			this.effectiveUserProvider.pushEffectivePrincipal(userLogin, fecruUser, true); // FECRU4.1.1 & FECRU4.2
		} catch (LicensePolicyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createGroup(String group) {
		//String groupName = group; // FECRU4.1.1
		GroupName groupName = GroupName.create(group); // FECRU4.2
		if (!this.userManager.groupExists(groupName)) {
			this.userManager.addGroup(groupName);
		}
	}
}
