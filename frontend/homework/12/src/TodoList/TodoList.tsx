import { Content } from "./Content";
import { Header } from "./Header";
import "./TodoList.scss";
import { TodoProvider } from './TodoContext';

export function TodoList() {
  return (
    <TodoProvider>
      <Header />
      <Content />
    </TodoProvider>
  );
}



