const ROOT_URL = "http://localhost:8080/api";

const ANGULAR_APP_URL = "http://localhost:4200";

export const AppConstants = {
    APP_ROOT_URL: ROOT_URL,
    USER_API_URL: ROOT_URL + "/user",
    PROJECTS_API_URL: ROOT_URL + "/projects",
    LOGOUT_REDIRECT_URI: ANGULAR_APP_URL + "/login"
}