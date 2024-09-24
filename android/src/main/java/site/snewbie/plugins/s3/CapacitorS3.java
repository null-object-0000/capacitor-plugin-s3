package site.snewbie.plugins.s3;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import cn.hutool.core.lang.Singleton;
import lombok.Getter;

@Getter
public final class CapacitorS3 {
    private final AmazonS3 client;
    private final String bucketName;

    private CapacitorS3(BasicAWSCredentials credentials, String endpoint, String bucketName) {
        this.client = new AmazonS3Client(credentials, Region.getRegion(Regions.CN_NORTHWEST_1));
        this.client.setEndpoint(endpoint);
        this.bucketName = bucketName;
    }

    public static CapacitorS3 getInstance(String accessKey, String secretKey, String endpoint, String bucketName) {
        return Singleton.get(CapacitorS3.class, new BasicAWSCredentials(accessKey, secretKey), endpoint, bucketName);
    }

    public static CapacitorS3 getInstance(BasicAWSCredentials credentials, String endpoint, String bucketName) {
        return Singleton.get(CapacitorS3.class, credentials, endpoint, bucketName);
    }

}