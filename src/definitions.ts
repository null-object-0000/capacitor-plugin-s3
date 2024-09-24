export interface BasicAWSCredentials {
  /**
   * @since 0.0.1
   */
  accessKey: string;
  /**
   * @since 0.0.1
   */
  secretKey: string;
}

export interface CreateS3Args {
  /**
   * S3 实例的唯一标识
   * @since 0.0.1
   */
  id: string;
  /**
   * 访问密钥
   * @since 0.0.1
   */
  credentials: BasicAWSCredentials;
  /**
   * S3 对外服务的访问域名
   * @since 0.0.1
   */
  endpoint: string;
  /**
   * 存储桶名称
   * @since 0.0.1
   */
  bucketName: string;
}

export interface PutStringResult {
  /**
   * 新上传对象的可选版本 ID。仅当为对象上传到的存储桶启用了对象版本控制时，才会设置此字段。
   */
  versionId: string;
  /**
   * 新创建的对象的 ETag 值。
   */
  etag: string;
  /**
   * 返回在客户端计算的对象内容的 Base64 编码 MD5 哈希。如果禁用了 MD5 验证，并且调用方在发送 PutObjectRequest 时未在 ObjectMetadata 中提供 MD5 哈希，则此方法返回 null。
   */
  contentMd5: string;
}