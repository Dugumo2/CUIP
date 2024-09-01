package com.epoch.model.dto;


import lombok.Data;

//img_urls、topic_id、tittle、内容
@Data
public class UploadPost {

    private String imgUrls;
    private String topicId;
    private String title;
    private String content;
}
