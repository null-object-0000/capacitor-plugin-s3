package site.snewbie.plugins.s3;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import lombok.NonNull;

@CapacitorPlugin(name = "CapacitorS3")
public class CapacitorS3Plugin extends Plugin {
    private final Map<String, CapacitorS3> s3Cache = new HashMap<>();

    @PluginMethod
    public void create(PluginCall call) {
        try {
            String id = call.getString("id");
            if (null == id || id.isEmpty()) {
                throw new IllegalArgumentException("id is required");
            }

            JSObject credentials = call.getObject("credentials");
            if (null == credentials) {
                throw new IllegalArgumentException("credentials object is missing");
            }

            String accessKey = credentials.getString("accessKey");
            String secretKey = credentials.getString("secretKey");

            String endpoint = call.getString("endpoint");
            String bucketName = call.getString("bucketName");
            if (StrUtil.isBlank(accessKey) || StrUtil.isBlank(secretKey) || StrUtil.isBlank(endpoint) || StrUtil.isBlank(bucketName)) {
                throw new IllegalArgumentException("accessKey, secretKey, endpoint and bucketName are required");
            }

            if (s3Cache.containsKey(id)) {
                call.resolve();
                return;
            }

            CapacitorS3 s3 = CapacitorS3.getInstance(new BasicAWSCredentials(accessKey, secretKey), endpoint, bucketName);
            s3Cache.put(id, s3);
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void putString(PluginCall call) {
        try {
            CapacitorS3 s3 = this.getS3(call);

            String key = call.getString("key");
            String value = call.getString("value");
            if (StrUtil.isBlank(key) || StrUtil.isBlank(value)) {
                throw new IllegalArgumentException("key and value are required");
            }

            ObjectMetadata metadata = this.getMetadata(call);

            PutObjectResult result = s3.getClient().putObject(new PutObjectRequest(s3.getBucketName(), key, IoUtil.toUtf8Stream(value), metadata));
            call.resolve(new JSObject()
                    .put("versionId", result.getVersionId())
                    .put("etag", result.getETag())
                    .put("contentMd5", result.getContentMd5())
            );
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void getString(PluginCall call) {
        try {
            CapacitorS3 s3 = this.getS3(call);

            String key = call.getString("key");
            if (StrUtil.isBlank(key)) {
                throw new IllegalArgumentException("key is required");
            }
            String value = s3.getClient().getObjectAsString(s3.getBucketName(), key);
            call.resolve(new JSObject().put("value", value));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
    }

    @PluginMethod
    public void doesObjectExist(PluginCall call) {
        try {
            CapacitorS3 s3 = this.getS3(call);

            String key = call.getString("key");
            if (StrUtil.isBlank(key)) {
                throw new IllegalArgumentException("key is required");
            }

            boolean exists = s3.getClient().doesObjectExist(s3.getBucketName(), key);
            call.resolve(new JSObject().put("exists", exists));
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
        call.resolve();
    }

    @PluginMethod
    public void deleteObject(PluginCall call) {
        try {
            CapacitorS3 s3 = this.getS3(call);

            String key = call.getString("key");
            if (StrUtil.isBlank(key)) {
                throw new IllegalArgumentException("key is required");
            }

            s3.getClient().deleteObject(s3.getBucketName(), key);
            call.resolve();
        } catch (Exception e) {
            call.reject(e.getMessage(), e);
        }
        call.resolve();
    }

    @NonNull
    private CapacitorS3 getS3(PluginCall call) {
        String id = call.getString("id");
        if (null == id || id.isEmpty()) {
            throw new IllegalArgumentException("id is required");
        }

        CapacitorS3 s3 = s3Cache.get(id);
        if (s3 == null) {
            throw new IllegalArgumentException("s3 not found");
        }

        return s3;
    }

    @NonNull
    private ObjectMetadata getMetadata(PluginCall call) {
        ObjectMetadata result = new ObjectMetadata();

        JSObject metadata = call.getObject("metadata");
        if (metadata == null) {
            return result;
        }

        return result;
    }

}
