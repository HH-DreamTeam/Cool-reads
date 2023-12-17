import React, { useEffect, useState } from "react";
import RecommendationListItem from "./RecommendationListItem";
import fetchCategories from "./fetchCategories";

export default function RecommendationList() {
  const [recommendations, setRecommendations] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState("any");
  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    fetchCategories().then(setCategories);
    fetchRecommendations(selectedCategoryId);
    checkAuthentication();
  }, []);

  useEffect(() => {
    fetchRecommendations(selectedCategoryId);
  }, [selectedCategoryId]);

  function fetchRecommendations(categoryId) {
    let url = "/api/recommendations";
    if (categoryId !== "any") {
      url = `/api/categories/${categoryId}/recommendations`;
    }
    fetch(url)
      .then((response) => response.json())
      .then((data) => setRecommendations(data));
  }

  function handleCategoryFilterChange(event) {
    setSelectedCategoryId(event.target.value);
  }

  function handleDelete(id, title) {
    const csrfToken = document.getElementById("_csrf").getAttribute("content");

    if (window.confirm(`Delete reading recommendation "${title}"?`)) {
      fetch(`/recommendations/${id}/delete`, {
        method: "POST",
        headers: {
          "X-CSRF-TOKEN": csrfToken,
        },
      })
        .then((response) => {
          if (response.ok) {
            setRecommendations(recommendations.filter((rec) => rec.id !== id));
          } else {
            console.error("Delete failed");
          }
        })
        .catch((error) => console.error("Error:", error));
    }
  }

  function checkAuthentication() {
    fetch("/api/users/current")
      .then((response) => response.json())
      .then((data) => {
        setCurrentUser(data);
      })
      .catch(() => setCurrentUser(null));
  }

  return (
    <div>
      <div className="mb-3">
        <label className="form-label">Filter by a category</label>
        <select
          className="form-select"
          onChange={handleCategoryFilterChange}
          value={selectedCategoryId}
        >
          <option value="any">Any category</option>
          {categories.map((category) => (
            <option value={category.id} key={category.id}>
              {category.name}
            </option>
          ))}
        </select>
      </div>
      <h1>Reading Recommendations</h1>
      <table className="table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Link</th>
            <th>Description</th>
            <th>Category</th>
            <th>Added by</th>
            <th>Added on</th>
          </tr>
        </thead>
        <tbody>
          {recommendations.map((recommendation) => (
            <RecommendationListItem
              key={recommendation.id}
              recommendation={recommendation}
              currentUser={currentUser}
              onDelete={handleDelete}
            />
          ))}
        </tbody>
      </table>
      {currentUser && (
        <a className="btn btn-primary" href="/add-recommendation">
          Add a reading recommendation
        </a>
      )}
    </div>
  );
}
