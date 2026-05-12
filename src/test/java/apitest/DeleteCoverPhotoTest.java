package apitest;

import base.BaseTest;
import constants.ApiConstants;
import endpoints.ApiRoutes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;

public class DeleteCoverPhotoTest extends BaseTest {
    @Test(description = "Verify user is able to delete cover photo successfully")
    public void delete_coverphotos_Sucess() {
        Response response = ApiUtils.deleteRequest(ApiRoutes.DELETE_COVER_PHOTO, 1);
        Assert.assertEquals(response.getStatusCode(), ApiConstants.SUCCESS_STATUS_CODE);
        Assert.assertTrue(response.asPrettyString().isEmpty());
    }

    @Test(description = "Verify error message is displayed for invalid id")
    public void delete_coverphotos_Invalid()
    {
        Response response = ApiUtils.deleteRequest(ApiRoutes.DELETE_COVER_PHOTO, 4444447789456L);
        Assert.assertEquals(response.getStatusCode(), ApiConstants.BAD_REQUEST_STATUS_CODE);
        Assert.assertEquals(response.jsonPath().getString("title"), "One or more validation errors occurred.");
        Assert.assertEquals(response.jsonPath().getString("status"), "400");
        Assert.assertTrue(response.asPrettyString().contains("is not valid"));
    }
}