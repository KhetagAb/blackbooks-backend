CREATE TABLE "user_profile"
(
    "id"        UUID             NOT NULL,
    "nick"      TEXT             NOT NULL,
    "name"      TEXT             NOT NULL,
    "age"       INTEGER          NOT NULL,
    "gender"    VARCHAR(6)       NULL
        CHECK (gender IN ('male', 'female')),
    "latitude"  DOUBLE PRECISION NOT NULL,
    "longitude" DOUBLE PRECISION NOT NULL
);
ALTER TABLE
    "user_profile"
    ADD PRIMARY KEY ("id");

CREATE TABLE "depository"
(
    "id"          UUID             NOT NULL,
    "name"        TEXT             NOT NULL,
    "address"     TEXT             NOT NULL,
    "description" TEXT             NOT NULL,
    "type"        TEXT             NOT NULL,
    "latitude"    DOUBLE PRECISION NOT NULL,
    "longitude"   DOUBLE PRECISION NOT NULL
);
ALTER TABLE
    "depository"
    ADD PRIMARY KEY ("id");

CREATE TABLE "transaction"
(
    "id"            UUID       NOT NULL,
    "user_id"       UUID       NOT NULL,
    "depository_id" UUID       NOT NULL,
    "book_id"       UUID       NOT NULL,
    "timestamp"     TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- toDo
    "type"          VARCHAR(255) CHECK
        ("type" IN (''))       NULL,                                          -- toDo
    "status"        VARCHAR(255)
        CHECK
            ("status" IN ('')) NULL                                           -- toDo
);
ALTER TABLE
    "transaction"
    ADD PRIMARY KEY ("id");

CREATE TABLE "book"
(
    "id"             UUID NOT NULL,
    "book_meta_id"   UUID NOT NULL,
    "user_id"        UUID NULL,
    "depository_id"  UUID NULL,
    "transaction_id" UUID NULL
);
ALTER TABLE
    "book"
    ADD PRIMARY KEY ("id");

CREATE TABLE "book_meta"
(
    "id"          UUID NOT NULL,
    "short_name"  TEXT NOT NULL,
    "description" TEXT NOT NULL,
    "author"      TEXT NOT NULL
);
ALTER TABLE
    "book_meta"
    ADD PRIMARY KEY ("id");

ALTER TABLE
    "transaction"
    ADD CONSTRAINT "transaction_userid_foreign" FOREIGN KEY ("user_id") REFERENCES "user_profile" ("id");
ALTER TABLE
    "transaction"
    ADD CONSTRAINT "transaction_depositoryid_foreign" FOREIGN KEY ("depository_id") REFERENCES "depository" ("id");
ALTER TABLE
    "book"
    ADD CONSTRAINT "book_userid_foreign" FOREIGN KEY ("user_id") REFERENCES "user_profile" ("id");
ALTER TABLE
    "book"
    ADD CONSTRAINT "book_depositoryid_foreign" FOREIGN KEY ("depository_id") REFERENCES "depository" ("id");
ALTER TABLE
    "book"
    ADD CONSTRAINT "book_bookmetaid_foreign" FOREIGN KEY ("book_meta_id") REFERENCES "book_meta" ("id");