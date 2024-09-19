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
