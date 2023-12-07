import React, { useEffect, useState } from "react";
import RecommendationListItem from "./RecommendationListItem";
import fetchRecommendations from "./fetchRecommendations";

export default function RecommendationList() {
  const [recommendations, setRecommendations] = useState([]);

  useEffect(() => {
    fetchRecommendations().then(setRecommendations);
  }, []);

  return (
    <div>
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
