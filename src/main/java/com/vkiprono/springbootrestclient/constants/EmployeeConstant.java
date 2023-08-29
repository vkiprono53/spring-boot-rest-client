package com.vkiprono.springbootrestclient.constants;

public class EmployeeConstant {
    public static final String EMPLOYEE_SAVE_ENDPOINT_URL = "http://localhost:9004/api/v1/employees";
    public static final String EMPLOYEE_UPDATE_ENDPOINT_URL = "http://localhost:9004/api/v1/employees";
    public static final String EMPLOYEE_DELETE_ENDPOINT_URL = "http://localhost:9004/api/v1/employees";
    public static final String EMPLOYEE_GET_BY_PNO_ENDPOINT_URL = "http://localhost:9004/api/v1/employees/{PNo}";


    public static final String USER_AUTHENTICATE_ENDPOINT_URL = "http://localhost:9004/api/v1/auth/authenticate";
    public static final String USER_REGISTER_ENDPOINT_URL = "http://localhost:9004/api/v1/auth/register";

    public static String STATUS_OK = "OK";
    public static String STATUS_NOK = "NOK";


    public static String USER_NOT_FOUND_CODE= "20009";
    public static String USER_NOT_FOUND_CODE_MESSAGE = "User Not found";

    public static String BEARER = "Bearer ";

}
