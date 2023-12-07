import React from "react";

export default function RecommendationListItem(props) {
  const { recommendation } = props;
  const { id, title, link, description, category, createdAt } = recommendation;

  return (
    <tr>
      <td>{title}</td>
      <td>
        <a href={link} target="_blank" rel="noopener noreferrer">
          {link}
        </a>
      </td>
      <td>{description}</td>
      <td>{category ? category.name : "No Category"}</td>
      <td>{new Date(createdAt).toLocaleDateString()}</td>
      <td>
        <a className="btn btn-primary" href={`/recommendations/edit/${id}`}>
          Edit
        </a>
      </td>
      <td>
        <form method="POST" action={`/recommendations/${id}/delete`}>
          <button type="submit" className="btn btn-danger">
            Delete
          </button>
        </form>
      </td>
    </tr>
  );
}
