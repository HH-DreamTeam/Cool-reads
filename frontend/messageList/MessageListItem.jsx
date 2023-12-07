import React from "react";

export default function MessageListItem(props) {
  return <li>{props.message.content}</li>;
}