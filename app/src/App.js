import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AppNavbar from "./components/layout/Navbar";
import Landing from "./components/layout/Landing";
import Login from "./components/login/Login";
import Register from "./components/register/Register";
import Products from "./components/products/Products";
import Detail from "./components/detail/Detail";
import Category from "./components/category/Category";
import Goal from "./components/goal/Goal";
import Order from "./components/order/Order";

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
        <Route exact path="/goals" component={Goal} />
        <Route exact path="/categories" component={Category} />
        <Route exact path="/order" component={Order} />
      </Switch>
    </div>
  </Router>
);

export default App;
