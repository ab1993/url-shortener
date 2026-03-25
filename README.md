# 🔗 URL Shortener Service (Spring Boot + Kubernetes)

A scalable URL shortening service built using **Java, Spring Boot, MongoDB, Redis, and Kubernetes**.
This project demonstrates backend system design concepts like **horizontal scaling, caching, and load balancing**.

---

## 🚀 Features

* Shorten long URLs → unique short IDs
* Redirect short URL → original URL
* Base62 encoding for compact URLs
* Distributed ID generation using Snowflake
* Redis caching for fast lookups
* MongoDB for persistent storage
* Kubernetes deployment with multiple replicas
* Load balancing via K8s Service

---

## 🧠 Architecture

```
Client
   ↓
Kubernetes Service (Load Balancer)
   ↓
Multiple Spring Boot Pods
   ↓
Redis (Cache)
   ↓
MongoDB (Database)
```

---

## ⚙️ Tech Stack

* **Backend:** Java, Spring Boot
* **Database:** MongoDB
* **Cache:** Redis
* **Containerization:** Docker
* **Orchestration:** Kubernetes (Minikube)
* **Load Testing:** k6

---

## 📡 API Endpoints

### 🔹 Create Short URL

POST /shorten

Request:

```json
{
  "longUrl": "https://example.com"
}
```

Response:

```json
{
  "shortId": "abc123",
  "shortUrl": "http://localhost:8080/abc123",
  "longUrl": "https://example.com"
}
```

---

### 🔹 Redirect

GET /{shortId}

Response:

* 302 Redirect → original URL

---

## 🏗️ How to Run

### 1. Build Project

```
mvn clean package
```

### 2. Build Docker Image

```
docker build -t url-shortener:latest .
```

### 3. Start Minikube

```
minikube start --driver=docker
```

### 4. Deploy to Kubernetes

```
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

### 5. Access Service

```
minikube ip
```

Open in browser:

```
http://<minikube-ip>:30007
```

---

## 📈 Scaling

* Horizontal scaling using Kubernetes replicas
* Load balancing via K8s Service

Scale manually:

```
kubectl scale deployment url-shortener --replicas=10
```

---

## 🔥 Future Improvements

* Custom short URLs
* URL expiration (TTL)
* Analytics (click tracking)
* Rate limiting
* Redis cluster
* MongoDB replica set

---

## 👨‍💻 Author

Abhishek Sharma
