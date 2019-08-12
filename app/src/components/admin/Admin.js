import React, { Component } from "react";
import { Button, Table, Spinner } from "reactstrap";
import { Link } from "react-router-dom";
import "./Admin.css";

class Admin extends Component {
  _isMounted = false;

  constructor(props) {
    super(props);
    this.state = {
      products: [],
      orders: [],
      goals: [],
      categories: [],
      users: []
    };
  }

  componentDidMount() {
    this._isMounted = true;

    this.setState({
      isLoading: true
    });

    fetch("/api/goals")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            goals: data
          });
        }
      });

    fetch("/api/category")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            categories: data
          });
        }
      });

    fetch("/api/products")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            products: data
          });
        }
      });

    fetch("/api/orders")
      .then(res => res.json())
      .then(data => {
        if (this._isMounted) {
          this.setState({
            orders: data,
            isLoading: false
          });
        }
      });
  }

  componentDidUpdate(prevProps) {
    this._isMounted = true;

    if (this.state.products !== prevProps.products) {
      fetch("/api/products")
        .then(res => res.json())
        .then(data => {
          if (this._isMounted) {
            this.setState({
              products: data
            });
          }
        });
    }
  }

  componentWillUnmount() {
    this._isMounted = false;
  }

  async removeProduct(productId) {
    await fetch(`/api/products/${productId}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    }).then(() => {
      let updatedProducts = [...this.state.products].filter(
        p => p.productId !== productId
      );
      this.setState({ products: updatedProducts });
    });
  }

  async removeGoal(id) {
    await fetch(`/api/goals/${id}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    }).then(() => {
      let updatedGoals = [...this.state.goals].filter(g => g.id !== id);
      this.setState({ goals: updatedGoals });
    });
  }

  async removeCategory(id) {
    await fetch(`/api/category/${id}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json"
      }
    }).then(() => {
      let updatedCats = [...this.state.categories].filter(c => c.id !== id);
      this.setState({ categories: updatedCats });
    });
  }

  render() {
    const { products, goals, categories, isLoading, orders } = this.state;

    if (isLoading) {
      return (
        <div className="admin mt-5">
          <div className="container">
            <Spinner style={{ width: "6rem", height: "6rem" }} type="grow" />;
          </div>
        </div>
      );
    }

    const productData = products.map(product => {
      return (
        <tr key={product.productId}>
          <td>{product.productId}</td>
          <td>{product.name}</td>
          <td>${product.price}.00</td>
          <td>{product.inventory}</td>
          <td>
            <Button
              size="sm"
              outline
              color="primary"
              tag={Link}
              to={`/edit/product/${product.productId}`}
            >
              Edit
            </Button>
            <Button
              size="sm"
              outline
              color="danger"
              className="ml-1"
              onClick={() => this.removeProduct(product.productId)}
            >
              Delete
            </Button>
          </td>
        </tr>
      );
    });

    const orderData = orders.map(order => {
      return (
        <tr key={order.orderId}>
          <td>{order.orderId}</td>
          <td>{order.userId}</td>
          <td>${order.totalPrice}.00</td>
          <td>
            {new Intl.DateTimeFormat("en-US", {
              year: "numeric",
              month: "long",
              day: "2-digit"
            }).format(new Date(order.orderDate))}
          </td>
          <td>
            <Button
              size="sm"
              outline
              color="info"
              tag={Link}
              to={`/orders/${order.orderId}`}
            >
              View
            </Button>
          </td>
        </tr>
      );
    });

    const goalData = goals.map(g => {
      return (
        <tr key={g.id}>
          <td>{g.id}</td>
          <td>{g.name}</td>
          <td>
            <Button
              size="sm"
              outline
              color="danger"
              onClick={() => this.removeGoal(g.id)}
            >
              Delete
            </Button>
          </td>
        </tr>
      );
    });

    const categoryData = categories.map(c => {
      return (
        <tr key={c.id}>
          <td>{c.id}</td>
          <td>{c.name}</td>
          <td>
            <Button
              size="sm"
              outline
              color="danger"
              onClick={() => this.removeCategory(c.id)}
            >
              Delete
            </Button>
          </td>
        </tr>
      );
    });

    return (
      <div className="admin mt-5">
        <div className="container">
          <h1>Admin Tools</h1>
          <hr />
          <div className="productTable">
            <div className="float-right">
              <Button outline color="success" tag={Link} to="/add/product">
                Add Product
              </Button>
            </div>
            <h3>Products</h3>
            <Table striped bordered className="mt-4">
              <thead>
                <tr>
                  <th width="10%">Id</th>
                  <th width="15%">Name</th>
                  <th width="10%">Price</th>
                  <th width="10%">Inventory</th>
                  <th width="20%">Actions</th>
                </tr>
              </thead>
              <tbody>{productData}</tbody>
            </Table>
          </div>
          <hr />
          <div className="orderTable">
            <h3>Past Orders</h3>
            <Table striped bordered className="mt-4">
              <thead>
                <tr>
                  <th width="10%">Id</th>
                  <th width="10%">UserId</th>
                  <th width="10%">Total Price</th>
                  <th width="10%">Order Date</th>
                  <th width="20%">Actions</th>
                </tr>
              </thead>
              <tbody>{orderData}</tbody>
            </Table>
          </div>
          <hr />
          <div className="row">
            <div className="col-md-6">
              <div className="float-right">
                <Button outline color="success" tag={Link} to="/add/goal">
                  Add Goal
                </Button>
              </div>
              <h3>Goals</h3>
              <Table striped bordered className="mt-4">
                <thead>
                  <tr>
                    <th width="10%">Id</th>
                    <th width="10%">Name</th>
                    <th width="15%">Actions</th>
                  </tr>
                </thead>
                <tbody>{goalData}</tbody>
              </Table>
            </div>
            <div className="col-md-6">
              <div className="float-right">
                <Button outline color="success" tag={Link} to="/add/category">
                  Add Category
                </Button>
              </div>
              <h3>Categories</h3>
              <Table striped bordered className="mt-4">
                <thead>
                  <tr>
                    <th width="10%">Id</th>
                    <th width="10%">Name</th>
                    <th width="20%">Actions</th>
                  </tr>
                </thead>
                <tbody>{categoryData}</tbody>
              </Table>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Admin;
