package com.hackerkernel.user.sqrfactor.Constants;

public class ServerConstants {

    //BASE
    public static final String IMAGE_URL = "https://archsqr.in";             // without the forward slash at the end
    public static final String IMAGE_BASE_URL = "https://archsqr.in/";
    public static final String BASE_URL = "https://archsqr.in/api/";
    public static final String BASE_URL_COMPETITION = BASE_URL + "competition/";
    private static final String BASE_URL_EVENT = BASE_URL + "event/";

    public static final String LOGIN = BASE_URL + "login";

    // COMPETITIONS
    //Post
    public static final String WALL_QUESTION_ADD = BASE_URL_COMPETITION + "wall/question/add";


    //Get
    public static final String COMPETITION = BASE_URL + "competition";
    public static final String COMPETITION_DETAIL = COMPETITION + "/";


    // EVENTS
    //Post
    public static final String EVENT_ADD = BASE_URL_EVENT + "add";


    //Get
    public static final String EVENT_LIST = BASE_URL + "event/list";
    public static final String EVENT_DETAIL = BASE_URL + "event/";


    //JOBS

    // Post


    // Get


}
