package datafactory;

import dataobject.CoverPhoto;

import java.util.Random;

    public class CoverPhotoDataFactory {


        static Random random = new Random();

        public static CoverPhoto createCoverPhotoData() {

            CoverPhoto coverPhoto = new CoverPhoto();
            coverPhoto.setId(String.valueOf(random.nextInt(1000)));
            coverPhoto.setIdBook(random.nextInt(500));
            coverPhoto.setUrl("https://testurl.com/book/" + random.nextInt(1000));

            return coverPhoto;
        }


        public static CoverPhoto createInvalidCoverPhotoData() {

            CoverPhoto coverPhoto = new CoverPhoto();
            String invalidId =
                    "Invalid_" + random.nextInt(1000);
            coverPhoto.setId(String.valueOf(invalidId));
            coverPhoto.setIdBook(random.nextInt(500));
            coverPhoto.setUrl("https://testurl.com/book/" + random.nextInt(1000));

            return coverPhoto;
        }
    }
