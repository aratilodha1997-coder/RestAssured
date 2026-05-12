package apitest;

import base.BaseTest;
import constants.ApiConstants;
import datafactory.CoverPhotoDataFactory;
import dataobject.CoverPhoto;
import endpoints.ApiRoutes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;

public class CreateCoverPhotoTest extends BaseTest {

    @Test(description = "Verify user is able to create cover photo successfully")

    public void create_coverphotos_Success() {

        CoverPhoto requestBody = CoverPhotoDataFactory.createCoverPhotoData();

        Response response = ApiUtils.postRequest(ApiRoutes.CREATE_COVER_PHOTO, requestBody);

        Assert.assertEquals(response.getStatusCode(), 200);
        CoverPhoto responseBody = response.as(CoverPhoto.class);
        Assert.assertEquals(responseBody.getId(), requestBody.getId());
        Assert.assertEquals(responseBody.getIdBook(), requestBody.getIdBook());
        Assert.assertEquals(responseBody.getUrl(), requestBody.getUrl());
        Assert.assertTrue(response.getHeader("Content-Type").contains(ApiConstants.CONTENT_TYPE));
    }

    @Test(description = "Verify that error message is displayed for invalid id")

    public void create_coverphotos_InvalidId() {
        CoverPhoto requestBody = CoverPhotoDataFactory.createInvalidCoverPhotoData();

        Response response = ApiUtils.postRequest(ApiRoutes.CREATE_COVER_PHOTO, requestBody);

        Assert.assertEquals(response.getStatusCode(), ApiConstants.BAD_REQUEST_STATUS_CODE);
        Assert.assertEquals(response.jsonPath().getString("title"), "One or more validation errors occurred.");
        Assert.assertTrue(response.asPrettyString().contains("could not be converted"));
        Assert.assertTrue(response.asPrettyString().contains("$.id"));
    }

}