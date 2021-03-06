package it.com.davidkoudela.crucible.tests;

import com.davidkoudela.crucible.model.RepositoryAdminModel;

/**
 * Description: CRUD (Create Read Update Delete) Test Data
 *              for {@link RepositoryCRUDRestApiWiredTest}
 * Copyright (C) 2016 David Koudela
 *
 * @author dkoudela
 * @since 26.10.2016
 */
abstract public class RepositoryCRUDRestApiWiredAbstract {
	protected final static String CVS_NAME = "corp-cvs";
	protected final static String CVS_BASIC_REQUEST = "{\n" +
			"  \"cvs\" : { \"directory\" : \"c:\\\\\", \"charset\" : { \"charsetName\" : \"ISO-8859-1\" } },\n" +
			"  \"name\" : \"corp-cvs\",\n" +
			"  \"description\" : \"corp-cvs description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String CVS_BASIC_RESPONSE = "{" +
			"\"name\":\"corp-cvs\"," +
			"\"description\":\"corp-cvs description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{}" +
			"}," +
			"\"cvs\":{\"directory\":\"c:\\\\\",\"charset\":{\"charsetName\":\"ISO-8859-1\"}}" +
			"}";
	protected final static String CVS_BASIC_UPDATE_REQUEST = "{\n" +
			"  \"cvs\" : { \"directory\" : \"c:\\\\\", \"charset\" : { \"charsetName\" : \"ISO-8859-1\" } },\n" +
			"  \"name\" : \"corp-cvs\",\n" +
			"  \"description\" : \"corp-cvs description\",\n" +
			"  \"storeDiff\" : true,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String CVS_BASIC_UPDATE_RESPONSE = "{" +
			"\"name\":\"corp-cvs\"," +
			"\"description\":\"corp-cvs description\"," +
			"\"storeDiff\":true," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{}" +
			"}," +
			"\"cvs\":{\"directory\":\"c:\\\\\",\"charset\":{\"charsetName\":\"ISO-8859-1\"}}" +
			"}";
	protected final static String CVS_EXTRA_REQUEST = "{\n" +
			"  \"cvs\" : { \"directory\" : \"c:\\\\\", \"charset\" : { \"charsetName\" : \"ISO-8859-1\" } },\n" +
			"  \"name\" : \"corp-cvs\",\n" +
			"  \"description\" : \"corp-cvs description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false,\n" +
			"  \"extraOptions\" : {\n" +
			"    \"updateOptions\" : { \"disablePolling\" : false, \"pollingInterval\" : \"45s\", \n" +
			"                        \"cvs\" : { \"fullScanInterval\" : \"55min\", \n" +
			"                                  \"historyFile\" : \"./CVSROOT/history\", \n" +
			"                                  \"stripPrefix\" : \"opt\" } \n" +
			"    }\n" +
			"  }\n" +
			"}";
	protected final static String CVS_EXTRA_RESPONSE = "{" +
			"\"name\":\"corp-cvs\"," +
			"\"description\":\"corp-cvs description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"updateOptions\":{" +
			"\"pollingInterval\":\"45s\"," +
			"\"cvs\":{\"fullScanInterval\":\"55min\",\"historyFile\":\"./CVSROOT/history\",\"stripPrefix\":\"opt\"}" +
			"}," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{}" +
			"}," +
			"\"cvs\":{\"directory\":\"c:\\\\\",\"charset\":{\"charsetName\":\"ISO-8859-1\"}}" +
			"}";
	protected final static String CVS_EXTRA_UPDATE_REQUEST = "{\n" +
			"  \"cvs\" : { \"directory\" : \"c:\\\\\", \"charset\" : { \"charsetName\" : \"ISO-8859-1\" } },\n" +
			"  \"name\" : \"corp-cvs\",\n" +
			"  \"description\" : \"corp-cvs description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false,\n" +
			"  \"extraOptions\" : {\n" +
			"    \"updateOptions\" : { \"disablePolling\" : false, \"pollingInterval\" : \"55s\", \n" +
			"                        \"cvs\" : { \"fullScanInterval\" : \"65min\", \n" +
			"                                  \"historyFile\" : \"./CVSROOT/history\", \n" +
			"                                  \"stripPrefix\" : \"opt\" } \n" +
			"    }\n" +
			"  }\n" +
			"}";
	protected final static String CVS_EXTRA_UPDATE_RESPONSE = "{" +
			"\"name\":\"corp-cvs\"," +
			"\"description\":\"corp-cvs description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"updateOptions\":{" +
			"\"pollingInterval\":\"55s\"," +
			"\"cvs\":{\"fullScanInterval\":\"65min\",\"historyFile\":\"./CVSROOT/history\",\"stripPrefix\":\"opt\"}" +
			"}," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{}" +
			"}," +
			"\"cvs\":{\"directory\":\"c:\\\\\",\"charset\":{\"charsetName\":\"ISO-8859-1\"}}" +
			"}";

	protected final static String GIT_NAME = "corp-git";
	protected final static String GIT_BASIC_REQUEST = "{\n" +
			"  \"git\" : { \"location\" : \"git@git.example.com:plugins/crucible-plugin.git\", \n" +
			"          \"path\" : \"src\", \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\", \n" +
			"          \"renameDetection\" : \"COPIES\" },\n" +
			"  \"name\" : \"corp-git\",\n" +
			"  \"description\" : \"corp-git description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String GIT_BASIC_RESPONSE = "{" +
			"\"name\":\"corp-git\"," +
			"\"description\":\"corp-git description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"git@git.example.com:plugins/crucible-plugin.git\"}" +
			"}," +
			"\"git\":{" +
			"\"location\":\"git@git.example.com:plugins/crucible-plugin.git\"," +
			"\"path\":\"src\"," +
			"\"auth\":{}," +
			"\"blockSize\":1024," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"renameDetection\":\"COPIES\"" +
			"}" +
			"}";
	protected final static String GIT_BASIC_UPDATE_REQUEST = "{\n" +
			"  \"git\" : { \"location\" : \"git@git.example.com:plugins/crucible-plugin.git\", \n" +
			"          \"path\" : \"crs\", \"blockSize\" : 4096, \"commandTimeout\" : \"60 minute\", \n" +
			"          \"renameDetection\" : \"COPIES\" },\n" +
			"  \"name\" : \"corp-git\",\n" +
			"  \"description\" : \"corp-git description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String GIT_BASIC_UPDATE_RESPONSE = "{" +
			"\"name\":\"corp-git\"," +
			"\"description\":\"corp-git description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"git@git.example.com:plugins/crucible-plugin.git\"}" +
			"}," +
			"\"git\":{" +
			"\"location\":\"git@git.example.com:plugins/crucible-plugin.git\"," +
			"\"path\":\"crs\"," +
			"\"auth\":{}," +
			"\"blockSize\":4096," +
			"\"commandTimeout\":\"60 minute\"," +
			"\"renameDetection\":\"COPIES\"" +
			"}" +
			"}";
	protected final static String GIT_PASSWORD_REQUEST = "{\n" +
			"  \"git\" : { \"location\" : \"git@git.example.com:plugins/crucible-plugin.git\", \n" +
			"          \"auth\" : { \"password\" : \"topSecret\" }\n" +
			"  },\n" +
			"  \"name\" : \"corp-git\",\n" +
			"  \"description\" : \"corp-git description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String GIT_PASSWORD_RESPONSE = "{" +
			"\"name\":\"corp-git\"," +
			"\"description\":\"corp-git description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"git@git.example.com:plugins/crucible-plugin.git\"}" +
			"}," +
			"\"git\":{" +
			"\"location\":\"git@git.example.com:plugins/crucible-plugin.git\"," +
			"\"auth\":{}," +
			"\"renameDetection\":\"NONE\"" +
			"}" +
			"}";
	protected final static String GIT_KEYPAIR_REQUEST = "{\n" +
			"  \"git\" : { \"location\" : \"git@git.example.com:plugins/crucible-plugin.git\",\n" +
			"            \"auth\" : { \n" +
			"              \"publicKey\" : \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDFMbxbTmyNGz5TDcT9S/CB2+tTOWh/wqp2NMv35Y0Z5et8M0mNtg+jXbKlJngNnhghgx4K0Y3X7MutTkWkDsmyRnxcWKVvX3nPYttY2aj+vUN0tAI3tQ3/keXLN2FeNZW87LWTbJ0RoLIEiXpzkv9uBpBYvLi1G/f0fGiuLPr4XLLCsBRvOmDfqfj1SaMnIFWJvHOWdgK2x/RUX4jakLxwakLPMZvQ1TovZMPKoq/7ybbzAW2uaOzw2uIf+jee5yDUQfD36cklXuBbEnjDhtNMU4c2pVEXYrpq4ncQbfz+7iaKqA5C03eVfVG0FEN+vTcYOxwhge7dclutRAG69MCPGul5Wz2bCeHcW2rwUGgIyPUC/bF6S3Po5Y9NARrXSiUapgU4utMrxdvcR/BHM5iyQtyICoZl6422IR6QQZt4E7zHdO2I9xwCSYJKCX6ccvfeArFuOaxucD1FCGwh5EYsR2jHMkedNIF7uO42VXQH6x8ACrs8A0EgSftdjIwdFUpJvEIrLP8ACKEkqp5G//XDBwiG5Cnw9W6t1LUx2iV5kotKx0pgaX/wvhdBeHb4v1SickgaPeT/MHYNLWQjCuA8rBMLCMVCO5YtQaOlNB4hD5V4P4/GLvQU1u4zUn4bBihqcqh4EL8aLbsxHAPPZKNUbUm0ryFhN5PC0WaIQ3jaTQ== corp-git on FishEye\", \n" +
			"              \"protectedKey\" : \"-----BEGIN RSA protected KEY-----\\\\r\\\\n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\\\\r\\\\n-----END RSA protected KEY-----\" }\n" +
			"  },\n" +
			"  \"name\" : \"corp-git\",\n" +
			"  \"description\" : \"corp-git description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String GIT_KEYPAIR_RESPONSE = "{" +
			"\"name\":\"corp-git\"," +
			"\"description\":\"corp-git description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"git@git.example.com:plugins/crucible-plugin.git\"}" +
			"}," +
			"\"git\":{" +
			"\"location\":\"git@git.example.com:plugins/crucible-plugin.git\"," +
			"\"auth\":{}," +
			"\"renameDetection\":\"NONE\"" +
			"}" +
			"}";
	protected final static String GIT_BASIC_ENABLED_REQUEST = "{\n" +
			"  \"git\" : { \"location\" : \"git@git.example.com:plugins/crucible-plugin.git\", \n" +
			"          \"path\" : \"src\", \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\", \n" +
			"          \"renameDetection\" : \"COPIES\" },\n" +
			"  \"name\" : \"corp-git\",\n" +
			"  \"description\" : \"corp-git description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : true,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String GIT_BASIC_ENABLED_RESPONSE = "{" +
			"\"name\":\"corp-git\"," +
			"\"description\":\"corp-git description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":true,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"git@git.example.com:plugins/crucible-plugin.git\"}" +
			"}," +
			"\"git\":{" +
			"\"location\":\"git@git.example.com:plugins/crucible-plugin.git\"," +
			"\"path\":\"src\"," +
			"\"auth\":{}," +
			"\"blockSize\":1024," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"renameDetection\":\"COPIES\"" +
			"}" +
			"}";

	protected final static String MERCURIAL_NAME = "corp-hg";
	protected final static String MERCURIAL_BASIC_REQUEST = "{\n" +
			"  \"hg\" : { \"location\" : \"c:\\\\hg-repo\\\\crucible-plugin-hg\", \n" +
			"           \"path\" : \"src\", \"blockSize\" : 1024, \n" +
			"           \"commandTimeout\" : \"30 minute\", \n" +
			"           \"auth\" : { \"password\" : \"myPassword\" }\n" +
			"         },\n" +
			"  \"name\" : \"corp-hg\",\n" +
			"  \"description\" : \"corp-hg description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String MERCURIAL_BASIC_RESPONSE = "{" +
			"\"name\":\"corp-hg\"," +
			"\"description\":\"corp-hg description\","+
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"c:\\\\hg-repo\\\\crucible-plugin-hg\"}" +
			"}," +
			"\"hg\":{" +
			"\"location\":\"c:\\\\hg-repo\\\\crucible-plugin-hg\"," +
			"\"auth\":{}," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"blockSize\":1024" +
			"}" +
			"}";
	protected final static String MERCURIAL_BASIC_UPDATE_REQUEST = "{\n" +
			"  \"hg\" : { \"location\" : \"c:\\\\hg-repo\\\\crucible-plugin-hg\", \n" +
			"           \"path\" : \"src\", \"blockSize\" : 4096, \n" +
			"           \"commandTimeout\" : \"60 minute\", \n" +
			"           \"auth\" : { \"password\" : \"myPassword\" }\n" +
			"         },\n" +
			"  \"name\" : \"corp-hg\",\n" +
			"  \"description\" : \"corp-hg description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String MERCURIAL_BASIC_UPDATE_RESPONSE = "{" +
			"\"name\":\"corp-hg\"," +
			"\"description\":\"corp-hg description\","+
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"c:\\\\hg-repo\\\\crucible-plugin-hg\"}" +
			"}," +
			"\"hg\":{" +
			"\"location\":\"c:\\\\hg-repo\\\\crucible-plugin-hg\"," +
			"\"auth\":{}," +
			"\"commandTimeout\":\"60 minute\"," +
			"\"blockSize\":4096" +
			"}" +
			"}";
	protected final static String MERCURIAL_PASSWORD_REQUEST = "{\n" +
			"  \"hg\" : { \"location\" : \"c:\\\\hg-repo\\\\crucible-plugin-hg\", \n" +
			"           \"path\" : \"src\", \"blockSize\" : 1024, \n" +
			"           \"commandTimeout\" : \"30 minute\", \n" +
			"           \"auth\" : { \"password\" : \"myPassword\" }\n" +
			"         },\n" +
			"  \"name\" : \"corp-hg\",\n" +
			"  \"description\" : \"corp-hg description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String MERCURIAL_PASSWORD_RESPONSE = "{" +
			"\"name\":\"corp-hg\"," +
			"\"description\":\"corp-hg description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"c:\\\\hg-repo\\\\crucible-plugin-hg\"}" +
			"}," +
			"\"hg\":{" +
			"\"location\":\"c:\\\\hg-repo\\\\crucible-plugin-hg\"," +
			"\"auth\":{}," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"blockSize\":1024" +
			"}" +
			"}";
	protected final static String MERCURIAL_KEYPAIR_REQUEST = "{\n" +
			"  \"hg\" : { \"location\" : \"c:\\\\hg-repo\\\\crucible-plugin-hg\", \n" +
			"           \"path\" : \"src\", \"blockSize\" : 1024, \n" +
			"           \"commandTimeout\" : \"30 minute\", \n" +
			"            \"auth\" : { \n" +
			"              \"publicKey\" : \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQDFMbxbTmyNGz5TDcT9S/CB2+tTOWh/wqp2NMv35Y0Z5et8M0mNtg+jXbKlJngNnhghgx4K0Y3X7MutTkWkDsmyRnxcWKVvX3nPYttY2aj+vUN0tAI3tQ3/keXLN2FeNZW87LWTbJ0RoLIEiXpzkv9uBpBYvLi1G/f0fGiuLPr4XLLCsBRvOmDfqfj1SaMnIFWJvHOWdgK2x/RUX4jakLxwakLPMZvQ1TovZMPKoq/7ybbzAW2uaOzw2uIf+jee5yDUQfD36cklXuBbEnjDhtNMU4c2pVEXYrpq4ncQbfz+7iaKqA5C03eVfVG0FEN+vTcYOxwhge7dclutRAG69MCPGul5Wz2bCeHcW2rwUGgIyPUC/bF6S3Po5Y9NARrXSiUapgU4utMrxdvcR/BHM5iyQtyICoZl6422IR6QQZt4E7zHdO2I9xwCSYJKCX6ccvfeArFuOaxucD1FCGwh5EYsR2jHMkedNIF7uO42VXQH6x8ACrs8A0EgSftdjIwdFUpJvEIrLP8ACKEkqp5G//XDBwiG5Cnw9W6t1LUx2iV5kotKx0pgaX/wvhdBeHb4v1SickgaPeT/MHYNLWQjCuA8rBMLCMVCO5YtQaOlNB4hD5V4P4/GLvQU1u4zUn4bBihqcqh4EL8aLbsxHAPPZKNUbUm0ryFhN5PC0WaIQ3jaTQ== corp-git on FishEye\", \n" +
			"              \"protectedKey\" : \"-----BEGIN RSA protected KEY-----\\\\r\\\\n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\\\\r\\\\n-----END RSA protected KEY-----\" \n" +
			"            }\n" +
			"         },\n" +
			"  \"name\" : \"corp-hg\",\n" +
			"  \"description\" : \"corp-hg description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String MERCURIAL_KEYPAIR_RESPONSE = "{" +
			"\"name\":\"corp-hg\"," +
			"\"description\":\"corp-hg description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true,"+
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"URL\":\"c:\\\\hg-repo\\\\crucible-plugin-hg\"}" +
			"}," +
			"\"hg\":{" +
			"\"location\":\"c:\\\\hg-repo\\\\crucible-plugin-hg\"," +
			"\"auth\":{}," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"blockSize\":1024" +
			"}" +
			"}";

	protected final static String P4_NAME = "corp-p4";
	protected final static String P4_BASIC_REQUEST = "{\n" +
			"  \"p4\" : { \"server\" : \"ssl:p4.example.com\", \n" +
			"           \"path\" : \"//depot/crucible-plugin/\", \"port\" : 1667,\n" +
			"           \"username\" : \"p4user\", \"password\" : \"p4password\",\n" +
			"           \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\",\n" +
			"           \"caseSensitive\" : false, \"disableMutli\" : true,\n" +
			"           \"charset\" : { \"charsetName\" : \"ISO-8859-2\" }, \n" +
			"           \"commandTimeout\" : \"30 minute\", \n" +
			"           \"connectionsPerSecond\" : 20.0,\n" +
			"           \"fileLogLimit\" : 666,\n" +
			"           \"initialImport\" : false,\n" +
			"           \"skipLabels\" : true,\n" +
			"           \"startRevision\" : 1,\n" +
			"           \"unicode\" : true\n" +
			"         },\n" +
			"  \"name\" : \"corp-p4\",\n" +
			"  \"description\" : \"corp-p4 description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String P4_BASIC_RESPONSE = "{" +
			"\"name\":\"corp-p4\"," +
			"\"description\":\"corp-p4 description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"Host\":\"ssl:p4.example.com\",\"Port\":\"1667\",\"Path\":\"//depot/crucible-plugin/\"}" +
			"}," +
			"\"p4\":{" +
			"\"server\":\"ssl:p4.example.com\"," +
			"\"path\":\"//depot/crucible-plugin/\"," +
			"\"username\":\"p4user\"," +
			"\"blockSize\":1024," +
			"\"caseSensitive\":false," +
			"\"disableMutli\":true," +
			"\"charset\":{\"charsetName\":\"ISO-8859-2\"}," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"connectionsPerSecond\":20.0," +
			"\"fileLogLimit\":666," +
			"\"initialImport\":false," +
			"\"port\":1667," +
			"\"skipLabels\":true," +
			"\"startRevision\":1," +
			"\"unicode\":true" +
			"}" +
			"}";
	protected final static String P4_BASIC_UPDATE_REQUEST = "{\n" +
			"  \"p4\" : { \"server\" : \"ssl:p4.example.com\", \n" +
			"           \"path\" : \"//depot/crucible-plugin/\", \"port\" : 1667,\n" +
			"           \"username\" : \"p4user\", \"password\" : \"p4password\",\n" +
			"           \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\",\n" +
			"           \"caseSensitive\" : false, \"disableMutli\" : true,\n" +
			"           \"charset\" : { \"charsetName\" : \"ISO-8859-2\" }, \n" +
			"           \"commandTimeout\" : \"60 minute\", \n" +
			"           \"connectionsPerSecond\" : 20.0,\n" +
			"           \"fileLogLimit\" : 999,\n" +
			"           \"initialImport\" : false,\n" +
			"           \"skipLabels\" : false,\n" +
			"           \"startRevision\" : 1,\n" +
			"           \"unicode\" : true\n" +
			"         },\n" +
			"  \"name\" : \"corp-p4\",\n" +
			"  \"description\" : \"corp-p4 description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String P4_BASIC_UPDATE_RESPONSE = "{" +
			"\"name\":\"corp-p4\"," +
			"\"description\":\"corp-p4 description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{\"Host\":\"ssl:p4.example.com\",\"Port\":\"1667\",\"Path\":\"//depot/crucible-plugin/\"}" +
			"}," +
			"\"p4\":{" +
			"\"server\":\"ssl:p4.example.com\"," +
			"\"path\":\"//depot/crucible-plugin/\"," +
			"\"username\":\"p4user\"," +
			"\"blockSize\":1024," +
			"\"caseSensitive\":false," +
			"\"disableMutli\":true," +
			"\"charset\":{\"charsetName\":\"ISO-8859-2\"}," +
			"\"commandTimeout\":\"60 minute\"," +
			"\"connectionsPerSecond\":20.0," +
			"\"fileLogLimit\":999," +
			"\"initialImport\":false," +
			"\"port\":1667," +
			"\"skipLabels\":false," +
			"\"startRevision\":1," +
			"\"unicode\":true" +
			"}" +
			"}";

	protected final static String SVN_NAME = "corp-svn";
	protected final static String SVN_BASIC_REQUEST = "{\n" +
			"  \"svn\" : { \"url\" : \"svn.example.com/crucible-plugin/\", \"path\" : \"src\", \n" +
			"            \"username\" : \"user\", \"password\" : \"pwd\", \n" +
			"            \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\",\n" +
			"            \"connectionsPerSecond\" : 20.0, \n" +
			"            \"charset\" : { \"charsetName\" : \"ISO-8859-1\" }, \n" +
			"            \"accessCode\" : \"md5:dc0c08df1f3e80b599c90f53d7dd05ec\",\n" +
			"            \"startRevision\" : 1, \"initialImport\" : \"NO_IMPORT\", \n" +
			"            \"followBase\" : true, \"usingInbuiltSymbolicRules\" : false,\n" +
			"            \"trunks\" : [ { \"name\" : \"name1\", \n" +
			"                           \"regex\" : \"regex1\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                         { \"name\" : \"name2\", \n" +
			"                           \"regex\" : \"regex2\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix2\" } ],\n" +
			"            \"branches\" : [ { \"name\" : \"name1\", \n" +
			"                             \"regex\" : \"regex1\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                           { \"name\" : \"name2\", \n" +
			"                             \"regex\" : \"regex2\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix2\" }],\n" +
			"            \"tags\" : [ { \"name\" : \"name1\", \n" +
			"                         \"regex\" : \"regex1\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                       { \"name\" : \"name2\", \n" +
			"                         \"regex\" : \"regex2\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix2\" }]\n" +
			"          },\n" +
			"  \"name\" : \"corp-svn\",\n" +
			"  \"description\" : \"corp-svn description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String SVN_BASIC_RESPONSE = "{" +
			"\"name\":\"corp-svn\"," +
			"\"description\":\"corp-svn description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{}" +
			"}," +
			"\"svn\":{" +
			"\"url\":\"svn.example.com/crucible-plugin/\"," +
			"\"path\":\"src\"," +
			"\"username\":\"user\"," +
			"\"blockSize\":1024," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"connectionsPerSecond\":20.0," +
			"\"charset\":{\"charsetName\":\"ISO-8859-1\"}," +
			"\"accessCode\":\"md5:dc0c08df1f3e80b599c90f53d7dd05ec\"," +
			"\"startRevision\":1," +
			"\"initialImport\":\"NO_IMPORT\"," +
			"\"followBase\":true," +
			"\"usingInbuiltSymbolicRules\":false," +
			"\"trunks\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]," +
			"\"branches\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]," +
			"\"tags\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]" +
			"}" +
			"}";
	protected final static String SVN_BASIC_UPDATE_REQUEST = "{\n" +
			"  \"svn\" : { \"url\" : \"svn.example.com/crucible-plugin/\", \"path\" : \"src\", \n" +
			"            \"username\" : \"user\", \"password\" : \"pwd\", \n" +
			"            \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\",\n" +
			"            \"connectionsPerSecond\" : 20.0, \n" +
			"            \"charset\" : { \"charsetName\" : \"ISO-8859-1\" }, \n" +
			"            \"accessCode\" : \"md5:dc0c08df1f3e80b599c90f53d7dd05ec\",\n" +
			"            \"startRevision\" : 1, \"initialImport\" : \"NO_IMPORT\", \n" +
			"            \"followBase\" : false, \"usingInbuiltSymbolicRules\" : false,\n" +
			"            \"trunks\" : [ { \"name\" : \"rename1\", \n" +
			"                           \"regex\" : \"reregex1\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                         { \"name\" : \"name2\", \n" +
			"                           \"regex\" : \"regex2\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix2\" } ],\n" +
			"            \"branches\" : [ { \"name\" : \"name1\", \n" +
			"                             \"regex\" : \"regex1\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                           { \"name\" : \"name2\", \n" +
			"                             \"regex\" : \"regex2\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix2\" }],\n" +
			"            \"tags\" : [ { \"name\" : \"name1\", \n" +
			"                         \"regex\" : \"regex1\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                       { \"name\" : \"name2\", \n" +
			"                         \"regex\" : \"regex2\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix2\" }]\n" +
			"          },\n" +
			"  \"name\" : \"corp-svn\",\n" +
			"  \"description\" : \"corp-svn description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false\n" +
			"}";
	protected final static String SVN_BASIC_UPDATE_RESPONSE = "{" +
			"\"name\":\"corp-svn\"," +
			"\"description\":\"corp-svn description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[]," +
			"\"allowExcludes\":[]," +
			"\"simpleLinkers\":[]," +
			"\"advancedLinkers\":[]," +
			"\"hiddenDirectories\":[]," +
			"\"requiredGroups\":[]," +
			"\"checkoutURL\":{}" +
			"}," +
			"\"svn\":{" +
			"\"url\":\"svn.example.com/crucible-plugin/\"," +
			"\"path\":\"src\"," +
			"\"username\":\"user\"," +
			"\"blockSize\":1024," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"connectionsPerSecond\":20.0," +
			"\"charset\":{\"charsetName\":\"ISO-8859-1\"}," +
			"\"accessCode\":\"md5:dc0c08df1f3e80b599c90f53d7dd05ec\"," +
			"\"startRevision\":1," +
			"\"initialImport\":\"NO_IMPORT\"," +
			"\"followBase\":false," +
			"\"usingInbuiltSymbolicRules\":false," +
			"\"trunks\":[" +
			"{\"regex\":\"reregex1\",\"name\":\"rename1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]," +
			"\"branches\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]," +
			"\"tags\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]" +
			"}" +
			"}";
	protected final static String SVN_EXTRA_REQUEST = "{\n" +
			"  \"svn\" : { \"url\" : \"svn.example.com/crucible-plugin/\", \"path\" : \"src\", \n" +
			"            \"username\" : \"user\", \"password\" : \"pwd\", \n" +
			"            \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\",\n" +
			"            \"connectionsPerSecond\" : 20.0, \n" +
			"            \"charset\" : { \"charsetName\" : \"ISO-8859-1\" }, \n" +
			"            \"accessCode\" : \"md5:dc0c08df1f3e80b599c90f53d7dd05ec\",\n" +
			"            \"startRevision\" : 1, \"initialImport\" : \"NO_IMPORT\", \n" +
			"            \"followBase\" : true, \"usingInbuiltSymbolicRules\" : false,\n" +
			"            \"trunks\" : [ { \"name\" : \"name1\", \n" +
			"                           \"regex\" : \"regex1\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                         { \"name\" : \"name2\", \n" +
			"                           \"regex\" : \"regex2\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix2\" } ],\n" +
			"            \"branches\" : [ { \"name\" : \"name1\", \n" +
			"                             \"regex\" : \"regex1\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                           { \"name\" : \"name2\", \n" +
			"                             \"regex\" : \"regex2\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix2\" }],\n" +
			"            \"tags\" : [ { \"name\" : \"name1\", \n" +
			"                         \"regex\" : \"regex1\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                       { \"name\" : \"name2\", \n" +
			"                         \"regex\" : \"regex2\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix2\" }]\n" +
			"          },\n" +
			"  \"name\" : \"corp-svn\",\n" +
			"  \"description\" : \"corp-svn description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false,\n" +
			"  \"extraOptions\" : {\n" +
			"      \"usingDefaultsPermissions\" : false, \"allowAnon\" : true, \n" +
			"      \"allowLoggedUsers\" : true, \"changesetDiscussionsEnabled\" : true,\n" +
			"      \"watchesEnabled\" : true,\n" +
			"      \"allowIncludes\" : [ { \"path\" : \"test\", \n" +
			"                            \"caseSensitive\" : false }, \n" +
			"                          { \"path\" : \"testautomation\", \n" +
			"                            \"caseSensitive\" : false } ],\n" +
			"      \"allowExcludes\" : [ { \"path\" : \"var\", \n" +
			"                            \"caseSensitive\" : false }, \n" +
			"                          { \"path\" : \"log\", \n" +
			"                            \"caseSensitive\" : true } ],\n" +
			"      \"tarballSettings\" : { \"enabled\" : true, \"maxFiles\" : 1024, \n" +
			"                            \"excludes\" : [ \n" +
			"                                 { \"baseDirectory\" : \"test\", \n" +
			"                                   \"excludeSubdirs\" : false }, \n" +
			"                                 { \"baseDirectory\" : \"testautomation\", \n" +
			"                                   \"excludeSubdirs\" : true } ] },\n" +
			"      \"commitMessageSyntaxSettings\" : { \n" +
			"                         \"syntaxType\" : \"WIKI\", \n" +
			"                         \"wikiSyntaxStartDate\" : \"2015-08-17\" },\n" +
			"      \"maxIndexableSize\" : 5242880.0,\n" +
			"      \"updateOptions\" : { \"disablePolling\" : false, \n" +
			"                          \"pollingInterval\" : \"45s\" },\n" +
			"      \"simpleLinkers\" : [ { \"description\" : \"SLD1\", \n" +
			"                            \"href\" : \"http://example.com\", \n" +
			"                            \"regex\" : \"^example\" }, \n" +
			"                          { \"description\" : \"SLD2\", \n" +
			"                            \"href\" : \"http://foo.com\", \n" +
			"                            \"regex\" : \"^foo\" } ],\n" +
			"      \"advancedLinkers\" : [ { \"description\" : \"ALD1\", \n" +
			"                              \"syntaxDef\" : \"^[0-9]\" }, \n" +
			"                            { \"description\" : \"ALD1\", \n" +
			"                              \"syntaxDef\" : \"^[A-Z]\" } ],\n" +
			"      \"hiddenDirectories\" : [ { \"path\" : \"test\", \n" +
			"                                \"caseSensitive\" : false }, \n" +
			"                              { \"path\" : \"testautomation\", \n" +
			"                                \"caseSensitive\" : false } ],\n" +
			"      \"requiredGroups\" : [ \"testGroup01\", \"testGroup02\" ],\n" +
			"      \"showCheckoutURL\" : true,\n" +
			"      \"checkoutURL\" : { \"Host\" : \"checkout.example.com\", \n" +
			"                        \"Port\" : \"666\", \n" +
			"                        \"Path\" : \"/opt\" }\n" +
			"  }\n" +
			"}";
	protected final static String SVN_EXTRA_RESPONSE = "{" +
			"\"name\":\"corp-svn\"," +
			"\"description\":\"corp-svn description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":false," +
			"\"allowAnon\":true," +
			"\"allowLoggedUsers\":true," +
			"\"watchesEnabled\":true," +
			"\"changesetDiscussionsEnabled\":true," +
			"\"allowIncludes\":[" +
			"{\"path\":\"test\",\"caseSensitive\":false}," +
			"{\"path\":\"testautomation\",\"caseSensitive\":false}" +
			"]," +
			"\"allowExcludes\":[" +
			"{\"path\":\"var\",\"caseSensitive\":false}," +
			"{\"path\":\"log\",\"caseSensitive\":true}" +
			"]," +
			"\"tarballSettings\":{" +
			"\"maxFiles\":1024," +
			"\"enabled\":true," +
			"\"excludes\":[" +
			"{\"baseDirectory\":\"test\",\"excludeSubdirs\":false}," +
			"{\"baseDirectory\":\"testautomation\",\"excludeSubdirs\":true}" +
			"]" +
			"}," +
			"\"commitMessageSyntaxSettings\":{" +
			"\"syntaxType\":\"WIKI\"," +
			"\"wikiSyntaxStartDate\":\"Aug 17, 2015 12:00:00 AM\"" +
			"}," +
			"\"maxIndexableSize\":5242880," +
			"\"updateOptions\":{\"pollingInterval\":\"45s\"}," +
			"\"simpleLinkers\":[" +
			"{\"description\":\"SLD1\",\"href\":\"http://example.com\",\"regex\":\"^example\"}," +
			"{\"description\":\"SLD2\",\"href\":\"http://foo.com\",\"regex\":\"^foo\"}" +
			"]," +
			"\"advancedLinkers\":[" +
			"{\"description\":\"ALD1\",\"syntaxDef\":\"^[0-9]\"}," +
			"{\"description\":\"ALD1\",\"syntaxDef\":\"^[A-Z]\"}" +
			"]," +
			"\"hiddenDirectories\":[" +
			"{\"path\":\"test\",\"caseSensitive\":false}," +
			"{\"path\":\"testautomation\",\"caseSensitive\":false}" +
			"]," +
			"\"requiredGroups\":[ \"testgroup01\", \"testgroup02\" ]," +
			"\"showCheckoutURL\":true," +
			"\"checkoutURL\":{\"URL\":\"\"}"+
			"}," +
			"\"svn\":{" +
			"\"url\":\"svn.example.com/crucible-plugin/\"," +
			"\"path\":\"src\"," +
			"\"username\":\"user\"," +
			"\"blockSize\":1024," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"connectionsPerSecond\":20.0," +
			"\"charset\":{\"charsetName\":\"ISO-8859-1\"}," +
			"\"accessCode\":\"md5:dc0c08df1f3e80b599c90f53d7dd05ec\"," +
			"\"startRevision\":1," +
			"\"initialImport\":\"NO_IMPORT\"," +
			"\"followBase\":true," +
			"\"usingInbuiltSymbolicRules\":false," +
			"\"trunks\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]," +
			"\"branches\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]," +
			"\"tags\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]" +
			"}" +
			"		}";
	protected final static String SVN_EXTRA_UPDATE_REQUEST = "{\n" +
			"  \"svn\" : { \"url\" : \"svn.example.com/crucible-plugin/\", \"path\" : \"src\", \n" +
			"            \"username\" : \"user\", \"password\" : \"pwd\", \n" +
			"            \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\",\n" +
			"            \"connectionsPerSecond\" : 20.0, \n" +
			"            \"charset\" : { \"charsetName\" : \"ISO-8859-1\" }, \n" +
			"            \"accessCode\" : \"md5:dc0c08df1f3e80b599c90f53d7dd05ec\",\n" +
			"            \"startRevision\" : 1, \"initialImport\" : \"NO_IMPORT\", \n" +
			"            \"followBase\" : true, \"usingInbuiltSymbolicRules\" : false,\n" +
			"            \"trunks\" : [ { \"name\" : \"name1\", \n" +
			"                           \"regex\" : \"regex1\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                         { \"name\" : \"name2\", \n" +
			"                           \"regex\" : \"regex2\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix2\" } ],\n" +
			"            \"branches\" : [ { \"name\" : \"name1\", \n" +
			"                             \"regex\" : \"regex1\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                           { \"name\" : \"name2\", \n" +
			"                             \"regex\" : \"regex2\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix2\" }],\n" +
			"            \"tags\" : [ { \"name\" : \"name1\", \n" +
			"                         \"regex\" : \"regex1\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                       { \"name\" : \"name2\", \n" +
			"                         \"regex\" : \"regex2\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix2\" }]\n" +
			"          },\n" +
			"  \"name\" : \"corp-svn\",\n" +
			"  \"description\" : \"corp-svn description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false,\n" +
			"  \"extraOptions\" : {\n" +
			"      \"usingDefaultsPermissions\" : false, \"allowAnon\" : false, \n" +
			"      \"allowLoggedUsers\" : true, \"changesetDiscussionsEnabled\" : true,\n" +
			"      \"watchesEnabled\" : false,\n" +
			"      \"allowIncludes\" : [ { \"path\" : \"test\", \n" +
			"                            \"caseSensitive\" : false }, \n" +
			"                          { \"path\" : \"testautomation\", \n" +
			"                            \"caseSensitive\" : false } ],\n" +
			"      \"allowExcludes\" : [ { \"path\" : \"far\", \n" +
			"                            \"caseSensitive\" : false }, \n" +
			"                          { \"path\" : \"log\", \n" +
			"                            \"caseSensitive\" : true } ],\n" +
			"      \"tarballSettings\" : { \"enabled\" : true, \"maxFiles\" : 1024, \n" +
			"                            \"excludes\" : [ \n" +
			"                                 { \"baseDirectory\" : \"test\", \n" +
			"                                   \"excludeSubdirs\" : false }, \n" +
			"                                 { \"baseDirectory\" : \"testautomation\", \n" +
			"                                   \"excludeSubdirs\" : true } ] },\n" +
			"      \"commitMessageSyntaxSettings\" : { \n" +
			"                         \"syntaxType\" : \"WIKI\", \n" +
			"                         \"wikiSyntaxStartDate\" : \"2015-08-17\" },\n" +
			"      \"maxIndexableSize\" : 5242880.0,\n" +
			"      \"updateOptions\" : { \"disablePolling\" : false, \n" +
			"                          \"pollingInterval\" : \"45s\" },\n" +
			"      \"simpleLinkers\" : [ { \"description\" : \"SLD1\", \n" +
			"                            \"href\" : \"http://example.com\", \n" +
			"                            \"regex\" : \"^example\" }, \n" +
			"                          { \"description\" : \"SLD2\", \n" +
			"                            \"href\" : \"http://foo.com\", \n" +
			"                            \"regex\" : \"^foo\" } ],\n" +
			"      \"advancedLinkers\" : [ { \"description\" : \"ALD1\", \n" +
			"                              \"syntaxDef\" : \"^[0-9]\" }, \n" +
			"                            { \"description\" : \"ALD1\", \n" +
			"                              \"syntaxDef\" : \"^[A-Z]\" } ],\n" +
			"      \"hiddenDirectories\" : [ { \"path\" : \"test\", \n" +
			"                                \"caseSensitive\" : false }, \n" +
			"                              { \"path\" : \"testautomation\", \n" +
			"                                \"caseSensitive\" : false } ],\n" +
			"      \"requiredGroups\" : [ \"testGroup01\", \"testGroup02\" ],\n" +
			"      \"showCheckoutURL\" : true,\n" +
			"      \"checkoutURL\" : { \"Host\" : \"checkout.example.com\", \n" +
			"                        \"Port\" : \"666\", \n" +
			"                        \"Path\" : \"/opt\" }\n" +
			"  }\n" +
			"}";
	protected final static String SVN_EXTRA_UPDATE_RESPONSE = "{" +
			"\"name\":\"corp-svn\"," +
			"\"description\":\"corp-svn description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":false," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":true," +
			"\"watchesEnabled\":false," +
			"\"changesetDiscussionsEnabled\":true," +
			"\"allowIncludes\":[" +
			"{\"path\":\"test\",\"caseSensitive\":false}," +
			"{\"path\":\"testautomation\",\"caseSensitive\":false}" +
			"]," +
			"\"allowExcludes\":[" +
			"{\"path\":\"far\",\"caseSensitive\":false}," +
			"{\"path\":\"log\",\"caseSensitive\":true}" +
			"]," +
			"\"tarballSettings\":{" +
			"\"maxFiles\":1024," +
			"\"enabled\":true," +
			"\"excludes\":[" +
			"{\"baseDirectory\":\"test\",\"excludeSubdirs\":false}," +
			"{\"baseDirectory\":\"testautomation\",\"excludeSubdirs\":true}" +
			"]" +
			"}," +
			"\"commitMessageSyntaxSettings\":{" +
			"\"syntaxType\":\"WIKI\"," +
			"\"wikiSyntaxStartDate\":\"Aug 17, 2015 12:00:00 AM\"" +
			"}," +
			"\"maxIndexableSize\":5242880," +
			"\"updateOptions\":{\"pollingInterval\":\"45s\"}," +
			"\"simpleLinkers\":[" +
			"{\"description\":\"SLD1\",\"href\":\"http://example.com\",\"regex\":\"^example\"}," +
			"{\"description\":\"SLD2\",\"href\":\"http://foo.com\",\"regex\":\"^foo\"}" +
			"]," +
			"\"advancedLinkers\":[" +
			"{\"description\":\"ALD1\",\"syntaxDef\":\"^[0-9]\"}," +
			"{\"description\":\"ALD1\",\"syntaxDef\":\"^[A-Z]\"}" +
			"]," +
			"\"hiddenDirectories\":[" +
			"{\"path\":\"test\",\"caseSensitive\":false}," +
			"{\"path\":\"testautomation\",\"caseSensitive\":false}" +
			"]," +
			"\"requiredGroups\":[ \"testgroup01\", \"testgroup02\" ]," +
			"\"showCheckoutURL\":true," +
			"\"checkoutURL\":{\"URL\":\"\"}"+
			"}," +
			"\"svn\":{" +
			"\"url\":\"svn.example.com/crucible-plugin/\"," +
			"\"path\":\"src\"," +
			"\"username\":\"user\"," +
			"\"blockSize\":1024," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"connectionsPerSecond\":20.0," +
			"\"charset\":{\"charsetName\":\"ISO-8859-1\"}," +
			"\"accessCode\":\"md5:dc0c08df1f3e80b599c90f53d7dd05ec\"," +
			"\"startRevision\":1," +
			"\"initialImport\":\"NO_IMPORT\"," +
			"\"followBase\":true," +
			"\"usingInbuiltSymbolicRules\":false," +
			"\"trunks\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]," +
			"\"branches\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]," +
			"\"tags\":[" +
			"{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}" +
			"]" +
			"}" +
			"		}";
	protected final static String SVN_EXTRA_WITH_DEFAULTS_REQUEST = "{\n" +
			"  \"svn\" : { \"url\" : \"svn.example.com/crucible-plugin/\", \"path\" : \"src\", \n" +
			"            \"username\" : \"user\", \"password\" : \"pwd\", \n" +
			"            \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\",\n" +
			"            \"connectionsPerSecond\" : 20.0, \n" +
			"            \"charset\" : { \"charsetName\" : \"ISO-8859-1\" }, \n" +
			"            \"accessCode\" : \"md5:dc0c08df1f3e80b599c90f53d7dd05ec\",\n" +
			"            \"startRevision\" : 1, \"initialImport\" : \"NO_IMPORT\", \n" +
			"            \"followBase\" : true, \"usingInbuiltSymbolicRules\" : false,\n" +
			"            \"trunks\" : [ { \"name\" : \"name1\", \n" +
			"                           \"regex\" : \"regex1\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                         { \"name\" : \"name2\", \n" +
			"                           \"regex\" : \"regex2\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix2\" } ],\n" +
			"            \"branches\" : [ { \"name\" : \"name1\", \n" +
			"                             \"regex\" : \"regex1\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                           { \"name\" : \"name2\", \n" +
			"                             \"regex\" : \"regex2\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix2\" }],\n" +
			"            \"tags\" : [ { \"name\" : \"name1\", \n" +
			"                         \"regex\" : \"regex1\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                       { \"name\" : \"name2\", \n" +
			"                         \"regex\" : \"regex2\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix2\" }]\n" +
			"          },\n" +
			"  \"name\" : \"corp-svn\",\n" +
			"  \"description\" : \"corp-svn description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false,\n" +
			"  \"extraOptions\" : {\n" +
			"      \"usingDefaultsPermissions\" : true, \"allowAnon\" : false, \n" +
			"      \"allowLoggedUsers\" : false, \"changesetDiscussionsEnabled\" : false,\n" +
			"      \"watchesEnabled\" : false,\n" +
			"      \"allowIncludes\" : [ { \"path\" : \"test\", \n" +
			"                            \"caseSensitive\" : false }, \n" +
			"                          { \"path\" : \"testautomation\", \n" +
			"                            \"caseSensitive\" : false } ],\n" +
			"      \"allowExcludes\" : [ { \"path\" : \"var\", \n" +
			"                            \"caseSensitive\" : false }, \n" +
			"                          { \"path\" : \"log\", \n" +
			"                            \"caseSensitive\" : true } ],\n" +
			"      \"tarballSettings\" : { \"enabled\" : true, \"maxFiles\" : 1024, \n" +
			"                            \"excludes\" : [ \n" +
			"                                 { \"baseDirectory\" : \"test\", \n" +
			"                                   \"excludeSubdirs\" : false }, \n" +
			"                                 { \"baseDirectory\" : \"testautomation\", \n" +
			"                                   \"excludeSubdirs\" : true } ] },\n" +
			"      \"commitMessageSyntaxSettings\" : { \n" +
			"                         \"syntaxType\" : \"WIKI\", \n" +
			"                         \"wikiSyntaxStartDate\" : \"2015-08-17\" },\n" +
			"      \"maxIndexableSize\" : 5242880.0,\n" +
			"      \"updateOptions\" : { \"disablePolling\" : false, \n" +
			"                          \"pollingInterval\" : \"45s\" },\n" +
			"      \"simpleLinkers\" : [ { \"description\" : \"SLD1\", \n" +
			"                            \"href\" : \"http://example.com\", \n" +
			"                            \"regex\" : \"^example\" }, \n" +
			"                          { \"description\" : \"SLD2\", \n" +
			"                            \"href\" : \"http://foo.com\", \n" +
			"                            \"regex\" : \"^foo\" } ],\n" +
			"      \"advancedLinkers\" : [ { \"description\" : \"ALD1\", \n" +
			"                              \"syntaxDef\" : \"^[0-9]\" }, \n" +
			"                            { \"description\" : \"ALD1\", \n" +
			"                              \"syntaxDef\" : \"^[A-Z]\" } ],\n" +
			"      \"hiddenDirectories\" : [ { \"path\" : \"test\", \n" +
			"                                \"caseSensitive\" : false }, \n" +
			"                              { \"path\" : \"testautomation\", \n" +
			"                                \"caseSensitive\" : false } ],\n" +
			"      \"requiredGroups\" : [ \"testGroup01\", \"testGroup02\" ],\n" +
			"      \"showCheckoutURL\" : false,\n" +
			"      \"checkoutURL\" : { \"Host\" : \"checkout.example.com\", \n" +
			"                        \"Port\" : \"666\", \n" +
			"                        \"Path\" : \"/opt\" }\n" +
			"  }\n" +
			"}";
	protected final static String SVN_EXTRA_WITH_DEFAULTS_RESPONSE = "{" +
			"\"name\":\"corp-svn\"," +
			"\"description\":\"corp-svn description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
				"\"usingDefaultsPermissions\":true," +
				"\"allowAnon\":false," +
				"\"allowLoggedUsers\":false," +
				"\"watchesEnabled\":false," +
				"\"changesetDiscussionsEnabled\":false," +
				"\"allowIncludes\":[{\"path\":\"test\",\"caseSensitive\":false}," +
					"{\"path\":\"testautomation\",\"caseSensitive\":false}]," +
				"\"allowExcludes\":[{\"path\":\"var\",\"caseSensitive\":false}," +
					"{\"path\":\"log\",\"caseSensitive\":true}]," +
				"\"tarballSettings\":{" +
					"\"maxFiles\":1024,\"enabled\":true," +
					"\"excludes\":[{\"baseDirectory\":\"test\",\"excludeSubdirs\":false}," +
					"{\"baseDirectory\":\"testautomation\",\"excludeSubdirs\":true}]}," +
				"\"commitMessageSyntaxSettings\":{" +
					"\"syntaxType\":\"WIKI\"," +
					"\"wikiSyntaxStartDate\":\"Aug 17, 2015 12:00:00 AM\"" +
				"}," +
				"\"maxIndexableSize\":5242880," +
				"\"updateOptions\":{\"pollingInterval\":\"45s\"}," +
				"\"simpleLinkers\":[{\"description\":\"SLD1\",\"href\":\"http://example.com\",\"regex\":\"^example\"}," +
					"{\"description\":\"SLD2\",\"href\":\"http://foo.com\",\"regex\":\"^foo\"}]," +
				"\"advancedLinkers\":[{\"description\":\"ALD1\",\"syntaxDef\":\"^[0-9]\"}," +
					"{\"description\":\"ALD1\",\"syntaxDef\":\"^[A-Z]\"}]," +
				"\"hiddenDirectories\":[{\"path\":\"test\",\"caseSensitive\":false}," +
					"{\"path\":\"testautomation\",\"caseSensitive\":false}]," +
				"\"requiredGroups\":[\"testgroup01\",\"testgroup02\"]," +
				"\"showCheckoutURL\":false," +
				"\"checkoutURL\":{\"URL\":\"\"}}," +
				"\"svn\":{" +
					"\"url\":\"svn.example.com/crucible-plugin/\"," +
					"\"path\":\"src\"," +
					"\"username\":\"user\"," +
					"\"blockSize\":1024," +
					"\"commandTimeout\":\"30 minute\"," +
					"\"connectionsPerSecond\":20.0," +
					"\"charset\":{\"charsetName\":\"ISO-8859-1\"}," +
					"\"accessCode\":\"md5:dc0c08df1f3e80b599c90f53d7dd05ec\"," +
					"\"startRevision\":1," +
					"\"initialImport\":\"NO_IMPORT\"," +
					"\"followBase\":true," +
					"\"usingInbuiltSymbolicRules\":false," +
					"\"trunks\":[{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
						"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}]," +
					"\"branches\":[{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
						"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}]," +
					"\"tags\":[{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
						"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}]" +
				"}" +
			"}";
	protected final static String SVN_EXTRA_MISSING_PARAMS_REQUEST = "{\n" +
			"  \"svn\" : { \"url\" : \"svn.example.com/crucible-plugin/\", \"path\" : \"src\", \n" +
			"            \"username\" : \"user\", \"password\" : \"pwd\", \n" +
			"            \"blockSize\" : 1024, \"commandTimeout\" : \"30 minute\",\n" +
			"            \"connectionsPerSecond\" : 20.0, \n" +
			"            \"charset\" : { \"charsetName\" : \"ISO-8859-1\" }, \n" +
			"            \"accessCode\" : \"md5:dc0c08df1f3e80b599c90f53d7dd05ec\",\n" +
			"            \"startRevision\" : 1, \"initialImport\" : \"NO_IMPORT\", \n" +
			"            \"followBase\" : true, \"usingInbuiltSymbolicRules\" : false,\n" +
			"            \"trunks\" : [ { \"name\" : \"name1\", \n" +
			"                           \"regex\" : \"regex1\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                         { \"name\" : \"name2\", \n" +
			"                           \"regex\" : \"regex2\", \n" +
			"                           \"logicalPathPrefix\" : \"logicalPathPrefix2\" } ],\n" +
			"            \"branches\" : [ { \"name\" : \"name1\", \n" +
			"                             \"regex\" : \"regex1\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                           { \"name\" : \"name2\", \n" +
			"                             \"regex\" : \"regex2\", \n" +
			"                             \"logicalPathPrefix\" : \"logicalPathPrefix2\" }],\n" +
			"            \"tags\" : [ { \"name\" : \"name1\", \n" +
			"                         \"regex\" : \"regex1\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix1\" }, \n" +
			"                       { \"name\" : \"name2\", \n" +
			"                         \"regex\" : \"regex2\", \n" +
			"                         \"logicalPathPrefix\" : \"logicalPathPrefix2\" }]\n" +
			"          },\n" +
			"  \"name\" : \"corp-svn\",\n" +
			"  \"description\" : \"corp-svn description\",\n" +
			"  \"storeDiff\" : false,\n" +
			"  \"enabled\" : false,\n" +
			"  \"started\" : false,\n" +
			"  \"extraOptions\" : {\n" +
			"      \"usingDefaultsPermissions\" : true, \"allowAnon\" : false, \n" +
			"      \"allowLoggedUsers\" : false,\n" +
			"      \"allowIncludes\" : [ { \"path\" : \"test\", \n" +
			"                            \"caseSensitive\" : false }, \n" +
			"                          { \"path\" : \"testautomation\", \n" +
			"                            \"caseSensitive\" : false } ],\n" +
			"      \"allowExcludes\" : [ { \"path\" : \"var\", \n" +
			"                            \"caseSensitive\" : false }, \n" +
			"                          { \"path\" : \"log\", \n" +
			"                            \"caseSensitive\" : true } ],\n" +
			"      \"tarballSettings\" : { \"enabled\" : true, \"maxFiles\" : 1024, \n" +
			"                            \"excludes\" : [ \n" +
			"                                 { \"baseDirectory\" : \"test\", \n" +
			"                                   \"excludeSubdirs\" : false }, \n" +
			"                                 { \"baseDirectory\" : \"testautomation\", \n" +
			"                                   \"excludeSubdirs\" : true } ] },\n" +
			"      \"commitMessageSyntaxSettings\" : { \n" +
			"                         \"syntaxType\" : \"WIKI\", \n" +
			"                         \"wikiSyntaxStartDate\" : \"2015-08-17\" },\n" +
			"      \"maxIndexableSize\" : 5242880.0,\n" +
			"      \"updateOptions\" : { \"disablePolling\" : false, \n" +
			"                          \"pollingInterval\" : \"45s\" },\n" +
			"      \"simpleLinkers\" : [ { \"description\" : \"SLD1\", \n" +
			"                            \"href\" : \"http://example.com\", \n" +
			"                            \"regex\" : \"^example\" }, \n" +
			"                          { \"description\" : \"SLD2\", \n" +
			"                            \"href\" : \"http://foo.com\", \n" +
			"                            \"regex\" : \"^foo\" } ],\n" +
			"      \"advancedLinkers\" : [ { \"description\" : \"ALD1\", \n" +
			"                              \"syntaxDef\" : \"^[0-9]\" }, \n" +
			"                            { \"description\" : \"ALD1\", \n" +
			"                              \"syntaxDef\" : \"^[A-Z]\" } ],\n" +
			"      \"hiddenDirectories\" : [ { \"path\" : \"test\", \n" +
			"                                \"caseSensitive\" : false }, \n" +
			"                              { \"path\" : \"testautomation\", \n" +
			"                                \"caseSensitive\" : false } ],\n" +
			"      \"requiredGroups\" : [ \"testGroup01\", \"testGroup02\" ]\n" +
			"  }\n" +
			"}";
	protected final static String SVN_EXTRA_MISSING_PARAMS_RESPONSE = "{" +
			"\"name\":\"corp-svn\"," +
			"\"description\":\"corp-svn description\"," +
			"\"storeDiff\":false," +
			"\"enabled\":false,\"started\":false," +
			"\"extraOptions\":{" +
			"\"usingDefaultsPermissions\":true," +
			"\"allowAnon\":false," +
			"\"allowLoggedUsers\":false," +
			"\"allowIncludes\":[{\"path\":\"test\",\"caseSensitive\":false}," +
			"{\"path\":\"testautomation\",\"caseSensitive\":false}]," +
			"\"allowExcludes\":[{\"path\":\"var\",\"caseSensitive\":false}," +
			"{\"path\":\"log\",\"caseSensitive\":true}]," +
			"\"tarballSettings\":{" +
			"\"maxFiles\":1024,\"enabled\":true," +
			"\"excludes\":[{\"baseDirectory\":\"test\",\"excludeSubdirs\":false}," +
			"{\"baseDirectory\":\"testautomation\",\"excludeSubdirs\":true}]}," +
			"\"commitMessageSyntaxSettings\":{" +
			"\"syntaxType\":\"WIKI\"," +
			"\"wikiSyntaxStartDate\":\"Aug 17, 2015 12:00:00 AM\"" +
			"}," +
			"\"maxIndexableSize\":5242880," +
			"\"updateOptions\":{\"pollingInterval\":\"45s\"}," +
			"\"simpleLinkers\":[{\"description\":\"SLD1\",\"href\":\"http://example.com\",\"regex\":\"^example\"}," +
			"{\"description\":\"SLD2\",\"href\":\"http://foo.com\",\"regex\":\"^foo\"}]," +
			"\"advancedLinkers\":[{\"description\":\"ALD1\",\"syntaxDef\":\"^[0-9]\"}," +
			"{\"description\":\"ALD1\",\"syntaxDef\":\"^[A-Z]\"}]," +
			"\"hiddenDirectories\":[{\"path\":\"test\",\"caseSensitive\":false}," +
			"{\"path\":\"testautomation\",\"caseSensitive\":false}]," +
			"\"requiredGroups\":[\"testgroup01\",\"testgroup02\"]," +
			"\"checkoutURL\":{}}," +
			"\"svn\":{" +
			"\"url\":\"svn.example.com/crucible-plugin/\"," +
			"\"path\":\"src\"," +
			"\"username\":\"user\"," +
			"\"blockSize\":1024," +
			"\"commandTimeout\":\"30 minute\"," +
			"\"connectionsPerSecond\":20.0," +
			"\"charset\":{\"charsetName\":\"ISO-8859-1\"}," +
			"\"accessCode\":\"md5:dc0c08df1f3e80b599c90f53d7dd05ec\"," +
			"\"startRevision\":1," +
			"\"initialImport\":\"NO_IMPORT\"," +
			"\"followBase\":true," +
			"\"usingInbuiltSymbolicRules\":false," +
			"\"trunks\":[{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}]," +
			"\"branches\":[{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}]," +
			"\"tags\":[{\"regex\":\"regex1\",\"name\":\"name1\",\"logicalPathPrefix\":\"logicalPathPrefix1\"}," +
			"{\"regex\":\"regex2\",\"name\":\"name2\",\"logicalPathPrefix\":\"logicalPathPrefix2\"}]" +
			"}" +
			"}";
}
