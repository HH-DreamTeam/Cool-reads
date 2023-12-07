export default function fetchRecommendations() {
  return fetch("/api/recommendations").then((response) => response.json());
}
