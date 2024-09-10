import React, { useState } from "react";
import { Content } from "./Content";
import { Header } from "./Header";
import "./TodoList.scss";

export function TodoList() {
  const [itemsList, setItemsList] = useState([
    {
      id: 1,
      content: "Item 1"
    },
  ]);

  const [searchTerm, setSearchTerm] = useState<string>('');

  return (
    <>
      <Header setSearchTerm={setSearchTerm} />
      <Content itemsList={itemsList} setItemsList={setItemsList} searchTerm={searchTerm} />
    </>
  );
}
