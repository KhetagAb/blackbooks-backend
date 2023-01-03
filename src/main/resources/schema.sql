CREATE TABLE "user-profile"
(
    "id"        UUID             NOT NULL,
    "nick"      TEXT             NOT NULL,
    "name"      TEXT             NOT NULL,
    "age"       INTEGER          NOT NULL,
    "gender"    VARCHAR(255) CHECK
        ("gender" IN (''))       NULL,
    "latitude"  DOUBLE PRECISION NOT NULL,
    "longitude" DOUBLE PRECISION NOT NULL
);
ALTER TABLE
    "user-profile"
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
    "id"           UUID                           NOT NULL,
    "userId"       UUID                           NOT NULL,
    "depositoryId" UUID                           NOT NULL,
    "bookId"       UUID                           NOT NULL,
    "timestamp"    TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    "type"         VARCHAR(255) CHECK
        ("type" IN (''))                          NOT NULL,
    "status"       VARCHAR(255)
        CHECK
            ("status" IN (''))                    NOT NULL
);
ALTER TABLE
    "transaction"
    ADD PRIMARY KEY ("id");

CREATE TABLE "book"
(
    "id"            UUID NOT NULL,
    "bookMetaId"    UUID NOT NULL,
    "userId"        UUID NOT NULL,
    "depositoryId"  UUID NOT NULL,
    "transactionId" UUID NOT NULL
);
ALTER TABLE
    "book"
    ADD PRIMARY KEY ("id");

CREATE TABLE "book-meta"
(
    "id"          UUID NOT NULL,
    "shortName"   TEXT NOT NULL,
    "description" TEXT NOT NULL,
    "author"      TEXT NOT NULL
);
ALTER TABLE
    "book-meta"
    ADD PRIMARY KEY ("id");

ALTER TABLE
    "transaction"
    ADD CONSTRAINT "transaction_userid_foreign" FOREIGN KEY ("userId") REFERENCES "user-profile" ("id");
ALTER TABLE
    "transaction"
    ADD CONSTRAINT "transaction_depositoryid_foreign" FOREIGN KEY ("depositoryId") REFERENCES "depository" ("id");
ALTER TABLE
    "book"
    ADD CONSTRAINT "book_userid_foreign" FOREIGN KEY ("userId") REFERENCES "user-profile" ("id");
ALTER TABLE
    "book"
    ADD CONSTRAINT "book_depositoryid_foreign" FOREIGN KEY ("depositoryId") REFERENCES "depository" ("id");
ALTER TABLE
    "book"
    ADD CONSTRAINT "book_bookmetaid_foreign" FOREIGN KEY ("bookMetaId") REFERENCES "book-meta" ("id");