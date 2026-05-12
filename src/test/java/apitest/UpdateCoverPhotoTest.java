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

public class UpdateCoverPhotoTest extends BaseTest
{
    @Test(description = "Verify user is able to update cover photo successfully")
    public void update_coverphotos_Sucess()
    {
        CoverPhoto requestBody =
                CoverPhotoDataFactory.createCoverPhotoData();

        requestBody.setId(String.valueOf(2));
        requestBody.setIdBook(2);
        requestBody.setUrl("string");
        Response response = ApiUtils.putRequest(ApiRoutes.UPDATE_COVER_PHOTO, requestBody, 2);

        CoverPhoto responseBody = response.as(CoverPhoto.class);
        Assert.assertEquals(response.getStatusCode(), ApiConstants.SUCCESS_STATUS_CODE);
        Assert.assertEquals(responseBody.getId(), requestBody.getId());
        Assert.assertEquals(responseBody.getIdBook(), requestBody.getIdBook());
        Assert.assertEquals(responseBody.getUrl(), requestBody.getUrl());
        Assert.assertTrue(response.getHeader("Content-Type").contains(ApiConstants.CONTENT_TYPE));
        Assert.assertTrue(response.getTime() < 5000);
        Assert.assertNotNull(responseBody);

    }

    @Test(description = "Verify error message is displayed for invalid id")
    public void update_coverphotos_by_invalid_id()
    {
        CoverPhoto requestBody = CoverPhotoDataFactory.createInvalidCoverPhotoData();

        Response response = ApiUtils.putRequest(ApiRoutes.UPDATE_COVER_PHOTO, requestBody, 2);

        Assert.assertEquals(response.getStatusCode(), 400);
        Assert.assertEquals(response.jsonPath().getString("title"), "One or more validation errors occurred.");
        Assert.assertEquals(response.jsonPath().getString("status"), "400");
        Assert.assertTrue(response.asPrettyString().contains("The JSON value could not be converted"));
    }

    }
