# s3

S3 Capacitor Plugin

## Install

```bash
npm install @snewbie/capacitor-s3
npx cap sync
```

## Usage

```typescript
import { S3 } from '@snewbie/capacitor-s3';

const s3Instance = await S3.create({
  credentials: {
    accessKey: 'my-key',
    secretKey: 'my-secret',
  },
  endpoint: 'https://s3.example.com',
  bucketName: 'my-bucket',
});
```

## API

<docgen-index>

* [`create(...)`](#create)
* [`putString(...)`](#putstring)
* [`getString(...)`](#getstring)
* [`doesObjectExist(...)`](#doesobjectexist)
* [`deleteObject(...)`](#deleteobject)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### create(...)

```typescript
create(args: { credentials: BasicAWSCredentials; endpoint: string; bucketName: string; }) => Promise<S3>
```

创建 S3 实例。

| Param      | Type                                                                                                                        |
| ---------- | --------------------------------------------------------------------------------------------------------------------------- |
| **`args`** | <code>{ credentials: <a href="#basicawscredentials">BasicAWSCredentials</a>; endpoint: string; bucketName: string; }</code> |

**Returns:** <code>Promise&lt;S3&gt;</code>

**Since:** 0.0.1

--------------------


### putString(...)

```typescript
putString(args: { key: string; value: string; }) => Promise<void>
```

将字符串上传到 S3。

| Param      | Type                                         |
| ---------- | -------------------------------------------- |
| **`args`** | <code>{ key: string; value: string; }</code> |

**Since:** 0.0.1

--------------------


### getString(...)

```typescript
getString(args: { key: string; }) => Promise<string>
```

从 S3 获取字符串。

| Param      | Type                          |
| ---------- | ----------------------------- |
| **`args`** | <code>{ key: string; }</code> |

**Returns:** <code>Promise&lt;string&gt;</code>

**Since:** 0.0.1

--------------------


### doesObjectExist(...)

```typescript
doesObjectExist(args: { key: string; }) => Promise<boolean>
```

检查对象是否存在。

| Param      | Type                          |
| ---------- | ----------------------------- |
| **`args`** | <code>{ key: string; }</code> |

**Returns:** <code>Promise&lt;boolean&gt;</code>

**Since:** 0.0.1

--------------------


### deleteObject(...)

```typescript
deleteObject(args: { key: string; }) => Promise<void>
```

删除对象。

| Param      | Type                          |
| ---------- | ----------------------------- |
| **`args`** | <code>{ key: string; }</code> |

**Since:** 0.0.1

--------------------


### Interfaces


#### BasicAWSCredentials

| Prop            | Type                | Since |
| --------------- | ------------------- | ----- |
| **`accessKey`** | <code>string</code> | 0.0.1 |
| **`secretKey`** | <code>string</code> | 0.0.1 |

</docgen-api>
