import React, { useEffect, useState } from "react";
import RecommendationListItem from "./RecommendationListItem";
import fetchRecommendations from "./fetchRecommendations";

export default function RecommendationList() {
  const [recommendations, setRecommendations] = useState([]);

  useEffect(() => {
    fetchRecommendations().then((fetchedRecommendations) => setRecommendations(fetchedRecommendations));
  }, []);

  return (
    <div>
      <h1>Recommendations</h1>

      <ul>
        {recommendations.map((recommendation) => (
          <RecommendationListItem recommendation={recommendation} key={recommendation.id} />
        ))}
      </ul>

      <a className="btn btn-primary" href="/add-recommendation">
        Add a recommendation
      </a>
    </div>
  );
}
