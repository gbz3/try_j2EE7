# try_j2EE7

## JavaEE7

### 初期設定

#### プロジェクト作成

```
$ mvn archetype:generate \
    -DgroupId=com.example \
    -DartifactId=javaee7-app-2nd \
    -DarchetypeArtifactId=maven-archetype-webapp \
    -DarchetypeVersion=1.4 \
    -DinteractiveMode=false
$ cd javaee7-app-2nd
$ vi pom.xml
$
```

## Java EE 8

### 初期設定

#### プロジェクト作成

```
$ mvn archetype:generate \
    -DgroupId=com.example \
    -DartifactId=my-javaee8-app \
    -DarchetypeArtifactId=maven-archetype-webapp \
    -DarchetypeVersion=1.4 \
    -DinteractiveMode=false
$ cd my-javaee8-app
$ vi pom.xml
$
```

### Table

```sql
CREATE TABLE employees (
    employee_id     NUMBER(20)      PRIMARY KEY, -- 従業員ID (主キー)
    first_name      VARCHAR2(20)    NOT NULL,    -- 名 (必須)
    last_name       VARCHAR2(25)    NOT NULL,    -- 姓 (必須)
    email           VARCHAR2(100)   UNIQUE,      -- メールアドレス (一意)
    phone_number    VARCHAR2(20),                -- 電話番号
    hire_date       DATE            DEFAULT SYSDATE, -- 採用日 (デフォルトは現在日付)
    birth           NUMBER(7, 0),
    job_id          VARCHAR2(10)    NOT NULL,    -- 職務ID (必須)
    salary          NUMBER(8, 2)    CHECK (salary > 0), -- 給与 (0より大きいことをチェック)
    commission_pct  NUMBER(2, 2),                -- 歩合率
    manager_id      NUMBER(6),                   -- 上司のID
    department_id   NUMBER(4)                    -- 部署ID
);
```
