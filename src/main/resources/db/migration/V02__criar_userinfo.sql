CREATE TABLE user_info (
    id SERIAL PRIMARY KEY,
    iss VARCHAR(255),
    azp VARCHAR(255),
    aud VARCHAR(255),
    sub VARCHAR(255),
    email VARCHAR(255),
    email_verified BOOLEAN,
    name VARCHAR(255),
    picture VARCHAR(255),
    given_name VARCHAR(255),
    family_name VARCHAR(255),
    locale VARCHAR(255),
    iat BIGINT,
    exp BIGINT
);
