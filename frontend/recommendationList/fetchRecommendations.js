export default function fetchRecommendations() {
  return fetch("/api/recommendations").then((response) => response.json());
}

export function fetchRecommendationsByCategory(categoryId) {
  return fetch(`/api/categories/${categoryId}/recommendations`).then((response) => response.json());
}