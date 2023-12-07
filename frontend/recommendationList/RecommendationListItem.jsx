import React from "react";

export default function RecommendationListItem(props) {
  const { recommendation,  onDelete} = props;
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
          <button onClick={() => onDelete(id, title)} className="btn btn-danger">
            Delete
          </button>
      </td>
    </tr>
  );
}