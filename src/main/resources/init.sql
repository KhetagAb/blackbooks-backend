CREATE TABLE "User"
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
    "User"
    ADD PRIMARY KEY ("id");

CREATE TABLE "Depository"
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
    "Depository"
    ADD PRIMARY KEY ("id");

CREATE TABLE "Transaction"
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
    "Transaction"
    ADD PRIMARY KEY ("id");

CREATE TABLE "Book"
(
    "id"            UUID NOT NULL,
    "bookMetaId"    UUID NOT NULL,
    "userId"        UUID NOT NULL,
    "depositoryId"  UUID NOT NULL,
    "transactionId" UUID NOT NULL
);
ALTER TABLE
    "Book"
    ADD PRIMARY KEY ("id");

CREATE TABLE "BookMeta"
(
    "id"          UUID NOT NULL,
    "shortName"   TEXT NOT NULL,
    "description" TEXT NOT NULL,
    "author"      TEXT NOT NULL
);
ALTER TABLE
    "BookMeta"
    ADD PRIMARY KEY ("id");

ALTER TABLE
    "Transaction"
    ADD CONSTRAINT "transaction_userid_foreign" FOREIGN KEY ("userId") REFERENCES "User" ("id");
ALTER TABLE
    "Transaction"
    ADD CONSTRAINT "transaction_depositoryid_foreign" FOREIGN KEY ("depositoryId") REFERENCES "Depository" ("id");
ALTER TABLE
    "Book"
    ADD CONSTRAINT "book_userid_foreign" FOREIGN KEY ("userId") REFERENCES "User" ("id");
ALTER TABLE
    "Book"
    ADD CONSTRAINT "book_depositoryid_foreign" FOREIGN KEY ("depositoryId") REFERENCES "Depository" ("id");
ALTER TABLE
    "Book"
    ADD CONSTRAINT "book_bookmetaid_foreign" FOREIGN KEY ("bookMetaId") REFERENCES "BookMeta" ("id");