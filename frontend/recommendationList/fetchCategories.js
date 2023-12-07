export default function fetchCategories() {
    return fetch("/api/categories").then((response) => response.json());
  }