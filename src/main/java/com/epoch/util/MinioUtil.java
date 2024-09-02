package com.epoch.util;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epoch.util.SystemConstants.IMG_URLS_PREFIX;
import static java.io.File.separator;


@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    /**
     * 创建bucket桶
     */
    public void createBucket(String bucket) throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    /**
     * 上传文件
     */

    public String uploadFile(MultipartFile file, String bucket, String dirPath) throws IOException, ErrorResponseException, InvalidResponseException, XmlParserException, InternalException, ServerException, InvalidBucketNameException, InsufficientDataException, NoSuchAlgorithmException, InvalidKeyException {
        // 读取原始文件
        InputStream originalInputStream = file.getInputStream();
        BufferedImage originalImage = ImageIO.read(originalInputStream);

        // 确保 BufferedImage 不为 null
        if (originalImage == null) {
            throw new IOException("Failed to read image from input stream.");
        }

        // 将调整大小后的图像写入输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "webp", outputStream);


        //转换为webp格式
        WebpUtils.towebp(outputStream.toByteArray(), outputStream);

        // 确保数据已写入 outputStream
        if (outputStream.size() == 0) {
            throw new IOException("Failed to write image to output stream.");
        }

        // 生成新的文件路径
        String objectName = builderFilePath(dirPath.substring(0,dirPath.lastIndexOf(".")) + ".webp" );

        // 将调整大小后的图像上传到 MinIO
        byte[] resizedBytes = outputStream.toByteArray();
        InputStream resizedInputStream = new ByteArrayInputStream(resizedBytes);
        PutObjectOptions options = new PutObjectOptions(resizedBytes.length, -1);
        options.setContentType("image/webp");
        minioClient.putObject(bucket, objectName, resizedInputStream, options);

        System.out.println("successfully uploaded to `" + bucket + "` bucket.");
        resizedInputStream.close();

        return IMG_URLS_PREFIX + objectName;
    }



    /**
     * 调整图像大小
     * @param originalImage
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();
        return resizedImage;
    }


    /**
     * 按照时间构建文件路径
     * @param dirPath
     * @return
     */
    public String builderFilePath(String dirPath) {
        StringBuilder stringBuilder = new StringBuilder(50);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String todayStr = sdf.format(new Date());
        stringBuilder.append(todayStr).append("/");
        stringBuilder.append(dirPath);
        return stringBuilder.toString();
    }




    /**
     * 列出所有桶
     */
    public List<String> getAllBucket() throws Exception {
        List<Bucket> buckets = minioClient.listBuckets();
        return buckets.stream().map(Bucket::name).collect(Collectors.toList());
    }

    /**
     * 下载文件
     */
    public InputStream downLoad(String bucket, String objectName) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder().bucket(bucket).object(objectName).build()
        );
    }

    /**
     * 删除桶
     */
    public void deleteBucket(String bucket) throws Exception {
        minioClient.removeBucket(
                RemoveBucketArgs.builder().bucket(bucket).build()
        );
    }

    /**
     * 删除文件
     */
    public void deleteObject(String bucket, String objectName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder().bucket(bucket).object(objectName).build()
        );
    }

    /**
     * 获取文件url
     */
    public String getPreviewFileUrl(String bucketName, String objectName) throws Exception {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName).object(objectName).build();

        return IMG_URLS_PREFIX + objectName;
    }

}
