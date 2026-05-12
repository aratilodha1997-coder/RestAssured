package apitest;

import base.BaseTest;
import constants.ApiConstants;
import endpoints.ApiRoutes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import dataobject.CoverPhoto;
import utils.ApiUtils;
import java.util.List;

public class GetCoverPhotoTest extends BaseTest {

    @Test(description =
            "Verify user is able to fetch all cover photos successfully")

    public void get_all_coverphotos_Success() {

        Response response = ApiUtils.getRequest(ApiRoutes.GET_ALL_COVER_PHOTOS);
        Assert.assertEquals(response.getStatusCode(), ApiConstants.SUCCESS_STATUS_CODE);
        Assert.assertEquals(response.getStatusLine(), ApiConstants.STATUS_LINE);
        Assert.assertTrue(response.getHeader("Content-Type").contains(ApiConstants.CONTENT_TYPE));
        List<CoverPhoto> coverPhotoList = response.jsonPath().getList("", CoverPhoto.class);
        Assert.assertNotNull(coverPhotoList);
        Assert.assertTrue(coverPhotoList.size() > 0);
        Assert.assertEquals(response.jsonPath().getInt("[0].id"), 1);
        Assert.assertEquals(coverPhotoList.get(0).getIdBook(),1);
        Assert.assertTrue(coverPhotoList.get(0).getUrl().contains("Book 1"));
    }


    @Test(description =
            "Verify user is able to fetch cover photo by ID successfully")

    public void get_coverphotos_by_ID_Success() {

        Response response = ApiUtils.getRequest(ApiRoutes.GET_COVER_PHOTOS_BY_ID);
        Assert.assertEquals(response.getStatusCode(), ApiConstants.SUCCESS_STATUS_CODE);
        Assert.assertEquals(response.getStatusLine(), ApiConstants.STATUS_LINE);
        Assert.assertTrue(response.getHeader("Content-Type").contains(ApiConstants.CONTENT_TYPE));
        CoverPhoto coverPhoto = response.as(CoverPhoto.class);
        Assert.assertNotNull(coverPhoto);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        Assert.assertEquals(coverPhoto.getIdBook(), 1);
        Assert.assertTrue(coverPhoto.getUrl().contains("Book 1"));
    }

    @Test(description = "Verify that error message is displayed for invalid id")

    public void get_coverphotos_by_invalid_id(){
        Response response = ApiUtils.getRequest(ApiRoutes.GET_INVALID_COVER_PHOTO);
        Assert.assertEquals(response.getStatusCode(), ApiConstants.BAD_REQUEST_STATUS_CODE);
        Assert.assertEquals(response.jsonPath().getString("title"), "One or more validation errors occurred.");
        Assert.assertEquals(response.jsonPath().getString("status"), "400");
        Assert.assertTrue(response.asPrettyString().contains("The value"));
    }

    @Test(description = "Verify that error message is displayed for content does not exists")

    public void get_coverphotos_NotExists(){
        Response response = ApiUtils.getRequest(ApiRoutes.GET_COVER_PHOTO_NOT_EXISTS);
        Assert.assertEquals(response.getStatusCode(), ApiConstants.NOT_FOUND_STATUS_CODE);
        Assert.assertEquals(response.jsonPath().getString("title"), "Not Found");
        Assert.assertEquals(response.jsonPath().getString("status"), "404");
    }
}