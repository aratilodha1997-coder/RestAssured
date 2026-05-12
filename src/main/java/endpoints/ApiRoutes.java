package endpoints;

public class ApiRoutes {

    public static final String GET_ALL_COVER_PHOTOS = "/api/v1/CoverPhotos";
    public static final String GET_COVER_PHOTOS_BY_ID ="api/v1/CoverPhotos/1";
    public static final String GET_INVALID_COVER_PHOTO = "/api/v1/CoverPhotos/qaq";
    public static final String GET_COVER_PHOTO_NOT_EXISTS = "/api/v1/CoverPhotos/50000";
    public static final String CREATE_COVER_PHOTO = "/api/v1/CoverPhotos";
    public static final String UPDATE_COVER_PHOTO = "/api/v1/CoverPhotos/{id}";
    public static final String DELETE_COVER_PHOTO = "/api/v1/CoverPhotos/{id}";
}