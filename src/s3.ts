import { BasicAWSCredentials } from './definitions';
import { CapacitorS3 } from './implementation';

export interface S3Interface {
    /**
     * 创建 S3 实例。
     * @function S3.create
     * @since 0.0.1
     */
    create(args: { credentials: BasicAWSCredentials, endpoint: string, bucketName: string }): Promise<S3>;
    /**
     * 将字符串上传到 S3。
     * @since 0.0.1
     */
    putString(args: { key: string, value: string }): Promise<void>;
    /**
     * 从 S3 获取字符串。
     * @since 0.0.1
     */
    getString(args: { key: string }): Promise<string>;
    /**
     * 检查对象是否存在。
     * @since 0.0.1
     */
    doesObjectExist(args: { key: string }): Promise<boolean>;
    /**
     * 删除对象。
     * @since 0.0.1
     */
    deleteObject(args: { key: string }): Promise<void>;
}

export class S3 implements S3Interface {
    /**
     * S3 实例的唯一标识
     * @since 0.0.1
     */
    private id: string;

    private constructor(id: string) {
        this.id = id;
    }
    
    /**
     * @deprecated Use AMap.create instead.
     */
    public create(_options: { credentials: BasicAWSCredentials, endpoint: string, bucketName: string }): Promise<S3> {
        throw new Error('Method not implemented.');
    }

    public static async create(options: { credentials: BasicAWSCredentials, endpoint: string, bucketName: string }): Promise<S3> {
        const id = Math.random().toString();

        const newS3 = new S3(id);

        await CapacitorS3.create({ ...options, id });

        return newS3;
    }

    public putString(args: { id: string; key: string; value: string; }): Promise<void> {
        return CapacitorS3.putString({ ...args, id: this.id });
    }

    public getString(args: { id: string; key: string; }): Promise<string> {
        return CapacitorS3.getString({ ...args, id: this.id });
    }

    public doesObjectExist(args: { id: string; key: string; }): Promise<boolean> {
        return CapacitorS3.doesObjectExist({ ...args, id: this.id });
    }

    public deleteObject(args: { id: string; key: string; }): Promise<void> {
        return CapacitorS3.deleteObject({ ...args, id: this.id });
    }

}