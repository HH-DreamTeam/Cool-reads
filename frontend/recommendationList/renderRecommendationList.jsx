import React from "react";
import { createRoot } from "react-dom/client";
import RecommendationList from "./RecommendationList";

const root = createRoot(document.getElementById("recommendationListRoot"));
root.render(<RecommendationList />);
