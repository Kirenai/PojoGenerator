import { createBrowserRouter, RouterProvider } from "react-router-dom";
import HomePage from "./sections/home/HomePage";
import Navbar from "./components/ui/navbar";
import PojoHome from "./sections/pojo/pojo-home";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Navbar />,
    children: [
      {
        path: "/",
        element: <HomePage />,
      },
      {
        path: "/pojo",
        element: <PojoHome />,
      },
    ],
  },
  {
    path: "*",
    element: <h1>Not Found</h1>,
  },
]);

function App() {
  return (
    <>
      <RouterProvider router={router} />
    </>
  );
}

export default App;
