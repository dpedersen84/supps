import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AppNavbar from "./components/layout/Navbar";
import Landing from "./components/layout/Landing";
import Login from "./components/login/Login";
import Register from "./components/register/Register";
import Products from "./components/products/Products";
import Detail from "./components/detail/Detail";
import Product from "./components/product/Product";
import Category from "./components/category/Category";
import Goal from "./components/goal/Goal";
import Order from "./components/order/Order";
import Admin from "./components/admin/Admin";
import Review from "./components/review/Review";
import Checkout from "./components/checkout/Checkout";
import OrderComplete from "./components/order-complete/OrderComplete";

const App = () => (
  <Router>
    <div>
      <AppNavbar />
      <Switch>
        <Route exact path="/" component={Landing} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/products" component={Products} />
        <Route exact path="/products/:id" component={Detail} />
        <Route exact path="/add/goal" component={Goal} />
        <Route exact path="/add/category" component={Category} />
        <Route exact path="/add/product" component={Product} />
        <Route exact path="/add/review/:id" component={Review} />
        <Route exact path="/edit/product/:id" component={Product} />
        <Route exact path="/order/:id" component={Order} />
        <Route exact path="/admin" component={Admin} />
        <Route exact path="/checkout" component={Checkout} />
        <Route exact path="/complete" component={OrderComplete} />
      </Switch>
    </div>
  </Router>
);

export default App;
