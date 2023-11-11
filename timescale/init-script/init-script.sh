#!/bin/bash


docker exec -it timescaledb bash
# Your initialization commands here
psql -U healthcare -d healthcare <<EOF
CREATE TABLE patients (
    time         TIMESTAMPTZ       NOT NULL,
    patient_id   VARCHAR(100)      PRIMARY KEY,
    name         VARCHAR(100)      NOT NULL,
    practitioner VARCHAR(100)      NOT NULL,
    operation    VARCHAR(100)      NOT NULL,
    location     VARCHAR(100)      NOT NULL,
    reason       VARCHAR(100)      NOT NULL,
    condition    VARCHAR(100)      NOT NULL,
    status       VARCHAR(100)      NOT NULL,
    data         JSONB
);

SELECT create_hypertable('patients', 'time');
EOF

