import React, { useEffect, useState } from "react";
import RecommendationListItem from "./RecommendationListItem";
import fetchCategories from "./fetchCategories";
import fetchRecommendations, {
  fetchRecommendationsByCategory,
} from "./fetchRecommendations";

export default function RecommendationList() {
  const [recommendations, setRecommendations] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState("any");

  useEffect(() => {
    fetchCategories().then(setCategories);
  }, []);

  useEffect(() => {
    const fetchRecommendationsData =
      selectedCategoryId === "any"
        ? fetchRecommendations
        : () => fetchRecommendationsByCategory(selectedCategoryId);

    fetchRecommendationsData().then(setRecommendations);
  }, [selectedCategoryId]);

  function handleCategoryFilterChange(event) {
    setSelectedCategoryId(event.target.value);
  }
  function handleDelete(id, title) {
    if (window.confirm(`Delete reading recommendation "${title}"?`)) {
      fetch(`/recommendations/${id}/delete`, {
        method: "POST",
      }).then(() => {
        setRecommendations(recommendations.filter((rec) => rec.id !== id));
      });
    }
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
            <th>Added on</th>
          </tr>
        </thead>
        <tbody>
          {recommendations.map((recommendation) => (
            <RecommendationListItem
              key={recommendation.id}
              recommendation={recommendation}
              onDelete={handleDelete}
            />
          ))}
        </tbody>
      </table>
      <a className="btn btn-primary" href="/add-recommendation">
        Add a reading recommendation
      </a>
    </div>
  );
}
