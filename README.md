
# Spring RAG (Spring Boot + Thymeleaf + MyBatis + pgvector + Spring AI)

## Import in IntelliJ
1. File → Open
2. Select this folder
3. IntelliJ detects Maven project
4. Wait for dependencies to download
5. Run RagApplication

## Before Run

CREATE DATABASE ragdb;

Enable pgvector

CREATE EXTENSION IF NOT EXISTS vector;

Create table

CREATE TABLE document_chunk (
    id BIGSERIAL PRIMARY KEY,
    content TEXT,
    embedding vector(1536)
);

Set OpenAI key in application.yml
spring.ai.openai.api-key=YOUR_KEY

Run RagApplication
Open http://localhost:8080
