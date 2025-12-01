import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from "./pages/Login"
import Signup from './pages/Signup'
import ProductList from './pages/ProductList'
import PlaceOrder from './pages/PlaceOrder'
import Navbar from "./pages/Navbar";

function App() {

  return (
    <Router>
      <Navbar></Navbar>
      <Routes>
        <Route path="/" element={<Login  />}></Route>
        <Route path="/signup" element={<Signup />}></Route>
        <Route path="/products" element={<ProductList  />}></Route>
        <Route path="/order" element={<PlaceOrder />}></Route>
      </Routes>
    </Router>    
  )
}

export default App
