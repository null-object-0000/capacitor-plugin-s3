import { registerPlugin } from '@capacitor/core';
import type { CreateS3Args } from './definitions';

export const CapacitorS3 = registerPlugin<S3Plugin>('CapacitorS3');

export interface S3Plugin {
    create(options: CreateS3Args): Promise<void>;
    putString(args: { id: string, key: string, value: string }): Promise<void>;
    getString(args: { id: string, key: string }): Promise<string>;
    doesObjectExist(args: { id: string, key: string }): Promise<boolean>;
    deleteObject(args: { id: string, key: string }): Promise<void>;
}
