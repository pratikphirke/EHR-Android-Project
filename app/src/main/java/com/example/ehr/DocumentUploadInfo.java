package com.example.ehr;

public class DocumentUploadInfo {


        public String imageName;

        public String imageURL;

        public DocumentUploadInfo() {

        }

        public DocumentUploadInfo(String name, String url) {

            this.imageName = name;
            this.imageURL= url;
        }

        public String getImageName() {
            return imageName;
        }

        public String getImageURL() {
            return imageURL;
        }

    }

