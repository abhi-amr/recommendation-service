# 🎬 Movie Recommendation Service

A production-ready **Spring Boot** microservice that delivers personalized movie recommendations using a **Neo4j graph database**. Supports multiple recommendation strategies — collaborative filtering, item-based filtering, and a hybrid approach — built to scale with the [MovieLens 32M dataset](https://grouplens.org/datasets/movielens/32m/).

---

## 🚀 Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java · Spring Boot |
| Database | Neo4j (Graph DB) |
| Algorithms | Collaborative Filtering · Item-Based Filtering · Hybrid |
| Dataset | MovieLens 32M (GroupLens) |
| Build Tool | Maven (Maven Wrapper) |
| API Testing | Postman |

---

## 💡 How It Works

Movies and users are stored as **nodes** in the Neo4j graph, with user ratings as **`RATED` relationships**. The service exposes three recommendation strategies:

| Strategy | Description |
|---|---|
| **Collaborative Filtering** | Finds users with similar taste and recommends movies they liked that the target user hasn't seen |
| **Item-Based Filtering** | Given a movie, recommends similar movies based on shared rating patterns |
| **Hybrid** | Combines collaborative and item-based signals for more robust recommendations |

All recommendation responses return a list of:

```json
{
  "movieId": 119667,
  "title": "Inception",
  "genres": "Action|Sci-Fi|Thriller",
  "averageRating": 4.2
}
```

---

## ✨ Features

- 🤝 **Collaborative filtering** — user-to-user similarity based recommendations
- 🎥 **Item-based filtering** — movie-to-movie similarity recommendations
- 🔀 **Hybrid recommendations** — best of both worlds
- 📈 **Trending movies** — surface globally popular movies
- 🔌 **Clean REST API** with versioned endpoints (`/v1/...`)

---

## 📋 Prerequisites

- Java 25+
- Maven (or use the included `mvnw` wrapper)
- Neo4j instance (local or cloud — [Neo4j AuraDB](https://neo4j.com/cloud/platform/aura-graph-database/) free tier works)
- [MovieLens 32M dataset](https://files.grouplens.org/datasets/movielens/ml-32m.zip) (`movies.csv` and `ratings.csv`)

---

## ⚙️ Setup & Installation

### 1. Clone the Repository

```bash
git clone https://github.com/abhi-amr/recommendation-service.git
cd recommendation-service
```

### 2. Configure Neo4j Connection

Set the following environment variables before running the app:

```bash
export NEO4J_URI=bolt://localhost:7687
export NEO4J_USERNAME=neo4j
export NEO4J_PASSWORD=yourpassword
export NEO4J_DATABASE=neo4j
```

> For Neo4j AuraDB (cloud), replace `NEO4J_URI` with your instance's connection URI and set `NEO4J_INSTANCE_ID` / `NEO4J_INSTANCE_NAME` as needed.

### 3. Download the Dataset

```bash
curl -O https://files.grouplens.org/datasets/movielens/ml-32m.zip
unzip ml-32m.zip
```

Place `movies.csv` and `ratings.csv` in a local directory (e.g., `/data/`).

### 4. Load the Data into Neo4j

Run these Cypher queries in Neo4j Browser or Cypher Shell:

```cypher
// Load movies
LOAD CSV WITH HEADERS FROM 'file:///data/movies.csv' AS row
CREATE (m:Movie {movieId: toInteger(row.movieId), title: row.title, genres: row.genres});

// Load users and ratings
LOAD CSV WITH HEADERS FROM 'file:///data/ratings.csv' AS row
MERGE (u:User {userId: toInteger(row.userId)})
MERGE (m:Movie {movieId: toInteger(row.movieId)})
CREATE (u)-[:RATED {rating: toFloat(row.rating), timestamp: toInteger(row.timestamp)}]->(m);
```

Or Simply download this python script from the below repo and run the script. 
More info can be found in README of the script.
```bash
https://github.com/abhi-amr/my-scripts/tree/main/recommendation-service
```

### 5. Run the Application

```bash
./mvnw spring-boot:run
```

The service starts at `http://localhost:8080`.

---

## 🔌 API Reference

Base URL: `http://localhost:8080/v1`

### Recommendations

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/v1/recommendations/hello` | Health check |
| `GET` | `/v1/recommendations/collaborative/{userId}` | User-based collaborative filtering recommendations |
| `GET` | `/v1/recommendations/movie/{movieId}` | Item-based recommendations for a given movie |
| `GET` | `/v1/recommendations/hybrid/{userId}` | Hybrid recommendations for a user |

### Movies

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/v1/movie/trending` | Get globally trending movies |

### Response Format

All recommendation endpoints return a list of `MovieRecommendationDto`:

```json
[
  {
    "movieId": 119667,
    "title": "Inception",
    "genres": "Action|Sci-Fi|Thriller",
    "averageRating": 4.2
  }
]
```

### Example Requests

```bash
# Collaborative filtering for user 5
curl http://localhost:8080/v1/recommendations/collaborative/5

# Item-based recommendations for movie 119667
curl http://localhost:8080/v1/recommendations/movie/119667

# Hybrid recommendations for user 5
curl http://localhost:8080/v1/recommendations/hybrid/5

# Trending movies
curl http://localhost:8080/v1/movie/trending
```

> A Postman collection is available in the [`postman-collection/`](./postman-collection) directory.

---

## 📁 Project Structure

```
recommendation-service/
├── src/
│   └── main/
│       └── java/           # Spring Boot application, controllers, services
├── postman-collection/     # Postman API collection for testing
├── .mvn/wrapper/           # Maven wrapper
├── pom.xml                 # Project dependencies
└── README.md
```

---

## 🤝 Contributing

Contributions are welcome! Ideas for improvement:
- Docker / docker-compose setup
- Pagination for recommendation results
- Unit and integration tests
- Content-based filtering as an additional strategy

---

## 📄 License

This project is open source. Dataset courtesy of [GroupLens Research](https://grouplens.org/).
