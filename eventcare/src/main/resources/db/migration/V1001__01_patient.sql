CREATE TABLE patient (
  time        TIMESTAMPTZ         PRIMARY KEY, 
  event_time  BIGINT            NOT NULL,
  patient     TEXT                NOT NULL,
  doctor      TEXT                NOT NULL,
  operation   TEXT                NOT NULL,
  location    TEXT                NOT NULL,
  past        TEXT                NOT NULL,
  next        TEXT                NOT NULL,
  reason      TEXT                NOT NULL,
  condition   TEXT                NOT NULL,
  status      TEXT                NOT NULL,
  data        JSONB
);

-- CREATE INDEX ON patient (doctor, patient, event_time DESC,  time DESC);

SELECT create_hypertable('patient', 'time');
