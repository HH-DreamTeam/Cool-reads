import React from "react";

export default function RecommendationListItem(props) {
  const { recommendation, currentUser, onDelete } = props;
  const { id, title, link, description, category, user, createdAt } =
    recommendation;
  const canEditDelete =
    currentUser && user && currentUser.username === user.username;

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
      <td>{user ? user.username : "Unknown"}</td>
      <td>{new Date(createdAt).toLocaleDateString()}</td>
      {canEditDelete && (
        <>
          <td>
            <a className="btn btn-primary" href={`/recommendations/edit/${id}`}>
              Edit
            </a>
          </td>
          <td>
            <button
              onClick={() => onDelete(id, title)}
              className="btn btn-danger"
            >
              Delete
            </button>
          </td>
        </>
      )}
    </tr>
  );
}
