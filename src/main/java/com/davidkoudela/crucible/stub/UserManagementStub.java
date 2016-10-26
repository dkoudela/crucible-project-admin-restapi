package com.davidkoudela.crucible.stub;

/**
 * Description: User Management Stub interface for Integration test purposes.
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 19.10.2016
 */
public interface UserManagementStub {
	public void createAdminUserSession();
	public void createGroup(String group);
}
