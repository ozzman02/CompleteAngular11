package com.taskmanager.constants;

public class ApplicationConstants {

    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";

    public static final String USERNAME_CLAIM = "username";

    public static final String AUTHORITIES_CLAIM = "authorities";

    public static final int SECONDS_TO_SLEEP = 1000;

    public static final String JWT_ISSUER = "Task Manager";

    public static final String JWT_SUBJECT = "JWT Token";

    public static final Integer JWT_EXPIRATION = 30000000;

    public static final String ANGULAR_APP_ORIGIN = "http://localhost:4200";

    public static final String ALL = "*";

    public static final Long MAX_AGE = 3600L;

    public static final String CSRF_REQUEST_ATTRIBUTE_NAME = "_csrf";

    public static final String USER_API_URL = "/api/user";

    public static final String PROJECTS_API_URL = "/api/projects";

    public static final String PROJECTS_API_SEARCH_URL = PROJECTS_API_URL + "/search/**";

    public static final String CLIENT_LOCATIONS_API_URL = "/api/clientlocations";

}
