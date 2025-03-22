CREATE TABLE tb_question (
    id serial PRIMARY KEY,
    text TEXT NOT NULL,
    response TEXT NOT NULL,
    difficulty_question VARCHAR(6) NOT NULL
);